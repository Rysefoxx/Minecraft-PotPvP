package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.ExecutionType;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Alias;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseExecution;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Prefix;
import io.github.rysefoxx.potpvp.core.constant.PermissionConstants;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import io.github.rysefoxx.potpvp.core.util.Utils;
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
@Alias(values = "tc")
@BaseCommand(command = "teamchat")
public class TeamChatCommand extends BaseExecutor {

    public TeamChatCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (sender.hasPermission(PermissionConstants.TEAM_CHAT)) {
            sender.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        if (args.length >= 1) {
            String message = Utils.buildMessage(args, 0);

            if (message.isBlank()) {
                sender.sendMessage(StringConstants.ERROR.append(Component.text("Bitte gib einen Text ein.", NamedTextColor.GRAY)));
                return;
            }

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (!onlinePlayer.hasPermission(PermissionConstants.TEAM_CHAT)) continue;
                onlinePlayer.sendMessage(PrefixConstants.TEAM_CHAT.append(
                        Component.text(sender.getName(), NamedTextColor.GOLD))
                                .append(Component.text(": ", NamedTextColor.GRAY))
                        .append(Component.text(message, NamedTextColor.WHITE)));
            }
            return;
        }

        help(sender, List.of("Teamchat <Nachricht>"));
    }
}
