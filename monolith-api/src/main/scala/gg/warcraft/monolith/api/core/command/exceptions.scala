package gg.warcraft.monolith.api.core.command

sealed trait CommandException extends Exception
final case class CommandAlreadyExists(aliases: List[String]) extends CommandException
final case class CommandNotFound(label: String) extends CommandException
