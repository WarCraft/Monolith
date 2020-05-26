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

import java.util.UUID

import gg.warcraft.monolith.api.block.Block
import gg.warcraft.monolith.api.entity.attribute.Attributes
import gg.warcraft.monolith.api.entity.status.Status
import gg.warcraft.monolith.api.entity.team.Team
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.Location
import gg.warcraft.monolith.api.math.AABBf

object Entity {
  type Type = Type.Value
  object Type extends Enumeration {
    val ARMOR_STAND, BAT, BEE, BLAZE, CAT, CAVE_SPIDER, CHICKEN, COD, COW, CREEPER,
        DOLPHIN, DONKEY, DROWNED, ELDER_GUARDIAN, ENDER_DRAGON, ENDERMAN, ENDERMITE,
        EVOKER, FOX, GHAST, GIANT, GUARDIAN, HORSE, HUSK, ILLUSIONER, IRON_GOLEM,
        LLAMA, MAGMA_CUBE, MULE, MUSHROOM_COW, OCELOT, PANDA, PARROT, PHANTOM, PIG,
        PIG_ZOMBIE, PILLAGER, PLAYER, POLAR_BEAR, PUFFERFISH, RABBIT, RAVAGER,
        SALMON, SHEEP, SHULKER, SILVERFISH, SKELETON, SKELETON_HORSE, SLIME, SNOWMAN,
        SPIDER, SQUID, STRAY, TRADER_LLAMA, TROPICAL_FISH, TURTLE, VEX, VILLAGER,
        VINDICATOR, WANDERING_TRADER, WITCH, WITHER, WITHER_SKELETON, WOLF, ZOMBIE,
        ZOMBIE_HORSE, ZOMBIE_VILLAGER = Value
  }

  case class Intersection(
      entity: Entity,
      location: Location
  )

  /** The entity intersection holds the closest entity the principal entity has
    * targeted with their reticle. If the returned intersection is None then the
    * entity is either not looking at another entity or they were out of range.
    * The block intersection holds the first block the entity has targeted given a set
    * of block types that were ignored. If the returned intersection is None the
    * entity is either looking into the sky or the first block who's type was not
    * ignored was out of range.
    * A block intersection will be calculated regardless of whether an entity is
    * standing in front of it. If an entity is between the principal entity and a
    * block is within range both will be returned. */
  case class Target(
      block: Option[Block.Intersection],
      entity: Option[Entity.Intersection]
  )
}

trait Entity {
  val id: UUID
  val typed: Entity.Type

  def attributes: Attributes
  def status: Status
  def team: Option[Team]

  def name: String
  def location: Location
  def eyeLocation: Location
  def boundingBox: AABBf
  def velocity: Vector3f
  def health: Float
  def equipment: Equipment
  def isAlive: Boolean
  def isGrounded: Boolean
}
