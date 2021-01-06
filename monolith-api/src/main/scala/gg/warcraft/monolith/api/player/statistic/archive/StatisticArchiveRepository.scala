package gg.warcraft.monolith.api.player.statistic.archive

import com.typesafe.config.Config
import io.getquill._
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait StatisticArchiveRepository {
  def save(statistics: List[StatisticArchive]): Future[Unit]
}

private trait StatisticArchiveContext[I <: SqlIdiom, N <: NamingStrategy] {
  this: JdbcContext[I, N] =>

  def insertStatistic = quote {
    (q: EntityQuery[StatisticArchive], statistic: StatisticArchive) =>
      q.insert(statistic)
  }
}

private[monolith] class PostgresStatisticArchiveRepository(
    config: Config
) extends StatisticArchiveRepository {
  private val database = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with StatisticArchiveContext[PostgresDialect, SnakeCase]
  import database._

  override def save(statistics: List[StatisticArchive]): Future[Unit] = Future {
    run {
      liftQuery(statistics).foreach { it =>
        insertStatistic(query[StatisticArchive], it)
      }
    }
  }
}

private[monolith] class SqliteStatisticArchiveRepository(
    config: Config
) extends StatisticArchiveRepository {
  private val database = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with StatisticArchiveContext[SqliteDialect, SnakeCase]
  import database._

  override def save(statistics: List[StatisticArchive]): Future[Unit] = Future {
    run {
      liftQuery(statistics).foreach { it =>
        insertStatistic(query[StatisticArchive], it)
      }
    }
  }
}
