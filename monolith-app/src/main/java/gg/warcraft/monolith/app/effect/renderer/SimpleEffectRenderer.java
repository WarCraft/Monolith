package gg.warcraft.monolith.app.effect.renderer;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.effect.EffectRenderer;
import gg.warcraft.monolith.api.effect.EffectVectors;
import gg.warcraft.monolith.api.effect.Particle;
import gg.warcraft.monolith.api.world.Location;

public class SimpleEffectRenderer implements EffectRenderer {
    private final Particle particle;
    private final EffectVectors vectors;

    @Inject
    public SimpleEffectRenderer(@Assisted Particle particle, @Assisted EffectVectors vectors) {
        this.particle = particle;
        this.vectors = vectors;
    }

    @Override
    public void renderAt(Location location) {
        vectors.iterator().forEachRemaining(vector -> {
            Location displayLocation = location.add(vector);
            particle.display(displayLocation);
        });
    }
}
