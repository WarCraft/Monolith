package gg.warcraft.monolith.api.world.block.spoofing;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.block.Block;

import java.util.List;
import java.util.UUID;

/**
 * This repository is injectable, however you generally have no need for it. Use the command and query services instead.
 * <p>
 * If you feel you absolutely have to use this repository it can be used to forgo the command service and save a
 * spoofed {@code Block} to the Monolith domain directly. This repository does no safety checks whatsoever.
 */
public interface BlockSpoofingRepository {

    /**
     * @param location The location. Can not be null.
     * @param playerId The player id. Can not be null.
     * @return The spoofed block at the location for the player. Can be null.
     */
    Block getSpoofedBlock(BlockLocation location, UUID playerId);

    /**
     * @param playerId The player Id. Can not be null.
     * @return All blocks currently spoofed for the player. Never null, but can be empty. Items are never null.
     */
    List<Block> getSpoofedBlocks(UUID playerId);

    /**
     * @param fakeBlock The fake block that is being spoofed. Can not be null.
     * @param playerId  The player id. Can not be null.
     */
    void save(Block fakeBlock, UUID playerId);

    /**
     * @param location The location of the spoofed block. Can not be null.
     * @param playerId The player id. Can not be null.
     */
    void delete(BlockLocation location, UUID playerId);
}
