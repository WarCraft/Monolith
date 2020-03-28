package gg.warcraft.monolith.api.block

import gg.warcraft.monolith.api.world.{BlockLocation, Location}

trait BlockService {
  def parseData(data: String): BlockTypeVariantOrState

  def getBlock(loc: BlockLocation): Block
  def getBlockIfType(loc: BlockLocation, types: BlockType*): Option[Block]
  def getHighestBlock(loc: BlockLocation): Block
  def getNearbyBlocks(location: Location, radius: Float): List[Block]

  def setBlock(loc: BlockLocation, data: BlockTypeVariantOrState): Unit
  def setBlock(loc: BlockLocation, block: Block): Unit
  def updateBlock(block: Block): Unit = setBlock(block.location, block)
}
