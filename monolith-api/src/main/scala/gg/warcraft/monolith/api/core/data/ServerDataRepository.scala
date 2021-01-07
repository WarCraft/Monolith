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

package gg.warcraft.monolith.api.core.data

import com.typesafe.config.Config
import io.getquill._
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom

trait ServerDataRepository {
  def load(data: String): Option[String]
  def save(data: String, value: String): Unit
  def delete(data: String): Unit
}

private trait ServerDataContext[I <: SqlIdiom, N <: NamingStrategy] {
  this: JdbcContext[I, N] =>

  def queryData = quote {
    (q: EntityQuery[ServerData], data: String) => q.filter { _.data == data }
  }

  def upsertData = quote {
    (q: EntityQuery[ServerData], data: ServerData) =>
      q.insert(data).onConflictUpdate(_.data)((t, e) => t.value -> e.value)
  }

  def deleteData = quote {
    (q: EntityQuery[ServerData], data: String) => q.filter { _.data == data }.delete
  }
}

private[monolith] class PostgresServerDataRepository(
    config: Config
) extends ServerDataRepository {
  private val database = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with ServerDataContext[PostgresDialect, SnakeCase]
  import database._

  override def load(data: String): Option[String] =
    run { queryData(query[ServerData], lift(data)) }.headOption.map { _.value }

  override def save(data: String, value: String): Unit = {
    val serverData = ServerData(data, value)
    run { upsertData(query[ServerData], lift(serverData)) }
  }

  override def delete(data: String): Unit =
    run { deleteData(query[ServerData], lift(data)) }
}

private[monolith] class SqliteServerDataRepository(
    config: Config
) extends ServerDataRepository {
  private val database = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with ServerDataContext[SqliteDialect, SnakeCase]
  import database._

  override def load(data: String): Option[String] =
    run { queryData(query[ServerData], lift(data)) }.headOption.map { _.value }

  override def save(data: String, value: String): Unit = {
    val serverData = ServerData(data, value)
    run { upsertData(query[ServerData], lift(serverData)) }
  }

  override def delete(data: String): Unit =
    run { deleteData(query[ServerData], lift(data)) }
}
