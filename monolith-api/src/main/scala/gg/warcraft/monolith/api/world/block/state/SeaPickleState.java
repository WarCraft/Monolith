package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.BlockState;

public enum SeaPickleState implements BlockState {
    COUNT_1,
    COUNT_2,
    COUNT_3,
    COUNT_4;

    @Override
    public int toInt() {
        return ordinal() + 1;
    }
}
