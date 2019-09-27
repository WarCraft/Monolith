package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.NumericalBlockState;

public enum RedstoneWireState implements NumericalBlockState {
    POWER_0,
    POWER_1,
    POWER_2,
    POWER_3,
    POWER_4,
    POWER_5,
    POWER_6,
    POWER_7,
    POWER_8,
    POWER_9,
    POWER_10,
    POWER_11,
    POWER_12,
    POWER_13,
    POWER_14,
    POWER_15;

    @Override
    public int toInt() {
        return ordinal();
    }
}
