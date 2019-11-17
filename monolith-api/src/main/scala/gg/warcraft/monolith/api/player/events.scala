package gg.warcraft.monolith.api.player

import java.util.UUID

import gg.warcraft.monolith.api.core.{Event, PreEvent}
import gg.warcraft.monolith.api.entity.Equipment
import gg.warcraft.monolith.api.entity.player.Statistic
import gg.warcraft.monolith.api.world.Location

// CONNECT
case class PlayerPreConnectEvent(
    playerId: UUID,
    name: String
) extends Event

case class PlayerConnectEvent(
    playerId: UUID
) extends Event

/**
  * A PlayerDisconnectEvent is fired whenever a Player disconnects from the server.
  * This event should only be used in cases where you are solely interested in the
  * fact whether a player has left the server. The quit or kick messages can not be
  * set as we can not be sure which caused this event to fire, nor can this event be
  * cancelled as it can represent a non-cancellable PlayerQuitEvent.
  */
case class PlayerDisconnectEvent(
    playerId: UUID
) extends Event

// CURRENCY
case class PlayerCurrencyGainedEvent(
    playerId: UUID,
    currency: String,
    amount: Int,
    newCurrent: Int,
    newLifeTime: Int
) extends Event

case class PlayerCurrencyLostEvent(
    playerId: UUID,
    currency: String,
    amount: Int,
    newCurrent: Int
) extends Event

// INVENTORY
case class PlayerEquipmentChangedEvent(
    playerId: UUID,
    equipment: Equipment
) extends Event

// PERMISSIONS
case class PlayerPermissionsChangedEvent(
    playerId: UUID,
    permissions: Map[String, Boolean]
) extends Event

// SPAWN
case class PlayerPreRespawnEvent(
    playerId: UUID,
    location: Location
) extends PreEvent

case class PlayerRespawnEvent(
    playerId: UUID,
    location: Location
) extends Event

// STATISTICS
case class PlayerStatisticsChangedEvent(
    playerId: UUID,
    statistic: Statistic,
    amount: Int,
    newCurrent: Int
)
