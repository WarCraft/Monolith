package gg.warcraft.monolith.spigot.entity

import java.util.UUID
import java.util.logging.Logger

import gg.warcraft.monolith.api.combat.{CombatValue, PotionEffect}
import gg.warcraft.monolith.api.core.Duration
import gg.warcraft.monolith.api.entity.{Entity, EntityService}
import gg.warcraft.monolith.api.entity.Entity.Type
import gg.warcraft.monolith.api.entity.attribute.AttributeService
import gg.warcraft.monolith.api.entity.data.EntityDataService
import gg.warcraft.monolith.api.entity.status.StatusService
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.player.PlayerService
import gg.warcraft.monolith.api.util.Ops._
import gg.warcraft.monolith.api.world.Location
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import gg.warcraft.monolith.spigot.SpigotPlayer
import gg.warcraft.monolith.spigot.item.SpigotItemMapper
import gg.warcraft.monolith.spigot.math.SpigotVectorMapper
import org.bukkit.Server

import scala.jdk.CollectionConverters._

class SpigotEntityService(
    implicit server: Server,
    logger: Logger,
    attributeService: AttributeService,
    statusService: StatusService,
    teamService: TeamService,
    dataService: EntityDataService,
    playerService: PlayerService,
    vectorMapper: SpigotVectorMapper,
    locationMapper: SpigotLocationMapper,
    itemMapper: SpigotItemMapper,
    entityTypeMapper: SpigotEntityTypeMapper
) extends EntityService {
  override def getEntity(id: UUID): Option[Entity] = {
    server getEntity id match {
      case _: SpigotPlayer  => playerService getPlayer id
      case it: SpigotEntity => new SpigotEntityAdapter(it) |> Some.apply
      case _                => None
    }
  }

  override def getNearbyEntities(
      location: Location,
      radius: (Float, Float, Float)
  ): List[Entity] = {
    val spigotLoc = locationMapper map location
    spigotLoc.getWorld
      .getNearbyEntities(spigotLoc, radius._1, radius._2, radius._3)
      .asScala
      .filter { _.isInstanceOf[SpigotEntity] }
      .map { _.asInstanceOf[SpigotEntity] }
      .map { new SpigotEntityAdapter(_) }
      .toList
  }

  override def setVelocity(id: UUID, vel: Vector3f): Unit =
    (server getEntity id) ?? { _ setVelocity new SpigotVector(vel.x, vel.y, vel.z) }

  override def addPotionEffect(id: UUID, effect: PotionEffect): Unit =
    (server getEntity id) ?? { _ addPotionEffect (potionMapper map effect) }
  /*
            org.bukkit.potion.PotionEffectType spigotPotionEffectType = potionEffectTypeMapper.map(effect.getType());
            org.bukkit.potion.PotionEffect spigotPotionEffect = new org.bukkit.potion.PotionEffect(
                    spigotPotionEffectType, effect.getDuration().inTicks(), effect.getLevel() - 1,
                    effect.isAmbient(), effect.hasParticles());
            livingEntity.addPotionEffect(spigotPotionEffect);
   */

  override def removePotionEffect(id: UUID, effect: PotionEffect): Unit = {}
  /*
        Entity entity = server.getEntity(entityId);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            org.bukkit.potion.PotionEffectType spigotPotionEffectType = potionEffectTypeMapper.map(type);
            livingEntity.removePotionEffect(spigotPotionEffectType);
        }
   */

  override def spawnEntity(
      typed: Type,
      location: Location,
      team: Option[Team] = None
  ): Unit = {
    // TODO spawn entity and store data
    ???
  }

  override def removeEntity(id: UUID): Unit = {}

  override def teleportEntity(id: UUID, location: Location, dir: Vector3f): Unit = {}

  override def damageEntity(id: UUID, amount: CombatValue): Unit = {}

  override def killEntity(id: UUID): Unit = {}

  override def healEntity(id: UUID, amount: CombatValue): Unit = {}

  override def burnEntity(id: UUID, duration: Duration): Unit = {
    (server getEntity id) ?? { _ setFireTicks duration.inTicks }
  }
}
