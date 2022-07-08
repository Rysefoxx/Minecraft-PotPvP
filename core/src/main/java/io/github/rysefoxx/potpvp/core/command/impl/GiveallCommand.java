package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Prefix;
import io.github.rysefoxx.potpvp.core.constant.PermissionConstants;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import io.github.rysefoxx.potpvp.core.util.PlayerUtils;
import io.github.rysefoxx.potpvp.core.util.StringUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@BaseCommand(command = "giveall")
public class GiveallCommand extends BaseExecutor {

    public GiveallCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;
        if (!player.hasPermission(PermissionConstants.GIVE_ALL)) {
            player.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        if (PlayerUtils.emptyHand(player)) {
            player.sendMessage(StringConstants.EMPTY_HAND);
            return;
        }
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.equals(player)) continue;

            PlayerUtils.give(onlinePlayer, itemStack);
        }

        Bukkit.broadcast(PrefixConstants.GIVE_ALL.append(Component.text("Es haben alle Spieler ", NamedTextColor.GRAY))
                .append(Component.text(StringUtils.formatString(itemStack.getType().toString()), NamedTextColor.BLUE))
                .append(Component.text(" " + itemStack.getAmount() + "x", NamedTextColor.GOLD))
                .append(Component.text(" erhalten.", NamedTextColor.GRAY)));
    }
}
