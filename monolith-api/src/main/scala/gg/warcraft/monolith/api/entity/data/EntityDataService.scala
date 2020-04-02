package gg.warcraft.monolith.api.entity.data

import java.util.UUID
import java.util.logging.Logger

import io.getquill.context.jdbc.JdbcContext

import scala.concurrent.{ExecutionContext, Future}

class EntityDataService(
    implicit logger: Logger,
    database: JdbcContext[_, _]
) {
  private implicit final val context: ExecutionContext = ExecutionContext.global
  import database._

  private var _data: Map[UUID, EntityData] = Map.empty

  // initialize entity data, as players are stored separately all entities are
  // expected to not spawn often or to be short lived and not clog the data map.
  run { query[EntityData] }.foreach { it => _data += (it.id -> it) }

  def getEntityData(id: UUID): Option[EntityData] =
    _data get id

  def setEntityData(data: EntityData): Unit = {
    _data += (data.id -> data)
    Future {
      run {
        query[EntityData]
          .insert { data }
          .onConflictUpdate(_.id)((it, _) => it.team -> it.team)
      }
    }
    // TODO if team has changed fire event
  }

  def deleteEntityData(id: UUID): Unit = {
    _data -= id
    Future { run { query[EntityData].filter { _.id == lift(id) }.delete } }
  }
}
