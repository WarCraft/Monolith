package gg.warcraft.monolith.api.world.block.variant;

import gg.warcraft.monolith.api.world.block.BlockVariant;
import gg.warcraft.monolith.api.world.item.ItemVariant;

public enum LogVariant implements BlockVariant, ItemVariant {
    ACACIA(false),
    BIRCH(false),
    DARK_OAK(false),
    JUNGLE(false),
    OAK(false),
    SPRUCE(false),

    STRIPPED_ACACIA(true),
    STRIPPED_BIRCH(true),
    STRIPPED_DARK_OAK(true),
    STRIPPED_JUNGLE(true),
    STRIPPED_OAK(true),
    STRIPPED_SPRUCE(true);

    public final boolean stripped;

    LogVariant(boolean stripped) {
        this.stripped = stripped;
    }
}
