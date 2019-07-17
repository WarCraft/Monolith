package gg.warcraft.monolith.api.entity.service;

import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.world.Location;

import java.util.List;
import java.util.UUID;

/**
 * This service is injectable.
 * <p>
 * The EntityQueryService serves as a point of entry into the entity implementation. It provides methods to query an
 * {@code Entity} by id, but also to retrieve all entities near a location.
 */
public interface EntityQueryService {

    /**
     * @param entityId The id of the entity. Can not be null.
     * @return The entity. Can be null.
     */
    Entity getEntity(UUID entityId);

    /**
     * @param location The location. Can not be null.
     * @param radius   The max distance from the location along all axes.
     * @return All entities found near the location within the given radius. Never null, but can be empty. Items are
     * never null.
     */
    List<Entity> getNearbyEntities(Location location, float radius);

    /**
     * @param location The location. Can not be null.
     * @param deltaX   The max distance from the location along the X axes.
     * @param deltaY   The max distance from the location along the Y axes.
     * @param deltaZ   The max distance from the location along the Z axes.
     * @return All entities found near the location within the given deltas. Never null, but can be empty. Items are
     * never null.
     */
    List<Entity> getNearbyEntities(Location location, float deltaX, float deltaY, float deltaZ);
}
