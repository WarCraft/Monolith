package gg.warcraft.monolith.app.world.block;

import com.google.common.base.MoreObjects;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.Gate;

import java.util.Objects;

public class GateBlock extends SimpleDirectionalBlock implements Gate {
    private final boolean open;

    public GateBlock(BlockType type, BlockLocation location, Direction facing, boolean open) {
        super(type, location, facing);
        this.open = open;
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    @Override
    public Gate withOpen(boolean open) {
        return new GateBlock(getType(), getLocation(), getFacing(), open);
    }

    @Override
    public Gate withLocation(BlockLocation location) {
        return new GateBlock(getType(), location, getFacing(), isOpen());
    }

    @Override
    public Gate withFacing(Direction facing) {
        return new GateBlock(getType(), getLocation(), facing, isOpen());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Gate other = (Gate) object;
        return Objects.equals(getType(), other.getType())
                && Objects.equals(getLocation(), other.getLocation())
                && Objects.equals(getFacing(), other.getFacing())
                && Objects.equals(isOpen(), other.isOpen());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getLocation(), getFacing(), isOpen());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("type", getType())
                .add("location", getLocation())
                .add("facing", getFacing())
                .add("open", isOpen())
                .toString();
    }
}
