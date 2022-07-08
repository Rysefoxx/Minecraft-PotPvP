package io.github.rysefoxx.potpvp.core.listener;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/8/2022
 */
@RequiredArgsConstructor
public class ConnectionListener implements Listener {

    private final CorePlugin plugin;

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.joinMessage(null);

        this.plugin.getGeneralManager().updateVanish(player);

        if (this.plugin.getGeneralManager().inVanish(player.getUniqueId())) {
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
                player.sendMessage("");
                player.sendMessage(PrefixConstants.VANISH.append(Component.text("Du bist weiterhin unsichtbar!", NamedTextColor.BLUE)));
                player.sendMessage("");
            }, 5L);
        }
    }
}
