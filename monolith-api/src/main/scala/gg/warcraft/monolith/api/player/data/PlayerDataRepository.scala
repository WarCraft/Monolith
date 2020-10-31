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

import java.util.UUID

import com.typesafe.config.Config
import gg.warcraft.monolith.api.core.Codecs.Quill.teamDecoder
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import io.getquill._
import io.getquill.context.Context
import io.getquill.idiom.Idiom

import scala.concurrent.{ExecutionContext, Future}

trait PlayerDataRepository {
  def load(id: UUID)(implicit teamService: TeamService): Option[PlayerData]

  def save(data: PlayerData)(implicit
      teamService: TeamService,
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit]
}

private trait PlayerDataContext[I <: Idiom, N <: NamingStrategy] {
  this: Context[I, N] =>

  def loadData(implicit teamService: TeamService) = {
    implicit val decoder: MappedEncoding[String, Team] = teamService.decoder
    quote { (q: Query[PlayerData], id: UUID) => q.filter { _.id == lift(id) } }
  }

  def upsertData(implicit teamService: TeamService) = {
    implicit val encoder: MappedEncoding[Team, String] = teamService.encoder
    quote {
      (q: EntityQuery[PlayerData], data: PlayerData) =>
        q.insert { lift(data) }.onConflictUpdate(_.id)(
          (t, e) => t.team -> e.team,
          (t, e) => t.timeConnected -> e.timeConnected,
          (t, e) => t.timeLastSeen -> e.timeLastSeen
        )
    }
  }
}

private[monolith] class PostgresPlayerDataRepository(
    config: Config
) extends PlayerDataRepository {
  private val databaseContext = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with PlayerDataContext[PostgresDialect, SnakeCase]
  import databaseContext._

  override def load(id: UUID)(implicit
      teamService: TeamService
  ): Option[PlayerData] =
    run { loadData(teamService)(query[PlayerData], id) }.headOption

  override def save(data: PlayerData)(implicit
      teamService: TeamService,
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] =
    Future { run { upsertData(teamService)(query[PlayerData], data) } }
}

private[monolith] class SqlitePlayerDataRepository(
    config: Config
) extends PlayerDataRepository {
  private val context = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with PlayerDataContext[SqliteDialect, SnakeCase]
  import context._

  override def load(id: UUID)(implicit
      teamService: TeamService
  ): Option[PlayerData] =
    run { loadData(teamService)(query[PlayerData], id) }.headOption

  override def save(data: PlayerData)(implicit
      teamService: TeamService,
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] =
    Future { run { upsertData(teamService)(query[PlayerData], data) } }
}
