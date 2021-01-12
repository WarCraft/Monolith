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

package gg.warcraft.monolith.spigot.item

import gg.warcraft.monolith.api.item.{Inventory, Item, ItemType, ItemVariant}

class SpigotInventory(
    private val inventory: SpigotItemInventory
)(
    implicit private val itemTypeMapper: SpigotItemTypeMapper,
    implicit private val itemVariantMapper: SpigotItemVariantMapper,
    implicit private val itemMapper: SpigotItemMapper
) extends Inventory {
  override lazy val items: List[Item] =
    inventory.getContents
      .map { itemMapper.map(_, None) }
      .filter(_.isDefined)
      .map(_.get)
      .toList

  override def hasSpace(count: Int): Boolean =
    count <= inventory.getStorageContents.count(_ == null)

  // TODO add more elaborate checks for merge-able items
  override def hasSpaceFor(items: Item*): Boolean =
    hasSpace(items.length)

  override def contains(`type`: ItemType, count: Int): Boolean = {
    val material = itemTypeMapper.map(`type`)
    inventory.contains(material, count)
  }

  override def contains(`type`: ItemType): Boolean =
    contains(`type`, count = 1)

  override def contains(variant: ItemVariant, count: Int): Boolean = {
    val material = itemVariantMapper.map(variant)
    inventory.contains(material, count)
  }

  override def contains(variant: ItemVariant): Boolean =
    contains(variant, count = 1)

  override def contains(item: Item): Boolean = {
    val spigotItem = itemMapper.map(item)
    inventory.contains(spigotItem, spigotItem.getAmount)
  }
}
