package gg.warcraft.monolith.api.world.block

import gg.warcraft.monolith.api.world.item.ItemVariant

trait BlockVariant

trait FlowerPotVariant extends BlockVariant

trait InfestedVariant extends BlockVariant with ItemVariant

trait SlabVariant extends BlockVariant with ItemVariant

trait StairsVariant extends BlockVariant with ItemVariant

trait WallVariant extends BlockVariant with ItemVariant
