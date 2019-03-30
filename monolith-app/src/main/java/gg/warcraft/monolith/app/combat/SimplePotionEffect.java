package gg.warcraft.monolith.app.combat;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.combat.PotionEffect;
import gg.warcraft.monolith.api.combat.PotionEffectType;
import gg.warcraft.monolith.api.util.Duration;

public class SimplePotionEffect implements PotionEffect {
    private final PotionEffectType type;
    private final int level;
    private final Duration duration;
    private final boolean isAmbient;
    private final boolean hasParticles;

    @Inject
    public SimplePotionEffect(@Assisted PotionEffectType type,
                              @Assisted int level,
                              @Assisted Duration duration) {
        this(type, level, duration, false, false);
    }

    public SimplePotionEffect(PotionEffectType type,
                              int level,
                              Duration duration,
                              boolean isAmbient,
                              boolean hasParticles) {
        this.type = type;
        this.level = level;
        this.duration = duration;
        this.isAmbient = isAmbient;
        this.hasParticles = hasParticles;
    }


    @Override
    public PotionEffectType getType() {
        return type;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public boolean isAmbient() {
        return isAmbient;
    }

    @Override
    public boolean hasParticles() {
        return hasParticles;
    }
}
