package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block.BlockAttachment
import org.bukkit.block.BlockFace
import org.bukkit.block.data.{ Attachable, Directional }

class SpigotBlockAttachmentMapper {

  def map(attachable: Attachable): Option[BlockAttachment] = {
    if (!attachable.isAttached) {
      return None
    }

    attachable.asInstanceOf[Directional].getFacing match {
      case BlockFace.NORTH => Some(BlockAttachment.WALL)
      case BlockFace.EAST => Some(BlockAttachment.WALL)
      case BlockFace.SOUTH => Some(BlockAttachment.WALL)
      case BlockFace.WEST => Some(BlockAttachment.WALL)
      case BlockFace.UP => Some(BlockAttachment.FLOOR)
      case BlockFace.DOWN => Some(BlockAttachment.CEILING)
    }
  }

  def map(attachment: Option[BlockAttachment]): Boolean = attachment.isDefined
}
