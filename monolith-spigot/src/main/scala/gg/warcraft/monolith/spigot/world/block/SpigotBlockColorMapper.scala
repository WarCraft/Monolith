package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block._
import org.bukkit.Material

class SpigotBlockColorMapper {

  def map(material: Material): BlockColor = material match {
    case Material.BLACK_BANNER | Material.BLACK_BED | Material.BLACK_CARPET |
         Material.BLACK_CONCRETE | Material.BLACK_CONCRETE_POWDER | Material.BLACK_GLAZED_TERRACOTTA |
         Material.BLACK_SHULKER_BOX | Material.BLACK_STAINED_GLASS | Material.BLACK_STAINED_GLASS_PANE |
         Material.BLACK_TERRACOTTA | Material.BLACK_WALL_BANNER | Material.BLACK_WOOL =>
      BlockColor.BLACK

    case Material.BLUE_BANNER | Material.BLUE_BED | Material.BLUE_CARPET |
         Material.BLUE_CONCRETE | Material.BLUE_CONCRETE_POWDER | Material.BLUE_GLAZED_TERRACOTTA |
         Material.BLUE_SHULKER_BOX | Material.BLUE_STAINED_GLASS | Material.BLUE_STAINED_GLASS_PANE |
         Material.BLUE_TERRACOTTA | Material.BLUE_WALL_BANNER | Material.BLUE_WOOL =>
      BlockColor.BLUE

    case Material.BROWN_BANNER | Material.BROWN_BED | Material.BROWN_CARPET |
         Material.BROWN_CONCRETE | Material.BROWN_CONCRETE_POWDER | Material.BROWN_GLAZED_TERRACOTTA |
         Material.BROWN_SHULKER_BOX | Material.BROWN_STAINED_GLASS | Material.BROWN_STAINED_GLASS_PANE |
         Material.BROWN_TERRACOTTA | Material.BROWN_WALL_BANNER | Material.BROWN_WOOL =>
      BlockColor.BROWN

    case Material.CYAN_BANNER | Material.CYAN_BED | Material.CYAN_CARPET |
         Material.CYAN_CONCRETE | Material.CYAN_CONCRETE_POWDER | Material.CYAN_GLAZED_TERRACOTTA |
         Material.CYAN_SHULKER_BOX | Material.CYAN_STAINED_GLASS | Material.CYAN_STAINED_GLASS_PANE |
         Material.CYAN_TERRACOTTA | Material.CYAN_WALL_BANNER | Material.CYAN_WOOL =>
      BlockColor.CYAN

    case Material.GRAY_BANNER | Material.GRAY_BED | Material.GRAY_CARPET |
         Material.GRAY_CONCRETE | Material.GRAY_CONCRETE_POWDER | Material.GRAY_GLAZED_TERRACOTTA |
         Material.GRAY_SHULKER_BOX | Material.GRAY_STAINED_GLASS | Material.GRAY_STAINED_GLASS_PANE |
         Material.GRAY_TERRACOTTA | Material.GRAY_WALL_BANNER | Material.GRAY_WOOL =>
      BlockColor.GRAY

    case Material.GREEN_BANNER | Material.GREEN_BED | Material.GREEN_CARPET |
         Material.GREEN_CONCRETE | Material.GREEN_CONCRETE_POWDER | Material.GREEN_GLAZED_TERRACOTTA |
         Material.GREEN_SHULKER_BOX | Material.GREEN_STAINED_GLASS | Material.GREEN_STAINED_GLASS_PANE |
         Material.GREEN_TERRACOTTA | Material.GREEN_WALL_BANNER | Material.GREEN_WOOL =>
      BlockColor.GREEN

    case Material.LIGHT_BLUE_BANNER | Material.LIGHT_BLUE_BED | Material.LIGHT_BLUE_CARPET |
         Material.LIGHT_BLUE_CONCRETE | Material.LIGHT_BLUE_CONCRETE_POWDER | Material.LIGHT_BLUE_GLAZED_TERRACOTTA |
         Material.LIGHT_BLUE_SHULKER_BOX | Material.LIGHT_BLUE_STAINED_GLASS | Material.LIGHT_BLUE_STAINED_GLASS_PANE |
         Material.LIGHT_BLUE_TERRACOTTA | Material.LIGHT_BLUE_WALL_BANNER | Material.LIGHT_BLUE_WOOL =>
      BlockColor.LIGHT_BLUE

    case Material.LIGHT_GRAY_BANNER | Material.LIGHT_GRAY_BED | Material.LIGHT_GRAY_CARPET |
         Material.LIGHT_GRAY_CONCRETE | Material.LIGHT_GRAY_CONCRETE_POWDER | Material.LIGHT_GRAY_GLAZED_TERRACOTTA |
         Material.LIGHT_GRAY_SHULKER_BOX | Material.LIGHT_GRAY_STAINED_GLASS | Material.LIGHT_GRAY_STAINED_GLASS_PANE |
         Material.LIGHT_GRAY_TERRACOTTA | Material.LIGHT_GRAY_WALL_BANNER | Material.LIGHT_GRAY_WOOL =>
      BlockColor.LIGHT_GRAY

    case Material.LIME_BANNER | Material.LIME_BED | Material.LIME_CARPET |
         Material.LIME_CONCRETE | Material.LIME_CONCRETE_POWDER | Material.LIME_GLAZED_TERRACOTTA |
         Material.LIME_SHULKER_BOX | Material.LIME_STAINED_GLASS | Material.LIME_STAINED_GLASS_PANE |
         Material.LIME_TERRACOTTA | Material.LIME_WALL_BANNER | Material.LIME_WOOL =>
      BlockColor.LIME

    case Material.MAGENTA_BANNER | Material.MAGENTA_BED | Material.MAGENTA_CARPET |
         Material.MAGENTA_CONCRETE | Material.MAGENTA_CONCRETE_POWDER | Material.MAGENTA_GLAZED_TERRACOTTA |
         Material.MAGENTA_SHULKER_BOX | Material.MAGENTA_STAINED_GLASS | Material.MAGENTA_STAINED_GLASS_PANE |
         Material.MAGENTA_TERRACOTTA | Material.MAGENTA_WALL_BANNER | Material.MAGENTA_WOOL =>
      BlockColor.MAGENTA

    case Material.ORANGE_BANNER | Material.ORANGE_BED | Material.ORANGE_CARPET |
         Material.ORANGE_CONCRETE | Material.ORANGE_CONCRETE_POWDER | Material.ORANGE_GLAZED_TERRACOTTA |
         Material.ORANGE_SHULKER_BOX | Material.ORANGE_STAINED_GLASS | Material.ORANGE_STAINED_GLASS_PANE |
         Material.ORANGE_TERRACOTTA | Material.ORANGE_WALL_BANNER | Material.ORANGE_WOOL =>
      BlockColor.ORANGE

    case Material.PINK_BANNER | Material.PINK_BED | Material.PINK_CARPET |
         Material.PINK_CONCRETE | Material.PINK_CONCRETE_POWDER | Material.PINK_GLAZED_TERRACOTTA |
         Material.PINK_SHULKER_BOX | Material.PINK_STAINED_GLASS | Material.PINK_STAINED_GLASS_PANE |
         Material.PINK_TERRACOTTA | Material.PINK_WALL_BANNER | Material.PINK_WOOL =>
      BlockColor.PINK

    case Material.PURPLE_BANNER | Material.PURPLE_BED | Material.PURPLE_CARPET |
         Material.PURPLE_CONCRETE | Material.PURPLE_CONCRETE_POWDER | Material.PURPLE_GLAZED_TERRACOTTA |
         Material.PURPLE_SHULKER_BOX | Material.PURPLE_STAINED_GLASS | Material.PURPLE_STAINED_GLASS_PANE |
         Material.PURPLE_TERRACOTTA | Material.PURPLE_WALL_BANNER | Material.PURPLE_WOOL =>
      BlockColor.PURPLE

    case Material.RED_BANNER | Material.RED_BED | Material.RED_CARPET |
         Material.RED_CONCRETE | Material.RED_CONCRETE_POWDER | Material.RED_GLAZED_TERRACOTTA |
         Material.RED_SHULKER_BOX | Material.RED_STAINED_GLASS | Material.RED_STAINED_GLASS_PANE |
         Material.RED_TERRACOTTA | Material.RED_WALL_BANNER | Material.RED_WOOL =>
      BlockColor.RED

    case Material.WHITE_BANNER | Material.WHITE_BED | Material.WHITE_CARPET |
         Material.WHITE_CONCRETE | Material.WHITE_CONCRETE_POWDER | Material.WHITE_GLAZED_TERRACOTTA |
         Material.WHITE_SHULKER_BOX | Material.WHITE_STAINED_GLASS | Material.WHITE_STAINED_GLASS_PANE |
         Material.WHITE_TERRACOTTA | Material.WHITE_WALL_BANNER | Material.WHITE_WOOL =>
      BlockColor.WHITE

    case Material.YELLOW_BANNER | Material.YELLOW_BED | Material.YELLOW_CARPET |
         Material.YELLOW_CONCRETE | Material.YELLOW_CONCRETE_POWDER | Material.YELLOW_GLAZED_TERRACOTTA |
         Material.YELLOW_SHULKER_BOX | Material.YELLOW_STAINED_GLASS | Material.YELLOW_STAINED_GLASS_PANE |
         Material.YELLOW_TERRACOTTA | Material.YELLOW_WALL_BANNER | Material.YELLOW_WOOL =>
      BlockColor.YELLOW

    case Material.GLASS | Material.GLASS_PANE | Material.SHULKER_BOX | Material.TERRACOTTA => null

    case _ => throw new IllegalArgumentException(s"Failed to map color for material: $material")
  }

