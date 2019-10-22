package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.core.CaseClass
import gg.warcraft.monolith.api.world.block.BlockColor

final case class ItemData(
    name: String,
    tooltip: Array[String] = Array.empty[String],
    unbreakable: Boolean = false,
    enchantments: Map[String, Int] = Map.empty[String, Int],
    flags: Set[ItemFlag] = Set.empty[ItemFlag]
) extends CaseClass

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

  def apply(name: String, material: ItemMaterial): ItemData = ItemData(
    s"${name} ${material.toString.capitalize}"
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
      variant: ItemVariant,
      name: String
  ): ItemData = {
    val variantf = variant.toString.capitalize
    val materialf = material.toString.capitalize
    ItemData(s"${variantf} ${materialf} ${name}")
  }

  def apply(
      material: ItemMaterial,
      variant: Option[ItemVariant],
      name: String
  ): ItemData = variant match {
    case None     => ItemData(material, name)
    case Some(it) => ItemData(material, it, name)
  }
}
