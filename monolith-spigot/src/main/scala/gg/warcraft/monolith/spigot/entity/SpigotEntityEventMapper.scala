/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.spigot.entity

import gg.warcraft.monolith.api.combat.{CombatSource, CombatValue}
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.entity._
import gg.warcraft.monolith.api.entity.status.StatusService
import gg.warcraft.monolith.api.player.PlayerService
import gg.warcraft.monolith.spigot.item.SpigotItemMapper
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.Server
import org.bukkit.attribute.Attribute
import org.bukkit.entity.{EntityType, LivingEntity}
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import org.bukkit.event.{EventHandler, EventPriority, Listener}
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.plugin.Plugin

import java.util.UUID
import scala.collection.mutable
import scala.jdk.CollectionConverters._
import scala.util.chaining._

class SpigotEntityEventMapper(implicit
    server: Server,
    plugin: Plugin,
    eventService: EventService,
    taskService: TaskService,
    entityService: EntityService,
    playerService: PlayerService,
    statusService: StatusService,
    locationMapper: SpigotLocationMapper,
    itemMapper: SpigotItemMapper
) extends Listener {
  private val combatValues = mutable.Map.empty[SpigotEvent, CombatValue]
  private val combatValueMetadataKey = classOf[CombatValue].getCanonicalName

  private val spigotEntityService = entityService.asInstanceOf[SpigotEntityService]

  private def isMonolithEntity(entity: org.bukkit.entity.Entity): Boolean =
    entity.isInstanceOf[SpigotEntity] && entity.getType != EntityType.ARMOR_STAND

  @EventHandler(priority = EventPriority.HIGH)
  def preSpawn(event: SpigotEntitySpawnEvent): Unit = {
    if (!isMonolithEntity(event.getEntity)) return
    val spigotEntity = event.getEntity.asInstanceOf[SpigotEntity]
    val entity = spigotEntityService.getEntityAdapter(spigotEntity).get
    val location = locationMapper.map(event.getEntity.getLocation)
    EntityPreSpawnEvent(entity, location, event.isCancelled)
      .pipe(eventService.publish)
      .tap { reducedEvent =>
        if (reducedEvent.allowed && reducedEvent.location != location)
          taskService.evalNextTick {
            val reducedLocation = locationMapper.map(reducedEvent.location)
            event.getEntity.teleport(reducedLocation)
          }
        event.setCancelled(!reducedEvent.allowed)
      }
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onSpawn(event: SpigotEntitySpawnEvent): Unit = {
    if (!isMonolithEntity(event.getEntity)) return
    val spigotEntity = event.getEntity.asInstanceOf[SpigotEntity]
    val entity = spigotEntityService.getEntityAdapter(spigotEntity).get
    val location = locationMapper.map(event.getEntity.getLocation)
    EntitySpawnEvent(entity, location).tap { eventService.publish }
  }

  @EventHandler(priority = EventPriority.HIGH)
  def preInteract(event: SpigotEntityInteractEvent): Unit = {
    if (!isMonolithEntity(event.getRightClicked)) return
    if (event.getHand == EquipmentSlot.OFF_HAND) return

    val entity = entityService.getEntity(event.getRightClicked.getUniqueId)
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val clickPosition = event.getClickedPosition.toLocation(event.getPlayer.getWorld)
    val clickLocation = locationMapper.map(clickPosition)
    EntityPreInteractEvent(entity, player, clickLocation, event.isCancelled)
      .pipe { eventService.publish(_) }
      .tap { reducedEvent => event.setCancelled(!reducedEvent.allowed) }
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onInteract(event: SpigotEntityInteractEvent): Unit = {
    if (!isMonolithEntity(event.getRightClicked)) return
    if (event.getHand == EquipmentSlot.OFF_HAND) return

    // TODO does Monolith only want this for LivingEntities?
    val entity = entityService.getEntity(event.getRightClicked.getUniqueId)
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val clickPosition = event.getClickedPosition.toLocation(event.getPlayer.getWorld)
    val clickLocation = locationMapper.map(clickPosition)
    EntityInteractEvent(entity, player, clickLocation).tap { eventService.publish }
  }

  private def getAttackerId(attacker: SpigotDoodad): UUID = attacker match {
    case arrow: SpigotArrow =>
      arrow.getShooter match {
        case shooter: LivingEntity => shooter.getUniqueId
        case _                     => arrow.getUniqueId
      }
    case entity: SpigotEntity => entity.getUniqueId
  }

  private def preAttack(event: SpigotEntityDamageByEntityEvent): CombatValue = {
    val entity = entityService.getEntity(event.getEntity.getUniqueId)
    val attackerId = getAttackerId(event.getDamager)
    val attacker = entityService.getEntity(attackerId)
    // TODO this used to return early if attackerId returned null for
    //  Server::getPlayer and Server::getEntity
    val projectileId =
      if (attacker.id == event.getDamager.getUniqueId) None
      else Some(event.getDamager.getUniqueId)
    val combatSource = CombatSource(attacker.name, Option(attackerId))
    val damage = CombatValue(combatSource, event.getDamage.toFloat)
    EntityPreAttackEvent(entity, attacker, projectileId, damage, event.isCancelled)
      .pipe { eventService.publish(_) }
      .tap { reducedEvent =>
        event.setDamage(reducedEvent.damage.modified)
        event.setCancelled(!reducedEvent.allowed)
      }
      .pipe { _.damage }
  }

  private def onAttack(event: SpigotEntityDamageByEntityEvent): Unit = {
    combatValues.get(event) match {
      case Some(it) =>
        val entity = entityService.getEntity(event.getEntity.getUniqueId)
        val attackerId = getAttackerId(event.getDamager)
        val attacker = entityService.getEntity(attackerId)
        // TODO this used to return early if attackerId returned null for
        //  Server::getPlayer and Server::getEntity
        val projectileId =
          if (attacker.id == event.getDamager.getUniqueId) None
          else Some(event.getDamager.getUniqueId)
        EntityAttackEvent(entity, attacker, projectileId, it)
          .tap { eventService.publish }
      case _ =>
    }
  }

  @EventHandler(priority = EventPriority.HIGH)
  def preDamage(event: SpigotEntityDamageEvent): Unit = {
    if (!isMonolithEntity(event.getEntity)) return
    val spigotEntity = event.getEntity.asInstanceOf[SpigotEntity]

    var attackDamage: CombatValue = null
    val cause = event.getCause
    if (cause == DamageCause.ENTITY_ATTACK || cause == DamageCause.PROJECTILE) {
      attackDamage = preAttack(event.asInstanceOf[SpigotEntityDamageByEntityEvent])
      if (event.isCancelled) return
    }

    val entity = entityService.getEntity(spigotEntity.getUniqueId)
    val entityStatus = statusService.getStatus(entity.id)
    val damage = combatValues.get(event) match {
      case Some(it) => it
      case None =>
        if (attackDamage != null) attackDamage
        else {
          spigotEntity.getMetadata(combatValueMetadataKey) match {
            case it if !it.isEmpty =>
              spigotEntity.removeMetadata(combatValueMetadataKey, plugin)
              it.get(0).value.asInstanceOf[CombatValue]
            case _ =>
              val combatSource = CombatSource(event.getCause.name, None)
              CombatValue(combatSource, event.getDamage.toFloat)
          }
        }
    }

    val reducedDamageEvent = EntityPreDamageEvent(entity, damage, event.isCancelled)
      .pipe { eventService.publish(_) }
      .pipe { reducedEvent =>
        if (reducedEvent.allowed) entityStatus.reduce(reducedEvent)
        else reducedEvent
      }
    if (!reducedDamageEvent.allowed) {
      event.setCancelled(true)
      return
    }

    combatValues.put(event, reducedDamageEvent.damage)

    if (damage.modified >= spigotEntity.getHealth) {
      val reducedFatalEvent = EntityPreFatalDamageEvent(entity, damage)
        .pipe { eventService.publish(_) }
        .pipe { reducedEvent =>
          if (reducedEvent.allowed) entityStatus.reduce(reducedEvent)
          else reducedEvent
        }
      if (!reducedFatalEvent.allowed) {
        val adjustedDamage = spigotEntity.getHealth.toFloat - 1
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
    if (event.isCancelled || !isMonolithEntity(event.getEntity)) return
    val spigotEntity = event.getEntity.asInstanceOf[SpigotEntity]

    val cause = event.getCause
    if (cause == DamageCause.ENTITY_ATTACK || cause == DamageCause.PROJECTILE)
      onAttack(event.asInstanceOf[SpigotEntityDamageByEntityEvent])

    val entity = entityService.getEntity(spigotEntity.getUniqueId)
    val entityStatus = statusService.getStatus(entity.id)
    val damage = combatValue match {
      case Some(it) => it
      case None =>
        val combatSource = CombatSource(event.getCause.name, None)
        CombatValue(combatSource, event.getDamage.toFloat)
    }

    EntityDamageEvent(entity, damage)
      .tap { entityStatus.handle }
      .tap { eventService.publish }

    if (damage.modified >= spigotEntity.getHealth) {
      EntityFatalDamageEvent(entity, damage)
        .tap { entityStatus.handle }
        .tap { eventService.publish }
    }

    val oldHealth = spigotEntity.getHealth.toFloat
    val oldPercentHealth = oldHealth / spigotEntity
      .getAttribute(Attribute.GENERIC_MAX_HEALTH)
      .getValue
      .toFloat
    taskService.runNextTick(() => {
      val newHealth = spigotEntity.getHealth.toFloat
      val newPercentHealth = newHealth / spigotEntity
        .getAttribute(Attribute.GENERIC_MAX_HEALTH)
        .getValue
        .toFloat
      EntityHealthChangedEvent(
        entity,
        oldHealth,
        oldPercentHealth,
        newHealth,
        newPercentHealth
      ).tap { entityStatus.handle }
        .tap { eventService.publish }
    })
  }

  @EventHandler(priority = EventPriority.HIGH)
  def onDeath(event: SpigotEntityDeathEvent): Unit = {
    if (!isMonolithEntity(event.getEntity)) return
    val entity = entityService.getEntity(event.getEntity.getUniqueId)
    val entityStatus = statusService.getStatus(entity.id)
    val drops = event.getDrops.asScala
      .map(itemMapper.map)
      .filter { _.isDefined }
      .map { _.get }
      .toList
    val reducedEvent = EntityPreDeathEvent(entity, drops)
      .tap(eventService.publish)
      .tap(entityStatus.reduce)
    val reducedDrops = reducedEvent.drops
    val reducedSpigotDrops = reducedDrops.map { itemMapper.map }.asJava
    event.getDrops.clear()
    event.getDrops.addAll(reducedSpigotDrops)
    EntityDeathEvent(entity, reducedDrops)
      .tap { entityStatus.handle }
      .tap { eventService.publish }
    eventService << EntityRemoveEvent(entity.id, hasDied = true)
  }
}
