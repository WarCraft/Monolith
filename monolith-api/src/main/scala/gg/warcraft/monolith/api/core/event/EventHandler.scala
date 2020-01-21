package gg.warcraft.monolith.api.core.event

trait EventHandler {
  def handle(event: Event): Unit = ()
  def reduce[T >: PreEvent](event: T): T = event
}
