package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.ExecutionType;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseExecution;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Prefix;
import io.github.rysefoxx.potpvp.core.constant.PermissionConstants;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@BaseExecution(type = ExecutionType.BOTH)
@BaseCommand(command = "kill")
public class KillCommand extends BaseExecutor {

    public KillCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (!sender.hasPermission(PermissionConstants.KILL)) {
            sender.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        if (args.length != 1) {
            help(sender, List.of("Kill <Player>"));
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage(StringConstants.PLAYER_OFFLINE);
            return;
        }

        target.setHealth(0);

        sender.sendMessage(PrefixConstants.KILL.append(Component.text("Du hast ", NamedTextColor.GRAY))
                .append(Component.text(target.getName(), NamedTextColor.AQUA))
                .append(Component.text(" getötet.", NamedTextColor.GRAY)));
    }
}
