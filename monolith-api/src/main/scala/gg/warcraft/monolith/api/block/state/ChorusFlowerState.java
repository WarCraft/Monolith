package gg.warcraft.monolith.api.block.state;

import gg.warcraft.monolith.api.block.BlockState;

public enum ChorusFlowerState implements BlockState {
    AGE_0,
    AGE_1,
    AGE_2,
    AGE_3,
    AGE_4,
    AGE_5;

    private static final ChorusFlowerState[] finalValues = values();

    public static ChorusFlowerState valueOf(int data) {
        return finalValues[data];
    }

    @Override
    public int toInt() {
        return ordinal();
    }
}
