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

import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.core.{DailyTickEvent, MonolithConfig}
import gg.warcraft.monolith.api.util.types._
import gg.warcraft.monolith.api.util.types.tags._
import shapeless.tag

import java.time.{LocalDate, LocalDateTime, ZoneId}
import java.util.logging.Logger

class ServerDataService(implicit
    logger: Logger,
    repository: ServerDataRepository,
    eventService: EventService
) {
  private final val ERR_MISSING_SERVER_DATA =
    "Server data is missing from the database! " +
      "Last daily tick will fall back to today. " +
      "If this is the first time Monolith is running this is normal."

  private var _data: ServerData = _
  private[data] def data: ServerData = _data

  private var _serverTimeZone: ZoneId = _
  def serverTimeZone: ZoneId = _serverTimeZone

  def serverTime: MonolithDateTime =
    tag[MonolithDateTimeTag][LocalDateTime](LocalDateTime.now(serverTimeZone))
  def serverDate: MonolithDate =
    tag[MonolithDateTag][LocalDate](LocalDate.now(serverTimeZone))

  def readConfig(config: MonolithConfig): Unit = {
    _serverTimeZone = config.serverTimeZone

    repository.load match {
      case Some(data) => _data = data
      case None =>
        logger.severe(ERR_MISSING_SERVER_DATA)
        _data = ServerData(serverDate)
    }
  }

  private[data] def tickDay(): Unit = {
    val today = serverDate
    if (today.isAfter(_data.lastDailyTick)) {
      val yesterday = _data.lastDailyTick

      _data = _data.copy(lastDailyTick = today)
      repository.save(_data)

      eventService << DailyTickEvent(today, yesterday)
    }
  }
}
