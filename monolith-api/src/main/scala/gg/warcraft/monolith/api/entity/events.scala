package gg.warcraft.monolith.api.entity

import java.util.UUID

import gg.warcraft.monolith.api.combat.CombatValue
import gg.warcraft.monolith.api.core.event.{CancellableEvent, Event}
import gg.warcraft.monolith.api.entity.team.Team
import gg.warcraft.monolith.api.item.Item
import gg.warcraft.monolith.api.world.Location

trait EntityEvent

// ATTACK
case class EntityPreAttackEvent(
    entityId: UUID,
    entityType: Entity.Type,
    attackerId: UUID,
    sneaking: Boolean,
    projectileId: Option[UUID],
    damage: CombatValue,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityAttackEvent(
    entityId: UUID,
    entityType: Entity.Type,
    attackerId: UUID,
    sneaking: Boolean,
    projectileId: Option[UUID],
    damage: CombatValue
) extends EntityEvent
    with Event

// DAMAGE
case class EntityPreDamageEvent(
    entityId: UUID,
    entityType: Entity.Type,
    damage: CombatValue,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityDamageEvent(
    entityId: UUID,
    entityType: Entity.Type,
    damage: CombatValue
) extends EntityEvent
    with Event

case class EntityPreFatalDamageEvent(
    entityId: UUID,
    entityType: Entity.Type,
    damage: CombatValue,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityFatalDamageEvent(
    entityId: UUID,
    entityType: Entity.Type,
    damage: CombatValue
) extends EntityEvent
    with Event

// DEATH
case class EntityDeathEvent(
    entityId: UUID,
    entityType: Entity.Type,
    drops: List[Item]
) extends EntityEvent
    with Event

// HEAL
case class EntityPreHealEvent(
    entityId: UUID,
    entityType: Entity.Type,
    heal: CombatValue,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityHealEvent(
    entityId: UUID,
    entityType: Entity.Type,
    heal: CombatValue
) extends EntityEvent
    with Event

// HEALTH
case class EntityHealthChangedEvent(
    entityId: UUID,
    entityType: Entity.Type,
    previousHealth: Float,
    previousPercentHealth: Float,
    newHealth: Float,
    newPercentHealth: Float
) extends EntityEvent
    with Event

// INTERACT
case class EntityPreInteractEvent(
    entityId: UUID,
    entityType: Entity.Type,
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item],
    location: Location,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityInteractEvent(
    entityId: UUID,
    entityType: Entity.Type,
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item],
    location: Location
) extends EntityEvent
    with Event

// TEAM
case class EntityTeamChangedEvent(
    entityId: UUID,
    entityType: Entity.Type,
    previousTeam: Option[Team],
    newTeam: Option[Team]
) extends EntityEvent
    with Event

// PATHFIND
case class EntityPrePathfindEvent(
    entityId: UUID,
    entityType: Entity.Type,
    targetLocation: Option[Location],
    targetEntity: Option[UUID],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityPathfindEvent(
    entityId: UUID,
    entityType: Entity.Type,
    targetLocation: Option[Location],
    targetEntity: Option[UUID]
) extends EntityEvent
    with Event

// SPAWN
case class EntityPreSpawnEvent(
    entityId: UUID,
    entityType: Entity.Type,
    location: Location,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntitySpawnEvent(
    entityId: UUID,
    entityType: Entity.Type,
    location: Location
) extends EntityEvent
    with Event

case class EntityPreDespawnEvent(
    entityId: UUID,
    entityType: Entity.Type,
    location: Location
) extends EntityEvent
    with Event

case class EntityDespawnEvent(
    entityId: UUID,
    entityType: Entity.Type,
    location: Location
) extends EntityEvent
    with Event

case class EntityPreRespawnEvent(
    entityId: UUID,
    entityType: Entity.Type,
    location: Location,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends EntityEvent
    with CancellableEvent

case class EntityRespawnEvent(
    entityId: UUID,
    entityType: Entity.Type,
    location: Location
) extends EntityEvent
    with Event
