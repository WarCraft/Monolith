package gg.warcraft.monolith.spigot.world

import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.world.ChunkPreLoadEvent
import org.bukkit.event.{EventHandler, EventPriority, Listener}
import org.bukkit.event.world.{ChunkLoadEvent, ChunkUnloadEvent}

class SpigotWorldEventMapper(
    implicit private val eventService: EventService
) extends Listener {
  @EventHandler(priority = EventPriority.HIGH)
  def preLoad(event: ChunkLoadEvent): Unit = {
    val chunk = event.getChunk
    val preLoadEvent = ChunkPreLoadEvent()
    eventService.publish(preLoadEvent)
    /*
      Chunk chunk = event.getChunk();
      ChunkLoadedEvent chunkLoadedEvent = new ChunkLoadedEvent(chunk.getX(), chunk.getZ());
      eventService.publish(chunkLoadedEvent);

      Set<Entity> allowedRespawns = new HashSet<>();
      for (Entity entity : chunk.getEntities()) {
          UUID entityId = entity.getUniqueId();
          EntityType entityType = EntityType.valueOf(entity.getType().name());
          Location entityLocation = locationMapper.map(entity.getLocation());
          EntityPreRespawnEvent preRespawnEvent = new SimpleEntityPreRespawnEvent(entityId, entityType, entityLocation, false);
          eventService.publish(preRespawnEvent);
          // TODO if the spawn location is changed on the pre event nothing actually happens
          if (!preRespawnEvent.isCancelled() || preRespawnEvent.isExplicitlyAllowed()) {
              allowedRespawns.add(entity);
          } else {
              entity.remove(); // TODO should this be called on the next tick instead?
          }
      }

      for (Entity entity : allowedRespawns) {
          UUID entityId = entity.getUniqueId();
          EntityType entityType = EntityType.valueOf(entity.getType().name());
          Location entityLocation = locationMapper.map(entity.getLocation());
          EntityRespawnEvent respawnEvent = new SimpleEntityRespawnEvent(entityId, entityType, entityLocation);
          eventService.publish(respawnEvent);
      }
   */
  }

  @EventHandler(priority = EventPriority.MONITOR)
  def onLoad(event: ChunkLoadEvent): Unit = {}

  @EventHandler(priority = EventPriority.HIGH)
  def preUnload(event: ChunkUnloadEvent): Unit = {
    /*
      Chunk chunk = event.getChunk();
      ChunkPreUnloadEvent preUnloadEvent = new ChunkPreUnloadEvent(chunk.getX(), chunk.getZ(), false);
      eventService.publish(preUnloadEvent);
//        if (preUnloadEvent.isExplicitlyAllowed()) {
//            event.setCancelled(false);
//            return;
//        } else if (preUnloadEvent.isCancelled()) {
//            event.setCancelled(true);
//            return;
//        }

      boolean cancelled = false;
      for (Entity entity : chunk.getEntities()) {
          UUID entityId = entity.getUniqueId();
          EntityType entityType = EntityType.valueOf(entity.getType().name());
          EntityPreDespawnEvent preDespawnEvent = new SimpleEntityPreDespawnEvent(entityId, entityType, false);
          eventService.publish(preDespawnEvent);
          if (preDespawnEvent.isCancelled() && !preDespawnEvent.isExplicitlyAllowed()) {
              cancelled = true;
              break;
          }
      }
//        if (cancelled) {
//            event.setCancelled(true);
//        }
   */
  }

  @EventHandler(priority = EventPriority.MONITOR)
  def onUnload(event: ChunkUnloadEvent): Unit = {
    /*
      Chunk chunk = event.getChunk();
      ChunkUnloadedEvent unloadedEvent = new ChunkUnloadedEvent(chunk.getX(), chunk.getZ());
      eventService.publish(unloadedEvent);

      for (Entity entity : chunk.getEntities()) {
          UUID entityId = entity.getUniqueId();
          EntityType entityType = EntityType.valueOf(entity.getType().name());
          EntityDespawnEvent despawnEvent = new SimpleEntityDespawnEvent(entityId, entityType);
          eventService.publish(despawnEvent);
      }
   */
  }
}
