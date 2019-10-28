package gg.warcraft.monolith.spigot.world.item

import java.util

import gg.warcraft.monolith.api.world.item.ItemVariant
import gg.warcraft.monolith.spigot.world.block.SpigotBlockVariantMapper
import javax.inject.Inject
import org.bukkit.Material

class SpigotItemVariantMapper @Inject()(
    blockMapper: SpigotBlockVariantMapper
) {
  private val cache =
    new util.EnumMap[Material, ItemVariant](classOf[Material])

  def map(block: SpigotItemStack): ItemVariant =
    cache.computeIfAbsent(block.getType, _ => compute(block))

  private def compute(item: SpigotItemStack): ItemVariant = item.getType match {

    case _ => null // TODO expose private compute def
  }
}
