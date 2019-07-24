package gg.warcraft.monolith.api.world.block

import enumeratum._

import scala.collection.immutable.IndexedSeq

sealed trait BlockOrientation extends EnumEntry

object BlockOrientation extends Enum[BlockOrientation] {
  val values: IndexedSeq[BlockOrientation] = findValues

  case object X_AXIS extends BlockOrientation
  case object Y_AXIS extends BlockOrientation
  case object Z_AXIS extends BlockOrientation
}
