package gg.warcraft.monolith.app.entity.service;

import gg.warcraft.monolith.api.combat.PotionEffect;
import gg.warcraft.monolith.api.combat.PotionEffectType;
import gg.warcraft.monolith.api.combat.CombatValue;
import gg.warcraft.monolith.api.core.event.EventService;
import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.attribute.GenericAttribute;
import gg.warcraft.monolith.api.entity.EntityHealEvent;
import gg.warcraft.monolith.api.entity.EntityHealthChangedEvent;
import gg.warcraft.monolith.api.entity.EntityPreHealEvent;
import gg.warcraft.monolith.api.entity.service.EntityCommandService;
import gg.warcraft.monolith.api.entity.service.EntityProfileRepository;
import gg.warcraft.monolith.api.entity.service.EntityQueryService;
import gg.warcraft.monolith.api.entity.service.EntityRepository;
import gg.warcraft.monolith.api.entity.service.EntityServerAdapter;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.core.Duration;
import gg.warcraft.monolith.api.util.TimeUtils;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.WorldService;
import gg.warcraft.monolith.api.block.Block;
import gg.warcraft.monolith.api.block.BlockFace;
import gg.warcraft.monolith.api.block.BlockUtils;
import gg.warcraft.monolith.app.combat.SimplePotionEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// TODO this class is severely lacking tests
public abstract class AbstractEntityCommandService implements EntityCommandService {

    @Override
    public void removeEntity(UUID entityId) {
        Entity entity = entityQueryService.getEntity(entityId);
        if (entity != null) {
            entityServerAdapter.removeEntity(entityId);
            entityProfileRepository.delete(entityId);
        } else {
            entityRepository.markForRemoval(entityId);
        }
    }

    private void publishHealthChangedEvent(UUID entityId, EntityType entityType, float previousHealth) {
        Entity newEntity = entityQueryService.getEntity(entityId);
        if (newEntity.getAttributes() == null) {
            return; // FIXME not all entities on server have attributes, could this be due to migration?
        }
        float newHealth = newEntity.getHealth();
        if (newHealth != previousHealth) { // FIXME should this be in the server event mappers instead? atm it will only trigger of Monolith health changes
            float maxHealth = newEntity.getAttributes().getValue(GenericAttribute.MAX_HEALTH);
            float previousPercentHealth = previousHealth / maxHealth;
            float newPercentHealth = newHealth / maxHealth;
            EntityHealthChangedEvent entityHealthChangedEvent = new EntityHealthChangedEvent(entityId, entityType,
                    previousHealth, previousPercentHealth, newHealth, newPercentHealth);
            eventService.publish(entityHealthChangedEvent);
        }
    }

    @Override
    public void heal(UUID entityId, CombatValue amount) {
        Entity entity = entityQueryService.getEntity(entityId);
        EntityType entityType = entity.getType();
        EntityPreHealEvent entityPreHealEvent = new EntityPreHealEvent(entityId, entityType, amount, false, false);
        eventService.publish(entityPreHealEvent);
        if (!entityPreHealEvent.allowed()) {
            return;
        }

        float previousHealth = entity.getHealth();

        CombatValue heal = entityPreHealEvent.heal();
        entityServerAdapter.heal(entityId, heal.modified());

        EntityHealEvent entityHealEvent = new EntityHealEvent(entityId, entityType, heal);
        eventService.publish(entityHealEvent);

        publishHealthChangedEvent(entityId, entityType, previousHealth);
    }

}
