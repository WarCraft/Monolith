package gg.warcraft.monolith.api.entity.team

class TeamService {
  private var teams: List[Team] = Nil

  def getTeams: List[Team] =
    teams

  def getTeam(name: String): Option[Team] =
    teams find { _.name == name }

  def registerTeam(team: Team): Unit =
    if (teams forall { _.name != team.name }) teams ::= team
    else throw new IllegalArgumentException
}
