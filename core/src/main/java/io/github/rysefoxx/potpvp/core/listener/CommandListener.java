package io.github.rysefoxx.potpvp.core.listener;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import io.github.rysefoxx.potpvp.core.constant.StringConstants;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/7/2022
 */
@RequiredArgsConstructor
public class CommandListener implements Listener {

    private final CorePlugin plugin;

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandPreprocess(@NotNull PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String[] split = message.split(" ");

        if (split[0].equals("/")) {
            event.setCancelled(true);
            return;
        }

        if (Bukkit.getServer().getHelpMap().getHelpTopic(split[0]) == null) {
            event.setCancelled(true);
            player.sendMessage(StringConstants.ERROR.append(
                    Component.text("§7Der Befehl ", NamedTextColor.GRAY)
                            .append(Component.text(split[0], NamedTextColor.AQUA))
                            .append(Component.text(" existiert nicht.", NamedTextColor.GRAY))));
            return;
        }

        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
            if (!this.plugin.getSpyManager().inCommandSpy(onlinePlayer.getUniqueId())) return;

            onlinePlayer.sendMessage(PrefixConstants.SPY.append(Component.text(player.getName(), NamedTextColor.AQUA)
                    .append(Component.text(" §7hat den Befehl §b" + split[0] + " §7ausgeführt.", NamedTextColor.GRAY))));
        });
    }
}
