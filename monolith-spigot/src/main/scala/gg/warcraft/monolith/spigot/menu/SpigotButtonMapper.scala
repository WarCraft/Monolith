package gg.warcraft.monolith.spigot.menu

import gg.warcraft.monolith.api.item.ItemService
import gg.warcraft.monolith.api.menu.{Button, SkullButton}
import gg.warcraft.monolith.spigot.item.SpigotItemMapper
import org.bukkit.inventory.{ItemFlag, ItemStack}
import org.bukkit.Material
import org.bukkit.inventory.meta.SkullMeta

import scala.jdk.CollectionConverters._

class SpigotButtonMapper(
    implicit itemService: ItemService,
    itemMapper: SpigotItemMapper
) {
  def map(button: Button): ItemStack = {
    case it: SkullButton => mapSkull(it)
    case _ =>
      val item = itemService
        .create(button.icon)
        .withName(button.title)
        .withTooltip(button.tooltip: _*)
        .withHideAttributes(true);
      itemMapper.map(item)
  }

  def mapSkull(button: SkullButton): ItemStack = {
    val item = new ItemStack(Material.PLAYER_HEAD)
    val meta = item.getItemMeta.asInstanceOf[SkullMeta]
    meta.setDisplayName(button.title)
    meta.setLore(button.tooltip.asJava)
    meta.setOwner(button.name)
    meta.addItemFlags(ItemFlag.values(): _*)
    item.setItemMeta(meta)
    item
  }
}
