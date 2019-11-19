package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.BlockState;

public enum CauldronState implements BlockState {
    LEVEL_0,
    LEVEL_1,
    LEVEL_2,
    LEVEL_3;

    private static final CauldronState[] finalValues = values();

    public static CauldronState valueOf(int data) {
        return finalValues[data];
    }

    @Override
    public int toInt() {
        return ordinal();
    }
}
