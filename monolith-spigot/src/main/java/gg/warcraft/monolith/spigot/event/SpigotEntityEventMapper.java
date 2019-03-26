package gg.warcraft.monolith.spigot.event;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import gg.warcraft.monolith.api.combat.CombatFactory;
import gg.warcraft.monolith.api.combat.CombatSource;
import gg.warcraft.monolith.api.combat.value.CombatValue;
import gg.warcraft.monolith.api.combat.value.CombatValueModifier;
import gg.warcraft.monolith.api.combat.value.CombatValueModifierType;
import gg.warcraft.monolith.api.core.EventService;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.event.EntityAttackEvent;
import gg.warcraft.monolith.api.entity.event.EntityDamageEvent;
import gg.warcraft.monolith.api.entity.event.EntityDeathEvent;
import gg.warcraft.monolith.api.entity.event.EntityFatalDamageEvent;
import gg.warcraft.monolith.api.entity.event.EntityInteractEvent;
import gg.warcraft.monolith.api.entity.event.EntityPreAttackEvent;
import gg.warcraft.monolith.api.entity.event.EntityPreDamageEvent;
import gg.warcraft.monolith.api.entity.event.EntityPreFatalDamageEvent;
import gg.warcraft.monolith.api.entity.event.EntityPreInteractEvent;
import gg.warcraft.monolith.api.entity.event.EntityPreSpawnEvent;
import gg.warcraft.monolith.api.entity.event.EntitySpawnEvent;
import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.world.location.Location;
import gg.warcraft.monolith.app.entity.event.SimpleEntityAttackEvent;
import gg.warcraft.monolith.app.entity.event.SimpleEntityDamageEvent;
import gg.warcraft.monolith.app.entity.event.SimpleEntityDeathEvent;
import gg.warcraft.monolith.app.entity.event.SimpleEntityFatalDamageEvent;
import gg.warcraft.monolith.app.entity.event.SimpleEntityInteractEvent;
import gg.warcraft.monolith.app.entity.event.SimpleEntityPreAttackEvent;
import gg.warcraft.monolith.app.entity.event.SimpleEntityPreDamageEvent;
import gg.warcraft.monolith.app.entity.event.SimpleEntityPreFatalDamageEvent;
import gg.warcraft.monolith.app.entity.event.SimpleEntityPreInteractEvent;
import gg.warcraft.monolith.app.entity.event.SimpleEntityPreSpawnEvent;
import gg.warcraft.monolith.app.entity.event.SimpleEntitySpawnEvent;
import gg.warcraft.monolith.spigot.entity.SpigotEntityTypeMapper;
import gg.warcraft.monolith.spigot.item.SpigotItemMapper;
import gg.warcraft.monolith.spigot.world.location.SpigotLocationMapper;
import org.bukkit.Server;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class SpigotEntityEventMapper implements Listener {
    private final EventService eventService;
    private final CombatFactory combatFactory;
    private final SpigotEntityTypeMapper entityTypeMapper;
    private final SpigotItemMapper itemMapper;
    private final SpigotLocationMapper locationMapper;
    private final Server server;
    private final Plugin plugin;

    private final Map<Event, CombatValue> combatValues;

    @Inject
    public SpigotEntityEventMapper(EventService eventService, CombatFactory combatFactory,
                                   SpigotEntityTypeMapper entityTypeMapper, SpigotItemMapper itemMapper,
                                   SpigotLocationMapper locationMapper, Server server, Plugin plugin) {
        this.eventService = eventService;
        this.combatFactory = combatFactory;
        this.entityTypeMapper = entityTypeMapper;
        this.itemMapper = itemMapper;
        this.locationMapper = locationMapper;
        this.server = server;
        this.plugin = plugin;

        this.combatValues = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityPreSpawnEvent(org.bukkit.event.entity.EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        UUID entityId = entity.getUniqueId();
        EntityType entityType = entityTypeMapper.map(event.getEntityType());
        Location location = locationMapper.map(entity.getLocation());
        EntityPreSpawnEvent entityPreSpawnEvent =
                new SimpleEntityPreSpawnEvent(entityId, entityType, location, event.isCancelled());
        eventService.publish(entityPreSpawnEvent);

        boolean isCancelled = entityPreSpawnEvent.isCancelled() && !entityPreSpawnEvent.isExplicitlyAllowed();
        event.setCancelled(isCancelled);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntitySpawnEvent(org.bukkit.event.entity.EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        UUID entityId = entity.getUniqueId();
        EntityType entityType = entityTypeMapper.map(event.getEntityType());
        Location location = locationMapper.map(entity.getLocation());
        EntitySpawnEvent entitySpawnEvent = new SimpleEntitySpawnEvent(entityId, entityType, location);
        eventService.publish(entitySpawnEvent);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityPreInteractEvent(PlayerInteractAtEntityEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        org.bukkit.entity.Player player = event.getPlayer();
        UUID entityId = event.getRightClicked().getUniqueId();
        EntityType entityType = entityTypeMapper.map(event.getRightClicked().getType());
        UUID playerId = player.getUniqueId();
        Item itemInClickHand = event.getHand() == EquipmentSlot.HAND
                ? itemMapper.map(player.getEquipment().getItemInMainHand())
                : itemMapper.map(player.getEquipment().getItemInOffHand());
        Location interactLocation = locationMapper.map(event.getClickedPosition().toLocation(player.getWorld()));
        EntityPreInteractEvent entityPreInteractEvent = new SimpleEntityPreInteractEvent(entityId, entityType, playerId,
                itemInClickHand, interactLocation, event.isCancelled());
        eventService.publish(entityPreInteractEvent);

        boolean isCancelled = entityPreInteractEvent.isCancelled() && !entityPreInteractEvent.isExplicitlyAllowed();
        event.setCancelled(isCancelled);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityInteractEvent(PlayerInteractAtEntityEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        org.bukkit.entity.Player player = event.getPlayer();
        UUID entityId = event.getRightClicked().getUniqueId(); // FIXME do we only want this for livingentities? Gonna need a new event for others
        EntityType entityType = entityTypeMapper.map(event.getRightClicked().getType());
        UUID playerId = player.getUniqueId();
        Item itemInClickHand = event.getHand() == EquipmentSlot.HAND
                ? itemMapper.map(player.getEquipment().getItemInMainHand())
                : itemMapper.map(player.getEquipment().getItemInOffHand());
        Location interactLocation = locationMapper.map(event.getClickedPosition().toLocation(player.getWorld()));
        EntityInteractEvent entityInteractEvent =
                new SimpleEntityInteractEvent(entityId, entityType, playerId, itemInClickHand, interactLocation);
        eventService.publish(entityInteractEvent);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityPreDamageEvent(org.bukkit.event.entity.EntityDamageEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity entity = (LivingEntity) event.getEntity();

        CombatValue attackDamage = null;
        org.bukkit.event.entity.EntityDamageEvent.DamageCause cause = event.getCause();
        if (cause == org.bukkit.event.entity.EntityDamageEvent.DamageCause.ENTITY_ATTACK ||
                cause == org.bukkit.event.entity.EntityDamageEvent.DamageCause.PROJECTILE) {
            attackDamage = onEntityPreAttackEvent((EntityDamageByEntityEvent) event);
            if (event.isCancelled()) {
                return;
            }
        }

        UUID entityId = entity.getUniqueId();
        EntityType entityType = entityTypeMapper.map(entity.getType());
        CombatValue damage = combatValues.get(event);
        if (damage == null) {
            if (attackDamage != null) {
                damage = attackDamage;
            } else {
                List<MetadataValue> metaCombatValue = entity.getMetadata(CombatValue.class.getCanonicalName());
                if (!metaCombatValue.isEmpty()) {
                    damage = (CombatValue) metaCombatValue.get(0).value();
                    entity.removeMetadata(CombatValue.class.getCanonicalName(), plugin);
                } else {
                    CombatSource combatSource = combatFactory.createCombatSource(event.getCause().name(), null);
                    damage = combatFactory.createCombatValue((float) event.getDamage(), new ArrayList<>(), combatSource);
                }
            }
        }
        EntityPreDamageEvent entityPreDamageEvent = new SimpleEntityPreDamageEvent(entityId, entityType, damage, event.isCancelled());
        eventService.publish(entityPreDamageEvent);
        if (!entityPreDamageEvent.isAllowed()) {
            event.setCancelled(true);
            return;
        }

        combatValues.put(event, entityPreDamageEvent.getDamage());

        if (damage.getModifiedValue() >= entity.getHealth()) {
            EntityPreFatalDamageEvent entityPreFatalDamageEvent =
                    new SimpleEntityPreFatalDamageEvent(entityId, entityType, damage, event.isCancelled());
            eventService.publish(entityPreFatalDamageEvent);
            if (entityPreFatalDamageEvent.isCancelled() && !entityPreFatalDamageEvent.isExplicitlyAllowed()) {
                float adjustedDamage = (float) entity.getHealth() - 1;
                List<CombatValueModifier> newModifiers = damage.getModifiers();
                newModifiers.add(combatFactory.createCombatValueModifier(CombatValueModifierType.OVERRIDE,
                        adjustedDamage, damage.getSource()));
                CombatValue clippedDamage =
                        combatFactory.createCombatValue(damage.getBaseValue(), newModifiers, damage.getSource());
                combatValues.put(event, clippedDamage);
                event.setDamage(adjustedDamage);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    // don't auto ignore cancelled until combat value is removed
    public void onEntityDamageEvent(org.bukkit.event.entity.EntityDamageEvent event) {
        if (event.isCancelled()) {
            combatValues.remove(event);
            return;
        }

        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity entity = (LivingEntity) event.getEntity();

        org.bukkit.event.entity.EntityDamageEvent.DamageCause cause = event.getCause();
        if (cause == org.bukkit.event.entity.EntityDamageEvent.DamageCause.ENTITY_ATTACK ||
                cause == org.bukkit.event.entity.EntityDamageEvent.DamageCause.PROJECTILE) {
            onEntityAttackEvent((EntityDamageByEntityEvent) event);
        }

        UUID entityId = entity.getUniqueId();
        EntityType entityType = entityTypeMapper.map(entity.getType());
        CombatValue damage = combatValues.remove(event);
        if (damage == null) {
            CombatSource combatSource = combatFactory.createCombatSource(event.getCause().name(), null);
            damage = combatFactory.createCombatValue((float) event.getDamage(), new ArrayList<>(), combatSource);
        }
        EntityDamageEvent entityDamageEvent = new SimpleEntityDamageEvent(entityId, entityType, damage);
        eventService.publish(entityDamageEvent);

        if (damage.getModifiedValue() >= entity.getHealth()) {
            EntityFatalDamageEvent entityFatalDamageEvent =
                    new SimpleEntityFatalDamageEvent(entityId, entityType, damage);
            eventService.publish(entityFatalDamageEvent);
        }

        // TODO publish health changed event
    }

    private UUID getAttackerId(Entity damager) {
        if (damager.getType() == org.bukkit.entity.EntityType.ARROW) {
            ProjectileSource arrowSource = ((Arrow) damager).getShooter();
            if (arrowSource instanceof LivingEntity) {
                return ((LivingEntity) arrowSource).getUniqueId();
            }
        }
        return damager.getUniqueId();
    }

    private CombatValue onEntityPreAttackEvent(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        UUID entityId = entity.getUniqueId();
        EntityType entityType = entityTypeMapper.map(entity.getType());

        Entity damager = event.getDamager();
        UUID attackerId = damager.getUniqueId();
        UUID projectileId = null;
        if (damager.getType() == org.bukkit.entity.EntityType.ARROW) {
            ProjectileSource arrowSource = ((Arrow) damager).getShooter();
            if (arrowSource instanceof LivingEntity) {
                attackerId = ((LivingEntity) arrowSource).getUniqueId();
                projectileId = damager.getUniqueId();
            }
        }
        if (server.getPlayer(attackerId) == null && server.getEntity(attackerId) == null) {
            return null;
        }

        CombatSource combatSource = combatFactory.createCombatSource(damager.getName(), attackerId);
        CombatValue damage = combatFactory.createCombatValue((float) event.getDamage(), new ArrayList<>(), combatSource);

        EntityPreAttackEvent entityPreAttackEvent = new SimpleEntityPreAttackEvent(entityId, entityType, attackerId,
                projectileId, damage, event.isCancelled());
        eventService.publish(entityPreAttackEvent);

        combatValues.put(event, entityPreAttackEvent.getDamage());
        event.setDamage(entityPreAttackEvent.getDamage().getModifiedValue());

        boolean isCancelled = entityPreAttackEvent.isCancelled() && !entityPreAttackEvent.isExplicitlyAllowed();
        event.setCancelled(isCancelled);

        return damage;
    }

    private void onEntityAttackEvent(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        UUID entityId = entity.getUniqueId();
        EntityType entityType = entityTypeMapper.map(entity.getType());

        Entity damager = event.getDamager();
        UUID attackerId = damager.getUniqueId();
        UUID projectileId = null;
        if (damager.getType() == org.bukkit.entity.EntityType.ARROW) {
            ProjectileSource arrowSource = ((Arrow) damager).getShooter();
            if (arrowSource instanceof LivingEntity) {
                attackerId = ((LivingEntity) arrowSource).getUniqueId();
                projectileId = damager.getUniqueId();
            }
        }
        if (server.getPlayer(attackerId) == null && server.getEntity(attackerId) == null) {
            return;
        }

        CombatValue damage = combatValues.get(event);
        EntityAttackEvent entityAttackEvent = new SimpleEntityAttackEvent(entityId, entityType, attackerId,
                projectileId, damage);
        eventService.publish(entityAttackEvent);
    }

    @EventHandler
    public void onEntityDeathEvent(org.bukkit.event.entity.EntityDeathEvent event) {
        UUID entityId = event.getEntity().getUniqueId();
        EntityType entityType = entityTypeMapper.map(event.getEntityType());
        List<Item> drops = event.getDrops().stream()
                .map(itemMapper::map)
                .collect(Collectors.toList());
        EntityDeathEvent entityDeathEvent = new SimpleEntityDeathEvent(entityId, entityType, drops);
        eventService.publish(entityDeathEvent);

        List<ItemStack> spigotDrops = entityDeathEvent.getDrops().stream()
                .map(itemMapper::map)
                .collect(Collectors.toList());
        event.getDrops().clear();
        event.getDrops().addAll(spigotDrops);
    }
}
