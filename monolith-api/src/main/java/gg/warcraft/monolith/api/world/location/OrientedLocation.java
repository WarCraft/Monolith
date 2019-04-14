package gg.warcraft.monolith.api.world.location;

import gg.warcraft.monolith.api.world.World;
import org.joml.Vector3f;
import org.joml.Vector3fc;

/**
 * An OrientedLocation represents the location and orientation of an orientable
 * actor, such as the head of a player.
 */
public interface OrientedLocation extends Location {

    /**
     * @return The pitch of this orientation.
     */
    float getPitch();

    /**
     * @return The yaw of this orientation.
     */
    float getYaw();

    /**
     * @return A new location that is the result of adding the given scalars to
     * this location.
     */
    @Override
    OrientedLocation add(float x, float y, float z);

    /**
     * @return A new location that is the result of subtracting the given
     * scalars from this location.
     */
    @Override
    OrientedLocation sub(float x, float y, float z);

    /**
     * @return A new location that is the result of adding the given vector to
     * this location.
     */
    @Override
    OrientedLocation add(Vector3fc vector);

    /**
     * @return A new location that is the result of subtracting the given vector
     * from this location.
     */
    @Override
    OrientedLocation sub(Vector3fc vector);

    /**
     * @return A new location that is the result of adding the given location to
     * this location.
     */
    @Override
    OrientedLocation add(Location location);

    /**
     * @return A new location that is the result of subtracting the given
     * location from this location.
     */
    @Override
    OrientedLocation sub(Location location);

    /**
     * @return A new location with the given world as its world.
     */
    @Override
    OrientedLocation withWorld(World world);

    /**
     * @return A new location with the X scalar as its X coordinate.
     */
    @Override
    OrientedLocation withX(float x);

    /**
     * @return A new location with the Y scalar as its Y coordinate.
     */
    @Override
    OrientedLocation withY(float y);

    /**
     * @return A new location with the Z scalar as its Z coordinate.
     */
    @Override
    OrientedLocation withZ(float z);

    /**
     * @return A new location with the given pitch.
     */
    OrientedLocation withPitch(float pitch);

    /**
     * @return A new location with the given yaw.
     */
    OrientedLocation withYaw(float yaw);

    /**
     * @return The direction of this location.
     */
    Vector3f toDirection();
}
