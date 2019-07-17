package gg.warcraft.monolith.api.entity;

import gg.warcraft.monolith.api.entity.attribute.Attributes;
import gg.warcraft.monolith.api.entity.status.Status;
import gg.warcraft.monolith.api.entity.team.Team;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.world.Location;
import org.joml.AABBf;

import java.util.UUID;

/**
 * An Entity is a living actor on the server.
 */
public interface Entity {

    /**
     * @return The id of this entity. Never null.
     */
    UUID getId();

    /**
     * @return The type of this entity. Never null.
     */
    EntityType getType();

    /**
     * @return The name of this entity. Never null or empty.
     */
    String getName();

    /**
     * @return The location of this entity. Never null.
     */
    Location getLocation();

    /**
     * @return The eye location of this entity. Never null.
     */
    Location getEyeLocation();

    /**
     * @return The velocity of this entity. Never null.
     */
    Vector3f getVelocity();

    /**
     * @return The health of this entity.
     */
    float getHealth();

    /**
     * @return The team of this entity. Can be null.
     */
    Team getTeam();

    /**
     * @param key The data. Can not be null.
     * @return The value of the data. Can be null, but never empty.
     */
    String getData(String key);

    /**
     * @param key The data. Can not be null.
     * @return The integer value of the data.
     */
    int getDataInt(String key);

    /**
     * @param key The data. Can not be null.
     * @return The float value of the data.
     */
    float getDataFoat(String key);

    /**
     * @param key The data. Can not be null.
     * @return The boolean value of the data.
     */
    boolean getDataBool(String key);

    /**
     * @return The attributes of this entity. Never null.
     */
    Attributes getAttributes();

    /**
     * @return The status of this entity. Never null.
     */
    Status getStatus();

    /**
     * @return The equipment this entity is currently wearing. Never null.
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
