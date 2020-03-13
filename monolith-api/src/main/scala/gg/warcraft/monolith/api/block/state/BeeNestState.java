package gg.warcraft.monolith.api.block.state;

import gg.warcraft.monolith.api.block.BlockState;

public enum BeeNestState implements BlockState {
    LEVEL_0,
    LEVEL_1,
    LEVEL_2,
    LEVEL_3,
    LEVEL_4,
    LEVEL_5;

    private static final BeeNestState[] finalValues = values();

    public static BeeNestState valueOf(int data) {
        return finalValues[data];
    }

    @Override
    public int toInt() {
        return ordinal();
    }
}
