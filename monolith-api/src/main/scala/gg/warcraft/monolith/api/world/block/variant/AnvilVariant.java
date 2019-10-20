package gg.warcraft.monolith.api.world.block.variant;

import gg.warcraft.monolith.api.world.block.BlockVariant;
import gg.warcraft.monolith.api.world.item.ItemVariant;

public enum AnvilVariant implements BlockVariant, ItemVariant {
    NORMAL("Normal"),
    CHIPPED("Chipped"),
    DAMAGED("Damaged");

    public final String capitalized;

    AnvilVariant(String capitalized) {
        this.capitalized = capitalized;
    }
}
