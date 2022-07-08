package io.github.rysefoxx.potpvp.core.manager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/7/2022
 */
public class SpyManager {

    private final List<UUID> commandSpy = new ArrayList<>();

    public void toggleSpy(@NotNull UUID uuid) {
        if (this.commandSpy.contains(uuid)) {
            this.commandSpy.remove(uuid);
            return;
        }
        this.commandSpy.add(uuid);
    }

    public boolean inCommandSpy(@NotNull UUID uuid) {
        return this.commandSpy.contains(uuid);
    }
}
