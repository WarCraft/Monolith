package gg.warcraft.monolith.api.block.state;

import gg.warcraft.monolith.api.block.BlockState;

// TODO properly check what Level means for lava
public enum LavaState implements BlockState {
    LEVEL_0,
    LEVEL_1,
    LEVEL_2,
    LEVEL_3,
    LEVEL_4,
    LEVEL_5,
    LEVEL_6,
    LEVEL_7,
    LEVEL_8,
    LEVEL_9,
    LEVEL_10,
    LEVEL_11,
    LEVEL_12,
    LEVEL_13,
    LEVEL_14,
    LEVEL_15;

    private static final LavaState[] finalValues = values();

    public static LavaState valueOf(int data) {
        return finalValues[data];
    }

    @Override
    public int toInt() {
        return ordinal();
    }
}
