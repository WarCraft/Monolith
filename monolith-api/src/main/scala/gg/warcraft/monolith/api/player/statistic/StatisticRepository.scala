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

import java.util.UUID

import com.typesafe.config.Config
import gg.warcraft.monolith.api.core.Codecs.Quill.teamDecoder
import io.getquill._
import io.getquill.context.Context
import io.getquill.idiom.Idiom

import scala.concurrent.{ExecutionContext, Future}
import scala.util.chaining.scalaUtilChainingOps

trait StatisticRepository {
  def load(id: UUID): Statistics

  def save(statistics: Statistic*)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit]
}

private trait StatisticContext[I <: Idiom, N <: NamingStrategy] {
  this: Context[I, N] =>

  def loadStatistics = quote {
    (q: Query[Statistic], id: UUID) => q.filter { _.playerId == lift(id) }
  }

  def upsertStatistic = quote {
    (q: EntityQuery[Statistic], statistic: Statistic) =>
      q.insert { statistic }
        .onConflictUpdate(_.playerId, _.statistic) {
          (t, e) => t.value -> (t.value + e.value)
        }
  }
}

private[monolith] class PostgresStatisticRepository(
    config: Config
) extends StatisticRepository {
  private val databaseContext = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with StatisticContext[PostgresDialect, SnakeCase]
  import databaseContext._

  override def load(id: UUID): Statistics =
    run { loadStatistics(query[Statistic], id) }
      .iterator.map { it => it.statistic -> it }
      .toMap.pipe { new Statistics(_) }

  override def save(statistics: Statistic*)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] = Future {
    run {
      liftQuery(statistics).foreach { statistic =>
        upsertStatistic(query[Statistic], statistic)
      }
    }
  }
}

private[monolith] class SqliteStatisticRepository(
    config: Config
) extends StatisticRepository {
  private val context = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with StatisticContext[SqliteDialect, SnakeCase]
  import context._

  override def load(id: UUID): Statistics =
    run { loadStatistics(query[Statistic], id) }
      .iterator.map { it => it.statistic -> it }
      .toMap.pipe { new Statistics(_) }

  override def save(statistics: Statistic*)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] = Future {
    run {
      liftQuery(statistics).foreach { statistic =>
        upsertStatistic(query[Statistic], statistic)
      }
    }
  }
}
