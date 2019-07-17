package gg.warcraft.monolith.app.world.block;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.Door;
import gg.warcraft.monolith.api.world.block.Hinge;

public class DoorBlock extends SimpleDirectionalBlock implements Door {
    private final Hinge hinge;
    private final boolean isTopHalf;
    private final boolean isOpen;

    public DoorBlock(BlockType type, BlockLocation location, Direction facing, Hinge hinge, boolean isTopHalf,
                     boolean isOpen) {
        super(type, location, facing);
        this.hinge = hinge;
        this.isTopHalf = isTopHalf;
        this.isOpen = isOpen;
    }

    @Override
    public Hinge getHinge() {
        return hinge;
    }

    @Override
    public boolean isTopHalf() {
        return isTopHalf;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public Door withHinge(Hinge hinge) {
        return new DoorBlock(getType(), getLocation(), getFacing(), hinge, isTopHalf(), isOpen());
    }

    @Override
    public Door withOpen(boolean open) {
        return new DoorBlock(getType(), getLocation(), getFacing(), getHinge(), isTopHalf(), open);
    }

    @Override
    public Door withFacing(Direction facing) {
        return new DoorBlock(getType(), getLocation(), facing, getHinge(), isTopHalf(), isOpen());
    }

    @Override
    public Door withLocation(BlockLocation location) {
        return new DoorBlock(getType(), location, getFacing(), getHinge(), isTopHalf(), isOpen());
    }
}
