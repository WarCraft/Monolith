package gg.warcraft.monolith.api.world.item

trait ItemVariant

trait EquipmentVariant extends ItemVariant {
  // ARMOR
  val leather: Boolean
  val chainmail: Boolean

  // TOOL
  val wood: Boolean
  val stone: Boolean

  // BOTH
  val iron: Boolean
  val gold: Boolean
  val diamond: Boolean
}
