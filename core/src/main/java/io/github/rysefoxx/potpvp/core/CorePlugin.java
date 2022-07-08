package io.github.rysefoxx.potpvp.core;

import io.github.rysefoxx.potpvp.core.inject.Injection;
import io.github.rysefoxx.potpvp.core.listener.CommandListener;
import io.github.rysefoxx.potpvp.core.manager.SpyManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/6/2022
 */
@Getter
public class CorePlugin extends JavaPlugin implements Listener {

    private final PluginLogger logger = new PluginLogger(this);

    private SpyManager spyManager;

    @Override
    public void onEnable() {
        init();
    }

    @Override
    public void onDisable() {
    }

    private void init() {
        registerManager();

        registerCommand();
        registerEvent();
    }

    private void registerManager() {
        this.spyManager = new SpyManager();
    }

    private void registerEvent() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new CommandListener(this), this);
    }

    private void registerCommand() {
        Injection.injectCommands(this);
    }

    public @NotNull PluginLogger logger() {
        return this.logger;
    }
}
