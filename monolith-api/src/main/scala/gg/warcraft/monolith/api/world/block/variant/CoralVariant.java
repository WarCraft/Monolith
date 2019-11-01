package gg.warcraft.monolith.api.world.block.variant;

import gg.warcraft.monolith.api.world.block.BlockVariant;
import gg.warcraft.monolith.api.world.item.ItemVariant;

public enum CoralVariant implements BlockVariant, ItemVariant {
    BRAIN(false),
    BUBBLE(false),
    FIRE(false),
    HORN(false),
    TUBE(false),

    DEAD_BRAIN(true),
    DEAD_BUBBLE(true),
    DEAD_FIRE(true),
    DEAD_HORN(true),
    DEAD_TUBE(true);

    public final boolean dead;

    CoralVariant(boolean dead) {
        this.dead = dead;
    }
}
