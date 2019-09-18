package gg.warcraft.monolith.api.world.block.material;

import gg.warcraft.monolith.api.world.block.*;

public enum StoneMaterial implements ButtonMaterial, InfestedMaterial,
        PressurePlateMaterial, SlabMaterial, StairsMaterial, WallMaterial {
    STONE,
    SMOOTH_STONE,

    STONE_BRICK,
    CHISELED_STONE_BRICK,
    CRACKED_STONE_BRICK,
    MOSSY_STONE_BRICK,

    COBBLESTONE,
    MOSSY_COBBLESTONE,

    ANDESITE,
    DIORITE,
    GRANITE,
    POLISHED_ANDESITE,
    POLISHED_DIORITE,
    POLISHED_GRANITE,

    END_STONE,
    END_STONE_BRICK,
}
