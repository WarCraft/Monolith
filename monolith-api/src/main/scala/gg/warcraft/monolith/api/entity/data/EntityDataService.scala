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
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.Codecs
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import io.getquill.{SnakeCase, SqliteDialect}
import io.getquill.context.jdbc.JdbcContext

import scala.concurrent.{ExecutionContext, Future}

class EntityDataService(implicit
    logger: Logger,
    context: ExecutionContext,
    database: JdbcContext[SqliteDialect, SnakeCase],
    eventService: EventService,
    teamService: TeamService
) {
  import database._

  private implicit val teamDecoder: MappedEncoding[String, Option[Team]] =
    Codecs.Quill.teamDecoder
  private implicit val teamEncoder: MappedEncoding[Team, String] =
    Codecs.Quill.teamEncoder

  private var _data: Map[UUID, EntityData] = Map.empty

  // initialize entity data, as players are stored separately all entities are
  // expected to not spawn often or to be short lived and not clog the data map.
  run { query[EntityData] }.foreach { it => _data += (it.id -> it) }

  def data: Map[UUID, EntityData] = _data

  private[entity] def setEntityData(data: EntityData): Unit = {
    _data += (data.id -> data)
    Future {
      run {
        query[EntityData]
          .insert { lift(data) }
          .onConflictUpdate(_.id) { (t, e) => t.team -> e.team }
      }
    }
  }

  private[entity] def deleteEntityData(id: UUID): Unit = {
    _data -= id
    Future { run { query[EntityData].filter { _.id == lift(id) }.delete } }
  }
}
