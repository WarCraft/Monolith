package gg.warcraft.monolith.api.entity.team

import gg.warcraft.monolith.api.core.auth.AuthService
import gg.warcraft.monolith.api.core.command.{Command, CommandService}
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.player.data.PlayerDataService

class TeamService(implicit
    commandService: CommandService,
    eventService: EventService,
    authService: AuthService,
    teamService: TeamService,
    playerDataService: PlayerDataService
) {
  private final val teamCommandHandler = new TeamStaffCommandHandler()

  private var _teams: Map[String, Team] = Map.empty

  def teams: Map[String, Team] = _teams

  def registerTeam(team: Team): Unit =
    if (!_teams.contains(team.name)) {
      _teams += team.name -> team
      val command = Command(team.name, Nil)
      commandService.registerCommand(command, teamCommandHandler)
    } else throw new IllegalArgumentException(s"Team ${team.name} already exists!")
}
