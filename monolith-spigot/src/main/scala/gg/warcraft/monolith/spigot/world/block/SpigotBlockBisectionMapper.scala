package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block.BlockBisection
import org.bukkit.block.data.Bisected.{ Half => SpigotBlockBisection }

class SpigotBlockBisectionMapper {

  def map(section: SpigotBlockBisection): BlockBisection = section match {
    case SpigotBlockBisection.BOTTOM => BlockBisection.BOTTOM
    case SpigotBlockBisection.TOP => BlockBisection.TOP
  }

  def map(section: BlockBisection): SpigotBlockBisection = section match {
    case BlockBisection.BOTTOM => SpigotBlockBisection.BOTTOM
    case BlockBisection.TOP => SpigotBlockBisection.TOP
  }
}
