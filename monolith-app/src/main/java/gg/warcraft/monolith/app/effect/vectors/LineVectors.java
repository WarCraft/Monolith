package gg.warcraft.monolith.app.effect.vectors;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.math.Vector3f;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LineVectors extends AbstractEffectVectors {
    private final Collection<Vector3f> vectors;

    @Inject
    public LineVectors(@Assisted("origin") Vector3f origin, @Assisted("target") Vector3f target,
                       @Assisted int count) {
        this.vectors = new ArrayList<>();
        Vector3f delta = target.subtract(origin).multiply(1f / count);
        for (int i = 0; i <= count; i += 1) {
            Vector3f multipliedDelta = delta.multiply(i);
            vectors.add(origin.add(multipliedDelta));
        }
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
