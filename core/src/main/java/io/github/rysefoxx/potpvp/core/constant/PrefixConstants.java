package io.github.rysefoxx.potpvp.core.constant;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

/**
 * @author Rysefoxx | Rysefoxx#6772
 * @since 7/6/2022
 */
@UtilityClass
public class PrefixConstants {

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public final Component ANNOUNCEMENT = miniMessage.deserialize("<red><b>EILMELDUNG</b></red> <dark_gray>» ");
    public final Component CLEAR = miniMessage.deserialize("<green><b>CLEAR</b></green> <dark_gray>» ");
    public final Component EAT = miniMessage.deserialize("<green><b>EAT</b></green> <dark_gray>» ");
    public final Component SPY = miniMessage.deserialize("<red><b>SPY</b></red> <dark_gray>» ");
    public final Component TEAM_CHAT = miniMessage.deserialize("<red><b>TEAMCHAT</b></red> <dark_gray>» ");
    public final Component FLY = miniMessage.deserialize("<b>FLY</b> <dark_gray>» ");
}
