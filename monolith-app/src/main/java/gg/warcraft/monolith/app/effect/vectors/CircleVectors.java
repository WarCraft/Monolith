package gg.warcraft.monolith.app.effect.vectors;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.util.MathUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CircleVectors extends AbstractEffectVectors {
    private final Collection<Vector3f> vectors;

    @Inject
    public CircleVectors(MathUtils mathUtils, @Assisted float radius, @Assisted int count) {
        this.vectors = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            Vector3f vector = mathUtils.randomVector();
            vector.mul(radius).withY(0);
            vectors.add(vector);
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
