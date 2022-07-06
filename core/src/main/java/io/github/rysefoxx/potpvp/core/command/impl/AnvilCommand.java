package io.github.rysefoxx.potpvp.core.command.impl;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import io.github.rysefoxx.potpvp.core.command.extension.annotation.BaseCommand;
import io.github.rysefoxx.potpvp.core.constant.PermissionConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/6/2022
 */
@BaseCommand(command = "anvil")
public class AnvilCommand extends BaseExecutor {

    public AnvilCommand(@NotNull CorePlugin plugin) {
        super(plugin);
    }

    @Override
    public void trigger(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        Player player = (Player) sender;
        if (!player.hasPermission(PermissionConstants.ANVIL)) {
            player.sendMessage(StringConstants.NO_PERMISSION);
            return;
        }

        player.openAnvil(null, true);
    }
}
