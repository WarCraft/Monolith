package gg.warcraft.monolith.api.entity.player.service;

import gg.warcraft.monolith.api.entity.EquipmentSlot;
import gg.warcraft.monolith.api.entity.player.PlayerServerData;
import gg.warcraft.monolith.api.world.item.Item;

import java.util.Collection;
import java.util.UUID;

/**
 * This adapter is injectable, however you generally have no need for it. Use the command and query services instead.
 * <p>
 * The PlayerServerAdapter abstracts entity related server implementations from the Monolith domain. It can be used to
 * query the ids of all online players, whether a name is currently in use by a player, and which Minecraft id belongs
 * to a given name.
 */
public interface PlayerServerAdapter {

    /**
     * @param playerId The id of the player. Can not be null.
     * @return The server data of the player. Can be null.
     */
    PlayerServerData getPlayerServerData(UUID playerId);

    /**
     * @return The ids of all online players. Never null, but can be empty. Items are never null.
     */
    Collection<UUID> getOnlinePlayers();

    /**
     * @param playerId The id of the player. Can not be null.
     * @param slot     The equipment slot to set. Can not be null.
     * @param item     The item to set in the equipment slot. Can not be null.
     */
    void setEquipment(UUID playerId, EquipmentSlot slot, Item item);

    /**
     * @param playerId The id of the player. Can not be null.
     * @param progress The new progress of the player's experience bar. Must be between 0.0 and 1.0.
     */
    void setExperienceProgress(UUID playerId, float progress);

    /**
     * @param playerId The id of the player. Can not be null.
     * @param level    The new level of the player. Must be greater than 0.
     */
    void setLevel(UUID playerId, int level);

    /**
     * @param playerId            The id of the player. Can not be null.
     * @param item                The item to add to the inventory. Can not be null.
     * @param dropOnFullInventory Whether or not to drop items that could not be added to the inventory.
     * @return True if all items could be added to the inventory, false otherwise.
     */
    boolean giveItem(UUID playerId, Item item, boolean dropOnFullInventory);

    /**
     * @param minecraftName The Minecraft name. Can not be null.
     * @return The Minecraft id belonging to the name. Can be null.
     */
    UUID resolvePlayerId(String minecraftName);

    /**
     * @param playerId the id of the player. Can not be null.
     * @param message  The message to send. Can not be null or empty.
     */
    void sendMessage(UUID playerId, String message);

    /**
     * @param playerId The id of the player. Can not be null.
     * @param title    The title to send. Can not be null, but can be empty.
     * @param subTitle The sub title to send. Can not be null, but can be empty.
     */
    void sendTitle(UUID playerId, String title, String subTitle);
}
