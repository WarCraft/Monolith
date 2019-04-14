package gg.warcraft.monolith.api.world.location;

import gg.warcraft.monolith.api.config.BlockLocationConfiguration;
import gg.warcraft.monolith.api.config.LocationConfiguration;
import gg.warcraft.monolith.api.config.OrientedLocationConfiguration;
import gg.warcraft.monolith.api.world.World;
import org.joml.Vector3fc;

public interface LocationFactory {

    /**
     * @return A new location in the given world at the specified coordinates.
     */
    Location createLocation(World world, float x, float y, float z);

    /**
     * @return A new location in the world and at the coordinates as specified
     * in the configuration.
     */
    default Location createLocation(LocationConfiguration configuration) {
        return createLocation(
                configuration.getWorld(),
                configuration.getX(),
                configuration.getY(),
                configuration.getZ()
        );
    }

    /**
     * @return A new block location in the given world at the specified
     * coordinates.
     */
    BlockLocation createBlockLocation(World world, int x, int y, int z);

    /**
     * @return A new block location in the world and at the coordinates as
     * specified in the configuration.
     */
    default BlockLocation createBlockLocation(
            BlockLocationConfiguration configuration) {
        return createBlockLocation(
                configuration.getWorld(),
                configuration.getX(),
                configuration.getY(),
                configuration.getZ()
        );
    }

    /**
     * @return A new oriented location in the given world at the specified
     * coordinates with the specified orientation.
     */
    OrientedLocation createOrientedLocation(World world,
                                            float x, float y, float z,
                                            float pitch, float yaw);

    /**
     * @return A new oriented location in the world and at the coordinates with
     * the orientation as specified in the configuration.
     */
    default OrientedLocation createOrientedLocation(
            OrientedLocationConfiguration configuration) {
        return createOrientedLocation(
                configuration.getWorld(),
                configuration.getX(),
                configuration.getY(),
                configuration.getZ(),
                configuration.getPitch(),
                configuration.getYaw()
        );
    }

    /**
     * @return A new oriented location in the given world at the specified
     * coordinates with the specified direction.
     */
    OrientedLocation createOrientedLocation(World world,
                                            float x, float y, float z,
                                            Vector3fc direction);
}
