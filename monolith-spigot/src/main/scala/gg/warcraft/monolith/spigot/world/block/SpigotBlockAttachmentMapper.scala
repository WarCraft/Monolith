package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block.BlockAttachment
import org.bukkit.block.data.`type`.Switch

class SpigotBlockAttachmentMapper {

  def map(switch: Switch): BlockAttachment = switch.getFace match {
    case Switch.Face.CEILING => BlockAttachment.CEILING
    case Switch.Face.FLOOR   => BlockAttachment.FLOOR
    case Switch.Face.WALL    => BlockAttachment.WALL
  }

  def map(attachment: BlockAttachment): Switch.Face = attachment match {
    case BlockAttachment.CEILING => Switch.Face.CEILING
    case BlockAttachment.FLOOR   => Switch.Face.FLOOR
    case BlockAttachment.WALL    => Switch.Face.WALL
  }
}
