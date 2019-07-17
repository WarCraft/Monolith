package gg.warcraft.monolith.api.world.block.spoofing;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.block.Block;

import java.util.List;
import java.util.UUID;

/**
 * This service is injectable.
 * <p>
 * The BlockSpoofingQueryService serves as a point of entry into the block implementation. It provides methods to query
 * which fake {@code Block}s are currently being spoofed to a player.
 */
public interface BlockSpoofingQueryService {

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
}
