package gg.warcraft.monolith.spigot.entity

import java.util.UUID
import java.util.logging.Logger

import gg.warcraft.monolith.api.combat.{CombatValue, PotionEffect}
import gg.warcraft.monolith.api.core.Duration
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.entity.{Entity, EntityService}
import gg.warcraft.monolith.api.entity.Entity.Type
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
import org.bukkit.entity.EntityType
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.Plugin

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
    deleteEntityData(id)
    getSpigotEntity(id).foreach { _.remove() }
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
    getSpigotEntity(id).foreach { entity => }

  /*
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
            EntityHealthChangedEvent entityHealthChangedEvent = new EntityHealthChangedEvent(entityId, entityType,
                    previousHealth, previousPercentHealth, newHealth, newPercentHealth);
            eventService.publish(entityHealthChangedEvent);
        }
    }

    @Override
    public void heal(UUID entityId, CombatValue amount) {
        Entity entity = entityQueryService.getEntity(entityId);
        EntityType entityType = entity.getType();
        EntityPreHealEvent entityPreHealEvent = new EntityPreHealEvent(entityId, entityType, amount, false, false);
        eventService.publish(entityPreHealEvent);
        if (!entityPreHealEvent.allowed()) {
            return;
        }

        float previousHealth = entity.getHealth();

        CombatValue heal = entityPreHealEvent.heal();
        entityServerAdapter.heal(entityId, heal.modified());

        EntityHealEvent entityHealEvent = new EntityHealEvent(entityId, entityType, heal);
        eventService.publish(entityHealEvent);

        publishHealthChangedEvent(entityId, entityType, previousHealth);
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
   */

  override def burnEntity(id: UUID, duration: Duration): Unit =
    getSpigotEntity(id).foreach { entity =>
      val updatedFireTicks = entity.getFireTicks + duration.ticks
      entity.setFireTicks(updatedFireTicks)
    }
}
