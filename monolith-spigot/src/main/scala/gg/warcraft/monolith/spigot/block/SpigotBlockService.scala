package gg.warcraft.monolith.spigot.block

import gg.warcraft.monolith.api.block.{Block, BlockService}
import gg.warcraft.monolith.api.block.box.{BlockBox, BlockBoxReader}
import gg.warcraft.monolith.api.world.{
  BlockLocation, Direction, Location, WorldService
}

class SpigotBlockService(
    implicit worldService: WorldService
) extends BlockService {
  // TODO move remaining block methods from world service to block service

  override def getNearbyBlocks(location: Location, radius: Float): List[Block] = {
    val min: BlockLocation = location subtract (radius, radius, radius)
    val max: BlockLocation = location subtract (radius, radius, radius)
    val boundingBox = BlockBox(location.world, min, max)
    val reader = new BlockBoxReader(boundingBox, Direction.NORTH)
    reader.getBlocks.filter { block =>
      val center = BlockLocation.toLocation(block.location).add(.5f, .5f, .5f)
      (location distanceTo center) <= radius
    }.toList
  }
}
