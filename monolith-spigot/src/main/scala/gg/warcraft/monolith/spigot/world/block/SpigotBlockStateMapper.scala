package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.state._
import org.bukkit.Material
import org.bukkit.block.{ Block => SpigotBlock }
import org.bukkit.block.data.{ Ageable, AnaloguePowerable, Levelled, Rail => SpigotRail }
import org.bukkit.block.data.`type`.{ Cake => SpigotCake, Comparator => SpigotComparator, NoteBlock => SpigotNoteBlock, Repeater => SpigotRepeater, Sapling => SpigotSapling, SeaPickle => SpigotSeaPickle, Stairs => SpigotStairs, StructureBlock => SpigotStructureBlock, TurtleEgg => SpigotTurtleEgg }

class SpigotBlockStateMapper {

  def map(block: SpigotBlock): BlockState = {
    val state = block.getState.getBlockData

    lazy val age = s"AGE_${ state.asInstanceOf[Ageable].getAge }"
    lazy val level = s"LEVEL_${ state.asInstanceOf[Levelled].getLevel }"
    lazy val power = s"POWER_${ state.asInstanceOf[AnaloguePowerable].getPower }"

    block.getType match {
      case Material.BAMBOO => BambooState.valueOf(age)
      case Material.BEETROOTS => BeetrootState.valueOf(age)
      case Material.CACTUS => CactusState.valueOf(age)
      case Material.CHORUS_FLOWER => ChorusFlowerState.valueOf(age)
      case Material.COCOA => CocoaState.valueOf(age)
      case Material.COMPOSTER => ComposterState.valueOf(level)
      case Material.KELP_PLANT => KelpState.valueOf(age)
      case Material.LAVA => LavaState.valueOf(level)
      case Material.NETHER_WART => NetherWartState.valueOf(age)
      case Material.POTATOES => PotatoState.valueOf(age)
      case Material.REDSTONE_WIRE => RedstoneWireState.valueOf(power)
      case Material.SUGAR_CANE => SugarCaneState.valueOf(age)
      case Material.SWEET_BERRY_BUSH => SweetBerryState.valueOf(age)
      case Material.WATER => WaterState.valueOf(level)
      case Material.WHEAT => WheatState.valueOf(age)

      // ANVIL
      case Material.ANVIL => AnvilState.PRISTINE
      case Material.CHIPPED_ANVIL => AnvilState.CHIPPED
      case Material.DAMAGED_ANVIL => AnvilState.DAMAGED

      // CAKE
      case Material.CAKE =>
        val bites = state.asInstanceOf[SpigotCake].getBites
        CakeState.valueOf(s"EATEN_$bites")

      // COMPARATOR
      case Material.COMPARATOR =>
        val mode = state.asInstanceOf[SpigotComparator].getMode
        mapComparatorMode(mode)

      // MELON_STEM
      case Material.MELON_STEM | Material.ATTACHED_MELON_STEM =>
        MelonStemState.valueOf(age)

      // NOTE_BLOCK
      case Material.NOTE_BLOCK =>
        val note = state.asInstanceOf[SpigotNoteBlock].getNote
        NoteBlockState.valueOf(s"NOTE_$note")

      // PUMPKIN_STEM
      case Material.PUMPKIN_STEM | Material.ATTACHED_PUMPKIN_STEM =>
        PumpkinStemState.valueOf(age)

      // RAIL
      case Material.RAIL | Material.ACTIVATOR_RAIL | Material.DETECTOR_RAIL | Material.POWERED_RAIL =>
        val shape = state.asInstanceOf[SpigotRail].getShape
        mapRailShape(shape)

      // REPEATER
      case Material.REPEATER =>
        val delay = state.asInstanceOf[SpigotRepeater].getDelay
        RepeaterState.valueOf(s"DELAY_$delay")

      // SANDSTONE TODO add slab stairs wall etc, stairs need their own case due to shape also going on the state
      case Material.SANDSTONE | Material.RED_SANDSTONE =>
        SandstoneState.NORMAL

      case Material.CHISELED_SANDSTONE | Material.CHISELED_RED_SANDSTONE =>
        SandstoneState.CHISELED

      case Material.CUT_SANDSTONE | Material.CUT_RED_SANDSTONE =>
        SandstoneState.CUT

      case Material.SMOOTH_SANDSTONE | Material.SMOOTH_RED_SANDSTONE =>
        SandstoneState.SMOOTH

      // SAPLING
      case Material.BAMBOO_SAPLING |
           Material.ACACIA_SAPLING | Material.BIRCH_SAPLING | Material.DARK_OAK_SAPLING |
           Material.JUNGLE_SAPLING | Material.OAK_SAPLING | Material.SPRUCE_SAPLING =>
        val stage = state.asInstanceOf[SpigotSapling].getStage
        SaplingState.valueOf(s"AGE_$stage")

      // SEA_PICKLE
      case Material.SEA_PICKLE =>
        val pickles = state.asInstanceOf[SpigotSeaPickle].getPickles
        SeaPickleState.valueOf(s"COUNT_$pickles")

      // STRUCTURE_BLOCK
      case Material.STRUCTURE_BLOCK =>
        val mode = state.asInstanceOf[SpigotStructureBlock].getMode
        mapStructureBlockMode(mode)

      // TURTLE_EGG
      case Material.TURTLE_EGG =>
        val hatch = state.asInstanceOf[SpigotTurtleEgg].getHatch
        val eggs = state.asInstanceOf[SpigotTurtleEgg].getEggs
        TurtleEggState(
          TurtleEggAge.valueOf(s"AGE_$hatch"),
          TurtleEggCount.valueOf(s"COUNT_$eggs"),
        )

      // WEIGHTED_PRESSURE_PLATE
      case Material.LIGHT_WEIGHTED_PRESSURE_PLATE | Material.HEAVY_WEIGHTED_PRESSURE_PLATE =>
        WeightedPressurePlateState.valueOf(power)

      case _ => throw new IllegalArgumentException(s"Failed to map state for material: ${ block.getType }")
    }
  }

