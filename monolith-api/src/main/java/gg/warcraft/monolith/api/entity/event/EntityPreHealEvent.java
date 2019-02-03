package gg.warcraft.monolith.api.entity.event;

import gg.warcraft.monolith.api.combat.value.CombatValue;
import gg.warcraft.monolith.api.combat.value.CombatValueModifier;

public interface EntityPreHealEvent extends EntityPreEvent {

    CombatValue getHeal();

    void setHeal(CombatValue heal);

    void addModifier(CombatValueModifier modifier);
}
