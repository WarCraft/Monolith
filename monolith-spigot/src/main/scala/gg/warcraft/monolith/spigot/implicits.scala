package gg.warcraft.monolith.spigot

import java.util.logging.Logger

import gg.warcraft.monolith.api.block.backup.BlockBackupService
import gg.warcraft.monolith.api.block.build.BlockBuildService
import gg.warcraft.monolith.api.core.auth.AuthService
import gg.warcraft.monolith.api.core.data.ServerDataService
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.entity.attribute.AttributeService
import gg.warcraft.monolith.api.entity.data.EntityDataService
import gg.warcraft.monolith.api.entity.status.StatusService
import gg.warcraft.monolith.api.entity.EntityService
import gg.warcraft.monolith.api.entity.team.TeamService
import gg.warcraft.monolith.api.player.currency.CurrencyService
import gg.warcraft.monolith.api.player.data.PlayerDataService
import gg.warcraft.monolith.api.player.statistic.StatisticService
import gg.warcraft.monolith.api.player.PlayerService
import gg.warcraft.monolith.api.player.hiding.PlayerHidingService
import gg.warcraft.monolith.api.world.portal.PortalService
import gg.warcraft.monolith.spigot.block._
import gg.warcraft.monolith.spigot.block.backup.SpigotBlockBackupService
import gg.warcraft.monolith.spigot.combat.{
  SpigotCombatEventMapper, SpigotPotionMapper
}
import gg.warcraft.monolith.spigot.core.command.SpigotCommandService
import gg.warcraft.monolith.spigot.entity.{
  SpigotEntityEventMapper, SpigotEntityService, SpigotEntityTypeMapper
}
import gg.warcraft.monolith.spigot.entity.attribute.{
  SpigotAttributeMapper, SpigotAttributeService
}
import gg.warcraft.monolith.spigot.item._
import gg.warcraft.monolith.spigot.math.{SpigotAABBfMapper, SpigotVectorMapper}
import gg.warcraft.monolith.spigot.menu.{
  SpigotButtonMapper, SpigotMenuMapper, SpigotMenuService
}
import gg.warcraft.monolith.spigot.player.{
  SpigotGameModeMapper, SpigotPlayerEventMapper, SpigotPlayerService
}
import gg.warcraft.monolith.spigot.player.hiding.SpigotPlayerHidingService
import gg.warcraft.monolith.spigot.world._
import io.getquill.{SnakeCase, SqliteDialect}
import io.getquill.context.jdbc.JdbcContext
import org.bukkit.Server
import org.bukkit.plugin.Plugin

object implicits {
  private[spigot] type DatabaseContext = JdbcContext[SqliteDialect, SnakeCase]

  private var _server: Server = _
  private var _plugin: Plugin = _
  private var _logger: Logger = _

  private var _database: DatabaseContext = _
  private var _eventService: EventService = _
  private var _taskService: TaskService = _

  private[spigot] def init(
      eventService: EventService = new EventService
  )(implicit
      server: Server,
      plugin: Plugin,
      logger: Logger,
      database: DatabaseContext,
      taskService: TaskService
  ): Unit = {
    _server = server
    _plugin = plugin
    _logger = logger
    _database = database
    _eventService = eventService
    _taskService = taskService
  }

  private[spigot] def monolithEventService: EventService = eventService

  // Spigot
  private implicit lazy val server: Server = _server
  private implicit lazy val plugin: Plugin = _plugin
  private implicit lazy val logger: Logger = _logger

  // Core
  private implicit lazy val database: DatabaseContext = _database
  private implicit lazy val eventService: EventService = _eventService
  private implicit lazy val taskService: TaskService = _taskService

  implicit lazy val authService: AuthService =
    new AuthService
  implicit lazy val commandService: SpigotCommandService =
    new SpigotCommandService
  implicit lazy val serverDataService: ServerDataService =
    new ServerDataService

