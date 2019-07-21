package gg.warcraft.monolith.api.world.block

import enumeratum._

import scala.collection.immutable.IndexedSeq

sealed trait BlockHinge extends EnumEntry

object BlockHinge extends Enum[BlockHinge] {
  val values: IndexedSeq[BlockHinge] = findValues

  case object LEFT extends BlockHinge
  case object RIGHT extends BlockHinge
}
