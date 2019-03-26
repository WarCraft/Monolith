package gg.warcraft.monolith.api.entity.service;

import gg.warcraft.monolith.api.combat.PotionEffect;
import gg.warcraft.monolith.api.combat.PotionEffectType;
import gg.warcraft.monolith.api.combat.value.CombatValue;
import gg.warcraft.monolith.api.entity.EntityServerData;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.attribute.GenericAttribute;
import gg.warcraft.monolith.api.util.Duration;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.location.Location;
import org.joml.Vector3fc;

import java.util.List;
import java.util.UUID;

/**
 * This adapter is injectable, however you generally have no need for it. Use the command and query services instead.
 * <p>
 * The EntityServerAdapter abstracts entity related server implementations from the Monolith domain. It can be used to
 * query the {@code EntityServerData} of an {@code Entity}, change its velocity, location, or add and remove potion
 * effects.
 */
public interface EntityServerAdapter {

    /**
     * @param entityId The id of the entity. Can not be null.
     * @return The server data of the entity. Can be null.
     */
    EntityServerData getEntityServerData(UUID entityId);

    /**
     * @param location The location.
     * @param deltaX   The max distance from the location along the X axes.
     * @param deltaY   The max distance from the location along the Y axes.
     * @param deltaZ   The max distance from the location along the Z axes.
     * @return A list with the server data of all entities found near the location within the given deltas. Never null,
     * but can be empty. Items are never null.
     */
    List<EntityServerData> getNearbyEntitiesServerData(Location location, float deltaX, float deltaY, float deltaZ);

    /**
     * @param entityId The id of the entity. Can not be null.
     * @param velocity The new velocity of the entity. Can not be null.
     */
    void setVelocity(UUID entityId, Vector3fc velocity);

    /**
     * @param entityId The id of the entity. Can not be null.
     * @param effect   The potion effect to add to the entity. Can not be null.
     */
    void addPotionEffect(UUID entityId, PotionEffect effect);

    /**
     * @param entityId The id of the entity. Can not be null.
     * @param type     The potion effect to remove from the entity. Can not be null.
     */
    void removePotionEffect(UUID entityId, PotionEffectType type);

    /**
     * @param entityId  The id of the entity. Can not be null.
     * @param attribute The generic attribute to return the value for. Can not be null.
     * @return The value of the generic attribute for the entity on the server. This value is made up of the default
     * value, the Monolith value, and any other values added by other plugins.
     */
    float getGenericAttribute(UUID entityId, GenericAttribute attribute);

    /**
     * @param entityId  The id of the entity. Can not be null.
     * @param attribute The generic attribute to force an update for. Can not be null.
     */
    void updateGenericAttribute(UUID entityId, GenericAttribute attribute);

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
     * @param entityId The id of the entity to damage. Can not be null.
     * @param amount   The damage amount.
     */
    void damage(UUID entityId, float amount);

    /**
     * Damages the entity by the given amount. This method will preserve the
     * combat source of the damage.
     *
     * @param entityId The id of the entity to damage. Can not be null.
     * @param amount   The damage amount.
     */
    void damage(UUID entityId, CombatValue amount);

    /**
     * @param entityId The id of the entity to kill. Can not be null.
     */
    void kill(UUID entityId);

    /**
     * @param entityId The id of the entity to heal. Can not be null.
     * @param amount   The heal amount.
     */
    void heal(UUID entityId, float amount);

    /**
     * Sets an entity on fire for a duration.
     *
     * @param entityId The id of the entity.
     * @param duration The duration.
     */
    void burn(UUID entityId, Duration duration);

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
    void teleport(UUID entityId, Location location, Direction orientation);
}
