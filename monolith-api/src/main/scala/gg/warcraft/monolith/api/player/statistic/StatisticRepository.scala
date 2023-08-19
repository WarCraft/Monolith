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

package gg.warcraft.monolith.api.player.statistic

import com.typesafe.config.Config
import gg.warcraft.monolith.api.util.future.FutureOps
import io.getquill._
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom

import java.util.UUID
import java.util.logging.Logger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.chaining.scalaUtilChainingOps

trait StatisticRepository {
  def load(id: UUID): Statistics

  def save(statistic: Statistic): Future[Unit]

  def save(statistics: List[Statistic]): Future[Unit]

  def queryTop(count: Int, statistic: String): Future[List[Statistic]]

  def queryAll(like: String): Future[List[Statistic]]

  private[statistic] def deleteAll(like: String): Future[Unit]
}

private trait StatisticRepositoryContext[I <: SqlIdiom, N <: NamingStrategy] {
  this: JdbcContext[I, N] =>

  def loadById = quote {
    (q: Query[Statistic], id: UUID) => q.filter { _.playerId == id }
  }

  def upsert = quote {
    (q: EntityQuery[Statistic], statistic: Statistic) =>
      q.insertValue(statistic)
        .onConflictUpdate(_.playerId, _.statistic) {
          (t, e) => t.value -> (t.value + e.value)
        }
  }

  def queryTopLike = quote {
    (q: Query[Statistic], count: Int, statistic: String) =>
      q.filter { _.statistic == statistic }.sortBy { _.value }(Ord.desc).take(count)
  }

  def queryAllLike = quote {
    (q: EntityQuery[Statistic], like: String) => q.filter { _.statistic.like(like) }
  }

  def deleteAllLike = quote {
    (q: EntityQuery[Statistic], like: String) =>
      q.filter { _.statistic.like(like) }.delete
  }
}

private[monolith] class PostgresStatisticRepository(
    config: Config
)(implicit
    logger: Logger
) extends StatisticRepository {
  private val database = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with StatisticRepositoryContext[PostgresDialect, SnakeCase]
  import database._

  override def load(id: UUID): Statistics =
    run { loadById(query[Statistic], lift(id)) }
      .iterator.map { it => it.statistic -> it }
      .toMap.pipe { new Statistics(_) }

  override def save(statistic: Statistic) = Future[Unit] {
    run { upsert(query[Statistic], lift(statistic)) }
  }.andThenLog("StatisticRepository", "save", statistic)

  override def save(statistics: List[Statistic]) = Future[Unit] {
    run { liftQuery(statistics).foreach { it => upsert(query[Statistic], it) } }
  }.andThenLog("StatisticRepository", "save", statistics)

  override def queryTop(count: Int, statistic: String) = Future[List[Statistic]] {
    run { queryTopLike(query[Statistic], lift(count), lift(statistic)) }
  }.andThenLog("StatisticRepository", "queryTopLike", count, statistic)

  override def queryAll(like: String) = Future[List[Statistic]] {
    run { queryAllLike(query[Statistic], lift(like)) }
  }.andThenLog("StatisticRepository", "queryAllLike", like)

  override def deleteAll(like: String) = Future[Unit] {
    run { deleteAllLike(query[Statistic], lift(like)) }
  }.andThenLog("StatisticRepository", "deleteAllLike", like)
}

private[monolith] class SqliteStatisticRepository(
    config: Config
)(implicit
    logger: Logger
) extends StatisticRepository {
  private val database = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with StatisticRepositoryContext[SqliteDialect, SnakeCase]
  import database._

  override def load(id: UUID): Statistics =
    run { loadById(query[Statistic], lift(id)) }
      .iterator.map { it => it.statistic -> it }
      .toMap.pipe { new Statistics(_) }

  override def save(statistic: Statistic) = Future[Unit] {
    run { upsert(query[Statistic], lift(statistic)) }
  }.andThenLog("StatisticRepository", "save", statistic)

  override def save(statistics: List[Statistic]) = Future[Unit] {
    run { liftQuery(statistics).foreach { it => upsert(query[Statistic], it) } }
  }.andThenLog("StatisticRepository", "save", statistics)

  override def queryTop(count: Int, statistic: String) = Future[List[Statistic]] {
    run { queryTopLike(query[Statistic], lift(count), lift(statistic)) }
  }.andThenLog("StatisticRepository", "queryTopLike", count, statistic)

  override def queryAll(like: String) = Future[List[Statistic]] {
    run { queryAllLike(query[Statistic], lift(like)) }
  }.andThenLog("StatisticRepository", "queryAllLike", like)

  override def deleteAll(like: String) = Future[Unit] {
    run { deleteAllLike(query[Statistic], lift(like)) }
  }.andThenLog("StatisticRepository", "deleteAllLike", like)
}
