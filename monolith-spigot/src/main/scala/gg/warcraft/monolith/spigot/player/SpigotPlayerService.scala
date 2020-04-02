package gg.warcraft.monolith.spigot.player

import java.util.UUID
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.entity.Equipment
import gg.warcraft.monolith.api.entity.Equipment.Slot
import gg.warcraft.monolith.api.entity.attribute.AttributeService
import gg.warcraft.monolith.api.entity.data.EntityDataService
import gg.warcraft.monolith.api.entity.status.StatusService
import gg.warcraft.monolith.api.entity.team.TeamService
import gg.warcraft.monolith.api.item.Item
import gg.warcraft.monolith.api.player.{OfflinePlayer, Player, PlayerService}
import gg.warcraft.monolith.api.player.currency.CurrencyService
import gg.warcraft.monolith.api.player.data.PlayerDataService
import gg.warcraft.monolith.api.player.statistic.StatisticService
import gg.warcraft.monolith.api.util.Ops._
import gg.warcraft.monolith.spigot.entity.SpigotEntityTypeMapper
import gg.warcraft.monolith.spigot.item.{
  SpigotItem, SpigotItemMapper, SpigotItemTypeMapper, SpigotItemVariantMapper
}
import gg.warcraft.monolith.spigot.math.SpigotVectorMapper
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.{Material, Server}
import org.bukkit.inventory.PlayerInventory

import scala.jdk.CollectionConverters._

class SpigotPlayerService(
    implicit server: Server,
    logger: Logger,
    taskService: TaskService,
    attributeService: AttributeService,
    statusService: StatusService,
    teamService: TeamService,
    currencyService: CurrencyService,
    statisticService: StatisticService,
    entityDataService: EntityDataService,
    dataService: PlayerDataService,
    vectorMapper: SpigotVectorMapper,
    locationMapper: SpigotLocationMapper,
    gameModeMapper: SpigotGameModeMapper,
    itemTypeMapper: SpigotItemTypeMapper,
    itemVariantMapper: SpigotItemVariantMapper,
    itemMapper: SpigotItemMapper,
    entityTypeMapper: SpigotEntityTypeMapper
) extends PlayerService {
  private implicit val playerService: SpigotPlayerService = this

  override def getPlayer(id: UUID): Option[Player] = {
    server getEntity id match {
      case it: SpigotPlayer => new SpigotPlayerAdapter(it) |> Some.apply
      case _                => None
    }
  }

  override def getOfflinePlayer(id: UUID): OfflinePlayer =
    (server getOfflinePlayer id) |> { new SpigotOfflinePlayerAdapter(_) }

  override def getOnlinePlayers: List[Player] =
    server.getOnlinePlayers.asScala.map { new SpigotPlayerAdapter(_) }.toList

  override def resolvePlayerId(name: String): UUID =
    server getPlayerUniqueId name

  override def setExperience(id: UUID, progress: Float): Unit =
    (server getPlayer id) ?? { _ setExp progress }

  override def setLevel(id: UUID, level: Int): Unit =
    (server getPlayer id) ?? { _ setLevel level }

  private def getEquipActions(
      inventory: PlayerInventory,
      slot: Equipment.Slot
  ): (SpigotItem, SpigotItem => Unit) = inventory |> { it =>
    slot match {
      case Equipment.HEAD      => (it.getHelmet, it.setHelmet)
      case Equipment.CHEST     => (it.getChestplate, it.setChestplate)
      case Equipment.LEGS      => (it.getLeggings, it.setLeggings)
      case Equipment.FEET      => (it.getBoots, it.setBoots)
      case Equipment.MAIN_HAND => (it.getItemInMainHand, it.setItemInMainHand)
      case Equipment.OFF_HAND  => (it.getItemInOffHand, it.setItemInOffHand)
    }
  }

  override def setEquipment(
      id: UUID,
      equipment: Equipment,
      force: Boolean = false
  ): Boolean = (server getPlayer id) ?? { player => ??? }

  override def setEquipment(
      id: UUID,
      slot: Slot,
      item: Item,
      force: Boolean = false
  ): Boolean = (server getPlayer id) ?? { player =>
    val inventory = player.getInventory
    val (unequip, equipAction) = getEquipActions(inventory, slot)
    val mapItemAndEquip = () => equipAction apply (itemMapper map item)
    if (unequip.getType == Material.AIR) {
      mapItemAndEquip()
      !force
    } else {
      val undelivered = inventory.addItem(unequip)
      if (undelivered.isEmpty) {
        mapItemAndEquip()
        !force
      } else {
        if (force) {
          mapItemAndEquip()
          true
        } else false
      }
    }
  }

  override def giveItem(id: UUID, item: Item*): List[Item] = {
    server getPlayer id match {
      case player: SpigotPlayer =>
        val spigotItems = item map itemMapper.map
        val undelivered = player.getInventory addItem (spigotItems: _*)
        if (!undelivered.isEmpty) {
          undelivered.values.asScala
            .map { itemMapper.map }
            .filter { _.isDefined }
            .map { _.get }
            .toList
        } else Nil
      case _ => item.toList
    }
  }

  override def sendMessage(id: UUID, message: Message): Unit =
    (server getPlayer id) ?? { _ sendMessage message.formatted }

  override def sendTitle(id: UUID, title: String, subTitle: String): Unit =
    (server getPlayer id) ?? { _ sendTitle (title, subTitle) }
}
