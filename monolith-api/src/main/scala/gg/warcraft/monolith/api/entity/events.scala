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
) extends EntityEvent
    with CancellableEvent

case class EntityAttackEvent(
    entity: Entity,
    attacker: Entity,
    projectileId: Option[UUID],
    damage: CombatValue
) extends EntityEvent
    with Event

// DAMAGE
case class EntityPreDamageEvent(
    entity: Entity,
    damage: CombatValue,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityDamageEvent(
    entity: Entity,
    damage: CombatValue
) extends EntityEvent
    with Event

case class EntityPreFatalDamageEvent(
    entity: Entity,
    damage: CombatValue,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityFatalDamageEvent(
    entity: Entity,
    damage: CombatValue
) extends EntityEvent
    with Event

// DEATH
case class EntityPreDeathEvent(
    entity: Entity,
    drops: List[Item]
) extends EntityEvent
    with PreEvent

case class EntityDeathEvent(
    entity: Entity,
    drops: List[Item]
) extends EntityEvent
    with Event

// HEAL
case class EntityPreHealEvent(
    entity: Entity,
    heal: CombatValue,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityHealEvent(
    entity: Entity,
    heal: CombatValue
) extends EntityEvent
    with Event

// HEALTH
case class EntityHealthChangedEvent(
    entity: Entity,
    oldHealth: Float,
    oldPercentHealth: Float,
    newHealth: Float,
    newPercentHealth: Float
) extends EntityEvent
    with Event

// INTERACT
case class EntityPreInteractEvent(
    entity: Entity,
    player: Player,
    location: Location,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityInteractEvent(
    entity: Entity,
    player: Player,
    location: Location
) extends EntityEvent
    with Event

// TEAM
case class EntityTeamChangedEvent(
    entity: Entity,
    oldTeam: Option[Team],
    newTeam: Option[Team]
) extends EntityEvent
    with Event

// PATHFIND
case class EntityPrePathfindEvent(
    entity: Entity,
    targetLocation: Option[Location],
    targetEntity: Option[UUID],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityPathfindEvent(
    entity: Entity,
    targetLocation: Option[Location],
    targetEntity: Option[UUID]
) extends EntityEvent
    with Event

// SPAWN
case class EntityPreSpawnEvent(
    entity: Entity,
    location: Location,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntitySpawnEvent(
    entity: Entity,
    location: Location
) extends EntityEvent
    with Event

case class EntityPreDespawnEvent(
    entity: Entity
) extends EntityEvent
    with Event

case class EntityDespawnEvent(
    entity: Entity
) extends EntityEvent
    with Event

case class EntityPreRespawnEvent(
    entity: Entity,
    location: Location,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityRespawnEvent(
    entity: Entity,
    location: Location
) extends EntityEvent
    with Event
