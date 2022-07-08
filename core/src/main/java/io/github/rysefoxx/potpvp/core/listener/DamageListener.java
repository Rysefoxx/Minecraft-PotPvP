package io.github.rysefoxx.potpvp.core.listener;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/7/2022
 */
@RequiredArgsConstructor
public class DamageListener implements Listener {

    private final CorePlugin plugin;

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(@NotNull EntityDamageEvent event) {
        if (event.getEntityType() != EntityType.PLAYER) return;
        Player player = (Player) event.getEntity();

        if (!this.plugin.getGeneralManager().inGod(player.getUniqueId())) return;

        event.setCancelled(true);
    }
}