  def mapComparatorMode(mode: SpigotComparator.Mode): ComparatorState = mode match {
    case SpigotComparator.Mode.COMPARE => ComparatorState.COMPARE
    case SpigotComparator.Mode.SUBTRACT => ComparatorState.SUBTRACT
  }

  def mapRailShape(shape: SpigotRail.Shape): RailsState = shape match {
    case SpigotRail.Shape.NORTH_EAST => RailsState.NORTH_EAST
    case SpigotRail.Shape.NORTH_SOUTH => RailsState.NORTH_SOUTH
    case SpigotRail.Shape.NORTH_WEST => RailsState.NORTH_WEST
    case SpigotRail.Shape.EAST_WEST => RailsState.EAST_WEST
    case SpigotRail.Shape.SOUTH_EAST => RailsState.SOUTH_EAST
    case SpigotRail.Shape.SOUTH_WEST => RailsState.SOUTH_WEST

    case SpigotRail.Shape.ASCENDING_NORTH => RailsState.ASCENDING_NORTH
    case SpigotRail.Shape.ASCENDING_EAST => RailsState.ASCENDING_EAST
    case SpigotRail.Shape.ASCENDING_SOUTH => RailsState.ASCENDING_SOUTH
    case SpigotRail.Shape.ASCENDING_WEST => RailsState.ASCENDING_WEST
  }

  // TODO is this gonna clash with stairs that already have a chipped state?
  def mapStairsShape(shape: SpigotStairs.Shape): StairsState = shape match {
    case SpigotStairs.Shape.STRAIGHT => StairsState.STRAIGHT
    case SpigotStairs.Shape.INNER_LEFT => StairsState.INNER_LEFT
    case SpigotStairs.Shape.INNER_RIGHT => StairsState.INNER_RIGHT
    case SpigotStairs.Shape.OUTER_LEFT => StairsState.OUTER_LEFT
    case SpigotStairs.Shape.OUTER_RIGHT => StairsState.OUTER_RIGHT
  }

  def mapStructureBlockMode(mode: SpigotStructureBlock.Mode): StructureBlockState = mode match {
    case SpigotStructureBlock.Mode.CORNER => StructureBlockState.CORNER
    case SpigotStructureBlock.Mode.DATA => StructureBlockState.DATA
    case SpigotStructureBlock.Mode.LOAD => StructureBlockState.LOAD
    case SpigotStructureBlock.Mode.SAVE => StructureBlockState.SAVE
  }
}
