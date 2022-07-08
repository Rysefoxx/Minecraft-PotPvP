package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.constant.PermissionConstants;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import io.github.rysefoxx.potpvp.core.util.PlayerUtils;
import io.github.rysefoxx.potpvp.core.util.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnegative;
import java.awt.*;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@BaseCommand(command = "fill")
public class FillCommand extends BaseExecutor {

    public FillCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;

        if (!player.hasPermission(PermissionConstants.FILL)) {
            player.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        int filled = 0;

        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack == null || itemStack.getType() != Material.GLASS_BOTTLE) continue;

            player.getInventory().remove(itemStack);
            PlayerUtils.give(player, buildPotion(itemStack.getAmount()));

            filled += itemStack.getAmount();
        }

        if (filled > 0) {
            player.sendMessage(PrefixConstants.FILL
                    .append(Component.text("Es " + Utils.numerous(filled, "wurde ", "wurden "), NamedTextColor.GRAY))
                    .append(Component.text(Utils.numerous(filled, "eine Glasflasche", filled + " Glasflaschen"), NamedTextColor.AQUA))
                    .append(Component.text(" befüllt.", NamedTextColor.GRAY)));
            return;
        }
        player.sendMessage(PrefixConstants.FILL.append(Component.text("Es konnten keine Glasflaschen gefunden werden.", NamedTextColor.RED)));
    }

    private @NotNull ItemStack buildPotion(@Nonnegative int amount) {
        ItemStack potion = new ItemStack(Material.POTION, amount);
        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();

        potionMeta.setColor(Color.BLUE);
        potionMeta.setBasePotionData(new PotionData(PotionType.WATER));
        potion.setItemMeta(potionMeta);

        return potion;
    }
}
