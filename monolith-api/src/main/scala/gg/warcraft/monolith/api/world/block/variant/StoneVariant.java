package gg.warcraft.monolith.api.world.block.variant;

import gg.warcraft.monolith.api.world.block.BlockVariant;
import gg.warcraft.monolith.api.world.item.ItemVariant;

public enum StoneVariant implements BlockVariant, ItemVariant {
    NORMAL,
    SMOOTH,

    BRICK,
    CHISELED_BRICK,
    CRACKED_BRICK,
    MOSSY_BRICK,
}
