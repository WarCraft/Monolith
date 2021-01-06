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

package gg.warcraft.monolith.spigot

import gg.warcraft.monolith.api.block.BlockService
import gg.warcraft.monolith.api.block.backup.{
  BlockBackupRepository, BlockBackupService
}
import gg.warcraft.monolith.api.block.build.BlockBuildService
import gg.warcraft.monolith.api.block.spoofing.BlockSpoofingService
import gg.warcraft.monolith.api.core.MonolithConfig
import gg.warcraft.monolith.api.core.auth.AuthService
import gg.warcraft.monolith.api.core.command.CommandService
import gg.warcraft.monolith.api.core.data.{ServerDataRepository, ServerDataService}
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.effect.ParticleAdapter
import gg.warcraft.monolith.api.entity.EntityService
import gg.warcraft.monolith.api.entity.attribute.AttributeService
import gg.warcraft.monolith.api.entity.data.{EntityDataRepository, EntityDataService}
import gg.warcraft.monolith.api.entity.status.StatusService
import gg.warcraft.monolith.api.entity.team.TeamService
import gg.warcraft.monolith.api.player.PlayerService
import gg.warcraft.monolith.api.player.currency.{CurrencyRepository, CurrencyService}
import gg.warcraft.monolith.api.player.data.{PlayerDataRepository, PlayerDataService}
import gg.warcraft.monolith.api.player.hiding.PlayerHidingService
import gg.warcraft.monolith.api.player.statistic.archive.{
  StatisticArchiveRepository, StatisticArchiveService
}
import gg.warcraft.monolith.api.player.statistic.{
  StatisticRepository, StatisticService
}
import gg.warcraft.monolith.api.world.portal.PortalService
import gg.warcraft.monolith.spigot.block._
import gg.warcraft.monolith.spigot.block.backup.SpigotBlockBackupService
import gg.warcraft.monolith.spigot.block.spoofing.SpigotBlockSpoofingService
import gg.warcraft.monolith.spigot.combat.{
  SpigotCombatEventMapper, SpigotPotionMapper
}
import gg.warcraft.monolith.spigot.effect.{
  SpigotParticleAdapter, SpigotParticleMapper
}
import gg.warcraft.monolith.spigot.entity.attribute.{
  SpigotAttributeMapper, SpigotAttributeService
}
import gg.warcraft.monolith.spigot.entity.{
  SpigotEntityEventMapper, SpigotEntityService, SpigotEntityTypeMapper
}
import gg.warcraft.monolith.spigot.item._
import gg.warcraft.monolith.spigot.math.{SpigotAABBfMapper, SpigotVectorMapper}
import gg.warcraft.monolith.spigot.menu.{
  SpigotButtonMapper, SpigotMenuMapper, SpigotMenuService
}
import gg.warcraft.monolith.spigot.player.hiding.SpigotPlayerHidingService
import gg.warcraft.monolith.spigot.player.{
  SpigotGameModeMapper, SpigotPlayerEventMapper, SpigotPlayerService
}
import gg.warcraft.monolith.spigot.world._
import org.bukkit.Server
import org.bukkit.plugin.Plugin

import java.util.logging.Logger

object implicits {
  private var _config: MonolithConfig = _

  private var _server: Server = _
  private var _plugin: Plugin = _
  private var _logger: Logger = _

  private var _commandService: CommandService = _
  private var _eventService: EventService = _
  private var _taskService: TaskService = _

  private[spigot] def init()(implicit
      server: Server,
      plugin: Plugin,
      logger: Logger,
      commandService: CommandService,
      eventService: EventService,
      taskService: TaskService
  ): Unit = {
    _server = server
    _plugin = plugin
    _logger = logger
    _commandService = commandService
    _eventService = eventService
    _taskService = taskService
  }

  private var _serverDataRepository: ServerDataRepository = _
  private var _entityDataRepository: EntityDataRepository = _
  private var _playerDataRepository: PlayerDataRepository = _
  private var _currencyRepository: CurrencyRepository = _
  private var _statisticRepository: StatisticRepository = _
  private var _statisticArchiveRepository: StatisticArchiveRepository = _
  private var _blockBackupRepository: BlockBackupRepository = _

  private[spigot] def configure(
      config: MonolithConfig,
      repositories: (
          ServerDataRepository,
          EntityDataRepository,
          PlayerDataRepository,
          CurrencyRepository,
          StatisticRepository,
          StatisticArchiveRepository,
          BlockBackupRepository
      )
  ): Unit = {
    _config = config
    _serverDataRepository = repositories._1
    _entityDataRepository = repositories._2
    _playerDataRepository = repositories._3
    _currencyRepository = repositories._4
    _statisticRepository = repositories._5
    _statisticArchiveRepository = repositories._6
    _blockBackupRepository = repositories._7
  }

  private[spigot] def monolithEventService(logger: Logger): EventService =
    if (_eventService != null) _eventService else new EventService()(logger)

  // Spigot
  private implicit lazy val server: Server = _server
  private implicit lazy val plugin: Plugin = _plugin
  private implicit lazy val logger: Logger = _logger

  // Persistence
  private implicit lazy val serverDataRepository: ServerDataRepository =
    _serverDataRepository
  private implicit lazy val entityDataRepository: EntityDataRepository =
    _entityDataRepository
  private implicit lazy val playerDataRepository: PlayerDataRepository =
    _playerDataRepository
  private implicit lazy val currencyRepository: CurrencyRepository =
    _currencyRepository
  private implicit lazy val statisticRepository: StatisticRepository =
    _statisticRepository
  private implicit lazy val statisticArchiveRepository: StatisticArchiveRepository =
    _statisticArchiveRepository
  private implicit lazy val blockBackupRepository: BlockBackupRepository =
    _blockBackupRepository

  // Core
  private implicit lazy val commandService: CommandService = _commandService
  private implicit lazy val eventService: EventService = _eventService
  private implicit lazy val taskService: TaskService = _taskService

  implicit lazy val authService: AuthService =
    new AuthService(_config)
  implicit lazy val serverDataService: ServerDataService =
    new ServerDataService

  // Math
  implicit lazy val boundingBoxMapper: SpigotAABBfMapper =
    new SpigotAABBfMapper
  implicit lazy val vectorMapper: SpigotVectorMapper =
    new SpigotVectorMapper

  // Block
  implicit lazy val blockService: BlockService =
    new SpigotBlockService
  implicit lazy val blockBackupService: BlockBackupService =
    new SpigotBlockBackupService
  implicit lazy val blockBuildService: BlockBuildService =
    new BlockBuildService
  implicit lazy val blockSpoofingService: BlockSpoofingService =
    new SpigotBlockSpoofingService

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

  // Effect
  implicit lazy val particleAdapter: ParticleAdapter =
    new SpigotParticleAdapter
  implicit lazy val particleMapper: SpigotParticleMapper =
    new SpigotParticleMapper

  // Entity
  implicit lazy val attributeService: AttributeService =
    new SpigotAttributeService
  implicit lazy val statusService: StatusService =
    new StatusService
  implicit lazy val teamService: TeamService =
    new TeamService(_config)
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
  implicit lazy val statisticArchiveService: StatisticArchiveService =
    new StatisticArchiveService
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
