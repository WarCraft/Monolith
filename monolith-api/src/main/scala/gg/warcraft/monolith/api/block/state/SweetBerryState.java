package gg.warcraft.monolith.api.block.state;

import gg.warcraft.monolith.api.block.BlockState;

public enum SweetBerryState implements BlockState {
    AGE_0,
    AGE_1,
    AGE_2,
    AGE_3;

    private static final SweetBerryState[] finalValues = values();

    public static SweetBerryState valueOf(int data) {
        return finalValues[data];
    }

    @Override
    public int toInt() {
        return ordinal();
    }
}
