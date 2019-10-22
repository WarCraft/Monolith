package gg.warcraft.monolith.api.world.item

import java.util

import gg.warcraft.monolith.api.core.CaseClass
import gg.warcraft.monolith.api.world.block.BlockColor

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
    copy(flags = Set(flags: _*))
}

object ItemData {
  def apply(color: BlockColor, name: String): ItemData = ItemData(
    s"${color.name.capitalize} ${name}" // TODO capitalize doesnt work, only touches first char and doesnt space _
  )

  def apply(color: Option[BlockColor], name: String): ItemData = color match {
    case None     => ItemData(name)
    case Some(it) => ItemData(it, name)
  }

  def apply(material: ItemMaterial, name: String): ItemData = ItemData(
    s"${material.toString.capitalize} ${name}"
  )

  def apply(variant: Boolean, variantName: String, name: String): ItemData = {
    if (variant) ItemData(s"${variantName} ${name}")
    else ItemData(name)
  }

  def apply(variant: ItemVariant, name: String): ItemData = {
    if (variant.toString == "NORMAL") ItemData(name)
    else ItemData(s"${variant.toString.capitalize} ${name}")
  }

  def apply(
      material: ItemMaterial,
      variant: Option[ItemVariant],
      name: String
  ): ItemData = variant match {
    case None => ItemData(material, name)
    case Some(it) =>
      val variantf = it.toString.capitalize
      val materialf = material.toString.capitalize
      ItemData(s"${variantf} ${materialf} ${name}")
  }
}
