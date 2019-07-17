package gg.warcraft.monolith.api.world.block.backup;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.block.BlockType;

import java.util.UUID;

public interface BlockBackup {

    /**
     * @return The id of this backup. Never null.
     */
    UUID getId();

    /**
     * @return The type of the block. Never null.
     */
    BlockType getType();

    /**
     * @return The raw data value of the block.
     */
    int getData();

    /**
     * @return The location of the block. Never null.
     */
    BlockLocation getLocation();
}
