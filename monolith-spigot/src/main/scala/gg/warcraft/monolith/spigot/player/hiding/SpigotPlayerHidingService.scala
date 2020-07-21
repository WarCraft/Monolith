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

package gg.warcraft.monolith.spigot.player.hiding

import java.util.UUID

import gg.warcraft.monolith.api.player.{Player, PlayerService}
import gg.warcraft.monolith.api.player.hiding.PlayerHidingService
import gg.warcraft.monolith.api.util.chaining._
import gg.warcraft.monolith.spigot.player.SpigotPlayer
import org.bukkit.Server
import org.bukkit.plugin.Plugin

import scala.jdk.CollectionConverters._

class SpigotPlayerHidingService(implicit
    server: Server,
    plugin: Plugin,
    playerService: PlayerService
) extends PlayerHidingService {
  private var hiddenPlayers: Map[UUID, Set[UUID]] = Map.empty
  private var hidePredicates: Map[UUID, Player => Boolean] = Map.empty

  private def hideFrom(id: UUID, from: Iterable[SpigotPlayer]): Unit = {
    val player = server.getPlayer(id)
    if (player != null) {
      val hideFromIds = from.map(_.getUniqueId).toSet
      val hiddenFrom = hiddenPlayers.getOrElse(player.getUniqueId, Set.empty)
      hiddenPlayers += (player.getUniqueId -> (hiddenFrom ++ hideFromIds))
      from.foreach { _.hidePlayer(plugin, player) }
    }
  }

  override def hide(id: UUID): Unit =
    hideFrom(id, server.getOnlinePlayers.asScala)

  override def hideFrom(id: UUID, from: UUID): Unit = {
    val fromPlayer = server.getPlayer(from)
    if (fromPlayer != null) hideFrom(id, fromPlayer :: Nil)
  }

  override def hideFrom(id: UUID, predicate: Player => Boolean): Unit =
    hideFrom(
      id,
      server.getOnlinePlayers.asScala.filter { player =>
        predicate.apply(playerService.getPlayer(player.getUniqueId))
      }
    )

  override def hideAllFrom(id: UUID): Unit = {} // TODO fix access modifier

  override def updateHideFrom(id: UUID): Unit = {} // TODO fix access modifier

  override def reveal(id: UUID): Unit =
    server.getPlayer(id) ?? { player =>
      hiddenPlayers -= id
      server.getOnlinePlayers.asScala.foreach { _.showPlayer(plugin, player) }
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

  override def revealAllTo(id: UUID): Unit = { // TODO fix access modifier
    val player = server.getPlayer(id)
    if (player != null) {
      hiddenPlayers = hiddenPlayers.transform {
        case (it, hiddenFrom) =>
          if (hiddenFrom.contains(id)) {
            val hiddenPlayer = server.getPlayer(it)
            if (hiddenPlayer != null) player.showPlayer(plugin, hiddenPlayer)
            hiddenFrom - id
          } else hiddenFrom
      }
    }
  }
}
