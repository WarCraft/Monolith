package gg.warcraft.monolith.api.core

import gg.warcraft.monolith.api.util.TimeUtils

object Duration {
  private val MILLIS_PER_TICK_RECIPROCAL = 1f / TimeUtils.MILLIS_PER_TICK
  private val MILLIS_PER_SECOND_RECIPROCAL = 1f / TimeUtils.MILLIS_PER_SECOND
  private val TICKS_PER_SECOND_RECIPROCAL = 1f / TimeUtils.TICKS_PER_SECOND

  val immediately = Duration.ofTicks(0)
  val oneTick = Duration.ofTicks(1)
  val oneMilli = Duration.ofMillis(1)
  val oneSecond = Duration.ofSeconds(1)

  def ofMillis(millis: Int): Duration = {
    val clippedMillis = Math.max(0, millis)
    new Duration(
      clippedMillis,
      (clippedMillis * MILLIS_PER_TICK_RECIPROCAL).toInt,
      (clippedMillis * MILLIS_PER_SECOND_RECIPROCAL).toInt
    )
  }

  def ofTicks(ticks: Int): Duration = {
    val clippedTicks = Math.max(0, ticks)
    new Duration(
      clippedTicks * TimeUtils.MILLIS_PER_TICK,
      clippedTicks,
      (clippedTicks * TICKS_PER_SECOND_RECIPROCAL).toInt
    )
  }

  def ofSeconds(seconds: Int): Duration = {
    val clippedSeconds = Math.max(0, seconds)
    new Duration(
      clippedSeconds * TimeUtils.MILLIS_PER_SECOND,
      clippedSeconds * TimeUtils.TICKS_PER_SECOND,
      clippedSeconds
    )
  }
}

class Duration private (
    val inMillis: Int,
    val inTicks: Int,
    val inSeconds: Int
) {
  def addMillis(millis: Int): Duration =
    Duration.ofMillis(inMillis + millis)

  def addTicks(ticks: Int): Duration =
    Duration.ofTicks(inTicks + ticks)

  def addSeconds(seconds: Int): Duration =
    Duration.ofSeconds(inSeconds + seconds)

  def subMillis(millis: Int): Duration =
    Duration.ofMillis(inMillis - millis)

  def subTicks(ticks: Int): Duration =
    Duration.ofTicks(inTicks - ticks)

  def subSeconds(seconds: Int): Duration =
    Duration.ofSeconds(inSeconds - seconds)
}
