package gg.warcraft.monolith.api.world.block

import enumeratum._

import scala.collection.immutable.IndexedSeq

trait BlockMaterial

trait ButtonMaterial extends BlockMaterial

trait DoorMaterial extends BlockMaterial

trait FenceMaterial extends BlockMaterial

trait FlowerPotMaterial extends BlockMaterial

trait MineralMaterial extends BlockMaterial

trait OreMaterial extends BlockMaterial

trait PillarMaterial extends BlockMaterial

trait PressurePlateMaterial extends BlockMaterial

trait SaplingMaterial extends FlowerPotMaterial

trait SlabMaterial extends BlockMaterial

trait StairsMaterial extends BlockMaterial

trait TrapdoorMaterial extends BlockMaterial

trait WallMaterial extends BlockMaterial

// AIR
sealed trait AirMaterial extends EnumEntry with BlockMaterial

object AirMaterial extends Enum[AirMaterial] {
  val values: IndexedSeq[AirMaterial] = findValues

  case object NORMAL extends AirMaterial
  case object CAVE extends AirMaterial
  case object VOID extends AirMaterial
}

// BAMBOO
sealed trait BambooMaterial extends EnumEntry with SaplingMaterial

object BambooMaterial extends Enum[BambooMaterial] {
  val values: IndexedSeq[BambooMaterial] = findValues

  case object BAMBOO extends BambooMaterial
}

// BAMBOO
sealed trait BambooLeavesMaterial extends EnumEntry with BlockMaterial

object BambooLeavesMaterial extends Enum[BambooLeavesMaterial] {
  val values: IndexedSeq[BambooLeavesMaterial] = findValues

  case object NONE extends BambooLeavesMaterial
  case object SMALL extends BambooLeavesMaterial
  case object LARGE extends BambooLeavesMaterial
}

// BRICK
sealed trait BrickMaterial extends EnumEntry
  with FenceMaterial with SlabMaterial with StairsMaterial with WallMaterial

object BrickMaterial extends Enum[BrickMaterial] {
  val values: IndexedSeq[BrickMaterial] = findValues

  case object BRICK extends BrickMaterial
  case object NETHER_BRICK extends BrickMaterial
  case object RED_NETHER_BRICK extends BrickMaterial
}

// COMMAND_BLOCK
sealed trait CommandBlockMaterial extends EnumEntry with BlockMaterial

object CommandBlockMaterial extends Enum[CommandBlockMaterial] {
  val values: IndexedSeq[CommandBlockMaterial] = findValues

  case object NORMAL extends CommandBlockMaterial
  case object CHAIN extends CommandBlockMaterial
  case object REPEATING extends CommandBlockMaterial
}

// CORAL
sealed trait CoralMaterial extends EnumEntry with BlockMaterial

object CoralMaterial extends Enum[CoralMaterial] {
  val values: IndexedSeq[CoralMaterial] = findValues

  case object BRAIN extends CoralMaterial
  case object BUBBLE extends CoralMaterial
  case object FIRE extends CoralMaterial
  case object HORN extends CoralMaterial
  case object TUBE extends CoralMaterial

  case object DEAD_BRAIN extends CoralMaterial
  case object DEAD_BUBBLE extends CoralMaterial
  case object DEAD_FIRE extends CoralMaterial
  case object DEAD_HORN extends CoralMaterial
  case object DEAD_TUBE extends CoralMaterial
}

// FLOWER
sealed trait FlowerMaterial extends EnumEntry with FlowerPotMaterial

object FlowerMaterial extends Enum[FlowerMaterial] {
  val values: IndexedSeq[FlowerMaterial] = findValues

  case object ALLIUM extends FlowerMaterial
  case object AZURE_BLUET extends FlowerMaterial
  case object BLUE_ORCHID extends FlowerMaterial
  case object CORNFLOWER extends FlowerMaterial
  case object DANDELION extends FlowerMaterial
  case object LILY_OF_THE_VALLEY extends FlowerMaterial
  case object ORANGE_TULIP extends FlowerMaterial
  case object OXEYE_DAISY extends FlowerMaterial
  case object PINK_TULIP extends FlowerMaterial
  case object POPPY extends FlowerMaterial
  case object RED_TULIP extends FlowerMaterial
  case object WHITE_TULIP extends FlowerMaterial
  case object WITHER_ROSE extends FlowerMaterial
}

