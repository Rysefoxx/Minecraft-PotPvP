package io.github.rysefoxx.potpvp.core.command.extension;

import com.google.common.base.Preconditions;
import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.*;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/6/2022
 */
public abstract class BaseExecutor implements CommandExecutor, Listener {

    private final CorePlugin plugin;

    private @Getter
    final @NotNull ExecutionType executionType =
            getClass().isAnnotationPresent(BaseExecution.class) ? getClass().getAnnotation(BaseExecution.class).type() : ExecutionType.PLAYER;
    private @Getter
    final @NotNull BaseCommand baseCommand = getClass().getAnnotation(BaseCommand.class);
    private @Getter
    final @Nullable Alias alias = getClass().getAnnotation(Alias.class);

    private @Getter
    final @NotNull List<AlternativeCommand> alternativeCommands = new ArrayList<>();

    private @Nullable Prefix prefix;

    public BaseExecutor(@NotNull CorePlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);

        init();
    }

    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
    }

    public void help(@NotNull CommandSender sender, @NotNull List<String> messages) {
        if (this.prefix != null && this.prefix.append()) {
            messages.forEach(message -> sender.sendMessage("§cVerwendung: §7/" + message));
            return;
        }
        messages.forEach(sender::sendMessage);
    }

    public void help(@NotNull CommandSender sender, @NotNull List<String> messages, @NotNull List<String> permissions) {
        Preconditions.checkArgument(permissions.size() == messages.size(), "Permissions and messages must be the same size");

        int sent = 0;

        for (int i = 0; i < messages.size(); i++) {
            String permission = permissions.get(i);
            if (!sender.hasPermission(permission)) continue;

            sent++;

            String message = messages.get(i);
            sender.sendMessage(this.prefix != null && this.prefix.append() ? "§cVerwendung: §7/" + message : message);
        }
        if (sent > 0) return;

        sender.sendMessage(StringConstants.NO_PERMISSION);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (this.executionType == ExecutionType.BOTH) {
            if (triggerAlternativeCommand(sender, label, args)) return true;
            trigger(sender, command, label, args);
            return true;
        }

        if (this.executionType == ExecutionType.PLAYER) {
            if (!(sender instanceof Player player)) return false;
            if (triggerAlternativeCommand(sender, label, args)) return true;
            trigger(player, command, label, args);
            return true;
        }

        if (this.executionType == ExecutionType.CONSOLE) {
            if (sender instanceof Player) return false;
            if (triggerAlternativeCommand(sender, label, args)) return true;
            trigger(sender, command, label, args);
            return true;
        }

        throw new IllegalStateException("[BaseExecutor.java] ExecutionType is not set correctly!");
    }

    private void init() {
        initPrefix();
        initAlternativeCommands();
    }

    private void initAlternativeCommands() {
        for (Method method : getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(AlternativeCommand.class)) continue;

            AlternativeCommand alternativeCommand = method.getAnnotation(AlternativeCommand.class);
            this.alternativeCommands.add(alternativeCommand);
        }
    }

    private void initPrefix() {
        for (Method method : getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Prefix.class)) continue;

            this.prefix = method.getAnnotation(Prefix.class);
            break;
        }
    }

    private boolean triggerAlternativeCommand(@NotNull CommandSender sender, @NotNull String label, String @NotNull [] args) {
        boolean executedAtLeastOne = false;
        for (AlternativeCommand alternativeCommand : this.alternativeCommands) {
            boolean execute = alternativeCommand.command().equalsIgnoreCase(label);

            if (!execute && alternativeCommand.aliases().length > 0) {
                for (String s : alternativeCommand.aliases()) {
                    if (!s.equalsIgnoreCase(label)) continue;

                    execute = true;
                    break;
                }
            }

            if (!execute) continue;

            Method method = findByAnnotation(alternativeCommand);
            if (method == null) continue;

            boolean access = method.canAccess(this);
            method.setAccessible(true);

            int counter = method.getParameterCount();

            try {
                if (counter == 1) {
                    method.invoke(this, sender);
                    executedAtLeastOne = true;
                    continue;
                }
                method.invoke(this, sender, args);
                executedAtLeastOne = true;
            } catch (IllegalAccessException | InvocationTargetException e) {
                this.plugin.logger().log(Level.SEVERE, "Error while executing alternative command", e);
            }
            method.setAccessible(access);
        }
        return executedAtLeastOne;
    }

    private @Nullable Method findByAnnotation(@NotNull AlternativeCommand command) {
        for (Method method : getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(AlternativeCommand.class)) continue;
            if (!method.getAnnotation(AlternativeCommand.class).equals(command)) continue;

            return method;
        }
        return null;
    }

    public @NotNull CorePlugin plugin() {
        return this.plugin;
    }
}
