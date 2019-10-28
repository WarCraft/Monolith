package gg.warcraft.monolith.spigot.world.item

import java.util

import gg.warcraft.monolith.api.world.item.ItemMaterial
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

  def map(material: Material): ItemMaterial =
    cache.computeIfAbsent(material, compute)

  private def compute(material: Material): ItemMaterial = material match {
    case it if !it.isItem => throw new IllegalArgumentException(s"$material")
    case it if it.isBlock => blockMapper.map(it).asInstanceOf[ItemMaterial]

    case Material.CHAINMAIL_BOOTS => null
    case Material.CHAINMAIL_CHESTPLATE => null
    case Material.CHAINMAIL_HELMET => null
    case Material.CHAINMAIL_LEGGINGS => null

    case Material.DIAMOND_AXE => null
    case Material.DIAMOND_BOOTS => null
    case Material.DIAMOND_CHESTPLATE => null
    case Material.DIAMOND_HELMET => null
    case Material.DIAMOND_HOE => null
    case Material.DIAMOND_HORSE_ARMOR => null
    case Material.DIAMOND_LEGGINGS => null
    case Material.DIAMOND_PICKAXE => null
    case Material.DIAMOND_SHOVEL => null
    case Material.DIAMOND_SWORD => null

    case Material.GOLDEN_APPLE => null
    case Material.GOLDEN_AXE => null
    case Material.GOLDEN_BOOTS => null
    case Material.GOLDEN_CARROT => null
    case Material.GOLDEN_CHESTPLATE => null
    case Material.GOLDEN_HELMET => null
    case Material.GOLDEN_HOE => null
    case Material.GOLDEN_HORSE_ARMOR => null
    case Material.GOLDEN_LEGGINGS => null
    case Material.GOLDEN_PICKAXE => null
    case Material.GOLDEN_SHOVEL => null
    case Material.GOLDEN_SWORD => null

    case Material.IRON_AXE => null
    case Material.IRON_BOOTS => null
    case Material.IRON_CHESTPLATE => null
    case Material.IRON_HELMET => null
    case Material.IRON_HOE => null
    case Material.IRON_HORSE_ARMOR => null
    case Material.IRON_INGOT => null
    case Material.IRON_LEGGINGS => null
    case Material.IRON_NUGGET => null
    case Material.IRON_PICKAXE => null
    case Material.IRON_SHOVEL => null
    case Material.IRON_SWORD => null

    case Material.LEATHER_BOOTS => null
    case Material.LEATHER_CHESTPLATE => null
    case Material.LEATHER_HELMET => null
    case Material.LEATHER_HORSE_ARMOR => null
    case Material.LEATHER_LEGGINGS => null

    case Material.STONE_AXE => null
    case Material.STONE_HOE => null
    case Material.STONE_PICKAXE => null
    case Material.STONE_SHOVEL => null
    case Material.STONE_SWORD => null

    case Material.WOODEN_AXE => null
    case Material.WOODEN_HOE => null
    case Material.WOODEN_PICKAXE => null
    case Material.WOODEN_SHOVEL => null
    case Material.WOODEN_SWORD => null

    case _ => throw new IllegalArgumentException(s"$material")
  }
}
