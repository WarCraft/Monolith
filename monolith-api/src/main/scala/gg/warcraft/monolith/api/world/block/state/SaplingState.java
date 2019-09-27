package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.IntegerBlockState;

public enum SaplingState implements IntegerBlockState {
    AGE_0,
    AGE_1;

    @Override
    public int toInt() {
        return ordinal();
    }
}
