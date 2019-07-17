package gg.warcraft.monolith.api.entity;

import gg.warcraft.monolith.api.world.Location;

import java.util.UUID;
import java.util.function.Predicate;

public interface EntityUtils {

    /**
     * @param origin The starting location. Can not be null.
     * @param target The target location. Can not be null.
     * @param ignore The predicate that tests which entities to ignore. Can not be null.
     * @return The closest entity the ray starting at origin towards target intersected that does not match the
     * predicate. Can be null.
     */
    EntityIntersection intersectEntity(Location origin, Location target, Predicate<Entity> ignore);

    /**
     * @param entityId         The id of the entity. Can not be null.
     * @param range            The max range to scan ahead for from the entities eye location.
     * @param ignoreFriendlies Whether or not to ignore entities on the same team as the entity.
     * @return The representation of what the entity is currently targeting given the range. Never null.
     */
    EntityTarget calculateTarget(UUID entityId, float range, boolean ignoreFriendlies);
}
