package gg.warcraft.monolith.app.world.block;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.Chest;

public class ChestBlock extends SimpleDirectionalBlock implements Chest {

    public ChestBlock(BlockType type, BlockLocation location, Direction facing) {
        super(type, location, facing);
    }

    @Override
    public ChestBlock withLocation(BlockLocation location) {
        return new ChestBlock(getType(), location, getFacing());
    }

    @Override
    public ChestBlock withFacing(Direction facing) {
        return new ChestBlock(getType(), getLocation(), facing);
    }
}
