package gg.warcraft.monolith.spigot.block

import java.util

import gg.warcraft.monolith.api.block.BlockFace
import org.bukkit.block.{BlockFace => SpigotBlockFace}

import scala.jdk.CollectionConverters._

class SpigotBlockFaceMapper {
  def map(face: SpigotBlockFace): BlockFace = face match {
    case SpigotBlockFace.NORTH => BlockFace.NORTH
    case SpigotBlockFace.EAST  => BlockFace.EAST
    case SpigotBlockFace.SOUTH => BlockFace.SOUTH
    case SpigotBlockFace.WEST  => BlockFace.WEST
    case SpigotBlockFace.UP    => BlockFace.UP
    case SpigotBlockFace.DOWN  => BlockFace.DOWN

    case it => throw new IllegalArgumentException(s"$it")
  }

  def map(face: BlockFace): SpigotBlockFace = face match {
    case BlockFace.NORTH => SpigotBlockFace.NORTH
    case BlockFace.EAST  => SpigotBlockFace.EAST
    case BlockFace.SOUTH => SpigotBlockFace.SOUTH
    case BlockFace.WEST  => SpigotBlockFace.WEST
    case BlockFace.UP    => SpigotBlockFace.UP
    case BlockFace.DOWN  => SpigotBlockFace.DOWN
  }

  def map(facing: util.Set[SpigotBlockFace]): Set[BlockFace] =
    facing.asScala.map(map).toSet

  def map(facing: Set[BlockFace]): util.Set[SpigotBlockFace] =
    facing.map(map).asJava
}
