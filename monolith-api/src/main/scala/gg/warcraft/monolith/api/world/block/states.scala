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

sealed trait CactusState extends EnumEntry with BlockState

object CactusState extends Enum[CactusState] {
  val values: IndexedSeq[CactusState] = findValues

  case object AGE_0 extends CactusState
  case object AGE_1 extends CactusState
  case object AGE_2 extends CactusState
  case object AGE_3 extends CactusState
  case object AGE_4 extends CactusState
  case object AGE_5 extends CactusState
  case object AGE_6 extends CactusState
  case object AGE_7 extends CactusState
  case object AGE_8 extends CactusState
  case object AGE_9 extends CactusState
  case object AGE_10 extends CactusState
  case object AGE_11 extends CactusState
  case object AGE_12 extends CactusState
  case object AGE_13 extends CactusState
  case object AGE_14 extends CactusState
  case object AGE_15 extends CactusState
}

sealed trait CakeState extends EnumEntry with BlockState

object CakeState extends Enum[CakeState] {
  val values: IndexedSeq[CakeState] = findValues

  case object EATEN_0 extends CakeState
  case object EATEN_1 extends CakeState
  case object EATEN_2 extends CakeState
  case object EATEN_3 extends CakeState
  case object EATEN_4 extends CakeState
  case object EATEN_5 extends CakeState
  case object EATEN_6 extends CakeState
}

sealed trait CocoaState extends EnumEntry with BlockState

object CocoaState extends Enum[CocoaState] {
  val values: IndexedSeq[CocoaState] = findValues

  case object AGE_0 extends CocoaState
  case object AGE_1 extends CocoaState
  case object AGE_2 extends CocoaState
}

sealed trait RailsState extends EnumEntry with BlockState

object RailsState extends Enum[RailsState] {
  val values: IndexedSeq[RailsState] = findValues

  case object NORTH_EAST extends RailsState
  case object NORTH_SOUTH extends RailsState
  case object NORTH_WEST extends RailsState
  case object EAST_WEST extends RailsState
  case object SOUTH_EAST extends RailsState
  case object SOUTH_WEST extends RailsState

  case object ASCENDING_NORTH extends RailsState
  case object ASCENDING_EAST extends RailsState
  case object ASCENDING_SOUTH extends RailsState
  case object ASCENDING_WEST extends RailsState
}

sealed trait SandstoneState extends EnumEntry with BlockState

object SandstoneState extends Enum[SandstoneState] {
  val values: IndexedSeq[SandstoneState] = findValues

  case object NORMAL extends SandstoneState
  case object CHISELED extends SandstoneState
  case object CUT extends SandstoneState
  case object SMOOTH extends SandstoneState
}

sealed trait WeightedPressurePlateState extends EnumEntry with BlockState

object WeightedPressurePlateState extends Enum[WeightedPressurePlateState] {
  val values: IndexedSeq[WeightedPressurePlateState] = findValues

  case object LEVEL_1 extends WeightedPressurePlateState
  case object LEVEL_2 extends WeightedPressurePlateState
  case object LEVEL_3 extends WeightedPressurePlateState
  case object LEVEL_4 extends WeightedPressurePlateState
  case object LEVEL_5 extends WeightedPressurePlateState
  case object LEVEL_6 extends WeightedPressurePlateState
  case object LEVEL_7 extends WeightedPressurePlateState
  case object LEVEL_8 extends WeightedPressurePlateState
  case object LEVEL_9 extends WeightedPressurePlateState
  case object LEVEL_10 extends WeightedPressurePlateState
  case object LEVEL_11 extends WeightedPressurePlateState
  case object LEVEL_12 extends WeightedPressurePlateState
  case object LEVEL_13 extends WeightedPressurePlateState
  case object LEVEL_14 extends WeightedPressurePlateState
  case object LEVEL_15 extends WeightedPressurePlateState
  case object LEVEL_16 extends WeightedPressurePlateState
}
