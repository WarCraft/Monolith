package gg.warcraft.monolith.api.entity.event;

import gg.warcraft.monolith.api.combat.value.CombatValue;

public interface EntityPreFatalDamageEvent extends EntityPreEvent {

    CombatValue getDamage();
}
