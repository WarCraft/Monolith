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

import gg.warcraft.monolith.api.combat.{CombatValue, PotionEffect}
import gg.warcraft.monolith.api.core.Duration
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.entity.Entity.Type
import gg.warcraft.monolith.api.entity._
import gg.warcraft.monolith.api.entity.attribute.AttributeService
import gg.warcraft.monolith.api.entity.data.{EntityData, EntityDataService}
import gg.warcraft.monolith.api.entity.status.StatusService
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.player.PlayerService
import gg.warcraft.monolith.api.util.chaining._
import gg.warcraft.monolith.api.world.{Location, WorldService}
import gg.warcraft.monolith.spigot.combat.SpigotPotionMapper
import gg.warcraft.monolith.spigot.item.SpigotItemMapper
import gg.warcraft.monolith.spigot.math.{SpigotAABBfMapper, SpigotVectorMapper}
import gg.warcraft.monolith.spigot.player.SpigotPlayer
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.Server
import org.bukkit.attribute.Attribute
import org.bukkit.entity.{EntityType, LivingEntity}
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.Plugin

import java.util.UUID
import java.util.logging.Logger
import scala.jdk.CollectionConverters._

class SpigotEntityService(implicit
    server: Server,
    plugin: Plugin,
    logger: Logger,
    eventService: EventService,
    attributeService: AttributeService,
    statusService: StatusService,
    teamService: TeamService,
    dataService: EntityDataService,
    playerService: PlayerService,
    vectorMapper: SpigotVectorMapper,
    boundingBoxMapper: SpigotAABBfMapper,
    locationMapper: SpigotLocationMapper,
    itemMapper: SpigotItemMapper,
    potionMapper: SpigotPotionMapper,
    entityTypeMapper: SpigotEntityTypeMapper,
    worldService: WorldService
) extends EntityService {
  private def getSpigotEntity(id: UUID): Option[SpigotEntity] =
    server.getEntity(id) match {
      case it: SpigotEntity if it.getType != EntityType.ARMOR_STAND => Some(it)
      case _                                                        => None
    }

  override def getEntity(id: UUID): Entity =
    getEntityOption(id).get

  override def getEntityOption(id: UUID): Option[Entity] =
    server.getEntity(id) match {
      case _: SpigotPlayer =>
        playerService.getPlayer(id) |> Some.apply
      case it: SpigotEntity if it.getType != EntityType.ARMOR_STAND =>
        new SpigotEntityAdapter(it) |> Some.apply
      case _ => None
    }

  def getEntityAdapter(entity: SpigotEntity): Option[Entity] =
    entity match {
      case it: SpigotPlayer =>
        playerService.getPlayer(it.getUniqueId) |> Some.apply
      case it: SpigotEntity if it.getType != EntityType.ARMOR_STAND =>
        new SpigotEntityAdapter(it) |> Some.apply
      case _ => None
    }

  override def getNearbyEntities(
      location: Location,
      radius: (Float, Float, Float)
  ): List[Entity] = {
    val spigotLocation = locationMapper.map(location)
    spigotLocation.getWorld
      .getNearbyEntities(spigotLocation, radius._1, radius._2, radius._3)
      .asScala.iterator
      .map { it => getEntityOption(it.getUniqueId) }
      .filter { _.isDefined }
      .map { _.get }
      .toList
  }

  override def setVelocity(id: UUID, velocity: Vector3f): Unit =
    getSpigotEntity(id).foreach {
      val newVelocity = new SpigotVector(velocity.x, velocity.y, velocity.z)
      _.setVelocity(newVelocity)
    }

  override def addPotionEffect(id: UUID, effect: PotionEffect): Unit =
    getSpigotEntity(id).foreach {
      val spigotEffect = potionMapper.map(effect)
      _.addPotionEffect(spigotEffect)
    }

  override def removePotionEffect(id: UUID, effect: PotionEffect.Type): Unit =
    getSpigotEntity(id).foreach {
      val spigotEffectType = potionMapper.map(effect)
      _.removePotionEffect(spigotEffectType)
    }

  override def spawnEntity(
      typed: Type,
      location: Location,
      team: Option[Team] = None
  ): UUID = {
    val spigotType = entityTypeMapper.map(typed)
    val spigotLocation = locationMapper.map(location)
    if (!spigotLocation.getChunk.isLoaded) spigotLocation.getChunk.load()
    val spigotEntity =
      spigotLocation.getWorld.spawnEntity(spigotLocation, spigotType)
    setEntityData(EntityData(spigotEntity.getUniqueId, team))
    spigotEntity.getUniqueId
  }

  override def removeEntity(id: UUID): Unit = {
    getSpigotEntity(id) match {
      case Some(entity) if entity.getType != EntityType.PLAYER =>
        entity.remove()
        eventService << EntityRemoveEvent(id, hasDied = false)
      case _ =>
    }
  }

  override def teleportEntity(
      id: UUID,
      location: Location,
      direction: Vector3f = null
  ): Unit = getSpigotEntity(id).foreach { entity =>
    val spigotLocation = locationMapper.map(location)
    val spigotDirection =
      if (direction != null) vectorMapper.map(direction)
      else entity.getLocation.getDirection
    spigotLocation.setDirection(spigotDirection)
    entity.teleport(spigotLocation)
  }

  override def damageEntity(id: UUID, amount: CombatValue): Unit =
    getSpigotEntity(id).foreach { entity =>
      val metaData = new FixedMetadataValue(plugin, amount)
      entity.setMetadata(classOf[CombatValue].getCanonicalName, metaData)
      entity.damage(amount.modified)
    }

  override def killEntity(id: UUID): Unit =
    getSpigotEntity(id).foreach { _.setHealth(0) }

  override def healEntity(id: UUID, amount: CombatValue): Unit =
    getEntityOption(id).foreach { entity =>
      val preEvent = eventService << EntityPreHealEvent(entity, amount)
      if (preEvent.allowed) {
        val reducedAmount = preEvent.heal
        server.getEntity(id) match {
          case it: LivingEntity =>
            val maxHealth =
              it.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue.toFloat
            val prevHealth = it.getHealth.toFloat
            val newHealth = Math.min(prevHealth + reducedAmount.modified, maxHealth)
            it.setHealth(newHealth)

            eventService << EntityHealEvent(entity, reducedAmount)
            if (newHealth != prevHealth) {
              val prevPercentHealth = prevHealth / maxHealth
              val newPercentHealth = newHealth / maxHealth
              eventService << EntityHealthChangedEvent(
                entity,
                prevHealth,
                prevPercentHealth,
                newHealth,
                newPercentHealth
              )
            }
          case _ =>
        }
      }
    }

  override def burnEntity(id: UUID, duration: Duration): Unit =
    getSpigotEntity(id).foreach { entity =>
      val updatedFireTicks = entity.getFireTicks + duration.ticks
      entity.setFireTicks(updatedFireTicks)
    }
}
