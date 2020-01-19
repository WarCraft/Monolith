package gg.warcraft.monolith.api.combat;

import com.google.inject.name.Named;
import gg.warcraft.monolith.api.core.Duration;

import java.util.List;
import java.util.UUID;

public interface CombatFactory {

    @Named("potionEffect")
    PotionEffect createPotionEffect(PotionEffectType type, int level, Duration duration);

    @Named("visiblePotionEffect")
    PotionEffect createVisiblePotionEffect(PotionEffectType type, int level, Duration duration);

    @Named("ambientPotionEffect")
    PotionEffect createAmbientPotionEffect(PotionEffectType type, int level, Duration duration);
}
