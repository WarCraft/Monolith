package gg.warcraft.monolith.app.world.block;

import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.Sign;

import java.util.Arrays;

public class SignBlock extends SimpleDirectionalBlock implements Sign {
    private final Block attachedTo;
    private final String[] lines;

    public SignBlock(BlockType type, BlockLocation location, Direction facing, Block attachedTo, String[] lines) {
        super(type, location, facing);
        this.attachedTo = attachedTo;
        this.lines = lines;
    }

    @Override
    public Block getAttachedTo() {
        return attachedTo;
    }

    @Override
    public String[] getLines() {
        return Arrays.copyOf(lines, lines.length);
    }
}
