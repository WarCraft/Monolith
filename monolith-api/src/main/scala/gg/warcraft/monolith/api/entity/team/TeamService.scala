package gg.warcraft.monolith.api.entity.team

import gg.warcraft.monolith.api.core.event.EventService

class TeamService(implicit eventService: EventService) {
  private implicit val teamService: TeamService = this

  private var _teams: Map[String, Team] = Map.empty

  def teams: Map[String, Team] = _teams

  def registerTeam(team: Team): Unit =
    if (!_teams.contains(team.name)) {
      _teams += team.name -> team
      val event = TeamRegisteredEvent(team.name, team.chatColor, team.particleColor)
      eventService.publish(event)
    } else throw new IllegalArgumentException(s"Team ${team.name} already exists!")
}
