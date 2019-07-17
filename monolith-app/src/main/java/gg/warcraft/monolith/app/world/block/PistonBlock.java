package gg.warcraft.monolith.app.world.block;

import com.google.common.base.MoreObjects;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.Piston;

import java.util.Objects;

public class PistonBlock extends SimpleDirectionalBlock implements Piston {
    private final boolean powered;
    private final boolean sticky;

    public PistonBlock(BlockType type, BlockLocation location, Direction facing, boolean powered, boolean sticky) {
        super(type, location, facing);
        this.powered = powered;
        this.sticky = sticky;
    }

    @Override
    public boolean isPowered() {
        return powered;
    }

    @Override
    public boolean isSticky() {
        return sticky;
    }

    @Override
    public Piston withPowered(boolean powered) {
        return new PistonBlock(getType(), getLocation(), getFacing(), powered, isSticky());
    }

    @Override
    public Piston withSticky(boolean sticky) {
        return new PistonBlock(getType(), getLocation(), getFacing(), isPowered(), sticky);
    }

    @Override
    public Piston withLocation(BlockLocation location) {
        return new PistonBlock(getType(), location, getFacing(), isPowered(), isSticky());
    }

    @Override
    public Piston withFacing(Direction facing) {
        return new PistonBlock(getType(), getLocation(), facing, isPowered(), isSticky());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Piston other = (Piston) object;
        return Objects.equals(getType(), other.getType())
                && Objects.equals(getLocation(), other.getLocation())
                && Objects.equals(getFacing(), other.getFacing())
                && Objects.equals(isPowered(), other.isPowered())
                && Objects.equals(isSticky(), other.isSticky());
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
                .add("sticky", isSticky())
                .toString();
    }
}
