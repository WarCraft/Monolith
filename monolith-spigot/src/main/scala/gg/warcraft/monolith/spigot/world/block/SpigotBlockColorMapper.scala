package gg.warcraft.monolith.spigot.world.block

import java.util

import gg.warcraft.monolith.api.core.Extensions._
import gg.warcraft.monolith.api.world.block._
import org.bukkit.Material

class SpigotBlockColorMapper {
  private val cache =
    new util.EnumMap[Material, BlockColor](classOf[Material])

  def map(material: Material): BlockColor = cache.computeIfAbsent(
    material, {
      case Material.GLASS | Material.GLASS_PANE | Material.SHULKER_BOX |
          Material.TERRACOTTA =>
        null

      case _ =>
        material.name match {
          case r"BLACK.*"      => BlockColor.BLACK
          case r"BLUE.*"       => BlockColor.BLUE
          case r"BROWN.*"      => BlockColor.BROWN
          case r"CYAN.*"       => BlockColor.CYAN
          case r"GRAY.*"       => BlockColor.GRAY
          case r"GREEN.*"      => BlockColor.GREEN
          case r"LIGHT_BLUE.*" => BlockColor.LIGHT_BLUE
          case r"LIGHT_GRAY.*" => BlockColor.LIGHT_GRAY
          case r"LIME.*"       => BlockColor.LIME
          case r"MAGENTA.*"    => BlockColor.MAGENTA
          case r"ORANGE.*"     => BlockColor.ORANGE
          case r"PINK.*"       => BlockColor.PINK
          case r"PURPLE.*"     => BlockColor.PURPLE
          case r"RED.*"        => BlockColor.RED
          case r"WHITE.*"      => BlockColor.WHITE
          case r"YELLOW.*"     => BlockColor.YELLOW

          case _ => throw new IllegalArgumentException(s"$material")
        }
    }
  )

  def map(block: ColoredBlock): Material = block match {
    case Banner(_, color, _, None)     => mapBanner(color)
    case Banner(_, color, None, _)     => mapWallBanner(color)
    case Bed(_, color, _, _)           => mapBed(color)
    case Carpet(_, color)              => mapCarpet(color)
    case Concrete(_, color)            => mapConcrete(color)
    case ConcretePowder(_, color)      => mapConcretePowder(color)
    case GlazedTerracotta(_, color, _) => mapGlazedTerracotta(color)
    case Wool(_, color)                => mapWool(color)

    case _ => throw new IllegalArgumentException(s"$block")
  }

  def map(block: ColorableBlock): Material = block match {
    case Glass(_, None)        => Material.GLASS
    case Glass(_, Some(color)) => mapStainedGlass(color)

    case GlassPane(_, None, _, _)        => Material.GLASS_PANE
    case GlassPane(_, Some(color), _, _) => mapStainedGlassPane(color)

    case ShulkerBox(_, None, _)        => Material.SHULKER_BOX
    case ShulkerBox(_, Some(color), _) => mapShulkerBox(color)

    case Terracotta(_, None)        => Material.TERRACOTTA
    case Terracotta(_, Some(color)) => mapTerracotta(color)

    case _ => throw new IllegalArgumentException(s"$block")
  }

  def mapBanner(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_BANNER
    case BlockColor.BLUE       => Material.BLUE_BANNER
    case BlockColor.BROWN      => Material.BROWN_BANNER
    case BlockColor.CYAN       => Material.CYAN_BANNER
    case BlockColor.GRAY       => Material.GRAY_BANNER
    case BlockColor.GREEN      => Material.GREEN_BANNER
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_BANNER
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_BANNER
    case BlockColor.LIME       => Material.LIME_BANNER
    case BlockColor.MAGENTA    => Material.MAGENTA_BANNER
    case BlockColor.ORANGE     => Material.ORANGE_BANNER
    case BlockColor.PINK       => Material.PINK_BANNER
    case BlockColor.PURPLE     => Material.PURPLE_BANNER
    case BlockColor.RED        => Material.RED_BANNER
    case BlockColor.WHITE      => Material.WHITE_BANNER
    case BlockColor.YELLOW     => Material.YELLOW_BANNER
  }

