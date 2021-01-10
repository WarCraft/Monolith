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

package gg.warcraft.monolith.api.core

import gg.warcraft.monolith.api.core.event.Event
import gg.warcraft.monolith.api.util.types.MonolithDate

/** A DailyTickEvent is fired exactly once every day on the first server tick of that
  * day. It's purpose is to provide a simple mechanism to listen to to kick off tasks
  * that should run once a day.
  * If the server was taken offline before 12AM and restarted at 2AM this event will
  * fire once at 2AM and then 12AM every subsequent day for as long as the server is
  * online.
  * If the server has been offline for more than a day this event is only fired for
  * the current day when the server is restarted and will not retroactively fire for
  * any days where the server was offline for 24 hours.
  */
case class DailyTickEvent(
    today: MonolithDate,
    yesterday: MonolithDate
) extends Event

/** The ServerShutdownEvent is a utility event that allows subscribers to perform
  * shutdown tasks without having to be notified from their own plugin's onDisable
  * method. This event is fired synchronously and the server WILL shut down at the
  * end of this tick.
  */
case class ServerShutdownEvent() extends Event
