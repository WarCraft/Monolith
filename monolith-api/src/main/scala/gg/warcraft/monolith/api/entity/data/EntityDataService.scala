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
          .onConflictUpdate(_.id)((_1, _2) => _1.team -> _2.team)
      }
    }
  }

  private[entity] def deleteEntityData(id: UUID): Unit = {
    _data -= id
    Future { run { query[EntityData].filter { _.id == lift(id) }.delete } }
  }
}
