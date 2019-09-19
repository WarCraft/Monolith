package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block.BlockAttachment
import org.bukkit.block.BlockFace
import org.bukkit.block.data.{ Attachable, Directional }

class SpigotBlockAttachmentMapper {

  def map(attachable: Attachable): Option[BlockAttachment] = {
    if (!attachable.isAttached) {
      return Option.empty
    }

    attachable.asInstanceOf[Directional].getFacing match {
      case BlockFace.NORTH => Option(BlockAttachment.WALL)
      case BlockFace.EAST => Option(BlockAttachment.WALL)
      case BlockFace.SOUTH => Option(BlockAttachment.WALL)
      case BlockFace.WEST => Option(BlockAttachment.WALL)
      case BlockFace.UP => Option(BlockAttachment.FLOOR)
      case BlockFace.DOWN => Option(BlockAttachment.CEILING)
    }
  }

  def map(attachment: Option[BlockAttachment]): Boolean = attachment.isDefined
}
