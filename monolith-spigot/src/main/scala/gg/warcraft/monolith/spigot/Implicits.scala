package gg.warcraft.monolith.spigot

import java.io.File
import java.util.logging.Logger
import java.util.Properties

import com.typesafe.config.{Config, ConfigFactory}
import gg.warcraft.monolith.api.core.EventService
import gg.warcraft.monolith.spigot.combat.SpigotCombatEventMapper
import gg.warcraft.monolith.spigot.world.{
  SpigotLocationMapper, SpigotSoundMapper, SpigotWorldMapper, SpigotWorldService
}
import gg.warcraft.monolith.spigot.world.block._
import gg.warcraft.monolith.spigot.world.block.backup.SpigotBlockBackupService
import gg.warcraft.monolith.spigot.world.item.{
  SpigotItemEventMapper, SpigotItemMapper, SpigotItemService, SpigotItemTypeMapper,
  SpigotItemVariantMapper
}
import org.bukkit.Server
import org.bukkit.plugin.Plugin

object Implicits {
  private implicit var plugin: Plugin = _
  def setPlugin(plugin: Plugin): Unit = this.plugin = plugin

  private implicit var logger: Logger = _
  def setLogger(logger: Logger): Unit = this.logger = logger

  private implicit var dbConfig: Config = _
  def setDatabaseConfig(dataFolderPath: String): Unit = {
    val props = new Properties()
    props.setProperty("driverClassName", "org.sqlite.JDBC")
    props.setProperty(
      "jdbcUrl",
      "jdbc:sqlite:" + dataFolderPath + File.separator + "db.sqlite"
    )
    dbConfig = ConfigFactory.parseProperties(props)
  }

  implicit var server: Server = _

  // Core services
  implicit lazy val eventService: EventService = new EventService

  // World mappers
  implicit lazy val worldMapper: SpigotWorldMapper =
    new SpigotWorldMapper
  implicit lazy val locationMapper: SpigotLocationMapper =
    new SpigotLocationMapper
  implicit lazy val soundMapper: SpigotSoundMapper =
    new SpigotSoundMapper // TODO rewrite to scala class

  // Block mappers
  implicit lazy val blockTypeMapper: SpigotBlockTypeMapper =
    new SpigotBlockTypeMapper
  implicit lazy val blockVariantMapper: SpigotBlockVariantMapper =
    new SpigotBlockVariantMapper
  implicit lazy val blockStateMapper: SpigotBlockStateMapper =
    new SpigotBlockStateMapper
  implicit lazy val blockAttachmentMapper: SpigotBlockAttachmentMapper =
    new SpigotBlockAttachmentMapper
  implicit lazy val blockBisectionMapper: SpigotBlockBisectionMapper =
    new SpigotBlockBisectionMapper
  implicit lazy val blockFaceMapper: SpigotBlockFaceMapper =
    new SpigotBlockFaceMapper
  implicit lazy val blockOrientationMapper: SpigotBlockOrientationMapper =
    new SpigotBlockOrientationMapper
  implicit lazy val blockRotationMapper: SpigotBlockRotationMapper =
    new SpigotBlockRotationMapper
  implicit lazy val blockShapeMapper: SpigotBlockShapeMapper =
    new SpigotBlockShapeMapper
  implicit lazy val blockMapper: SpigotBlockMapper =
    new SpigotBlockMapper
  implicit lazy val blockEventMapper: SpigotBlockEventMapper =
    new SpigotBlockEventMapper

  // Combat mappers
  implicit lazy val combatEventMapper: SpigotCombatEventMapper =
    new SpigotCombatEventMapper

  // Item mappers
  implicit lazy val itemTypeMapper: SpigotItemTypeMapper =
    new SpigotItemTypeMapper
  implicit lazy val itemVariantMapper: SpigotItemVariantMapper =
    new SpigotItemVariantMapper
  implicit lazy val itemMapper: SpigotItemMapper =
    new SpigotItemMapper
  implicit lazy val itemEventMapper: SpigotItemEventMapper =
    new SpigotItemEventMapper

  // Services
  implicit lazy val blockBackupService: SpigotBlockBackupService =
    new SpigotBlockBackupService
  implicit lazy val itemService: SpigotItemService =
    new SpigotItemService
  implicit lazy val worldService: SpigotWorldService =
    new SpigotWorldService
}
