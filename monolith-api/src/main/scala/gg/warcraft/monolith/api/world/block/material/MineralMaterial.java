package gg.warcraft.monolith.api.world.block.material;

import gg.warcraft.monolith.api.world.block.BlockMaterial;
import gg.warcraft.monolith.api.world.item.ItemMaterial;

public enum MineralMaterial implements BlockMaterial, ItemMaterial {
    COAL,
    DIAMOND,
    EMERALD,
    GOLD,
    IRON,
    LAPIS,
    QUARTZ, // TODO remove? has own QuartzBlock with variants
    REDSTONE,
}
