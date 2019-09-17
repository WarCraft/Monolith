package gg.warcraft.monolith.api.world.service;

import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.Sound;
import gg.warcraft.monolith.api.world.SoundCategory;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.Sign;

import java.util.List;
import java.util.UUID;

/**
 * This adapter is injectable, however you generally have no need for it. Use the command and query services instead.
 * <p>
 * The WorldServerAdapter abstracts world related server implementations from the Monolith domain. It can be used to
 * query blocks on the server, but also to drop items at a given location.
 */
public interface WorldServerAdapter {

    /**
     * @param world The world.
     * @param x     The X coordinate.
     * @param y     The Y coordinate.
     * @param z     The Z coordinate.
     * @return The block in the given world at the specified coordinates.
     */
    Block getBlockAt(World world, int x, int y, int z);

    /**
     * @param world The world.
     * @param x     The X coordinate.
     * @param z     The Y coordinate.
     * @return The highest non-air block in the given world at the specified coordinates.
     */
    Block getHighestBlockAt(World world, int x, int z);

    /**
     * @param block The block to update. Can not be null.
     */
    void setBlock(Block block);

//    /**
//     * @param block The block to update. Can not be null.
//     * @param type  The new type. Can not be null.
//     */
//    void setBlockType(Block block, BlockType type);
//
//    /**
//     * @param sign  The sign to update. Can not be null.
//     * @param lines The new lines. Can not be null.
//     */
//    void setSignLines(Sign sign, String[] lines);

    /**
     * @param items    The items to drop. Can not be null, but can be empty. Items can not be null.
     * @param location The location to drop at. Can not be null.
     * @return A list of ids of the dropped items. Never null, but can be empty.
     */
    List<UUID> dropItemsAt(List<Item> items, Location location);

    void spoofBlock(Block fakeBlock, UUID playerId);

    void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch);

    void strikeLightning(Location location, boolean ambient);

    void createExplosion(Location location, boolean ambient);

    UUID createArrow(UUID shooterId, Location location, Vector3f direction, float speed, float spread);
}
