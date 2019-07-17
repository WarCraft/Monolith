package gg.warcraft.monolith.api.effect;

import gg.warcraft.monolith.api.math.Vector3f;

import java.util.Collection;

/**
 * EffectVectors are a collection of vectors in 3D space representing the offsets from a given location particles should
 * be rendered at to achieve the desired effect.
 */
public interface EffectVectors extends Iterable<Vector3f> {

    /**
     * Returns the vectors of this collection.
     *
     * @return The vectors of this collection. Never null, but can be empty. Items are never null.
     */
    Collection<Vector3f> getVectors();

    /**
     * Creates a new {@code EffectVectors} offset by the given offset.
     *
     * @param offset The offset.
     * @return A new {@code EffectVectors} offset by the given offset. Never null.
     */
    EffectVectors addOffset(Vector3f offset);

    /**
     * Creates a new {@code EffectVectors} rotated by the given angle around the X axis.
     *
     * @param angle The angle in radians.
     * @return A new {@code EffectVectors} rotated by the given angle around the X axis. Never null.
     */
    EffectVectors rotateAroundAxisX(float angle);

    /**
     * Creates a new {@code EffectVectors} rotated by the given angle around the Y axis.
     *
     * @param angle The angle in radians.
     * @return A new {@code EffectVectors} rotated by the given angle around the Y axis. Never null.
     */
    EffectVectors rotateAroundAxisY(float angle);

    /**
     * Creates a new {@code EffectVectors} rotated by the given angle around the Z axis.
     *
     * @param angle The angle in radians.
     * @return A new {@code EffectVectors} rotated by the given angle around the Z axis. Never null.
     */
    EffectVectors rotateAroundAxisZ(float angle);
}
