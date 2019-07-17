package gg.warcraft.monolith.app.world.block;

import com.google.common.base.MoreObjects;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.Stairs;

import java.util.Objects;

public class StairsBlock extends SimpleDirectionalBlock implements Stairs {
    private final boolean inverted;

    public StairsBlock(BlockType type, BlockLocation location, Direction facing, boolean inverted) {
        super(type, location, facing);
        this.inverted = inverted;
    }

    @Override
    public boolean isInverted() {
        return inverted;
    }

    @Override
    public Stairs withLocation(BlockLocation location) {
        return new StairsBlock(getType(), location, getFacing(), isInverted());
    }

    @Override
    public Stairs withFacing(Direction facing) {
        return new StairsBlock(getType(), getLocation(), facing, isInverted());
    }

    @Override
    public Stairs withInverted(boolean inverted) {
        return new StairsBlock(getType(), getLocation(), getFacing(), inverted);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Stairs other = (Stairs) object;
        return Objects.equals(getType(), other.getType())
                && Objects.equals(getLocation(), other.getLocation())
                && Objects.equals(getFacing(), other.getFacing())
                && Objects.equals(isInverted(), other.isInverted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getLocation(), getFacing(), isInverted());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("type", getType())
                .add("location", getLocation())
                .add("facing", getFacing())
                .add("inverted", isInverted())
                .toString();
    }
}
