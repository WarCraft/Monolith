package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.NumericalBlockState;

public enum ComposterState implements NumericalBlockState {
    LEVEL_0,
    LEVEL_1,
    LEVEL_2,
    LEVEL_3,
    LEVEL_4,
    LEVEL_5,
    LEVEL_6,
    LEVEL_7,
    LEVEL_8;

    @Override
    public int toInt() {
        return ordinal();
    }
}
