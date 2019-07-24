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

sealed trait SandstoneState extends EnumEntry with BlockState

object SandstoneState extends Enum[SandstoneState] {
  val values: IndexedSeq[SandstoneState] = findValues

  case object NORMAL extends SandstoneState
  case object CHISELED extends SandstoneState
  case object CUT extends SandstoneState
  case object SMOOTH extends SandstoneState
}
