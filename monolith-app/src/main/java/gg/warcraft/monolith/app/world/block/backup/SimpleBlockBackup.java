package gg.warcraft.monolith.app.world.block.backup;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.backup.BlockBackup;

import java.util.UUID;

public class SimpleBlockBackup implements BlockBackup {
    private final UUID id;
    private final BlockType type;
    private final int data;
    private final BlockLocation location;

    public SimpleBlockBackup(UUID id, BlockType type, int data, BlockLocation location) {
        this.id = id;
        this.type = type;
        this.data = data;
        this.location = location;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public BlockType getType() {
        return type;
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public BlockLocation getLocation() {
        return location;
    }
}
