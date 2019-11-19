package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.BlockState;

public enum SaplingState implements BlockState {
    AGE_0,
    AGE_1;

    private static final SaplingState[] finalValues = values();

    public static SaplingState valueOf(int data) {
        return finalValues[data];
    }

    @Override
    public int toInt() {
        return ordinal();
    }
}
