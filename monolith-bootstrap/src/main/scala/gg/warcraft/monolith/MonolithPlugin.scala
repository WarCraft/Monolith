package gg.warcraft.monolith

trait MonolithPlugin {
  def enable(): Unit
  def disable(): Unit
}
