package gg.warcraft.monolith.spigot.world

import gg.warcraft.monolith.api.world.block._
import org.bukkit.Material

class SpigotMaterialMapper {

  def map(material: Material): BlockMaterial = material match {

    // AIR
    case Material.AIR => AirMaterial.NORMAL
    case Material.CAVE_AIR => AirMaterial.CAVE
    case Material.VOID_AIR => AirMaterial.VOID

    // COMMAND_BLOCK
    case Material.COMMAND_BLOCK => CommandBlockMaterial.NORMAL
    case Material.CHAIN_COMMAND_BLOCK => CommandBlockMaterial.CHAIN
    case Material.REPEATING_COMMAND_BLOCK => CommandBlockMaterial.REPEATING

    // FLOWER
    case Material.ALLIUM => FlowerMaterial.ALLIUM
    case Material.AZURE_BLUET => FlowerMaterial.AZURE_BLUET
    case Material.BLUE_ORCHID => FlowerMaterial.BLUE_ORCHID
    case Material.CORNFLOWER => FlowerMaterial.CORNFLOWER
    case Material.DANDELION => FlowerMaterial.DANDELION
    case Material.LILY_OF_THE_VALLEY => FlowerMaterial.LILY_OF_THE_VALLEY
    case Material.ORANGE_TULIP => FlowerMaterial.ORANGE_TULIP
    case Material.OXEYE_DAISY => FlowerMaterial.OXEYE_DAISY
    case Material.PINK_TULIP => FlowerMaterial.PINK_TULIP
    case Material.POPPY => FlowerMaterial.POPPY
    case Material.RED_TULIP => FlowerMaterial.RED_TULIP
    case Material.WHITE_TULIP => FlowerMaterial.WHITE_TULIP
    case Material.WITHER_ROSE => FlowerMaterial.WITHER_ROSE

    // MOB_HEAD
    case Material.CREEPER_HEAD | Material.CREEPER_WALL_HEAD => MobHeadMaterial.CREEPER
    case Material.DRAGON_HEAD | Material.DRAGON_WALL_HEAD => MobHeadMaterial.DRAGON
    case Material.PLAYER_HEAD | Material.PLAYER_WALL_HEAD => MobHeadMaterial.PLAYER
    case Material.SKELETON_SKULL | Material.SKELETON_WALL_SKULL => MobHeadMaterial.SKELETON
    case Material.WITHER_SKELETON_SKULL | Material.WITHER_SKELETON_WALL_SKULL => MobHeadMaterial.WITHER_SKELETON
    case Material.ZOMBIE_HEAD | Material.ZOMBIE_WALL_HEAD => MobHeadMaterial.ZOMBIE

    // PILLAR
    case Material.PURPUR_PILLAR => PurpurMaterial.PURPUR
    case Material.QUARTZ_PILLAR => QuartzMaterial.QUARTZ

    // RESOURCE
    case Material.COAL_ORE => ResourceMaterial.COAL
    case Material.DIAMOND_ORE => ResourceMaterial.DIAMOND
    case Material.EMERALD_ORE => ResourceMaterial.EMERALD
    case Material.GOLD_ORE => ResourceMaterial.GOLD
    case Material.IRON_ORE => ResourceMaterial.IRON
    case Material.LAPIS_ORE => ResourceMaterial.LAPIS_LAZULI
    case Material.NETHER_QUARTZ_ORE => ResourceMaterial.NETHER_QUARTZ
    case Material.REDSTONE_ORE => ResourceMaterial.REDSTONE

    // WOOD
    case Material.ACACIA_BUTTON | Material.ACACIA_DOOR | Material.ACACIA_FENCE | Material.ACACIA_FENCE_GATE |
         Material.ACACIA_LEAVES | Material.ACACIA_LOG | Material.ACACIA_PLANKS | Material.ACACIA_PRESSURE_PLATE |
         Material.ACACIA_SAPLING | Material.ACACIA_SIGN | Material.ACACIA_SLAB | Material.ACACIA_STAIRS |
         Material.ACACIA_TRAPDOOR | Material.ACACIA_WALL_SIGN | Material.ACACIA_WOOD |
         Material.STRIPPED_ACACIA_LOG | Material.STRIPPED_ACACIA_WOOD =>
      WoodMaterial.ACACIA

    case Material.BIRCH_BUTTON | Material.BIRCH_DOOR | Material.BIRCH_FENCE | Material.BIRCH_FENCE_GATE |
         Material.BIRCH_LEAVES | Material.BIRCH_LOG | Material.BIRCH_PLANKS | Material.BIRCH_PRESSURE_PLATE |
         Material.BIRCH_SAPLING | Material.BIRCH_SIGN | Material.BIRCH_SLAB | Material.BIRCH_STAIRS |
         Material.BIRCH_TRAPDOOR | Material.BIRCH_WALL_SIGN | Material.BIRCH_WOOD |
         Material.STRIPPED_BIRCH_LOG | Material.STRIPPED_BIRCH_WOOD =>
      WoodMaterial.BIRCH

    case Material.DARK_OAK_BUTTON | Material.DARK_OAK_DOOR | Material.DARK_OAK_FENCE | Material.DARK_OAK_FENCE_GATE |
         Material.DARK_OAK_LEAVES | Material.DARK_OAK_LOG | Material.DARK_OAK_PLANKS | Material.DARK_OAK_PRESSURE_PLATE |
         Material.DARK_OAK_SAPLING | Material.DARK_OAK_SIGN | Material.DARK_OAK_SLAB | Material.DARK_OAK_STAIRS |
         Material.DARK_OAK_TRAPDOOR | Material.DARK_OAK_WALL_SIGN | Material.DARK_OAK_WOOD |
         Material.STRIPPED_DARK_OAK_LOG | Material.STRIPPED_DARK_OAK_WOOD =>
      WoodMaterial.DARK_OAK

    case Material.JUNGLE_BUTTON | Material.JUNGLE_DOOR | Material.JUNGLE_FENCE | Material.JUNGLE_FENCE_GATE |
         Material.JUNGLE_LEAVES | Material.JUNGLE_LOG | Material.JUNGLE_PLANKS | Material.JUNGLE_PRESSURE_PLATE |
         Material.JUNGLE_SAPLING | Material.JUNGLE_SIGN | Material.JUNGLE_SLAB | Material.JUNGLE_STAIRS |
         Material.JUNGLE_TRAPDOOR | Material.JUNGLE_WALL_SIGN | Material.JUNGLE_WOOD |
         Material.STRIPPED_JUNGLE_LOG | Material.STRIPPED_JUNGLE_WOOD =>
      WoodMaterial.JUNGLE

    case Material.OAK_BUTTON | Material.OAK_DOOR | Material.OAK_FENCE | Material.OAK_FENCE_GATE |
         Material.OAK_LEAVES | Material.OAK_LOG | Material.OAK_PLANKS | Material.OAK_PRESSURE_PLATE |
         Material.OAK_SAPLING | Material.OAK_SIGN | Material.OAK_SLAB | Material.OAK_STAIRS |
         Material.OAK_TRAPDOOR | Material.OAK_WALL_SIGN | Material.OAK_WOOD |
         Material.STRIPPED_OAK_LOG | Material.STRIPPED_OAK_WOOD =>
      WoodMaterial.OAK

    case Material.SPRUCE_BUTTON | Material.SPRUCE_DOOR | Material.SPRUCE_FENCE | Material.SPRUCE_FENCE_GATE |
         Material.SPRUCE_LEAVES | Material.SPRUCE_LOG | Material.SPRUCE_PLANKS | Material.SPRUCE_PRESSURE_PLATE |
         Material.SPRUCE_SAPLING | Material.SPRUCE_SIGN | Material.SPRUCE_SLAB | Material.SPRUCE_STAIRS |
         Material.SPRUCE_TRAPDOOR | Material.SPRUCE_WALL_SIGN | Material.SPRUCE_WOOD |
         Material.STRIPPED_SPRUCE_LOG | Material.STRIPPED_SPRUCE_WOOD =>
      WoodMaterial.SPRUCE
  }

