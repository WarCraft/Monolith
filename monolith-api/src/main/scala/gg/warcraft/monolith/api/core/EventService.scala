package gg.warcraft.monolith.api.core

import java.lang.reflect.Method

import scala.collection.mutable

private class EventListener(
    val instance: Any,
    val method: Method
)

private object EventService {
  private val listeners: mutable.Map[Class[_ <: Event], mutable.Set[EventListener]] =
    mutable.Map.empty
}

class EventService {
  import EventService.listeners

  def publish(event: Event): Unit = {
    val eventListeners = listeners.get(event.getClass)
    if (eventListeners.isDefined) eventListeners.get.foreach(listener => {
      listener.method.invoke(listener.instance, event)
    })
  }

  def subscribe(listener: Any): Unit = {
    listener.getClass.getDeclaredMethods.foreach(method => {
      val paramTypes = method.getParameterTypes
      if (paramTypes.size == 1 && paramTypes(0).isAssignableFrom(classOf[Event])) {
        val listener = new EventListener(listener, method)
        listeners.getOrElseUpdate(classOf[Event], mutable.Set.empty).add(listener)
      }
    })
  }

  def unsubscribe(listener: Any): Unit = {
    listener.getClass.getDeclaredMethods.foreach(method => {
      val paramTypes = method.getParameterTypes
      if (paramTypes.size == 1 && paramTypes(0).isAssignableFrom(classOf[Event])) {
        val eventListeners = listeners.get(paramTypes(0))
        if (eventListeners.isDefined) {
          val listenersToRemove = eventListeners.get
            .map(it => if (it.instance == listener) it else null)
            .filter(_ != null)
          eventListeners.get.subtractAll(listenersToRemove)
        }
      }
    })
  }
}
