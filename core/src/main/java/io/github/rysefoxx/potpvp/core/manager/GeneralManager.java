package io.github.rysefoxx.potpvp.core.manager;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/7/2022
 */
@RequiredArgsConstructor
public class GeneralManager {

    private final CorePlugin plugin;

    private final HashMap<UUID, String> reply = new HashMap<>();
    private final List<UUID> god = new ArrayList<>();
    private final List<UUID> vanish = new ArrayList<>();

    public void toggleVanish(@NotNull UUID uuid) {
        if (this.vanish.contains(uuid)) {
            removeHide(uuid);
            return;
        }
        addHide(uuid);
    }

    public void toggleGod(@NotNull UUID uuid) {
        if (this.god.contains(uuid)) {
            this.god.remove(uuid);
            return;
        }
        this.god.add(uuid);
    }

    public void updateVanish(@NotNull Player player) {
        if (!this.vanish.contains(player.getUniqueId())) {
            for (UUID uuid : this.vanish) {
                Player target = Bukkit.getPlayer(uuid);

                if (target == null) return;
                player.hidePlayer(this.plugin, target);
            }
            return;
        }
        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.hidePlayer(this.plugin, player));
    }

    public boolean inVanish(@NotNull UUID uuid) {
        return this.vanish.contains(uuid);
    }

    private void removeHide(@NotNull UUID uuid) {
        this.vanish.remove(uuid);
        Player player = Bukkit.getPlayer(uuid);

        if (player == null) return;

        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.showPlayer(this.plugin, player));
    }

    private void addHide(@NotNull UUID uuid) {
        this.vanish.add(uuid);
        Player player = Bukkit.getPlayer(uuid);

        if (player == null) return;

        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.hidePlayer(this.plugin, player));
    }

    public boolean inGod(@NotNull UUID uuid) {
        return this.god.contains(uuid);
    }

    public void addReply(@NotNull UUID uuid, @NotNull String reply) {
        this.reply.put(uuid, reply);
    }

    public boolean hasReply(@NotNull UUID uuid) {
        return this.reply.containsKey(uuid);
    }

    public String getReply(@NotNull UUID uuid) {
        return this.reply.get(uuid);
    }
}
