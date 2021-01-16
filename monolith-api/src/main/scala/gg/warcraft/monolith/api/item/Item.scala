/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.api.item

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

  // TODO what are these?
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

  val customModelData: Option[Int]
  def withCustomModelData(customModelData: Option[Int]): this.type =
    copyWith("customModelData", customModelData)

  def isVariant(variant: ItemVariant): Boolean = false
  def hasData(data: ItemTypeOrVariant): Boolean = this.`type` == data
  def data: ItemTypeOrVariant = this.`type`
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
  def oneLessOrNone: Option[this.type] =
    if(count > 1) Some(withCount(count - 1)) else None

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
  override def data: ItemTypeOrVariant = variant
}
