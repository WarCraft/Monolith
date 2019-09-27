package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.NumericalBlockState;

public enum BeetrootState implements NumericalBlockState {
    AGE_0,
    AGE_1,
    AGE_2,
    AGE_3;

    @Override
    public int toInt() {
        return ordinal();
    }
}
