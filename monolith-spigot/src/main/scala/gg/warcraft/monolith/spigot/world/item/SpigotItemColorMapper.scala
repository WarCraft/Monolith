package gg.warcraft.monolith.spigot.world.item

import gg.warcraft.monolith.api.world.block.BlockColor
import gg.warcraft.monolith.api.world.item._
import gg.warcraft.monolith.spigot.world.block.SpigotBlockColorMapper
import javax.inject.Inject
import org.bukkit.Material

class SpigotItemColorMapper @Inject() (
    blockMapper: SpigotBlockColorMapper
) {
  def map(material: Material): BlockColor =
    blockMapper.map(material)

  def map(item: ColoredItem): Material = item match {
    case Banner(color, _, _, _, _, _)         => blockMapper.mapBanner(color)
    case Bed(color, _, _, _, _, _)            => blockMapper.mapBed(color)
    case Carpet(color, _, _, _, _, _)         => blockMapper.mapCarpet(color)
    case Concrete(color, _, _, _, _, _)       => blockMapper.mapConcrete(color)
    case ConcretePowder(color, _, _, _, _, _) => blockMapper.mapConcretePowder(color)
    case Dye(color, _, _, _, _, _)            => mapDye(color)
    case FireworkStar(color, _, _, _, _, _)   => mapFireworkStar(color)
    case GlazedTerracotta(color, _, _, _, _, _) =>
      blockMapper.mapGlazedTerracotta(color)
    case Wool(color, _, _, _, _, _) => blockMapper.mapWool(color)

    case _ => throw new IllegalArgumentException(s"$item")
  }

  def map(item: ColorableItem): Material = item match {
    case Glass(None, _, _, _, _, _)        => Material.GLASS
    case Glass(Some(color), _, _, _, _, _) => blockMapper.mapStainedGlass(color)

    case GlassPane(None, _, _, _, _, _) => Material.GLASS_PANE
    case GlassPane(Some(color), _, _, _, _, _) =>
      blockMapper.mapStainedGlassPane(color)

    case ShulkerBox(None, _, _, _, _, _)        => Material.SHULKER_BOX
    case ShulkerBox(Some(color), _, _, _, _, _) => blockMapper.mapShulkerBox(color)

    case Terracotta(None, _, _, _, _, _)        => Material.TERRACOTTA
    case Terracotta(Some(color), _, _, _, _, _) => blockMapper.mapTerracotta(color)

    case _ => throw new IllegalArgumentException(s"$item")
  }

  def mapDye(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_DYE
    case BlockColor.BLUE       => Material.BLUE_DYE
    case BlockColor.BROWN      => Material.BROWN_DYE
    case BlockColor.CYAN       => Material.CYAN_DYE
    case BlockColor.GRAY       => Material.GRAY_DYE
    case BlockColor.GREEN      => Material.GREEN_DYE
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_DYE
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_DYE
    case BlockColor.LIME       => Material.LIME_DYE
    case BlockColor.MAGENTA    => Material.MAGENTA_DYE
    case BlockColor.ORANGE     => Material.ORANGE_DYE
    case BlockColor.PINK       => Material.PINK_DYE
    case BlockColor.PURPLE     => Material.PURPLE_DYE
    case BlockColor.RED        => Material.RED_DYE
    case BlockColor.WHITE      => Material.WHITE_DYE
    case BlockColor.YELLOW     => Material.YELLOW_DYE
  }

  def mapFireworkStar(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.FIREWORK_STAR
    case BlockColor.BLUE       => Material.FIREWORK_STAR
    case BlockColor.BROWN      => Material.FIREWORK_STAR
    case BlockColor.CYAN       => Material.FIREWORK_STAR
    case BlockColor.GRAY       => Material.FIREWORK_STAR
    case BlockColor.GREEN      => Material.FIREWORK_STAR
    case BlockColor.LIGHT_BLUE => Material.FIREWORK_STAR
    case BlockColor.LIGHT_GRAY => Material.FIREWORK_STAR
    case BlockColor.LIME       => Material.FIREWORK_STAR
    case BlockColor.MAGENTA    => Material.FIREWORK_STAR
    case BlockColor.ORANGE     => Material.FIREWORK_STAR
    case BlockColor.PINK       => Material.FIREWORK_STAR
    case BlockColor.PURPLE     => Material.FIREWORK_STAR
    case BlockColor.RED        => Material.FIREWORK_STAR
    case BlockColor.WHITE      => Material.FIREWORK_STAR
    case BlockColor.YELLOW     => Material.FIREWORK_STAR
  }
}
