package gg.warcraft.monolith.app.world.block.backup.persistence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.block.BlockType;

import java.util.UUID;

public class BlockBackupItem {
    private final UUID id;
    private final BlockType type;
    private final int data;
    private final World world;
    private final int x;
    private final int y;
    private final int z;

    @JsonCreator
    public BlockBackupItem(@JsonProperty("id") UUID id,
                           @JsonProperty("type") BlockType type,
                           @JsonProperty("data") int data,
                           @JsonProperty("world") World world,
                           @JsonProperty("x") int x,
                           @JsonProperty("y") int y,
                           @JsonProperty("z") int z) {
        this.id = id;
        this.type = type;
        this.data = data;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public UUID getId() {
        return id;
    }

    public BlockType getType() {
        return type;
    }

    public int getData() {
        return data;
    }

    public World getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
