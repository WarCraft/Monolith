package gg.warcraft.monolith.api.world.block.variant;

import gg.warcraft.monolith.api.world.block.BlockVariant;

public enum BambooLeavesVariant implements BlockVariant {
    NORMAL,
    SMALL, // TODO rename BambooVariant and SMALL_LEAVES, LARGE_LEAVES?
    LARGE, // TODO or make a BambooVariant with NORMAL and THICK?
}
