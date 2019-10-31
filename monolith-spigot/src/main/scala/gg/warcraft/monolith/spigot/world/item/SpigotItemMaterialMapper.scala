package gg.warcraft.monolith.spigot.world.item

import java.util

import gg.warcraft.monolith.api.core.Extensions._
import gg.warcraft.monolith.api.world.item.ItemMaterial
import gg.warcraft.monolith.api.world.item.material.{ ArmorMaterial, ToolMaterial }
import gg.warcraft.monolith.spigot.world.block.SpigotBlockMaterialMapper
import javax.inject.Inject
import org.bukkit.Material

class SpigotItemMaterialMapper @Inject()(
    private val colorMapper: SpigotItemColorMapper,
    private val variantMapper: SpigotItemVariantMapper,
    private val blockMapper: SpigotBlockMaterialMapper
) {
  private val cache =
    new util.EnumMap[Material, ItemMaterial](classOf[Material])

  def map(material: Material): ItemMaterial = cache.computeIfAbsent(material, {
    case _ if !material.isItem => throw new IllegalArgumentException(s"$material")
    case _ if material.isBlock => blockMapper.map(material).asInstanceOf[ItemMaterial]

    case Material.DIAMOND_BOOTS | Material.DIAMOND_CHESTPLATE |
         Material.DIAMOND_HELMET | Material.DIAMOND_HORSE_ARMOR |
         Material.DIAMOND_LEGGINGS =>
      ArmorMaterial.DIAMOND

    case Material.DIAMOND_AXE | Material.DIAMOND_HOE |
         Material.DIAMOND_PICKAXE | Material.DIAMOND_SHOVEL |
         Material.DIAMOND_SWORD =>
      ToolMaterial.DIAMOND

    case Material.GOLDEN_BOOTS | Material.GOLDEN_CHESTPLATE |
         Material.GOLDEN_HELMET | Material.GOLDEN_HORSE_ARMOR |
         Material.GOLDEN_LEGGINGS =>
      ArmorMaterial.GOLD

    case Material.GOLDEN_AXE | Material.GOLDEN_HOE |
         Material.GOLDEN_PICKAXE | Material.GOLDEN_SHOVEL |
         Material.GOLDEN_SWORD =>
      ToolMaterial.GOLD

    case Material.IRON_BOOTS | Material.IRON_CHESTPLATE |
         Material.IRON_HELMET | Material.IRON_HORSE_ARMOR |
         Material.IRON_LEGGINGS =>
      ArmorMaterial.IRON

    case Material.IRON_AXE | Material.IRON_HOE |
         Material.IRON_PICKAXE | Material.IRON_SHOVEL |
         Material.IRON_SWORD =>
      ToolMaterial.IRON

    case _ => material.name match {
      case r"CHAINMAIL.*" => ArmorMaterial.CHAINMAIL
      case r"LEATHER.*"   => ArmorMaterial.LEATHER
      case r"STONE.*"     => ToolMaterial.STONE
      case r"WOODEN.*"    => ToolMaterial.WOOD

      case _ => throw new IllegalArgumentException(s"$material")
    }
  })
}
