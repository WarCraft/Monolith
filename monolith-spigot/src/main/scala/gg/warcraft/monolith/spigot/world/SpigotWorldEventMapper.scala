package gg.warcraft.monolith.spigot.world

import gg.warcraft.monolith.api.core.event.EventService
import org.bukkit.event.Listener

class SpigotWorldEventMapper(
    implicit private val eventService: EventService
) extends Listener {

}

/*
private final Map<Event, List<Item>> alternativeDropsByEvent;

    @EventHandler(priority = EventPriority.HIGH)
    public void onChunkLoadEvent(ChunkLoadEvent event) {
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
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onChunkUnloadEvent(ChunkUnloadEvent event) { // TODO chunk events can no longer be cancelled
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
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onChunkUnloadEventMonitor(ChunkUnloadEvent event) {
        Chunk chunk = event.getChunk();
        ChunkUnloadedEvent unloadedEvent = new ChunkUnloadedEvent(chunk.getX(), chunk.getZ());
        eventService.publish(unloadedEvent);

        for (Entity entity : chunk.getEntities()) {
            UUID entityId = entity.getUniqueId();
            EntityType entityType = EntityType.valueOf(entity.getType().name());
            EntityDespawnEvent despawnEvent = new SimpleEntityDespawnEvent(entityId, entityType);
            eventService.publish(despawnEvent);
        }
    }
 */