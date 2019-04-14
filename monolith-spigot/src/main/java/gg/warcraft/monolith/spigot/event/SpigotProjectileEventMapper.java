package gg.warcraft.monolith.spigot.event;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.combat.ProjectileType;
import gg.warcraft.monolith.api.combat.event.ProjectileHitEvent;
import gg.warcraft.monolith.api.combat.event.ProjectileLaunchEvent;
import gg.warcraft.monolith.api.combat.event.ProjectilePickupEvent;
import gg.warcraft.monolith.api.combat.event.ProjectilePreHitEvent;
import gg.warcraft.monolith.api.combat.event.ProjectilePreLaunchEvent;
import gg.warcraft.monolith.api.combat.event.ProjectilePrePickupEvent;
import gg.warcraft.monolith.api.core.EventService;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.app.combat.event.SimpleProjectileHitEvent;
import gg.warcraft.monolith.app.combat.event.SimpleProjectileLaunchEvent;
import gg.warcraft.monolith.app.combat.event.SimpleProjectilePickupEvent;
import gg.warcraft.monolith.app.combat.event.SimpleProjectilePreHitEvent;
import gg.warcraft.monolith.app.combat.event.SimpleProjectilePreLaunchEvent;
import gg.warcraft.monolith.app.combat.event.SimpleProjectilePrePickupEvent;
import gg.warcraft.monolith.spigot.world.block.SpigotBlockMapper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.UUID;

// FIXME currently all events from this mapper are fired as Arrow events, likely not necessarily true
public class SpigotProjectileEventMapper implements Listener {
    private final EventService eventService;
    private final SpigotBlockMapper blockMapper;

    @Inject
    public SpigotProjectileEventMapper(EventService eventService, SpigotBlockMapper blockMapper) {
        this.eventService = eventService;
        this.blockMapper = blockMapper;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onProjectileLaunchEvent(org.bukkit.event.entity.ProjectileLaunchEvent event) {
        org.bukkit.entity.Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        UUID shooterId = null;
        if (shooter instanceof Entity) {
            shooterId = ((Entity) shooter).getUniqueId();
        }

        ProjectilePreLaunchEvent preLaunchEvent = new SimpleProjectilePreLaunchEvent(projectile.getUniqueId(),
                ProjectileType.ARROW, shooterId, event.isCancelled());
        eventService.publish(preLaunchEvent);

        boolean isCancelled = preLaunchEvent.isCancelled() && !preLaunchEvent.isExplicitlyAllowed();
        event.setCancelled(isCancelled);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onProjectileLaunchEventMonitor(org.bukkit.event.entity.ProjectileLaunchEvent event) {
        org.bukkit.entity.Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        UUID shooterId = null;
        if (shooter instanceof Entity) {
            shooterId = ((Entity) shooter).getUniqueId();
        }

        ProjectileLaunchEvent preLaunchEvent =
                new SimpleProjectileLaunchEvent(projectile.getUniqueId(), ProjectileType.ARROW, shooterId);
        eventService.publish(preLaunchEvent);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onProjectileHitEvent(org.bukkit.event.entity.ProjectileHitEvent event) {
        org.bukkit.entity.Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        UUID shooterId = null;
        if (shooter instanceof Entity) {
            shooterId = ((Entity) shooter).getUniqueId();
        }

        Block block = blockMapper.map(event.getHitBlock());
        UUID entityId = null;
        if (event.getHitEntity() instanceof LivingEntity) {
            entityId = event.getHitEntity().getUniqueId();
        }

        ProjectilePreHitEvent preHitEvent = new SimpleProjectilePreHitEvent(projectile.getUniqueId(),
                ProjectileType.ARROW, shooterId, block, entityId, false);
        eventService.publish(preHitEvent);

        boolean isCancelled = preHitEvent.isCancelled() && !preHitEvent.isExplicitlyAllowed();
        if (isCancelled) {
            event.getEntity().setBounce(true);
            event.getEntity().remove();
        } else {
            event.getEntity().setBounce(preHitEvent.hasBounced());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onProjectileHitEventMonitor(org.bukkit.event.entity.ProjectileHitEvent event) {
        if (event.getEntity().isDead() || event.getEntity().doesBounce()) {
            return;
        }

        org.bukkit.entity.Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        UUID shooterId = null;
        if (shooter instanceof Entity) {
            shooterId = ((Entity) shooter).getUniqueId();
        }

        Block block = blockMapper.map(event.getHitBlock());
        UUID entityId = null;
        if (event.getHitEntity() instanceof LivingEntity) {
            entityId = event.getHitEntity().getUniqueId();
        }

        ProjectileHitEvent hitEvent = new SimpleProjectileHitEvent(projectile.getUniqueId(),
                ProjectileType.ARROW, shooterId, block, entityId);
        eventService.publish(hitEvent);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerPrePickupArrowEvent(PlayerPickupArrowEvent event) {
        org.bukkit.entity.Projectile projectile = event.getArrow();
        ProjectileSource shooter = projectile.getShooter();
        UUID shooterId = null;
        if (shooter instanceof Entity) {
            shooterId = ((Entity) shooter).getUniqueId();
        }

        UUID arrowId = projectile.getUniqueId();
        UUID pickupEntityId = event.getPlayer().getUniqueId();
        ProjectilePrePickupEvent prePickupEvent = new SimpleProjectilePrePickupEvent(arrowId,
                ProjectileType.ARROW, shooterId, pickupEntityId, event.isCancelled());
        eventService.publish(prePickupEvent);
        boolean isCancelled = prePickupEvent.isCancelled() && !prePickupEvent.isExplicitlyAllowed();
        event.setCancelled(isCancelled);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerPickupArrowEvent(PlayerPickupArrowEvent event) {
        org.bukkit.entity.Projectile projectile = event.getArrow();
        ProjectileSource shooter = projectile.getShooter();
        UUID shooterId = null;
        if (shooter instanceof Entity) {
            shooterId = ((Entity) shooter).getUniqueId();
        }

        UUID arrowId = projectile.getUniqueId();
        UUID pickupEntityId = event.getPlayer().getUniqueId();
        ProjectilePickupEvent pickupEvent = new SimpleProjectilePickupEvent(arrowId,
                ProjectileType.ARROW, shooterId, pickupEntityId);
        eventService.publish(pickupEvent);
    }
}
