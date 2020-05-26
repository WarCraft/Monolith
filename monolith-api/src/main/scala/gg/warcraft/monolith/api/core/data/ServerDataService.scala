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

import java.time.{Instant, LocalDate, ZoneOffset}

import io.getquill.{SnakeCase, SqliteDialect}
import io.getquill.context.jdbc.JdbcContext

import scala.concurrent.{ExecutionContext, Future}

class ServerDataService(implicit
    database: JdbcContext[SqliteDialect, SnakeCase]
) {
  import database._

  private implicit val executionContext: ExecutionContext =
    ExecutionContext.global
  private implicit val dataInsertMeta: InsertMeta[ServerData] =
    insertMeta[ServerData]()

  private val _lastDailyTick: LocalDate = {
    val epoch = database.run {
      query[ServerData].filter { _.data == "last_daily_tick" }
    }.headOption.map { _.intValue }.getOrElse(0L)
    val instant = if (epoch > 0) Instant.ofEpochMilli(epoch) else Instant.now()
    instant.atOffset(ZoneOffset.UTC).toLocalDate
  }

  def lastDailyTick: LocalDate = _lastDailyTick

  def updateLastDailyTick(): Future[Unit] = Future {
    val epoch = Instant.now().atOffset(ZoneOffset.UTC).toEpochSecond * 1000
    val data = ServerData("last_daily_tick", intValue = epoch)
    database.run {
      query[ServerData]
        .insert { lift(data) }
        .onConflictUpdate(_.data)((_1, _2) => _1.intValue -> _2.intValue)
    }
  }
}
