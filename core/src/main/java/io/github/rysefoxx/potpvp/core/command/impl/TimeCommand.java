package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.ExecutionType;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Alias;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseExecution;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Prefix;
import io.github.rysefoxx.potpvp.core.constant.PermissionConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import io.github.rysefoxx.potpvp.core.util.Maths;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@BaseExecution(type = ExecutionType.BOTH)
@BaseCommand(command = "time")
public class TimeCommand extends BaseExecutor {

    public TimeCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (!sender.hasPermission(PermissionConstants.TIME)) {
            sender.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
            if (args[1].equalsIgnoreCase("day")) {
                Bukkit.getWorlds().forEach(world -> world.setTime(1000));
                return;
            }
            if (args[1].equalsIgnoreCase("night")) {
                Bukkit.getWorlds().forEach(world -> world.setTime(23000));
                return;
            }
            if (!Maths.isInteger(args[1], true)) {
                sender.sendMessage(StringConstants.PARSE_ERROR);
                return;
            }
            int time = Integer.parseInt(args[1]);
            Bukkit.getWorlds().forEach(world -> world.setTime(time));
            return;
        }

        help(sender, Arrays.asList("Time set day", "Time set night", "Time set <Zeit>"));
    }
}