  def mapColor(material: Material): BlockColor = material match {

    // BLACK
    case Material.BLACK_BANNER | Material.BLACK_BED | Material.BLACK_CARPET | Material.BLACK_CONCRETE |
         Material.BLACK_CONCRETE_POWDER | Material.BLACK_GLAZED_TERRACOTTA | Material.BLACK_SHULKER_BOX | Material.BLACK_STAINED_GLASS |
         Material.BLACK_STAINED_GLASS_PANE | Material.BLACK_TERRACOTTA | Material.BLACK_WALL_BANNER | Material.BLACK_WOOL =>
      BlockColor.BLACK

    // BLUE
    case Material.BLUE_BANNER | Material.BLUE_BED | Material.BLUE_CARPET | Material.BLUE_CONCRETE |
         Material.BLUE_CONCRETE_POWDER | Material.BLUE_GLAZED_TERRACOTTA | Material.BLUE_SHULKER_BOX | Material.BLUE_STAINED_GLASS |
         Material.BLUE_STAINED_GLASS_PANE | Material.BLUE_TERRACOTTA | Material.BLUE_WALL_BANNER | Material.BLUE_WOOL =>
      BlockColor.BLUE

    // BROWN
    case Material.BROWN_BANNER | Material.BROWN_BED | Material.BROWN_CARPET | Material.BROWN_CONCRETE |
         Material.BROWN_CONCRETE_POWDER | Material.BROWN_GLAZED_TERRACOTTA | Material.BROWN_SHULKER_BOX | Material.BROWN_STAINED_GLASS |
         Material.BROWN_STAINED_GLASS_PANE | Material.BROWN_TERRACOTTA | Material.BROWN_WALL_BANNER | Material.BROWN_WOOL =>
      BlockColor.BROWN

    // CYAN
    case Material.CYAN_BANNER | Material.CYAN_BED | Material.CYAN_CARPET | Material.CYAN_CONCRETE |
         Material.CYAN_CONCRETE_POWDER | Material.CYAN_GLAZED_TERRACOTTA | Material.CYAN_SHULKER_BOX | Material.CYAN_STAINED_GLASS |
         Material.CYAN_STAINED_GLASS_PANE | Material.CYAN_TERRACOTTA | Material.CYAN_WALL_BANNER | Material.CYAN_WOOL =>
      BlockColor.CYAN

    // GRAY
    case Material.GRAY_BANNER | Material.GRAY_BED | Material.GRAY_CARPET | Material.GRAY_CONCRETE |
         Material.GRAY_CONCRETE_POWDER | Material.GRAY_GLAZED_TERRACOTTA | Material.GRAY_SHULKER_BOX | Material.GRAY_STAINED_GLASS |
         Material.GRAY_STAINED_GLASS_PANE | Material.GRAY_TERRACOTTA | Material.GRAY_WALL_BANNER | Material.GRAY_WOOL =>
      BlockColor.GRAY

    // GREEN
    case Material.GREEN_BANNER | Material.GREEN_BED | Material.GREEN_CARPET | Material.GREEN_CONCRETE |
         Material.GREEN_CONCRETE_POWDER | Material.GREEN_GLAZED_TERRACOTTA | Material.GREEN_SHULKER_BOX | Material.GREEN_STAINED_GLASS |
         Material.GREEN_STAINED_GLASS_PANE | Material.GREEN_TERRACOTTA | Material.GREEN_WALL_BANNER | Material.GREEN_WOOL =>
      BlockColor.GREEN

    // LIGHT_BLUE
    case Material.LIGHT_BLUE_BANNER | Material.LIGHT_BLUE_BED | Material.LIGHT_BLUE_CARPET | Material.LIGHT_BLUE_CONCRETE |
         Material.LIGHT_BLUE_CONCRETE_POWDER | Material.LIGHT_BLUE_GLAZED_TERRACOTTA | Material.LIGHT_BLUE_SHULKER_BOX | Material.LIGHT_BLUE_STAINED_GLASS |
         Material.LIGHT_BLUE_STAINED_GLASS_PANE | Material.LIGHT_BLUE_TERRACOTTA | Material.LIGHT_BLUE_WALL_BANNER | Material.LIGHT_BLUE_WOOL =>
      BlockColor.LIGHT_BLUE

    // LIGHT_GRAY
    case Material.LIGHT_GRAY_BANNER | Material.LIGHT_GRAY_BED | Material.LIGHT_GRAY_CARPET | Material.LIGHT_GRAY_CONCRETE |
         Material.LIGHT_GRAY_CONCRETE_POWDER | Material.LIGHT_GRAY_GLAZED_TERRACOTTA | Material.LIGHT_GRAY_SHULKER_BOX | Material.LIGHT_GRAY_STAINED_GLASS |
         Material.LIGHT_GRAY_STAINED_GLASS_PANE | Material.LIGHT_GRAY_TERRACOTTA | Material.LIGHT_GRAY_WALL_BANNER | Material.LIGHT_GRAY_WOOL =>
      BlockColor.LIGHT_GRAY

    // LIME
    case Material.LIME_BANNER | Material.LIME_BED | Material.LIME_CARPET | Material.LIME_CONCRETE |
         Material.LIME_CONCRETE_POWDER | Material.LIME_GLAZED_TERRACOTTA | Material.LIME_SHULKER_BOX | Material.LIME_STAINED_GLASS |
         Material.LIME_STAINED_GLASS_PANE | Material.LIME_TERRACOTTA | Material.LIME_WALL_BANNER | Material.LIME_WOOL =>
      BlockColor.LIME

    // MAGENTA
    case Material.MAGENTA_BANNER | Material.MAGENTA_BED | Material.MAGENTA_CARPET | Material.MAGENTA_CONCRETE |
         Material.MAGENTA_CONCRETE_POWDER | Material.MAGENTA_GLAZED_TERRACOTTA | Material.MAGENTA_SHULKER_BOX | Material.MAGENTA_STAINED_GLASS |
         Material.MAGENTA_STAINED_GLASS_PANE | Material.MAGENTA_TERRACOTTA | Material.MAGENTA_WALL_BANNER | Material.MAGENTA_WOOL =>
      BlockColor.MAGENTA

    // ORANGE
    case Material.ORANGE_BANNER | Material.ORANGE_BED | Material.ORANGE_CARPET | Material.ORANGE_CONCRETE |
         Material.ORANGE_CONCRETE_POWDER | Material.ORANGE_GLAZED_TERRACOTTA | Material.ORANGE_SHULKER_BOX | Material.ORANGE_STAINED_GLASS |
         Material.ORANGE_STAINED_GLASS_PANE | Material.ORANGE_TERRACOTTA | Material.ORANGE_WALL_BANNER | Material.ORANGE_WOOL =>
      BlockColor.ORANGE

    // PINK
    case Material.PINK_BANNER | Material.PINK_BED | Material.PINK_CARPET | Material.PINK_CONCRETE |
         Material.PINK_CONCRETE_POWDER | Material.PINK_GLAZED_TERRACOTTA | Material.PINK_SHULKER_BOX | Material.PINK_STAINED_GLASS |
         Material.PINK_STAINED_GLASS_PANE | Material.PINK_TERRACOTTA | Material.PINK_WALL_BANNER | Material.PINK_WOOL =>
      BlockColor.PINK

    // PURPLE
    case Material.PURPLE_BANNER | Material.PURPLE_BED | Material.PURPLE_CARPET | Material.PURPLE_CONCRETE |
         Material.PURPLE_CONCRETE_POWDER | Material.PURPLE_GLAZED_TERRACOTTA | Material.PURPLE_SHULKER_BOX | Material.PURPLE_STAINED_GLASS |
         Material.PURPLE_STAINED_GLASS_PANE | Material.PURPLE_TERRACOTTA | Material.PURPLE_WALL_BANNER | Material.PURPLE_WOOL =>
      BlockColor.PURPLE

    // RED
    case Material.RED_BANNER | Material.RED_BED | Material.RED_CARPET | Material.RED_CONCRETE |
         Material.RED_CONCRETE_POWDER | Material.RED_GLAZED_TERRACOTTA | Material.RED_SHULKER_BOX | Material.RED_STAINED_GLASS |
         Material.RED_STAINED_GLASS_PANE | Material.RED_TERRACOTTA | Material.RED_WALL_BANNER | Material.RED_WOOL =>
      BlockColor.RED

    // WHITE
    case Material.WHITE_BANNER | Material.WHITE_BED | Material.WHITE_CARPET | Material.WHITE_CONCRETE |
         Material.WHITE_CONCRETE_POWDER | Material.WHITE_GLAZED_TERRACOTTA | Material.WHITE_SHULKER_BOX | Material.WHITE_STAINED_GLASS |
         Material.WHITE_STAINED_GLASS_PANE | Material.WHITE_TERRACOTTA | Material.WHITE_WALL_BANNER | Material.WHITE_WOOL =>
      BlockColor.WHITE

    // YELLOW
    case Material.YELLOW_BANNER | Material.YELLOW_BED | Material.YELLOW_CARPET | Material.YELLOW_CONCRETE |
         Material.YELLOW_CONCRETE_POWDER | Material.YELLOW_GLAZED_TERRACOTTA | Material.YELLOW_SHULKER_BOX | Material.YELLOW_STAINED_GLASS |
         Material.YELLOW_STAINED_GLASS_PANE | Material.YELLOW_TERRACOTTA | Material.YELLOW_WALL_BANNER | Material.YELLOW_WOOL =>
      BlockColor.YELLOW

    // NULL
    case Material.GLASS | Material.GLASS_PANE | Material.SHULKER_BOX | Material.TERRACOTTA => null

    case _ => throw new IllegalArgumentException(s"Failed to map color for material: $material")
  }
}