  def map(block: ColoredBlock): Material = block match {
    case Banner(_, _, Option.empty, _) => block.color match {
      case BlockColor.BLACK => Material.BLACK_WALL_BANNER
      case BlockColor.BLUE => Material.BLUE_WALL_BANNER
      case BlockColor.BROWN => Material.BROWN_WALL_BANNER
      case BlockColor.CYAN => Material.CYAN_WALL_BANNER
      case BlockColor.GRAY => Material.GRAY_WALL_BANNER
      case BlockColor.GREEN => Material.GREEN_WALL_BANNER
      case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_WALL_BANNER
      case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_WALL_BANNER
      case BlockColor.LIME => Material.LIME_WALL_BANNER
      case BlockColor.MAGENTA => Material.MAGENTA_WALL_BANNER
      case BlockColor.ORANGE => Material.ORANGE_WALL_BANNER
      case BlockColor.PINK => Material.PINK_WALL_BANNER
      case BlockColor.PURPLE => Material.PURPLE_WALL_BANNER
      case BlockColor.RED => Material.RED_WALL_BANNER
      case BlockColor.WHITE => Material.WHITE_WALL_BANNER
      case BlockColor.YELLOW => Material.YELLOW_WALL_BANNER
    }

    case Banner(_, _, _, Option.empty) => block.color match {
      case BlockColor.BLACK => Material.BLACK_BANNER
      case BlockColor.BLUE => Material.BLUE_BANNER
      case BlockColor.BROWN => Material.BROWN_BANNER
      case BlockColor.CYAN => Material.CYAN_BANNER
      case BlockColor.GRAY => Material.GRAY_BANNER
      case BlockColor.GREEN => Material.GREEN_BANNER
      case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_BANNER
      case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_BANNER
      case BlockColor.LIME => Material.LIME_BANNER
      case BlockColor.MAGENTA => Material.MAGENTA_BANNER
      case BlockColor.ORANGE => Material.ORANGE_BANNER
      case BlockColor.PINK => Material.PINK_BANNER
      case BlockColor.PURPLE => Material.PURPLE_BANNER
      case BlockColor.RED => Material.RED_BANNER
      case BlockColor.WHITE => Material.WHITE_BANNER
      case BlockColor.YELLOW => Material.YELLOW_BANNER
    }

    case _: Bed => block.color match {
      case BlockColor.BLACK => Material.BLACK_BED
      case BlockColor.BLUE => Material.BLUE_BED
      case BlockColor.BROWN => Material.BROWN_BED
      case BlockColor.CYAN => Material.CYAN_BED
      case BlockColor.GRAY => Material.GRAY_BED
      case BlockColor.GREEN => Material.GREEN_BED
      case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_BED
      case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_BED
      case BlockColor.LIME => Material.LIME_BED
      case BlockColor.MAGENTA => Material.MAGENTA_BED
      case BlockColor.ORANGE => Material.ORANGE_BED
      case BlockColor.PINK => Material.PINK_BED
      case BlockColor.PURPLE => Material.PURPLE_BED
      case BlockColor.RED => Material.RED_BED
      case BlockColor.WHITE => Material.WHITE_BED
      case BlockColor.YELLOW => Material.YELLOW_BED
    }

    case _: Carpet => block.color match {
      case BlockColor.BLACK => Material.BLACK_CARPET
      case BlockColor.BLUE => Material.BLUE_CARPET
      case BlockColor.BROWN => Material.BROWN_CARPET
      case BlockColor.CYAN => Material.CYAN_CARPET
      case BlockColor.GRAY => Material.GRAY_CARPET
      case BlockColor.GREEN => Material.GREEN_CARPET
      case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_CARPET
      case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_CARPET
      case BlockColor.LIME => Material.LIME_CARPET
      case BlockColor.MAGENTA => Material.MAGENTA_CARPET
      case BlockColor.ORANGE => Material.ORANGE_CARPET
      case BlockColor.PINK => Material.PINK_CARPET
      case BlockColor.PURPLE => Material.PURPLE_CARPET
      case BlockColor.RED => Material.RED_CARPET
      case BlockColor.WHITE => Material.WHITE_CARPET
      case BlockColor.YELLOW => Material.YELLOW_CARPET
    }

    case _: Concrete => block.color match {
      case BlockColor.BLACK => Material.BLACK_CONCRETE
      case BlockColor.BLUE => Material.BLUE_CONCRETE
      case BlockColor.BROWN => Material.BROWN_CONCRETE
      case BlockColor.CYAN => Material.CYAN_CONCRETE
      case BlockColor.GRAY => Material.GRAY_CONCRETE
      case BlockColor.GREEN => Material.GREEN_CONCRETE
      case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_CONCRETE
      case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_CONCRETE
      case BlockColor.LIME => Material.LIME_CONCRETE
      case BlockColor.MAGENTA => Material.MAGENTA_CONCRETE
      case BlockColor.ORANGE => Material.ORANGE_CONCRETE
      case BlockColor.PINK => Material.PINK_CONCRETE
      case BlockColor.PURPLE => Material.PURPLE_CONCRETE
      case BlockColor.RED => Material.RED_CONCRETE
      case BlockColor.WHITE => Material.WHITE_CONCRETE
      case BlockColor.YELLOW => Material.YELLOW_CONCRETE
    }

    case _: ConcretePowder => block.color match {
      case BlockColor.BLACK => Material.BLACK_CONCRETE_POWDER
      case BlockColor.BLUE => Material.BLUE_CONCRETE_POWDER
      case BlockColor.BROWN => Material.BROWN_CONCRETE_POWDER
      case BlockColor.CYAN => Material.CYAN_CONCRETE_POWDER
      case BlockColor.GRAY => Material.GRAY_CONCRETE_POWDER
      case BlockColor.GREEN => Material.GREEN_CONCRETE_POWDER
      case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_CONCRETE_POWDER
      case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_CONCRETE_POWDER
      case BlockColor.LIME => Material.LIME_CONCRETE_POWDER
      case BlockColor.MAGENTA => Material.MAGENTA_CONCRETE_POWDER
      case BlockColor.ORANGE => Material.ORANGE_CONCRETE_POWDER
      case BlockColor.PINK => Material.PINK_CONCRETE_POWDER
      case BlockColor.PURPLE => Material.PURPLE_CONCRETE_POWDER
      case BlockColor.RED => Material.RED_CONCRETE_POWDER
      case BlockColor.WHITE => Material.WHITE_CONCRETE_POWDER
      case BlockColor.YELLOW => Material.YELLOW_CONCRETE_POWDER
    }

    case _: GlazedTerracotta => block.color match {
      case BlockColor.BLACK => Material.BLACK_GLAZED_TERRACOTTA
      case BlockColor.BLUE => Material.BLUE_GLAZED_TERRACOTTA
      case BlockColor.BROWN => Material.BROWN_GLAZED_TERRACOTTA
      case BlockColor.CYAN => Material.CYAN_GLAZED_TERRACOTTA
      case BlockColor.GRAY => Material.GRAY_GLAZED_TERRACOTTA
      case BlockColor.GREEN => Material.GREEN_GLAZED_TERRACOTTA
      case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_GLAZED_TERRACOTTA
      case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_GLAZED_TERRACOTTA
      case BlockColor.LIME => Material.LIME_GLAZED_TERRACOTTA
      case BlockColor.MAGENTA => Material.MAGENTA_GLAZED_TERRACOTTA
      case BlockColor.ORANGE => Material.ORANGE_GLAZED_TERRACOTTA
      case BlockColor.PINK => Material.PINK_GLAZED_TERRACOTTA
      case BlockColor.PURPLE => Material.PURPLE_GLAZED_TERRACOTTA
      case BlockColor.RED => Material.RED_GLAZED_TERRACOTTA
      case BlockColor.WHITE => Material.WHITE_GLAZED_TERRACOTTA
      case BlockColor.YELLOW => Material.YELLOW_GLAZED_TERRACOTTA
    }

    case _: Wool => block.color match {
      case BlockColor.BLACK => Material.BLACK_WOOL
      case BlockColor.BLUE => Material.BLUE_WOOL
      case BlockColor.BROWN => Material.BROWN_WOOL
      case BlockColor.CYAN => Material.CYAN_WOOL
      case BlockColor.GRAY => Material.GRAY_WOOL
      case BlockColor.GREEN => Material.GREEN_WOOL
      case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_WOOL
      case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_WOOL
      case BlockColor.LIME => Material.LIME_WOOL
      case BlockColor.MAGENTA => Material.MAGENTA_WOOL
      case BlockColor.ORANGE => Material.ORANGE_WOOL
      case BlockColor.PINK => Material.PINK_WOOL
      case BlockColor.PURPLE => Material.PURPLE_WOOL
      case BlockColor.RED => Material.RED_WOOL
      case BlockColor.WHITE => Material.WHITE_WOOL
      case BlockColor.YELLOW => Material.YELLOW_WOOL
    }

    case _ => throw new IllegalArgumentException(s"Failed to map block to material: ${ block.getClass.getSimpleName }")
  }

