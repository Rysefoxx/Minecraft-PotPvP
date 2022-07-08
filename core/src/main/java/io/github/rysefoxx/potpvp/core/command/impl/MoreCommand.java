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
import io.github.rysefoxx.potpvp.core.util.Maths;
import io.github.rysefoxx.potpvp.core.util.PlayerUtils;
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
@BaseCommand(command = "more")
public class MoreCommand extends BaseExecutor {

    public MoreCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;
        if (!player.hasPermission(PermissionConstants.MORE)) {
            player.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        if(args.length == 1) {
            if(!Maths.isInteger(args[0], false)) {
                player.sendMessage(StringConstants.PARSE_ERROR);
                return;
            }
            if(PlayerUtils.emptyHand(player)) {
                player.sendMessage(StringConstants.EMPTY_HAND);
                return;
            }

            int amount = Integer.parseInt(args[0]);
            player.getInventory().getItemInMainHand().setAmount(amount);

            player.sendMessage(PrefixConstants.MORE.append(Component.text("Du hast das Item nun " + amount + "x", NamedTextColor.GRAY)));
            return;
        }

        help(player, List.of("More <Anzahl>"));
    }
}
