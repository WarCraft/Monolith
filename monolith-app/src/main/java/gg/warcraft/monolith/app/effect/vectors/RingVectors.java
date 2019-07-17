package gg.warcraft.monolith.app.effect.vectors;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.math.Vector3f;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class RingVectors extends AbstractEffectVectors {
    private static final float TWO_PI = 2 * (float) Math.PI;

    private final Collection<Vector3f> vectors;

    @Inject
    public RingVectors(@Assisted float radius, @Assisted int count) {
        this.vectors = new ArrayList<>(count);
        float angle = 0;
        float delta = TWO_PI / count;
        for (int i = 0; i < count; ++i) {
            float x = (float) Math.cos(angle) * radius;
            float z = (float) Math.sin(angle) * radius;
            vectors.add(new Vector3f(x, 0, z));
            angle += delta;
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
