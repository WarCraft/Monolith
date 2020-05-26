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
