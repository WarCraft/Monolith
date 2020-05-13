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