// ICE
sealed trait IceMaterial extends EnumEntry with BlockMaterial

object IceMaterial extends Enum[IceMaterial] {
  val values: IndexedSeq[IceMaterial] = findValues

  case object NORMAL extends IceMaterial
  case object PACKED extends IceMaterial
  case object BLUE extends IceMaterial
}

// IRON
sealed trait IronMaterial extends EnumEntry
  with DoorMaterial with TrapdoorMaterial

object IronMaterial extends Enum[IronMaterial] {
  val values: IndexedSeq[IronMaterial] = findValues

  case object IRON extends IronMaterial
}

// MOB_HEAD
sealed trait MobHeadMaterial extends EnumEntry with BlockMaterial

object MobHeadMaterial extends Enum[MobHeadMaterial] {
  val values: IndexedSeq[MobHeadMaterial] = findValues

  case object CREEPER extends MobHeadMaterial
  case object DRAGON extends MobHeadMaterial
  case object PLAYER extends MobHeadMaterial
  case object SKELETON extends MobHeadMaterial
  case object WITHER_SKELETON extends MobHeadMaterial
  case object ZOMBIE extends MobHeadMaterial
}

// MUSHROOM
sealed trait MushroomMaterial extends EnumEntry with BlockMaterial

object MushroomMaterial extends Enum[MushroomMaterial] {
  val values: IndexedSeq[MushroomMaterial] = findValues

  case object BROWN extends MushroomMaterial
  case object RED extends MushroomMaterial
}

// MUSHROOM_BLOCK
sealed trait MushroomBlockMaterial extends EnumEntry with BlockMaterial

object MushroomBlockMaterial extends Enum[MushroomBlockMaterial] {
  val values: IndexedSeq[MushroomBlockMaterial] = findValues

  case object BROWN extends MushroomBlockMaterial
  case object RED extends MushroomBlockMaterial
  case object STEM extends MushroomBlockMaterial
}

// NOTE_BLOCK
sealed trait NoteBlockMaterial extends EnumEntry with FlowerPotMaterial

object NoteBlockMaterial extends Enum[NoteBlockMaterial] {
  val values: IndexedSeq[NoteBlockMaterial] = findValues

  case object BANJO extends NoteBlockMaterial
  case object BASEDRUM extends NoteBlockMaterial
  case object BASS extends NoteBlockMaterial
  case object BELL extends NoteBlockMaterial
  case object BIT extends NoteBlockMaterial
  case object CHIME extends NoteBlockMaterial
  case object COW_BELL extends NoteBlockMaterial
  case object DIDGERIDOO extends NoteBlockMaterial
  case object FLUTE extends NoteBlockMaterial
  case object GUITAR extends NoteBlockMaterial
  case object HARP extends NoteBlockMaterial
  case object HAT extends NoteBlockMaterial
  case object IRON_XYLOPHONE extends NoteBlockMaterial
  case object PLING extends NoteBlockMaterial
  case object SNARE extends NoteBlockMaterial
  case object XYLOPHONE extends NoteBlockMaterial
}

// PRISMARINE
sealed trait PrismarineMaterial extends EnumEntry
  with SlabMaterial with StairsMaterial

object PrismarineMaterial extends Enum[PrismarineMaterial] {
  val values: IndexedSeq[PrismarineMaterial] = findValues

  case object PRISMARINE extends PrismarineMaterial
  case object DARK_PRISMARINE extends PrismarineMaterial
  case object PRISMARINE_BRICK extends PrismarineMaterial
}

// PURPUR
sealed trait PurpurMaterial extends EnumEntry
  with PillarMaterial with SlabMaterial with StairsMaterial

object PurpurMaterial extends Enum[PurpurMaterial] {
  val values: IndexedSeq[PurpurMaterial] = findValues

  case object PURPUR extends PurpurMaterial
}

// QUARTZ
sealed trait QuartzMaterial extends EnumEntry
  with PillarMaterial with SlabMaterial with StairsMaterial

object QuartzMaterial extends Enum[QuartzMaterial] {
  val values: IndexedSeq[QuartzMaterial] = findValues

