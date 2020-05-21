package gg.warcraft.monolith.api.entity.team

class TeamService {
  private var _teams: List[Team] = Nil

  def teams: List[Team] = _teams

  def teamOption(name: String): Option[Team] =
    _teams.find { _.name == name }

  def registerTeam(team: Team): Unit =
    if (_teams.forall { _.name != team.name }) _teams ::= team
    else throw new IllegalArgumentException(s"Team ${team.name} already exists!")
}
