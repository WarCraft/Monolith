package gg.warcraft.monolith.app.combat;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.combat.PotionEffectType;
import gg.warcraft.monolith.api.util.Duration;

public class AmbientPotionEffect extends SimplePotionEffect {

    @Inject
    public AmbientPotionEffect(@Assisted PotionEffectType type,
                               @Assisted int level,
                               @Assisted Duration duration) {
        super(type, level, duration, true, true);
    }
}
