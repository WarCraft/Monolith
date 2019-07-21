package gg.warcraft.monolith.api.world.block

import enumeratum._

import scala.collection.immutable.IndexedSeq

sealed trait BlockColor extends EnumEntry

object BlockColor extends Enum[BlockColor] {
  val values: IndexedSeq[BlockColor] = findValues

  case object BLACK extends BlockColor
  case object BLUE extends BlockColor
  case object BROWN extends BlockColor
  case object CYAN extends BlockColor
  case object GRAY extends BlockColor
  case object GREEN extends BlockColor
  case object LIGHT_BLUE extends BlockColor
  case object LIGHT_GRAY extends BlockColor
  case object LIME extends BlockColor
  case object MAGENTA extends BlockColor
  case object ORANGE extends BlockColor
  case object PINK extends BlockColor
  case object PURPLE extends BlockColor
  case object RED extends BlockColor
  case object WHITE extends BlockColor
  case object YELLOW extends BlockColor
}
