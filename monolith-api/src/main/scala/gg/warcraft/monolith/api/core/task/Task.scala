package gg.warcraft.monolith.api.core.task

trait Task {
  val id: Int
  val async: Boolean
  def cancel(): Unit
  def cancelled: Boolean
}
