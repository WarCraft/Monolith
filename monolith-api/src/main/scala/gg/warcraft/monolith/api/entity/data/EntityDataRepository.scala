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

package gg.warcraft.monolith.api.entity.data

import java.util.UUID

import com.typesafe.config.Config
import gg.warcraft.monolith.api.core.Codecs.Quill.teamDecoder
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import io.getquill._
import io.getquill.context.Context
import io.getquill.idiom.Idiom

import scala.concurrent.{ExecutionContext, Future}

trait EntityDataRepository {
  def all(implicit teamService: TeamService): Map[UUID, EntityData]

  def save(data: EntityData)(implicit
      teamService: TeamService,
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit]

  def delete(id: UUID)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit]
}

private trait EntityDataContext[I <: Idiom, N <: NamingStrategy] {
  this: Context[I, N] =>

  def queryAllData(implicit teamService: TeamService) = {
    implicit val decoder: MappedEncoding[String, Team] = teamService.decoder
    quote { (q: Query[EntityData]) => q }
  }

  def upsertData(implicit teamService: TeamService) = {
    implicit val encoder: MappedEncoding[Team, String] = teamService.encoder
    quote {
      (q: EntityQuery[EntityData], data: EntityData) =>
        q.insert { lift(data) }.onConflictUpdate(_.id) { (t, e) => t.team -> e.team }
    }
  }

  def deleteData = quote {
    (q: EntityQuery[EntityData], id: UUID) => q.filter { _.id == lift(id) }.delete
  }
}

private[monolith] class PostgresEntityDataRepository(
    config: Config
) extends EntityDataRepository {
  private val databaseContext = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with EntityDataContext[PostgresDialect, SnakeCase]
  import databaseContext._

  override def all(implicit teamService: TeamService): Map[UUID, EntityData] =
    run { queryAllData(teamService)(query[EntityData]) }
      .map { it => it.id -> it }.toMap

  override def save(data: EntityData)(implicit
      teamService: TeamService,
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] =
    Future { run { upsertData(teamService)(query[EntityData], data) } }

  override def delete(id: UUID)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] = Future { run { deleteData(id) } }
}

private[monolith] class SqliteEntityDataRepository(
    config: Config
) extends EntityDataRepository {
  private val context = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with EntityDataContext[SqliteDialect, SnakeCase]
  import context._

  override def all(implicit teamService: TeamService): Map[UUID, EntityData] =
    run { queryAllData(teamService)(query[EntityData]) }
      .map { it => it.id -> it }.toMap

  override def save(data: EntityData)(implicit
      teamService: TeamService,
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] =
    Future { run { upsertData(teamService)(query[EntityData], data) } }

  override def delete(id: UUID)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] = Future { run { deleteData(id) } }
}
