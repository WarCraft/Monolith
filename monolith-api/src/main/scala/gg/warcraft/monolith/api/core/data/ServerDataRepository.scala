package gg.warcraft.monolith.api.core.data

import com.typesafe.config.Config
import io.getquill._
import io.getquill.context.Context
import io.getquill.idiom.Idiom

trait ServerDataRepository {
  def lastDailyTick: Long
  def lastDailyTick_=(value: Long): Unit
}

private trait ServerDataContext[I <: Idiom, N <: NamingStrategy] {
  this: Context[I, N] =>

  final val last_daily_tick = "last_daily_tick"

  def queryLastDailyTick = quote {
    (q: Query[ServerData]) => q.filter { _.data == last_daily_tick }
  }

  def upsertLastDailyTick = quote {
    (q: EntityQuery[ServerData], data: ServerData) =>
      q.insert { lift(data) }
        .onConflictUpdate(_.data)((t, e) => t.intValue -> e.intValue)
  }
}

private[monolith] class PostgresServerDataRepository(
    config: Config
) extends ServerDataRepository {
  private val context = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with ServerDataContext[PostgresDialect, SnakeCase]
  import context._

  override def lastDailyTick: Long =
    run { queryLastDailyTick(query[ServerData]) }
      .headOption.flatMap { _.intValue }.getOrElse(-1L)

  override def lastDailyTick_=(value: Long): Unit = {
    val data = ServerData(last_daily_tick, intValue = Some(value))
    upsertLastDailyTick(data)
  }
}

private[monolith] class SqliteServerDataRepository(
    config: Config
) extends ServerDataRepository {
  private val context = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with ServerDataContext[SqliteDialect, SnakeCase]
  import context._

  override def lastDailyTick: Long =
    run { queryLastDailyTick(query[ServerData]) }
      .headOption.flatMap { _.intValue }.getOrElse(-1L)

  override def lastDailyTick_=(value: Long): Unit = {
    val data = ServerData(last_daily_tick, intValue = Some(value))
    upsertLastDailyTick(data)
  }
}
