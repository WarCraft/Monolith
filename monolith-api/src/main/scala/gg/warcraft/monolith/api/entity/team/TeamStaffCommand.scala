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

package gg.warcraft.monolith.api.entity.team

import gg.warcraft.monolith.api.core.auth.{AuthService, Principal}
import gg.warcraft.monolith.api.core.command.Command
import gg.warcraft.monolith.api.player.{Player, PlayerService}

class TeamStaffCommand() extends Command("team")

object TeamStaffCommand {
  class Handler(implicit
      authService: AuthService,
      teamService: TeamService,
      playerService: PlayerService
  ) extends Command.Handler {
    override def handle(
        principal: Principal,
        command: Command,
        args: String*
    ): Command.Result = principal match {
      case player: Player =>
        if (!authService.isStaff(player)) Command.invalid
        else if (args.length != 1) Command.invalid
        else
          teamService.teams.get(args.head) match {
            case team @ Some(_) =>
              playerService.setTeam(player.id, team)
              Command.success
            case None => Command.invalid
          }
      case _ => Command.playersOnly
    }
  }
}
