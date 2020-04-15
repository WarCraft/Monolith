package gg.warcraft.monolith.api.world

import java.util.UUID

import gg.warcraft.monolith.api.block.{Block, BlockType, BlockTypeVariantOrState}
import gg.warcraft.monolith.api.math.Vector3f

trait WorldService {
  protected type Data = BlockTypeVariantOrState

  def parseData(data: String): Data

  def getBlock(loc: BlockLocation): Block
  def getBlockIfType(loc: BlockLocation, types: BlockType*): Option[Block]
  def getHighestBlock(loc: BlockLocation): Block

  def setBlock(loc: BlockLocation, data: Data): Unit
  def setBlock(loc: BlockLocation, block: Block): Unit
  def updateBlock(block: Block): Unit = setBlock(block.location, block)

  def playSound(
      location: Location,
      sound: Sound,
      category: SoundCategory,
      volume: Float = 1,
      pitch: Float = 1
  ): Unit

  def strikeLightning(location: Location, ambient: Boolean): Unit

  def createExplosion(location: Location, ambient: Boolean): Unit

  def createArrow(
      shooterId: UUID,
      location: Location,
      direction: Vector3f,
      speed: Float,
      spread: Float
  ): UUID
}
