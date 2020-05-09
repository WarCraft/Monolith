package gg.warcraft.monolith.api.entity.status

import gg.warcraft.monolith.api.combat.CombatSource
import gg.warcraft.monolith.api.core.event.{Event, PreEvent}
import gg.warcraft.monolith.api.core.Duration
import gg.warcraft.monolith.api.util.chaining._

object Status {
  trait Effect extends Event.Handler {
    val source: CombatSource
    val name: String
    val group: String
    val duration: Option[Duration]
  }
}

case class Status(effects: Set[Status.Effect] = Set.empty) extends Event.Handler {
  override def handle(event: Event): Unit =
    effects foreach { _.handle(event) }

  override def reduce[T <: PreEvent](event: T): T =
    effects.foldLeft(event) { _ |> _.reduce }
}
