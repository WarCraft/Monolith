package gg.warcraft.monolith.app.entity.service;

import gg.warcraft.monolith.api.combat.PotionEffect;
import gg.warcraft.monolith.api.combat.PotionEffectType;
import gg.warcraft.monolith.api.combat.value.CombatValue;
import gg.warcraft.monolith.api.core.EventService;
import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.attribute.GenericAttribute;
import gg.warcraft.monolith.api.entity.event.EntityHealEvent;
import gg.warcraft.monolith.api.entity.event.EntityHealthChangedEvent;
import gg.warcraft.monolith.api.entity.event.EntityPreHealEvent;
import gg.warcraft.monolith.api.entity.service.EntityCommandService;
import gg.warcraft.monolith.api.entity.service.EntityProfileRepository;
import gg.warcraft.monolith.api.entity.service.EntityQueryService;
import gg.warcraft.monolith.api.entity.service.EntityRepository;
import gg.warcraft.monolith.api.entity.service.EntityServerAdapter;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.util.Duration;
import gg.warcraft.monolith.api.util.TimeUtils;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockFace;
import gg.warcraft.monolith.api.world.block.BlockTypeUtils;
import gg.warcraft.monolith.api.world.block.BlockUtils;
import gg.warcraft.monolith.api.world.service.WorldQueryService;
import gg.warcraft.monolith.app.combat.SimplePotionEffect;
import gg.warcraft.monolith.app.entity.event.SimpleEntityHealEvent;
import gg.warcraft.monolith.app.entity.event.SimpleEntityHealthChangedEvent;
import gg.warcraft.monolith.app.entity.event.SimpleEntityPreHealEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// TODO this class is severely lacking tests
public abstract class AbstractEntityCommandService implements EntityCommandService {
    private final EntityQueryService entityQueryService;
    private final EntityServerAdapter entityServerAdapter;
    private final EntityRepository entityRepository;
    private final EntityProfileRepository entityProfileRepository;
    private final WorldQueryService worldQueryService;
    private final EventService eventService;
    private final BlockUtils blockUtils;
    private final BlockTypeUtils blockTypeUtils;
    private final TimeUtils timeUtils;

    final Map<Float, Float> knockbackStrength;
    final Map<Float, Float> knockupStrength;
    final Map<Float, Float> leapStrength;

    public AbstractEntityCommandService(EntityQueryService entityQueryService, EntityServerAdapter entityServerAdapter,
                                        EntityRepository entityRepository, EntityProfileRepository entityProfileRepository,
                                        WorldQueryService worldQueryService, EventService eventService,
                                        BlockUtils blockUtils, BlockTypeUtils blockTypeUtils, TimeUtils timeUtils) {
        this.entityQueryService = entityQueryService;
        this.entityServerAdapter = entityServerAdapter;
        this.entityRepository = entityRepository;
        this.entityProfileRepository = entityProfileRepository;
        this.worldQueryService = worldQueryService;
        this.eventService = eventService;
        this.blockUtils = blockUtils;
        this.blockTypeUtils = blockTypeUtils;
        this.timeUtils = timeUtils;
        this.knockbackStrength = new HashMap<>();
        this.knockupStrength = new HashMap<>();
        this.leapStrength = new HashMap<>();
    }

    @Override
    public void setVelocity(UUID entityId, Vector3f velocity) {
        entityServerAdapter.setVelocity(entityId, velocity);
    }

    @Override
    public void addPotionEffect(UUID entityId, PotionEffect effect) {
        entityServerAdapter.addPotionEffect(entityId, effect);
    }

    @Override
    public void removePotionEffect(UUID entityId, PotionEffectType type) {
        entityServerAdapter.removePotionEffect(entityId, type);
    }

    @Override
    public UUID spawnEntity(EntityType entityType, Location spawnLocation) {
        return entityServerAdapter.spawnEntity(entityType, spawnLocation);
    }

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

    @Override
    public void teleport(UUID entityId, Location location) {
        entityServerAdapter.teleport(entityId, location);
    }

