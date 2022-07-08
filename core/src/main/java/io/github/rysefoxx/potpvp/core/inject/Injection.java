package io.github.rysefoxx.potpvp.core.inject;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.command.extension.BaseExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.logging.Level;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/6/2022
 */
public class Injection {

    private final static Reflections REFLECTIONS = new Reflections(CorePlugin.class.getPackageName());

    public static void injectCommands(@NotNull CorePlugin plugin) {
        for (Class<? extends BaseExecutor> clazz : REFLECTIONS.getSubTypesOf(BaseExecutor.class)) {
            BaseExecutor executor;

            try {
                executor = clazz.getConstructor(CorePlugin.class).newInstance(plugin);
            } catch (Exception e) {
                plugin.logger().log(Level.SEVERE, "Could not register Command " + clazz.getSimpleName(), e);
                continue;
            }

            registerCommand(plugin, executor);
        }
    }

    private static PluginCommand getCommand(String name, Plugin plugin) {
        PluginCommand command = null;

        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);

            command = constructor.newInstance(name, plugin);
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException |
                 InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return command;
    }

    private static CommandMap getCommandMap() {
        CommandMap commandMap = null;

        try {
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field field = SimplePluginManager.class.getDeclaredField("commandMap");
                field.setAccessible(true);

                commandMap = (CommandMap) field.get(Bukkit.getPluginManager());
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return commandMap;
    }

    private static void registerCommand(@NotNull CorePlugin plugin, @NotNull BaseExecutor baseExecutor) {
        PluginCommand command = getCommand(baseExecutor.getBaseCommand().command(), plugin);
        command.setExecutor(baseExecutor);

        if (baseExecutor.getAlias() != null)
            command.setAliases(Arrays.asList(baseExecutor.getAlias().values()));

        if (!baseExecutor.getBaseCommand().description().isBlank())
            command.setDescription(baseExecutor.getBaseCommand().description());


        getCommandMap().register(plugin.getName(), command);
    }
}
