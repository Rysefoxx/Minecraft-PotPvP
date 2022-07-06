package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
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
@BaseCommand(command = "clear")
public class ClearCommand extends BaseExecutor {

    public ClearCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            if (!player.hasPermission(PermissionConstants.CLEAR_PERSONAL)) {
                player.sendMessage(StringConstants.NO_PERMISSION);
                return;
            }

            player.getInventory().setArmorContents(null);
            player.getInventory().clear();

            player.sendMessage(PrefixConstants.CLEAR.append(Component.text("Du hast dein Inventar geleert.", NamedTextColor.GREEN)));
            return;
        }
        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(StringConstants.PLAYER_OFFLINE);
                return;
            }

            target.getInventory().setArmorContents(null);
            target.getInventory().clear();


            target.sendMessage(PrefixConstants.CLEAR
                    .append(Component.text("Dein Inventar wurde von ", NamedTextColor.GREEN))
                    .append(Component.text(player.getName(), NamedTextColor.AQUA))
                    .append(Component.text(" geleert.", NamedTextColor.GREEN)));
            player.sendMessage(PrefixConstants.CLEAR.append(Component.text("Du hast das Inventar erfolgreich geleert.", NamedTextColor.GREEN)));
            return;
        }

        help(sender, Arrays.asList("Clear", "Clear <Player>"), Arrays.asList(PermissionConstants.CLEAR_PERSONAL, PermissionConstants.CLEAR_TARGET));
    }
}
