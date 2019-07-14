package gg.warcraft.monolith.api.entity;

import gg.warcraft.monolith.api.world.location.Location;
import gg.warcraft.monolith.api.world.location.Orientation;
import org.joml.AABBf;
import org.joml.Vector3f;

import java.util.UUID;

public interface EntityServerData {

    /**
     * @return The id of the entity. Never null.
     */
    UUID getEntityId();

    /**
     * @return The type of the entity. Never null.
     */
    EntityType getType();

    /**
     * @return The name of this entity. Never null or empty.
     */
    String getName();

    /**
     * @return The location of the entity. Never null.
     */
    Location getLocation();

    Orientation getOrientation();

    /**
     * @return The eye location of the entity. Never null.
     */
    Location getEyeLocation();

    Orientation getEyeOrientation();

    /**
     * @return The velocity of the entity. Never null.
     */
    Vector3f getVelocity();

    /**
     * @return The health of this entity.
     */
    float getHealth();

    /**
     * @return The equipment the entity is currently wearing. Never null.
     */
    Equipment getEquipment();

    /**
     * @return The axis aligned bounding box of this entity. Never null.
     */
    AABBf getBoundingBox();

    /**
     * @return True if this entity is standing on the ground, false otherwise.
     */
    boolean isGrounded();

    /**
     * @param permission The permission. Can not be null or empty.
     * @return True if the entity has the permission favorably set, false otherwise.
     */
    boolean hasPermission(String permission);
}
