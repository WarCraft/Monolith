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

package gg.warcraft.monolith.api.player.data

import com.typesafe.config.Config
import gg.warcraft.monolith.api.entity.team.TeamService
import gg.warcraft.monolith.api.util.codecs.monolith._
import gg.warcraft.monolith.api.util.codecs.quill._
import gg.warcraft.monolith.api.util.future.FutureOps
import io.getquill._
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom

import java.util.UUID
import java.util.logging.Logger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait PlayerDataRepository {
  def load(id: UUID)(implicit
      teamService: TeamService
  ): Option[PlayerData]

  def save(data: PlayerData): Future[Unit]
}

private trait PlayerDataContext[I <: SqlIdiom, N <: NamingStrategy] {
  this: JdbcContext[I, N] =>

  def queryById = quote {
    (q: Query[PlayerData], id: UUID) => q.filter { _.id == id }
  }

  def upsert = quote {
    (q: EntityQuery[PlayerData], data: PlayerData) =>
      q.insert(data).onConflictUpdate(_.id)(
        (t, e) => t.team -> e.team,
        (t, e) => t.timeConnected -> e.timeConnected,
        (t, e) => t.timeLastSeen -> e.timeLastSeen
      )
  }
}

private[monolith] class PostgresPlayerDataRepository(
    config: Config
)(implicit
    logger: Logger
) extends PlayerDataRepository {
  private val database = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with PlayerDataContext[PostgresDialect, SnakeCase]
  import database._

  override def load(id: UUID)(implicit
      teamService: TeamService
  ): Option[PlayerData] =
    run { queryById(query[PlayerData], lift(id)) }.headOption

  override def save(data: PlayerData) = Future[Unit] {
    run { upsert(query[PlayerData], lift(data)) }
  }.andThenLog("PlayerDataRepository", "upsert", data)
}

private[monolith] class SqlitePlayerDataRepository(
    config: Config
)(implicit
    logger: Logger
) extends PlayerDataRepository {
  private val database = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with PlayerDataContext[SqliteDialect, SnakeCase]
  import database._

  override def load(id: UUID)(implicit
      teamService: TeamService
  ): Option[PlayerData] =
    run { queryById(query[PlayerData], lift(id)) }.headOption

  override def save(data: PlayerData) = Future[Unit] {
    run { upsert(query[PlayerData], lift(data)) }
  }.andThenLog("PlayerDataRepository", "upsert", data)
}
