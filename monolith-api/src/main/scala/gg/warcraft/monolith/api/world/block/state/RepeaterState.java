package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.IntegerBlockState;

public enum RepeaterState implements IntegerBlockState {
    DELAY_1,
    DELAY_2,
    DELAY_3,
    DELAY_4;

    @Override
    public int toInt() {
        return ordinal() + 1;
    }
}
