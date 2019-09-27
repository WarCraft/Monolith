package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.NumericalBlockState;

public enum CakeState implements NumericalBlockState {
    EATEN_0,
    EATEN_1,
    EATEN_2,
    EATEN_3,
    EATEN_4,
    EATEN_5,
    EATEN_6;

    @Override
    public int toInt() {
        return ordinal();
    }
}
