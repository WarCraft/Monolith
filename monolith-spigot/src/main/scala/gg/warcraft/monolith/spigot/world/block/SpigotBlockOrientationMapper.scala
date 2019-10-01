package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block.BlockOrientation
import org.bukkit.{Axis => SpigotBlockOrientation}

class SpigotBlockOrientationMapper {

  def map(orientation: SpigotBlockOrientation): BlockOrientation =
    orientation match {
      case SpigotBlockOrientation.X => BlockOrientation.X_AXIS
      case SpigotBlockOrientation.Y => BlockOrientation.Y_AXIS
      case SpigotBlockOrientation.Z => BlockOrientation.Z_AXIS
    }

  def map(orientation: BlockOrientation): SpigotBlockOrientation =
    orientation match {
      case BlockOrientation.X_AXIS => SpigotBlockOrientation.X
      case BlockOrientation.Y_AXIS => SpigotBlockOrientation.Y
      case BlockOrientation.Z_AXIS => SpigotBlockOrientation.Z
    }
}
