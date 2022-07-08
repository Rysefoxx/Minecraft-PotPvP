package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Prefix;
import io.github.rysefoxx.potpvp.core.constant.PermissionConstants;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import io.github.rysefoxx.potpvp.core.util.Maths;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@BaseCommand(command = "random")
public class RandomCommand extends BaseExecutor {

    public RandomCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;
        if (!player.hasPermission(PermissionConstants.RANDOM)) {
            player.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        if (args.length == 0) {
            Player randomPlayer = (Player) Bukkit.getOnlinePlayers().toArray()[new Random().nextInt(Bukkit.getOnlinePlayers().size())];

            Bukkit.broadcast(PrefixConstants.RANDOM.append(
                    Component.text(randomPlayer.getName(), NamedTextColor.AQUA)
                            .append(Component.text(" wurde zufällig ausgewählt!", NamedTextColor.GRAY))));
            return;
        }

        if (args.length == 1) {
            if (!Maths.isInteger(args[0], false)) {
                player.sendMessage(StringConstants.PARSE_ERROR);
                return;
            }
            int amount = Integer.parseInt(args[0]);

            for (int i = 1; i <= amount; i++) {
                Player randomPlayer = (Player) Bukkit.getOnlinePlayers().toArray()[new Random().nextInt(Bukkit.getOnlinePlayers().size())];
                Bukkit.broadcast(PrefixConstants.RANDOM.append(Component.text("#" + i + " ", NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                        .append(Component.text(randomPlayer.getName(), NamedTextColor.AQUA))
                        .append(Component.text(" wurde zufällig ausgewählt!", NamedTextColor.GRAY)));
            }
            return;
        }

        help(player, Arrays.asList("Random", "Random <Anzahl>"));
    }
}
