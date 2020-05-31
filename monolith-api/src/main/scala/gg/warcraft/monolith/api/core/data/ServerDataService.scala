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

import java.time.{LocalDate, ZoneId}
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.types.DatabaseContext
import gg.warcraft.monolith.api.core.MonolithConfig

import scala.concurrent.{ExecutionContext, Future}

class ServerDataService(implicit
    logger: Logger,
    context: ExecutionContext,
    database: DatabaseContext
) {
  import database._

  private final val ERR_LAST_DAILY_TICK =
    "Failed to retrieve last daily tick from database, falling back to today!"

  private var _lastDailyTick: LocalDate = _
  private var _serverTimeZone: ZoneId = _

  def lastDailyTick: LocalDate = _lastDailyTick
  def serverTimeZone: ZoneId = _serverTimeZone

  def readConfig(config: MonolithConfig): Unit = {
    _serverTimeZone = config.serverTimeZone

    _lastDailyTick = {
      val epochDay = database.run {
        query[ServerData].filter { _.data == "last_daily_tick" }
      }.headOption.flatMap { _.intValue }.getOrElse(-1L)
      if (epochDay <= 0) {
        if (epochDay < 0) logger.severe(ERR_LAST_DAILY_TICK)
        LocalDate.now(serverTimeZone)
      } else LocalDate.ofEpochDay(epochDay)
    }
  }

  def updateLastDailyTick(): Future[Unit] = Future {
    _lastDailyTick = LocalDate.now(serverTimeZone)
    val data = ServerData(
      "last_daily_tick",
      intValue = Some(_lastDailyTick.toEpochDay)
    )
    database.run {
      query[ServerData]
        .insert { lift(data) }
        .onConflictUpdate(_.data)((_t, _e) => _t.intValue -> _e.intValue)
    }
  }
}
