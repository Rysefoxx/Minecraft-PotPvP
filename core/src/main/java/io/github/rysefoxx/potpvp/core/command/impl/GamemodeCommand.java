package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Alias;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.AlternativeCommand;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Prefix;
import io.github.rysefoxx.potpvp.core.constant.PermissionConstants;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import io.github.rysefoxx.potpvp.core.util.StringUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnegative;
import java.util.Arrays;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@Alias(values = "gm")
@BaseCommand(command = "gamemode")
public class GamemodeCommand extends BaseExecutor {

    public GamemodeCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;
        if (args.length == 1) {
            if (!player.hasPermission(PermissionConstants.GAME_MODE_PERSONAL)) {
                player.sendMessage(StringConstants.NO_PERMISSION);
                return;
            }

            invokeByArguments(player, args, 0);
            return;
        }

        if (args.length == 2) {
            if (!player.hasPermission(PermissionConstants.GAME_MODE_TARGET)) {
                player.sendMessage(StringConstants.NO_PERMISSION);
                return;
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage(StringConstants.PLAYER_OFFLINE);
                return;
            }
            invokeByArguments(target, args, 1);
            player.sendMessage(PrefixConstants.GAME_MODE.append(Component.text(target.getName(), NamedTextColor.AQUA))
                    .append(Component.text(" befindet sich nun im Gamemode ", NamedTextColor.GRAY))
                    .append(Component.text(StringUtils.formatString(target.getGameMode().toString()) + ".", NamedTextColor.AQUA)));
            return;
        }

        help(player, Arrays.asList("Gamemode <Gamemode>", "Gamemode <Player> <Gamemode>"), Arrays.asList(PermissionConstants.GAME_MODE_PERSONAL, PermissionConstants.GAME_MODE_TARGET));
    }

    @AlternativeCommand(command = "survival", aliases = "0", permission = PermissionConstants.GAME_MODE_PERSONAL)
    private void survival(@NotNull CommandSender sender) {
        change((Player) sender, GameMode.SURVIVAL);
    }

    @AlternativeCommand(command = "creative", aliases = "1", permission = PermissionConstants.GAME_MODE_PERSONAL)
    private void creative(@NotNull CommandSender sender) {
        change((Player) sender, GameMode.CREATIVE);
    }

    @AlternativeCommand(command = "adventure", aliases = "2", permission = PermissionConstants.GAME_MODE_PERSONAL)
    private void adventure(@NotNull CommandSender sender) {
        change((Player) sender, GameMode.ADVENTURE);
    }

    @AlternativeCommand(command = "spectator", aliases = "3", permission = PermissionConstants.GAME_MODE_PERSONAL)
    private void spectator(@NotNull CommandSender sender) {
        change((Player) sender, GameMode.SPECTATOR);
    }

    private void change(@NotNull Player player, @NotNull GameMode mode) {
        player.setGameMode(mode);
        player.sendMessage(PrefixConstants.GAME_MODE.append(Component.text("Du befindest dich nun im Gamemode ", NamedTextColor.GRAY)
                .append(Component.text(StringUtils.formatString(mode.toString()), NamedTextColor.AQUA))));
    }

    private void invokeByArguments(@NotNull Player player, String @NotNull [] args, @Nonnegative int offset) {
        switch (args[offset].toLowerCase()) {
            case "0", "survival" -> change(player, GameMode.SURVIVAL);
            case "1", "creative" -> change(player, GameMode.CREATIVE);
            case "2", "adventure" -> change(player, GameMode.ADVENTURE);
            case "3", "spectator" -> change(player, GameMode.SPECTATOR);
            default ->
                    help(player, Arrays.asList("Gamemode <Gamemode>", "Gamemode <Player> <Gamemode>"), Arrays.asList(PermissionConstants.GAME_MODE_PERSONAL, PermissionConstants.GAME_MODE_TARGET));
        }
    }
}
