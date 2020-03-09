package gg.warcraft.monolith.api.core.event

private object EventService {
  private var handlers: List[EventHandler] = Nil
}

class EventService {
  import EventService.handlers

  def publish(event: Event): Unit =
    handlers foreach { _.handle(event) }

  def publish[T <: PreEvent](event: T): T =
    handlers.foldLeft(event) { (it, handler) =>
      handler.reduce(it)
    }

  def subscribe(handler: EventHandler): Unit =
    handlers ::= handler

  def unsubscribe(handler: EventHandler): Unit =
    handlers = handlers filter { _ != handler }
}
