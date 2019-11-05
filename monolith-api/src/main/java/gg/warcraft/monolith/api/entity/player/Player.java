package gg.warcraft.monolith.api.entity.player;

import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.world.item.Inventory;

/**
 * A Player is a human actor on the server.
 */
public interface Player extends Entity {

    /**
     * @return The unix timestamp in milliseconds when this player connected this session, or the previous session if
     * they have since logged off.
     */
    int getTimeConnected();

    /**
     * @return The unix timestamp in milliseconds when this player first connected to the server.
     */
    int getTimeFirstConnected();

    /**
     * @return The unix timestamp in milliseconds when this player was last seen on the server.
     */
    int getTimeLastSeen();

    /**
     * @return The total amount of time in milliseconds this player has played on the server.
     */
    int getTimePlayed();

    /**
     * @param currency The currency. Can not be null or empty.
     * @return The amount of the given currency this player currently has.
     */
    int getCurrency(String currency);

    /**
     * @param currency The currency. Can not be null or empty.
     * @return The total amount of the given currency this player has accumulated over their lifetime on the server.
     */
    int getLifetimeCurrency(String currency);

    /**
     * @param statistic The statistic. Can not be null or empty.
     * @return The value of the given statistic this player currently has.
     */
    int getStatistic(String statistic);

    GameMode getGameMode();

    /**
     * @return The inventory of this player. Never null.
     */
    Inventory getInventory();

    /**
     * @return True if this player is sneaking, false otherwise.
     */
    boolean isSneaking();

    /**
     * @return True if this player is online, false otherwise.
     */
    boolean isOnline();
}
