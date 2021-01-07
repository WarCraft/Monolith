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

import gg.warcraft.monolith.api.block.backup.{
  BlockBackup, BlockBackupRepository, BlockBackupService
}
import gg.warcraft.monolith.api.world.{BlockLocation, WorldService}
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.Bukkit
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.Plugin

import java.util.logging.Logger
import scala.collection.mutable

class SpigotBlockBackupService(implicit
    plugin: Plugin,
    logger: Logger,
    repository: BlockBackupRepository,
    locationMapper: SpigotLocationMapper,
    worldService: WorldService
) extends BlockBackupService {
  private val cache: mutable.Map[Int, Option[BlockBackup]] = mutable.LinkedHashMap()
  private val metaDataKey = getClass.getCanonicalName

  // TODO allow creating multiple backups at once for db call grouping
  override def createBackup(location: BlockLocation): Int = {
    val block = locationMapper.map(location).getBlock

    // get existing backup
    val metaData = block.getMetadata(metaDataKey)
    val existingBackup =
      if (metaData.isEmpty) None
      else cache(metaData.get(0).asInt())

    // create backup
    val id = cache.size
    val data = existingBackup match {
      case Some(backup) => backup.data
      case None         => block.getBlockData.getAsString()
    }
    val backup = BlockBackup(id, location, data)

    // set existing backup
    val metaDataValue = new FixedMetadataValue(plugin, id)
    block.setMetadata(metaDataKey, metaDataValue)

    // save backup
    repository.save(backup)
    cache += backup.id -> Some(backup)

    id
  }

  override def createBackups(locations: Seq[BlockLocation]): Range.Inclusive = {
    val ids = locations.map(createBackup)
    ids.head to (ids.head + ids.size)
  }

  override def restoreBackup(id: Int): Unit = {
    cache(id).foreach(restoreBackup)
    repository.delete(id)
    cache -= id
  }

  override def restoreBackups(ids: Range.Inclusive): Unit = {
    ids.flatMap(cache).foreach(restoreBackup)
    repository.deleteRange(ids)
    cache --= ids
  }

  override def restoreAllBackups(): Unit = {
    repository.all().foreach(restoreBackup)
    cache.clear
  }

  private def restoreBackup(backup: BlockBackup): Unit = {
    val block = locationMapper.map(backup.location).getBlock
    val blockData = Bukkit.createBlockData(backup.data)
    val blockState = block.getState
    blockState.setBlockData(blockData)
    blockState.update(true, false)
    block.removeMetadata(metaDataKey, plugin)
  }
}
