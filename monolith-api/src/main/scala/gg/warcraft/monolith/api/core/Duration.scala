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

import gg.warcraft.monolith.api.core.Duration._
import gg.warcraft.monolith.api.util.TimeUtils

object Duration {
  @inline implicit final class DurationOps(private val self: Int) extends AnyVal {
    def millis: Duration = {
      val clippedMillis = Math.max(0, self)
      new Duration(
        clippedMillis,
        clippedMillis / TimeUtils.MILLIS_PER_TICK,
        clippedMillis / TimeUtils.MILLIS_PER_SECOND
      )
    }

    def ticks: Duration = {
      val clippedTicks = Math.max(0, self)
      new Duration(
        clippedTicks * TimeUtils.MILLIS_PER_TICK,
        clippedTicks,
        clippedTicks / TimeUtils.TICKS_PER_SECOND
      )
    }

    def seconds: Duration = {
      val clippedSeconds = Math.max(0, self)
      new Duration(
        clippedSeconds * TimeUtils.MILLIS_PER_SECOND,
        clippedSeconds * TimeUtils.TICKS_PER_SECOND,
        clippedSeconds
      )
    }
  }
}

class Duration private (
    val millis: Int,
    val ticks: Int,
    val seconds: Int
) {
  def +(duration: Duration): Duration =
    (millis + duration.millis).millis

  def -(duration: Duration): Duration =
    (millis - duration.millis).millis
}
