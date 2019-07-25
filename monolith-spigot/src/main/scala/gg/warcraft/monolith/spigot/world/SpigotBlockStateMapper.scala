package gg.warcraft.monolith.spigot.world

import com.google.inject.Inject
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.spigot.world.block.SpigotBlockFaceMapper
import org.bukkit.Axis
import org.bukkit.block.data.Bisected.{Half => SpigotBisection}
import org.bukkit.block.{BlockFace => SpigotBlockFace}

class SpigotBlockStateMapper @Inject()(
  private val worldMapper: SpigotWorldMapper,
  private val locationMapper: SpigotLocationMapper,
  private val materialMapper: SpigotMaterialMapper,
  private val blockFaceMapper: SpigotBlockFaceMapper,
  private val directionMapper: SpigotDirectionMapper
) {

  def map(block: Block): BlockState = {
    val location = locationMapper.map(block.location)
    block match {
      case _ => null
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
