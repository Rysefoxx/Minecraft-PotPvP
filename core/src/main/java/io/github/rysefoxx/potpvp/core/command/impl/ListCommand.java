package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.ExecutionType;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseExecution;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@BaseExecution(type = ExecutionType.BOTH)
@BaseCommand(command = "list")
public class ListCommand extends BaseExecutor {

    public ListCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        sender.sendMessage(PrefixConstants.INFO.append(
                        Component.text("Es spielen derzeit ", NamedTextColor.GRAY))
                .append(Component.text(Bukkit.getOnlinePlayers().size(), NamedTextColor.AQUA))
                .append(Component.text(" Spieler auf dem Server.", NamedTextColor.GRAY)));

    }
}
