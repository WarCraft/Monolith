package gg.warcraft.monolith.app.world.block;

import com.google.common.base.MoreObjects;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.DirectionalBlock;

import java.util.Objects;

public class SimpleDirectionalBlock extends SimpleBlock implements DirectionalBlock {
    private final Direction facing;

    public SimpleDirectionalBlock(BlockType type, BlockLocation location, Direction facing) {
        super(type, location);
        this.facing = facing;
    }

    @Override
    public Direction getFacing() {
        return facing;
    }

    @Override
    public DirectionalBlock withLocation(BlockLocation location) {
        return new SimpleDirectionalBlock(getType(), location, getFacing());
    }

    @Override
    public DirectionalBlock withFacing(Direction facing) {
        return new SimpleDirectionalBlock(getType(), getLocation(), facing);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        DirectionalBlock other = (DirectionalBlock) object;
        return Objects.equals(getType(), other.getType())
                && Objects.equals(getLocation(), other.getLocation())
                && Objects.equals(getFacing(), other.getFacing());
    }

    @Override
    public int hashCode() {
        String id = getType() + ":"
                + getLocation() + ":"
                + getFacing();
        return id.hashCode();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("type", getType())
                .add("location", getLocation())
                .add("facing", getFacing())
                .toString();
    }
}
