package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block.BlockFace
import org.bukkit.block.{ BlockFace => SpigotBlockFace }

class SpigotBlockFaceMapper {

  def map(face: SpigotBlockFace): BlockFace = face match {
    case SpigotBlockFace.NORTH => BlockFace.NORTH
    case SpigotBlockFace.EAST  => BlockFace.EAST
    case SpigotBlockFace.SOUTH => BlockFace.SOUTH
    case SpigotBlockFace.WEST  => BlockFace.WEST
    case SpigotBlockFace.UP    => BlockFace.UP
    case SpigotBlockFace.DOWN  => BlockFace.DOWN

    case _ => throw new IllegalArgumentException(s"$face")
  }

  def map(face: BlockFace): SpigotBlockFace = face match {
    case BlockFace.NORTH => SpigotBlockFace.NORTH
    case BlockFace.EAST  => SpigotBlockFace.EAST
    case BlockFace.SOUTH => SpigotBlockFace.SOUTH
    case BlockFace.WEST  => SpigotBlockFace.WEST
    case BlockFace.UP    => SpigotBlockFace.UP
    case BlockFace.DOWN  => SpigotBlockFace.DOWN
  }
}
