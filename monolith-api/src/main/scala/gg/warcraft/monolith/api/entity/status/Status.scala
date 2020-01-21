package gg.warcraft.monolith.api.entity.status

import gg.warcraft.monolith.api.core.event.{Event, EventHandler, PreEvent}

import scala.collection.mutable

class Status extends EventHandler {
  private[status] val effects: mutable.Set[StatusEffect] =
    new mutable.HashSet[StatusEffect]

  override def handle(event: Event): Unit =
    effects.foreach(_.handle(event))

  override def reduce[T >: PreEvent](event: T): T =
    effects.foldLeft(event)((it, effect) => effect.reduce(it))
}
