package gg.warcraft.monolith.app.world.block;

import com.google.common.base.MoreObjects;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.Slab;

import java.util.Objects;

public class SlabBlock extends SimpleBlock implements Slab {
    private final boolean top;
    private final boolean bottom;

    public SlabBlock(BlockType type, BlockLocation location, boolean top, boolean bottom) {
        super(type, location);
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public boolean isTop() {
        return top;
    }

    @Override
    public boolean isBottom() {
        return bottom;
    }

    @Override
    public Slab withLocation(BlockLocation location) {
        return new SlabBlock(getType(), location, isTop(), isBottom());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        SlabBlock other = (SlabBlock) object;
        return Objects.equals(getType(), other.getType())
                && Objects.equals(getLocation(), other.getLocation())
                && Objects.equals(isTop(), other.isTop())
                && Objects.equals(isBottom(), other.isBottom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getLocation(), isTop());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("type", getType())
                .add("location", getLocation())
                .add("top", isTop())
                .add("bottom", isBottom())
                .toString();
    }
}
