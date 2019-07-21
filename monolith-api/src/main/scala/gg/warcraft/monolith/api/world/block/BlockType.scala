package gg.warcraft.monolith.api.world.block

import enumeratum._

import scala.collection.immutable.IndexedSeq

sealed trait BlockType extends EnumEntry {
  def isSolid: Boolean = entryName match {
    // TODO add AIR, DEAD_SHRUB, TALL_GRASS, FERN, DEAD_BUSH
    case "CORAL" | "CORAL_FAN" | "CORAL_WALL_FAN" => false
    case _ => true
  }

  def isLiquid: Boolean = entryName match {
    case "LAVA" | "WATER" => true
    case _ => false
  }
}

object BlockType extends Enum[BlockType] {
  val values: IndexedSeq[BlockType] = findValues

  case object BED extends BlockType
  case object BRICK extends BlockType
  case object BUTTON extends BlockType
  case object CHEST extends BlockType
  case object CONCRETE extends BlockType
  case object CONCRETE_POWDER extends BlockType
  case object CORAL extends BlockType
  case object CORAL_BLOCK extends BlockType
  case object CORAL_FAN extends BlockType
  case object CORAL_WALL_FAN extends BlockType
  case object DOOR extends BlockType
  case object FENCE extends BlockType
  case object GATE extends BlockType
  case object GLAZED_TERRACOTTA extends BlockType
  case object HARDENED_CLAY extends BlockType
  case object LAVA extends BlockType
  case object LEVER extends BlockType
  case object PILLAR extends BlockType
  case object PISTON extends BlockType
  case object PRISMARINE extends BlockType
  case object PURPUR extends BlockType
  case object QUARTZ extends BlockType
  case object SAND extends BlockType
  case object SANDSTONE extends BlockType
  case object SHULKER_BOX extends BlockType
  case object SLAB extends BlockType
  case object STAINED_GLASS extends BlockType
  case object STAINED_GLASS_PANE extends BlockType
  case object STAIRS extends BlockType
  case object STONE extends BlockType
  case object TRAPDOOR extends BlockType
  case object WALL extends BlockType
  case object WATER extends BlockType
  case object WOOL extends BlockType
}
