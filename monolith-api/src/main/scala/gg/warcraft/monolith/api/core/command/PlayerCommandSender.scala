package gg.warcraft.monolith.api.core.command

import java.util.UUID

case class PlayerCommandSender(
    name: String,
    playerId: Option[UUID]
) extends CommandSender {
  if (playerId.isEmpty) throw new IllegalArgumentException
}
