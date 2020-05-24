package gg.warcraft.monolith.spigot.block

import java.util.logging.Logger

import gg.warcraft.monolith.api.block.{
  BlockBreakEvent, BlockInteractEvent, BlockPlaceEvent, BlockPreBreakEvent,
  BlockPreInteractEvent, BlockPrePlaceEvent, BlockPrePunchEvent,
  BlockPreTriggerEvent, BlockPunchEvent, BlockTriggerEvent
}
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.item.{Item, ItemService}
import gg.warcraft.monolith.api.player.PlayerService
import gg.warcraft.monolith.spigot.item.SpigotItemMapper
import org.bukkit.event.{EventHandler, EventPriority, Listener}
import org.bukkit.event.block.Action
import org.bukkit.inventory.EquipmentSlot

import scala.collection.mutable

private object SpigotBlockEventMapper {
  private val alternativeDropsByEvent = mutable.Map[SpigotEvent, List[Item]]()
}

class SpigotBlockEventMapper(implicit
    logger: Logger,
    eventService: EventService,
    playerService: PlayerService,
    itemService: ItemService,
    blockMapper: SpigotBlockMapper,
    blockFaceMapper: SpigotBlockFaceMapper,
    itemMapper: SpigotItemMapper
) extends Listener {
  import SpigotBlockEventMapper.alternativeDropsByEvent

  @EventHandler(priority = EventPriority.HIGH)
  def preBreak(event: SpigotBlockBreakEvent): Unit = {
    logger.info("Handling SpigotBlockBreakEvent")
    val block = blockMapper.map(event.getBlock).get
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val cancelled = event.isCancelled
    val preBreakEvent = BlockPreBreakEvent(block, player, None, cancelled)

    val reducedEvent = eventService.publish(preBreakEvent)
    reducedEvent.alternativeDrops match {
      case Some(drops) =>
        event.setDropItems(false)
        alternativeDropsByEvent.put(event, drops)
      case None => event.setDropItems(true)
    }
    event.setCancelled(!reducedEvent.allowed)
  }

  @EventHandler(priority = EventPriority.MONITOR)
  // Don't ignore if cancelled until having removed alternative drops
  def onBreak(event: SpigotBlockBreakEvent): Unit = {
    logger.info("Handling SpigotBlockBreakEvent MONITOR")
    val alternativeDrops = alternativeDropsByEvent.remove(event)
    if (event.isCancelled) return

    val block = blockMapper.map(event.getBlock).get
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val breakEvent = BlockBreakEvent(block, player, alternativeDrops)

    eventService.publish(breakEvent)
    alternativeDrops match {
      case Some(drops) if drops.nonEmpty =>
        itemService.dropItems(block.location, drops: _*)
      case _ => ()
    }
  }

  @EventHandler(priority = EventPriority.HIGH)
  def prePlace(event: SpigotBlockPlaceEvent): Unit = {
    logger.info("Handling SpigotBlockPlaceEvent")
    val block = blockMapper.map(event.getBlock).get
    val againstBlock = blockMapper.map(event.getBlockAgainst).get
    val againstBlockFace = null // TODO
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val cancelled = event.isCancelled
    val prePlaceEvent = BlockPrePlaceEvent(
      block,
      againstBlock,
      againstBlockFace,
      player,
      cancelled
    )

    val reducedEvent = eventService.publish(prePlaceEvent)
    event.setCancelled(!reducedEvent.allowed)
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onPlace(event: SpigotBlockPlaceEvent): Unit = {
    logger.info("Handling SpigotBlockPlaceEvent MONITOR")
    val block = blockMapper.map(event.getBlock).get
    val againstBlock = blockMapper.map(event.getBlockAgainst).get
    val blockFace = null // TODO calc from 2 blocks
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val placeEvent = BlockPlaceEvent(block, againstBlock, blockFace, player)

    eventService.publish(placeEvent)
  }

  private def prePunch(event: SpigotPlayerInteractEvent): Unit = {
    val block = blockMapper.map(event.getClickedBlock).get
    val blockFace = blockFaceMapper.map(event.getBlockFace)
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val cancelled = event.isCancelled
    val prePunchEvent = BlockPrePunchEvent(block, blockFace, player, cancelled)

    val reducedEvent = eventService.publish(prePunchEvent)
    // This weird logic is in place as otherwise bow shots mysteriously stop working
    // TODO investigate if this is to do with cancelling block and item use separately
    if (event.isCancelled && reducedEvent.allowed) event.setCancelled(false)
    else if (!event.isCancelled && !reducedEvent.allowed) event.setCancelled(true)
  }

  private def preInteract(event: SpigotPlayerInteractEvent): Unit = {
    val block = blockMapper.map(event.getClickedBlock).get
    val blockFace = blockFaceMapper.map(event.getBlockFace)
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val cancelled = event.isCancelled
    val preInteractEvent = BlockPreInteractEvent(block, blockFace, player, cancelled)

    val reducedEvent = eventService.publish(preInteractEvent)
    event.setCancelled(!reducedEvent.allowed)
  }

  private def preTrigger(event: SpigotPlayerInteractEvent): Unit = {
    val block = blockMapper.map(event.getClickedBlock).get
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val cancelled = event.isCancelled
    val preTriggerEvent = BlockPreTriggerEvent(block, player, cancelled)

    val reducedEvent = eventService.publish(preTriggerEvent)
    event.setCancelled(!reducedEvent.allowed)
  }

  @EventHandler(priority = EventPriority.HIGH)
  def prePunchInteractOrTrigger(event: SpigotPlayerInteractEvent): Unit = {
    logger.info("Handling SpigotPlayerInteractEvent")
    if (!event.hasBlock || event.getHand == EquipmentSlot.OFF_HAND) return
    event.getAction match {
      case Action.LEFT_CLICK_BLOCK | Action.LEFT_CLICK_AIR   => prePunch(event)
      case Action.RIGHT_CLICK_BLOCK | Action.RIGHT_CLICK_AIR => preInteract(event)
      case Action.PHYSICAL                                   => preTrigger(event)
    }
  }

  private def onPunch(event: SpigotPlayerInteractEvent): Unit = {
    val block = blockMapper.map(event.getClickedBlock).get
    val blockFace = blockFaceMapper.map(event.getBlockFace)
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val punchEvent = BlockPunchEvent(block, blockFace, player)

    eventService.publish(punchEvent)
  }

  private def onInteract(event: SpigotPlayerInteractEvent): Unit = {
    val block = blockMapper.map(event.getClickedBlock).get
    val blockFace = blockFaceMapper.map(event.getBlockFace)
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val interactEvent = BlockInteractEvent(block, blockFace, player)

    eventService.publish(interactEvent)
  }

  private def onTrigger(event: SpigotPlayerInteractEvent): Unit = {
    val block = blockMapper.map(event.getClickedBlock).get
    val player = playerService.getPlayer(event.getPlayer.getUniqueId)
    val triggerEvent = BlockTriggerEvent(block, player)

    eventService.publish(triggerEvent)
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onPunchInteractOrTrigger(event: SpigotPlayerInteractEvent): Unit = {
    logger.info("Handling SpigotPlayerInteractEvent MONITOR")
    if (!event.hasBlock || event.getHand == EquipmentSlot.OFF_HAND) return
    event.getAction match {
      case Action.LEFT_CLICK_BLOCK | Action.LEFT_CLICK_AIR   => onPunch(event)
      case Action.RIGHT_CLICK_BLOCK | Action.RIGHT_CLICK_AIR => onInteract(event)
      case Action.PHYSICAL                                   => onTrigger(event)
    }
  }
}
