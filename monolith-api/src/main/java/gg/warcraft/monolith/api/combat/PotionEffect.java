package gg.warcraft.monolith.api.combat;

import gg.warcraft.monolith.api.core.Duration;

/**
 * A PotionEffect is a Minecraft status effect.
 */
public interface PotionEffect {

    /**
     * @return The type of this potion effect. Never null.
     */
    PotionEffectType getType();

    /**
     * Returns the level of this potion effect.
     * <p>
     * This level value is 1-based compared to Spigot's {@code PotionEffect} amplifier being 0-based. A potion effect
     * with a level value of 0 will have the same effect as no potion effect at all.
     *
     * @return The level of this potion effect.
     */
    int getLevel();

    /**
     * @return The duration of this potion effect. Never null.
     */
    Duration getDuration();

    /**
     * @return True if this potion effect is ambient, false otherwise.
     */
    boolean isAmbient();

    /**
     * @return True if this potion effect has particles, false otherwise.
     */
    boolean hasParticles();
}
