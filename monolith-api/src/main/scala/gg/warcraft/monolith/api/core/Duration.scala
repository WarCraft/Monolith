package gg.warcraft.monolith.api.core

import gg.warcraft.monolith.api.core.Duration._
import gg.warcraft.monolith.api.util.TimeUtils

object Duration {
  @inline implicit final class IntOps(private val self: Int) extends AnyVal {
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
