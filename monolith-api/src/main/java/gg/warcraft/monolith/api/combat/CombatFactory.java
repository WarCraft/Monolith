package gg.warcraft.monolith.api.combat;

import com.google.inject.name.Named;
import gg.warcraft.monolith.api.combat.value.CombatValue;
import gg.warcraft.monolith.api.combat.value.CombatValueModifier;
import gg.warcraft.monolith.api.combat.value.CombatValueModifierType;
import gg.warcraft.monolith.api.util.Duration;

import java.util.List;
import java.util.UUID;

public interface CombatFactory {

    @Named("potionEffect")
    PotionEffect createPotionEffect(PotionEffectType type, int level, Duration duration);

    @Named("visiblePotionEffect")
    PotionEffect createVisiblePotionEffect(PotionEffectType type, int level, Duration duration);

    @Named("ambientPotionEffect")
    PotionEffect createAmbientPotionEffect(PotionEffectType type, int level, Duration duration);

    @Named("source")
    CombatSource createCombatSource(String name, UUID entityId);

    @Named("value")
    CombatValue createCombatValue(float baseValue, List<CombatValueModifier> modifiers, CombatSource source);

    @Named("modifier")
    CombatValueModifier createCombatValueModifier(CombatValueModifierType type, float modifier, CombatSource source);
}
