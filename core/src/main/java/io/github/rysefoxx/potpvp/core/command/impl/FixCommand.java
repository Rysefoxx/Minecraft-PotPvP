package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Alias;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.constant.PermissionConstants;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import io.github.rysefoxx.potpvp.core.util.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@Alias(values = "repair")
@BaseCommand(command = "fix")
public class FixCommand extends BaseExecutor {

    public FixCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;

        if (!player.hasPermission(PermissionConstants.FIX)) {
            player.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        int repaired = 0;

        repaired += repair(player.getInventory().getArmorContents());
        repaired += repair(player.getInventory().getContents());

        if (repaired > 0) {
            player.sendMessage(PrefixConstants.FIX
                    .append(Component.text("Es " + Utils.numerous(repaired, "wurde ", "wurden "), NamedTextColor.GRAY))
                    .append(Component.text(Utils.numerous(repaired, "ein Gegenstand", repaired + " Gegenstände"), NamedTextColor.AQUA))
                    .append(Component.text(" repariert.", NamedTextColor.GRAY)));
            return;
        }
        player.sendMessage(PrefixConstants.FIX.append(Component.text("Es konnten keine Gegenstände gefunden werden.", NamedTextColor.RED)));
    }

    private int repair(ItemStack @NotNull [] contents) {
        int repaired = 0;
        for (ItemStack content : contents) {
            if (content == null || content.getType().isAir()) continue;
            if (content.getType().getMaxDurability() == 0) continue;
            if(!content.hasItemMeta())continue;

            Damageable damageable = (Damageable) content.getItemMeta();
            if(!damageable.hasDamage())continue;

            damageable.setDamage(0);
            content.setItemMeta(damageable);
            repaired++;
        }
        return repaired;
    }
}
