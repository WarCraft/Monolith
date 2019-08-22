package gg.warcraft.monolith.api.world.block

import enumeratum._

import scala.collection.immutable.IndexedSeq

trait BlockState

// ANVIL
sealed trait AnvilState extends EnumEntry with BlockState

object AnvilState extends Enum[AnvilState] {
  val values: IndexedSeq[AnvilState] = findValues

  case object PRISTINE extends AnvilState
  case object CHIPPED extends AnvilState
  case object DAMAGED extends AnvilState
}

// BAMBOO
sealed trait BambooState extends EnumEntry with BlockState

object BambooState extends Enum[BambooState] {
  val values: IndexedSeq[BambooState] = findValues

  case object AGE_0 extends BambooState
  case object AGE_1 extends BambooState
}

// BEETROOT
sealed trait BeetrootState extends EnumEntry with BlockState

object BeetrootState extends Enum[BeetrootState] {
  val values: IndexedSeq[BeetrootState] = findValues

  case object AGE_0 extends BeetrootState
  case object AGE_1 extends BeetrootState
  case object AGE_2 extends BeetrootState
  case object AGE_3 extends BeetrootState
}

// CACTUS
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

// CAKE
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

// CARROT
sealed trait CarrotState extends EnumEntry with BlockState

object CarrotState extends Enum[CarrotState] {
  val values: IndexedSeq[CarrotState] = findValues

  case object AGE_0 extends CarrotState
  case object AGE_1 extends CarrotState
  case object AGE_2 extends CarrotState
  case object AGE_3 extends CarrotState
  case object AGE_4 extends CarrotState
  case object AGE_5 extends CarrotState
  case object AGE_6 extends CarrotState
  case object AGE_7 extends CarrotState
}

// CAULDRON
sealed trait CauldronState extends EnumEntry with BlockState

object CauldronState extends Enum[CauldronState] {
  val values: IndexedSeq[CauldronState] = findValues

  case object LEVEL_0 extends CauldronState
  case object LEVEL_1 extends CauldronState
  case object LEVEL_2 extends CauldronState
  case object LEVEL_3 extends CauldronState
}

// CHORUS_FLOWER
sealed trait ChorusFlowerState extends EnumEntry with BlockState

object ChorusFlowerState extends Enum[ChorusFlowerState] {
  val values: IndexedSeq[ChorusFlowerState] = findValues

  case object AGE_0 extends ChorusFlowerState
  case object AGE_1 extends ChorusFlowerState
  case object AGE_2 extends ChorusFlowerState
  case object AGE_3 extends ChorusFlowerState
  case object AGE_4 extends ChorusFlowerState
  case object AGE_5 extends ChorusFlowerState
}

// COCOA
sealed trait CocoaState extends EnumEntry with BlockState

object CocoaState extends Enum[CocoaState] {
  val values: IndexedSeq[CocoaState] = findValues

  case object AGE_0 extends CocoaState
  case object AGE_1 extends CocoaState
  case object AGE_2 extends CocoaState
}

// COMPARATOR
sealed trait ComparatorState extends EnumEntry with BlockState

object ComparatorState extends Enum[ComparatorState] {
  val values: IndexedSeq[ComparatorState] = findValues

  case object COMPARE extends ComparatorState
  case object SUBTRACT extends ComparatorState
}

// COMPOSTER
sealed trait ComposterState extends EnumEntry with BlockState

object ComposterState extends Enum[ComposterState] {
  val values: IndexedSeq[ComposterState] = findValues

  case object LEVEL_0 extends ComposterState
  case object LEVEL_1 extends ComposterState
  case object LEVEL_2 extends ComposterState
  case object LEVEL_3 extends ComposterState
  case object LEVEL_4 extends ComposterState
  case object LEVEL_5 extends ComposterState
  case object LEVEL_6 extends ComposterState
  case object LEVEL_7 extends ComposterState
  case object LEVEL_8 extends ComposterState
}

// NETHER_WART
sealed trait NetherWartState extends EnumEntry with BlockState

object NetherWartState extends Enum[NetherWartState] {
  val values: IndexedSeq[NetherWartState] = findValues

  case object AGE_0 extends NetherWartState
  case object AGE_1 extends NetherWartState
  case object AGE_2 extends NetherWartState
  case object AGE_3 extends NetherWartState
}

// POTATO
sealed trait PotatoState extends EnumEntry with BlockState

object PotatoState extends Enum[PotatoState] {
  val values: IndexedSeq[PotatoState] = findValues

  case object AGE_0 extends PotatoState
  case object AGE_1 extends PotatoState
  case object AGE_2 extends PotatoState
  case object AGE_3 extends PotatoState
  case object AGE_4 extends PotatoState
  case object AGE_5 extends PotatoState
  case object AGE_6 extends PotatoState
  case object AGE_7 extends PotatoState
}

