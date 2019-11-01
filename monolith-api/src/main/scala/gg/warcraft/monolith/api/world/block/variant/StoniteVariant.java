package gg.warcraft.monolith.api.world.block.variant;

import gg.warcraft.monolith.api.world.block.BlockVariant;
import gg.warcraft.monolith.api.world.item.ItemVariant;

public enum StoniteVariant implements BlockVariant, ItemVariant {
    ANDESITE(true, false, false, false),
    DIORITE(false, true, false, false),
    GRANITE(false, false, true, false),

    POLISHED_ANDESITE(true, false, false, true),
    POLISHED_DIORITE(false, true, false, true),
    POLISHED_GRANITE(false, false, true, true);

    public final boolean andesite;
    public final boolean diorite;
    public final boolean granite;
    public final boolean polished;

    StoniteVariant(
            boolean andesite,
            boolean diorite,
            boolean granite,
            boolean polished
    ) {
        this.andesite = andesite;
        this.diorite = diorite;
        this.granite = granite;
        this.polished = polished;
    }
}
