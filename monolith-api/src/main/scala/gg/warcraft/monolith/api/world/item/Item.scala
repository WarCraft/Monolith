package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.util.JavaInterop

trait Item extends JavaInterop {
  val `type`: ItemType

  val name: String
  def withName(name: String): this.type =
    copyWith("name", name)

  val count: Int
  def withCount(count: Int): this.type =
    copyWith("count", count)

  val durability: Int
  def withDurability(durability: Int): this.type =
    copyWith("durability", durability)

  val tooltip: Array[String]
  def withTooltip(tooltip: Array[String]): this.type =
    copyWith("tooltip", tooltip)
}

trait ColoredItem extends Item {
  val color: ItemColor
  def withColor(color: ItemColor): ColoredItem =
    copyWith("color", color)
}

trait ColorableItem extends Item {
  val color: Option[ItemColor]
  def withColor(color: Option[ItemColor]): ColorableItem =
    copyWith("color", color)
}

trait CookableItem extends Item {
  val cooked: Boolean
  def withCooked(cooked: Boolean): CookableItem =
    copyWith("cooked", cooked)
}

trait MaterialItem[T <: ItemMaterial] extends Item {
  val material: T
  def withMaterial(material: T): MaterialItem[T] =
    copyWith("material", material)
}

trait VariedItem[T <: ItemVariant] extends Item {
  val variant: T
  def withVariant(variant: T): VariedItem[T] =
    copyWith("variant", variant)
}

trait VariableItem[T <: ItemVariant] extends Item {
  val variant: Option[T]
  def withVariant(variant: Option[T]): VariableItem[T] =
    copyWith("variant", variant)
}
