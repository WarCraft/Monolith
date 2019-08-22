package gg.warcraft.monolith.api.world.block

import enumeratum._

import scala.collection.immutable.IndexedSeq

sealed trait BlockRotation extends EnumEntry

object BlockRotation extends Enum[BlockRotation] {
  val values: IndexedSeq[BlockRotation] = findValues

  case object NORTH extends BlockRotation
  case object NORTH_NORTH_EAST extends BlockRotation
  case object NORTH_EAST extends BlockRotation
  case object EAST_NORTH_EAST extends BlockRotation
  case object EAST extends BlockRotation
  case object EAST_SOUTH_EAST extends BlockRotation
  case object SOUTH_EAST extends BlockRotation
  case object SOUTH_SOUTH_EAST extends BlockRotation
  case object SOUTH extends BlockRotation
  case object SOUTH_SOUTH_WEST extends BlockRotation
  case object SOUTH_WEST extends BlockRotation
  case object WEST_SOUTH_WEST extends BlockRotation
  case object WEST extends BlockRotation
  case object WEST_NORTH_WEST extends BlockRotation
  case object NORTH_WEST extends BlockRotation
  case object NORTH_NORTH_WEST extends BlockRotation
}
