package gg.warcraft.monolith.api.world.block

import gg.warcraft.monolith.api.world.item.ItemMaterial

trait BlockMaterial

trait ButtonMaterial extends BlockMaterial with ItemMaterial

trait DoorMaterial extends BlockMaterial with ItemMaterial

trait FenceMaterial extends BlockMaterial with ItemMaterial

trait InfestedMaterial extends BlockMaterial with ItemMaterial

trait PillarMaterial extends BlockMaterial with ItemMaterial

trait PressurePlateMaterial extends BlockMaterial with ItemMaterial

trait SlabMaterial extends BlockMaterial with ItemMaterial

trait StairsMaterial extends BlockMaterial with ItemMaterial

trait TrapdoorMaterial extends BlockMaterial with ItemMaterial

trait WallMaterial extends BlockMaterial with ItemMaterial
