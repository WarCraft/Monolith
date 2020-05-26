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

object TimeUtils {
  final val SECONDS_PER_MINUTE = 60
  final val SECONDS_PER_HOUR = 60 * SECONDS_PER_MINUTE
  final val SECONDS_PER_DAY = 24 * SECONDS_PER_HOUR

  final val TICKS_PER_SECOND = 20
  final val TICKS_PER_MINUTE = 60 * TICKS_PER_SECOND
  final val TICKS_PER_HOUR = 60 * TICKS_PER_MINUTE
  final val TICKS_PER_DAY = 24 * TICKS_PER_HOUR

  final val MILLIS_PER_TICK = 50
  final val MILLIS_PER_SECOND = 1000
  final val MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND
  final val MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE
  final val MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR

  def asReadable(age: Long): String = {
    if (age < MILLIS_PER_MINUTE) "less than a minute"
    else if (age < MILLIS_PER_HOUR) {
      val count = age / MILLIS_PER_MINUTE;
      count + (if (count != 1) " minutes" else " minute")
    } else if (age < MILLIS_PER_DAY) {
      val count = age / MILLIS_PER_HOUR;
      count + (if (count != 1) " hours" else " hour")
    } else {
      val count = age / MILLIS_PER_DAY;
      count + (if (count != 1) " days" else " day")
    }
  }

  def timeElapsedSince(unixTimestamp: Long): String =
    asReadable(System.currentTimeMillis - unixTimestamp)

  def timeUntil(unixTimestamp: Long): String =
    asReadable(unixTimestamp - System.currentTimeMillis)

  def asDigitalDisplay(duration: Duration): String = {
    val hours = duration.seconds / SECONDS_PER_HOUR
    val secondsMinusHours = duration.seconds % SECONDS_PER_HOUR
    val minutes = secondsMinusHours / SECONDS_PER_MINUTE
    val seconds = secondsMinusHours % SECONDS_PER_MINUTE

    var display = ""
    if (hours != 0) {
      if (hours < 10) display += "0";
      display += hours + ":";
    }
    if (minutes < 10) display += "0";
    display += minutes + ":";
    if (seconds < 10) display += "0";
    display + seconds
  }
}
