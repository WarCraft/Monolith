package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.world.item.variant.SpawnEggVariant

case class SpawnEgg(
    name: String,
    tooltip: Array[String],
    count: Int,
    durability: Int,
    variant: SpawnEggVariant
) extends VariedItem[SpawnEggVariant] {
  override val `type` = ItemType.SPAWN_EGG
}
