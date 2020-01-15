package gg.warcraft.monolith.spigot.world.item

import java.util.UUID

import gg.warcraft.monolith.api.core.EventService
import gg.warcraft.monolith.api.entity.EntityType
import gg.warcraft.monolith.api.world.item.{Item, ItemPickupEvent, ItemPrePickupEvent}
import org.bukkit.event.{EventHandler, EventPriority, Listener}

class SpigotItemEventMapper(
    implicit private val eventService: EventService,
    implicit private val itemMapper: SpigotItemMapper
) extends Listener {
  private def parse(
      item: SpigotItem,
      entity: SpigotEntity
  ): (UUID, Item, UUID, EntityType) = (
    item.getUniqueId,
    itemMapper.map(item.getItemStack).get,
    entity.getUniqueId,
    EntityType.valueOf(entity.getType.name)
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
