package gg.warcraft.monolith.api.world.item

import java.util

import gg.warcraft.monolith.api.core.CaseClass
import gg.warcraft.monolith.api.world.block.BlockColor

import scala.annotation.varargs
import scala.collection.JavaConverters._

trait Item extends CaseClass {
  val `type`: ItemType
  val maxStackSize: Int = 64
  val edible: Boolean = false

  val data: ItemData
  private def withData(data: ItemData): this.type =
    copyWith("data", data)

  val name: String = data.name
  def withName(name: String): this.type =
    withData(data.copy(name = name))

  val tooltip: Array[String] = data.tooltip
  def withTooltip(tooltip: Array[String]): this.type =
    withData(data.copy(tooltip = tooltip))

  val unbreakable: Boolean = data.unbreakable
  def withUnbreakable(unbreakable: Boolean): this.type =
    withData(data.copy(unbreakable = unbreakable))

  val enchantments: util.Map[String, Int] = data.enchantments.asJava
  @varargs def addEnchantments(enchantments: (String, Int)*): this.type =
    withData(data.copy(enchantments = data.enchantments ++ enchantments))
  @varargs def removeEnchantments(enchantments: String*): this.type =
    withData(data.copy(enchantments = data.enchantments -- enchantments))
  @varargs def withEnchantments(enchantments: (String, Int)*): this.type =
    withData(data.copy(enchantments = Map[String, Int](enchantments: _*)))

  val flags: util.Set[ItemFlag] = data.flags.asJava
  @varargs def addFlags(flags: ItemFlag*): this.type =
    withData(data.copy(flags = data.flags ++ flags))
  @varargs def removeFlags(flags: ItemFlag*): this.type =
    withData(data.copy(flags = data.flags -- flags))
  @varargs def withFlags(flags: ItemFlag*): this.type =
    withData(data.copy(flags = Set(flags: _*)))
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