  // Math
  implicit lazy val boundingBoxMapper: SpigotAABBfMapper =
    new SpigotAABBfMapper
  implicit lazy val vectorMapper: SpigotVectorMapper =
    new SpigotVectorMapper

  // Block
  implicit lazy val blockBackupService: BlockBackupService =
    new SpigotBlockBackupService
  implicit lazy val blockBuildService: BlockBuildService =
    new BlockBuildService

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
  implicit lazy val blockStateMapper: SpigotBlockStateMapper =
    new SpigotBlockStateMapper
  implicit lazy val blockTypeMapper: SpigotBlockTypeMapper =
    new SpigotBlockTypeMapper
  implicit lazy val blockVariantMapper: SpigotBlockVariantMapper =
    new SpigotBlockVariantMapper
  implicit lazy val blockMapper: SpigotBlockMapper =
    new SpigotBlockMapper
  implicit lazy val blockEventMapper: SpigotBlockEventMapper =
    new SpigotBlockEventMapper

  // Combat
  implicit lazy val combatEventMapper: SpigotCombatEventMapper =
    new SpigotCombatEventMapper
  implicit lazy val potionMapper: SpigotPotionMapper =
    new SpigotPotionMapper

  // Entity
  implicit lazy val attributeService: AttributeService =
    new SpigotAttributeService
  implicit lazy val statusService: StatusService =
    new StatusService
  implicit lazy val teamService: TeamService =
    new TeamService
  implicit lazy val entityService: EntityService =
    new SpigotEntityService
  implicit lazy val entityDataService: EntityDataService =
    new EntityDataService

  implicit lazy val attributeMapper: SpigotAttributeMapper =
    new SpigotAttributeMapper
  implicit lazy val entityTypeMapper: SpigotEntityTypeMapper =
    new SpigotEntityTypeMapper
  implicit lazy val entityEventMapper: SpigotEntityEventMapper =
    new SpigotEntityEventMapper

  // Player
  implicit lazy val currencyService: CurrencyService =
    new CurrencyService
  implicit lazy val statisticService: StatisticService =
    new StatisticService
  implicit lazy val playerService: PlayerService =
    new SpigotPlayerService
  implicit lazy val playerDataService: PlayerDataService =
    new PlayerDataService
  implicit lazy val playerHidingService: PlayerHidingService =
    new SpigotPlayerHidingService

  implicit lazy val gameModeMapper: SpigotGameModeMapper =
    new SpigotGameModeMapper
  implicit lazy val playerEventMapper: SpigotPlayerEventMapper =
    new SpigotPlayerEventMapper

  // Item
  implicit lazy val itemService: SpigotItemService =
    new SpigotItemService

  implicit lazy val itemTypeMapper: SpigotItemTypeMapper =
    new SpigotItemTypeMapper
  implicit lazy val itemVariantMapper: SpigotItemVariantMapper =
    new SpigotItemVariantMapper
  implicit lazy val itemMapper: SpigotItemMapper =
    new SpigotItemMapper
  implicit lazy val itemEventMapper: SpigotItemEventMapper =
    new SpigotItemEventMapper

  // Menu
  implicit lazy val menuService: SpigotMenuService =
    new SpigotMenuService

  implicit lazy val buttonMapper: SpigotButtonMapper =
    new SpigotButtonMapper
  implicit lazy val menuMapper: SpigotMenuMapper =
    new SpigotMenuMapper

  // World
  implicit lazy val portalService: PortalService =
    new PortalService
  implicit lazy val worldService: SpigotWorldService =
    new SpigotWorldService

  implicit lazy val locationMapper: SpigotLocationMapper =
    new SpigotLocationMapper
  implicit lazy val soundMapper: SpigotSoundMapper =
    new SpigotSoundMapper
  implicit lazy val worldMapper: SpigotWorldMapper =
    new SpigotWorldMapper
  implicit lazy val worldEventMapper: SpigotWorldEventMapper =
    new SpigotWorldEventMapper
}
