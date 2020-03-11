package gg.warcraft.monolith.api.core.event

class EventService {
  private var handlers: List[Event.Handler] = Nil

  def publish(event: Event): Unit =
    handlers foreach { _.handle(event) }

  def publish[T <: PreEvent](event: T): T =
    handlers.foldLeft(event) { (it, handler) =>
      handler.reduce(it)
    }

  def subscribe(handler: Event.Handler): Unit =
    handlers ::= handler

  def unsubscribe(handler: Event.Handler): Unit =
    handlers = handlers filter { _ != handler }
}
