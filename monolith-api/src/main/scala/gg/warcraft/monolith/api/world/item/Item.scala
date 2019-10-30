package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.core.CaseClass
import gg.warcraft.monolith.api.world.block.BlockColor

import scala.annotation.varargs

trait Item extends CaseClass {
  val `type`: ItemType
  val edible: Boolean = false

  val name: String
  def withName(name: String): this.type =
    copyWith("name", name)

  val tooltip: Array[String]
  def withTooltip(tooltip: Array[String]): this.type =
    copyWith("tooltip", tooltip)

  val attributes: List[String]
  @varargs def addAttributes(attributes: String*): this.type =
    copyWith("attributes", this.attributes ++ attributes)
  //  @varargs def removeAttributes(attributes: String*): this.type =
  //    copyWith("attributes", this.attributes -- attributes)
  @varargs def withAttributes(attributes: String*): this.type =
    copyWith("attributes", List(attributes: _*))

  //  val flags: util.Set[ItemFlag] TODO these might be able to be integrated into specific item types
  //  @varargs def addFlags(flags: ItemFlag*): this.type =
  //    withData(data.copy(flags = data.flags ++ flags))
  //  @varargs def removeFlags(flags: ItemFlag*): this.type =
  //    withData(data.copy(flags = data.flags -- flags))
  //  @varargs def withFlags(flags: ItemFlag*): this.type =
  //    withData(data.copy(flags = Set(flags: _*)))
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

  val maxDurability: Int
  require(durability > 0 && durability <= maxDurability, {
    s"durability is $durability, must be > 0 and <= $maxDurability"
  })

  val unbreakable: Boolean
  def withUnbreakable(unbreakable: Boolean): this.type =
    copyWith("unbreakable", unbreakable)
}

trait EnchantableItem extends Item {
  val enchantments: Map[String, Int]
  @varargs def addEnchantments(enchantments: (String, Int)*): this.type =
    copyWith("enchantments", this.enchantments ++ enchantments)
  @varargs def removeEnchantments(enchantments: String*): this.type =
    copyWith("enchantments", this.enchantments -- enchantments)
  @varargs def withEnchantments(enchantments: (String, Int)*): this.type =
    copyWith("enchantments", Map[String, Int](enchantments: _*))
}

trait MaterialItem[T <: ItemMaterial] extends Item {
  val material: T
  def withMaterial(material: T): this.type =
    copyWith("material", material)
}

trait StackableItem extends Item {
  val count: Int
  def withCount(count: Int): this.type =
    copyWith("count", count)

  val maxCount: Int = 64
  require(count > 0 && count <= maxCount, {
    s"count is $count, must be > 0 and <= $maxCount"
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
