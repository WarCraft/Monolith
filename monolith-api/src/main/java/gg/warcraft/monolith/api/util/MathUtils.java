package gg.warcraft.monolith.api.util;

import gg.warcraft.monolith.api.math.Vector3f;

/**
 * This utility is injectable.
 * <p>
 * MathUtils provides utility methods for mathematical operations.
 */
public interface MathUtils {

    /**
     * @return A random float vector in 3D space of length unit. Never null.
     */
    Vector3f randomVector();

    /**
     * @return A random float vector in 2D space on the XZ plane of length unit. Never null.
     */
    Vector3f randomCircleVector();

    /**
     * @return A random angle in radians.
     */
    float randomAngle();
}
