package gg.warcraft.monolith.api.world.block

import enumeratum._

import scala.collection.immutable.IndexedSeq

sealed trait BlockFace extends EnumEntry

object BlockFace extends Enum[BlockFace] {
  val values: IndexedSeq[BlockFace] = findValues

  case object NORTH extends BlockFace
  case object EAST extends BlockFace
  case object SOUTH extends BlockFace
  case object WEST extends BlockFace
  case object UP extends BlockFace
  case object DOWN extends BlockFace
}
