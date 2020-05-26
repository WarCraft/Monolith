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

package gg.warcraft.monolith.spigot.player

import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.player.{
  PlayerConnectEvent, PlayerDisconnectEvent, PlayerEquipmentChangedEvent,
  PlayerInteractEvent, PlayerPreConnectEvent, PlayerPreInteractEvent,
  PlayerPrePunchEvent, PlayerPreRespawnEvent, PlayerPunchEvent, PlayerRespawnEvent,
  PlayerService
}
import gg.warcraft.monolith.spigot.item.SpigotItemMapper
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.event.{EventHandler, EventPriority, Listener}
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.{InventoryClickEvent, InventoryType}
import org.bukkit.event.player.{
  AsyncPlayerPreLoginEvent, PlayerItemHeldEvent, PlayerJoinEvent, PlayerKickEvent,
  PlayerQuitEvent
}
import org.bukkit.inventory.EquipmentSlot

import scala.util.chaining._

class SpigotPlayerEventMapper(implicit
    eventService: EventService,
    taskService: TaskService,
    playerService: PlayerService,
    locationMapper: SpigotLocationMapper,
    itemMapper: SpigotItemMapper
) extends Listener {
  @EventHandler
  def preConnect(event: AsyncPlayerPreLoginEvent): Unit = {
    val reducedEvent = PlayerPreConnectEvent(event.getUniqueId, event.getName)
      .pipe { eventService.publish(_) }
    if (!reducedEvent.allowed) {
      event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER)
      event.setKickMessage("Failed to connect to the server.")
    }
  }

  @EventHandler
  def onConnect(event: PlayerJoinEvent): Unit = {
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    PlayerConnectEvent(player).tap { eventService.publish }
  }

  @EventHandler
  def onDisconnect(event: PlayerKickEvent): Unit = {
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    PlayerDisconnectEvent(player).tap { eventService.publish }
  }

  @EventHandler
  def onDisconnect(event: PlayerQuitEvent): Unit = {
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    PlayerDisconnectEvent(player).tap { eventService.publish }
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onEquipmentChanged(event: InventoryClickEvent): Unit =
    if (event.getSlotType == InventoryType.SlotType.ARMOR) {
      val player = playerService.getPlayer(event.getWhoClicked.getUniqueId)
      taskService.evalNextTick {
        PlayerEquipmentChangedEvent(player).tap { eventService.publish }
      }
    }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onEquipmentChanged(event: PlayerItemHeldEvent): Unit = {
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    taskService.evalNextTick {
      PlayerEquipmentChangedEvent(player).tap { eventService.publish }
    }
  }

  private def prePunch(event: SpigotPlayerInteractEvent): Unit = {
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val reducedEvent = PlayerPrePunchEvent(player, event.isCancelled)
      .pipe { eventService.publish(_) }
    // This weird logic is in place as otherwise bow shots mysteriously stop working
    // TODO investigate if this is to do with cancelling block and item use separately
    if (event.isCancelled && reducedEvent.allowed) event.setCancelled(false)
    else if (!event.isCancelled && !reducedEvent.allowed) event.setCancelled(true)
  }

  private def preInteract(event: SpigotPlayerInteractEvent): Unit = {
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val reducedEvent = PlayerPreInteractEvent(player, event.isCancelled)
      .pipe { eventService.publish(_) }
    // This weird logic is in place as otherwise bow shots mysteriously stop working
    // TODO investigate if this is to do with cancelling block and item use separately
    if (event.isCancelled && reducedEvent.allowed) event.setCancelled(false)
    else if (!event.isCancelled && !reducedEvent.allowed) event.setCancelled(true)
  }

  @EventHandler(priority = EventPriority.HIGH)
  def prePunchOrInteract(event: SpigotPlayerInteractEvent): Unit = {
    import event._
    if (!hasBlock && getHand != EquipmentSlot.OFF_HAND) getAction match {
      case Action.LEFT_CLICK_BLOCK | Action.LEFT_CLICK_AIR   => prePunch(event)
      case Action.RIGHT_CLICK_BLOCK | Action.RIGHT_CLICK_AIR => preInteract(event)
      case _                                                 => ()
    }
  }

  private def onPunch(event: SpigotPlayerInteractEvent): Unit = {
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    PlayerPunchEvent(player).tap { eventService.publish }
  }

  private def onInteract(event: SpigotPlayerInteractEvent): Unit = {
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    PlayerInteractEvent(player).tap { eventService.publish }
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onPunchOrInteract(event: SpigotPlayerInteractEvent): Unit = {
    import event._
    if (!hasBlock && getHand != EquipmentSlot.OFF_HAND) getAction match {
      case Action.LEFT_CLICK_BLOCK | Action.LEFT_CLICK_AIR   => onPunch(event)
      case Action.RIGHT_CLICK_BLOCK | Action.RIGHT_CLICK_AIR => onInteract(event)
      case _                                                 => ()
    }
  }

  @EventHandler(priority = EventPriority.HIGH)
  def preRespawn(event: SpigotPlayerRespawnEvent): Unit = {
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val location = locationMapper.map(event.getRespawnLocation)
    val reducedEvent = PlayerPreRespawnEvent(player, location)
      .pipe { eventService.publish(_) }
    val reducedLocation = locationMapper.map(reducedEvent.location)
    event.setRespawnLocation(reducedLocation)
  }

  @EventHandler(priority = EventPriority.MONITOR)
  def onRespawn(event: SpigotPlayerRespawnEvent): Unit = {
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val location = locationMapper.map(event.getRespawnLocation)
    PlayerRespawnEvent(player, location).tap { eventService.publish }
  }
}
