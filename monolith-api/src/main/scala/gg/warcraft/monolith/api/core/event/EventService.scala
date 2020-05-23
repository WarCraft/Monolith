package gg.warcraft.monolith.api.core.event

import scala.util.chaining._

class EventService {
  protected var handlers: List[Event.Handler] = Nil

  def publish(event: Event): Unit = { // TODO add << method?
    println(
      s"PUBLISHING ${event.getClass.getSimpleName} to ${handlers.size} handlers"
    )
    handlers.foreach { _.handle(event) }
  }

  def publish[T <: PreEvent](event: T): T = {
    println(s"REDUCING ${event.getClass.getSimpleName} to ${handlers.size} handlers")
    handlers.foldLeft(event) { (event, handler) => handler.reduce(event) }.tap {
      case it: CancellableEvent if it.allowed => handlers.foreach { _.handle(it) }
      case it                                 => handlers.foreach { _.handle(it) }
    }
  }

  def subscribe(handler: Event.Handler): Unit = {
    println(s"SUBSCRIBING ${handler.getClass.getSimpleName} (${handlers.size})")
    handlers ::= handler
  }

  def unsubscribe(handler: Event.Handler): Unit =
    handlers = handlers.filter { _ != handler }
}

object EventService {
  private[monolith] class Wrapper(eventService: EventService) extends EventService {
    override def publish(event: Event): Unit =
      eventService.publish(event)

    override def publish[T <: PreEvent](event: T): T =
      eventService.publish(event)

    override def subscribe(handler: Event.Handler): Unit = {
      handlers ::= handler
      eventService.subscribe(handler)
    }

    override def unsubscribe(handler: Event.Handler): Unit =
      if (handlers.contains(handler)) {
        eventService.unsubscribe(handler)
        handlers = handlers.filter { _ != handler }
      }

    def unsubscribeAll(): Unit = {
      handlers.foreach { eventService.unsubscribe }
      handlers = Nil
    }
  }
}
