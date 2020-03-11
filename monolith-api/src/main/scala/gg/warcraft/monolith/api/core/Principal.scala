package gg.warcraft.monolith.api.core

trait Principal {
  val name: String
  def isPlayer: Boolean
  def isConsole: Boolean
  def sendMessage(message: Message): Unit
}
