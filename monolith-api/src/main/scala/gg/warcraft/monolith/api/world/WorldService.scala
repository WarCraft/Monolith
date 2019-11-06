package gg.warcraft.monolith.api.world

import java.util.UUID

import gg.warcraft.monolith.api.math.{Vector3f, Vector3i}
import gg.warcraft.monolith.api.world.block.{Block, BlockType}
import gg.warcraft.monolith.api.world.item.Item

import scala.annotation.varargs

trait WorldService {
  def parseData(data: String): Any

  def getBlock(world: World, x: Int, y: Int, z: Int): Block
  def getBlock(world: World, translation: Vector3i): Block =
    getBlock(world, translation.x, translation.y, translation.z)
  def getBlock(location: BlockLocation): Block =
    getBlock(location.world, location.x, location.y, location.z)

  def getHighestBlock(world: World, x: Int, z: Int): Block
  def getHighestBlock(world: World, translation: Vector3i): Block =
    getHighestBlock(world, translation.x, translation.z)
  def getHighestBlock(location: BlockLocation): Block =
    getHighestBlock(location.world, location.x, location.z)

  def setBlock(world: World, x: Int, y: Int, z: Int, block: Block): Unit
  def setBlock(world: World, translation: Vector3i, block: Block): Unit =
    setBlock(world, translation.x, translation.y, translation.z, block)
  def setBlock(location: BlockLocation, block: Block): Unit =
    setBlock(location.world, location.x, location.y, location.z, block)

  def setBlockType(world: World, x: Int, y: Int, z: Int, `type`: BlockType): Unit
  def setBlockType(world: World, translation: Vector3i, `type`: BlockType): Unit =
    setBlockType(world, translation.x, translation.y, translation.z, `type`)
  def setBlockType(location: BlockLocation, `type`: BlockType): Unit =
    setBlockType(location.world, location.x, location.y, location.z, `type`)

  def setBlockData(world: World, x: Int, y: Int, z: Int, data: Any): Unit
  def setBlockData(world: World, translation: Vector3i, data: Any): Unit =
    setBlockData(world, translation.x, translation.y, translation.z, data)
  def setBlockData(location: BlockLocation, data: Any): Unit =
    setBlockData(location.world, location.x, location.y, location.z, data)

  def updateBlock(block: Block): Unit =
    setBlock(block.location, block)

  def spoofBlock(block: Block, playerId: UUID): Unit

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
