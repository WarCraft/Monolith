package gg.warcraft.monolith.spigot.player

import java.util.UUID

import gg.warcraft.monolith.api.player.{Player, PlayerHidingService}
import org.bukkit.Server
import org.bukkit.plugin.Plugin

import scala.jdk.CollectionConverters._

class SpigotPlayerHidingService(
    implicit server: Server,
    plugin: Plugin
) extends PlayerHidingService {
  private var hiddenPlayers: Map[UUID, Set[UUID]] = Map.empty
  private var hidePredicates: Map[UUID, Player => Boolean] = Map.empty

  private def hideFrom(id: UUID, from: Iterable[SpigotPlayer]): Unit = {
    val player = server.getPlayer(id)
    if (player != null) {
      val hideFromIds = from.map(_.getUniqueId).toSet
      val hiddenFrom = hiddenPlayers.getOrElse(player.getUniqueId, Set.empty)
      hiddenPlayers += (player.getUniqueId -> (hiddenFrom ++ hideFromIds))
      from foreach { _.hidePlayer(plugin, player) }
    }
  }

  override def hide(id: UUID): Unit =
    hideFrom(id, server.getOnlinePlayers.asScala)

  override def hideFrom(id: UUID, from: UUID): Unit = {
    val fromPlayer = server.getPlayer(from)
    if (fromPlayer != null) hideFrom(id, fromPlayer :: Nil)
  }

  override def hideFrom(id: UUID, predicate: Player => Boolean): Unit =
    hideFrom(id, server.getOnlinePlayers.asScala filter { predicate apply _ })

  private[player] override def hideAllFrom(id: UUID): Unit = {}

  private[player] override def updateHideFrom(id: UUID): Unit = {}

  override def reveal(id: UUID): Unit = {
    val player = server.getPlayer(id)
    if (player != null) {
      hiddenPlayers -= id
      server.getOnlinePlayers.asScala foreach { _.showPlayer(plugin, player) }
    }
  }

  override def revealTo(id: UUID, to: UUID): Unit = {
    val player = server.getPlayer(id)
    if (player != null) {
      val hiddenFrom = hiddenPlayers.getOrElse(id, Set.empty)
      val newHiddenFrom = hiddenFrom - to
      if (newHiddenFrom.isEmpty) hiddenPlayers -= id
      else hiddenPlayers += (id -> newHiddenFrom)

      val toPlayer = server.getPlayer(to)
      if (toPlayer != null) toPlayer.showPlayer(plugin, player)
    }
  }

  private[player] override def revealAllTo(id: UUID): Unit = {
    val player = server.getPlayer(id)
    if (player != null) {
      hiddenPlayers = hiddenPlayers transform {
        case (it, hiddenFrom) =>
          if (hiddenFrom contains id) {
            val hiddenPlayer = server.getPlayer(it)
            if (hiddenPlayer != null) player.showPlayer(plugin, hiddenPlayer)
            hiddenFrom - id
          } else hiddenFrom
      }
    }
  }
}
