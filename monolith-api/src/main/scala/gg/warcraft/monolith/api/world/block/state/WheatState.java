package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.IntegerBlockState;

public enum WheatState implements IntegerBlockState {
    AGE_0,
    AGE_1,
    AGE_2,
    AGE_3,
    AGE_4,
    AGE_5,
    AGE_6,
    AGE_7;

    @Override
    public int toInt() {
        return ordinal();
    }
}
