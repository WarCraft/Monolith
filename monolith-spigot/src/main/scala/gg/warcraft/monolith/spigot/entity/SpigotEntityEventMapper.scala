package gg.warcraft.monolith.spigot.entity

import java.util.UUID

import gg.warcraft.monolith.api.combat.{CombatSource, CombatValue}
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.entity.{
  Entity, EntityAttackEvent, EntityDamageEvent, EntityDeathEvent,
  EntityFatalDamageEvent, EntityHealthChangedEvent, EntityInteractEvent,
  EntityPreAttackEvent, EntityPreDamageEvent, EntityPreFatalDamageEvent,
  EntityPreInteractEvent, EntityPreSpawnEvent, EntitySpawnEvent
}
import gg.warcraft.monolith.api.entity.status.StatusService
import gg.warcraft.monolith.spigot.item.SpigotItemMapper
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.entity.LivingEntity
import org.bukkit.event.{EventHandler, EventPriority, Listener}
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.Server
import org.bukkit.attribute.Attribute
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import org.bukkit.plugin.Plugin

import scala.collection.mutable
import scala.jdk.CollectionConverters._

class SpigotEntityEventMapper(
    private implicit val server: Server,
    private implicit val plugin: Plugin,
    private implicit val eventService: EventService,
    private implicit val taskService: TaskService,
    private implicit val statusService: StatusService,
    private implicit val locationMapper: SpigotLocationMapper,
    private implicit val itemMapper: SpigotItemMapper
) extends Listener {
  private val combatValues = mutable.Map.empty[SpigotEvent, CombatValue]
  private val combatValueMetadataKey = classOf[CombatValue].getCanonicalName

  @EventHandler(priority = EventPriority.HIGH)
  def preSpawn(event: SpigotEntitySpawnEvent): Unit = {
    val entity = event.getEntity
    val entityId = entity.getUniqueId
    val entityType = Entity.Type.withName(event.getEntityType.name)
    val location = locationMapper.map(entity.getLocation)
    val preSpawnEvent = EntityPreSpawnEvent(entityId, entityType, location)

    val reducedEvent = eventService.publish(preSpawnEvent)
    if (reducedEvent.location != location) {
      val reducedLocation = locationMapper.map(reducedEvent.location)
      taskService.runNextTick(() => ()) // TODO teleport entity
    }
    event.setCancelled(!reducedEvent.allowed)
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onSpawn(event: SpigotEntitySpawnEvent): Unit = {
    val entity = event.getEntity
    val entityId = entity.getUniqueId
    val entityType = Entity.Type.withName(event.getEntityType.name)
    val location = locationMapper.map(entity.getLocation)
    val spawnEvent = EntitySpawnEvent(entityId, entityType, location)

    eventService.publish(spawnEvent)
  }

  @EventHandler(priority = EventPriority.HIGH)
  def preInteract(event: SpigotEntityInteractEvent): Unit = {
    if (event.getHand == EquipmentSlot.OFF_HAND) return

    val entityId = event.getRightClicked.getUniqueId
    val entityType = Entity.Type.withName(event.getRightClicked.getType.name)
    val player = event.getPlayer
    val playerId = player.getUniqueId
    val mainHand = itemMapper.map(player.getInventory.getItemInMainHand)
    val offHand = itemMapper.map(player.getInventory.getItemInOffHand)
    val clickPosition = event.getClickedPosition
    val location = locationMapper.map(clickPosition.toLocation(player.getWorld))
    val cancelled = event.isCancelled
    val preInteractEvent = EntityPreInteractEvent(
      entityId,
      entityType,
      playerId,
      mainHand,
      offHand,
      location,
      cancelled
    )

    val reducedEvent = eventService.publish(preInteractEvent)
    event.setCancelled(!reducedEvent.allowed)
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onInteract(event: SpigotEntityInteractEvent): Unit = {
    if (event.getHand == EquipmentSlot.OFF_HAND) return

    // TODO does Monolith only want this for LivingEntities?
    val entityId = event.getRightClicked.getUniqueId
    val entityType = Entity.Type.withName(event.getRightClicked.getType.name)
    val player = event.getPlayer
    val playerId = player.getUniqueId
    val mainHand = itemMapper.map(player.getInventory.getItemInMainHand)
    val offHand = itemMapper.map(player.getInventory.getItemInOffHand)
    val clickPosition = event.getClickedPosition
    val location = locationMapper.map(clickPosition.toLocation(player.getWorld))
    val interactEvent = EntityInteractEvent(
      entityId,
      entityType,
      playerId,
      mainHand,
      offHand,
      location
    )

    eventService.publish(interactEvent)
  }

  private def getAttackerId(attacker: SpigotEntity): UUID = attacker match {
    case arrow: SpigotArrow =>
      arrow.getShooter match {
        case shooter: LivingEntity => shooter.getUniqueId
        case _                     => arrow.getUniqueId
      }
    case _ => attacker.getUniqueId
  }

  private def preAttack(event: SpigotEntityDamageByEntityEvent): CombatValue = {
    val entity = event.getEntity
    val entityId = entity.getUniqueId
    val entityType = Entity.Type.withName(entity.getType.name)
    val attacker = event.getDamager
    val attackerId = getAttackerId(attacker)
    // TODO this used to return early if attackerId returned null for
    //  Server::getPlayer and Server::getEntity
    val projectileId =
      if (attackerId != attacker.getUniqueId) Some(attacker.getUniqueId)
      else None
    val combatSource = CombatSource(attacker.getName, Option(attackerId))
    val damage = CombatValue(combatSource, event.getDamage.toFloat)
    val cancelled = event.isCancelled
    val preAttackEvent = EntityPreAttackEvent(
      entityId,
      entityType,
      attackerId,
      projectileId,
      damage,
      cancelled
    )

    val reducedEvent = eventService.publish(preAttackEvent)
    event.setDamage(reducedEvent.damage.modified)
    event.setCancelled(!reducedEvent.allowed)

    reducedEvent.damage
  }

  private def onAttack(event: SpigotEntityDamageByEntityEvent): Unit = {
    combatValues.get(event) match {
      case Some(it) =>
        val entity = event.getEntity
        val entityId = entity.getUniqueId
        val entityType = Entity.Type.withName(entity.getType.name)
        val attacker = event.getDamager // TODO cast to SpigotEntity, and deny pre events of all kinds for non living entities
        val attackerId = getAttackerId(attacker)
        // TODO this used to return early if attackerId returned null for
        //  Server::getPlayer and Server::getEntity
        val projectileId =
          if (attackerId != attacker.getUniqueId) Some(attacker.getUniqueId)
          else None
        val attackEvent = EntityAttackEvent(
          entityId,
          entityType,
          attackerId,
          projectileId,
          it
        )

        eventService.publish(attackEvent)
      case _ => ()
    }
  }

  @EventHandler(priority = EventPriority.HIGH)
  def preDamage(event: SpigotEntityDamageEvent): Unit = {
    if (!event.getEntity.isInstanceOf[LivingEntity]) return
    val entity = event.getEntity.asInstanceOf[LivingEntity]

    var attackDamage: CombatValue = null
    val cause = event.getCause
    if (cause == DamageCause.ENTITY_ATTACK || cause == DamageCause.PROJECTILE) {
      attackDamage = preAttack(event.asInstanceOf[SpigotEntityDamageByEntityEvent])
      if (event.isCancelled) return
    }

    val entityId = entity.getUniqueId
    val entityType = Entity.Type.withName(entity.getType.name)
    val entityStatus = statusService.getStatus(entityId)
    val damage = combatValues.get(event) match {
      case Some(it) => it
      case None =>
        if (attackDamage != null) attackDamage
        else {
          entity.getMetadata(combatValueMetadataKey) match {
            case it if !it.isEmpty =>
              entity.removeMetadata(combatValueMetadataKey, plugin)
              it.get(0).value.asInstanceOf[CombatValue]
            case _ =>
              val combatSource = CombatSource(event.getCause.name, None)
              CombatValue(combatSource, event.getDamage.toFloat)
          }
        }
    }

    val cancelled = event.isCancelled
    val preDamageEvent = EntityPreDamageEvent(
      entityId,
      entityType,
      damage,
      cancelled
    )

    var reducedPreDamageEvent = eventService.publish(preDamageEvent)
    if (reducedPreDamageEvent.allowed) {
      reducedPreDamageEvent = entityStatus.reduce(reducedPreDamageEvent)
    }
    if (!reducedPreDamageEvent.allowed) {
      event.setCancelled(true)
      return
    }

    combatValues.put(event, reducedPreDamageEvent.damage)

    if (damage.modified >= entity.getHealth) {
      val preFatalDamageEvent =
        EntityPreFatalDamageEvent(entityId, entityType, damage)
      var reducedPreFatalDamageEvent = eventService.publish(preFatalDamageEvent)
      if (reducedPreFatalDamageEvent.allowed) {
        reducedPreFatalDamageEvent = entityStatus.reduce(reducedPreFatalDamageEvent)
      }
      if (!reducedPreFatalDamageEvent.allowed) {
        val adjustedDamage = entity.getHealth.toFloat - 1
        val damageOverride = CombatValue.Modifier(
          damage.source,
          CombatValue.Modifier.OVERRIDE,
          adjustedDamage
        )
        val newDamageMods = damageOverride :: damage.modifiers
        val clippedDamage = damage.copy(modifiers = newDamageMods)
        combatValues.put(event, clippedDamage)
        event.setDamage(adjustedDamage)
      }
    }
  }

  @EventHandler(priority = EventPriority.MONITOR)
  // Don't ignore if cancelled until having removed combat value
  def onDamage(event: SpigotEntityDamageEvent): Unit = {
    val combatValue = combatValues.remove(event)
    if (event.isCancelled || !event.getEntity.isInstanceOf[LivingEntity]) return
    val entity = event.getEntity.asInstanceOf[LivingEntity]

    val cause = event.getCause
    if (cause == DamageCause.ENTITY_ATTACK || cause == DamageCause.PROJECTILE)
      onAttack(event.asInstanceOf[SpigotEntityDamageByEntityEvent])

    val entityId = entity.getUniqueId
    val entityType = Entity.Type.withName(entity.getType.name)
    val entityStatus = statusService.getStatus(entityId)
    val damage = combatValue match {
      case Some(it) => it
      case None =>
        val combatSource = CombatSource(event.getCause.name, None)
        CombatValue(combatSource, event.getDamage.toFloat)
    }

    val damageEvent = EntityDamageEvent(entityId, entityType, damage)
    eventService.publish(damageEvent)
    entityStatus.handle(damageEvent)

    if (damage.modified >= entity.getHealth) {
      val fatalDamageEvent = EntityFatalDamageEvent(entityId, entityType, damage)
      eventService.publish(fatalDamageEvent)
    }

    val previousHealth = entity.getHealth.toFloat
    val previousPercentHealth = previousHealth / entity
      .getAttribute(Attribute.GENERIC_MAX_HEALTH)
      .getValue
      .toFloat
    taskService.runNextTick(() => {
      val currentHealth = entity.getHealth.toFloat
      val currentPercentHealth = currentHealth / entity
        .getAttribute(Attribute.GENERIC_MAX_HEALTH)
        .getValue
        .toFloat
      val healthChangedEvent = EntityHealthChangedEvent(
        entityId,
        entityType,
        previousHealth,
        previousPercentHealth,
        currentHealth,
        currentPercentHealth
      )
      eventService.publish(healthChangedEvent)
      entityStatus.handle(healthChangedEvent)
    })
  }

  @EventHandler(priority = EventPriority.HIGH)
  def onDeath(event: SpigotEntityDeathEvent): Unit = {
    val entityId = event.getEntity.getUniqueId
    val entityType = Entity.Type.withName(event.getEntityType.name)
    val entityStatus = null // TODO get from statusQueryService
    val drops = event.getDrops.asScala
      .map(itemMapper.map)
      .filter(_.isDefined)
      .map(_.get)
      .toList
    val deathEvent = EntityDeathEvent(entityId, entityType, drops)

    // TODO entityStatus.getEffects.forEach(effect => effect.onEntityDeathEvent(deathEvent))
    val reducedEvent = eventService.publish(deathEvent)

    /*
     @EventHandler
    public void onEntityDeathEvent(org.bukkit.event.entity.EntityDeathEvent event) {
        UUID entityId = event.getEntity().getUniqueId();
        EntityType entityType = Entity.Type.withName(event.getEntityType().name());
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
   */
  }
}
