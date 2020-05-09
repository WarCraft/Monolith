package gg.warcraft.monolith.api.entity.data

import java.util.UUID
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.Codecs
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.entity.team.{ Team, TeamService }
import gg.warcraft.monolith.api.entity.EntityTeamChangedEvent
import io.getquill.{ SnakeCase, SqliteDialect }
import io.getquill.context.jdbc.JdbcContext

import scala.concurrent.{ ExecutionContext, Future }

class EntityDataService(
    implicit logger: Logger,
    database: JdbcContext[SqliteDialect, SnakeCase],
    eventService: EventService,
    teamService: TeamService
) {
  import database._

  private implicit val executionContext: ExecutionContext =
    ExecutionContext.global
  private implicit val teamDecoder: MappedEncoding[String, Option[Team]] =
    Codecs.Quill.teamDecoder
  private implicit val teamEncoder: MappedEncoding[Team, String] =
    Codecs.Quill.teamEncoder
  private implicit val dataInsertMeta: InsertMeta[EntityData] =
    insertMeta[EntityData]()

  private var _data: Map[UUID, EntityData] = Map.empty

  // initialize entity data, as players are stored separately all entities are
  // expected to not spawn often or to be short lived and not clog the data map.
  run { query[EntityData] }.foreach { it => _data += (it.id -> it) }

  def getEntityData(id: UUID): Option[EntityData] =
    _data get id

  def setEntityData(data: EntityData): Unit = {
    val oldData = _data.get(data.id)

    _data += (data.id -> data)
    Future {
      run {
        query[EntityData]
          .insert { lift(data) }
          .onConflictUpdate(_.id)((_1, _2) => _1.team -> _2.team)
      }
    }

    oldData match {
      case Some(oldData) =>
        if(oldData.team != data.team) { // TODO this logic belongs in a domain service
          val teamChanged = EntityTeamChangedEvent(data.id, oldData.team, data.team)
          eventService publish teamChanged
        }
      case None          =>
    }
  }

  def deleteEntityData(id: UUID): Unit = {
    _data -= id
    Future { run { query[EntityData].filter { _.id == lift(id) }.delete } }
  }
}
