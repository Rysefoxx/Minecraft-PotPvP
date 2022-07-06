package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.ExecutionType;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Alias;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseExecution;
import io.github.rysefoxx.potpvp.core.constant.PermissionConstants;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/7/2022
 */
@BaseExecution(type = ExecutionType.BOTH)
@Alias(values = {"announce", "alert"})
@BaseCommand(command = "announcement")
public class AnnouncementCommand extends BaseExecutor {

    public AnnouncementCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (sender instanceof Player player && !player.hasPermission(PermissionConstants.ANNOUNCEMENT)) {
            player.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            String arg = ChatColor.translateAlternateColorCodes('&', args[i]);

            if (i + 1 >= args.length) {
                builder.append(arg);
                continue;
            }
            builder.append(arg).append(" ");
        }
        if (builder.isEmpty()) {
            sender.sendMessage(StringConstants.ERROR + "Bitte gib einen Text ein.");
            return;
        }

        Bukkit.broadcast(PrefixConstants.ANNOUNCEMENT.append(Component.text(builder.toString())));
    }
}
