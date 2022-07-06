package io.github.rysefoxx.potpvp.auctionhouse;

import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/7/2022
 */
public class AuctionHousePlugin extends JavaPlugin {

    private final PluginLogger logger = new PluginLogger(this);

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public @NotNull PluginLogger logger() {
        return this.logger;
    }
}
