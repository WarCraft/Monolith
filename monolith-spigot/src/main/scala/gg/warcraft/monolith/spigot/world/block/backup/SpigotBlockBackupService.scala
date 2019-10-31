package gg.warcraft.monolith.spigot.world.block.backup

import java.util.UUID
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.PluginLogger
import gg.warcraft.monolith.api.world.block.backup.{ BlockBackup, BlockBackupService }
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import javax.inject.Inject
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.Plugin
import org.bukkit.Bukkit

class SpigotBlockBackupService @Inject()(
    plugin: Plugin, @PluginLogger pluginLogger: Logger,
    locationMapper: SpigotLocationMapper,
) extends BlockBackupService {

  override def createBackup(location: BlockLocation): UUID = {
    val block = locationMapper.map(location).getBlock

    // check for existing backup
    val metaData = block.getMetadata(SpigotBlockBackupService.metaDataKey)
    if (!metaData.isEmpty) {
      val id = metaData.get(0).asString()
      return UUID.fromString(id)
    }

    // create backup
    val id     = UUID.randomUUID()
    val data   = block.getBlockData.getAsString()
    val backup = BlockBackup(id, data, location)

    // set existing backup
    val metaDataValue = new FixedMetadataValue(plugin, id.toString)
    block.setMetadata(SpigotBlockBackupService.metaDataKey, metaDataValue)

    // save backup
    SpigotBlockBackupService.backups += (id -> backup)
    val json = backup.asJson.noSpaces // TODO persist
    id
  }

  override def restoreBackup(id: UUID): Boolean = {
    val backup: BlockBackup = SpigotBlockBackupService.backups(id)
    if (backup == null) {
      println(s"Attempted to restore nonexistent block backup $id")
      return false
    }

    // check it equals existing backup
    val block    = locationMapper.map(backup.location).getBlock
    val metaData = block.getMetadata(SpigotBlockBackupService.metaDataKey)
    if (!metaData.isEmpty && metaData.get(0).asString() != backup.id.toString) {
      println(s"Attempted to restore block backup for block with different meta data")
      return false
    }

    // restore backup
    val blockState = block.getState
    val blockData  = Bukkit.createBlockData(backup.data)
    blockState.setBlockData(blockData)
    blockState.update(true, false)

    // delete backup
    block.removeMetadata(SpigotBlockBackupService.metaDataKey, plugin)
    SpigotBlockBackupService.backups -= id
    // TODO delete from persistence
    true
  }
  override def restoreBackups(): Unit = {
    // TODO get from persistence
    val keep   = ""
    val backup = decode[BlockBackup](keep)
  }
}

private object SpigotBlockBackupService {
  private final val metaDataKey = getClass.getCanonicalName
  private final var backups     = Map[UUID, BlockBackup]()
}