// PUMPKIN_STEM
sealed trait PumpkinStemState extends EnumEntry with BlockState

object PumpkinStemState extends Enum[PumpkinStemState] {
  val values: IndexedSeq[PumpkinStemState] = findValues

  case object AGE_0 extends PumpkinStemState
  case object AGE_1 extends PumpkinStemState
  case object AGE_2 extends PumpkinStemState
  case object AGE_3 extends PumpkinStemState
  case object AGE_4 extends PumpkinStemState
  case object AGE_5 extends PumpkinStemState
  case object AGE_6 extends PumpkinStemState
  case object AGE_7 extends PumpkinStemState
}

// RAILS
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

// SANDSTONE
sealed trait SandstoneState extends EnumEntry with BlockState

object SandstoneState extends Enum[SandstoneState] {
  val values: IndexedSeq[SandstoneState] = findValues

  case object NORMAL extends SandstoneState
  case object CHISELED extends SandstoneState
  case object CUT extends SandstoneState
  case object SMOOTH extends SandstoneState
}

// SAPLING
sealed trait SaplingState extends EnumEntry with BlockState

object SaplingState extends Enum[SaplingState] {
  val values: IndexedSeq[SaplingState] = findValues

  case object AGE_0 extends SaplingState
  case object AGE_1 extends SaplingState
}

// SEA_PICKLE
sealed trait SeaPickleState extends EnumEntry with BlockState

object SeaPickleState extends Enum[SeaPickleState] {
  val values: IndexedSeq[SeaPickleState] = findValues

  case object COUNT_1 extends SeaPickleState
  case object COUNT_2 extends SeaPickleState
  case object COUNT_3 extends SeaPickleState
  case object COUNT_4 extends SeaPickleState
}

// STAIRS
sealed trait StairsState extends EnumEntry with BlockState

object StairsState extends Enum[StairsState] {
  val values: IndexedSeq[StairsState] = findValues

  case object STRAIGHT extends StairsState
  case object INNER_LEFT extends StairsState
  case object INNER_RIGHT extends StairsState
  case object OUTER_LEFT extends StairsState
  case object OUTER_RIGHT extends StairsState
}

// STRUCTURE_BLOCK
sealed trait StructureBlockState extends EnumEntry with BlockState

object StructureBlockState extends Enum[StructureBlockState] {
  val values: IndexedSeq[StructureBlockState] = findValues

  case object CORNER extends StructureBlockState
  case object DATA extends StructureBlockState
  case object LOAD extends StructureBlockState
  case object SAVE extends StructureBlockState
}

// SUGAR_CANE
sealed trait SugarCaneState extends EnumEntry with BlockState

object SugarCaneState extends Enum[SugarCaneState] {
  val values: IndexedSeq[SugarCaneState] = findValues

  case object AGE_0 extends SugarCaneState
  case object AGE_1 extends SugarCaneState
  case object AGE_2 extends SugarCaneState
  case object AGE_3 extends SugarCaneState
  case object AGE_4 extends SugarCaneState
  case object AGE_5 extends SugarCaneState
  case object AGE_6 extends SugarCaneState
  case object AGE_7 extends SugarCaneState
  case object AGE_8 extends SugarCaneState
  case object AGE_9 extends SugarCaneState
  case object AGE_10 extends SugarCaneState
  case object AGE_11 extends SugarCaneState
  case object AGE_12 extends SugarCaneState
  case object AGE_13 extends SugarCaneState
  case object AGE_14 extends SugarCaneState
  case object AGE_15 extends SugarCaneState
}

// SWEET_BERRY
sealed trait SweetBerryState extends EnumEntry with BlockState

object SweetBerryState extends Enum[SweetBerryState] {
  val values: IndexedSeq[SweetBerryState] = findValues

  case object AGE_0 extends SweetBerryState
  case object AGE_1 extends SweetBerryState
  case object AGE_2 extends SweetBerryState
  case object AGE_3 extends SweetBerryState
}

// WEIGHTED_PRESSURE_PLATE
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

// WHEAT
sealed trait WheatState extends EnumEntry with BlockState

object WheatState extends Enum[WheatState] {
  val values: IndexedSeq[WheatState] = findValues

  case object AGE_0 extends WheatState
  case object AGE_1 extends WheatState
  case object AGE_2 extends WheatState
  case object AGE_3 extends WheatState
  case object AGE_4 extends WheatState
  case object AGE_5 extends WheatState
  case object AGE_6 extends WheatState
  case object AGE_7 extends WheatState
}
