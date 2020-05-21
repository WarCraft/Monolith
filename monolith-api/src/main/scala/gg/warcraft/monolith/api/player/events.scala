package gg.warcraft.monolith.api.player

import java.util.UUID

import gg.warcraft.monolith.api.core.event.{CancellableEvent, Event, PreEvent}
import gg.warcraft.monolith.api.world.Location

trait PlayerEvent

// CONNECT
case class PlayerPreConnectEvent(
    id: UUID,
    name: String,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with PlayerEvent

case class PlayerConnectEvent(
    player: Player
) extends Event
    with PlayerEvent

/**
  * A PlayerDisconnectEvent is fired whenever a Player disconnects from the server.
  * This event should only be used in cases where you are solely interested in the
  * fact whether a player has left the server. The quit or kick messages can not be
  * set as we can not be sure which caused this event to fire, nor can this event be
  * cancelled as it can represent a non-cancellable PlayerQuitEvent.
  */
case class PlayerDisconnectEvent(
    player: Player
) extends Event
    with PlayerEvent

// CURRENCY
case class PlayerCurrencyGainedEvent(
    player: Player,
    currency: String,
    amount: Int,
    newCurrent: Int,
    newLifeTime: Int
) extends Event
    with PlayerEvent

case class PlayerCurrencyLostEvent(
    player: Player,
    currency: String,
    amount: Int,
    newCurrent: Int
) extends Event
    with PlayerEvent

// INTERACT TODO do these events make sense with only player data?
case class PlayerPreInteractEvent(
    player: Player,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with PlayerEvent

case class PlayerInteractEvent(
    player: Player
) extends Event
    with PlayerEvent

// INVENTORY
case class PlayerEquipmentChangedEvent(
    player: Player
) extends Event
    with PlayerEvent

// PERMISSIONS
case class PlayerPermissionsChangedEvent(
    player: Player,
    permissions: Map[String, Boolean]
) extends Event
    with PlayerEvent

// PUNCH
case class PlayerPrePunchEvent(
    player: Player,
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent
    with PlayerEvent

case class PlayerPunchEvent(
    player: Player
) extends Event
    with PlayerEvent

// SPAWN
case class PlayerPreRespawnEvent(
    player: Player,
    location: Location
) extends PreEvent
    with PlayerEvent

case class PlayerRespawnEvent(
    player: Player,
    location: Location
) extends Event
    with PlayerEvent

// STATISTICS
case class PlayerStatisticsChangedEvent(
    player: Player,
    statistic: String,
    amount: Int,
    newCurrent: Int
) extends Event
    with PlayerEvent
