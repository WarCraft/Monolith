package gg.warcraft.monolith.api.world

import java.util.UUID

import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.block.{Block, BlockType}
import gg.warcraft.monolith.api.world.item.Item

import scala.annotation.varargs

trait WorldService {
  def getBlock(world: World, x: Int, y: Int, z: Int): Block

  def getBlock(location: BlockLocation): Block

  def getHighestBlock(world: World, x: Int, z: Int): Block

  def getHighestBlock(location: BlockLocation): Block

  def setBlock(location: BlockLocation, block: Block): Unit

  def setBlockType(location: BlockLocation, `type`: BlockType): Unit

  def updateBlock(block: Block): Unit

  def spoofBlock(block: Block, playerId: UUID): Unit

  @varargs def dropItems(location: Location, items: Item*)

  def playSound(
      location: Location,
      sound: Sound,
      category: SoundCategory,
      volume: Float,
      pitch: Float
  ): Unit

  def playSound(location: Location, sound: Sound, category: SoundCategory): Unit

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
