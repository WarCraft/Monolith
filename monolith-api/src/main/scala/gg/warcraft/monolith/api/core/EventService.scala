package gg.warcraft.monolith.api.core

import scala.collection.mutable

private object EventService {
  private val handlers = mutable.ListBuffer.empty[EventHandler]
}

class EventService {
  import EventService.handlers

  def publish(event: Event): Unit =
    handlers.foreach(_.handle(event))

  def publish[T <: PreEvent](event: T): T =
    handlers.foldLeft(event)((it, handler) => handler.reduce(it))

  def subscribe(handler: EventHandler): Unit =
    handlers.addOne(handler)

  def unsubscribe(handler: EventHandler): Unit =
    handlers.subtractOne(handler)
}
