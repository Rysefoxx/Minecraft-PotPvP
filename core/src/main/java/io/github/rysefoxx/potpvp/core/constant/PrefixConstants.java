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
    public final Component KILL = miniMessage.deserialize("<red><b>KILL</b></red> <dark_gray>» ");
    public final Component GOD = miniMessage.deserialize("<red><b>GOD</b></red> <dark_gray>» ");
    public final Component CLEAR = miniMessage.deserialize("<green><b>CLEAR</b></green> <dark_gray>» ");
    public final Component EAT = miniMessage.deserialize("<green><b>EAT</b></green> <dark_gray>» ");
    public final Component SPY = miniMessage.deserialize("<red><b>SPY</b></red> <dark_gray>» ");
    public final Component TEAM_CHAT = miniMessage.deserialize("<red><b>TEAMCHAT</b></red> <dark_gray>» ");
    public final Component FLY = miniMessage.deserialize("<b>FLY</b> <dark_gray>» ");
    public final Component MORE = miniMessage.deserialize("<gold><b>MORE</b></gold> <dark_gray>» ");
    public final Component RANDOM = miniMessage.deserialize("<gold><b>RANDOM</b></gold> <dark_gray>» ");
    public final Component PVP = miniMessage.deserialize("<yellow><b>PVP</b></yellow> <dark_gray>» ");
    public final Component MSG = miniMessage.deserialize("<yellow><b>MSG</b></yellow> <dark_gray>» ");
    public final Component PING = miniMessage.deserialize("<b>PING</b> <dark_gray>» ");
    public final Component INFO = miniMessage.deserialize("<blue><b>INFO</b></blue> <dark_gray>» ");
    public final Component GIVE_ALL = miniMessage.deserialize("<green><b>GIVEALL</b></green> <dark_gray>» ");
    public final Component GAME_MODE = miniMessage.deserialize("<yellow><b>GAMEMODE</b></yellow> <dark_gray>» ");
    public final Component CLEAR_LAG = miniMessage.deserialize("<red><b>CLEARLAG</b></red> <dark_gray>» ");
    public final Component FILL = miniMessage.deserialize("<gold><b>FILL</b></gold> <dark_gray>» ");
    public final Component FIX = miniMessage.deserialize("<gold><b>REPAIR</b></gold> <dark_gray>» ");
    public final Component VANISH = miniMessage.deserialize("<blue><b>VANISH</b></blue> <dark_gray>» ");
}
