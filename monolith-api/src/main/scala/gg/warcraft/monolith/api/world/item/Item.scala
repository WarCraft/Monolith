package gg.warcraft.monolith.api.world.item

import java.util

import gg.warcraft.monolith.api.core.CaseTrait

import scala.annotation.varargs
import scala.collection.immutable.Map
import scala.jdk.CollectionConverters._

trait Item extends CaseTrait {
  val `type`: ItemType
  val edible: Boolean = false

  val name: String
  def withName(name: String): this.type =
    copyWith("name", name)

  val tooltip: List[String]
  def getTooltip: util.List[String] = tooltip.asJava
  @varargs def addTooltip(tooltip: String*): this.type =
    copyWith("tooltip", this.tooltip ++ tooltip)
  @varargs def withTooltip(tooltip: String*): this.type =
    copyWith("tooltip", List(tooltip: _*))

  val attributes: Set[String]
  def getAttributes: util.Set[String] = attributes.asJava
  @varargs def hasAttributes(attributes: String*): Boolean =
    attributes.forall(this.attributes.contains)
  @varargs def addAttributes(attributes: String*): this.type =
    copyWith("attributes", this.attributes ++ attributes)
  @varargs def removeAttributes(attributes: String*): this.type =
    copyWith("attributes", this.attributes -- attributes)
  @varargs def withAttributes(attributes: String*): this.type =
    copyWith("attributes", Set(attributes: _*))

  val hideAttributes: Boolean
  def withHideAttributes(hideAttributes: Boolean): this.type =
    copyWith("hideAttributes", hideAttributes)

  def isVariant(variant: ItemVariant): Boolean = false
  def hasData(data: ItemTypeOrVariant): Boolean = this.`type` == data
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

  override def isVariant(variant: ItemVariant): Boolean =
    this.variant == variant
  override def hasData(data: ItemTypeOrVariant): Boolean =
    this.variant == data || super.hasData(data)
}
