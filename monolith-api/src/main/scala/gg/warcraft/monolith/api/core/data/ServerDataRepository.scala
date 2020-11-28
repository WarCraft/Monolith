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

import com.typesafe.config.Config
import io.getquill._
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom

trait ServerDataRepository {
  def lastDailyTick: Long
  def lastDailyTick_=(value: Long): Unit
}

private trait ServerDataContext[I <: SqlIdiom, N <: NamingStrategy] {
  this: JdbcContext[I, N] =>

  final val last_daily_tick = "last_daily_tick"

  def queryLastDailyTick = quote {
    (q: Query[ServerData]) => q.filter { _.data == lift(last_daily_tick) }
  }

  def upsertLastDailyTick = quote {
    (q: EntityQuery[ServerData], data: ServerData) =>
      q.insert(data).onConflictUpdate(_.data)((t, e) => t.intValue -> e.intValue)
  }
}

private[monolith] class PostgresServerDataRepository(
    config: Config
) extends ServerDataRepository {
  private val context = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with ServerDataContext[PostgresDialect, SnakeCase]
  import context._

  override def lastDailyTick: Long =
    run { queryLastDailyTick(query[ServerData]) }
      .headOption.flatMap { _.intValue }.getOrElse(-1L)

  override def lastDailyTick_=(value: Long): Unit = {
    val data = ServerData(last_daily_tick, intValue = Some(value))
    run { upsertLastDailyTick(query[ServerData], lift(data)) }
  }
}

private[monolith] class SqliteServerDataRepository(
    config: Config
) extends ServerDataRepository {
  private val context = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with ServerDataContext[SqliteDialect, SnakeCase]
  import context._

  override def lastDailyTick: Long =
    run { queryLastDailyTick(query[ServerData]) }
      .headOption.flatMap { _.intValue }.getOrElse(-1L)

  override def lastDailyTick_=(value: Long): Unit = {
    val data = ServerData(last_daily_tick, intValue = Some(value))
    run { upsertLastDailyTick(query[ServerData], lift(data)) }
  }
}
