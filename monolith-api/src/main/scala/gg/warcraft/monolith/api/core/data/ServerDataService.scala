package gg.warcraft.monolith.api.core.data

import java.time.{Instant, LocalDate, ZoneOffset}

import io.getquill.{SnakeCase, SqliteDialect}
import io.getquill.context.jdbc.JdbcContext

import scala.concurrent.{ExecutionContext, Future}

class ServerDataService(implicit
    database: JdbcContext[SqliteDialect, SnakeCase]
) {
  import database._

  private implicit val executionContext: ExecutionContext =
    ExecutionContext.global
  private implicit val dataInsertMeta: InsertMeta[ServerData] =
    insertMeta[ServerData]()

  private val _lastDailyTick: LocalDate = {
    val epoch = database.run {
      query[ServerData].filter { _.data == "last_daily_tick" }
    }.headOption.map { _.intValue }.getOrElse(0L)
    val instant = if (epoch > 0) Instant.ofEpochMilli(epoch) else Instant.now()
    instant.atOffset(ZoneOffset.UTC).toLocalDate
  }

  def lastDailyTick: LocalDate = _lastDailyTick

  def updateLastDailyTick(): Future[Unit] = Future {
    val epoch = Instant.now().atOffset(ZoneOffset.UTC).toEpochSecond * 1000
    val data = ServerData("last_daily_tick", intValue = epoch)
    database.run {
      query[ServerData]
        .insert { lift(data) }
        .onConflictUpdate(_.data)((_1, _2) => _1.intValue -> _2.intValue)
    }
  }
}
