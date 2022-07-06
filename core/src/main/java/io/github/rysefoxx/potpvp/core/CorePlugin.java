package io.github.rysefoxx.potpvp.core;

import io.github.rysefoxx.potpvp.core.inject.Injection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/6/2022
 */
public class CorePlugin extends JavaPlugin implements Listener {

    private final PluginLogger logger = new PluginLogger(this);

    @Override
    public void onEnable() {
        init();
    }

    @Override
    public void onDisable() {

    }

    private void init() {
        registerCommands();
    }

    private void registerCommands() {
        Injection.injectCommands(this);
    }

    public @NotNull PluginLogger logger() {
        return this.logger;
    }
}
