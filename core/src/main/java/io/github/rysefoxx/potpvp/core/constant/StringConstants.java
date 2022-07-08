package io.github.rysefoxx.potpvp.core.constant;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/6/2022
 */
@UtilityClass
public class StringConstants {

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public final Component ERROR = miniMessage.deserialize("<red><b>FEHLER</b></red> <dark_gray>» ");
    public final Component NO_PERMISSION = miniMessage.deserialize("<error><gradient:#ff0004:#b50003>Du hast für diesen Befehl keine Berechtigung.</gradient>", Placeholder.component("error", ERROR));
    public final Component PARSE_ERROR = miniMessage.deserialize("<error><gradient:#ff0004:#b50003>Die Eingabe war keine valide Zahl.</gradient>", Placeholder.component("error", ERROR));
    public final Component PLAYER_OFFLINE = miniMessage.deserialize("<error><gradient:#ff3b5b:#f7000c>Dieser Spieler ist nicht online.</gradient>", Placeholder.component("error", ERROR));
    public final Component EMPTY_HAND = miniMessage.deserialize("<error><red>Du musst etwas in der Hand halten.</red>", Placeholder.component("error", ERROR));

}
