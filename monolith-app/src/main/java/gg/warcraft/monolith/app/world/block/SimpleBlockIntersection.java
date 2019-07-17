package gg.warcraft.monolith.app.world.block;

import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockFace;
import gg.warcraft.monolith.api.world.block.BlockIntersection;

public class SimpleBlockIntersection implements BlockIntersection {
    private final Block block;
    private final BlockFace face;
    private final Location location;

    public SimpleBlockIntersection(Block block, BlockFace face, Location location) {
        this.block = block;
        this.face = face;
        this.location = location;
    }

    @Override
    public Block getBlock() {
        return block;
    }

    @Override
    public BlockFace getFace() {
        return face;
    }

    @Override
    public Location getLocation() {
        return location;
    }
}