  def mapWallBanner(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_WALL_BANNER
    case BlockColor.BLUE       => Material.BLUE_WALL_BANNER
    case BlockColor.BROWN      => Material.BROWN_WALL_BANNER
    case BlockColor.CYAN       => Material.CYAN_WALL_BANNER
    case BlockColor.GRAY       => Material.GRAY_WALL_BANNER
    case BlockColor.GREEN      => Material.GREEN_WALL_BANNER
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_WALL_BANNER
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_WALL_BANNER
    case BlockColor.LIME       => Material.LIME_WALL_BANNER
    case BlockColor.MAGENTA    => Material.MAGENTA_WALL_BANNER
    case BlockColor.ORANGE     => Material.ORANGE_WALL_BANNER
    case BlockColor.PINK       => Material.PINK_WALL_BANNER
    case BlockColor.PURPLE     => Material.PURPLE_WALL_BANNER
    case BlockColor.RED        => Material.RED_WALL_BANNER
    case BlockColor.WHITE      => Material.WHITE_WALL_BANNER
    case BlockColor.YELLOW     => Material.YELLOW_WALL_BANNER
  }

  def mapBed(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_BED
    case BlockColor.BLUE       => Material.BLUE_BED
    case BlockColor.BROWN      => Material.BROWN_BED
    case BlockColor.CYAN       => Material.CYAN_BED
    case BlockColor.GRAY       => Material.GRAY_BED
    case BlockColor.GREEN      => Material.GREEN_BED
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_BED
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_BED
    case BlockColor.LIME       => Material.LIME_BED
    case BlockColor.MAGENTA    => Material.MAGENTA_BED
    case BlockColor.ORANGE     => Material.ORANGE_BED
    case BlockColor.PINK       => Material.PINK_BED
    case BlockColor.PURPLE     => Material.PURPLE_BED
    case BlockColor.RED        => Material.RED_BED
    case BlockColor.WHITE      => Material.WHITE_BED
    case BlockColor.YELLOW     => Material.YELLOW_BED
  }

  def mapCarpet(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_CARPET
    case BlockColor.BLUE       => Material.BLUE_CARPET
    case BlockColor.BROWN      => Material.BROWN_CARPET
    case BlockColor.CYAN       => Material.CYAN_CARPET
    case BlockColor.GRAY       => Material.GRAY_CARPET
    case BlockColor.GREEN      => Material.GREEN_CARPET
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_CARPET
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_CARPET
    case BlockColor.LIME       => Material.LIME_CARPET
    case BlockColor.MAGENTA    => Material.MAGENTA_CARPET
    case BlockColor.ORANGE     => Material.ORANGE_CARPET
    case BlockColor.PINK       => Material.PINK_CARPET
    case BlockColor.PURPLE     => Material.PURPLE_CARPET
    case BlockColor.RED        => Material.RED_CARPET
    case BlockColor.WHITE      => Material.WHITE_CARPET
    case BlockColor.YELLOW     => Material.YELLOW_CARPET
  }

  def mapConcrete(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_CONCRETE
    case BlockColor.BLUE       => Material.BLUE_CONCRETE
    case BlockColor.BROWN      => Material.BROWN_CONCRETE
    case BlockColor.CYAN       => Material.CYAN_CONCRETE
    case BlockColor.GRAY       => Material.GRAY_CONCRETE
    case BlockColor.GREEN      => Material.GREEN_CONCRETE
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_CONCRETE
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_CONCRETE
    case BlockColor.LIME       => Material.LIME_CONCRETE
    case BlockColor.MAGENTA    => Material.MAGENTA_CONCRETE
    case BlockColor.ORANGE     => Material.ORANGE_CONCRETE
    case BlockColor.PINK       => Material.PINK_CONCRETE
    case BlockColor.PURPLE     => Material.PURPLE_CONCRETE
    case BlockColor.RED        => Material.RED_CONCRETE
    case BlockColor.WHITE      => Material.WHITE_CONCRETE
    case BlockColor.YELLOW     => Material.YELLOW_CONCRETE
  }

