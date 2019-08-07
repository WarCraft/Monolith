package gg.warcraft.monolith.spigot.world

import com.google.inject.Inject
import gg.warcraft.monolith.api.world.block._
import org.bukkit.block.data.Ageable
import org.bukkit.block.data.Bisected.{Half => SpigotBisection}
import org.bukkit.block.data.`type`.{Cake => SpigotCake, Sapling => SpigotSapling}
import org.bukkit.block.{Block => SpigotBlock, BlockFace => SpigotBlockFace}
import org.bukkit.{Axis, Material}

class SpigotBlockStateMapper @Inject()(
  private val locationMapper: SpigotLocationMapper,
) {

  def map(block: SpigotBlock): BlockState = {
    lazy val age = { block.getState.asInstanceOf[Ageable].getAge }

    block.getType match {
      // ANVIL
      case Material.ANVIL => AnvilState.PRISTINE
      case Material.CHIPPED_ANVIL => AnvilState.CHIPPED
      case Material.DAMAGED_ANVIL => AnvilState.DAMAGED

      case Material.BEETROOTS => BeetrootState.withName(s"AGE_$age");
      case Material.CACTUS => CactusState.withName(s"AGE_$age");
      case Material.CAKE =>
        val bites = block.getState.asInstanceOf[SpigotCake].getBites
        CakeState.withName(s"EATEN_$bites");
      case Material.COCOA => CocoaState.withName(s"AGE_$age");
      case Material.NETHER_WART => NetherWartState.withName(s"AGE_$age");
      case Material.POTATOES => PotatoState.withName(s"AGE_$age");

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
        val stage = block.getState.asInstanceOf[SpigotSapling].getStage
        SaplingState.withName(s"AGE_$stage")

      case Material.STRUCTURE_BLOCK => null
      case Material.SUGAR_CANE => SugarCaneState.withName(s"AGE_$age");
      case Material.SWEET_BERRY_BUSH => SweetBerryState.withName(s"AGE_$age");
      case Material.WHEAT => WheatState.withName(s"AGE_$age");

      case _ => throw new IllegalArgumentException(s"Failed to map state for material: ${ block.getType }")
    }
  }

  def mapBisection(section: BlockBisection): SpigotBisection = section match {
    case BlockBisection.BOTTOM => SpigotBisection.BOTTOM
    case BlockBisection.TOP => SpigotBisection.TOP
  }

  def mapFace(face: BlockFace): SpigotBlockFace = face match {
    case BlockFace.NORTH => SpigotBlockFace.NORTH
    case BlockFace.EAST => SpigotBlockFace.EAST
    case BlockFace.SOUTH => SpigotBlockFace.SOUTH
    case BlockFace.WEST => SpigotBlockFace.WEST
    case BlockFace.UP => SpigotBlockFace.UP
    case BlockFace.DOWN => SpigotBlockFace.DOWN
  }

  def mapOrientation(orientation: BlockOrientation): Axis = orientation match {
    case BlockOrientation.X_AXIS => Axis.X
    case BlockOrientation.Y_AXIS => Axis.Y
    case BlockOrientation.Z_AXIS => Axis.Z
  }
}
