package gg.warcraft.monolith.api.entity

import java.util.UUID

import gg.warcraft.monolith.api.combat.{CombatValue, PotionEffect}
import gg.warcraft.monolith.api.core.Duration
import gg.warcraft.monolith.api.core.Duration._
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.entity.data.EntityDataService
import gg.warcraft.monolith.api.entity.team.Team
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.Location

import scala.collection.mutable
import scala.util.chaining._

abstract class EntityService(implicit
    eventService: EventService,
    dataService: EntityDataService
) {
  private final val knockBackStrength: mutable.Map[Float, Float] = mutable.Map.empty
  private final val knockUpStrength: mutable.Map[Float, Float] = mutable.Map.empty
  private final val leapStrength: mutable.Map[Float, Float] = mutable.Map.empty

  def getEntity(id: UUID): Entity
  def getEntityOption(id: UUID): Option[Entity]
  def getNearbyEntities(loc: Location, radius: (Float, Float, Float)): List[Entity]
  def getNearbyEntities(loc: Location, radius: Float): List[Entity] =
    getNearbyEntities(loc, (radius, radius, radius))

  def setTeam(id: UUID, team: Option[Team]): Unit = {
    val oldData = dataService.data(id)
    if (oldData.team != team) {
      val newData = oldData.copy(team = team)
      dataService.setEntityData(newData)

      EntityTeamChangedEvent(getEntity(id), oldData.team, newData.team)
        .pipe { eventService.publish }
    }
  }

  def setVelocity(id: UUID, velocity: Vector3f): Unit
  def addPotionEffect(id: UUID, effect: PotionEffect): Unit
  def removePotionEffect(id: UUID, effect: PotionEffect.Type): Unit

  def spawnEntity(
      typed: Entity.Type,
      location: Location,
      team: Option[Team] = None
  ): UUID
  def removeEntity(id: UUID): Unit
  def killEntity(id: UUID): Unit

  def burnEntity(id: UUID, duration: Duration): Unit
  def damageEntity(id: UUID, amount: CombatValue): Unit
  def healEntity(id: UUID, amount: CombatValue): Unit
  def teleportEntity(
      id: UUID,
      location: Location,
      direction: Vector3f = null
  ): Unit

  def heavyEntity(id: UUID, duration: Duration): Unit = {
    val entity = getEntity(id)
    /*
        private Block findBlockUnderFeet(Entity entity) {
        Block current = worldService.getBlock(Location.toBlockLocation(entity.getLocation()));
        while (!current.solid() && current.location().y() >= 0) {
            current = blockUtils.getRelative(current, BlockFace.DOWN);
        }
        return current;
    }
     */
//        Block targetBlock = findBlockUnderFeet(entity);
//        int safeY = targetBlock.location().y() + 1;
//        Location safeLocation = entity.getLocation().withY(safeY);
//        setVelocity(id, Vector3f())
//        teleportEntity(id, safeLocation)

//        this.removePotionEffect(entityId, PotionEffectType.JUMP);
//        PotionEffect effect = new SimplePotionEffect(PotionEffectType.JUMP, 128, duration);
//        this.addPotionEffect(entityId, effect);
  }

  def freezeEntity(id: UUID, duration: Duration): Unit = {
//    this.removePotionEffect(entityId, PotionEffectType.SLOW);
//    PotionEffect effect = new SimplePotionEffect(PotionEffectType.SLOW, 7, duration);
//    this.addPotionEffect(entityId, effect);
    heavyEntity(id, duration)
  }

  /**
    * Curve fitting on https://mycurvefit.com/ with the assumption that we
    * always want the Y component of the knock back vector to be 10% of the
    * length of the direction of said vector and manually checking what
    * strength modifier would be required to be knocked back x amount of
    * blocks giving the following data set:
    * distance    modifier
    * 0.5         0.230
    * 1           0.458
    * 2           0.764
    * 3           1.144
    * 4           1.325
    * 5           1.576
    * 10          2.494
    * 20          4.465
    * Resulted in the following formula where x is the knock back distance
    * in blocks and y is the strength modifier required to achieve this
    * distance. The formula is valid for up to 20 blocks after which it
    * will most likely start to behave weirdly (but that is more due to
    * the fact that the 10% Y component is no longer realistic for such
    * large knock backs).
    * y = 593343 + (0.009109503 - 593343)/(1 + (x/108856200) ** 0.7613247)
    */
  protected def calcKnockBackStrength(distance: Float): Float = {
    val exponent = Math.pow(distance / 108856200f, 0.7613247f).toFloat
    593343f + (0.009109503f - 593343f) / (1f + exponent)
  }

  def knockBackEntity(id: UUID, direction: Vector3f, distance: Float): Unit = {
    val strength = knockBackStrength
      .getOrElseUpdate(distance, calcKnockBackStrength(distance))
    val knockBack = direction.copy(y = 0.1f).normalized
    val velocity = knockBack * strength
    setVelocity(id, velocity)
  }

  def knockBackEntity(id: UUID, source: Location, distance: Float): Unit = {
    val entity = getEntity(id)
    val direction = source - entity.location
    knockBackEntity(id, direction, distance)
  }

  /**
    * Curve fitting on https://mycurvefit.com/ with manually checking what
    * strength modifier would be required to be knocked up x amount of
    * blocks giving the following data set:
    * distance    modifier
    * 0.5         0.250
    * 1           0.370
    * 2           0.545
    * 3           0.685
    * 4           0.806
    * 5           0.913
    * 10          1.344
    * 20          1.999
    * Resulted in the following formula where x is the knock up distance
    * in blocks and y is the strength modifier required to achieve this
    * distance. The formula is valid for up to 20 blocks after which it
    * will most likely start to behave weirdly (but that is more due to
    * the fact that the 10% Y component is no longer realistic for such
    * large knock ups).
    * y = 23865.35 + (0.007556944 - 23865.35)/(1 + (x/296502700) ** 0.5688117)
    */
  protected def calcKnockUpStrength(distance: Float): Float = {
    val exponent = Math.pow(distance / 296502700f, 0.5688117f).toFloat
    23865.35f + (0.007556944f - 23865.35f) / (1f + exponent)
  }

  def knockUpEntity(id: UUID, distance: Float): Unit = {
    val entity = getEntity(id)
    val strength = knockUpStrength
      .getOrElseUpdate(distance, calcKnockUpStrength(distance))
    val knockUp = Vector3f(y = strength)
    val velocity = entity.velocity add knockUp
    setVelocity(id, velocity)
  }

  /**
    * Curve fitting on https://mycurvefit.com/ by manually checking what
    * strength modifier would be required to leap forward x amount of
    * blocks giving the following data set:
    * distance    modifier
    * 0.5         0.207
    * 1           0.357
    * 2           0.580
    * 3           0.753
    * 4           0.949
    * 5           1.085
    * 10          1.834
    * Resulted in the following formula where x is the leap distance in
    * blocks and y is the strength modifier required to achieve this
    * distance. The formula is valid for up to 10 blocks after which it
    * will start to damage the user.
    * y = 158503.1 + (0.02094631 - 158503.1)/(1 + (x/45625190) ** 0.7422179)
    */
  protected def calcLeapStrength(distance: Float): Float = {
    val exponent = Math.pow(distance / 45625190f, 0.7422179f).toFloat
    158503.1f + (0.02094631f - 158503.1f) / (1f + exponent)
  }

  def leapEntity(id: UUID, direction: Vector3f, distance: Float): Unit = {
    val entity = getEntity(id)
    val strength = leapStrength
      .getOrElseUpdate(distance, calcKnockUpStrength(distance))
    val y = if (distance <= 10f) 0.4f else 0.25f
    val leap = direction.copy(y = y).normalized
    val velocity = leap * strength
    val newVelocity = entity.velocity add velocity
    setVelocity(id, newVelocity)
  }

  def vacuumEntity(id: UUID, source: Location, strength: Float): Unit = {
    val entity = getEntity(id)
    heavyEntity(id, 1.seconds)
    val direction = source - entity.location.normalized * (0.05f * strength)
    val velocity = entity.velocity.copy(x = direction.x).copy(z = direction.z)
    setVelocity(id, velocity)
  }

  def intersectEntity(
      origin: Location,
      target: Location,
      ignore: Entity => Boolean
  ): Entity.Intersection

  def calculateTarget(
      id: UUID,
      range: Float,
      ignore: Entity => Boolean
  ): Entity.Target
}
