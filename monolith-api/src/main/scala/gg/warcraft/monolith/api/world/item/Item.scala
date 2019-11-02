package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.core.CaseClass
import gg.warcraft.monolith.api.world.block.BlockColor

import scala.annotation.varargs
import scala.collection.immutable.Map

trait Item extends CaseClass {
  val `type`: ItemType
  val edible: Boolean = false

  val name: String
  def withName(name: String): this.type =
    copyWith("name", name)

  val tooltip: Array[String]
  def withTooltip(tooltip: Array[String]): this.type =
    copyWith("tooltip", tooltip)

  val attributes: Set[String]
  @varargs def addAttributes(attributes: String*): this.type =
    copyWith("attributes", this.attributes ++ attributes)
  @varargs def removeAttributes(attributes: String*): this.type =
    copyWith("attributes", this.attributes -- attributes)
  @varargs def withAttributes(attributes: String*): this.type =
    copyWith("attributes", Set(attributes: _*))

  val hideAttributes: Boolean
  def withHideAttributes(hideAttributes: Boolean): this.type =
    copyWith("hideAttributes", hideAttributes)
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

trait DurableItem extends Item {
  val durability: Int
  def withDurability(durability: Int): this.type =
    copyWith("durability", durability)

  def maxDurability: Int
  require(durability > 0 && durability <= maxDurability, {
    s"durability is $durability, must be > 0 and <= $maxDurability"
  })

  val unbreakable: Boolean
  def withUnbreakable(unbreakable: Boolean): this.type =
    copyWith("unbreakable", unbreakable)

  val hideUnbreakable: Boolean
  def withHideUnbreakable(hideUnbreakable: Boolean): this.type =
    copyWith("hideUnbreakable", hideUnbreakable)
}

trait EnchantableItem extends Item {
  val enchantments: Map[String, Int]
  @varargs def addEnchantments(enchantments: (String, Int)*): this.type =
    copyWith("enchantments", this.enchantments ++ enchantments)
  @varargs def removeEnchantments(enchantments: String*): this.type =
    copyWith("enchantments", this.enchantments -- enchantments)
  @varargs def withEnchantments(enchantments: (String, Int)*): this.type =
    copyWith("enchantments", Map[String, Int](enchantments: _*))

  val hideEnchantments: Boolean
  def withHideEnchantments(hideEnchantments: Boolean): this.type =
    copyWith("hideEnchantments", hideEnchantments)
}

trait StackableItem extends Item {
  val count: Int
  def withCount(count: Int): this.type =
    copyWith("count", count)

  def maxCount: Int = 64
  require(count > 0 && count <= maxCount, {
    s"count is $count, must be > 0 and <= $maxCount"
  })
}

trait VariableItem[T <: ItemVariant] extends Item {
  val variant: T
  def withVariant(variant: T): this.type =
    copyWith("variant", variant)
}
