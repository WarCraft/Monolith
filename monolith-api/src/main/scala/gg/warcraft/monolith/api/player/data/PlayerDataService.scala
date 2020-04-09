package gg.warcraft.monolith.api.player.data

import java.util.UUID
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.Codecs
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import gg.warcraft.monolith.api.player.statistic.StatisticService
import gg.warcraft.monolith.api.util.Ops._
import io.getquill.{SnakeCase, SqliteDialect}
import io.getquill.context.jdbc.JdbcContext

import scala.concurrent.{ExecutionContext, Future}
import scala.util.chaining._

class PlayerDataService(
    implicit logger: Logger,
    database: JdbcContext[SqliteDialect, SnakeCase],
    statisticService: StatisticService,
    teamService: TeamService
) {
  import database._

  private implicit val executionContext: ExecutionContext =
    ExecutionContext.global
  private implicit val teamDecoder: MappedEncoding[String, Option[Team]] =
    Codecs.Quill.teamDecoder
  private implicit val teamEncoder: MappedEncoding[Team, String] =
    Codecs.Quill.teamEncoder
  private implicit val dataInsertMeta: InsertMeta[PlayerData] =
    insertMeta[PlayerData]()

  private var _data: Map[UUID, PlayerData] = Map.empty

  def getPlayerData(id: UUID): Future[PlayerData] = {
    _data get id match {
      case Some(playerData) => playerData |> Future.successful
      case None =>
        Future {
          run {
            query[PlayerData] filter { _.id == lift(id) }
          }.headOption match {
            case Some(playerData) => playerData.asInstanceOf[PlayerData]
            case None             => PlayerData(id)
          }
        }
    }
  }

  def setPlayerData(data: PlayerData): Unit = {
    _data += (data.id -> data)
    Future {
      run {
        query[PlayerData]
          .insert { lift(data) }
          .onConflictUpdate(_.id)(
            (it, _) => it.team -> it.team,
            (it, _) => it.timeConnected -> it.timeConnected,
            (it, _) => it.timeLastSeen -> it.timeLastSeen
          )
      }
    }
    // TODO if team has changed fire event
  }

  def updatePlayerData(id: UUID): Unit = {
    val data = _data(id)
    val oldLastSeen = data.timeLastSeen
    val newLastSeen = System.currentTimeMillis

    statisticService increaseStatistic (id, newLastSeen - oldLastSeen, "TimePlayed")
    this setPlayerData data.copy(timeLastSeen = newLastSeen)
  }

  def loadPlayerData(id: UUID): Option[PlayerData] =
    run { query[PlayerData].filter { _.id == lift(id) } }.headOption
      .tap {
        case Some(it) => _data += (it.id -> it)
        case None     =>
      }

  def invalidatePlayerData(id: UUID): Unit =
    _data -= id
}
