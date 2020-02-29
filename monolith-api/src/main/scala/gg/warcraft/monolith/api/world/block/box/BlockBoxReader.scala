package gg.warcraft.monolith.api.world.block.box

import gg.warcraft.monolith.api.math.Vector3i
import gg.warcraft.monolith.api.world.block.{Block, BlockDirection, BlockType}
import gg.warcraft.monolith.api.world.WorldService

trait BlockBoxReader {
  val box: BlockBox
  val direction: BlockDirection

  protected implicit val worldService: WorldService

  import box._

  private val readBlock: (Int, Int, Int) => Block = direction match {
    case BlockDirection.NORTH =>
      (h: Int, v: Int, d: Int) =>
        worldService.getBlock(world, west + h, lower + v, south - d)
    case BlockDirection.EAST =>
      (h: Int, v: Int, d: Int) =>
        worldService.getBlock(world, west + d, lower + v, north + h)
    case BlockDirection.SOUTH =>
      (h: Int, v: Int, d: Int) =>
        worldService.getBlock(world, east - h, lower + v, north + d)
    case BlockDirection.WEST =>
      (h: Int, v: Int, d: Int) =>
        worldService.getBlock(world, east - d, lower + v, south - h)
  }

  private val readOffset: Block => (Int, Int, Int) = direction match {
    case BlockDirection.NORTH =>
      b => (b.location.x - west, b.location.y - lower, south - b.location.z)
    case BlockDirection.EAST =>
      b => (b.location.z - north, b.location.y - lower, b.location.x - west)
    case BlockDirection.SOUTH =>
      b => (east - b.location.x, b.location.y - lower, b.location.z - north)
    case BlockDirection.WEST =>
      b => (south - b.location.z, b.location.y - lower, east - b.location.x)
  }

  def getBlock(offset: Vector3i): Block = readBlock.tupled(offset)
  def getOffset(block: Block): Vector3i = readOffset(block)

  def getBlocks: LazyList[Block] = {}

  def getBlocks(types: BlockType*): LazyList[Block] = {}

  def sliceX(x: Int): LazyList[Block] = {}

  def sliceY(y: Int): LazyList[Block] = {}

  def sliceZ(z: Int): LazyList[Block] = {}
}

/*
    @Override
    public Stream<Block> stream() {
        return Stream
                .iterate(minX, currentX -> currentX + 1)
                .limit(maxX - minX + 1)
                .flatMap(x -> Stream
                        .iterate(minY, currentY -> currentY + 1)
                        .limit(maxY - minY + 1)
                        .flatMap(y -> Stream
                                .iterate(minZ, currentZ -> currentZ + 1)
                                .limit(maxZ - minZ + 1)
                                .map(z -> worldService.getBlock(getWorld(), x, y, z))));
    }

    @Override
    public Stream<Block> sliceX(int x) {
        return Stream
                .iterate(minY, currentY -> currentY + 1)
                .limit(maxY - minY + 1)
                .flatMap(y -> Stream
                        .iterate(minZ, currentZ -> currentZ + 1)
                        .limit(maxZ - minZ + 1)
                        .map(z -> worldService.getBlock(getWorld(), x, y, z)));
    }

    @Override
    public Stream<Block> sliceY(int y) {
        return Stream
                .iterate(minX, currentX -> currentX + 1)
                .limit(maxX - minX + 1)
                .flatMap(x -> Stream
                        .iterate(minZ, currentZ -> currentZ + 1)
                        .limit(maxZ - minZ + 1)
                        .map(z -> worldService.getBlock(getWorld(), x, y, z)));
    }

    @Override
    public Stream<Block> sliceZ(int z) {
        return Stream
                .iterate(minX, currentX -> currentX + 1)
                .limit(maxX - minX + 1)
                .flatMap(x -> Stream
                        .iterate(minY, currentY -> currentY + 1)
                        .limit(maxY - minY + 1)
                        .map(y -> worldService.getBlock(getWorld(), x, y, z)));
    }
 */
