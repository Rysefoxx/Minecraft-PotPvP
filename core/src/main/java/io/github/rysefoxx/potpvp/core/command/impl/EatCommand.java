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
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/7/2022
 */
@Alias(values = {"fed", "feed", "hunger", "food"})
@BaseCommand(command = "eat")
public class EatCommand extends BaseExecutor {

    public EatCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;

        if (!player.hasPermission(PermissionConstants.EAT)) {
            player.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        player.setFoodLevel(20);
        player.setSaturation(20);
        player.sendMessage(PrefixConstants.EAT.append(Component.text("Du hast dein Hunger gestillt.", NamedTextColor.GRAY)));
    }
}
