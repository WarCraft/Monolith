package gg.warcraft.monolith.api.world.block

import enumeratum._

import scala.collection.immutable.IndexedSeq

sealed trait BlockAttachment extends EnumEntry

object BlockAttachment extends Enum[BlockAttachment] {
  val values: IndexedSeq[BlockAttachment] = findValues

  case object CEILING extends BlockAttachment
  case object FLOOR extends BlockAttachment
  case object WALL extends BlockAttachment
}
