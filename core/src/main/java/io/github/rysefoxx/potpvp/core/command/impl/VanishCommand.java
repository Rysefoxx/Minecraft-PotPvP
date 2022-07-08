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
 * @since 7/8/2022
 */
@Alias(values = {"v", "hide"})
@BaseCommand(command = "vanish")
public class VanishCommand extends BaseExecutor {

    public VanishCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            if (!player.hasPermission(PermissionConstants.VANISH_PERSONAL)) {
                player.sendMessage(StringConstants.NO_PERMISSION);
                return;
            }
            plugin().getGeneralManager().toggleVanish(player.getUniqueId());

            if (plugin().getGeneralManager().inVanish(player.getUniqueId())) {
                player.sendMessage(PrefixConstants.VANISH
                        .append(Component.text("Du bist jetzt ", NamedTextColor.GRAY))
                        .append(Component.text("unsichtbar.", NamedTextColor.AQUA)));
                return;
            }
            player.sendMessage(PrefixConstants.VANISH
                    .append(Component.text("Du bist jetzt ", NamedTextColor.GRAY))
                    .append(Component.text("sichtbar.", NamedTextColor.AQUA)));
            return;
        }
        if (args.length == 1) {
            if (!player.hasPermission(PermissionConstants.VANISH_TARGET)) {
                player.sendMessage(StringConstants.NO_PERMISSION);
                return;
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(StringConstants.PLAYER_OFFLINE);
                return;
            }
            plugin().getGeneralManager().toggleVanish(target.getUniqueId());

            if (plugin().getGeneralManager().inVanish(target.getUniqueId())) {
                target.sendMessage(PrefixConstants.VANISH
                        .append(Component.text("Du bist jetzt ", NamedTextColor.GRAY))
                        .append(Component.text("unsichtbar.", NamedTextColor.AQUA)));

                player.sendMessage(PrefixConstants.VANISH
                        .append(Component.text(target.getName(), NamedTextColor.AQUA))
                        .append(Component.text("ist jetzt ", NamedTextColor.GRAY))
                        .append(Component.text("unsichtbar.", NamedTextColor.AQUA)));
                return;
            }
            target.sendMessage(PrefixConstants.VANISH
                    .append(Component.text("Du bist jetzt ", NamedTextColor.GRAY))
                    .append(Component.text("sichtbar.", NamedTextColor.AQUA)));

            player.sendMessage(PrefixConstants.VANISH
                    .append(Component.text(target.getName(), NamedTextColor.AQUA))
                    .append(Component.text("ist jetzt ", NamedTextColor.GRAY))
                    .append(Component.text("sichtbar.", NamedTextColor.AQUA)));
            return;
        }

        help(player, Arrays.asList("Vanish", "Vanish <Spieler>"), Arrays.asList(PermissionConstants.VANISH_PERSONAL, PermissionConstants.VANISH_TARGET));
    }
}
