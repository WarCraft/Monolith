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

package gg.warcraft.monolith.api.block.backup

import com.typesafe.config.Config
import gg.warcraft.monolith.api.world.WorldService
import gg.warcraft.monolith.api.util.codecs.monolith._
import gg.warcraft.monolith.api.util.codecs.quill._
import io.getquill._
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait BlockBackupRepository {
  def all()(implicit
      worldService: WorldService
  ): List[BlockBackup]

  def save(data: BlockBackup): Future[Unit]

  def delete(id: Int): Future[Unit]

  def deleteRange(ids: Range.Inclusive): Future[Unit]
}

private trait BlockBackupContext[I <: SqlIdiom, N <: NamingStrategy] {
  this: JdbcContext[I, N] =>

  def queryAllBackups = quote {
    (q: Query[BlockBackup]) => q
  }

  def insertBackup = quote {
    (q: EntityQuery[BlockBackup], backup: BlockBackup) => q.insert { backup }
  }

  def deleteBackup = quote {
    (q: EntityQuery[BlockBackup], id: Int) => q.filter { _.id == id }.delete
  }

  def deleteBackups = quote {
    (q: EntityQuery[BlockBackup], from: Int, to: Int) =>
      q.filter { it => it.id >= from && it.id <= to }.delete
  }
}

private[monolith] class PostgresBlockBackupRepository(
    config: Config
) extends BlockBackupRepository {
  private val database = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with BlockBackupContext[PostgresDialect, SnakeCase]
  import database._

  override def all()(implicit
      worldService: WorldService
  ): List[BlockBackup] =
    run { queryAllBackups(query[BlockBackup]) }

  override def save(data: BlockBackup): Future[Unit] = Future {
    run { insertBackup(query[BlockBackup], lift(data)) }
  }

  override def delete(id: Int): Future[Unit] = Future {
    run { deleteBackup(query[BlockBackup], lift(id)) }
  }

  override def deleteRange(ids: Range.Inclusive): Future[Unit] = Future {
    run { deleteBackups(query[BlockBackup], lift(ids.start), lift(ids.`end`)) }
  }
}

private[monolith] class SqliteBlockBackupRepository(
    config: Config
) extends BlockBackupRepository {
  private val database = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with BlockBackupContext[SqliteDialect, SnakeCase]
  import database._

  override def all()(implicit
      worldService: WorldService
  ): List[BlockBackup] =
    run { queryAllBackups(query[BlockBackup]) }

  override def save(data: BlockBackup): Future[Unit] = Future {
    run { insertBackup(query[BlockBackup], lift(data)) }
  }

  override def delete(id: Int): Future[Unit] = Future {
    run { deleteBackup(query[BlockBackup], lift(id)) }
  }

  override def deleteRange(ids: Range.Inclusive): Future[Unit] = Future {
    run { deleteBackups(query[BlockBackup], lift(ids.start), lift(ids.`end`)) }
  }
}
