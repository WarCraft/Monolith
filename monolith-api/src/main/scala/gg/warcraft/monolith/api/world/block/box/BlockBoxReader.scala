package gg.warcraft.monolith.api.world.block.box

import gg.warcraft.monolith.api.math.Vector3i
import gg.warcraft.monolith.api.world.block.{Block, BlockDirection, BlockType}
import gg.warcraft.monolith.api.world.WorldService

class BlockBoxReader(
    val box: BlockBox,
    val direction: BlockDirection
)(
    private implicit val worldService: WorldService
) {
  import box._

  private val readBlock: Vector3i => Block = direction match {
    case BlockDirection.NORTH =>
      vec => worldService.getBlock(world, west + vec.x, lower + vec.y, south - vec.z)
    case BlockDirection.EAST =>
      vec => worldService.getBlock(world, west + vec.z, lower + vec.y, north + vec.x)
    case BlockDirection.SOUTH =>
      vec => worldService.getBlock(world, east - vec.x, lower + vec.y, north + vec.z)
    case BlockDirection.WEST =>
      vec => worldService.getBlock(world, east - vec.z, lower + vec.y, south - vec.x)
  }

  private val readOffset: Block => Vector3i = direction match {
    case BlockDirection.NORTH =>
      block => Vector3i(block.x - west, block.y - lower, south - block.z)
    case BlockDirection.EAST =>
      block => Vector3i(block.z - north, block.y - lower, block.x - west)
    case BlockDirection.SOUTH =>
      block => Vector3i(east - block.x, block.y - lower, block.z - north)
    case BlockDirection.WEST =>
      block => Vector3i(south - block.z, block.y - lower, east - block.x)
  }

  def getBlock(offset: Vector3i): Block = readBlock(offset)
  def getOffset(block: Block): Vector3i = readOffset(block)

  def getBlocks: LazyList[Block] = getBlocks(locationGenerator)

  def getBlocks(types: BlockType*): LazyList[Block] = locationGenerator
    .map(it => worldService.getBlockIfType((world, it._1, it._2, it._3), types: _*))
    .filter(_.isDefined)
    .map(_.get)

  def sliceX(x: Int): LazyList[Block] = getBlocks(for {
    y <- LazyList.range(min.y, max.y + 1)
    z <- min.z to max.z
  } yield (x, y, z))

  def sliceY(y: Int): LazyList[Block] = getBlocks(for {
    x <- LazyList.range(min.x, max.x + 1)
    z <- min.z to max.z
  } yield (x, y, z))

  def sliceZ(z: Int): LazyList[Block] = getBlocks(for {
    x <- LazyList.range(min.x, max.x + 1)
    y <- min.y to max.y
  } yield (x, y, z))

  private def locationGenerator: LazyList[(Int, Int, Int)] = for {
    x <- LazyList.range(min.x, max.x + 1)
    y <- min.y to max.y
    z <- min.z to max.z
  } yield (x, y, z)

  private def getBlocks(generator: LazyList[(Int, Int, Int)]): LazyList[Block] =
    generator.map(it => worldService.getBlock(world, it._1, it._2, it._3))
}
