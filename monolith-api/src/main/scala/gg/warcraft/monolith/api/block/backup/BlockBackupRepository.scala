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
import gg.warcraft.monolith.api.core.Codecs.Quill._
import gg.warcraft.monolith.api.world.{World, WorldService}
import io.getquill._
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom

import scala.concurrent.{ExecutionContext, Future}

trait BlockBackupRepository {
  def all: List[BlockBackup]

  def save(data: BlockBackup)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit]

  def delete(id: Int)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit]

  def deleteRange(ids: Range.Inclusive)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit]
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
)(implicit
    worldService: WorldService
) extends BlockBackupRepository {
  private implicit val decoder: MappedEncoding[String, World] = worldDecoder

  private val databaseContext = new PostgresJdbcContext[SnakeCase](SnakeCase, config)
    with BlockBackupContext[PostgresDialect, SnakeCase]
  import databaseContext._

  override def all: List[BlockBackup] =
    run { queryAllBackups(query[BlockBackup]) }

  override def save(data: BlockBackup)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] = Future {
    run { insertBackup(query[BlockBackup], lift(data)) }
  }

  override def delete(id: Int)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] = Future {
    run { deleteBackup(query[BlockBackup], lift(id)) }
  }

  override def deleteRange(ids: Range.Inclusive)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] = Future {
    run { deleteBackups(query[BlockBackup], lift(ids.start), lift(ids.`end`)) }
  }
}

private[monolith] class SqliteBlockBackupRepository(
    config: Config
)(implicit
    worldService: WorldService
) extends BlockBackupRepository {
  private implicit val decoder: MappedEncoding[String, World] = worldDecoder

  private val context = new SqliteJdbcContext[SnakeCase](SnakeCase, config)
    with BlockBackupContext[SqliteDialect, SnakeCase]
  import context._

  override def all: List[BlockBackup] =
    run { queryAllBackups(query[BlockBackup]) }

  override def save(data: BlockBackup)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] = Future {
    run { insertBackup(query[BlockBackup], lift(data)) }
  }

  override def delete(id: Int)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] = Future {
    run { deleteBackup(query[BlockBackup], lift(id)) }
  }

  override def deleteRange(ids: Range.Inclusive)(implicit
      executionContext: ExecutionContext = ExecutionContext.global
  ): Future[Unit] = Future {
    run { deleteBackups(query[BlockBackup], lift(ids.start), lift(ids.`end`)) }
  }
}
