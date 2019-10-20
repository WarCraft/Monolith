package gg.warcraft.monolith.api.world.item

import java.util

import gg.warcraft.monolith.api.core.CaseClass
import gg.warcraft.monolith.api.world.block.BlockColor
import gg.warcraft.monolith.api.world.block.variant.AnvilVariant

import scala.annotation.varargs
import scala.collection.JavaConverters._

final case class ItemData(
    name: String,
    tooltip: Array[String] = Array.empty[String],
    unbreakable: Boolean = false,
    enchantments: Map[String, Int] = Map.empty[String, Int],
    flags: Set[ItemFlag] = Set.empty[ItemFlag]
) extends CaseClass {
  def withName(name: String): ItemData = copy(name = name)
  def withTooltip(tooltip: Array[String]): ItemData = copy(tooltip = tooltip)
  def withUnbreakable(unbreakable: Boolean): ItemData =
    copy(unbreakable = unbreakable)

  val enchantmentsAsJava: util.Map[String, Int] = enchantments.asJava
  @varargs def addEnchantments(enchantments: (String, Int)*): ItemData =
    copy(enchantments = this.enchantments ++ enchantments)
  @varargs def removeEnchantments(enchantments: String*): ItemData =
    copy(enchantments = this.enchantments -- enchantments)
  @varargs def withEnchantments(enchantments: (String, Int)*): ItemData =
    copy(enchantments = Map[String, Int](enchantments: _*))

  val flagsAsJava: util.Set[ItemFlag] = flags.asJava
  @varargs def addFlags(flags: ItemFlag*): ItemData =
    copy(flags = this.flags ++ flags)
  @varargs def removeFlags(flags: ItemFlag*): ItemData =
    copy(flags = this.flags -- flags)
  @varargs def withFlags(flags: ItemFlag*): ItemData =
    copy(flags = Set(flags))
}

object ItemData {
  def apply(color: BlockColor, name: String) = ItemData(
    s"${color.capitalized} ${name}"
  )

  def apply(color: Option[BlockColor], name: String): ItemData = color match {
    case Some(it) => ItemData(s"${it} ${name}")
    case None     => ItemData(name)
  }

  def apply(variant: ItemVariant): ItemData = variant match {
    case AnvilVariant.NORMAL => ItemData("Anvil")
    case it: AnvilVariant    => ItemData(s"${it.capitalized} Anvil")

    case _ => throw new IllegalArgumentException(s"${variant}")
  }
}
