package gg.warcraft.monolith.spigot.world.block

import com.google.inject.Inject
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.state._
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.Material
import org.bukkit.block.data.Ageable
import org.bukkit.block.data.`type`.{Cake => SpigotCake, Comparator => SpigotComparator, Sapling => SpigotSapling, StructureBlock => SpigotStructureBlock}
import org.bukkit.block.{Block => SpigotBlock}

class SpigotBlockStateMapper @Inject()(
  private val locationMapper: SpigotLocationMapper
) {

  def map(block: SpigotBlock): BlockState = {
    val state = block.getState

    lazy val age = s"AGE_${block.getState.asInstanceOf[Ageable].getAge}"

    block.getType match {
      case Material.ANVIL => AnvilState.PRISTINE
      case Material.CHIPPED_ANVIL => AnvilState.CHIPPED
      case Material.DAMAGED_ANVIL => AnvilState.DAMAGED

      case Material.BAMBOO => BambooState.valueOf(age)
      case Material.BEETROOTS => BeetrootState.valueOf(age)
      case Material.CACTUS => CactusState.valueOf(age)

      case Material.CAKE =>
        val bites = state.asInstanceOf[SpigotCake].getBites
        CakeState.valueOf(s"EATEN_$bites")

      case Material.CHORUS_FLOWER => ChorusFlowerState.valueOf(age)
      case Material.COCOA => CocoaState.valueOf(age)

      case Material.COMPARATOR =>
        val mode = state.asInstanceOf[SpigotComparator].getMode
        mapComparatorMode(mode)

      case Material.COMPOSTER => ComposterState.values()
      case Material.KELP_PLANT => KelpState.valueOf(age)
      case Material.LAVA => LavaState.values()

      case Material.MELON_STEM | Material.ATTACHED_MELON_STEM => MelonStemState.valueOf(age)

      case Material.NETHER_WART => NetherWartState.valueOf(age)

      case Material.NOTE_BLOCK =>
        NoteBlockState.values()

      case Material.POTATOES => PotatoState.valueOf(age)

      case Material.PUMPKIN_STEM | Material.ATTACHED_PUMPKIN_STEM => PumpkinStemState.valueOf(age)

      case Material.RAIL | Material.ACTIVATOR_RAIL | Material.DETECTOR_RAIL | Material.POWERED_RAIL =>
        RailsState.values()

      case Material.REDSTONE_WIRE => RedstoneWireState.values()
      case Material.REPEATER => RepeaterState.values()

      // TODO add slab stairs wall etc
      case Material.SANDSTONE | Material.RED_SANDSTONE => SandstoneState.NORMAL
      case Material.CHISELED_SANDSTONE | Material.CHISELED_RED_SANDSTONE => SandstoneState.CHISELED
      case Material.CUT_SANDSTONE | Material.CUT_RED_SANDSTONE => SandstoneState.CUT
      case Material.SMOOTH_SANDSTONE | Material.SMOOTH_RED_SANDSTONE => SandstoneState.SMOOTH

      case Material.BAMBOO_SAPLING |
           Material.ACACIA_SAPLING | Material.BIRCH_SAPLING | Material.DARK_OAK_SAPLING |
           Material.JUNGLE_SAPLING | Material.OAK_SAPLING | Material.SPRUCE_SAPLING =>
        val stage = state.asInstanceOf[SpigotSapling].getStage
        SaplingState.values()(stage)

      case Material.SEA_PICKLE => SeaPickleState.values()

      case Material.STRUCTURE_BLOCK =>
        val mode = state.asInstanceOf[SpigotStructureBlock].getMode
        mapStructureBlockMode(mode)

      case Material.SUGAR_CANE => SugarCaneState.valueOf(age)
      case Material.SWEET_BERRY_BUSH => SweetBerryState.valueOf(age)
      case Material.TURTLE_EGG => TurtleEggState.values()
      case Material.WATER => WaterState.values()

      case Material.LIGHT_WEIGHTED_PRESSURE_PLATE | Material.HEAVY_WEIGHTED_PRESSURE_PLATE =>
        WeightedPressurePlateState.values()

      case Material.WHEAT => WheatState.valueOf(age)

      case _ => throw new IllegalArgumentException(s"Failed to map state for material: ${block.getType}")
    }
  }

  def mapComparatorMode(mode: SpigotComparator.Mode): ComparatorState = mode match {
    case SpigotComparator.Mode.COMPARE => ComparatorState.COMPARE
    case SpigotComparator.Mode.SUBTRACT => ComparatorState.SUBTRACT
  }

  def mapStructureBlockMode(mode: SpigotStructureBlock.Mode): StructureBlockState = mode match {
    case SpigotStructureBlock.Mode.CORNER => StructureBlockState.CORNER
    case SpigotStructureBlock.Mode.DATA => StructureBlockState.DATA
    case SpigotStructureBlock.Mode.LOAD => StructureBlockState.LOAD
    case SpigotStructureBlock.Mode.SAVE => StructureBlockState.SAVE
  }
}
