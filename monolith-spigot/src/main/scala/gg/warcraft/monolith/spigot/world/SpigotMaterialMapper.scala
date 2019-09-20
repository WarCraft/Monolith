package gg.warcraft.monolith.spigot.world

import gg.warcraft.monolith.api.world.block.{ Block, BlockMaterial, MaterialBlock }
import gg.warcraft.monolith.api.world.block.`type`._
import gg.warcraft.monolith.api.world.block.material._
import org.bukkit.Material

class SpigotMaterialMapper {

  def map(material: Material): BlockMaterial = material match {

    // AIR
    case Material.AIR => AirMaterial.NORMAL
    case Material.CAVE_AIR => AirMaterial.CAVE
    case Material.VOID_AIR => AirMaterial.VOID

    // BAMBOO
    case Material.BAMBOO | Material.BAMBOO_SAPLING =>
      BambooMaterial.BAMBOO

    // BRICK
    case Material.BRICKS | Material.BRICK_SLAB | Material.BRICK_STAIRS | Material.BRICK_WALL =>
      BrickMaterial.BRICK

    case Material.NETHER_BRICKS | Material.NETHER_BRICK_FENCE | Material.NETHER_BRICK_SLAB |
         Material.NETHER_BRICK_STAIRS | Material.NETHER_BRICK_WALL =>
      BrickMaterial.NETHER_BRICK

    case Material.RED_NETHER_BRICKS | Material.RED_NETHER_BRICK_SLAB |
         Material.RED_NETHER_BRICK_STAIRS | Material.RED_NETHER_BRICK_WALL =>
      BrickMaterial.RED_NETHER_BRICK

    // CHEST
    case Material.CHEST => ChestMaterial.NORMAL
    case Material.ENDER_CHEST => ChestMaterial.ENDER
    case Material.TRAPPED_CHEST => ChestMaterial.TRAPPED

    // COMMAND_BLOCK
    case Material.COMMAND_BLOCK => CommandBlockMaterial.NORMAL
    case Material.CHAIN_COMMAND_BLOCK => CommandBlockMaterial.CHAIN
    case Material.REPEATING_COMMAND_BLOCK => CommandBlockMaterial.REPEATING

    // CORAL
    case Material.BRAIN_CORAL | Material.BRAIN_CORAL_BLOCK |
         Material.BRAIN_CORAL_FAN | Material.BRAIN_CORAL_WALL_FAN =>
      CoralMaterial.BRAIN

    case Material.BUBBLE_CORAL | Material.BUBBLE_CORAL_BLOCK |
         Material.BUBBLE_CORAL_FAN | Material.BUBBLE_CORAL_WALL_FAN =>
      CoralMaterial.BUBBLE

    case Material.FIRE_CORAL | Material.FIRE_CORAL_BLOCK |
         Material.FIRE_CORAL_FAN | Material.FIRE_CORAL_WALL_FAN =>
      CoralMaterial.FIRE

    case Material.HORN_CORAL | Material.HORN_CORAL_BLOCK |
         Material.HORN_CORAL_FAN | Material.HORN_CORAL_WALL_FAN =>
      CoralMaterial.HORN

    case Material.TUBE_CORAL | Material.TUBE_CORAL_BLOCK |
         Material.TUBE_CORAL_FAN | Material.TUBE_CORAL_WALL_FAN =>
      CoralMaterial.TUBE

    case Material.DEAD_BRAIN_CORAL | Material.DEAD_BRAIN_CORAL_BLOCK |
         Material.DEAD_BRAIN_CORAL_FAN | Material.DEAD_BRAIN_CORAL_WALL_FAN =>
      CoralMaterial.DEAD_BRAIN

    case Material.DEAD_BUBBLE_CORAL | Material.DEAD_BUBBLE_CORAL_BLOCK |
         Material.DEAD_BUBBLE_CORAL_FAN | Material.DEAD_BUBBLE_CORAL_WALL_FAN =>
      CoralMaterial.DEAD_BUBBLE

    case Material.DEAD_FIRE_CORAL | Material.DEAD_FIRE_CORAL_BLOCK |
         Material.DEAD_FIRE_CORAL_FAN | Material.DEAD_FIRE_CORAL_WALL_FAN =>
      CoralMaterial.DEAD_FIRE

    case Material.DEAD_HORN_CORAL | Material.DEAD_HORN_CORAL_BLOCK |
         Material.DEAD_HORN_CORAL_FAN | Material.DEAD_HORN_CORAL_WALL_FAN =>
      CoralMaterial.DEAD_HORN

    case Material.DEAD_TUBE_CORAL | Material.DEAD_TUBE_CORAL_BLOCK |
         Material.DEAD_TUBE_CORAL_FAN | Material.DEAD_TUBE_CORAL_WALL_FAN =>
      CoralMaterial.DEAD_TUBE

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

    // FLOWER_POT
    case Material.POTTED_ALLIUM | Material.POTTED_AZURE_BLUET | Material.POTTED_BLUE_ORCHID |
         Material.POTTED_CORNFLOWER | Material.POTTED_DANDELION | Material.POTTED_LILY_OF_THE_VALLEY |
         Material.POTTED_ORANGE_TULIP | Material.POTTED_OXEYE_DAISY | Material.POTTED_PINK_TULIP |
         Material.POTTED_POPPY | Material.POTTED_RED_TULIP | Material.POTTED_WHITE_TULIP |
         Material.POTTED_WITHER_ROSE |

         Material.POTTED_BAMBOO | Material.POTTED_BROWN_MUSHROOM | Material.POTTED_CACTUS |
         Material.POTTED_DEAD_BUSH | Material.POTTED_FERN | Material.POTTED_RED_MUSHROOM |

         Material.POTTED_ACACIA_SAPLING | Material.POTTED_BIRCH_SAPLING | Material.POTTED_DARK_OAK_SAPLING |
         Material.POTTED_JUNGLE_SAPLING | Material.POTTED_OAK_SAPLING | Material.POTTED_SPRUCE_SAPLING =>
      null // TODO

    // ICE
    case Material.ICE => IceMaterial.NORMAL
    case Material.BLUE_ICE => IceMaterial.BLUE
    case Material.PACKED_ICE => IceMaterial.PACKED

    // IRON
    case Material.IRON_DOOR => IronMaterial.IRON

    // INFESTED_BLOCK
    case Material.INFESTED_STONE => StoneMaterial.STONE
    case Material.INFESTED_COBBLESTONE => StoneMaterial.COBBLESTONE
    case Material.INFESTED_STONE_BRICKS => StoneMaterial.STONE_BRICK
    case Material.INFESTED_CRACKED_STONE_BRICKS => StoneMaterial.CRACKED_STONE_BRICK
    case Material.INFESTED_CHISELED_STONE_BRICKS => StoneMaterial.CHISELED_STONE_BRICK
    case Material.INFESTED_MOSSY_STONE_BRICKS => StoneMaterial.MOSSY_STONE_BRICK

    // MOB_HEAD
    case Material.CREEPER_HEAD | Material.CREEPER_WALL_HEAD => MobHeadMaterial.CREEPER
    case Material.DRAGON_HEAD | Material.DRAGON_WALL_HEAD => MobHeadMaterial.DRAGON
    case Material.PLAYER_HEAD | Material.PLAYER_WALL_HEAD => MobHeadMaterial.PLAYER
    case Material.SKELETON_SKULL | Material.SKELETON_WALL_SKULL => MobHeadMaterial.SKELETON
    case Material.WITHER_SKELETON_SKULL | Material.WITHER_SKELETON_WALL_SKULL => MobHeadMaterial.WITHER_SKELETON
    case Material.ZOMBIE_HEAD | Material.ZOMBIE_WALL_HEAD => MobHeadMaterial.ZOMBIE

    // MUSHROOM
    case Material.BROWN_MUSHROOM => MushroomMaterial.BROWN
    case Material.RED_MUSHROOM => MushroomMaterial.RED

    // MUSHROOM_BLOCK
    case Material.BROWN_MUSHROOM_BLOCK => MushroomBlockMaterial.BROWN
    case Material.RED_MUSHROOM_BLOCK => MushroomBlockMaterial.RED
    case Material.MUSHROOM_STEM => MushroomBlockMaterial.STEM

    // PRISMARINE
    case Material.PRISMARINE |
         Material.PRISMARINE_SLAB | Material.PRISMARINE_STAIRS | Material.PRISMARINE_WALL =>
      PrismarineMaterial.PRISMARINE
    case Material.DARK_PRISMARINE |
         Material.PRISMARINE_BRICK_SLAB | Material.PRISMARINE_BRICK_STAIRS =>
      PrismarineMaterial.DARK_PRISMARINE
    case Material.PRISMARINE_BRICKS |
         Material.PRISMARINE_BRICK_SLAB | Material.PRISMARINE_BRICK_STAIRS =>
      PrismarineMaterial.PRISMARINE_BRICK

    // PURPUR
    case Material.PURPUR_BLOCK |
         Material.PURPUR_PILLAR | Material.PURPUR_SLAB | Material.PURPUR_STAIRS =>
      PurpurMaterial.PURPUR

    // QUARTZ
    case Material.QUARTZ_BLOCK |
         Material.QUARTZ_PILLAR | Material.QUARTZ_SLAB | Material.QUARTZ_STAIRS =>
      QuartzMaterial.QUARTZ

    case Material.CHISELED_QUARTZ_BLOCK => QuartzMaterial.CHISELED_QUARTZ

    case Material.SMOOTH_QUARTZ |
         Material.SMOOTH_QUARTZ_SLAB | Material.SMOOTH_QUARTZ_STAIRS =>
      QuartzMaterial.SMOOTH_QUARTZ

    // RAILS
    case Material.RAIL => RailsMaterial.NORMAL
    case Material.ACTIVATOR_RAIL => RailsMaterial.ACTIVATOR
    case Material.DETECTOR_RAIL => RailsMaterial.DETECTOR
    case Material.POWERED_RAIL => RailsMaterial.POWERED

    // RESOURCE
    case Material.COAL_ORE | Material.COAL_BLOCK => ResourceMaterial.COAL
    case Material.DIAMOND_ORE | Material.DIAMOND_BLOCK => ResourceMaterial.DIAMOND
    case Material.EMERALD_ORE | Material.EMERALD_BLOCK => ResourceMaterial.EMERALD
    case Material.GOLD_ORE | Material.GOLD_BLOCK => ResourceMaterial.GOLD
    case Material.IRON_ORE | Material.IRON_BLOCK => ResourceMaterial.IRON
    case Material.LAPIS_ORE | Material.LAPIS_BLOCK => ResourceMaterial.LAPIS_LAZULI
    case Material.NETHER_QUARTZ_ORE => ResourceMaterial.NETHER_QUARTZ
    case Material.REDSTONE_ORE | Material.REDSTONE_BLOCK => ResourceMaterial.REDSTONE

    // SAND
    case Material.SAND => SandMaterial.SAND
    case Material.RED_SAND => SandMaterial.RED_SAND
    case Material.SOUL_SAND => SandMaterial.SOUL_SAND

    // SANDSTONE
    case Material.SANDSTONE |
         Material.SANDSTONE_SLAB | Material.SANDSTONE_STAIRS | Material.SANDSTONE_WALL |
         Material.CHISELED_SANDSTONE | Material.CUT_SANDSTONE | Material.SMOOTH_SANDSTONE |
         Material.SMOOTH_SANDSTONE_SLAB | Material.SMOOTH_SANDSTONE_STAIRS | Material.CUT_SANDSTONE_SLAB =>
      SandstoneMaterial.SANDSTONE

    case Material.RED_SANDSTONE |
         Material.RED_SANDSTONE_SLAB | Material.RED_SANDSTONE_STAIRS | Material.RED_SANDSTONE_WALL |
         Material.CHISELED_RED_SANDSTONE | Material.CUT_RED_SANDSTONE | Material.SMOOTH_RED_SANDSTONE |
         Material.SMOOTH_RED_SANDSTONE_SLAB | Material.SMOOTH_RED_SANDSTONE_STAIRS | Material.CUT_RED_SANDSTONE_SLAB =>
      SandstoneMaterial.RED_SANDSTONE

    // STONE
    case Material.STONE | Material.STONE_SLAB | Material.STONE_BUTTON =>
      StoneMaterial.STONE

    case Material.STONE_BRICKS |
         Material.STONE_BRICK_SLAB | Material.STONE_BRICK_STAIRS | Material.STONE_BRICK_WALL =>
      StoneMaterial.STONE_BRICK

    case Material.CHISELED_STONE_BRICKS => StoneMaterial.CHISELED_STONE_BRICK

    case Material.COBBLESTONE |
         Material.COBBLESTONE_SLAB | Material.COBBLESTONE_STAIRS | Material.COBBLESTONE_WALL =>
      StoneMaterial.COBBLESTONE

    case Material.MOSSY_COBBLESTONE |
         Material.MOSSY_COBBLESTONE_SLAB | Material.MOSSY_COBBLESTONE_STAIRS | Material.MOSSY_COBBLESTONE_WALL =>
      StoneMaterial.MOSSY_COBBLESTONE

    case Material.MOSSY_STONE_BRICK_SLAB |
         Material.MOSSY_STONE_BRICK_STAIRS | Material.MOSSY_STONE_BRICK_WALL | Material.MOSSY_STONE_BRICKS =>
      StoneMaterial.MOSSY_STONE_BRICK

    case Material.CRACKED_STONE_BRICKS => StoneMaterial.CRACKED_STONE_BRICK

    case Material.END_STONE | Material.END_STONE_BRICKS |
         Material.END_STONE_BRICK_SLAB | Material.END_STONE_BRICK_STAIRS | Material.END_STONE_BRICK_WALL =>
      StoneMaterial.END_STONE

    case Material.SMOOTH_STONE | Material.SMOOTH_STONE_SLAB =>
      StoneMaterial.SMOOTH_STONE

    // WEIGHTED_PRESSURE_PLATE
    case Material.HEAVY_WEIGHTED_PRESSURE_PLATE => WeightedPressurePlateMaterial.HEAVY
    case Material.LIGHT_WEIGHTED_PRESSURE_PLATE => WeightedPressurePlateMaterial.LIGHT

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

  def map(block: Block): Material = block match {

    // AIR
    case air: Air => air.material match {
      case AirMaterial.NORMAL => Material.AIR
      case AirMaterial.CAVE => Material.CAVE_AIR
      case AirMaterial.VOID => Material.VOID_AIR
    }

    // TODO map all 150 Monolith blocks to their Material

    // TODO call colorMapper as necessary
  }
}

// TODO implement StatefulBlock twice on TurtleEgg to remove nesting of state obj?
// TODO rename all state enums that dont need it to make sense, so keep AnvilState
// TODO but rename WeightedPressurePlateState to WeightedPressurePlatePower
// TODO and TurtleEggCount plus TurtleEggAge