package gg.warcraft.monolith.spigot.effect.particle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import de.slikey.effectlib.util.ParticleEffect;
import gg.warcraft.monolith.api.effect.Particle;
import gg.warcraft.monolith.api.effect.ParticleType;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper;

public class SpeedParticle implements Particle {
    private static final float ZERO_OFFSET = 0f;
    private static final float DEFAULT_RANGE = 64;

    private final SpigotLocationMapper locationMapper;
    private final ParticleEffect particle;
    private final float speed;
    private final int amount;

    @Inject
    public SpeedParticle(ParticleTypeMapper particleTypeMapper, SpigotLocationMapper locationMapper,
                         @Assisted ParticleType particle, @Assisted float speed, @Assisted int amount) {
        this.locationMapper = locationMapper;
        this.particle = particleTypeMapper.toParticleEffect(particle);
        this.speed = speed;
        this.amount = amount;
    }

    @Override
    public void display(Location location) {
        org.bukkit.Location spigotLocation = locationMapper.map(location);
        particle.display(ZERO_OFFSET, ZERO_OFFSET, ZERO_OFFSET, speed, amount, spigotLocation, DEFAULT_RANGE);
    }
}
