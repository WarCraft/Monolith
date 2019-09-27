package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.IntegerBlockState;

public enum CakeState implements IntegerBlockState {
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
