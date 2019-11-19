package gg.warcraft.monolith.spigot.player

import gg.warcraft.monolith.api.core.{EventService, TaskService}
import gg.warcraft.monolith.api.entity.Equipment
import gg.warcraft.monolith.api.player.{
  PlayerConnectEvent, PlayerDisconnectEvent, PlayerEquipmentChangedEvent,
  PlayerInteractEvent, PlayerPreConnectEvent, PlayerPreInteractEvent,
  PlayerPrePunchEvent, PlayerPreRespawnEvent, PlayerPunchEvent, PlayerRespawnEvent
}
import gg.warcraft.monolith.api.world.item.Item
import gg.warcraft.monolith.app.entity.SimpleEquipment
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import gg.warcraft.monolith.spigot.world.item.SpigotItemMapper
import org.bukkit.entity.Player
import org.bukkit.event.{EventHandler, EventPriority}
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.{InventoryClickEvent, InventoryType}
import org.bukkit.event.player.{
  AsyncPlayerPreLoginEvent, PlayerItemHeldEvent, PlayerJoinEvent, PlayerKickEvent,
  PlayerQuitEvent
}
import org.bukkit.inventory.EquipmentSlot

class SpigotPlayerEventMapper(
    private implicit val eventService: EventService,
    private implicit val taskService: TaskService,
    private implicit val locationMapper: SpigotLocationMapper,
    private implicit val itemMapper: SpigotItemMapper
) {
  @EventHandler
  def preConnect(event: AsyncPlayerPreLoginEvent): Unit = {
    val playerId = event.getUniqueId
    val name = event.getName
    val preConnectEvent = PlayerPreConnectEvent(playerId, name)
    eventService.publish(preConnectEvent)
  }

  @EventHandler
  def onConnect(event: PlayerJoinEvent): Unit = {
    val playerId = event.getPlayer.getUniqueId
    val connectEvent = PlayerConnectEvent(playerId)
    eventService.publish(connectEvent)
  }

  @EventHandler
  def onDisconnect(event: PlayerKickEvent): Unit = {
    val playerId = event.getPlayer.getUniqueId
    val disconnectEvent = PlayerDisconnectEvent(playerId)
    eventService.publish(disconnectEvent)
  }

  @EventHandler
  def onDisconnect(event: PlayerQuitEvent): Unit = {
    val playerId = event.getPlayer.getUniqueId
    val disconnectEvent = PlayerDisconnectEvent(playerId)
    eventService.publish(disconnectEvent)
  }

  // TODO replace with case class
  private def getEquipment(player: Player): Equipment = {
    val head: Item = itemMapper
      .map(player.getInventory.getHelmet)
      .getOrElse(() => null)
    val chest: Item = itemMapper
      .map(player.getInventory.getChestplate)
      .getOrElse(() => null)
    val legs: Item = itemMapper
      .map(player.getInventory.getLeggings)
      .getOrElse(() => null)
    val feet: Item = itemMapper
      .map(player.getInventory.getBoots)
      .getOrElse(() => null)
    val mainHand: Item = itemMapper
      .map(player.getInventory.getItemInMainHand)
      .getOrElse(() => null)
    val offHand: Item = itemMapper
      .map(player.getInventory.getItemInOffHand)
      .getOrElse(() => null)
    new SimpleEquipment(head, chest, legs, feet, mainHand, offHand)
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onEquipmentChanged(event: InventoryClickEvent): Unit = {
    if (event.getSlotType == InventoryType.SlotType.ARMOR) {
      val playerId = event.getWhoClicked.getUniqueId
      val equipment = getEquipment(event.getWhoClicked.asInstanceOf[Player])
      taskService.runNextTick(() => {
        val equipmentChangedEvent = PlayerEquipmentChangedEvent(playerId, equipment)
        eventService.publish(equipmentChangedEvent)
      })
    }
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onEquipmentChanged(event: PlayerItemHeldEvent): Unit = {
    val playerId = event.getPlayer.getUniqueId
    val equipment = getEquipment(event.getPlayer)
    taskService.runNextTick(() => {
      val equipmentChangedEvent = PlayerEquipmentChangedEvent(playerId, equipment)
      eventService.publish(equipmentChangedEvent)
    })
  }

  private def prePunch(event: SpigotPlayerInteractEvent): Unit = {
    val playerId = event.getPlayer.getUniqueId
    val sneaking = event.getPlayer.isSneaking
    val mainHand = itemMapper.map(event.getItem)
    val offHand = itemMapper.map(event.getPlayer.getInventory.getItemInOffHand)
    val cancelled = event.isCancelled
    val prePunchEvent = PlayerPrePunchEvent(
      playerId,
      sneaking,
      mainHand,
      offHand,
      cancelled
    )

    val reducedEvent = eventService.publish(prePunchEvent)
    // This weird logic is in place as otherwise bow shots mysteriously stop working
    // TODO investigate if this is to do with cancelling block and item use separately
    if (event.isCancelled && reducedEvent.allowed) event.setCancelled(false)
    else if (!event.isCancelled && !reducedEvent.allowed) event.setCancelled(true)
  }

  private def preInteract(event: SpigotPlayerInteractEvent): Unit = {
    val playerId = event.getPlayer.getUniqueId
    val sneaking = event.getPlayer.isSneaking
    val mainHand = itemMapper.map(event.getItem)
    val offHand = itemMapper.map(event.getPlayer.getInventory.getItemInOffHand)
    val cancelled = event.isCancelled
    val preInteractEvent = PlayerPreInteractEvent(
      playerId,
      sneaking,
      mainHand,
      offHand,
      cancelled
    )

    val reducedEvent = eventService.publish(preInteractEvent)
    event.setCancelled(!reducedEvent.allowed)
  }

  @EventHandler(priority = EventPriority.HIGH)
  def prePunchOrInteract(event: SpigotPlayerInteractEvent): Unit = {
    if (event.hasBlock || event.getHand == EquipmentSlot.OFF_HAND) return
    event.getAction match {
      case Action.LEFT_CLICK_BLOCK | Action.LEFT_CLICK_AIR   => prePunch(event)
      case Action.RIGHT_CLICK_BLOCK | Action.RIGHT_CLICK_AIR => preInteract(event)
      case _                                                 => ()
    }
  }

  private def onPunch(event: SpigotPlayerInteractEvent): Unit = {
    val playerId = event.getPlayer.getUniqueId
    val sneaking = event.getPlayer.isSneaking
    val mainHand = itemMapper.map(event.getItem)
    val offHand = itemMapper.map(event.getPlayer.getInventory.getItemInOffHand)
    val punchEvent = PlayerPunchEvent(playerId, sneaking, mainHand, offHand)
    eventService.publish(punchEvent)
  }

  private def onInteract(event: SpigotPlayerInteractEvent): Unit = {
    val playerId = event.getPlayer.getUniqueId
    val sneaking = event.getPlayer.isSneaking
    val mainHand = itemMapper.map(event.getItem)
    val offHand = itemMapper.map(event.getPlayer.getInventory.getItemInOffHand)
    val interactEvent = PlayerInteractEvent(playerId, sneaking, mainHand, offHand)
    eventService.publish(interactEvent)
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onPunchOrInteract(event: SpigotPlayerInteractEvent): Unit = {
    if (event.hasBlock || event.getHand == EquipmentSlot.OFF_HAND) return
    event.getAction match {
      case Action.LEFT_CLICK_BLOCK | Action.LEFT_CLICK_AIR   => onPunch(event)
      case Action.RIGHT_CLICK_BLOCK | Action.RIGHT_CLICK_AIR => onInteract(event)
      case _                                                 => ()
    }
  }

  @EventHandler(priority = EventPriority.HIGH)
  def preRespawn(event: SpigotPlayerRespawnEvent): Unit = {
    val playerId = event.getPlayer.getUniqueId
    val location = locationMapper.map(event.getRespawnLocation)
    val preRespawnEvent = PlayerPreRespawnEvent(playerId, location)

    val reducedEvent = eventService.publish(preRespawnEvent)
    val reducedLocation = locationMapper.map(reducedEvent.location)
    event.setRespawnLocation(reducedLocation)
  }

  @EventHandler(priority = EventPriority.MONITOR)
  def onRespawn(event: SpigotPlayerRespawnEvent): Unit = {
    val playerId = event.getPlayer.getUniqueId
    val location = locationMapper.map(event.getRespawnLocation)
    val respawnEvent = PlayerRespawnEvent(playerId, location)
    eventService.publish(respawnEvent)
  }
}
