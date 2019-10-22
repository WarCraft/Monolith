package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.core.CaseClass
import gg.warcraft.monolith.api.world.block.BlockColor

trait Item extends CaseClass {
  val `type`: ItemType

  val data: ItemData
  def withData(data: ItemData): this.type =
    copyWith("data", data)

  def withName(name: String): this.type =
    copyWith("data", data.withName(name))
  // TODO do we want these ItemData delegate methods?
}

trait ColoredItem extends Item {
  val color: BlockColor
  def withColor(color: BlockColor): this.type =
    copyWith("color", color)
}

trait ColorableItem extends Item {
  val color: Option[BlockColor]
  def withColor(color: Option[BlockColor]): this.type =
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

  val maxDurability: Int
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

  val maxStackSize: Int
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
