package gg.warcraft.monolith.app.world.block;

import com.google.common.base.MoreObjects;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockType;

import java.util.Objects;

public class SimpleBlock implements Block {
    private final BlockType type;
    private final BlockLocation location;

    public SimpleBlock(BlockType type, BlockLocation location) {
        this.type = type;
        this.location = location;
    }

    @Override
    public BlockType getType() {
        return type;
    }

    @Override
    public BlockLocation getLocation() {
        return location;
    }

    @Override
    public Block withType(BlockType type) {
        return new SimpleBlock(type, getLocation());
    }

    @Override
    public Block withLocation(BlockLocation location) {
        return new SimpleBlock(getType(), location);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Block other = (Block) object;
        return Objects.equals(getType(), other.getType())
                && Objects.equals(getLocation(), other.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getLocation());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("type", getType())
                .add("location", getLocation())
                .toString();
    }
}
