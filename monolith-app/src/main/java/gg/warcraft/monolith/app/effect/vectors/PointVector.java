package gg.warcraft.monolith.app.effect.vectors;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.math.Vector3f;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PointVector extends AbstractEffectVectors {
    private final Collection<Vector3f> vectors;

    @Inject
    public PointVector(@Assisted Vector3f point) {
        this.vectors = new ArrayList<>();
        this.vectors.add(point);
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
