package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.core.CaseClass

trait Item extends CaseClass {
  val `type`: ItemType

  val name: String
  def withName(name: String): this.type =
    copyWith("name", name)

  val tooltip: Array[String]
  def withTooltip(tooltip: Array[String]): this.type =
    copyWith("tooltip", tooltip)
}

trait ColoredItem extends Item {
  val color: ItemColor
  def withColor(color: ItemColor): this.type =
    copyWith("color", color)
}

trait ColorableItem extends Item {
  val color: Option[ItemColor]
  def withColor(color: Option[ItemColor]): this.type =
    copyWith("color", color)
}

trait CookableItem extends Item {
  val cooked: Boolean
  def withCooked(cooked: Boolean): this.type =
    copyWith("cooked", cooked)
}

trait DurableItem extends Item {
  val durability: Int
  def withDurability(durability: Int): this.type =
    copyWith("durability", durability)

  def maxDurability: Int
  require(durability > 0 && durability <= maxDurability, {
    s"durability is $durability, must be > 0 and <= $maxDurability"
  })
}

trait MaterialItem[T <: ItemMaterial] extends Item {
  val material: T
  def withMaterial(material: T): this.type =
    copyWith("material", material)
}

trait StackableItem extends Item {
  val stackSize: Int
  def withStackSize(stackSize: Int): this.type =
    copyWith("stackSize", stackSize)

  def maxStackSize: Int
  require(stackSize > 0 && stackSize <= maxStackSize, {
    s"stackSize is $stackSize, must be > 0 and <= $maxStackSize"
  })
}

trait VariedItem[T <: ItemVariant] extends Item {
  val variant: T
  def withVariant(variant: T): this.type =
    copyWith("variant", variant)
}

trait VariableItem[T <: ItemVariant] extends Item {
  val variant: Option[T]
  def withVariant(variant: Option[T]): this.type =
    copyWith("variant", variant)
}
