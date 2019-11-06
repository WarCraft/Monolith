package gg.warcraft.monolith.spigot.event;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import gg.warcraft.monolith.api.combat.CombatFactory;
import gg.warcraft.monolith.api.combat.CombatSource;
import gg.warcraft.monolith.api.combat.value.CombatValue;
import gg.warcraft.monolith.api.combat.value.CombatValueModifier;
import gg.warcraft.monolith.api.combat.value.CombatValueModifierType;
import gg.warcraft.monolith.api.core.EventService;
import gg.warcraft.monolith.api.core.TaskService;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.event.*;
import gg.warcraft.monolith.api.entity.status.Status;
import gg.warcraft.monolith.api.entity.status.service.StatusQueryService;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.item.Item;
import gg.warcraft.monolith.app.entity.event.*;
import gg.warcraft.monolith.spigot.Implicits;
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper;
import gg.warcraft.monolith.spigot.world.item.SpigotItemMapper;
import org.bukkit.Server;
import org.bukkit.attribute.Attribute;
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

import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class SpigotEntityEventMapper implements Listener {
    private final StatusQueryService statusQueryService;
    private final EventService eventService;
    private final TaskService taskService;
    private final CombatFactory combatFactory;
    private final SpigotItemMapper itemMapper;
    private final SpigotLocationMapper locationMapper;
    private final Server server;
    private final Plugin plugin;

    private final Map<Event, CombatValue> combatValues;

    @Inject
    public SpigotEntityEventMapper(StatusQueryService statusQueryService,
                                   EventService eventService,
                                   TaskService taskService,
                                   CombatFactory combatFactory,
                                   Server server, Plugin plugin) {
        this.statusQueryService = statusQueryService;
        this.eventService = eventService;
        this.taskService = taskService;
        this.combatFactory = combatFactory;
        this.locationMapper = Implicits.locationMapper();
        this.itemMapper = Implicits.itemMapper(); // TODO undo
        this.server = server;
        this.plugin = plugin;

        this.combatValues = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityPreSpawnEvent(org.bukkit.event.entity.EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        UUID entityId = entity.getUniqueId();
        EntityType entityType = EntityType.valueOf(event.getEntityType().name());
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
        EntityType entityType = EntityType.valueOf(event.getEntityType().name());
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
        EntityType entityType = EntityType.valueOf(event.getRightClicked().getType().name());
        UUID playerId = player.getUniqueId();
        Item itemInClickHand = event.getHand() == EquipmentSlot.HAND
                ? itemMapper.map(player.getEquipment().getItemInMainHand()).getOrElse(() -> null) // TODO use Option
                : itemMapper.map(player.getEquipment().getItemInOffHand()).getOrElse(() -> null);
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
        EntityType entityType = EntityType.valueOf(event.getRightClicked().getType().name());
        UUID playerId = player.getUniqueId();
        Item itemInClickHand = event.getHand() == EquipmentSlot.HAND
                ? itemMapper.map(player.getEquipment().getItemInMainHand()).getOrElse(() -> null) // TODO use Option
                : itemMapper.map(player.getEquipment().getItemInOffHand()).getOrElse(() -> null);
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
        EntityType entityType = EntityType.valueOf(entity.getType().name());
        Status entityStatus = statusQueryService.getStatus(entityId);
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
        if (entityPreDamageEvent.isAllowed()) {
            entityStatus.getEffects().forEach(effect -> effect.onEntityPreDamageEvent(entityPreDamageEvent));
        }
        if (!entityPreDamageEvent.isAllowed()) {
            event.setCancelled(true);
            return;
        }

        combatValues.put(event, entityPreDamageEvent.getDamage());

        if (damage.getModifiedValue() >= entity.getHealth()) {
            EntityPreFatalDamageEvent entityPreFatalDamageEvent =
                    new SimpleEntityPreFatalDamageEvent(entityId, entityType, damage, event.isCancelled());
            eventService.publish(entityPreFatalDamageEvent);
            if (entityPreFatalDamageEvent.isAllowed()) {
                entityStatus.getEffects().forEach(effect -> effect.onEntityPreFatalDamageEvent(entityPreFatalDamageEvent));
            }
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
        EntityType entityType = EntityType.valueOf(entity.getType().name());
        Status entityStatus = statusQueryService.getStatus(entityId);
        CombatValue damage = combatValues.remove(event);
        if (damage == null) {
            CombatSource combatSource = combatFactory.createCombatSource(event.getCause().name(), null);
            damage = combatFactory.createCombatValue((float) event.getDamage(), new ArrayList<>(), combatSource);
        }
        EntityDamageEvent entityDamageEvent = new SimpleEntityDamageEvent(entityId, entityType, damage);
        eventService.publish(entityDamageEvent);
        entityStatus.getEffects().forEach(effect -> effect.onEntityDamageEvent(entityDamageEvent));

        if (damage.getModifiedValue() >= entity.getHealth()) {
            EntityFatalDamageEvent entityFatalDamageEvent =
                    new SimpleEntityFatalDamageEvent(entityId, entityType, damage);
            eventService.publish(entityFatalDamageEvent);
        }

        float previousHealth = (float) entity.getHealth();
        float previousPercentHealth = previousHealth /
                (float) entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        taskService.runNextTick(() -> {
            float currentHealth = (float) entity.getHealth();
            float currentPercentHealth = currentHealth /
                    (float) entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            EntityHealthChangedEvent healthChangedEvent = new SimpleEntityHealthChangedEvent(entityId, entityType,
                    previousHealth, previousPercentHealth, currentHealth, currentPercentHealth);
            eventService.publish(healthChangedEvent);
            entityStatus.getEffects().forEach(effect -> effect.onEntityHealthChangedEvent(healthChangedEvent));
        });
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
        EntityType entityType = EntityType.valueOf(entity.getType().name());

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
        EntityType entityType = EntityType.valueOf(entity.getType().name());

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
        EntityType entityType = EntityType.valueOf(event.getEntityType().name());
        Status entityStatus = statusQueryService.getStatus(entityId);
        List<Item> drops = event.getDrops().stream()
                .map(itemMapper::map)
                .map(it -> it.getOrElse(() -> (Item) null)) // TODO use Option
                .collect(Collectors.toList());
        EntityDeathEvent entityDeathEvent = new SimpleEntityDeathEvent(entityId, entityType, drops);
        eventService.publish(entityDeathEvent);
        entityStatus.getEffects().forEach(effect -> effect.onEntityDeathEvent(entityDeathEvent));

        List<ItemStack> spigotDrops = entityDeathEvent.getDrops().stream()
                .map(itemMapper::map)
                .collect(Collectors.toList());
        event.getDrops().clear();
        event.getDrops().addAll(spigotDrops);
    }
}
