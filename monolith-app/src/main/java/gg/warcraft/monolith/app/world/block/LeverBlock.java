package gg.warcraft.monolith.app.world.block;

import com.google.common.base.MoreObjects;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.Lever;

import java.util.Objects;

public class LeverBlock extends SimpleDirectionalBlock implements Lever {
    private final boolean powered;

    public LeverBlock(BlockType type, BlockLocation location, Direction facing, boolean powered) {
        super(type, location, facing);
        this.powered = powered;
    }

    @Override
    public boolean isPowered() {
        return powered;
    }

    @Override
    public Lever withPowered(boolean powered) {
        return new LeverBlock(getType(), getLocation(), getFacing(), powered);
    }

    @Override
    public Lever withLocation(BlockLocation location) {
        return new LeverBlock(getType(), location, getFacing(), isPowered());
    }

    @Override
    public Lever withFacing(Direction facing) {
        return new LeverBlock(getType(), getLocation(), facing, isPowered());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Lever other = (Lever) object;
        return Objects.equals(getType(), other.getType())
                && Objects.equals(getLocation(), other.getLocation())
                && Objects.equals(getFacing(), other.getFacing())
                && Objects.equals(isPowered(), other.isPowered());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getLocation(), getFacing(), isPowered());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("type", getType())
                .add("location", getLocation())
                .add("facing", getFacing())
                .add("powered", isPowered())
                .toString();
    }
}
