package io.github.rysefoxx.potpvp.core.util;

import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@UtilityClass
public class PlayerUtils {

    public boolean emptyHand(@NotNull Player player) {
        return player.getInventory().getItemInMainHand().getType().isAir();
    }

    public boolean hasEmptySlots(@NotNull Player player) {
        return player.getInventory().firstEmpty() != -1;
    }

    public void give(@NotNull Player player, ItemStack @NotNull ... itemStacks) {
        for (ItemStack itemStack : itemStacks) {
            if (hasEmptySlots(player)) {
                player.getInventory().addItem(itemStack.clone());
                continue;
            }
            player.getWorld().dropItem(player.getLocation(), itemStack.clone());

            player.sendMessage(PrefixConstants.INFO.append(Component.text("Es wurde das Item ", NamedTextColor.GRAY))
                    .append(Component.text(StringUtils.formatString(itemStack.getType().toString()), NamedTextColor.BLUE))
                    .append(Component.text(" auf dem Boden fallengelassen, da du keinen Platz mehr hast.", NamedTextColor.GRAY)));
        }
    }

}
