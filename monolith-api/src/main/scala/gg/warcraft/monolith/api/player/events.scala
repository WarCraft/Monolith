package gg.warcraft.monolith.api.player

import java.util.UUID

import gg.warcraft.monolith.api.core.event.{CancellableEvent, Event, PreEvent}
import gg.warcraft.monolith.api.core.{CancellableEvent, PreEvent}
import gg.warcraft.monolith.api.entity.Equipment
import gg.warcraft.monolith.api.entity.player.Statistic
import gg.warcraft.monolith.api.world.Location
import gg.warcraft.monolith.api.world.item.Item

trait PlayerEvent

// CONNECT
case class PlayerPreConnectEvent(
    playerId: UUID,
    name: String
) extends PlayerEvent
    with Event

case class PlayerConnectEvent(
    playerId: UUID
) extends PlayerEvent
    with Event

/**
  * A PlayerDisconnectEvent is fired whenever a Player disconnects from the server.
  * This event should only be used in cases where you are solely interested in the
  * fact whether a player has left the server. The quit or kick messages can not be
  * set as we can not be sure which caused this event to fire, nor can this event be
  * cancelled as it can represent a non-cancellable PlayerQuitEvent.
  */
case class PlayerDisconnectEvent(
    playerId: UUID
) extends PlayerEvent
    with Event

// CURRENCY
case class PlayerCurrencyGainedEvent(
    playerId: UUID,
    currency: String,
    amount: Int,
    newCurrent: Int,
    newLifeTime: Int
) extends PlayerEvent
    with Event

case class PlayerCurrencyLostEvent(
    playerId: UUID,
    currency: String,
    amount: Int,
    newCurrent: Int
) extends PlayerEvent
    with Event

// INTERACT
case class PlayerPreInteractEvent(
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends PlayerEvent
    with CancellableEvent

case class PlayerInteractEvent(
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item]
) extends PlayerEvent
    with Event

// INVENTORY
case class PlayerEquipmentChangedEvent(
    playerId: UUID,
    equipment: Equipment
) extends PlayerEvent
    with Event

// PERMISSIONS
case class PlayerPermissionsChangedEvent(
    playerId: UUID,
    permissions: Map[String, Boolean]
) extends PlayerEvent
    with Event

// PUNCH
case class PlayerPrePunchEvent(
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends PlayerEvent
    with CancellableEvent

case class PlayerPunchEvent(
    playerId: UUID,
    sneaking: Boolean,
    mainHand: Option[Item],
    offHand: Option[Item]
) extends PlayerEvent
    with Event

// SPAWN
case class PlayerPreRespawnEvent(
    playerId: UUID,
    location: Location
) extends PlayerEvent
    with PreEvent

case class PlayerRespawnEvent(
    playerId: UUID,
    location: Location
) extends PlayerEvent
    with Event

// STATISTICS
case class PlayerStatisticsChangedEvent(
    playerId: UUID,
    statistic: Statistic,
    amount: Int,
    newCurrent: Int
) extends PlayerEvent
    with Event
