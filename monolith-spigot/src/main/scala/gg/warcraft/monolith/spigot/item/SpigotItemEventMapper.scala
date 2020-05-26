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

import java.util.UUID

import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.entity.Entity
import gg.warcraft.monolith.api.item.{Item, ItemPickupEvent, ItemPrePickupEvent}
import org.bukkit.event.{EventHandler, EventPriority, Listener}

class SpigotItemEventMapper(implicit
    eventService: EventService,
    itemMapper: SpigotItemMapper
) extends Listener {
  private def parse(
      item: SpigotDrop,
      entity: SpigotEntity
  ): (UUID, Item, UUID, Entity.Type) = (
    item.getUniqueId,
    itemMapper.map(item.getItemStack).get,
    entity.getUniqueId,
    Entity.Type.withName(entity.getType.name)
  )

  @EventHandler(priority = EventPriority.HIGH)
  def prePickup(event: SpigotItemPickupEvent): Unit = {
    val (itemId, item, entityId, entityType) = parse(event.getItem, event.getEntity)
    val cancelled = event.isCancelled
    val prePickupEvent = ItemPrePickupEvent(
      item,
      itemId,
      entityId,
      entityType,
      cancelled
    )

    val reducedEvent = eventService.publish(prePickupEvent)
    event.setCancelled(!reducedEvent.allowed)
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onPickup(event: SpigotItemPickupEvent): Unit = {
    val (itemId, item, entityId, entityType) = parse(event.getItem, event.getEntity)
    val pickupEvent = ItemPickupEvent(item, itemId, entityId, entityType)

    eventService.publish(pickupEvent)
  }
}
