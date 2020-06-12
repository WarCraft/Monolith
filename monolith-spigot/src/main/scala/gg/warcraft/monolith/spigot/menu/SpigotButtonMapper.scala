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

package gg.warcraft.monolith.spigot.menu

import gg.warcraft.monolith.api.item.ItemService
import gg.warcraft.monolith.api.menu.{Button, SkullButton}
import gg.warcraft.monolith.spigot.item.SpigotItemMapper
import org.bukkit.inventory.{ItemFlag, ItemStack}
import org.bukkit.Material
import org.bukkit.inventory.meta.SkullMeta

import scala.jdk.CollectionConverters._

class SpigotButtonMapper(implicit
    itemService: ItemService,
    itemMapper: SpigotItemMapper
) {
  def map(button: Button): ItemStack = button match {
    case it: SkullButton => mapSkull(it)
    case _ =>
      val item = itemService
        .create(button.icon)
        .withName(button.formattedTitle)
        .withTooltip(button.formattedTooltip: _*)
        .withHideAttributes(true)
      itemMapper.map(item)
  }

  def mapSkull(button: SkullButton): ItemStack = {
    val item = new ItemStack(Material.PLAYER_HEAD)
    val meta = item.getItemMeta.asInstanceOf[SkullMeta]
    meta.setDisplayName(button.formattedTitle)
    meta.setLore(button.formattedTooltip.asJava)
    meta.setOwner(button.playerName)
    meta.addItemFlags(ItemFlag.values(): _*)
    item.setItemMeta(meta)
    item
  }
}
