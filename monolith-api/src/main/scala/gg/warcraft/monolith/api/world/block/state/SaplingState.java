package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.NumericalBlockState;

public enum SaplingState implements NumericalBlockState {
    AGE_0,
    AGE_1;

    @Override
    public int toInt() {
        return ordinal();
    }
}
