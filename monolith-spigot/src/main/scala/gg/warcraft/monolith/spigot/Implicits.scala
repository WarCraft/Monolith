package gg.warcraft.monolith.spigot

import java.io.File
import java.util.Properties
import java.util.logging.Logger

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.typesafe.config.{Config, ConfigFactory}
import gg.warcraft.monolith.api.core.{
  AuthorizationService, JsonMapper2, TaskService, YamlMapper2
}
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.entity.player.service.{
  PlayerCommandService, PlayerQueryService
}
import gg.warcraft.monolith.api.entity.service.{
  EntityCommandService, EntityQueryService
}
import gg.warcraft.monolith.api.entity.status.StatusService
import gg.warcraft.monolith.spigot.combat.SpigotCombatEventMapper
import gg.warcraft.monolith.spigot.core.{
  SpigotAuthorizationService, SpigotCommandService, SpigotTaskService
}
import gg.warcraft.monolith.spigot.entity.SpigotEntityEventMapper
import gg.warcraft.monolith.spigot.menu.{
  SpigotMenuEventMapper, SpigotMenuMapper, SpigotMenuService
}
import gg.warcraft.monolith.spigot.player.SpigotPlayerEventMapper
import gg.warcraft.monolith.spigot.world._
import gg.warcraft.monolith.spigot.world.block._
import gg.warcraft.monolith.spigot.world.block.backup.SpigotBlockBackupService
import gg.warcraft.monolith.spigot.world.item._
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

  // TODO remove
  implicit var entityQueryService: EntityQueryService = _
  def setEntityQueryService(service: EntityQueryService): Unit =
    this.entityQueryService = service
  implicit var entityCommandService: EntityCommandService = _
  def setEntityCommandService(service: EntityCommandService): Unit =
    this.entityCommandService = service
  implicit var playerQueryService: PlayerQueryService = _
  def setPlayerQueryService(service: PlayerQueryService): Unit =
    this.playerQueryService = service
  implicit var playerCommandService: PlayerCommandService = _
  def setPlayerCommandService(service: PlayerCommandService): Unit =
    this.playerCommandService = service

  implicit var server: Server = _

  implicit lazy val authService: AuthorizationService =
    new SpigotAuthorizationService(server)
  implicit lazy val eventService: EventService =
    new EventService
  implicit lazy val taskService: TaskService =
    new SpigotTaskService(plugin)

  // World mappers
  implicit lazy val worldMapper: SpigotWorldMapper =
    new SpigotWorldMapper
  implicit lazy val locationMapper: SpigotLocationMapper =
    new SpigotLocationMapper
  implicit lazy val soundMapper: SpigotSoundMapper =
    new SpigotSoundMapper // TODO rewrite to scala class
  implicit lazy val worldEventMapper: SpigotWorldEventMapper =
    new SpigotWorldEventMapper

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

  // Entity mappers
  implicit lazy val entityEventMapper: SpigotEntityEventMapper =
    new SpigotEntityEventMapper
  implicit lazy val playerEventMapper: SpigotPlayerEventMapper =
    new SpigotPlayerEventMapper

  // Item mappers
  implicit lazy val itemTypeMapper: SpigotItemTypeMapper =
    new SpigotItemTypeMapper
  implicit lazy val itemVariantMapper: SpigotItemVariantMapper =
    new SpigotItemVariantMapper
  implicit lazy val itemMapper: SpigotItemMapper =
    new SpigotItemMapper
  implicit lazy val itemEventMapper: SpigotItemEventMapper =
    new SpigotItemEventMapper

  // Other services
  implicit lazy val commandService: SpigotCommandService =
    new SpigotCommandService
  implicit lazy val blockBackupService: SpigotBlockBackupService =
    new SpigotBlockBackupService
  implicit lazy val itemService: SpigotItemService =
    new SpigotItemService
  implicit lazy val statusService: StatusService =
    new StatusService
  implicit lazy val worldService: SpigotWorldService =
    new SpigotWorldService

  // Menu mappers
  implicit lazy val menuMapper: SpigotMenuMapper =
    new SpigotMenuMapper(server, itemService, itemMapper) // TODO cleanup
  implicit lazy val menuService: SpigotMenuService =
    new SpigotMenuService(server, taskService, menuMapper) // TODO cleanup
  implicit lazy val menuEventMapper: SpigotMenuEventMapper =
    new SpigotMenuEventMapper
}
