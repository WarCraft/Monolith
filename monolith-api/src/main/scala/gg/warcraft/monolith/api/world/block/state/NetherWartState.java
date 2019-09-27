package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.IntegerBlockState;

public enum NetherWartState implements IntegerBlockState {
    AGE_0,
    AGE_1,
    AGE_2,
    AGE_3;

    @Override
    public int toInt() {
        return ordinal();
    }
}
