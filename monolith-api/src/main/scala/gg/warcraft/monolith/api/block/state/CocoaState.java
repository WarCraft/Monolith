package gg.warcraft.monolith.api.block.state;

import gg.warcraft.monolith.api.block.BlockState;

public enum CocoaState implements BlockState {
    AGE_0,
    AGE_1,
    AGE_2;

    private static final CocoaState[] finalValues = values();

    public static CocoaState valueOf(int data) {
        return finalValues[data];
    }

    @Override
    public int toInt() {
        return ordinal();
    }
}
