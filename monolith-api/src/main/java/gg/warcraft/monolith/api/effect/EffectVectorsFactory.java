package gg.warcraft.monolith.api.effect;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;
import gg.warcraft.monolith.api.math.Vector3f;

/**
 * This factory is injectable.
 * <p>
 * EffectVectorsFactory serves as a point of entry into the effect implementation. It allows for easy creation of
 * {@code EffectVectors} objects.
 */
public interface EffectVectorsFactory {

    /**
     * Creates a new {@code EffectVectors} in the shape of a circle.
     * <p>
     * This method will return a filled circle. To create an empty ring use {@code createRingVectors} instead.
     *
     * @param radius The radius of the circle.
     * @param count  The amount of vectors used to construct the circle.
     * @return A new {@code EffectVectors} in the shape of a circle. Never null.
     */
    @Named("circle")
    EffectVectors createCircleVectors(float radius, int count);

    /**
     * Creates a new {@code EffectVectors} in the shape of a dome.
     *
     * @param radius The radius of the base of the dome.
     * @param count  The amount of vectors used to construct the dome.
     * @return A new {@code EffectVectors} in the shape of a dome. Never null.
     */
    @Named("dome")
    EffectVectors createDomeVectors(float radius, int count);

    /**
     * Creates a new {@code EffectVectors} in the shape of a line.
     *
     * @param origin The origin vector of the line.
     * @param target The target vector of the line.
     * @param count  The amount of vectors used to construct the line.
     * @return A new {@code EffectVectors} in the shape of a line. Never null.
     */
    @Named("line")
    EffectVectors createLineVectors(@Assisted("origin") Vector3f origin, @Assisted("target") Vector3f target,
                                    int count);

    /**
     * Creates a new {@code EffectVectors} consisting of one point at the origin.
     *
     * @return A new {@code EffectVectors} consisting of one point at the origin. Never null.
     */
    @Named("origin")
    EffectVectors createOriginVector();

    /**
     * Creates a new {@code EffectVectors} consisting of one point.
     *
     * @param point The point vector.
     * @return A new {@code EffectVectors} consisting of one point. Never null.
     */
    @Named("point")
    EffectVectors createPointVector(Vector3f point);

    /**
     * Creates a new {@code EffectVectors} in the shape of a ring.
     * <p>
     * This method will return an empty ring. To create a filled circle use {@code createCircleVectors} in stead.
     *
     * @param radius The radius of the ring.
     * @param count  The amount of vectors used to construct the ring.
     * @return A new {@code EffectVectors} in the shape of a ring. Never null.
     */
    @Named("ring")
    EffectVectors createRingVectors(float radius, int count);

    /**
     * Creates a new {@code EffectVectors} in the shape of a sphere.
     *
     * @param radius The radius of the ring.
     * @param count  The amount of vectors used to construct the sphere.
     * @return A new {@code EffectVectors} in the shape of a sphere. Never null.
     */
    @Named("sphere")
    EffectVectors createSphereVectors(float radius, int count);
}
