package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.BlockState;

public enum NetherWartState implements BlockState {
    AGE_0,
    AGE_1,
    AGE_2,
    AGE_3;

    @Override
    public int toInt() {
        return ordinal();
    }
}
