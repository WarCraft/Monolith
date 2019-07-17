package gg.warcraft.monolith.app.effect.vectors;

import gg.warcraft.monolith.api.math.Vector3f;

import java.util.Collection;
import java.util.Iterator;

public class SimpleEffectVectors extends AbstractEffectVectors {
    private final Collection<Vector3f> vectors;

    public SimpleEffectVectors(Collection<Vector3f> vectors) {
        this.vectors = vectors;
    }

    @Override
    public Collection<Vector3f> getVectors() {
        return vectors;
    }

    @Override
    public Iterator<Vector3f> iterator() {
        return vectors.iterator();
    }
}
