package gg.warcraft.monolith.app.world.block;

import com.google.common.base.MoreObjects;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.Trapdoor;

import java.util.Objects;

public class TrapdoorBlock extends SimpleDirectionalBlock implements Trapdoor {
    private final boolean inverted;
    private final boolean open;

    public TrapdoorBlock(BlockType type, BlockLocation location, Direction facing, boolean inverted, boolean open) {
        super(type, location, facing);
        this.inverted = inverted;
        this.open = open;
    }

    @Override
    public boolean isInverted() {
        return inverted;
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    @Override
    public Trapdoor withInverted(boolean inverted) {
        return new TrapdoorBlock(getType(), getLocation(), getFacing(), inverted, isOpen());
    }

    @Override
    public Trapdoor withOpen(boolean open) {
        return new TrapdoorBlock(getType(), getLocation(), getFacing(), isInverted(), open);
    }

    @Override
    public Trapdoor withLocation(BlockLocation location) {
        return new TrapdoorBlock(getType(), location, getFacing(), isInverted(), isOpen());
    }

    @Override
    public Trapdoor withFacing(Direction facing) {
        return new TrapdoorBlock(getType(), getLocation(), facing, isInverted(), isOpen());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Trapdoor other = (Trapdoor) object;
        return Objects.equals(getType(), other.getType())
                && Objects.equals(getLocation(), other.getLocation())
                && Objects.equals(getFacing(), other.getFacing())
                && Objects.equals(isInverted(), other.isInverted())
                && Objects.equals(isOpen(), other.isOpen());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getLocation(), getFacing(), isInverted(), isOpen());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("type", getType())
                .add("location", getLocation())
                .add("facing", getFacing())
                .add("inverted", isInverted())
                .add("open", isOpen())
                .toString();
    }
}
