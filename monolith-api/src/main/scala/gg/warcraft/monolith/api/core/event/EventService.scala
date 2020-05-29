/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.api.core.event

import java.util.logging.Logger

import scala.util.chaining._
import scala.util.Try

class EventService(implicit logger: Logger) {
  protected var handlers: List[Event.Handler] = Nil

  def publish(event: Event): Unit = { // TODO add << method?
    logEvent("Publishing", event)
    handlers.foreach { handler =>
      Try(handler.handle(event)).toEither match {
        case Left(throwable) => logFailure("handling", handler, event, throwable)
        case _               =>
      }
    }
  }

  def publish[T <: PreEvent](event: T): T = {
    logEvent("Reducing", event)
    handlers.foldLeft(event) { (event, handler) =>
      Try(handler.reduce(event))
        .recover(throwable => {
          logFailure("reducing", handler, event, throwable)
          event
        }).get
    }.tap {
      case event: CancellableEvent if !event.allowed => // event is cancelled
      case event => handlers.foreach { handler =>
          Try(handler.handle(event)).toEither match {
            case Left(throwable) => logFailure("handling", handler, event, throwable)
            case _               =>
          }
        }
    }
  }

  def subscribe(handler: Event.Handler): Unit = {
    println(s"SUBSCRIBING ${handler.getClass.getSimpleName} (${handlers.size})")
    handlers ::= handler
  }

  def unsubscribe(handler: Event.Handler): Unit =
    handlers = handlers.filter { _ != handler }

  private def logEvent(action: String, event: Any): Unit = {
    val name = event.getClass.getSimpleName
    if (!name.contains("Chunk")) logger.info(s"$action $name")
  }

  private def logFailure(
      action: String,
      handler: Any,
      event: Any,
      throwable: Throwable
  ): Unit = {
    val name = handler.getClass.getSimpleName
    logger.severe(s"$name blew up $action $event!")
    throwable.printStackTrace()
  }
}

object EventService {
  private[monolith] class Wrapper(eventService: EventService)(implicit
      logger: Logger
  ) extends EventService {
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
