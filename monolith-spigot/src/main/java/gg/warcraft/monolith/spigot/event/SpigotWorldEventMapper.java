package gg.warcraft.monolith.spigot.event;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.core.EventService;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.EntityDespawnEvent;
import gg.warcraft.monolith.api.entity.EntityPreDespawnEvent;
import gg.warcraft.monolith.api.entity.EntityPreRespawnEvent;
import gg.warcraft.monolith.api.entity.EntityRespawnEvent;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.WorldService;
import gg.warcraft.monolith.api.world.block.*;
import gg.warcraft.monolith.api.world.item.Item;
import gg.warcraft.monolith.api.world.item.ItemService;
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper;
import gg.warcraft.monolith.spigot.world.block.SpigotBlockFaceMapper;
import gg.warcraft.monolith.spigot.world.block.SpigotBlockMapper;
import gg.warcraft.monolith.spigot.world.item.SpigotItemMapper;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.*;

public class SpigotWorldEventMapper implements Listener {
    private final EventService eventService;
    private final SpigotBlockMapper blockMapper;
    private final SpigotBlockFaceMapper blockFaceMapper;
    private final SpigotItemMapper itemMapper;
    private final WorldService worldService;
    private final ItemService itemService;
    private final SpigotLocationMapper locationMapper;

    private final Map<Event, List<Item>> alternativeDropsByEvent;

    @Inject
    public SpigotWorldEventMapper(EventService eventService, SpigotBlockMapper blockMapper,
                                  SpigotBlockFaceMapper blockFaceMapper, SpigotItemMapper itemMapper,
                                  SpigotLocationMapper locationMapper,
                                  WorldService worldService, ItemService itemService) {
        this.eventService = eventService;
        this.blockMapper = blockMapper;
        this.blockFaceMapper = blockFaceMapper;
        this.itemMapper = itemMapper;
        this.worldService = worldService;
        this.locationMapper = locationMapper;
        this.itemService = itemService;
        this.alternativeDropsByEvent = new HashMap<>();
    }



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
}
