package gg.warcraft.monolith.app.entity.event;

import gg.warcraft.monolith.api.combat.CombatSource;
import gg.warcraft.monolith.api.combat.value.CombatValue;
import gg.warcraft.monolith.api.combat.value.CombatValueModifier;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.event.AbstractEntityPreEvent;
import gg.warcraft.monolith.api.entity.event.EntityPreAttackEvent;
import gg.warcraft.monolith.app.combat.value.LazyCombatValue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleEntityPreAttackEvent extends AbstractEntityPreEvent implements EntityPreAttackEvent {
    private final UUID attackerId;
    private final UUID projectileId;

    private CombatValue damage;

    public SimpleEntityPreAttackEvent(UUID entityId, EntityType entityType, UUID attackerId, UUID projectileId,
                                      CombatValue damage, boolean cancelled) {
        super(entityId, entityType, cancelled);
        this.attackerId = attackerId;
        this.projectileId = projectileId;
        this.damage = damage;
    }

    @Override
    public UUID getAttackerId() {
        return attackerId;
    }

    @Override
    public UUID getProjectileId() {
        return projectileId;
    }

    @Override
    public CombatValue getDamage() {
        return damage;
    }

    @Override
    public void setDamage(CombatValue damage) {
        float baseValue = damage.getBaseValue();
        List<CombatValueModifier> modifiers = new ArrayList<>(damage.getModifiers());
        CombatSource source = damage.getSource();
        this.damage = new LazyCombatValue(baseValue, modifiers, source);
    }

    @Override
    public void addModifier(CombatValueModifier modifier) {
        List<CombatValueModifier> newModifiers = damage.getModifiers();
        newModifiers.add(modifier);
        damage = new LazyCombatValue(damage.getBaseValue(), newModifiers, damage.getSource());
    }
}
