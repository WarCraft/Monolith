package gg.warcraft.monolith.api.player.statistic.archive

import com.typesafe.config.Config
import gg.warcraft.monolith.api.util.codecs.monolith._
import gg.warcraft.monolith.api.util.codecs.quill._
import gg.warcraft.monolith.api.util.future.FutureOps
import io.getquill._
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom

import java.util.logging.Logger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait StatisticArchiveRepository {
  def save(archives: List[StatisticArchive]): Future[Unit]
}

private trait StatisticArchiveContext[I <: SqlIdiom, N <: NamingStrategy] {
  this: JdbcContext[I, N] =>

  def insert = quote {
    (q: EntityQuery[StatisticArchive], statistic: StatisticArchive) =>
      q.insert(statistic)
  }
}

private[monolith] class PostgresStatisticArchiveRepository(
    config: Config
)(implicit
    logger: Logger
) extends StatisticArchiveRepository {
  private val database = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with StatisticArchiveContext[PostgresDialect, SnakeCase]
  import database._

  override def save(archives: List[StatisticArchive]) = Future[Unit] {
    run { liftQuery(archives).foreach { it => insert(query[StatisticArchive], it) } }
  }.andThenLog("StatisticArchiveRepository", "insert", archives)
}

private[monolith] class SqliteStatisticArchiveRepository(
    config: Config
)(implicit
    logger: Logger
) extends StatisticArchiveRepository {
  private val database = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with StatisticArchiveContext[SqliteDialect, SnakeCase]
  import database._

  override def save(archives: List[StatisticArchive]) = Future[Unit] {
    run { liftQuery(archives).foreach { it => insert(query[StatisticArchive], it) } }
  }.andThenLog("StatisticArchiveRepository", "insert", archives)
}
