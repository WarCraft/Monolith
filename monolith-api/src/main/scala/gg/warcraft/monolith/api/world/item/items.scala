package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.world.item.variant.SpawnEggVariant

case class Bed(
    color: ItemColor,
    name: String = "Bed",
    tooltip: Array[String] = Array()
) extends ColoredItem {
  override val `type` = ItemType.BED
}

case class PickAxe(
    material: ToolMaterial,
    name: String = "Pick Axe",
    tooltip: Array[String],
    durability: Int
) extends MaterialItem[ToolMaterial] with DurableItem {
  override val `type` = ItemType.PICKAXE
  override val maxDurability = 20
}

case class SpawnEgg(
    name: String,
    tooltip: Array[String],
    count: Int,
    durability: Int,
    variant: SpawnEggVariant
) extends VariedItem[SpawnEggVariant] {
  override val `type` = ItemType.SPAWN_EGG
}
