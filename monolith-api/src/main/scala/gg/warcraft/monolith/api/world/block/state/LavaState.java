package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.BlockState;

public enum LavaState implements BlockState {
    LEVEL_1,
    LEVEL_2,
    LEVEL_3,
    LEVEL_4,
    LEVEL_5,
    LEVEL_6,
    LEVEL_7,
    LEVEL_8;

    private static final LavaState[] finalValues = values();

    public static LavaState valueOf(int data) {
        return finalValues[data - 1];
    }

    @Override
    public int toInt() {
        return ordinal() + 1;
    }
}
