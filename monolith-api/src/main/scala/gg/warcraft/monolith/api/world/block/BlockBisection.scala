package gg.warcraft.monolith.api.world.block

import enumeratum._

import scala.collection.immutable.IndexedSeq

sealed trait BlockBisection extends EnumEntry

object BlockBisection extends Enum[BlockBisection] {
  val values: IndexedSeq[BlockBisection] = findValues

  case object TOP extends BlockBisection
  case object BOTTOM extends BlockBisection
}
