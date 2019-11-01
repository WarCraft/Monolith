package gg.warcraft.monolith.api.world.block.variant;

import gg.warcraft.monolith.api.world.block.BlockVariant;
import gg.warcraft.monolith.api.world.item.ItemVariant;

public enum MobHeadVariant implements BlockVariant, ItemVariant {
    CREEPER(false),
    DRAGON(false),
    PLAYER(false),
    SKELETON(false),
    WITHER_SKELETON(false),
    ZOMBIE(false),

    CREEPER_WALL(true),
    DRAGON_WALL(true),
    PLAYER_WALL(true),
    SKELETON_WALL(true),
    WITHER_SKELETON_WALL(true),
    ZOMBIE_WALL(true);

    public final boolean wall;

    MobHeadVariant(boolean wall) {
        this.wall = wall;
    }
}