  def mapConcretePowder(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_CONCRETE_POWDER
    case BlockColor.BLUE       => Material.BLUE_CONCRETE_POWDER
    case BlockColor.BROWN      => Material.BROWN_CONCRETE_POWDER
    case BlockColor.CYAN       => Material.CYAN_CONCRETE_POWDER
    case BlockColor.GRAY       => Material.GRAY_CONCRETE_POWDER
    case BlockColor.GREEN      => Material.GREEN_CONCRETE_POWDER
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_CONCRETE_POWDER
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_CONCRETE_POWDER
    case BlockColor.LIME       => Material.LIME_CONCRETE_POWDER
    case BlockColor.MAGENTA    => Material.MAGENTA_CONCRETE_POWDER
    case BlockColor.ORANGE     => Material.ORANGE_CONCRETE_POWDER
    case BlockColor.PINK       => Material.PINK_CONCRETE_POWDER
    case BlockColor.PURPLE     => Material.PURPLE_CONCRETE_POWDER
    case BlockColor.RED        => Material.RED_CONCRETE_POWDER
    case BlockColor.WHITE      => Material.WHITE_CONCRETE_POWDER
    case BlockColor.YELLOW     => Material.YELLOW_CONCRETE_POWDER
  }

  def mapGlazedTerracotta(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_GLAZED_TERRACOTTA
    case BlockColor.BLUE       => Material.BLUE_GLAZED_TERRACOTTA
    case BlockColor.BROWN      => Material.BROWN_GLAZED_TERRACOTTA
    case BlockColor.CYAN       => Material.CYAN_GLAZED_TERRACOTTA
    case BlockColor.GRAY       => Material.GRAY_GLAZED_TERRACOTTA
    case BlockColor.GREEN      => Material.GREEN_GLAZED_TERRACOTTA
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_GLAZED_TERRACOTTA
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_GLAZED_TERRACOTTA
    case BlockColor.LIME       => Material.LIME_GLAZED_TERRACOTTA
    case BlockColor.MAGENTA    => Material.MAGENTA_GLAZED_TERRACOTTA
    case BlockColor.ORANGE     => Material.ORANGE_GLAZED_TERRACOTTA
    case BlockColor.PINK       => Material.PINK_GLAZED_TERRACOTTA
    case BlockColor.PURPLE     => Material.PURPLE_GLAZED_TERRACOTTA
    case BlockColor.RED        => Material.RED_GLAZED_TERRACOTTA
    case BlockColor.WHITE      => Material.WHITE_GLAZED_TERRACOTTA
    case BlockColor.YELLOW     => Material.YELLOW_GLAZED_TERRACOTTA
  }

  def mapShulkerBox(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_SHULKER_BOX
    case BlockColor.BLUE       => Material.BLUE_SHULKER_BOX
    case BlockColor.BROWN      => Material.BROWN_SHULKER_BOX
    case BlockColor.CYAN       => Material.CYAN_SHULKER_BOX
    case BlockColor.GRAY       => Material.GRAY_SHULKER_BOX
    case BlockColor.GREEN      => Material.GREEN_SHULKER_BOX
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_SHULKER_BOX
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_SHULKER_BOX
    case BlockColor.LIME       => Material.LIME_SHULKER_BOX
    case BlockColor.MAGENTA    => Material.MAGENTA_SHULKER_BOX
    case BlockColor.ORANGE     => Material.ORANGE_SHULKER_BOX
    case BlockColor.PINK       => Material.PINK_SHULKER_BOX
    case BlockColor.PURPLE     => Material.PURPLE_SHULKER_BOX
    case BlockColor.RED        => Material.RED_SHULKER_BOX
    case BlockColor.WHITE      => Material.WHITE_SHULKER_BOX
    case BlockColor.YELLOW     => Material.YELLOW_SHULKER_BOX
  }

