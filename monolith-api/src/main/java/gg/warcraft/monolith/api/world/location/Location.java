package gg.warcraft.monolith.api.world.location;

import gg.warcraft.monolith.api.world.World;
import org.joml.Vector3f;
import org.joml.Vector3fc;

/**
 * A Location represents a location in a world.
 */
public interface Location {

    /**
     * @return The world this location belongs to.
     */
    World getWorld();

    /**
     * @return The X coordinate of this location.
     */
    float getX();

    /**
     * @return The Y coordinate of this location.
     */
    float getY();

    /**
     * @return The Z coordinate of this location.
     */
    float getZ();

    /**
     * @return A new location that is the result of adding the given scalars to
     * this location.
     */
    Location add(float x, float y, float z);

    /**
     * @return A new location that is the result of subtracting the given
     * scalars from this location.
     */
    Location sub(float x, float y, float z);

    /**
     * @return A new location that is the result of adding the given vector to
     * this location.
     */
    Location add(Vector3fc vector);

    /**
     * @return A new location that is the result of subtracting the given vector
     * from this location.
     */
    Location sub(Vector3fc vector);

    /**
     * @return A new location that is the result of adding the given location to
     * this location.
     */
    Location add(Location location);

    /**
     * @return A new location that is the result of subtracting the given
     * location from this location.
     */
    Location sub(Location location);

    /**
     * @return A new location with the given world as its world.
     */
    Location withWorld(World world);

    /**
     * @return A new location with the X scalar as its X coordinate.
     */
    Location withX(float x);

    /**
     * @return A new location with the Y scalar as its Y coordinate.
     */
    Location withY(float y);

    /**
     * @return A new location with the Z scalar as its Z coordinate.
     */
    Location withZ(float z);

    /**
     * @return A new oriented location at the position of this location with the
     * given pitch and yaw.
     */
    OrientedLocation withOrientation(float pitch, float yaw);

    /**
     * @return This location as a block location.
     */
    BlockLocation toBlockLocation();

    /**
     * @return This location as a 3D int vector.
     */
    Vector3f toVector();
}
