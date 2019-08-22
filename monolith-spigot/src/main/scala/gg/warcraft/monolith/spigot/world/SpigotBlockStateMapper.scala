package gg.warcraft.monolith.spigot.world

import com.google.inject.Inject
import gg.warcraft.monolith.api.world.block._
import org.bukkit.Material
import org.bukkit.block.{ Block => SpigotBlock }
import org.bukkit.block.data.Ageable
import org.bukkit.block.data.`type`.{ Cake => SpigotCake, Comparator => SpigotComparator, Sapling => SpigotSapling, StructureBlock => SpigotStructureBlock }

class SpigotBlockStateMapper @Inject()(
  private val locationMapper: SpigotLocationMapper
) {

  def map(block: SpigotBlock): BlockState = {
    val state = block.getState

    lazy val age = { block.getState.asInstanceOf[Ageable].getAge }

    block.getType match {
      // ANVIL
      case Material.ANVIL => AnvilState.PRISTINE
      case Material.CHIPPED_ANVIL => AnvilState.CHIPPED
      case Material.DAMAGED_ANVIL => AnvilState.DAMAGED

      case Material.BAMBOO => BambooState.values(age)
      case Material.BEETROOTS => BeetrootState.values(age)
      case Material.CACTUS => CactusState.values(age)
      case Material.CAKE =>
        val bites = state.asInstanceOf[SpigotCake].getBites
        CakeState.values(bites)
      case Material.COCOA => CocoaState.values(age)
      case Material.COMPARATOR =>
        val mode = state.asInstanceOf[SpigotComparator].getMode
        mapComparatorMode(mode)
      case Material.NETHER_WART => NetherWartState.values(age)
      case Material.POTATOES => PotatoState.values(age)

      // PRESSURE_PLATE
      case Material.LIGHT_WEIGHTED_PRESSURE_PLATE | Material.HEAVY_WEIGHTED_PRESSURE_PLATE =>
        null

      // RAILS
      case Material.RAIL | Material.ACTIVATOR_RAIL | Material.DETECTOR_RAIL | Material.POWERED_RAIL =>
        null

      // SANDSTONE
      case Material.SANDSTONE | Material.RED_SANDSTONE => SandstoneState.NORMAL
      case Material.CHISELED_SANDSTONE | Material.CHISELED_RED_SANDSTONE => SandstoneState.CHISELED
      case Material.CUT_SANDSTONE | Material.CUT_RED_SANDSTONE => SandstoneState.CUT
      case Material.SMOOTH_SANDSTONE | Material.SMOOTH_RED_SANDSTONE => SandstoneState.SMOOTH

      // SAPLING
      case Material.BAMBOO_SAPLING |
           Material.ACACIA_SAPLING | Material.BIRCH_SAPLING | Material.DARK_OAK_SAPLING |
           Material.JUNGLE_SAPLING | Material.OAK_SAPLING | Material.SPRUCE_SAPLING =>
        val stage = state.asInstanceOf[SpigotSapling].getStage
        SaplingState.values(stage)

      case Material.STRUCTURE_BLOCK =>
        val mode = state.asInstanceOf[SpigotStructureBlock].getMode
        mapStructureBlockMode(mode)
      case Material.SUGAR_CANE => SugarCaneState.values(age)
      case Material.SWEET_BERRY_BUSH => SweetBerryState.values(age)
      case Material.WHEAT => WheatState.values(age)

      case _ => throw new IllegalArgumentException(s"Failed to map state for material: ${ block.getType }")
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

  //  def mapBisection(section: BlockBisection): SpigotBisection = section match {
  //    case BlockBisection.BOTTOM => SpigotBisection.BOTTOM
  //    case BlockBisection.TOP => SpigotBisection.TOP
  //  }
  //
  //  def mapFace(face: BlockFace): SpigotBlockFace = face match {
  //    case BlockFace.NORTH => SpigotBlockFace.NORTH
  //    case BlockFace.EAST => SpigotBlockFace.EAST
  //    case BlockFace.SOUTH => SpigotBlockFace.SOUTH
  //    case BlockFace.WEST => SpigotBlockFace.WEST
  //    case BlockFace.UP => SpigotBlockFace.UP
  //    case BlockFace.DOWN => SpigotBlockFace.DOWN
  //  }
  //
  //  def mapOrientation(orientation: BlockOrientation): Axis = orientation match {
  //    case BlockOrientation.X_AXIS => Axis.X
  //    case BlockOrientation.Y_AXIS => Axis.Y
  //    case BlockOrientation.Z_AXIS => Axis.Z
  //  }
}
