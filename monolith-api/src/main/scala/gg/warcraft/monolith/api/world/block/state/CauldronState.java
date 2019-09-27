package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.NumericalBlockState;

public enum CauldronState implements NumericalBlockState {
    LEVEL_0,
    LEVEL_1,
    LEVEL_2,
    LEVEL_3;

    @Override
    public int toInt() {
        return ordinal();
    }
}
