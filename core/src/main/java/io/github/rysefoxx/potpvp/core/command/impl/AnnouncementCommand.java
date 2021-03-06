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
import io.github.rysefoxx.potpvp.core.util.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
        if (!sender.hasPermission(PermissionConstants.ANNOUNCEMENT)) {
            sender.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        String message = ChatColor.translateAlternateColorCodes('&', Utils.buildMessage(args, 0));

        if (message.isBlank()) {
            sender.sendMessage(StringConstants.ERROR.append(Component.text("Bitte gib einen Text ein.", NamedTextColor.GRAY)));
            return;
        }

        Bukkit.broadcast(PrefixConstants.ANNOUNCEMENT.append(Component.text(message)));
    }
}
