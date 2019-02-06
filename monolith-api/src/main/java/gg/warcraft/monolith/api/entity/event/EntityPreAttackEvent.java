package gg.warcraft.monolith.api.entity.event;

import gg.warcraft.monolith.api.combat.value.CombatValue;
import gg.warcraft.monolith.api.combat.value.CombatValueModifier;

import java.util.UUID;

public interface EntityPreAttackEvent extends EntityPreEvent {

    UUID getAttackerId();

    UUID getProjectileId();

    CombatValue getDamage();

    void setDamage(CombatValue damage);

    void addModifier(CombatValueModifier modifier);
}
