package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.shape.{RailsShape, StairsShape}
import org.bukkit.Material
import org.bukkit.block.{Block => SpigotBlock}
import org.bukkit.block.data.{BlockData => SpigotBlockData, Rail => SpigotRails}
import org.bukkit.block.data.`type`.{Stairs => SpigotStairs}

class SpigotBlockShapeMapper {

  def map(block: SpigotBlock): BlockShape = {
    val data = block.getState.getBlockData
    def dataAs[T <: SpigotBlockData]: T = data.asInstanceOf[T]

    block.getType match {

      // RAILS
      case Material.RAIL | Material.ACTIVATOR_RAIL | Material.DETECTOR_RAIL |
          Material.POWERED_RAIL =>
        dataAs[SpigotRails].getShape match {
          case SpigotRails.Shape.NORTH_EAST  => RailsShape.NORTH_EAST
          case SpigotRails.Shape.NORTH_SOUTH => RailsShape.NORTH_SOUTH
          case SpigotRails.Shape.NORTH_WEST  => RailsShape.NORTH_WEST
          case SpigotRails.Shape.EAST_WEST   => RailsShape.EAST_WEST
          case SpigotRails.Shape.SOUTH_EAST  => RailsShape.SOUTH_EAST
          case SpigotRails.Shape.SOUTH_WEST  => RailsShape.SOUTH_WEST

          case SpigotRails.Shape.ASCENDING_NORTH => RailsShape.ASCENDING_NORTH
          case SpigotRails.Shape.ASCENDING_EAST  => RailsShape.ASCENDING_EAST
          case SpigotRails.Shape.ASCENDING_SOUTH => RailsShape.ASCENDING_SOUTH
          case SpigotRails.Shape.ASCENDING_WEST  => RailsShape.ASCENDING_WEST
        }

      // STAIRS
      case Material.ACACIA_STAIRS | Material.ANDESITE_STAIRS |
          Material.BIRCH_STAIRS | Material.BRICK_STAIRS |
          Material.COBBLESTONE_STAIRS | Material.DARK_OAK_STAIRS |
          Material.DARK_PRISMARINE_STAIRS | Material.DIORITE_STAIRS |
          Material.END_STONE_BRICK_STAIRS | Material.GRANITE_STAIRS |
          Material.JUNGLE_STAIRS | Material.MOSSY_COBBLESTONE_STAIRS |
          Material.MOSSY_STONE_BRICK_STAIRS | Material.NETHER_BRICK_STAIRS |
          Material.OAK_STAIRS | Material.POLISHED_ANDESITE_STAIRS |
          Material.POLISHED_DIORITE_STAIRS | Material.POLISHED_GRANITE_STAIRS |
          Material.PRISMARINE_BRICK_STAIRS | Material.PRISMARINE_STAIRS |
          Material.PURPUR_STAIRS | Material.QUARTZ_STAIRS |
          Material.RED_NETHER_BRICK_STAIRS | Material.RED_SANDSTONE_STAIRS |
          Material.SANDSTONE_STAIRS | Material.SMOOTH_QUARTZ_STAIRS |
          Material.SMOOTH_RED_SANDSTONE_STAIRS |
          Material.SMOOTH_SANDSTONE_STAIRS | Material.SPRUCE_STAIRS |
          Material.STONE_BRICK_STAIRS | Material.STONE_STAIRS =>
        dataAs[SpigotStairs].getShape match {
          case SpigotStairs.Shape.STRAIGHT    => StairsShape.STRAIGHT
          case SpigotStairs.Shape.INNER_LEFT  => StairsShape.INNER_LEFT
          case SpigotStairs.Shape.INNER_RIGHT => StairsShape.INNER_RIGHT
          case SpigotStairs.Shape.OUTER_LEFT  => StairsShape.OUTER_LEFT
          case SpigotStairs.Shape.OUTER_RIGHT => StairsShape.OUTER_RIGHT
        }

      case _ => throw new IllegalArgumentException(s"${block.getType}")
    }
  }

  def map(block: ShapedBlock[_], data: SpigotBlockData): Unit = {
    def dataAs[T <: SpigotBlockData]: T = data.asInstanceOf[T]

    block.shape match {
      case it: RailsShape  => dataAs[SpigotRails].setShape(map(it))
      case it: StairsShape => dataAs[SpigotStairs].setShape(map(it))
    }
  }

  def map(shape: RailsShape): SpigotRails.Shape = shape match {
    case RailsShape.NORTH_EAST  => SpigotRails.Shape.NORTH_EAST
    case RailsShape.NORTH_SOUTH => SpigotRails.Shape.NORTH_SOUTH
    case RailsShape.NORTH_WEST  => SpigotRails.Shape.NORTH_WEST
    case RailsShape.EAST_WEST   => SpigotRails.Shape.EAST_WEST
    case RailsShape.SOUTH_EAST  => SpigotRails.Shape.SOUTH_EAST
    case RailsShape.SOUTH_WEST  => SpigotRails.Shape.SOUTH_WEST

    case RailsShape.ASCENDING_NORTH => SpigotRails.Shape.ASCENDING_NORTH
    case RailsShape.ASCENDING_EAST  => SpigotRails.Shape.ASCENDING_EAST
    case RailsShape.ASCENDING_SOUTH => SpigotRails.Shape.ASCENDING_SOUTH
    case RailsShape.ASCENDING_WEST  => SpigotRails.Shape.ASCENDING_WEST
  }

  def map(shape: StairsShape): SpigotStairs.Shape = shape match {
    case StairsShape.STRAIGHT    => SpigotStairs.Shape.STRAIGHT
    case StairsShape.INNER_LEFT  => SpigotStairs.Shape.INNER_LEFT
    case StairsShape.INNER_RIGHT => SpigotStairs.Shape.INNER_RIGHT
    case StairsShape.OUTER_LEFT  => SpigotStairs.Shape.OUTER_LEFT
    case StairsShape.OUTER_RIGHT => SpigotStairs.Shape.OUTER_RIGHT
  }
}
