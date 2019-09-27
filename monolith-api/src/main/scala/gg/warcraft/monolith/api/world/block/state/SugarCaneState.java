package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.IntegerBlockState;

public enum SugarCaneState implements IntegerBlockState {
    AGE_0,
    AGE_1,
    AGE_2,
    AGE_3,
    AGE_4,
    AGE_5,
    AGE_6,
    AGE_7,
    AGE_8,
    AGE_9,
    AGE_10,
    AGE_11,
    AGE_12,
    AGE_13,
    AGE_14,
    AGE_15;

    @Override
    public int toInt() {
        return ordinal();
    }
}
