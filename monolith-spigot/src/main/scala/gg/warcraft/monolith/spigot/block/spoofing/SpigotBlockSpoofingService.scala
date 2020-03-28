package gg.warcraft.monolith.spigot.block.spoofing

import java.util.UUID

import gg.warcraft.monolith.api.block.spoofing.BlockSpoofingService
import gg.warcraft.monolith.api.block.Block
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.spigot.block.SpigotBlockMapper
import gg.warcraft.monolith.spigot.world.{SpigotLocation, SpigotLocationMapper}
import org.bukkit.Server

import scala.collection.mutable

class SpigotBlockSpoofingService(
    implicit server: Server,
    locationMapper: SpigotLocationMapper,
    blockMapper: SpigotBlockMapper
) extends BlockSpoofingService {
  private var spoofedBlocks: Map[UUID, Map[BlockLocation, Block]] = Map.empty

  override def spoofBlock(playerId: UUID, fakeBlocks: Block*): Unit = {
    val player = server getPlayer playerId
    if (player != null) {
      var playerSpoofedBlocks = spoofedBlocks getOrElse (playerId, Map.empty)
      fakeBlocks foreach { fakeBlock =>
        val spigotLoc = locationMapper map fakeBlock.location
        val spigotData = blockMapper map fakeBlock
        player sendBlockChange (spigotLoc, spigotData)
        playerSpoofedBlocks += (fakeBlock.location -> fakeBlock)
      }
      spoofedBlocks += (playerId -> playerSpoofedBlocks)
    }
  }

  override def unspoofBlock(playerId: UUID, locations: BlockLocation*): Unit = {
    val player = server getPlayer playerId
    if (player != null) {
      var playerSpoofedBlocks = spoofedBlocks getOrElse (playerId, Map.empty)
      locations foreach { location =>
        val spigotLoc = locationMapper map location
        val spigotData = spigotLoc.getBlock.getBlockData
        player sendBlockChange (spigotLoc, spigotData)
        playerSpoofedBlocks -= location
      }
      spoofedBlocks += (playerId -> playerSpoofedBlocks)
    }
  }

  override def unspoofAll(playerIds: UUID*): Unit = {
    val locCache: mutable.Map[BlockLocation, SpigotLocation] = mutable.Map.empty
    playerIds foreach { playerId =>
      val player = server getPlayer playerId
      if (player != null) {
        spoofedBlocks get playerId match {
          case Some(playerSpoofedBlocks) =>
            playerSpoofedBlocks foreach {
              case (spoofedLoc, _) =>
                val spigotLoc = locCache getOrElseUpdate (spoofedLoc, {
                  locationMapper map spoofedLoc
                })
                player sendBlockChange (spigotLoc, spigotLoc.getBlock.getBlockData)
            }
          case None =>
        }
      }
      spoofedBlocks -= playerId
    }
  }
}
