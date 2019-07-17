package gg.warcraft.monolith.app.effect.vectors;

import gg.warcraft.monolith.api.effect.EffectVectors;
import gg.warcraft.monolith.api.math.Vector3f;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class AbstractEffectVectors implements EffectVectors {

    @Override
    public EffectVectors addOffset(Vector3f offset) {
        Collection<Vector3f> vectors = getVectors().stream()
                .map(vector -> vector.add(offset))
                .collect(Collectors.toList());
        return new SimpleEffectVectors(vectors);
    }

    @Override
    public EffectVectors rotateAroundAxisX(float angle) {
        Collection<Vector3f> vectors = getVectors().stream()
                .map(vec -> new org.joml.Vector3f(vec.x(), vec.y(), vec.z()))
                .map(vector -> vector.rotateX(angle))
                .map(vec -> new Vector3f(vec.x, vec.y, vec.z))
                .collect(Collectors.toList());
        return new SimpleEffectVectors(vectors);
    }

    @Override
    public EffectVectors rotateAroundAxisY(float angle) {
        Collection<Vector3f> vectors = getVectors().stream()
                .map(vec -> new org.joml.Vector3f(vec.x(), vec.y(), vec.z()))
                .map(vector -> vector.rotateY(angle))
                .map(vec -> new Vector3f(vec.x, vec.y, vec.z))
                .collect(Collectors.toList());
        return new SimpleEffectVectors(vectors);
    }

    @Override
    public EffectVectors rotateAroundAxisZ(float angle) {
        Collection<Vector3f> vectors = getVectors().stream()
                .map(vec -> new org.joml.Vector3f(vec.x(), vec.y(), vec.z()))
                .map(vector -> vector.rotateZ(angle))
                .map(vec -> new Vector3f(vec.x, vec.y, vec.z))
                .collect(Collectors.toList());
        return new SimpleEffectVectors(vectors);
    }
}
