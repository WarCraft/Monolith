package gg.warcraft.monolith.api.block.spoofing;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.block.Block;

import java.util.UUID;

/**
 * This service is injectable.
 * <p>
 * The BlockSpoofingCommandService serves as a point of entry into the block module implementation. It provides methods
 * to spoof, and restore, fake blocks to players.
 */
public interface BlockSpoofingCommandService {

    /**
     * @param fakeBlock The block to spoof. Can not be null.
     * @param playerIds The player ids to spoof the block for. Can not be null or empty.
     */
    void spoofBlock(Block fakeBlock, UUID... playerIds);

    /**
     * @param location  The location of the spoofed block to restore. Can not be null.
     * @param playerIds The player ids to unspoof the block at the location for. Can not be null or empty.
     */
    void unspoofBlock(BlockLocation location, UUID... playerIds);

    /**
     * @param playerIds The player ids for which to unspoof all currently spoofed blocks. Can not be null or empty.
     */
    void unspoofAll(UUID... playerIds);
}
