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

package gg.warcraft.monolith.api.util

import gg.warcraft.monolith.api.core.Duration
import gg.warcraft.monolith.api.util.TimeUtils._

class Timer(duration: Duration, repeat: Boolean = false) {
  private var _minutesRemaining = 0
  private var _secondsRemaining = 0
  reset()

  def secondsRemaining: Int =
    _minutesRemaining * SECONDS_PER_MINUTE + _secondsRemaining

  def reset(): Unit = {
    _minutesRemaining = duration.seconds / SECONDS_PER_MINUTE
    _secondsRemaining = duration.seconds - _minutesRemaining * SECONDS_PER_MINUTE
  }

  def tick(): Unit = {
    _secondsRemaining -= 1
    if (_secondsRemaining < 0) {
      _minutesRemaining -= 1
      if (_minutesRemaining < 0) {
        _minutesRemaining = 0
        _secondsRemaining = 0
        if (repeat) {
          reset()
          tick()
        }
      } else {
        _secondsRemaining = 59
      }
    }
  }

  override def toString: String =
    "%02d:%02d".format(_minutesRemaining, _secondsRemaining)
}