    @Override
    public void teleport(UUID entityId, Location location, Direction orientation) {
        entityServerAdapter.teleport(entityId, location, orientation);
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
            EntityHealthChangedEvent entityHealthChangedEvent = new SimpleEntityHealthChangedEvent(entityId, entityType,
                    previousHealth, previousPercentHealth, newHealth, newPercentHealth);
            eventService.publish(entityHealthChangedEvent);
        }
    }

    @Override
    public void damage(UUID entityId, CombatValue amount) {
        entityServerAdapter.damage(entityId, amount);
    }

    @Override
    public void kill(UUID entityId) {
        entityServerAdapter.kill(entityId);
    }

    @Override
    public void heal(UUID entityId, CombatValue amount) {
        Entity entity = entityQueryService.getEntity(entityId);
        EntityType entityType = entity.getType();
        EntityPreHealEvent entityPreHealEvent = new SimpleEntityPreHealEvent(entityId, entityType, amount, false);
        eventService.publish(entityPreHealEvent);
        if (!entityPreHealEvent.isAllowed()) {
            return;
        }

        float previousHealth = entity.getHealth();

        CombatValue heal = entityPreHealEvent.getHeal();
        entityServerAdapter.heal(entityId, heal.getModifiedValue());

        EntityHealEvent entityHealEvent = new SimpleEntityHealEvent(entityId, entityType, heal);
        eventService.publish(entityHealEvent);

        publishHealthChangedEvent(entityId, entityType, previousHealth);
    }

    @Override
    public void burn(UUID entityId, Duration duration) {
        entityServerAdapter.burn(entityId, duration);
    }

    @Override
    public void heavy(UUID entityId, Duration duration) {
        Entity entity = entityQueryService.getEntity(entityId);
        if (entity == null) {
            throw new IllegalArgumentException("Failed to apply heavy to null entity with id " + entityId);
        }

        Block targetBlock = findBlockUnderFeet(entity);
        int safeY = targetBlock.getLocation().y() + 1;
        Location safeLocation = entity.getLocation().withY(safeY);
        this.setVelocity(entityId, new Vector3f());
        this.teleport(entityId, safeLocation);

        this.removePotionEffect(entityId, PotionEffectType.JUMP);
        PotionEffect effect = new SimplePotionEffect(PotionEffectType.JUMP, 128, duration);
        this.addPotionEffect(entityId, effect);
    }

    @Override
    public void freeze(UUID entityId, Duration duration) {
        this.removePotionEffect(entityId, PotionEffectType.SLOW);
        PotionEffect effect = new SimplePotionEffect(PotionEffectType.SLOW, 7, duration);
        this.addPotionEffect(entityId, effect);

        this.heavy(entityId, duration);
    }

    private Block findBlockUnderFeet(Entity entity) {
        Block current = worldQueryService.getBlockAt(entity.getLocation());
        while (blockTypeUtils.getNonSolids().contains(current.getType())
                && current.getLocation().y() >= 0) {
            current = blockUtils.getRelative(current, BlockFace.DOWN);
        }
        return current;
    }

    /**
     * Curve fitting on https://mycurvefit.com/ with the assumption that we
     * always want the Y component of the knock back vector to be 10% of the
     * length of the direction of said vector and manually checking what
     * strength modifier would be required to be knocked back x amount of
     * blocks giving the following data set:
     * distance    modifier
     * 0.5         0.230
     * 1           0.458
     * 2           0.764
     * 3           1.144
     * 4           1.325
     * 5           1.576
     * 10          2.494
     * 20          4.465
     * Resulted in the following formula where x is the knock back distance
     * in blocks and y is the strength modifier required to achieve this
     * distance. The formula is valid for up to 20 blocks after which it
     * will most likely start to behave weirdly (but that is more due to
     * the fact that the 10% Y component is no longer realistic for such
     * large knock backs).
     * y = 593343 + (0.009109503 - 593343)/(1 + (x/108856200)^0.7613247)
     */
    float calculateKnockbackStrength(float distance) {
        return 593343f + (0.009109503f - 593343f) / (1f + (float) Math.pow(distance / 108856200f, 0.7613247f));
    }

    @Override
    public void knockback(UUID entityId, Vector3f direction, float distance) {
        float strength = knockbackStrength.computeIfAbsent(distance, this::calculateKnockbackStrength);
        Vector3f knockback = direction
                .withY(0)
                .normalize()
                .withY(0.1f)
                .normalize();
        Vector3f velocity = knockback.multiply(strength);
        this.setVelocity(entityId, velocity);
    }

    @Override
    public void knockback(UUID entityId, Location source, float distance) {
        Entity entity = entityQueryService.getEntity(entityId);
        if (entity == null) {
            throw new IllegalArgumentException("Failed to apply knockback to null entity with id " + entityId);
        }

        Vector3f direction = source.subtract(entity.getLocation()).translation();
        this.knockback(entityId, direction, distance);
    }

    /**
     * Curve fitting on https://mycurvefit.com/ with manually checking what
     * strength modifier would be required to be knocked up x amount of
     * blocks giving the following data set:
     * distance    modifier
     * 0.5         0.250
     * 1           0.370
     * 2           0.545
     * 3           0.685
     * 4           0.806
     * 5           0.913
     * 10          1.344
     * 20          1.999
     * Resulted in the following formula where x is the knock up distance
     * in blocks and y is the strength modifier required to achieve this
     * distance. The formula is valid for up to 20 blocks after which it
     * will most likely start to behave weirdly (but that is more due to
     * the fact that the 10% Y component is no longer realistic for such
     * large knock backs).
     * y = 23865.35 + (0.007556944 - 23865.35)/(1 + (x/296502700)^0.5688117)
     */
    float calculateKnockupStrength(float distance) {
        return 23865.35f + (0.007556944f - 23865.35f) / (1f + (float) Math.pow(distance / 296502700f, 0.5688117f));
    }

    @Override
    public void knockup(UUID entityId, float distance) {
        Entity entity = entityQueryService.getEntity(entityId);
        if (entity == null) {
            throw new IllegalArgumentException("Failed to apply knockup to null entity with id " + entityId);
        }

        float strength = knockupStrength.computeIfAbsent(distance, this::calculateKnockupStrength);
        Vector3f knockup = new Vector3f(0, strength, 0);
        Vector3f newVelocity = entity.getVelocity().add(knockup);
        this.setVelocity(entityId, newVelocity);
    }

    /**
     * Curve fitting on https://mycurvefit.com/ by manually checking what
     * strength modifier would be required to leap forward x amount of
     * blocks giving the following data set:
     * distance    modifier
     * 0.5         0.207
     * 1           0.357
     * 2           0.580
     * 3           0.753
     * 4           0.949
     * 5           1.085
     * 10          1.834
     * Resulted in the following formula where x is the leap distance in
     * blocks and y is the strength modifier required to achieve this
     * distance. The formula is valid for up to 10 blocks after which it
     * will start to damage the user.
     * y = 158503.1 + (0.02094631 - 158503.1)/(1 + (x/45625190)^0.7422179)
     */
    float calculateLeapStrength(float distance) {
        return 158503.1f + (0.02094631f - 158503.1f) / (1f + (float) Math.pow(distance / 45625190f, 0.7422179f));
    }

    @Override
    public void leap(UUID entityId, Vector3f direction, float distance) {
        Entity entity = entityQueryService.getEntity(entityId);
        if (entity == null) {
            throw new IllegalArgumentException("Failed to apply leap to null entity with id " + entityId);
        }

        float strength = leapStrength.computeIfAbsent(distance, this::calculateLeapStrength);
        Vector3f leap = direction
                .withY(0)
                .normalize()
                .withY(distance <= 10f ? 0.4f : 0.25f)
                .normalize();
        Vector3f velocity = leap.multiply(strength);
        Vector3f newVelocity = entity.getVelocity().add(velocity);
        this.setVelocity(entityId, newVelocity);
    }

    @Override
    public void vacuum(UUID entityId, Location source, float strength) {
        Entity entity = entityQueryService.getEntity(entityId);
        if (entity == null) {
            throw new IllegalArgumentException("Failed to apply vacuum to null entity with id " + entityId);
        }

        this.heavy(entityId, timeUtils.oneSecond());
        Vector3f direction = source.subtract(entity.getLocation()).translation();
        direction.normalize().multiply(0.05f * strength);
        Vector3f newVelocity = entity.getVelocity()
                .withX(direction.x())
                .withZ(direction.z());
        this.setVelocity(entityId, newVelocity);
    }
}
