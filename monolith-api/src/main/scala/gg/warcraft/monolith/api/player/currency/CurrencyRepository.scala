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

package gg.warcraft.monolith.api.player.currency

import com.typesafe.config.Config
import io.getquill._
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}
import scala.util.chaining.scalaUtilChainingOps

trait CurrencyRepository {
  def load(id: UUID): Currencies

  def save(Currencies: Currency*)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit]
}

private trait CurrencyContext[I <: SqlIdiom, N <: NamingStrategy] {
  this: JdbcContext[I, N] =>

  def loadCurrencies = quote {
    (q: Query[Currency], id: UUID) => q.filter { _.playerId == id }
  }

  def upsertCurrency = quote {
    (q: EntityQuery[Currency], currency: Currency) =>
      q.insert(currency)
        .onConflictUpdate(_.playerId, _.currency)(
          (t, e) => t.amount -> (t.amount + e.amount),
          (t, e) => t.lifetime -> (t.lifetime + e.lifetime)
        )
  }
}

private[monolith] class PostgresCurrencyRepository(
    config: Config
) extends CurrencyRepository {
  private val database = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with CurrencyContext[PostgresDialect, SnakeCase]
  import database._

  override def load(id: UUID): Currencies =
    run { loadCurrencies(query[Currency], lift(id)) }
      .iterator.map { it => it.currency -> it }
      .toMap.pipe { new Currencies(_) }

  override def save(Currencies: Currency*)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] = Future {
    run {
      liftQuery(Currencies).foreach { it => upsertCurrency(query[Currency], it) }
    }
  }
}

private[monolith] class SqliteCurrencyRepository(
    config: Config
) extends CurrencyRepository {
  private val database = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with CurrencyContext[SqliteDialect, SnakeCase]
  import database._

  override def load(id: UUID): Currencies =
    run { loadCurrencies(query[Currency], lift(id)) }
      .iterator.map { it => it.currency -> it }
      .toMap.pipe { new Currencies(_) }

  override def save(Currencies: Currency*)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] = Future {
    run {
      liftQuery(Currencies).foreach { it => upsertCurrency(query[Currency], it) }
    }
  }
}
