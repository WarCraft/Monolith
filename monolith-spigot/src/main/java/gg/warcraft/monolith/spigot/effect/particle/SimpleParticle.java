package gg.warcraft.monolith.spigot.effect.particle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import de.slikey.effectlib.util.ParticleEffect;
import gg.warcraft.monolith.api.effect.Particle;
import gg.warcraft.monolith.api.effect.ParticleType;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.spigot.world.location.SpigotLocationMapper;

public class SimpleParticle implements Particle {
    private static final float DEFAULT_RANGE = 64;

    private final SpigotLocationMapper locationMapper;
    private final ParticleEffect particle;

    @Inject
    public SimpleParticle(ParticleTypeMapper particleTypeMapper, SpigotLocationMapper locationMapper,
                          @Assisted ParticleType particle) {
        this.locationMapper = locationMapper;
        this.particle = particleTypeMapper.toParticleEffect(particle);
    }

    @Override
    public void display(Location location) {
        org.bukkit.Location spigotLocation = locationMapper.map(location);
        particle.display(spigotLocation, null, DEFAULT_RANGE);
    }
}
