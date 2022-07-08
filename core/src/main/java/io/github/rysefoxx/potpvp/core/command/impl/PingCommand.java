package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Prefix;
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
@BaseCommand(command = "ping")
public class PingCommand extends BaseExecutor {

    public PingCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(PrefixConstants.PING.append(Component.text("Dein Ping: ", NamedTextColor.GRAY)).append(getColoredPing(player.getPing())));
            return;
        }
        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(StringConstants.PLAYER_OFFLINE);
                return;
            }

            player.sendMessage(PrefixConstants.PING.append(Component.text(target.getName() + "'s Ping: ", NamedTextColor.GRAY)).append(getColoredPing(target.getPing())));
            return;
        }

        help(sender, Arrays.asList("Ping", "Ping <Player>"));
    }

    private @NotNull Component getColoredPing(int ping) {
        if (ping <= 45) {
            return Component.text(ping + "ms", NamedTextColor.DARK_GREEN);
        } else if (ping <= 100) {
            return Component.text(ping + "ms", NamedTextColor.GREEN);
        } else if (ping <= 150) {
            return Component.text(ping + "ms", NamedTextColor.YELLOW);
        } else if (ping <= 250) {
            return Component.text(ping + "ms", NamedTextColor.RED);
        } else {
            return Component.text(ping + "ms", NamedTextColor.DARK_RED);
        }
    }
}
