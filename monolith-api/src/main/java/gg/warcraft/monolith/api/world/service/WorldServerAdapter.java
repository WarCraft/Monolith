package gg.warcraft.monolith.api.world.service;

import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.Sound;
import gg.warcraft.monolith.api.world.SoundCategory;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.type.Sign;

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
    void updateBlock(Block block);

    void spoofBlock(Block block, UUID playerId);

    void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch);

    void strikeLightning(Location location, boolean ambient);

    void createExplosion(Location location, boolean ambient);

    UUID createArrow(UUID shooterId, Location location, Vector3f direction, float speed, float spread);
}
