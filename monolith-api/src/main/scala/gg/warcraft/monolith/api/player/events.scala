package gg.warcraft.monolith.api.player

import java.util.UUID

import gg.warcraft.monolith.api.core.event.{CancellableEvent, Event, PreEvent}
import gg.warcraft.monolith.api.world.Location

trait PlayerEvent

// CONNECT
case class PlayerPreConnectEvent(
    playerId: UUID,
    name: String,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends PlayerEvent
    with CancellableEvent

case class PlayerConnectEvent(
    player: Player
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
    player: Player
) extends PlayerEvent
    with Event

// CURRENCY
case class PlayerCurrencyGainedEvent(
    player: Player,
    currency: String,
    amount: Int,
    newCurrent: Int,
    newLifeTime: Int
) extends PlayerEvent
    with Event

case class PlayerCurrencyLostEvent(
    player: Player,
    currency: String,
    amount: Int,
    newCurrent: Int
) extends PlayerEvent
    with Event

// INTERACT TODO do these events make sense with only player data?
case class PlayerPreInteractEvent(
    player: Player,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends PlayerEvent
    with CancellableEvent

case class PlayerInteractEvent(
    player: Player
) extends PlayerEvent
    with Event

// INVENTORY
case class PlayerEquipmentChangedEvent(
    player: Player
) extends PlayerEvent
    with Event

// PERMISSIONS
case class PlayerPermissionsChangedEvent(
    player: Player,
    permissions: Map[String, Boolean]
) extends PlayerEvent
    with Event

// PUNCH
case class PlayerPrePunchEvent(
    player: Player,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends PlayerEvent
    with CancellableEvent

case class PlayerPunchEvent(
    player: Player
) extends PlayerEvent
    with Event

// SPAWN
case class PlayerPreRespawnEvent(
    player: Player,
    location: Location
) extends PlayerEvent
    with PreEvent

case class PlayerRespawnEvent(
    player: Player,
    location: Location
) extends PlayerEvent
    with Event

// STATISTICS
case class PlayerStatisticsChangedEvent(
    player: Player,
    statistic: String,
    amount: Int,
    newCurrent: Int
) extends PlayerEvent
    with Event
