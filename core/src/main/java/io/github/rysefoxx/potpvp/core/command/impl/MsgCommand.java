package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Alias;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.Prefix;
import io.github.rysefoxx.potpvp.core.constant.PermissionConstants;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import io.github.rysefoxx.potpvp.core.util.Maths;
import io.github.rysefoxx.potpvp.core.util.PlayerUtils;
import io.github.rysefoxx.potpvp.core.util.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@Alias(values = {"whisper", "message"})
@BaseCommand(command = "msg")
public class MsgCommand extends BaseExecutor {

    public MsgCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Prefix(append = true)
    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;

        //Msg <Spieler> <Nachricht>

        if(args.length < 2) {
            help(player, List.of("Msg <Spieler> <Nachricht>"));
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage(StringConstants.PLAYER_OFFLINE);
            return;
        }

        //TODO: Check if player is muted
        //TODO: Check if target wants to receive messages
        //TODO: Check if target settings are set to receive messages + player permission

        String message = Utils.buildMessage(args, 1);
        target.sendMessage(PrefixConstants.MSG.append(
                Component.text(player.getName(), NamedTextColor.AQUA))
                .append(Component.text(" zu dir", NamedTextColor.GOLD))
                .append(Component.text(" ●", NamedTextColor.DARK_GRAY))
                .append(Component.text(" " + message, NamedTextColor.WHITE)));
        player.sendMessage(PrefixConstants.MSG.append(
                Component.text("Du an ", NamedTextColor.GOLD))
                .append(Component.text(target.getName(), NamedTextColor.AQUA))
                .append(Component.text(" ●", NamedTextColor.DARK_GRAY))
                .append(Component.text(" " + message, NamedTextColor.WHITE)));

        plugin().getGeneralManager().addReply(player.getUniqueId(), target.getName());
        plugin().getGeneralManager().addReply(target.getUniqueId(), player.getName());

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if(!plugin().getSpyManager().inMsgSpy(onlinePlayer.getUniqueId())) continue;

            onlinePlayer.sendMessage(PrefixConstants.SPY.append(
                    Component.text(player.getName(), NamedTextColor.AQUA))
                    .append(Component.text(" an ", NamedTextColor.GOLD))
                    .append(Component.text(target.getName(), NamedTextColor.AQUA))
                    .append(Component.text(" ●", NamedTextColor.DARK_GRAY))
                    .append(Component.text(" " + message, NamedTextColor.WHITE)));
        }
    }
}
