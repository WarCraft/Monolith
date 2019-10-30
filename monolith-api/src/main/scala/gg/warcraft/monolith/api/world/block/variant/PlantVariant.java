package gg.warcraft.monolith.api.world.block.variant;

import gg.warcraft.monolith.api.world.block.FlowerPotVariant;
import gg.warcraft.monolith.api.world.item.ItemVariant;

public enum PlantVariant implements FlowerPotVariant, ItemVariant {
    CACTUS,
    DEAD_BUSH,
    FERN,

    // TODO remove upper ones into new Shrub enum or keep all in Plant?
    SUNFLOWER,
    LILAC,
    PEONY,
    ROSE_BUSH,
}
