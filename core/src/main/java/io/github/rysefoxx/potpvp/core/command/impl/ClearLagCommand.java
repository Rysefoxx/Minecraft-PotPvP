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
import io.github.rysefoxx.potpvp.core.manager.LagManager;
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
@Alias(values = "cl")
@BaseCommand(command = "clearlag")
public class ClearLagCommand extends BaseExecutor {

    public ClearLagCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (!sender.hasPermission(PermissionConstants.CLEAR_LAG)) {
            sender.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        if(args.length != 1) {
            help(sender, List.of("Clearlag <Flag> (-M, -I, -A, -AL)"));
            return;
        }
        LagManager.Type type = LagManager.Type.getType(args[0]);
        if(type == null) {
            help(sender, List.of("Clearlag <Flag> (-M, -I, -A, -AL)"));
            return;
        }

        plugin().getLagManager().remove(type);
    }
}