  case object QUARTZ extends QuartzMaterial
  case object CHISELED_QUARTZ extends QuartzMaterial
  case object SMOOTH_QUARTZ extends QuartzMaterial
}

// RAILS
sealed trait RailsMaterial extends EnumEntry with BlockMaterial

object RailsMaterial extends Enum[RailsMaterial] {
  val values: IndexedSeq[RailsMaterial] = findValues

  case object NORMAL extends RailsMaterial
  case object ACTIVATOR extends RailsMaterial
  case object DETECTOR extends RailsMaterial
  case object POWERED extends RailsMaterial
}

// RESOURCE
sealed trait ResourceMaterial extends EnumEntry
  with MineralMaterial with OreMaterial

object ResourceMaterial extends Enum[ResourceMaterial] {
  val values: IndexedSeq[ResourceMaterial] = findValues

  case object COAL extends ResourceMaterial
  case object DIAMOND extends ResourceMaterial
  case object EMERALD extends ResourceMaterial
  case object GOLD extends ResourceMaterial
  case object IRON extends ResourceMaterial
  case object LAPIS_LAZULI extends ResourceMaterial
  case object NETHER_QUARTZ extends ResourceMaterial
  case object REDSTONE extends ResourceMaterial
}

// SAND
sealed trait SandMaterial extends EnumEntry with BlockMaterial

object SandMaterial extends Enum[SandMaterial] {
  val values: IndexedSeq[SandMaterial] = findValues

  case object SAND extends SandMaterial
  case object RED_SAND extends SandMaterial
  case object SOUL_SAND extends SandMaterial
}

// SANDSTONE
sealed trait SandstoneMaterial extends EnumEntry
  with SlabMaterial with StairsMaterial with WallMaterial

object SandstoneMaterial extends Enum[SandstoneMaterial] {
  val values: IndexedSeq[SandstoneMaterial] = findValues

  case object SANDSTONE extends SandstoneMaterial
  case object RED_SANDSTONE extends SandstoneMaterial
}

// STONE
sealed trait StoneMaterial extends EnumEntry
  with ButtonMaterial with PressurePlateMaterial with SlabMaterial
  with StairsMaterial with WallMaterial

object StoneMaterial extends Enum[StoneMaterial] {
  val values: IndexedSeq[StoneMaterial] = findValues

  case object STONE extends StoneMaterial
  case object SMOOTH_STONE extends StoneMaterial

  case object STONE_BRICK extends StoneMaterial
  case object CHISELED_STONE_BRICK extends StoneMaterial
  case object CRACKED_STONE_BRICK extends StoneMaterial
  case object MOSSY_STONE_BRICK extends StoneMaterial

  case object COBBLESTONE extends StoneMaterial
  case object MOSSY_COBBLESTONE extends StoneMaterial

  case object ANDESITE extends StoneMaterial
  case object DIORITE extends StoneMaterial
  case object GRANITE extends StoneMaterial
  case object POLISHED_ANDESITE extends StoneMaterial
  case object POLISHED_DIORITE extends StoneMaterial
  case object POLISHED_GRANITE extends StoneMaterial

  case object END_STONE extends StoneMaterial
  case object END_STONE_BRICK extends StoneMaterial
}

// WEIGHTED_PRESSURE_PLATE
sealed trait WeightedPressurePlateMaterial extends EnumEntry with BlockMaterial

object WeightedPressurePlateMaterial extends Enum[WeightedPressurePlateMaterial] {
  val values: IndexedSeq[WeightedPressurePlateMaterial] = findValues

  case object LIGHT extends WeightedPressurePlateMaterial
  case object HEAVY extends WeightedPressurePlateMaterial
}

// WOOD
sealed trait WoodMaterial extends EnumEntry
  with ButtonMaterial with DoorMaterial with FenceMaterial with SaplingMaterial
  with SlabMaterial with StairsMaterial with TrapdoorMaterial

object WoodMaterial extends Enum[WoodMaterial] {
  val values: IndexedSeq[WoodMaterial] = findValues

  case object ACACIA extends WoodMaterial
  case object BIRCH extends WoodMaterial
  case object DARK_OAK extends WoodMaterial
  case object JUNGLE extends WoodMaterial
  case object OAK extends WoodMaterial
  case object SPRUCE extends WoodMaterial
}
