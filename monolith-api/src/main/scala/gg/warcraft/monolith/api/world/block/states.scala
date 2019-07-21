package gg.warcraft.monolith.api.world.block

import enumeratum._

import scala.collection.immutable.IndexedSeq

trait BlockState

sealed trait AnvilState extends EnumEntry with BlockState

object AnvilState extends Enum[AnvilState] {
  val values: IndexedSeq[AnvilState] = findValues

  case object PRISTINE extends AnvilState
  case object CHIPPED extends AnvilState
  case object DAMAGED extends AnvilState
}
