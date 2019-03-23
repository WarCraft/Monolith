package gg.warcraft.monolith.api.entity.player.service;

import gg.warcraft.monolith.api.entity.EquipmentSlot;
import gg.warcraft.monolith.api.entity.player.Currency;
import gg.warcraft.monolith.api.entity.player.Statistic;
import gg.warcraft.monolith.api.entity.team.Team;
import gg.warcraft.monolith.api.item.Item;

import java.util.UUID;

/**
 * This service is injectable.
 * <p>
 * The PlayerCommandService serves as a point of entry into the entity module implementation. It provides a method to
 * trigger an update of the total play time of a {@code Player}.
 */
public interface PlayerCommandService {

    /**
     * @param playerId The id of the player. Can not be null.
     * @param team     The new team of the player. Can be null.
     */
    void setTeam(UUID playerId, Team team);

    /**
     * @param playerId The id of the player. Can not be null.
     * @param data     The data to set. Can not be null or empty.
     * @param value    The new value of the data. Can be null or empty, but either of these values will result in the
     *                 data being removed from the player.
     */
    void setData(UUID playerId, String data, String value);

    /**
     * @param playerId The id of the player. Can not be null.
     * @param slot     The equipment slot to set. Can not be null.
     * @param item     The item to set in the equipment slot. Can not be null.
     */
    void setEquipment(UUID playerId, EquipmentSlot slot, Item item);

    /**
     * @param playerId            The id of the player. Can not be null.
     * @param item                The item to add to the inventory. Can not be null.
     * @param dropOnFullInventory Whether or not to drop items that could not be added to the inventory.
     * @return True if all items could be added to the inventory, false otherwise.
     */
    boolean giveItem(UUID playerId, Item item, boolean dropOnFullInventory);

    /**
     * Adds an amount of currency to the player.
     *
     * @param playerId The id of the player. Can not be null.
     * @param currency The currency to add. Can not be null.
     * @param amount   The amount of currency to add.
     */
    void addCurrency(UUID playerId, Currency currency, int amount);

    /**
     * Removes an amount of currency to the player. Throws an IllegalArgumentException if the player does not have
     * sufficient funds to cover the removal.
     *
     * @param playerId The id of the player. Can not be null.
     * @param currency The currency to remove. Can not be null.
     * @param amount   The amount of currency to remove.
     */
    void removeCurrency(UUID playerId, Currency currency, int amount);

    /**
     * Revokes an amount of currency to the player. Revoking does not throw on sufficient funds and also reduces the
     * amount from the lifetime amount the player has accumulated.
     *
     * @param playerId The id of the player. Can not be null.
     * @param currency The currency to revoke. Can not be null.
     * @param amount   The amount of currency to revoke.
     */
    void revokeCurrency(UUID playerId, Currency currency, int amount);

    /**
     * Adds a given amount to the player's statistics. Try to consolidate as many statistics per call as each call
     * results in only one round trip to the persistence store.
     *
     * @param playerId   The id of the player. Can not be null.
     * @param statistics The statistics to increase. Can not be null.
     * @param amount     The amount to increase the statistic by.
     */
    void increaseStatistics(UUID playerId, int amount, Statistic... statistics);

    /**
     * Resets the given statistics to 0. Try to consolidate as many statistics per call as each call results in only one
     * round trip to the persistence store.
     *
     * @param playerId   The id of the player. Can not be null.
     * @param statistics The statistics to reset. Can not be null.
     */
    void resetStatistics(UUID playerId, Statistic... statistics);


    /**
     * @param playerId The id of the player to update. Can not be null.
     * @param force    Forces an update regardless of whether the player is online or offline.
     */
    void update(UUID playerId, boolean force);

    /**
     * @param playerId the id of the player. Can not be null.
     * @param message  The message to send. Can not be null or empty.
     */
    void sendMessage(UUID playerId, String message);

    /**
     * Sends a server notification to the player. Server notifications are yellow messages prefixed with '[SERVER]'.
     *
     * @param playerId     the id of the player. Can not be null.
     * @param notification The notification to send. Can not be null or empty.
     */
    void sendNotification(UUID playerId, String notification);

    /**
     * @param playerId The id of the player. Can not be null.
     * @param title    The title to send. Can not be null, but can be empty.
     * @param subTitle The sub title to send. Can not be null, but can be empty.
     */
    void sendTitle(UUID playerId, String title, String subTitle);
}
