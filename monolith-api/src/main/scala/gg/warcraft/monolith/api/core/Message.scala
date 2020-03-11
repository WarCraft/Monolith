package gg.warcraft.monolith.api.core

import java.time.{LocalDateTime, LocalTime}

import gg.warcraft.monolith.api.util.ColorCode

object Message {
  def apply(text: String): Message = Message(text, text)

  def server(text: String): Message = {
    val formatted = s"${ColorCode.YELLOW}[Server] $text"
    Message(text, formatted)
  }

  def error(code: Int): Message = {
    val extendedCode = s"$code:${LocalTime.now}"
    val text = s"Please notify an admin you ran into error code $extendedCode."
    val formatted = s"${ColorCode.YELLOW}[Internal Error] $text"
    Message(text, formatted)
  }
}

case class Message(
    original: String,
    formatted: String,
    timestamp: LocalDateTime = LocalDateTime.now
)
