package gg.warcraft.monolith.spigot.entity.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.combat.PotionEffect;
import gg.warcraft.monolith.api.combat.PotionEffectType;
import gg.warcraft.monolith.api.combat.value.CombatValue;
import gg.warcraft.monolith.api.entity.EntityServerData;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.attribute.AttributeModifier;
import gg.warcraft.monolith.api.entity.attribute.Attributes;
import gg.warcraft.monolith.api.entity.attribute.GenericAttribute;
import gg.warcraft.monolith.api.entity.attribute.service.AttributeQueryService;
import gg.warcraft.monolith.api.entity.service.EntityServerAdapter;
import gg.warcraft.monolith.api.util.Duration;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.DirectionUtils;
import gg.warcraft.monolith.api.world.location.Location;
import gg.warcraft.monolith.spigot.combat.SpigotPotionEffectTypeMapper;
import gg.warcraft.monolith.spigot.entity.GenericAttributeMapper;
import gg.warcraft.monolith.spigot.entity.SpigotEntityDataFactory;
import gg.warcraft.monolith.spigot.entity.SpigotEntityTypeMapper;
import gg.warcraft.monolith.spigot.world.location.SpigotLocationMapper;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.joml.Vector3fc;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SpigotEntityAdapter implements EntityServerAdapter {
    private final Server server;
    private final Plugin plugin;
    private final AttributeQueryService attributeQueryService;
    private final DirectionUtils directionUtils;
    private final SpigotEntityTypeMapper entityTypeMapper;
    private final SpigotLocationMapper locationMapper;
    private final SpigotEntityDataFactory entityDataFactory;
    private final SpigotPotionEffectTypeMapper potionEffectTypeMapper;
    private final GenericAttributeMapper genericAttributeMapper;

    @Inject
    public SpigotEntityAdapter(Server server, Plugin plugin,
                               AttributeQueryService attributeQueryService,
                               DirectionUtils directionUtils,
                               SpigotEntityTypeMapper entityTypeMapper,
                               SpigotLocationMapper locationMapper,
                               SpigotEntityDataFactory entityDataFactory,
                               SpigotPotionEffectTypeMapper potionEffectTypeMapper,
                               GenericAttributeMapper genericAttributeMapper) {
        this.server = server;
        this.plugin = plugin;
        this.attributeQueryService = attributeQueryService;
        this.directionUtils = directionUtils;
        this.entityTypeMapper = entityTypeMapper;
        this.locationMapper = locationMapper;
        this.entityDataFactory = entityDataFactory;
        this.potionEffectTypeMapper = potionEffectTypeMapper;
        this.genericAttributeMapper = genericAttributeMapper;
    }

    EntityServerData createEntityServerData(LivingEntity entity) {
        return entityDataFactory.create(entity);
    }

    @Override
    public EntityServerData getEntityServerData(UUID entityId) {
        Entity entity = server.getEntity(entityId);
        if (entity instanceof LivingEntity) {
            return createEntityServerData((LivingEntity) entity);
        }
        return null;
    }

    @Override
    public List<EntityServerData> getNearbyEntitiesServerData(Location location, float deltaX, float deltaY, float deltaZ) {
        org.bukkit.Location spigotLocation = locationMapper.map(location);
        return spigotLocation.getWorld().getNearbyEntities(spigotLocation, deltaX, deltaY, deltaZ).stream()
                .filter(entity -> entity instanceof LivingEntity)
                .filter(entity -> entity.getType() != org.bukkit.entity.EntityType.ARMOR_STAND)
                .filter(entity -> entity.getType() != org.bukkit.entity.EntityType.PLAYER ||
                        ((Player) entity).getGameMode() != GameMode.CREATIVE)
                .map(entity -> (LivingEntity) entity)
                .map(this::createEntityServerData)
                .collect(Collectors.toList());
    }

    @Override
    public void setVelocity(UUID entityId, Vector3fc velocity) {
        Entity entity = server.getEntity(entityId);
        if (entity != null) {
            Vector newVelocity = new Vector(velocity.x(), velocity.y(), velocity.z());
            entity.setVelocity(newVelocity);
        }
    }

    @Override
    public void addPotionEffect(UUID entityId, PotionEffect effect) {
        Entity entity = server.getEntity(entityId);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            org.bukkit.potion.PotionEffectType spigotPotionEffectType = potionEffectTypeMapper.map(effect.getType());
            org.bukkit.potion.PotionEffect spigotPotionEffect = new org.bukkit.potion.PotionEffect(
                    spigotPotionEffectType, effect.getDuration().inTicks(), effect.getLevel() - 1);
            livingEntity.addPotionEffect(spigotPotionEffect);
        }
    }

    @Override
    public void removePotionEffect(UUID entityId, PotionEffectType type) {
        Entity entity = server.getEntity(entityId);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            org.bukkit.potion.PotionEffectType spigotPotionEffectType = potionEffectTypeMapper.map(type);
            livingEntity.removePotionEffect(spigotPotionEffectType);
        }
    }

    @Override
    public float getGenericAttribute(UUID entityId, GenericAttribute attribute) {
        Entity entity = server.getEntity(entityId);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            Attribute spigotAttribute = genericAttributeMapper.map(attribute);
            AttributeInstance spigotAttributeInstance = livingEntity.getAttribute(spigotAttribute);
            return (float) spigotAttributeInstance.getValue();
        }
        return 0;
    }

    @Override
    public void updateGenericAttribute(UUID entityId, GenericAttribute attribute) {
        Entity entity = server.getEntity(entityId);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            Attributes attributes = attributeQueryService.getAttributes(entityId);
            float monolithValue = attributes.getModifiers(attribute).stream()
                    .map(AttributeModifier::getValue)
                    .reduce(Float::sum)
                    .orElse(0f);
            Attribute spigotAttribute = genericAttributeMapper.map(attribute);
            AttributeInstance spigotAttributeInstance = livingEntity.getAttribute(spigotAttribute);
            double defaultValue = spigotAttributeInstance.getDefaultValue();
            spigotAttributeInstance.setBaseValue(defaultValue + monolithValue);
        }
    }

    @Override
    public UUID spawnEntity(EntityType type, Location spawnLocation) {
        org.bukkit.entity.EntityType spigotEntityType = entityTypeMapper.map(type);
        org.bukkit.Location spigotSpawnLocation = locationMapper.map(spawnLocation);
        if (!spigotSpawnLocation.getChunk().isLoaded()) {
            spigotSpawnLocation.getChunk().load();
        }
        Entity entity = spigotSpawnLocation.getWorld().spawnEntity(spigotSpawnLocation, spigotEntityType);
        return entity.getUniqueId();
    }

    @Override
    public void removeEntity(UUID entityId) {
        Entity entity = server.getEntity(entityId);
        if (entity != null) {
            System.out.println("DEBUG marking " + entityId + " for removal");
            entity.remove();
        }
    }

    @Override
    public void damage(UUID entityId, float amount) {
        Entity entity = server.getEntity(entityId);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.damage(amount);
        }
    }

    @Override
    public void damage(UUID entityId, CombatValue amount) {
        Entity entity = server.getEntity(entityId);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            MetadataValue amountMetadata = new FixedMetadataValue(plugin, amount);
            livingEntity.setMetadata(CombatValue.class.getCanonicalName(), amountMetadata);
            livingEntity.damage(amount.getModifiedValue());
        }
    }

    @Override
    public void kill(UUID entityId) {
        Entity entity = server.getEntity(entityId);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.setHealth(0);
        }
    }

    @Override
    public void heal(UUID entityId, float amount) {
        Entity entity = server.getEntity(entityId);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            double maxHealth = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            double newHealth = Math.min(livingEntity.getHealth() + amount, maxHealth);
            livingEntity.setHealth(newHealth);
        }
    }

    @Override
    public void burn(UUID entityId, Duration duration) {
        Entity entity = server.getEntity(entityId);
        if (entity != null) {
            System.out.println("DEBUG burning entity for " + duration.inTicks() + " ticks");
            int currentTicks = entity.getFireTicks();
            int newFireTicks = currentTicks + duration.inTicks();
            entity.setFireTicks(newFireTicks);
        }
    }

    @Override
    public void teleport(UUID entityId, Location location) {
        Entity entity = server.getEntity(entityId);
        if (entity != null) {
            org.bukkit.Location newLocation = locationMapper.map(location);
            newLocation.setDirection(entity.getLocation().getDirection());
            entity.teleport(newLocation);
        }
    }

    @Override
    public void teleport(UUID entityId, Location location, Direction orientation) {
        Entity entity = server.getEntity(entityId);
        if (entity != null) {
            org.bukkit.Location newLocation = locationMapper.map(location);
            Vector3fc direction = directionUtils.toVector(orientation);
            newLocation.setDirection(new Vector(direction.x(), direction.y(), direction.z()));
            entity.teleport(newLocation);
        }
    }
}
