package gg.warcraft.monolith.spigot.entity.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.combat.PotionEffect;
import gg.warcraft.monolith.api.combat.PotionEffectType;
import gg.warcraft.monolith.api.combat.CombatValue;
import gg.warcraft.monolith.api.entity.EntityServerData;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.attribute.AttributeModifier;
import gg.warcraft.monolith.api.entity.attribute.Attributes;
import gg.warcraft.monolith.api.entity.attribute.GenericAttribute;
import gg.warcraft.monolith.api.entity.attribute.service.AttributeQueryService;
import gg.warcraft.monolith.api.entity.service.EntityServerAdapter;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.core.Duration;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.DirectionUtils;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.spigot.Implicits;
import gg.warcraft.monolith.spigot.combat.SpigotPotionEffectTypeMapper;
import gg.warcraft.monolith.spigot.entity.SpigotAttributeMapper;
import gg.warcraft.monolith.spigot.entity.SpigotEntityDataFactory;
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper;
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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SpigotEntityAdapter implements EntityServerAdapter {

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
        org.bukkit.entity.EntityType spigotEntityType = org.bukkit.entity.EntityType.valueOf(type.name());
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
            livingEntity.damage(amount.modified());
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
            Vector3f direction = directionUtils.toVector(orientation);
            newLocation.setDirection(new Vector(direction.x(), direction.y(), direction.z()));
            entity.teleport(newLocation);
        }
    }
}
