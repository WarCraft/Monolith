package gg.warcraft.monolith.api.world.block.backup.service;

import gg.warcraft.monolith.api.world.BlockLocation;

import java.util.UUID;

public interface BlockBackupCommandService {

    /**
     * @param location The location of the block. Can not be null.
     * @return The id of the block backup. Never null.
     */
    UUID createBlockBackup(BlockLocation location);

    /**
     * @param id The id of the backup. Can not be null.
     */
    void restoreBlockBackup(UUID id);
}
