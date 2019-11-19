package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.BlockState;

public enum RepeaterState implements BlockState {
    DELAY_1,
    DELAY_2,
    DELAY_3,
    DELAY_4;

    private static final RepeaterState[] finalValues = values();

    public static RepeaterState valueOf(int data) {
        return finalValues[data - 1];
    }

    @Override
    public int toInt() {
        return ordinal() + 1;
    }
}
