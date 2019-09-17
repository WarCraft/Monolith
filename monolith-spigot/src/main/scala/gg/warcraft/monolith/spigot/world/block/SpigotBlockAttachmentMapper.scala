package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block.BlockAttachment
import org.bukkit.block.{ BlockState => SpigotBlockState }

class SpigotBlockAttachmentMapper {

  def map(state: SpigotBlockState): BlockAttachment = {
    state.getClass.getClasses.foreach(clazz => print(clazz.getSimpleName))
    // TODO implement properly when actual attachment data is found
    BlockAttachment.FLOOR
  }
}
