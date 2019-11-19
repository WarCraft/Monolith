package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block.BlockRotation
import org.bukkit.block.{BlockFace => SpigotBlockFace}

class SpigotBlockRotationMapper {
  def map(rotation: SpigotBlockFace): BlockRotation = rotation match {
    case SpigotBlockFace.NORTH            => BlockRotation.NORTH
    case SpigotBlockFace.NORTH_NORTH_EAST => BlockRotation.NORTH_NORTH_EAST
    case SpigotBlockFace.NORTH_EAST       => BlockRotation.NORTH_EAST
    case SpigotBlockFace.EAST_NORTH_EAST  => BlockRotation.EAST_NORTH_EAST
    case SpigotBlockFace.EAST             => BlockRotation.EAST
    case SpigotBlockFace.EAST_SOUTH_EAST  => BlockRotation.EAST_SOUTH_EAST
    case SpigotBlockFace.SOUTH_EAST       => BlockRotation.SOUTH_EAST
    case SpigotBlockFace.SOUTH_SOUTH_EAST => BlockRotation.SOUTH_SOUTH_EAST
    case SpigotBlockFace.SOUTH            => BlockRotation.SOUTH
    case SpigotBlockFace.SOUTH_SOUTH_WEST => BlockRotation.SOUTH_SOUTH_WEST
    case SpigotBlockFace.SOUTH_WEST       => BlockRotation.SOUTH_WEST
    case SpigotBlockFace.WEST_SOUTH_WEST  => BlockRotation.WEST_SOUTH_WEST
    case SpigotBlockFace.WEST             => BlockRotation.WEST
    case SpigotBlockFace.WEST_NORTH_WEST  => BlockRotation.WEST_NORTH_WEST
    case SpigotBlockFace.NORTH_WEST       => BlockRotation.NORTH_WEST
    case SpigotBlockFace.NORTH_NORTH_WEST => BlockRotation.NORTH_NORTH_WEST

    case _ => throw new IllegalArgumentException(s"$rotation")
  }

  def map(rotation: BlockRotation): SpigotBlockFace = rotation match {
    case BlockRotation.NORTH            => SpigotBlockFace.NORTH
    case BlockRotation.NORTH_NORTH_EAST => SpigotBlockFace.NORTH_NORTH_EAST
    case BlockRotation.NORTH_EAST       => SpigotBlockFace.NORTH_EAST
    case BlockRotation.EAST_NORTH_EAST  => SpigotBlockFace.EAST_NORTH_EAST
    case BlockRotation.EAST             => SpigotBlockFace.EAST
    case BlockRotation.EAST_SOUTH_EAST  => SpigotBlockFace.EAST_SOUTH_EAST
    case BlockRotation.SOUTH_EAST       => SpigotBlockFace.SOUTH_EAST
    case BlockRotation.SOUTH_SOUTH_EAST => SpigotBlockFace.SOUTH_SOUTH_EAST
    case BlockRotation.SOUTH            => SpigotBlockFace.SOUTH
    case BlockRotation.SOUTH_SOUTH_WEST => SpigotBlockFace.SOUTH_SOUTH_WEST
    case BlockRotation.SOUTH_WEST       => SpigotBlockFace.SOUTH_WEST
    case BlockRotation.WEST_SOUTH_WEST  => SpigotBlockFace.WEST_SOUTH_WEST
    case BlockRotation.WEST             => SpigotBlockFace.WEST
    case BlockRotation.WEST_NORTH_WEST  => SpigotBlockFace.WEST_NORTH_WEST
    case BlockRotation.NORTH_WEST       => SpigotBlockFace.NORTH_WEST
    case BlockRotation.NORTH_NORTH_WEST => SpigotBlockFace.NORTH_NORTH_WEST

    case _ => throw new IllegalArgumentException(s"$rotation")
  }
}