  def map(block: ColorableBlock): Material = {
    case Glass(_, Option.empty) => Material.GLASS
    case Glass(_, _) => block.color.get match {
      case BlockColor.BLACK => Material.BLACK_STAINED_GLASS
      case BlockColor.BLUE => Material.BLUE_STAINED_GLASS
      case BlockColor.BROWN => Material.BROWN_STAINED_GLASS
      case BlockColor.CYAN => Material.CYAN_STAINED_GLASS
      case BlockColor.GRAY => Material.GRAY_STAINED_GLASS
      case BlockColor.GREEN => Material.GREEN_STAINED_GLASS
      case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_STAINED_GLASS
      case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_STAINED_GLASS
      case BlockColor.LIME => Material.LIME_STAINED_GLASS
      case BlockColor.MAGENTA => Material.MAGENTA_STAINED_GLASS
      case BlockColor.ORANGE => Material.ORANGE_STAINED_GLASS
      case BlockColor.PINK => Material.PINK_STAINED_GLASS
      case BlockColor.PURPLE => Material.PURPLE_STAINED_GLASS
      case BlockColor.RED => Material.RED_STAINED_GLASS
      case BlockColor.WHITE => Material.WHITE_STAINED_GLASS
      case BlockColor.YELLOW => Material.YELLOW_STAINED_GLASS
    }

    case GlassPane(_, Option.empty) => Material.GLASS_PANE
    case GlassPane(_, _) => block.color.get match {
      case BlockColor.BLACK => Material.BLACK_STAINED_GLASS_PANE
      case BlockColor.BLUE => Material.BLUE_STAINED_GLASS_PANE
      case BlockColor.BROWN => Material.BROWN_STAINED_GLASS_PANE
      case BlockColor.CYAN => Material.CYAN_STAINED_GLASS_PANE
      case BlockColor.GRAY => Material.GRAY_STAINED_GLASS_PANE
      case BlockColor.GREEN => Material.GREEN_STAINED_GLASS_PANE
      case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_STAINED_GLASS_PANE
      case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_STAINED_GLASS_PANE
      case BlockColor.LIME => Material.LIME_STAINED_GLASS_PANE
      case BlockColor.MAGENTA => Material.MAGENTA_STAINED_GLASS_PANE
      case BlockColor.ORANGE => Material.ORANGE_STAINED_GLASS_PANE
      case BlockColor.PINK => Material.PINK_STAINED_GLASS_PANE
      case BlockColor.PURPLE => Material.PURPLE_STAINED_GLASS_PANE
      case BlockColor.RED => Material.RED_STAINED_GLASS_PANE
      case BlockColor.WHITE => Material.WHITE_STAINED_GLASS_PANE
      case BlockColor.YELLOW => Material.YELLOW_STAINED_GLASS_PANE
    }

    case ShulkerBox(_, Option.empty) => Material.SHULKER_BOX
    case ShulkerBox(_, _) => block.color.get match {
      case BlockColor.BLACK => Material.BLACK_SHULKER_BOX
      case BlockColor.BLUE => Material.BLUE_SHULKER_BOX
      case BlockColor.BROWN => Material.BROWN_SHULKER_BOX
      case BlockColor.CYAN => Material.CYAN_SHULKER_BOX
      case BlockColor.GRAY => Material.GRAY_SHULKER_BOX
      case BlockColor.GREEN => Material.GREEN_SHULKER_BOX
      case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_SHULKER_BOX
      case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_SHULKER_BOX
      case BlockColor.LIME => Material.LIME_SHULKER_BOX
      case BlockColor.MAGENTA => Material.MAGENTA_SHULKER_BOX
      case BlockColor.ORANGE => Material.ORANGE_SHULKER_BOX
      case BlockColor.PINK => Material.PINK_SHULKER_BOX
      case BlockColor.PURPLE => Material.PURPLE_SHULKER_BOX
      case BlockColor.RED => Material.RED_SHULKER_BOX
      case BlockColor.WHITE => Material.WHITE_SHULKER_BOX
      case BlockColor.YELLOW => Material.YELLOW_SHULKER_BOX
    }

    case Terracotta(_, Option.empty) => Material.TERRACOTTA
    case Terracotta(_, _) => block.color.get match {
      case BlockColor.BLACK => Material.BLACK_TERRACOTTA
      case BlockColor.BLUE => Material.BLUE_TERRACOTTA
      case BlockColor.BROWN => Material.BROWN_TERRACOTTA
      case BlockColor.CYAN => Material.CYAN_TERRACOTTA
      case BlockColor.GRAY => Material.GRAY_TERRACOTTA
      case BlockColor.GREEN => Material.GREEN_TERRACOTTA
      case BlockColor.LIGHT_BLUE => Material.LIGHT_BLUE_TERRACOTTA
      case BlockColor.LIGHT_GRAY => Material.LIGHT_GRAY_TERRACOTTA
      case BlockColor.LIME => Material.LIME_TERRACOTTA
      case BlockColor.MAGENTA => Material.MAGENTA_TERRACOTTA
      case BlockColor.ORANGE => Material.ORANGE_TERRACOTTA
      case BlockColor.PINK => Material.PINK_TERRACOTTA
      case BlockColor.PURPLE => Material.PURPLE_TERRACOTTA
      case BlockColor.RED => Material.RED_TERRACOTTA
      case BlockColor.WHITE => Material.WHITE_TERRACOTTA
      case BlockColor.YELLOW => Material.YELLOW_TERRACOTTA
    }

    case _ => throw new IllegalArgumentException(s"Failed to map block to material: ${ block.getClass.getSimpleName }")
  }
}
