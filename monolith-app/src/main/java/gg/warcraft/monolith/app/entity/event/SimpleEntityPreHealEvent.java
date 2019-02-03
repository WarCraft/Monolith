package gg.warcraft.monolith.app.entity.event;

import gg.warcraft.monolith.api.combat.CombatSource;
import gg.warcraft.monolith.api.combat.value.CombatValue;
import gg.warcraft.monolith.api.combat.value.CombatValueModifier;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.event.AbstractEntityPreEvent;
import gg.warcraft.monolith.api.entity.event.EntityPreHealEvent;
import gg.warcraft.monolith.app.combat.value.LazyCombatValue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleEntityPreHealEvent extends AbstractEntityPreEvent implements EntityPreHealEvent {
    private CombatValue heal;

    public SimpleEntityPreHealEvent(UUID entityId, EntityType entityType, CombatValue heal, boolean cancelled) {
        super(entityId, entityType, cancelled);
        this.heal = heal;
    }

    @Override
    public CombatValue getHeal() {
        return heal;
    }

    @Override
    public void setHeal(CombatValue heal) {
        float baseValue = heal.getBaseValue();
        List<CombatValueModifier> modifiers = new ArrayList<>(heal.getModifiers());
        CombatSource source = heal.getSource();
        this.heal = new LazyCombatValue(baseValue, modifiers, source);
    }

    @Override
    public void addModifier(CombatValueModifier modifier) {
        List<CombatValueModifier> newModifiers = heal.getModifiers();
        newModifiers.add(modifier);
        heal = new LazyCombatValue(heal.getBaseValue(), newModifiers, heal.getSource());
    }
}
