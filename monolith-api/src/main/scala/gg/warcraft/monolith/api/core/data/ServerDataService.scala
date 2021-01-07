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

package gg.warcraft.monolith.api.core.data

import gg.warcraft.monolith.api.core.MonolithConfig

import java.time.{LocalDate, LocalDateTime, ZoneId}
import java.util.logging.Logger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ServerDataService(implicit
    logger: Logger,
    repository: ServerDataRepository
) {
  private final val last_daily_tick = "last_daily_tick"
  private final val ERR_LAST_DAILY_TICK =
    "Failed to retrieve last daily tick from database, falling back to today!"

  private var _lastDailyTick: LocalDate = _
  def lastDailyTick: LocalDate = _lastDailyTick

  private var _serverTimeZone: ZoneId = _
  def serverTimeZone: ZoneId = _serverTimeZone
  def serverTime: LocalDateTime = LocalDateTime.now(serverTimeZone)

  private var _today: LocalDate = _
  def today: LocalDate = _today

  def readConfig(config: MonolithConfig): Unit = {
    _serverTimeZone = config.serverTimeZone
    _today = LocalDate.now(_serverTimeZone)

    _lastDailyTick = {
      val epochDay = repository.load(last_daily_tick).map { _.toLong }.getOrElse(0L)
      if (epochDay <= 0) {
        if (epochDay < 0) logger.severe(ERR_LAST_DAILY_TICK)
        LocalDate.now(_serverTimeZone)
      } else LocalDate.ofEpochDay(epochDay)
    }
  }

  def updateLastDailyTick(): Future[Unit] = Future {
    _today = _lastDailyTick

    _lastDailyTick = LocalDate.now(_serverTimeZone)
    repository.save(last_daily_tick, _lastDailyTick.toEpochDay.toString)
  }

  def load(data: String): Option[String] =
    repository.load(data)

  def save(data: String, value: String): Unit =
    repository.save(data, value)

  def delete(data: String): Unit =
    repository.delete(data)
}
