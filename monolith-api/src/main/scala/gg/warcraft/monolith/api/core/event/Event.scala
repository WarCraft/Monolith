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

object Event {
  trait Handler {
    def handle(event: Event): Unit = {}

    def reduce[T <: PreEvent](event: T): T = event
    def handle(event: PreEvent): Unit = {}
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
