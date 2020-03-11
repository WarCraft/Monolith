package gg.warcraft.monolith.api.core.command

import gg.warcraft.monolith.api.core.auth.Principal
import gg.warcraft.monolith.api.core.event.{CancellableEvent, Event}

case class CommandPreExecuteEvent(
    principal: Principal,
    command: Command,
    args: List[String],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent

case class CommandExecuteEvent(
    principal: Principal,
    command: Command,
    args: List[String],
    result: Command.Result
) extends Event
