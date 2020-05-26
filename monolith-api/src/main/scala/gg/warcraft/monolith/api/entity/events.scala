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

import gg.warcraft.monolith.api.combat.CombatValue
import gg.warcraft.monolith.api.core.event.{CancellableEvent, Event, PreEvent}
import gg.warcraft.monolith.api.entity.team.Team
import gg.warcraft.monolith.api.item.Item
import gg.warcraft.monolith.api.player.Player
import gg.warcraft.monolith.api.world.Location

trait EntityEvent

// ATTACK
case class EntityPreAttackEvent(
    entity: Entity,
    attacker: Entity,
    projectileId: Option[UUID],
    damage: CombatValue,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with EntityEvent

case class EntityAttackEvent(
    entity: Entity,
    attacker: Entity,
    projectileId: Option[UUID],
    damage: CombatValue
) extends Event
    with EntityEvent

// DAMAGE
case class EntityPreDamageEvent(
    entity: Entity,
    damage: CombatValue,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with EntityEvent

case class EntityDamageEvent(
    entity: Entity,
    damage: CombatValue
) extends Event
    with EntityEvent

case class EntityPreFatalDamageEvent(
    entity: Entity,
    damage: CombatValue,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with EntityEvent

case class EntityFatalDamageEvent(
    entity: Entity,
    damage: CombatValue
) extends Event
    with EntityEvent

// DEATH
case class EntityPreDeathEvent(
    entity: Entity,
    drops: List[Item]
) extends PreEvent
    with EntityEvent

case class EntityDeathEvent(
    entity: Entity,
    drops: List[Item]
) extends Event
    with EntityEvent

// HEAL
case class EntityPreHealEvent(
    entity: Entity,
    heal: CombatValue,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with EntityEvent

case class EntityHealEvent(
    entity: Entity,
    heal: CombatValue
) extends Event
    with EntityEvent

// HEALTH
case class EntityHealthChangedEvent(
    entity: Entity,
    oldHealth: Float,
    oldPercentHealth: Float,
    newHealth: Float,
    newPercentHealth: Float
) extends Event
    with EntityEvent

// INTERACT
case class EntityPreInteractEvent(
    entity: Entity,
    player: Player,
    location: Location,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with EntityEvent

case class EntityInteractEvent(
    entity: Entity,
    player: Player,
    location: Location
) extends Event
    with EntityEvent

// TEAM
case class EntityTeamChangedEvent(
    entity: Entity,
    oldTeam: Option[Team],
    newTeam: Option[Team]
) extends Event
    with EntityEvent

// PATHFIND
case class EntityPrePathfindEvent(
    entity: Entity,
    targetLocation: Option[Location],
    targetEntity: Option[UUID],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with EntityEvent

case class EntityPathfindEvent(
    entity: Entity,
    targetLocation: Option[Location],
    targetEntity: Option[UUID]
) extends Event
    with EntityEvent

// SPAWN
case class EntityPreSpawnEvent(
    entity: Entity,
    location: Location,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with EntityEvent

case class EntitySpawnEvent(
    entity: Entity,
    location: Location
) extends Event
    with EntityEvent

case class EntityPreDespawnEvent(
    entity: Entity
) extends Event
    with EntityEvent

case class EntityDespawnEvent(
    entity: Entity
) extends Event
    with EntityEvent

case class EntityPreRespawnEvent(
    entity: Entity,
    location: Location,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with EntityEvent

case class EntityRespawnEvent(
    entity: Entity,
    location: Location
) extends Event
    with EntityEvent
