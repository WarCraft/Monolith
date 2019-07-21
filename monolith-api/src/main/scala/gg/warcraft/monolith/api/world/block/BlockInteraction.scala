package gg.warcraft.monolith.api.world.block

import enumeratum._

import scala.collection.immutable.IndexedSeq

sealed trait BlockInteraction extends EnumEntry

object BlockInteraction extends Enum[BlockInteraction] {
  val values: IndexedSeq[BlockInteraction] = findValues

  case object LEFT_CLICK extends BlockInteraction
  case object RIGHT_CLICK extends BlockInteraction
  case object TRIGGER extends BlockInteraction
}
