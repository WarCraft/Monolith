package gg.warcraft.monolith.api.entity.event;

import gg.warcraft.monolith.api.combat.value.CombatValue;

import java.util.UUID;

public interface EntityAttackEvent extends EntityEvent {

    UUID getAttackerId();

    UUID getProjectileId();

    CombatValue getDamage();
}
