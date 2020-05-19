package gg.warcraft.monolith.api.core.event

object Event {
  trait Handler {
    def handle(event: Event): Unit = ()
    def reduce[T <: PreEvent](event: T): T = event
  }
}

/** Event is a utility class that prevents accidental passing of objects to the
  * EventService that were not meant to be used as an event. */
abstract class Event

/** Pre- and cancellable events replace the Minecraft event priority system by
  * allowing handlers to mutate, cancel, or explicitly allow a pre-event. They are
  * fired before their corresponding read-only events and cancellable events can be
  * cancelled just like Paper events. However, pre-events that have been explicitly
  * allowed will not cancel the native Minecraft event they represent regardless of
  * whether it was also cancelled. This allows plugins to exert ownership of a
  * certain gameplay concept and overrule other plugins.
  */
abstract class PreEvent

/** Pre- and cancellable events replace the Minecraft event priority system by
  * allowing handlers to mutate, cancel, or explicitly allow a pre-event. They are
  * fired before their corresponding read-only events and cancellable events can be
  * cancelled just like Paper events. However, pre-events that have been explicitly
  * allowed will not cancel the native Minecraft event they represent regardless of
  * whether it was also cancelled. This allows plugins to exert ownership of a
  * certain gameplay concept and overrule other plugins.
  */
abstract class CancellableEvent extends PreEvent {
  val cancelled: Boolean
  val explicitlyAllowed: Boolean
  val allowed: Boolean = explicitlyAllowed || !cancelled
}
