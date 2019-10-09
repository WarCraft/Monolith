package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.BlockState;

public enum BambooState implements BlockState {
    AGE_0,
    AGE_1;

    private static final BambooState[] finalValues = values();

    public static BambooState valueOf(int data) {
        return finalValues[data];
    }

    @Override
    public int toInt() {
        return ordinal();
    }
}
