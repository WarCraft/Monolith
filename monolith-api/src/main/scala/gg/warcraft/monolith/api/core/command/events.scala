package gg.warcraft.monolith.api.core.command

import gg.warcraft.monolith.api.core.event.{CancellableEvent, Event}

trait CommandEvent

// TODO fire these events
case class CommandPreExecuteEvent(
    sender: CommandSender,
    cmd: String,
    label: String,
    args: Array[String],
    cancelled: Boolean = false,
    explicitlyAllowed: Boolean = false
) extends CancellableEvent

case class CommandExecuteEvent(
    sender: CommandSender,
    cmd: String,
    label: String,
    args: Array[String]
) extends Event
