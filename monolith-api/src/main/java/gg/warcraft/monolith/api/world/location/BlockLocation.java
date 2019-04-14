package gg.warcraft.monolith.api.world.location;

import gg.warcraft.monolith.api.world.World;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * A BlockLocation represents the location of a block. As blocks have integer
 * coordinates this location object only supports integer operations.
 */
public interface BlockLocation {

    /**
     * @return The world this location is in.
     */
    World getWorld();

    /**
     * @return The X coordinate of this location.
     */
    int getX();

    /**
     * @return The Y coordinate of this location.
     */
    int getY();

    /**
     * @return The Z coordinate of this location.
     */
    int getZ();

    /**
     * @return A new location that is the result of adding the given scalars to
     * this location.
     */
    BlockLocation add(int x, int y, int z);

    /**
     * @return A new location that is the result of subtracting the given
     * scalars from this location.
     */
    BlockLocation sub(int x, int y, int z);

    /**
     * @return A new location that is the result of adding the given vector to
     * this location.
     */
    BlockLocation add(Vector3ic vector);

    /**
     * @return A new location that is the result of subtracting the given vector
     * from this location.
     */
    BlockLocation sub(Vector3ic vector);

    /**
     * @return A new location that is the result of adding the given location to
     * this location.
     */
    BlockLocation add(BlockLocation location);

    /**
     * @return A new location that is the result of subtracting the given
     * location from this location.
     */
    BlockLocation sub(BlockLocation location);

    /**
     * @return A new location with the given world as its world.
     */
    BlockLocation withWorld(World world);

    /**
     * @return A new location with the given scalar as its X coordinate.
     */
    BlockLocation withX(int x);

    /**
     * @return A new location with the given scalar as its Y coordinate.
     */
    BlockLocation withY(int y);

    /**
     * @return A new location with the given scalar as its Z coordinate.
     */
    BlockLocation withZ(int z);

    /**
     * @return This block location as a floating point location.
     */
    Location toLocation();

    /**
     * @return This location as a 3D int vector.
     */
    Vector3i toVector();
}
