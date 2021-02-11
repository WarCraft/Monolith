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

package gg.warcraft.monolith.api.entity

import gg.warcraft.monolith.api.block.BlockIterator
import gg.warcraft.monolith.api.block.box.BlockBox
import gg.warcraft.monolith.api.combat.{CombatValue, PotionEffect}
import gg.warcraft.monolith.api.core.Duration
import gg.warcraft.monolith.api.core.Duration._
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.entity.data.{EntityData, EntityDataService}
import gg.warcraft.monolith.api.entity.team.Team
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.util.chaining._
import gg.warcraft.monolith.api.world.{Location, WorldService}
import org.joml.Vector2f
import org.joml.primitives.{AABBf, Intersectionf, LineSegmentf}

import java.util.UUID
import scala.collection.mutable
import scala.util.chaining._

abstract class EntityService(implicit
    eventService: EventService,
    dataService: EntityDataService,
    worldService: WorldService
) {
  private final val knockBackStrength: mutable.Map[Float, Float] = mutable.Map.empty
  private final val knockUpStrength: mutable.Map[Float, Float] = mutable.Map.empty
  private final val leapStrength: mutable.Map[Float, Float] = mutable.Map.empty

  protected final val setEntityData: EntityData => Unit = dataService.setEntityData
  protected final val deleteEntityData: UUID => Unit = dataService.deleteEntityData

  def getEntity(id: UUID): Entity
  def getEntityOption(id: UUID): Option[Entity]
  def getNearbyEntities(loc: Location, radius: (Float, Float, Float)): List[Entity]
  def getNearbyEntities(loc: Location, radius: Float): List[Entity] =
    getNearbyEntities(loc, (radius, radius, radius))
  def getEntitiesWithin(box: BlockBox): List[Entity] =
    getNearbyEntities(box.center, (box.halfWidth, box.halfHeight, box.halfLength))

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

  def crippleEntity(id:UUID, duration: Duration):Unit = {
    removePotionEffect(id, PotionEffect.SLOW)
    addPotionEffect(id, PotionEffect(PotionEffect.SLOW, 7, duration))
  }

  def heavyEntity(id: UUID, duration: Duration): Unit = {
    val entity = getEntity(id)
    val location = entity.location

    removePotionEffect(id, PotionEffect.JUMP)
    addPotionEffect(id, PotionEffect(PotionEffect.JUMP, 128, duration))

    setVelocity(id, Vector3f())
    teleportEntity(id, location)
  }

  def freezeEntity(id: UUID, duration: Duration): Unit = {
    crippleEntity(id, duration)
    heavyEntity(id, duration)
  }

  /** Curve fitting on https://mycurvefit.com/ with the assumption that we
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

  /** Curve fitting on https://mycurvefit.com/ with manually checking what
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
    val velocity = entity.velocity + knockUp
    setVelocity(id, velocity)
  }

  /** Curve fitting on https://mycurvefit.com/ by manually checking what
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
    val newVelocity = entity.velocity + velocity
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
      predicate: Entity => Boolean
  ): Option[Entity.Intersection] = {
    val boundingBox =
      new AABBf(origin.translation, target.translation).correctBounds()

    val deltaX = (boundingBox.maxX - boundingBox.minX) * .5f
    val deltaY = (boundingBox.maxY - boundingBox.minY) * .5f
    val deltaZ = (boundingBox.maxZ - boundingBox.minZ) * .5f

    val centerX = boundingBox.minX + deltaX
    val centerY = boundingBox.minY + deltaY
    val centerZ = boundingBox.minZ + deltaZ

    val center = Location(origin.world, Vector3f(centerX, centerY, centerZ))
    val intersectionLine = new LineSegmentf(origin.translation, target.translation)
    val nearbyEntities = getNearbyEntities(center, (deltaX, deltaY, deltaZ))

    var closestIntersectionScalar = Float.MaxValue
    var closestIntersectedEntity: Entity = null
    nearbyEntities.foreach { entity =>
      if (predicate.apply(entity)) {
        val intersectionResult = new Vector2f()
        val jomlBoundingBox =
          new AABBf(entity.boundingBox.min, entity.boundingBox.max)
        val result =
          jomlBoundingBox.intersectsLineSegment(intersectionLine, intersectionResult)
        if (result != Intersectionf.OUTSIDE) {
          if (intersectionResult.x < closestIntersectionScalar) {
            closestIntersectionScalar = intersectionResult.x
            closestIntersectedEntity = entity
          }
        }
      }
    }

    if (closestIntersectedEntity != null) {
      val distanceAlongRay = (target - origin) * closestIntersectionScalar
      val intersection = origin + distanceAlongRay
      val intersectionLocation = Location(origin.world, intersection)
      Entity.Intersection(
        closestIntersectedEntity,
        intersectionLocation
      ) |> Some.apply
    } else None
  }

  def calculateTarget(
      id: UUID,
      range: Float,
      predicate: Entity => Boolean
  ): Entity.Target = {
    val entity = getEntity(id)
    val origin = entity.eyeLocation
    val direction = origin.rotation
    val target = origin + (direction * range)

    val blockIntersection = new BlockIterator(origin, target).intersect(_.solid)
    val correctedTarget = blockIntersection match {
      case Some(intersection) => intersection.location
      case None               => target
    }
    val entityIntersection = intersectEntity(origin, correctedTarget, predicate)
    Entity.Target(blockIntersection, entityIntersection)
  }
}
