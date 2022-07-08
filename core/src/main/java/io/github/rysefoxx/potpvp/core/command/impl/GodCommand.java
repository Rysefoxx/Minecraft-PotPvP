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
 * @since 7/8/2022
 */
@BaseCommand(command = "god")
public class GodCommand extends BaseExecutor {

    public GodCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            if (!player.hasPermission(PermissionConstants.GOD_PERSONAL)) {
                player.sendMessage(StringConstants.NO_PERMISSION);
                return;
            }
            plugin().getGeneralManager().toggleGod(player.getUniqueId());

            if (plugin().getGeneralManager().inGod(player.getUniqueId())) {
                player.sendMessage(PrefixConstants.GOD.append(Component.text("Du hast deinen Gottmodus ", NamedTextColor.GRAY)).append(Component.text("aktiviert.", NamedTextColor.GREEN)));
                return;
            }
            player.sendMessage(PrefixConstants.GOD.append(Component.text("Du hast deinen Gottmodus ", NamedTextColor.GRAY)).append(Component.text("deaktiviert.", NamedTextColor.RED)));
            return;
        }
        if (args.length == 1) {
            if (!player.hasPermission(PermissionConstants.GOD_TARGET)) {
                player.sendMessage(StringConstants.NO_PERMISSION);
                return;
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(StringConstants.PLAYER_OFFLINE);
                return;
            }

            plugin().getGeneralManager().toggleGod(target.getUniqueId());

            if (plugin().getGeneralManager().inGod(target.getUniqueId())) {
                target.sendMessage(PrefixConstants.GOD.append(Component.text("Dein Gottmodus wurde ", NamedTextColor.GRAY)).append(Component.text("aktiviert.", NamedTextColor.GREEN)));
                player.sendMessage(PrefixConstants.GOD.append(Component.text(target.getName(), NamedTextColor.AQUA)).append(Component.text(" ist nun im Gottmodus.", NamedTextColor.GRAY)));
                return;
            }
            target.sendMessage(PrefixConstants.GOD.append(Component.text("Dein Gottmodus wurde ", NamedTextColor.GRAY)).append(Component.text("deaktiviert.", NamedTextColor.RED)));
            player.sendMessage(PrefixConstants.GOD.append(Component.text(target.getName(), NamedTextColor.AQUA)).append(Component.text(" ist nicht mehr im Gottmodus.", NamedTextColor.GRAY)));
            return;
        }

        help(sender, Arrays.asList("God", "God <Player>"), Arrays.asList(PermissionConstants.GOD_PERSONAL, PermissionConstants.GOD_TARGET));
    }
}
