package io.github.rysefoxx.potpvp.core.manager;

import io.github.rysefoxx.potpvp.core.CorePlugin;
import io.github.rysefoxx.potpvp.core.constant.PrefixConstants;
import io.github.rysefoxx.potpvp.core.util.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnegative;
import java.util.Arrays;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/7/2022
 */
public class LagManager {

    private int items = 0;
    private int animals = 0;
    private int monster = 0;

    public void invokeScheduler(@NotNull CorePlugin plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, this::removeAll, 300L, 300L);
    }

    public void remove(@NotNull Type type) {
        switch (type) {
            case ALL -> removeAll();
            case MONSTER -> removeMonster();
            case ANIMAL -> removeAnimal();
            case ITEM -> removeItems();
        }
    }

    private void removeItems() {
        remove(0);
        print(this.items, 0, 0);
    }

    private void removeMonster() {
        remove(1);
        print(0, this.monster, 0);
    }

    private void removeAnimal() {
        remove(2);
        print(0, 0, this.animals);
    }

    private void removeAll() {
        remove(3);
        print(items, monster, animals);
    }

    private void remove(@Nonnegative int state) {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                switch (state) {
                    case 0 -> {
                        if (!(entity instanceof Item)) continue;
                        items++;
                        entity.remove();
                    }
                    case 1 -> {
                        if (!(entity instanceof Monster)) continue;
                        monster++;
                        entity.remove();
                    }
                    case 2 -> {
                        if (!(entity instanceof Animals)) continue;
                        animals++;
                        entity.remove();
                    }
                    case 3 -> {
                        if (entity instanceof Item) {
                            this.items++;
                            entity.remove();
                        }

                        if (entity instanceof Monster) {
                            this.monster++;
                            entity.remove();
                        }

                        if (entity instanceof Animals) {
                            this.animals++;
                            entity.remove();
                        }
                    }
                }
            }
        }
    }

    private void print(@Nonnegative int items, @Nonnegative int monster, @Nonnegative int animals) {
        Bukkit.broadcast(PrefixConstants.CLEAR_LAG.append(Component.text("Es ", NamedTextColor.GRAY))
                .append(Component.text(Utils.numerous(items, "wurde", "wurden") + " ", NamedTextColor.GRAY))
                .append(Component.text(items + Utils.numerous(items, " Item, ", " Items, "), NamedTextColor.GOLD))
                .append(Component.text(monster + " Monster ", NamedTextColor.GOLD))
                .append(Component.text("& ", NamedTextColor.DARK_GRAY))
                .append(Component.text(animals + Utils.numerous(animals, " Tier ", " Tiere "), NamedTextColor.GOLD))
                .append(Component.text("entfernt.", NamedTextColor.GRAY)));

        this.animals = 0;
        this.items = 0;
        this.monster = 0;
    }

    public enum Type {
        ALL("-al"),
        MONSTER("-m"),
        ANIMAL("-a"),
        ITEM("-i"),
        ;

        private final String shortCut;

        @Contract(pure = true)
        Type(@NotNull String shortCut) {
            this.shortCut = shortCut;
        }

        public static @Nullable Type getType(@NotNull String type) {
            return Arrays.stream(Type.values()).filter(t -> t.shortCut.equalsIgnoreCase(type)).findAny().orElse(null);
        }
    }
}