  def mapStainedGlass(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_STAINED_GLASS
    case BlockColor.BLUE       => Material.BLUE_STAINED_GLASS
    case BlockColor.BROWN      => Material.BROWN_STAINED_GLASS
    case BlockColor.CYAN       => Material.CYAN_STAINED_GLASS
    case BlockColor.GRAY       => Material.GRAY_STAINED_GLASS
    case BlockColor.GREEN      => Material.GREEN_STAINED_GLASS
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_STAINED_GLASS
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_STAINED_GLASS
    case BlockColor.LIME       => Material.LIME_STAINED_GLASS
    case BlockColor.MAGENTA    => Material.MAGENTA_STAINED_GLASS
    case BlockColor.ORANGE     => Material.ORANGE_STAINED_GLASS
    case BlockColor.PINK       => Material.PINK_STAINED_GLASS
    case BlockColor.PURPLE     => Material.PURPLE_STAINED_GLASS
    case BlockColor.RED        => Material.RED_STAINED_GLASS
    case BlockColor.WHITE      => Material.WHITE_STAINED_GLASS
    case BlockColor.YELLOW     => Material.YELLOW_STAINED_GLASS
  }

  def mapStainedGlassPane(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_STAINED_GLASS_PANE
    case BlockColor.BLUE       => Material.BLUE_STAINED_GLASS_PANE
    case BlockColor.BROWN      => Material.BROWN_STAINED_GLASS_PANE
    case BlockColor.CYAN       => Material.CYAN_STAINED_GLASS_PANE
    case BlockColor.GRAY       => Material.GRAY_STAINED_GLASS_PANE
    case BlockColor.GREEN      => Material.GREEN_STAINED_GLASS_PANE
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_STAINED_GLASS_PANE
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_STAINED_GLASS_PANE
    case BlockColor.LIME       => Material.LIME_STAINED_GLASS_PANE
    case BlockColor.MAGENTA    => Material.MAGENTA_STAINED_GLASS_PANE
    case BlockColor.ORANGE     => Material.ORANGE_STAINED_GLASS_PANE
    case BlockColor.PINK       => Material.PINK_STAINED_GLASS_PANE
    case BlockColor.PURPLE     => Material.PURPLE_STAINED_GLASS_PANE
    case BlockColor.RED        => Material.RED_STAINED_GLASS_PANE
    case BlockColor.WHITE      => Material.WHITE_STAINED_GLASS_PANE
    case BlockColor.YELLOW     => Material.YELLOW_STAINED_GLASS_PANE
  }

  def mapTerracotta(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_TERRACOTTA
    case BlockColor.BLUE       => Material.BLUE_TERRACOTTA
    case BlockColor.BROWN      => Material.BROWN_TERRACOTTA
    case BlockColor.CYAN       => Material.CYAN_TERRACOTTA
    case BlockColor.GRAY       => Material.GRAY_TERRACOTTA
    case BlockColor.GREEN      => Material.GREEN_TERRACOTTA
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_TERRACOTTA
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_TERRACOTTA
    case BlockColor.LIME       => Material.LIME_TERRACOTTA
    case BlockColor.MAGENTA    => Material.MAGENTA_TERRACOTTA
    case BlockColor.ORANGE     => Material.ORANGE_TERRACOTTA
    case BlockColor.PINK       => Material.PINK_TERRACOTTA
    case BlockColor.PURPLE     => Material.PURPLE_TERRACOTTA
    case BlockColor.RED        => Material.RED_TERRACOTTA
    case BlockColor.WHITE      => Material.WHITE_TERRACOTTA
    case BlockColor.YELLOW     => Material.YELLOW_TERRACOTTA
  }

  def mapWool(color: BlockColor): Material = color match {
    case BlockColor.BLACK      => Material.BLACK_WOOL
    case BlockColor.BLUE       => Material.BLUE_WOOL
    case BlockColor.BROWN      => Material.BROWN_WOOL
    case BlockColor.CYAN       => Material.CYAN_WOOL
    case BlockColor.GRAY       => Material.GRAY_WOOL
    case BlockColor.GREEN      => Material.GREEN_WOOL
    case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_WOOL
    case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_WOOL
    case BlockColor.LIME       => Material.LIME_WOOL
    case BlockColor.MAGENTA    => Material.MAGENTA_WOOL
    case BlockColor.ORANGE     => Material.ORANGE_WOOL
    case BlockColor.PINK       => Material.PINK_WOOL
    case BlockColor.PURPLE     => Material.PURPLE_WOOL
    case BlockColor.RED        => Material.RED_WOOL
    case BlockColor.WHITE      => Material.WHITE_WOOL
    case BlockColor.YELLOW     => Material.YELLOW_WOOL
  }
}
