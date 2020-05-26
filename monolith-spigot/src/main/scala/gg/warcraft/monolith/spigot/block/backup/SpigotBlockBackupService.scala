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

package gg.warcraft.monolith.spigot.block.backup

import java.util.UUID
import java.util.logging.Logger

import gg.warcraft.monolith.api.block.backup.{BlockBackup, BlockBackupService}
import gg.warcraft.monolith.api.core.Codecs
import gg.warcraft.monolith.api.world.{BlockLocation, World}
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import io.getquill.{SnakeCase, SqliteDialect}
import io.getquill.context.jdbc.JdbcContext
import org.bukkit.Bukkit
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.Plugin

import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}

class SpigotBlockBackupService(
    private implicit val plugin: Plugin,
    private implicit val logger: Logger,
    private implicit val database: JdbcContext[SqliteDialect, SnakeCase],
    private implicit val locationMapper: SpigotLocationMapper
) extends BlockBackupService {
  import database._

  private implicit val executionContext: ExecutionContext =
    ExecutionContext.global
  private implicit val teamDecoder: MappedEncoding[String, World] =
    Codecs.Quill.enumDecoder(World.valueOf)
  private implicit val teamEncoder: MappedEncoding[World, String] =
    Codecs.Quill.enumEncoder[World]
  private implicit val dataInsertMeta: InsertMeta[BlockBackup] =
    insertMeta[BlockBackup]()

  private val cache: mutable.Map[UUID, BlockBackup] = mutable.Map.empty
  private val metaDataKey = getClass.getCanonicalName

  override def createBackup(location: BlockLocation): UUID = {
    val block = locationMapper.map(location).getBlock

    // check for existing backup
    val metaData = block.getMetadata(metaDataKey)
    if (!metaData.isEmpty) {
      val id = metaData.get(0).asString()
      return UUID.fromString(id)
    }

    // create backup
    val id = UUID.randomUUID()
    val data = block.getBlockData.getAsString()
    val backup = BlockBackup(id, data, location)

    // set existing backup
    val metaDataValue = new FixedMetadataValue(plugin, id.toString)
    block.setMetadata(metaDataKey, metaDataValue)

    // save backup
    Future { database.run(query[BlockBackup].insert(lift(backup))) }
    cache.put(id, backup)

    id
  }

  private def restoreBackup(backup: BlockBackup): Boolean = {
    // check it equals existing backup
    val block = locationMapper.map(backup.location).getBlock
    val metaData = block.getMetadata(metaDataKey)
    if (!metaData.isEmpty && metaData.get(0).asString() != backup.id.toString) {
      println(s"Attempted to restore backup for block with different meta data")
      return false
    }

    // restore backup
    val blockState = block.getState
    val blockData = Bukkit.createBlockData(backup.data)
    blockState.setBlockData(blockData)
    blockState.update(true, false)

    // delete backup
    block.removeMetadata(metaDataKey, plugin)
    Future {
      database.run(query[BlockBackup].filter(_.id == lift(backup.id)).delete)
    }
    cache.remove(backup.id)

    true
  }

  override def restoreBackup(id: UUID): Boolean = cache.get(id) match {
    case Some(it) => restoreBackup(it)
    case None     => false
  }

  override def restoreBackups(): Unit = {
    database.run(query[BlockBackup]).foreach(restoreBackup)
    cache.clear
  }
}
