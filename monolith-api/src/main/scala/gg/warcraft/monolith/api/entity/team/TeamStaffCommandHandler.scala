package gg.warcraft.monolith.api.entity.team

import gg.warcraft.monolith.api.core.auth.{AuthService, Principal}
import gg.warcraft.monolith.api.core.command.{Command, CommandService}
import gg.warcraft.monolith.api.core.event.{Event, EventService}
import gg.warcraft.monolith.api.entity.EntityTeamChangedEvent
import gg.warcraft.monolith.api.player.Player
import gg.warcraft.monolith.api.player.data.PlayerDataService

class TeamStaffCommandHandler(implicit
    eventService: EventService,
    commandService: CommandService,
    authService: AuthService,
    teamService: TeamService,
    playerDataService: PlayerDataService
) extends Command.Handler
    with Event.Handler {
  import teamService._

  override def handle(
      principal: Principal,
      command: Command,
      args: String*
  ): Command.Result = principal match {
    case player: Player =>
      if (!authService.isStaff(player)) Command.invalid
      else if (args.nonEmpty) Command.invalid
      else if (!teams.contains(command.name)) Command.invalid
      else {
        val team = teams.get(command.name)
        val oldData = player.data
        if (oldData.team != team) {
          val newData = oldData.copy(team = team)
          playerDataService.setPlayerData(newData)

          // TODO fire this event in an appropriate place
          eventService.publish(
            EntityTeamChangedEvent(
              player,
              oldData.team,
              newData.team
            )
          )
        }
        Command.success
      }
    case _ => Command.playersOnly
  }

  override def handle(event: Event): Unit = event match {
    case TeamRegisteredEvent(name, _, _) =>
      val command = Command(name, Nil)
      commandService.registerCommand(command, this)
    case _ =>
  }
}
