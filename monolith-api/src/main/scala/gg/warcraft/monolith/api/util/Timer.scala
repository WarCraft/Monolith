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
    _minutesRemaining = duration.inSeconds / SECONDS_PER_MINUTE
    _secondsRemaining = duration.inSeconds - _minutesRemaining * SECONDS_PER_MINUTE
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
