package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
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
 * @since 7/8/2022
 */
@BaseCommand(command = "fly")
public class FlyCommand extends BaseExecutor {

    public FlyCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            if (!player.hasPermission(PermissionConstants.FLY_PERSONAL)) {
                player.sendMessage(StringConstants.NO_PERMISSION);
                return;
            }

            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage(PrefixConstants.FLY.append(Component.text("Du hast deinen Flugmodus deaktiviert.", NamedTextColor.GRAY)));
                return;
            }
            player.setAllowFlight(true);
            player.sendMessage(PrefixConstants.FLY.append(Component.text("Du hast deinen Flugmodus aktiviert.", NamedTextColor.GRAY)));
            return;
        }
        if (args.length == 1) {
            if (!player.hasPermission(PermissionConstants.FLY_TARGET)) {
                player.sendMessage(StringConstants.NO_PERMISSION);
                return;
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(StringConstants.PLAYER_OFFLINE);
                return;
            }

            if (target.getAllowFlight()) {
                target.setAllowFlight(false);
                target.setFlying(false);
                target.sendMessage(PrefixConstants.FLY.append(Component.text("Dein Flugmodus wurde deaktiviert.", NamedTextColor.GRAY)));
                player.sendMessage(PrefixConstants.FLY.append(Component.text(target.getName(), NamedTextColor.AQUA))
                        .append(Component.text(" kann nicht mehr fliegen.", NamedTextColor.GRAY)));
                return;
            }
            target.setAllowFlight(true);
            target.sendMessage(PrefixConstants.FLY.append(Component.text("Dein Flugmodus wurde aktiviert.", NamedTextColor.GRAY)));
            player.sendMessage(PrefixConstants.FLY.append(Component.text(target.getName(), NamedTextColor.AQUA))
                    .append(Component.text(" kann nun fliegen.", NamedTextColor.GRAY)));

            return;
        }

        help(sender, Arrays.asList("Fly", "Fly <Player>"), Arrays.asList(PermissionConstants.FLY_PERSONAL, PermissionConstants.FLY_TARGET));

    }
}
