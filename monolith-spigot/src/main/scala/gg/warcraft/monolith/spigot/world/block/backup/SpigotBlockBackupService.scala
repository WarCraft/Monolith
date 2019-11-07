package gg.warcraft.monolith.spigot.world.block.backup

import java.util.UUID
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.{ PersistenceService, PluginLogger }
import gg.warcraft.monolith.api.world.block.backup.{ BlockBackup, BlockBackupService }
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import gg.warcraft.monolith.spigot.Implicits
import javax.inject.Inject
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.Plugin
import org.bukkit.Bukkit

class SpigotBlockBackupService @Inject() (
    plugin: Plugin,
    persistenceService: PersistenceService,
    @PluginLogger pluginLogger: Logger
) extends BlockBackupService {
  private final val metaDataKey = getClass.getCanonicalName
  private final val persistenceKey = "blockbackup"

  val locationMapper: SpigotLocationMapper = Implicits.locationMapper // TODO undo

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
    SpigotBlockBackupService.backups += (id -> backup)
    // TODO persist
    id
  }

  override def restoreBackup(id: UUID): Boolean = {
    val backup: BlockBackup = SpigotBlockBackupService.backups(id)
    if (backup == null) {
      println(s"Attempted to restore nonexistent block backup $id")
      return false
    }

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
    SpigotBlockBackupService.backups -= id
    // TODO delete from persistence
    true
  }
  override def restoreBackups(): Unit = {
    // TODO get from persistence
    SpigotBlockBackupService.backups.keys.foreach(restoreBackup)
  }
}

private object SpigotBlockBackupService {
  private final var backups = Map[UUID, BlockBackup]()
}
