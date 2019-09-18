package gg.warcraft.monolith.api.world.block.material;

import gg.warcraft.monolith.api.world.block.FenceMaterial;
import gg.warcraft.monolith.api.world.block.SlabMaterial;
import gg.warcraft.monolith.api.world.block.StairsMaterial;
import gg.warcraft.monolith.api.world.block.WallMaterial;

public enum BrickMaterial implements FenceMaterial, SlabMaterial,
        StairsMaterial, WallMaterial {
    BRICK,
    NETHER_BRICK,
    RED_NETHER_BRICK,
}
