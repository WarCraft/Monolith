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
import gg.warcraft.monolith.api.core.Codecs.Quill._
import io.getquill._
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom

import java.util.UUID
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.chaining.scalaUtilChainingOps

trait StatisticRepository {
  def load(id: UUID): Statistics

  def save(statistics: Statistic*): Future[Unit]

  // TODO think of better way to extend monolith repos
  def queryTop(count: Int, like: String): Future[List[Statistic]]

  def queryAll(like: String): Future[List[Statistic]]

  def deleteAll(like: String): Future[Unit]
}

trait StatisticRepositoryContext[I <: SqlIdiom, N <: NamingStrategy] {
  this: JdbcContext[I, N] =>

  def loadStatistics = quote {
    (q: Query[Statistic], id: UUID) => q.filter { _.playerId == id }
  }

  def upsertStatistic = quote {
    (q: EntityQuery[Statistic], statistic: Statistic) =>
      q.insert(statistic)
        .onConflictUpdate(_.playerId, _.statistic) {
          (t, e) => t.value -> (t.value + e.value)
        }
  }

  def queryTopLike = quote {
    (q: Query[Statistic], count: Int, like: String) =>
      q.filter { _.statistic.like(like) }.sortBy { _.value }(Ord.desc).take(count)
  }

  def queryLike = quote {
    (q: EntityQuery[Statistic], like: String) => q.filter { _.statistic.like(like) }
  }

  def deleteLike = quote {
    (q: EntityQuery[Statistic], like: String) =>
      q.filter { _.statistic.like(like) }.delete
  }
}

class PostgresStatisticRepository(
    config: Config
) extends StatisticRepository {
  private val database = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with StatisticRepositoryContext[PostgresDialect, SnakeCase]
  import database._

  override def load(id: UUID): Statistics =
    run { loadStatistics(query[Statistic], lift(id)) }
      .iterator.map { it => it.statistic -> it }
      .toMap.pipe { new Statistics(_) }

  override def save(statistics: Statistic*): Future[Unit] = Future {
    run {
      liftQuery(statistics).foreach { it => upsertStatistic(query[Statistic], it) }
    }
  }

  override def queryTop(count: Int, like: String): Future[List[Statistic]] =
    Future { run { queryTopLike(query[Statistic], lift(count), lift(like)) } }

  override def queryAll(like: String): Future[List[Statistic]] =
    Future { run { queryLike(query[Statistic], lift(like)) } }

  override def deleteAll(like: String): Future[Unit] =
    Future { run { deleteLike(query[Statistic], lift(like)) } }
}

class SqliteStatisticRepository(
    config: Config
) extends StatisticRepository {
  private val database = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with StatisticRepositoryContext[SqliteDialect, SnakeCase]
  import database._

  override def load(id: UUID): Statistics =
    run { loadStatistics(query[Statistic], lift(id)) }
      .iterator.map { it => it.statistic -> it }
      .toMap.pipe { new Statistics(_) }

  override def save(statistics: Statistic*): Future[Unit] = Future {
    run {
      liftQuery(statistics).foreach { it => upsertStatistic(query[Statistic], it) }
    }
  }

  override def queryTop(count: Int, like: String): Future[List[Statistic]] =
    Future { run { queryTopLike(query[Statistic], lift(count), lift(like)) } }

  override def queryAll(like: String): Future[List[Statistic]] =
    Future { run { queryLike(query[Statistic], lift(like)) } }

  override def deleteAll(like: String): Future[Unit] =
    Future { run { deleteLike(query[Statistic], lift(like)) } }
}
