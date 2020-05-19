package gg.warcraft.monolith.api.core.event

object EventService {
  class Wrapper(eventService: EventService) extends EventService {
    private var handlers: List[Event.Handler] = Nil

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

class EventService {
  private var handlers: List[Event.Handler] = Nil

  def publish(event: Event): Unit =
    handlers.foreach { _.handle(event) }

  def publish[T <: PreEvent](event: T): T =
    handlers.foldLeft(event) { (event, handler) => handler.reduce(event) }

  def subscribe(handler: Event.Handler): Unit =
    handlers ::= handler

  def unsubscribe(handler: Event.Handler): Unit =
    handlers = handlers.filter { _ != handler }
}
