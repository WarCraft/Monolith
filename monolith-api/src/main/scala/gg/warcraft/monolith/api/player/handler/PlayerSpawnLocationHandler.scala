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

package gg.warcraft.monolith.api.player.handler

import gg.warcraft.monolith.api.core.event.{Event, PreEvent}
import gg.warcraft.monolith.api.entity.{EntityService, EntityTeamChangedEvent}
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.player.{Player, PlayerPreRespawnEvent}

class PlayerSpawnLocationHandler(implicit
    entityService: EntityService
) extends Event.Handler {
  override def handle(event: Event): Unit = event match {
    case EntityTeamChangedEvent(player: Player, Some(_), Some(team)) =>
      // TODO set teleport direction
      entityService.teleportEntity(player.id, team.spawn, Vector3f())

    case _ =>
  }

  override def reduce[T <: PreEvent](event: T): T = event match {
    case event @ PlayerPreRespawnEvent(player, _) => player.team match {
        case Some(team) => event.copy(location = team.spawn).asInstanceOf[T]
        case None       => event
      }
    case _ => event
  }
}
