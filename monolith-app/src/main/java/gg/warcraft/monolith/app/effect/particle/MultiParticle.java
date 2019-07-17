package gg.warcraft.monolith.app.effect.particle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.effect.Particle;
import gg.warcraft.monolith.api.world.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MultiParticle implements Particle {
    private final List<Particle> particles;

    private Iterator<Particle> iterator;

    @Inject
    public MultiParticle(@Assisted Particle... particles) {
        this.particles = new ArrayList<>();
        this.particles.addAll(Arrays.asList(particles));
        this.iterator = this.particles.iterator();
    }

    @Override
    public void display(Location location) {
        if (!iterator.hasNext()) {
            iterator = particles.iterator();
        }
        if (iterator.hasNext()) {
            iterator.next().display(location);
        }
    }
}
