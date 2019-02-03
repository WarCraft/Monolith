package gg.warcraft.monolith.app.entity.event;

import gg.warcraft.monolith.api.combat.value.CombatValue;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.event.AbstractEntityEvent;
import gg.warcraft.monolith.api.entity.event.EntityHealEvent;

import java.util.UUID;

public class SimpleEntityHealEvent extends AbstractEntityEvent implements EntityHealEvent {
    private final CombatValue heal;

    public SimpleEntityHealEvent(UUID entityId, EntityType entityType, CombatValue heal) {
        super(entityId, entityType);
        this.heal = heal;
    }

    @Override
    public CombatValue getHeal() {
        return heal;
    }
}
