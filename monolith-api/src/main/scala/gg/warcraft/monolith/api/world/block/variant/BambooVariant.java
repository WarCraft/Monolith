package gg.warcraft.monolith.api.world.block.variant;

import gg.warcraft.monolith.api.world.block.BlockVariant;

// TODO Split SAPLING into its own block, has different data values than BAMBOO
public enum BambooVariant implements BlockVariant {
    SAPLING,
    NO_LEAVES,
    SMALL_LEAVES,
    LARGE_LEAVES,
}
