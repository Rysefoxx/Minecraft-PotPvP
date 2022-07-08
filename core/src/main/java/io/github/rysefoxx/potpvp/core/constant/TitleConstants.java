package io.github.rysefoxx.potpvp.core.constant;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/6/2022
 */
@UtilityClass
public class TitleConstants {

    private final String DOT = " ● ";

    public final Component TRASH = Component.text(DOT).append(Component.text("Abfall"));


}
