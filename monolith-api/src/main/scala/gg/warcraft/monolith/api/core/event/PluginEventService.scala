package gg.warcraft.monolith.api.core.event

class PluginEventService(eventService: EventService) extends EventService {
  private var handlers: List[EventHandler] = Nil

  override def publish(event: Event): Unit =
    eventService.publish(event)

  override def publish[T <: PreEvent](event: T): T =
    eventService.publish(event)

  override def subscribe(handler: EventHandler): Unit = {
    handlers ::= handler
    eventService.subscribe(handler)
  }

  override def unsubscribe(handler: EventHandler): Unit = {
    handlers = handlers filter { _ != handler }
    eventService.unsubscribe(handler)
  }

  def unsubscribeAll(): Unit = {
    handlers foreach eventService.unsubscribe
    handlers = Nil
  }
}
