package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.core.EventService
import gg.warcraft.monolith.api.world.block.{
  BlockBreakEvent, BlockInteractEvent, BlockPlaceEvent, BlockPreBreakEvent,
  BlockPreInteractEvent, BlockPrePlaceEvent, BlockPrePunchEvent,
  BlockPreTriggerEvent, BlockPunchEvent, BlockTriggerEvent
}
import gg.warcraft.monolith.api.world.item.{Item, ItemService}
import gg.warcraft.monolith.spigot.world.item.SpigotItemMapper
import org.bukkit.event.{EventHandler, EventPriority}
import org.bukkit.event.block.Action
import org.bukkit.inventory.EquipmentSlot

import scala.collection.mutable

private object SpigotBlockEventMapper {
  private val alternativeDropsByEvent = mutable.Map[SpigotEvent, List[Item]]()
}

class SpigotBlockEventMapper(
    private implicit val eventService: EventService,
    private implicit val itemService: ItemService,
    private implicit val blockMapper: SpigotBlockMapper,
    private implicit val blockFaceMapper: SpigotBlockFaceMapper,
    private implicit val itemMapper: SpigotItemMapper
) {
  import SpigotBlockEventMapper.alternativeDropsByEvent

  @EventHandler(priority = EventPriority.HIGH)
  def preBreak(event: SpigotBlockBreakEvent): Unit = {
    val block = blockMapper.map(event.getBlock).get
    val playerId = event.getPlayer.getUniqueId
    val cancelled = event.isCancelled
    val preBreakEvent = BlockPreBreakEvent(block, playerId, None, cancelled)

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
    val alternativeDrops = alternativeDropsByEvent.remove(event)
    if (event.isCancelled) return

    val block = blockMapper.map(event.getBlock).get
    val playerId = event.getPlayer.getUniqueId
    val breakEvent = BlockBreakEvent(block, playerId, alternativeDrops)

    eventService.publish(breakEvent)
    alternativeDrops match {
      case Some(drops) if drops.nonEmpty =>
        val dropLocation = block.location.toSafeLocation
        itemService.dropItems(dropLocation, drops: _*)
      case _ => ()
    }
  }

  @EventHandler(priority = EventPriority.HIGH)
  def prePlace(event: SpigotBlockPlaceEvent): Unit = {
    val block = blockMapper.map(event.getBlock).get
    val againstBlock = blockMapper.map(event.getBlockAgainst).get
    val againstBlockFace = null // TODO
    val playerId = event.getPlayer.getUniqueId
    val cancelled = event.isCancelled
    val prePlaceEvent = BlockPrePlaceEvent(
      block,
      againstBlock,
      againstBlockFace,
      playerId,
      cancelled
    )

    val reducedEvent = eventService.publish(prePlaceEvent)
    event.setCancelled(!reducedEvent.allowed)
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onPlace(event: SpigotBlockPlaceEvent): Unit = {
    val block = blockMapper.map(event.getBlock).get
    val againstBlock = blockMapper.map(event.getBlockAgainst).get
    val blockFace = null // TODO calc from 2 blocks
    val playerId = event.getPlayer.getUniqueId
    val placeEvent = BlockPlaceEvent(block, againstBlock, blockFace, playerId)

    eventService.publish(placeEvent)
  }

  private def prePunch(event: SpigotPlayerInteractEvent): Unit = {
    val block = blockMapper.map(event.getClickedBlock)
    val blockFace = blockFaceMapper.map(event.getBlockFace)
    val playerId = event.getPlayer.getUniqueId
    val sneaking = event.getPlayer.isSneaking
    val mainHand = itemMapper.map(event.getItem)
    val offHand = itemMapper.map(event.getPlayer.getInventory.getItemInOffHand)
    val cancelled = event.isCancelled
    val prePunchEvent = BlockPrePunchEvent(
      block,
      blockFace,
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
    val block = blockMapper.map(event.getClickedBlock)
    val blockFace = blockFaceMapper.map(event.getBlockFace)
    val playerId = event.getPlayer.getUniqueId
    val sneaking = event.getPlayer.isSneaking
    val mainHand = itemMapper.map(event.getItem)
    val offHand = itemMapper.map(event.getPlayer.getInventory.getItemInOffHand)
    val cancelled = event.isCancelled
    val preInteractEvent = BlockPreInteractEvent(
      block,
      blockFace,
      playerId,
      sneaking,
      mainHand,
      offHand,
      cancelled
    )

    val reducedEvent = eventService.publish(preInteractEvent)
    event.setCancelled(!reducedEvent.allowed)
  }

  private def preTrigger(event: SpigotPlayerInteractEvent): Unit = {
    val block = blockMapper.map(event.getClickedBlock).get
    val playerId = event.getPlayer.getUniqueId
    val cancelled = event.isCancelled
    val preTriggerEvent = BlockPreTriggerEvent(block, playerId, cancelled)

    val reducedEvent = eventService.publish(preTriggerEvent)
    event.setCancelled(!reducedEvent.allowed)
  }

  @EventHandler(priority = EventPriority.HIGH)
  def prePunchInteractOrTrigger(event: SpigotPlayerInteractEvent): Unit = {
    if (event.getHand == EquipmentSlot.OFF_HAND) return
    event.getAction match {
      case Action.LEFT_CLICK_BLOCK | Action.LEFT_CLICK_AIR   => prePunch(event)
      case Action.RIGHT_CLICK_BLOCK | Action.RIGHT_CLICK_AIR => preInteract(event)
      case Action.PHYSICAL                                   => preTrigger(event)
    }
  }

  private def onPunch(event: SpigotPlayerInteractEvent): Unit = {
    val block = blockMapper.map(event.getClickedBlock)
    val blockFace = blockFaceMapper.map(event.getBlockFace)
    val playerId = event.getPlayer.getUniqueId
    val sneaking = event.getPlayer.isSneaking
    val mainHand = itemMapper.map(event.getItem)
    val offHand = itemMapper.map(event.getPlayer.getInventory.getItemInOffHand)
    val punchEvent = BlockPunchEvent(
      block,
      blockFace,
      playerId,
      sneaking,
      mainHand,
      offHand
    )

    eventService.publish(punchEvent)
  }

  private def onInteract(event: SpigotPlayerInteractEvent): Unit = {
    val block = blockMapper.map(event.getClickedBlock)
    val blockFace = blockFaceMapper.map(event.getBlockFace)
    val playerId = event.getPlayer.getUniqueId
    val sneaking = event.getPlayer.isSneaking
    val mainHand = itemMapper.map(event.getItem)
    val offHand = itemMapper.map(event.getPlayer.getInventory.getItemInOffHand)
    val interactEvent = BlockInteractEvent(
      block,
      blockFace,
      playerId,
      sneaking,
      mainHand,
      offHand
    )

    eventService.publish(interactEvent)
  }

  private def onTrigger(event: SpigotPlayerInteractEvent): Unit = {
    val block = blockMapper.map(event.getClickedBlock).get
    val playerId = event.getPlayer.getUniqueId
    val triggerEvent = BlockTriggerEvent(block, playerId)

    eventService.publish(triggerEvent)
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  def onPunchInteractOrTrigger(event: SpigotPlayerInteractEvent): Unit = {
    if (event.getHand == EquipmentSlot.OFF_HAND) return
    event.getAction match {
      case Action.LEFT_CLICK_BLOCK | Action.LEFT_CLICK_AIR   => onPunch(event)
      case Action.RIGHT_CLICK_BLOCK | Action.RIGHT_CLICK_AIR => onInteract(event)
      case Action.PHYSICAL                                   => onTrigger(event)
    }
  }
}
