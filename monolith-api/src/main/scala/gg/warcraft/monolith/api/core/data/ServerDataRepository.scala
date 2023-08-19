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
import gg.warcraft.monolith.api.util.codecs.quill._
import gg.warcraft.monolith.api.util.future.FutureOps
import io.getquill._
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom

import java.util.logging.Logger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ServerDataRepository {
  def load: Option[ServerData]
  def save(data: ServerData): Future[Unit]
}

private trait ServerDataContext[I <: SqlIdiom, N <: NamingStrategy] {
  this: JdbcContext[I, N] =>

  def queryAll = quote {
    (q: EntityQuery[ServerData]) => q
  }

  def upsert = quote {
    (q: EntityQuery[ServerData], data: ServerData) => q.updateValue(data)
  }
}

private[monolith] class PostgresServerDataRepository(
    config: Config
)(implicit
    logger: Logger
) extends ServerDataRepository {
  private val database = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with ServerDataContext[PostgresDialect, SnakeCase]
  import database._

  override def load: Option[ServerData] =
    run { queryAll(query[ServerData]) }.headOption

  override def save(data: ServerData) = Future[Unit] {
    run { upsert(query[ServerData], lift(data)) }
  }.andThenLog("ServerDataRepository", "upsert", data)
}

private[monolith] class SqliteServerDataRepository(
    config: Config
)(implicit
    logger: Logger
) extends ServerDataRepository {
  private val database = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with ServerDataContext[SqliteDialect, SnakeCase]
  import database._

  override def load: Option[ServerData] =
    run { queryAll(query[ServerData]) }.headOption

  override def save(data: ServerData) = Future[Unit] {
    run { upsert(query[ServerData], lift(data)) }
  }.andThenLog("ServerDataRepository", "upsert", data)
}
