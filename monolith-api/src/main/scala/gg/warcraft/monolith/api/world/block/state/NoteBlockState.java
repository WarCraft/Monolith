package gg.warcraft.monolith.api.world.block.state;

import gg.warcraft.monolith.api.world.block.BlockState;

public enum NoteBlockState implements BlockState {
    NOTE_0,
    NOTE_1,
    NOTE_2,
    NOTE_3,
    NOTE_4,
    NOTE_5,
    NOTE_6,
    NOTE_7,
    NOTE_8,
    NOTE_9,
    NOTE_10,
    NOTE_11,
    NOTE_12,
    NOTE_13,
    NOTE_14,
    NOTE_15,
    NOTE_16,
    NOTE_17,
    NOTE_18,
    NOTE_19,
    NOTE_20,
    NOTE_21,
    NOTE_22,
    NOTE_23,
    NOTE_24;

    private static final NoteBlockState[] finalValues = values();

    public static NoteBlockState valueOf(int data) {
        return finalValues[data];
    }

    @Override
    public int toInt() {
        return ordinal();
    }
}
