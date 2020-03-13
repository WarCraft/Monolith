package gg.warcraft.monolith.api.entity.service;

import gg.warcraft.monolith.api.combat.CombatValue;
import gg.warcraft.monolith.api.combat.PotionEffect;
import gg.warcraft.monolith.api.combat.PotionEffectType;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.team.Team;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.core.Duration;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.block.BlockRotation;

import java.util.UUID;

/**
 * This service is injectable.
 * <p>
 * The EntityCommandService serves as a point of entry into the entity module implementation. It provides a plethora of
 * methods to interact with the physics of an {@code Entity}.
 */
public interface EntityCommandService {

    /**
     * @param entityId The id of the entity. Can not be null.
     * @param velocity The new velocity of the entity. Can not be null.
     */
    void setVelocity(UUID entityId, Vector3f velocity);

    /**
     * @param entityId The id of the entity. Can not be null.
     * @param team     The new team of the entity. Can be null.
     */
    void setTeam(UUID entityId, Team team);

    /**
     * @param entityId The id of the entity. Can not be null.
     * @param data     The data to set. Can not be null or empty.
     * @param value    The new value of the data. Can be null or empty, but either of these values will result in the
     *                 data being removed from the entity.
     */
    void setData(UUID entityId, String data, String value);

    /**
     * @param entityId The id of the entity. Can not be null.
     * @param effect   The potion effect to add to the entity. Can not be null.
     */
    void addPotionEffect(UUID entityId, PotionEffect effect);

    /**
     * @param entityId The id of the entity. Can not be null.
     * @param type     The potion effect type to remove from the entity. Can not be null.
     */
    void removePotionEffect(UUID entityId, PotionEffectType type);

    /**
     * @param type          The type of entity to spawn. Can not be null.
     * @param spawnLocation The location to spawn the entity at. Can not be null.
     * @return The id of the spawned entity.
     */
    UUID spawnEntity(EntityType type, Location spawnLocation);

    /**
     * @param entityId The id of the entity to remove. Can not be null.
     */
    void removeEntity(UUID entityId);

    /**
     * @param entityId The id of the entity. Can not be null.
     * @param location The location to teleport the entity to. Can not be null.
     */
    void teleport(UUID entityId, Location location);

    /**
     * @param entityId    The id of the entity. Can not be null.
     * @param location    The location to teleport the entity to. Can not be null.
     * @param orientation The new orientation of the entity. Can not be null.
     */
    void teleport(UUID entityId, Location location, BlockRotation orientation);

    /**
     * @param entityId The id of the entity. Can not be null.
     * @param amount   The amount of damage the entity should take.
     */
    void damage(UUID entityId, CombatValue amount);

    /**
     * @param entityId The id of the entity to kill. Can not be null.
     */
    void kill(UUID entityId);

    /**
     * @param entityId The id of the entity. Can not be null.
     * @param amount   The amount of healing the entity should take.
     */
    void heal(UUID entityId, CombatValue amount);

    /**
     * Sets an entity on fire for a duration.
     *
     * @param entityId The id of the entity.
     * @param duration The duration.
     */
    void burn(UUID entityId, Duration duration);

    /**
     * Prevents an entity from jumping for a duration.
     *
     * @param entityId The id of the entity.
     * @param duration The duration.
     */
    void heavy(UUID entityId, Duration duration);

    /**
     * Prevents an entity from moving for a duration.
     *
     * @param entityId The id of the entity.
     * @param duration The duration.
     */
    void freeze(UUID entityId, Duration duration);

    /**
     * Knocks an entity back a distance in a direction.
     *
     * @param entityId  The id of the entity.
     * @param direction The direction.
     * @param distance  The distance in blocks.
     */
    void knockback(UUID entityId, Vector3f direction, float distance);

    /**
     * Knocks an entity back a distance from a point of impact.
     *
     * @param entityId The id of the entity.
     * @param source   The source of the knockback.
     * @param distance The distance in blocks.
     */
    void knockback(UUID entityId, Location source, float distance);

    /**
     * Knocks an entity up a distance.
     *
     * @param entityId The id of the entity.
     * @param distance The distance in blocks.
     */
    void knockup(UUID entityId, float distance);

    /**
     * Causes an entity to leap a distance in a direction.
     *
     * @param entityId  The id of the entity.
     * @param direction The direction.
     * @param distance  The distance in blocks.
     */
    void leap(UUID entityId, Vector3f direction, float distance);

    /**
     * Sucks an entity towards a point.
     *
     * @param entityId The id of the entity.
     * @param source   The point.
     * @param strength The strength.
     */
    void vacuum(UUID entityId, Location source, float strength);
}
