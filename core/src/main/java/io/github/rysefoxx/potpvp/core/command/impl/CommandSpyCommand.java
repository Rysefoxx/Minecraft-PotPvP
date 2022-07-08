package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Alias;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
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

import java.util.Arrays;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/7/2022
 */
@Alias(values = "cmdspy")
@BaseCommand(command = "commandspy")
public class CommandSpyCommand extends BaseExecutor {

    public CommandSpyCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            if (!player.hasPermission(PermissionConstants.CMD_SPY_PERSONAL)) {
                player.sendMessage(StringConstants.NO_PERMISSION);
                return;
            }

            plugin().getSpyManager().toggleSpy(player.getUniqueId());

            if (plugin().getSpyManager().inCommandSpy(player.getUniqueId())) {
                player.sendMessage(PrefixConstants.SPY.append(Component.text("Du wirst nun über ausgeführte Befehle informiert.", NamedTextColor.GRAY)));
                return;
            }
            player.sendMessage(PrefixConstants.SPY.append(Component.text("Du wirst nicht mehr über ausgeführte Befehle informiert.", NamedTextColor.GRAY)));
            return;
        }
        if (args.length == 1) {
            if (!player.hasPermission(PermissionConstants.CMD_SPY_TARGET)) {
                player.sendMessage(StringConstants.NO_PERMISSION);
                return;
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(StringConstants.PLAYER_OFFLINE);
                return;
            }
            plugin().getSpyManager().toggleSpy(target.getUniqueId());

            if (plugin().getSpyManager().inCommandSpy(target.getUniqueId())) {
                target.sendMessage(PrefixConstants.SPY.append(Component.text("Du wirst nun über ausgeführte Befehle informiert.", NamedTextColor.GRAY)));
                player.sendMessage(PrefixConstants.SPY.append(Component.text(target.getName(), NamedTextColor.AQUA)
                                .append(Component.text(" wird nun über ausgeführte Befehle informiert.", NamedTextColor.GRAY))));
                return;
            }
            target.sendMessage(PrefixConstants.SPY.append(Component.text("Du wirst nicht mehr über ausgeführte Befehle informiert.", NamedTextColor.GRAY)));
            player.sendMessage(PrefixConstants.SPY.append(Component.text(target.getName(), NamedTextColor.AQUA)
                    .append(Component.text(" wird nicht mehr über ausgeführte Befehle informiert.", NamedTextColor.GRAY))));

            return;
        }

        help(sender, Arrays.asList("Commandspy", "Commandspy <Player>"), Arrays.asList(PermissionConstants.CMD_SPY_PERSONAL, PermissionConstants.CMD_SPY_TARGET));
    }
}
