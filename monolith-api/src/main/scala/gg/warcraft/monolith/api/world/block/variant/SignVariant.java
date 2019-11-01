package gg.warcraft.monolith.api.world.block.variant;

import gg.warcraft.monolith.api.world.block.BlockVariant;
import gg.warcraft.monolith.api.world.item.ItemVariant;

public enum SignVariant implements BlockVariant, ItemVariant {
    ACACIA(false),
    BIRCH(false),
    DARK_OAK(false),
    JUNGLE(false),
    OAK(false),
    SPRUCE(false),

    ACACIA_WALL(true),
    BIRCH_WALL(true),
    DARK_OAK_WALL(true),
    JUNGLE_WALL(true),
    OAK_WALL(true),
    SPRUCE_WALL(true);

    public final boolean wall;

    SignVariant(boolean wall) {
        this.wall = wall;
    }
}
