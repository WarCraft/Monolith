package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.variant._
import org.bukkit.{Material, Instrument => SpigotInstrument}
import org.bukkit.block.data.`type`.Bamboo.{Leaves => SpigotBambooLeaves}
import org.bukkit.block.data.`type`.Comparator.{Mode => SpigotComparatorMode}
import org.bukkit.block.data.`type`.StructureBlock.{Mode => SpigotStructureBlockMode}

class SpigotBlockVariantMapper {
  def map(material: Material): BlockVariant = {
    // AIR
    case Material.AIR      => AirVariant.NORMAL
    case Material.CAVE_AIR => AirVariant.CAVE
    case Material.VOID_AIR => AirVariant.VOID

    // ANVIL
    case Material.ANVIL         => AnvilVariant.NORMAL
    case Material.CHIPPED_ANVIL => AnvilVariant.CHIPPED
    case Material.DAMAGED_ANVIL => AnvilVariant.DAMAGED

    // BUTTON
    case Material.ACACIA_BUTTON   => ButtonVariant.ACACIA
    case Material.BIRCH_BUTTON    => ButtonVariant.BIRCH
    case Material.DARK_OAK_BUTTON => ButtonVariant.DARK_OAK
    case Material.JUNGLE_BUTTON   => ButtonVariant.JUNGLE
    case Material.OAK_BUTTON      => ButtonVariant.OAK
    case Material.SPRUCE_BUTTON   => ButtonVariant.SPRUCE

    case Material.STONE_BUTTON => ButtonVariant.STONE

    // CHEST
    case Material.CHEST         => ChestVariant.NORMAL
    case Material.ENDER_CHEST   => ChestVariant.ENDER
    case Material.TRAPPED_CHEST => ChestVariant.TRAPPED

    // COBBLESTONE
    case Material.COBBLESTONE       => CobblestoneVariant.NORMAL
    case Material.MOSSY_COBBLESTONE => CobblestoneVariant.MOSSY

    // COMMAND_BLOCK
    case Material.COMMAND_BLOCK           => CommandBlockVariant.NORMAL
    case Material.CHAIN_COMMAND_BLOCK     => CommandBlockVariant.CHAIN
    case Material.REPEATING_COMMAND_BLOCK => CommandBlockVariant.REPEATING

    // CORAL
    case Material.BRAIN_CORAL  => CoralVariant.BRAIN
    case Material.BUBBLE_CORAL => CoralVariant.BUBBLE
    case Material.FIRE_CORAL   => CoralVariant.FIRE
    case Material.HORN_CORAL   => CoralVariant.HORN
    case Material.TUBE_CORAL   => CoralVariant.TUBE

    case Material.DEAD_BRAIN_CORAL  => CoralVariant.DEAD_BRAIN
    case Material.DEAD_BUBBLE_CORAL => CoralVariant.DEAD_BUBBLE
    case Material.DEAD_FIRE_CORAL   => CoralVariant.DEAD_FIRE
    case Material.DEAD_HORN_CORAL   => CoralVariant.DEAD_HORN
    case Material.DEAD_TUBE_CORAL   => CoralVariant.DEAD_TUBE

    // CORAL_BLOCK
    case Material.BRAIN_CORAL_BLOCK  => CoralBlockVariant.BRAIN
    case Material.BUBBLE_CORAL_BLOCK => CoralBlockVariant.BUBBLE
    case Material.FIRE_CORAL_BLOCK   => CoralBlockVariant.FIRE
    case Material.HORN_CORAL_BLOCK   => CoralBlockVariant.HORN
    case Material.TUBE_CORAL_BLOCK   => CoralBlockVariant.TUBE

    case Material.DEAD_BRAIN_CORAL_BLOCK  => CoralBlockVariant.DEAD_BRAIN
    case Material.DEAD_BUBBLE_CORAL_BLOCK => CoralBlockVariant.DEAD_BUBBLE
    case Material.DEAD_FIRE_CORAL_BLOCK   => CoralBlockVariant.DEAD_FIRE
    case Material.DEAD_HORN_CORAL_BLOCK   => CoralBlockVariant.DEAD_HORN
    case Material.DEAD_TUBE_CORAL_BLOCK   => CoralBlockVariant.DEAD_TUBE

    // CORAL_FAN
    case Material.BRAIN_CORAL_FAN  => CoralFanVariant.BRAIN
    case Material.BUBBLE_CORAL_FAN => CoralFanVariant.BUBBLE
    case Material.FIRE_CORAL_FAN   => CoralFanVariant.FIRE
    case Material.HORN_CORAL_FAN   => CoralFanVariant.HORN
    case Material.TUBE_CORAL_FAN   => CoralFanVariant.TUBE

    case Material.DEAD_BRAIN_CORAL_FAN  => CoralFanVariant.DEAD_BRAIN
    case Material.DEAD_BUBBLE_CORAL_FAN => CoralFanVariant.DEAD_BUBBLE
    case Material.DEAD_FIRE_CORAL_FAN   => CoralFanVariant.DEAD_FIRE
    case Material.DEAD_HORN_CORAL_FAN   => CoralFanVariant.DEAD_HORN
    case Material.DEAD_TUBE_CORAL_FAN   => CoralFanVariant.DEAD_TUBE

    case Material.BRAIN_CORAL_WALL_FAN  => CoralFanVariant.BRAIN_WALL
    case Material.BUBBLE_CORAL_WALL_FAN => CoralFanVariant.BUBBLE_WALL
    case Material.FIRE_CORAL_WALL_FAN   => CoralFanVariant.FIRE_WALL
    case Material.HORN_CORAL_WALL_FAN   => CoralFanVariant.HORN_WALL
    case Material.TUBE_CORAL_WALL_FAN   => CoralFanVariant.TUBE_WALL

    case Material.DEAD_BRAIN_CORAL_WALL_FAN  => CoralFanVariant.DEAD_BRAIN_WALL
    case Material.DEAD_BUBBLE_CORAL_WALL_FAN => CoralFanVariant.DEAD_BUBBLE_WALL
    case Material.DEAD_FIRE_CORAL_WALL_FAN   => CoralFanVariant.DEAD_FIRE_WALL
    case Material.DEAD_HORN_CORAL_WALL_FAN   => CoralFanVariant.DEAD_HORN_WALL
    case Material.DEAD_TUBE_CORAL_WALL_FAN   => CoralFanVariant.DEAD_TUBE_WALL

    // DOOR
    case Material.ACACIA_DOOR   => DoorVariant.ACACIA
    case Material.BIRCH_DOOR    => DoorVariant.BIRCH
    case Material.DARK_OAK_DOOR => DoorVariant.DARK_OAK
    case Material.JUNGLE_DOOR   => DoorVariant.JUNGLE
    case Material.OAK_DOOR      => DoorVariant.OAK
    case Material.SPRUCE_DOOR   => DoorVariant.SPRUCE
    case Material.IRON_DOOR     => DoorVariant.IRON

    // END_STONE
    case Material.END_STONE        => EndStoneVariant.NORMAL
    case Material.END_STONE_BRICKS => EndStoneVariant.BRICK

    // FENCE
    case Material.ACACIA_FENCE   => FenceVariant.ACACIA
    case Material.BIRCH_FENCE    => FenceVariant.BIRCH
    case Material.DARK_OAK_FENCE => FenceVariant.DARK_OAK
    case Material.JUNGLE_FENCE   => FenceVariant.JUNGLE
    case Material.OAK_FENCE      => FenceVariant.OAK
    case Material.SPRUCE_FENCE   => FenceVariant.SPRUCE

    case Material.NETHER_BRICK_FENCE => FenceVariant.NETHER_BRICK

    // FENCE_GATE
    case Material.ACACIA_FENCE_GATE   => FenceGateVariant.ACACIA
    case Material.BIRCH_FENCE_GATE    => FenceGateVariant.BIRCH
    case Material.DARK_OAK_FENCE_GATE => FenceGateVariant.DARK_OAK
    case Material.JUNGLE_FENCE_GATE   => FenceGateVariant.JUNGLE
    case Material.OAK_FENCE_GATE      => FenceGateVariant.OAK
    case Material.SPRUCE_FENCE_GATE   => FenceGateVariant.SPRUCE

    // FLOWER
    case Material.ALLIUM             => FlowerVariant.ALLIUM
    case Material.AZURE_BLUET        => FlowerVariant.AZURE_BLUET
    case Material.BLUE_ORCHID        => FlowerVariant.BLUE_ORCHID
    case Material.CORNFLOWER         => FlowerVariant.CORNFLOWER
    case Material.DANDELION          => FlowerVariant.DANDELION
    case Material.LILY_OF_THE_VALLEY => FlowerVariant.LILY_OF_THE_VALLEY
    case Material.ORANGE_TULIP       => FlowerVariant.ORANGE_TULIP
    case Material.OXEYE_DAISY        => FlowerVariant.OXEYE_DAISY
    case Material.PINK_TULIP         => FlowerVariant.PINK_TULIP
    case Material.POPPY              => FlowerVariant.POPPY
    case Material.RED_TULIP          => FlowerVariant.RED_TULIP
    case Material.WHITE_TULIP        => FlowerVariant.WHITE_TULIP
    case Material.WITHER_ROSE        => FlowerVariant.WITHER_ROSE

    // FLOWER_POT
    case Material.FLOWER_POT => FlowerPotVariant.EMPTY

    case Material.POTTED_ALLIUM             => FlowerPotVariant.ALLIUM
    case Material.POTTED_AZURE_BLUET        => FlowerPotVariant.AZURE_BLUET
    case Material.POTTED_BLUE_ORCHID        => FlowerPotVariant.BLUE_ORCHID
    case Material.POTTED_CORNFLOWER         => FlowerPotVariant.CORNFLOWER
    case Material.POTTED_DANDELION          => FlowerPotVariant.DANDELION
    case Material.POTTED_ORANGE_TULIP       => FlowerPotVariant.ORANGE_TULIP
    case Material.POTTED_OXEYE_DAISY        => FlowerPotVariant.OXEYE_DAISY
    case Material.POTTED_PINK_TULIP         => FlowerPotVariant.PINK_TULIP
    case Material.POTTED_POPPY              => FlowerPotVariant.POPPY
    case Material.POTTED_RED_TULIP          => FlowerPotVariant.RED_TULIP
    case Material.POTTED_WHITE_TULIP        => FlowerPotVariant.WHITE_TULIP
    case Material.POTTED_WITHER_ROSE        => FlowerPotVariant.WITHER_ROSE
    case Material.POTTED_LILY_OF_THE_VALLEY => FlowerPotVariant.LILY_OF_THE_VALLEY

    case Material.POTTED_BROWN_MUSHROOM => FlowerPotVariant.BROWN_MUSHROOM
    case Material.POTTED_RED_MUSHROOM   => FlowerPotVariant.RED_MUSHROOM

    case Material.POTTED_ACACIA_SAPLING   => FlowerPotVariant.ACACIA
    case Material.POTTED_BIRCH_SAPLING    => FlowerPotVariant.BIRCH
    case Material.POTTED_DARK_OAK_SAPLING => FlowerPotVariant.DARK_OAK
    case Material.POTTED_JUNGLE_SAPLING   => FlowerPotVariant.JUNGLE
    case Material.POTTED_OAK_SAPLING      => FlowerPotVariant.OAK
    case Material.POTTED_SPRUCE_SAPLING   => FlowerPotVariant.SPRUCE

    case Material.POTTED_BAMBOO    => FlowerPotVariant.BAMBOO
    case Material.POTTED_CACTUS    => FlowerPotVariant.CACTUS
    case Material.POTTED_DEAD_BUSH => FlowerPotVariant.DEAD_BUSH
    case Material.POTTED_FERN      => FlowerPotVariant.FERN

    // ICE
    case Material.ICE        => IceVariant.NORMAL
    case Material.BLUE_ICE   => IceVariant.BLUE
    case Material.PACKED_ICE => IceVariant.PACKED

    // INFESTED_BLOCK
    case Material.INFESTED_STONE       => InfestedBlockVariant.STONE
    case Material.INFESTED_COBBLESTONE => InfestedBlockVariant.COBBLESTONE

    case Material.INFESTED_STONE_BRICKS =>
      InfestedBlockVariant.STONE_BRICK
    case Material.INFESTED_CHISELED_STONE_BRICKS =>
      InfestedBlockVariant.CHISELED_STONE_BRICK
    case Material.INFESTED_CRACKED_STONE_BRICKS =>
      InfestedBlockVariant.CRACKED_STONE_BRICK
    case Material.INFESTED_MOSSY_STONE_BRICKS =>
      InfestedBlockVariant.MOSSY_STONE_BRICK

    // LEAVES
    case Material.ACACIA_LEAVES   => LeavesVariant.ACACIA
    case Material.BIRCH_LEAVES    => LeavesVariant.BIRCH
    case Material.DARK_OAK_LEAVES => LeavesVariant.DARK_OAK
    case Material.JUNGLE_LEAVES   => LeavesVariant.JUNGLE
    case Material.OAK_LEAVES      => LeavesVariant.OAK
    case Material.SPRUCE_LEAVES   => LeavesVariant.SPRUCE

    // LOG
    case Material.ACACIA_LOG   => LogVariant.ACACIA
    case Material.BIRCH_LOG    => LogVariant.BIRCH
    case Material.DARK_OAK_LOG => LogVariant.DARK_OAK
    case Material.JUNGLE_LOG   => LogVariant.JUNGLE
    case Material.OAK_LOG      => LogVariant.OAK
    case Material.SPRUCE_LOG   => LogVariant.SPRUCE

    case Material.STRIPPED_ACACIA_LOG   => LogVariant.STRIPPED_ACACIA
    case Material.STRIPPED_BIRCH_LOG    => LogVariant.STRIPPED_BIRCH
    case Material.STRIPPED_DARK_OAK_LOG => LogVariant.STRIPPED_DARK_OAK
    case Material.STRIPPED_JUNGLE_LOG   => LogVariant.STRIPPED_JUNGLE
    case Material.STRIPPED_OAK_LOG      => LogVariant.STRIPPED_OAK
    case Material.STRIPPED_SPRUCE_LOG   => LogVariant.STRIPPED_SPRUCE

    // MOB_HEAD
    case Material.CREEPER_HEAD          => MobHeadVariant.CREEPER
    case Material.DRAGON_HEAD           => MobHeadVariant.DRAGON
    case Material.PLAYER_HEAD           => MobHeadVariant.PLAYER
    case Material.SKELETON_SKULL        => MobHeadVariant.SKELETON
    case Material.WITHER_SKELETON_SKULL => MobHeadVariant.WITHER_SKELETON
    case Material.ZOMBIE_HEAD           => MobHeadVariant.ZOMBIE

    case Material.CREEPER_WALL_HEAD          => MobHeadVariant.CREEPER_WALL
    case Material.DRAGON_WALL_HEAD           => MobHeadVariant.DRAGON_WALL
    case Material.PLAYER_WALL_HEAD           => MobHeadVariant.PLAYER_WALL
    case Material.SKELETON_WALL_SKULL        => MobHeadVariant.SKELETON_WALL
    case Material.WITHER_SKELETON_WALL_SKULL => MobHeadVariant.WITHER_SKELETON_WALL
    case Material.ZOMBIE_WALL_HEAD           => MobHeadVariant.ZOMBIE_WALL

    // MUSHROOM
    case Material.BROWN_MUSHROOM => MushroomVariant.BROWN
    case Material.RED_MUSHROOM   => MushroomVariant.RED

    // MUSHROOM_BLOCK
    case Material.BROWN_MUSHROOM_BLOCK => MushroomBlockVariant.BROWN
    case Material.RED_MUSHROOM_BLOCK   => MushroomBlockVariant.RED
    case Material.MUSHROOM_STEM        => MushroomBlockVariant.STEM

    // PILLAR
    case Material.PURPUR_PILLAR => PillarVariant.PURPUR
    case Material.QUARTZ_PILLAR => PillarVariant.QUARTZ

    // PLANKS
    case Material.ACACIA_PLANKS   => PlanksVariant.ACACIA
    case Material.BIRCH_PLANKS    => PlanksVariant.BIRCH
    case Material.DARK_OAK_PLANKS => PlanksVariant.DARK_OAK
    case Material.JUNGLE_PLANKS   => PlanksVariant.JUNGLE
    case Material.OAK_PLANKS      => PlanksVariant.OAK
    case Material.SPRUCE_PLANKS   => PlanksVariant.SPRUCE

    // PLANT
    case Material.SUNFLOWER => PlantVariant.SUNFLOWER
    case Material.LILAC     => PlantVariant.LILAC
    case Material.PEONY     => PlantVariant.PEONY
    case Material.ROSE_BUSH => PlantVariant.ROSE_BUSH

    // PRESSURE_PLATE
    case Material.ACACIA_PRESSURE_PLATE   => PressurePlateVariant.ACACIA
    case Material.BIRCH_PRESSURE_PLATE    => PressurePlateVariant.BIRCH
    case Material.DARK_OAK_PRESSURE_PLATE => PressurePlateVariant.DARK_OAK
    case Material.JUNGLE_PRESSURE_PLATE   => PressurePlateVariant.JUNGLE
    case Material.OAK_PRESSURE_PLATE      => PressurePlateVariant.OAK
    case Material.SPRUCE_PRESSURE_PLATE   => PressurePlateVariant.SPRUCE

    case Material.STONE_PRESSURE_PLATE => PressurePlateVariant.STONE

    // PRISMARINE
    case Material.PRISMARINE        => PrismarineVariant.NORMAL
    case Material.DARK_PRISMARINE   => PrismarineVariant.DARK
    case Material.PRISMARINE_BRICKS => PrismarineVariant.BRICK

    // QUARTZ
    case Material.QUARTZ_BLOCK          => QuartzBlockVariant.NORMAL
    case Material.CHISELED_QUARTZ_BLOCK => QuartzBlockVariant.CHISELED
    case Material.SMOOTH_QUARTZ         => QuartzBlockVariant.SMOOTH

    // RAIL
    case Material.RAIL           => RailVariant.NORMAL
    case Material.ACTIVATOR_RAIL => RailVariant.ACTIVATOR
    case Material.DETECTOR_RAIL  => RailVariant.DETECTOR
    case Material.POWERED_RAIL   => RailVariant.POWERED

    // SANDSTONE
    case Material.SANDSTONE          => SandstoneVariant.NORMAL
    case Material.CHISELED_SANDSTONE => SandstoneVariant.CHISELED
    case Material.CUT_SANDSTONE      => SandstoneVariant.CUT
    case Material.SMOOTH_SANDSTONE   => SandstoneVariant.SMOOTH

    case Material.RED_SANDSTONE          => SandstoneVariant.RED
    case Material.CHISELED_RED_SANDSTONE => SandstoneVariant.CHISELED_RED
    case Material.CUT_RED_SANDSTONE      => SandstoneVariant.CUT_RED
    case Material.SMOOTH_RED_SANDSTONE   => SandstoneVariant.SMOOTH_RED

    // SAND
    case Material.SAND     => SandVariant.NORMAL
    case Material.RED_SAND => SandVariant.RED

    // SAPLING
    case Material.ACACIA_SAPLING   => SaplingVariant.ACACIA
    case Material.BIRCH_SAPLING    => SaplingVariant.BIRCH
    case Material.DARK_OAK_SAPLING => SaplingVariant.DARK_OAK
    case Material.JUNGLE_SAPLING   => SaplingVariant.JUNGLE
    case Material.OAK_SAPLING      => SaplingVariant.OAK
    case Material.SPRUCE_SAPLING   => SaplingVariant.SPRUCE

    case Material.BAMBOO_SAPLING => SaplingVariant.BAMBOO

    // SIGN
    case Material.ACACIA_SIGN   => SignVariant.ACACIA
    case Material.BIRCH_SIGN    => SignVariant.BIRCH
    case Material.DARK_OAK_SIGN => SignVariant.DARK_OAK
    case Material.JUNGLE_SIGN   => SignVariant.JUNGLE
    case Material.OAK_SIGN      => SignVariant.OAK
    case Material.SPRUCE_SIGN   => SignVariant.SPRUCE

    case Material.ACACIA_WALL_SIGN   => SignVariant.ACACIA_WALL
    case Material.BIRCH_WALL_SIGN    => SignVariant.BIRCH_WALL
    case Material.DARK_OAK_WALL_SIGN => SignVariant.DARK_OAK_WALL
    case Material.JUNGLE_WALL_SIGN   => SignVariant.JUNGLE_WALL
    case Material.OAK_WALL_SIGN      => SignVariant.OAK_WALL
    case Material.SPRUCE_WALL_SIGN   => SignVariant.SPRUCE_WALL

    // SLAB
    case Material.BRICK_SLAB            => SlabVariant.BRICK
    case Material.NETHER_BRICK_SLAB     => SlabVariant.NETHER_BRICK
    case Material.RED_NETHER_BRICK_SLAB => SlabVariant.RED_NETHER_BRICK

    case Material.COBBLESTONE_SLAB       => SlabVariant.COBBLESTONE
    case Material.MOSSY_COBBLESTONE_SLAB => SlabVariant.MOSSY_COBBLESTONE

    case Material.END_STONE_BRICK_SLAB => SlabVariant.END_STONE_BRICK

    case Material.PETRIFIED_OAK_SLAB => SlabVariant.PETRIFIED_OAK

    case Material.PRISMARINE_SLAB       => SlabVariant.PRISMARINE
    case Material.PRISMARINE_BRICK_SLAB => SlabVariant.PRISMARINE_BRICK
    case Material.DARK_PRISMARINE_SLAB  => SlabVariant.DARK_PRISMARINE

    case Material.PURPUR_SLAB => SlabVariant.PURPUR

    case Material.QUARTZ_SLAB        => SlabVariant.QUARTZ
    case Material.SMOOTH_QUARTZ_SLAB => SlabVariant.SMOOTH_QUARTZ

    case Material.SANDSTONE_SLAB        => SlabVariant.SANDSTONE
    case Material.CUT_SANDSTONE_SLAB    => SlabVariant.CUT_SANDSTONE
    case Material.SMOOTH_SANDSTONE_SLAB => SlabVariant.SMOOTH_SANDSTONE

    case Material.RED_SANDSTONE_SLAB        => SlabVariant.RED_SANDSTONE
    case Material.CUT_RED_SANDSTONE_SLAB    => SlabVariant.CUT_RED_SANDSTONE
    case Material.SMOOTH_RED_SANDSTONE_SLAB => SlabVariant.SMOOTH_RED_SANDSTONE

    case Material.STONE_SLAB        => SlabVariant.STONE
    case Material.SMOOTH_STONE_SLAB => SlabVariant.SMOOTH_STONE

    case Material.STONE_BRICK_SLAB       => SlabVariant.STONE_BRICK
    case Material.MOSSY_STONE_BRICK_SLAB => SlabVariant.MOSSY_STONE_BRICK

    case Material.ANDESITE_SLAB          => SlabVariant.ANDESITE
    case Material.POLISHED_ANDESITE_SLAB => SlabVariant.POLISHED_ANDESITE

    case Material.DIORITE_SLAB          => SlabVariant.DIORITE
    case Material.POLISHED_DIORITE_SLAB => SlabVariant.POLISHED_DIORITE

    case Material.GRANITE_SLAB          => SlabVariant.GRANITE
    case Material.POLISHED_GRANITE_SLAB => SlabVariant.POLISHED_GRANITE

    case Material.ACACIA_SLAB   => SlabVariant.ACACIA
    case Material.BIRCH_SLAB    => SlabVariant.BIRCH
    case Material.DARK_OAK_SLAB => SlabVariant.DARK_OAK
    case Material.JUNGLE_SLAB   => SlabVariant.JUNGLE
    case Material.OAK_SLAB      => SlabVariant.OAK
    case Material.SPRUCE_SLAB   => SlabVariant.SPRUCE

    // STAIRS
    case Material.BRICK_STAIRS            => StairsVariant.BRICK
    case Material.NETHER_BRICK_STAIRS     => StairsVariant.NETHER_BRICK
    case Material.RED_NETHER_BRICK_STAIRS => StairsVariant.RED_NETHER_BRICK

    case Material.COBBLESTONE_STAIRS       => StairsVariant.COBBLESTONE
    case Material.MOSSY_COBBLESTONE_STAIRS => StairsVariant.MOSSY_COBBLESTONE

    case Material.END_STONE_BRICK_STAIRS => StairsVariant.END_STONE_BRICK

    case Material.PRISMARINE_STAIRS       => StairsVariant.PRISMARINE
    case Material.PRISMARINE_BRICK_STAIRS => StairsVariant.PRISMARINE_BRICK
    case Material.DARK_PRISMARINE_STAIRS  => StairsVariant.DARK_PRISMARINE

    case Material.PURPUR_STAIRS => StairsVariant.PURPUR

    case Material.QUARTZ_STAIRS        => StairsVariant.QUARTZ
    case Material.SMOOTH_QUARTZ_STAIRS => StairsVariant.SMOOTH_QUARTZ

    case Material.SANDSTONE_STAIRS        => StairsVariant.SANDSTONE
    case Material.SMOOTH_SANDSTONE_STAIRS => StairsVariant.SMOOTH_SANDSTONE

    case Material.RED_SANDSTONE_STAIRS        => StairsVariant.RED_SANDSTONE
    case Material.SMOOTH_RED_SANDSTONE_STAIRS => StairsVariant.SMOOTH_RED_SANDSTONE

    case Material.STONE_STAIRS             => StairsVariant.STONE
    case Material.STONE_BRICK_STAIRS       => StairsVariant.STONE_BRICK
    case Material.MOSSY_STONE_BRICK_STAIRS => StairsVariant.MOSSY_STONE_BRICK

    case Material.ANDESITE_STAIRS          => StairsVariant.ANDESITE
    case Material.POLISHED_ANDESITE_STAIRS => StairsVariant.POLISHED_ANDESITE

    case Material.DIORITE_STAIRS          => StairsVariant.DIORITE
    case Material.POLISHED_DIORITE_STAIRS => StairsVariant.POLISHED_DIORITE

    case Material.GRANITE_STAIRS          => StairsVariant.GRANITE
    case Material.POLISHED_GRANITE_STAIRS => StairsVariant.POLISHED_GRANITE

    case Material.ACACIA_STAIRS   => StairsVariant.ACACIA
    case Material.BIRCH_STAIRS    => StairsVariant.BIRCH
    case Material.DARK_OAK_STAIRS => StairsVariant.DARK_OAK
    case Material.JUNGLE_STAIRS   => StairsVariant.JUNGLE
    case Material.OAK_STAIRS      => StairsVariant.OAK
    case Material.SPRUCE_STAIRS   => StairsVariant.SPRUCE

    // STONE TODO

    // STONITE TODO

    // STRUCTURE_BLOCK
    case Material.STRUCTURE_VOID => StructureBlockVariant.VOID

    // TRAPDOOR
    case Material.ACACIA_TRAPDOOR   => TrapdoorVariant.ACACIA
    case Material.BIRCH_TRAPDOOR    => TrapdoorVariant.BIRCH
    case Material.DARK_OAK_TRAPDOOR => TrapdoorVariant.DARK_OAK
    case Material.JUNGLE_TRAPDOOR   => TrapdoorVariant.JUNGLE
    case Material.OAK_TRAPDOOR      => TrapdoorVariant.OAK
    case Material.SPRUCE_TRAPDOOR   => TrapdoorVariant.SPRUCE

    case Material.IRON_TRAPDOOR => TrapdoorVariant.IRON

    // WALL
    case Material.BRICK_WALL            => WallVariant.BRICK
    case Material.NETHER_BRICK_WALL     => WallVariant.NETHER_BRICK
    case Material.RED_NETHER_BRICK_WALL => WallVariant.RED_NETHER_BRICK

    case Material.COBBLESTONE_WALL       => WallVariant.COBBLESTONE
    case Material.MOSSY_COBBLESTONE_WALL => WallVariant.MOSSY_COBBLESTONE

    case Material.END_STONE_BRICK_WALL => WallVariant.END_STONE_BRICK

    case Material.PRISMARINE_WALL => WallVariant.PRISMARINE

    case Material.SANDSTONE_WALL     => WallVariant.SANDSTONE
    case Material.RED_SANDSTONE_WALL => WallVariant.RED_SANDSTONE

    case Material.STONE_BRICK_WALL       => WallVariant.STONE_BRICK
    case Material.MOSSY_STONE_BRICK_WALL => WallVariant.MOSSY_STONE_BRICK

    case Material.ANDESITE_WALL => WallVariant.ANDESITE
    case Material.DIORITE_WALL  => WallVariant.DIORITE
    case Material.GRANITE_WALL  => WallVariant.GRANITE

    // WEIGHTED_PRESSURE_PLATE
    case Material.LIGHT_WEIGHTED_PRESSURE_PLATE => WeightedPressurePlateVariant.LIGHT
    case Material.HEAVY_WEIGHTED_PRESSURE_PLATE => WeightedPressurePlateVariant.HEAVY

    // WOOD
    case Material.ACACIA_WOOD   => WoodVariant.ACACIA
    case Material.BIRCH_WOOD    => WoodVariant.BIRCH
    case Material.DARK_OAK_WOOD => WoodVariant.DARK_OAK
    case Material.JUNGLE_WOOD   => WoodVariant.JUNGLE
    case Material.OAK_WOOD      => WoodVariant.OAK
    case Material.SPRUCE_WOOD   => WoodVariant.SPRUCE
  }

  def map(block: SpigotBlock): BlockVariant = {
    lazy val data: SpigotBlockData = block.getState.getBlockData

    block.getType match {
      case Material.BAMBOO =>
        val leaves = data.asInstanceOf[SpigotBamboo].getLeaves
        mapBambooLeaves(leaves)

      case Material.COMPARATOR =>
        val mode = data.asInstanceOf[SpigotComparator].getMode
        mapComparatorMode(mode)

      case Material.NOTE_BLOCK =>
        val instrument = data.asInstanceOf[SpigotNoteBlock].getInstrument
        mapNoteBlock(instrument)

      case Material.STRUCTURE_BLOCK =>
        val mode = data.asInstanceOf[SpigotStructureBlock].getMode
        mapStructureMode(mode)

      case it => map(it)
    }
  }

  def map(variant: BlockVariant): Material = {
    // AIR
    case AirVariant.NORMAL => Material.AIR
    case AirVariant.CAVE   => Material.CAVE_AIR
    case AirVariant.VOID   => Material.VOID_AIR

    // ANVIL
    case AnvilVariant.NORMAL  => Material.ANVIL
    case AnvilVariant.CHIPPED => Material.CHIPPED_ANVIL
    case AnvilVariant.DAMAGED => Material.DAMAGED_ANVIL

    // CHEST
    case ChestVariant.NORMAL  => Material.CHEST
    case ChestVariant.ENDER   => Material.ENDER_CHEST
    case ChestVariant.TRAPPED => Material.TRAPPED_CHEST

    // COBBLESTONE
    case CobblestoneVariant.NORMAL => Material.COBBLESTONE
    case CobblestoneVariant.MOSSY  => Material.MOSSY_COBBLESTONE

    // COMMAND_BLOCK
    case CommandBlockVariant.NORMAL    => Material.COMMAND_BLOCK
    case CommandBlockVariant.CHAIN     => Material.CHAIN_COMMAND_BLOCK
    case CommandBlockVariant.REPEATING => Material.REPEATING_COMMAND_BLOCK

    // CORAL
    case CoralVariant.BRAIN  => Material.BRAIN_CORAL
    case CoralVariant.BUBBLE => Material.BUBBLE_CORAL
    case CoralVariant.FIRE   => Material.FIRE_CORAL
    case CoralVariant.HORN   => Material.HORN_CORAL
    case CoralVariant.TUBE   => Material.TUBE_CORAL

    case CoralVariant.DEAD_BRAIN  => Material.DEAD_BRAIN_CORAL
    case CoralVariant.DEAD_BUBBLE => Material.DEAD_BUBBLE_CORAL
    case CoralVariant.DEAD_FIRE   => Material.DEAD_FIRE_CORAL
    case CoralVariant.DEAD_HORN   => Material.DEAD_HORN_CORAL
    case CoralVariant.DEAD_TUBE   => Material.DEAD_TUBE_CORAL

    // CORAL_BLOCK
    case CoralBlockVariant.BRAIN  => Material.BRAIN_CORAL_BLOCK
    case CoralBlockVariant.BUBBLE => Material.BUBBLE_CORAL_BLOCK
    case CoralBlockVariant.FIRE   => Material.FIRE_CORAL_BLOCK
    case CoralBlockVariant.HORN   => Material.HORN_CORAL_BLOCK
    case CoralBlockVariant.TUBE   => Material.TUBE_CORAL_BLOCK

    case CoralBlockVariant.DEAD_BRAIN  => Material.DEAD_BRAIN_CORAL_BLOCK
    case CoralBlockVariant.DEAD_BUBBLE => Material.DEAD_BUBBLE_CORAL_BLOCK
    case CoralBlockVariant.DEAD_FIRE   => Material.DEAD_FIRE_CORAL_BLOCK
    case CoralBlockVariant.DEAD_HORN   => Material.DEAD_HORN_CORAL_BLOCK
    case CoralBlockVariant.DEAD_TUBE   => Material.DEAD_TUBE_CORAL_BLOCK

    // CORAL_FAN
    case CoralFanVariant.BRAIN  => Material.BRAIN_CORAL_FAN
    case CoralFanVariant.BUBBLE => Material.BUBBLE_CORAL_FAN
    case CoralFanVariant.FIRE   => Material.FIRE_CORAL_FAN
    case CoralFanVariant.HORN   => Material.HORN_CORAL_FAN
    case CoralFanVariant.TUBE   => Material.TUBE_CORAL_FAN

    case CoralFanVariant.DEAD_BRAIN  => Material.DEAD_BRAIN_CORAL_FAN
    case CoralFanVariant.DEAD_BUBBLE => Material.DEAD_BUBBLE_CORAL_FAN
    case CoralFanVariant.DEAD_FIRE   => Material.DEAD_FIRE_CORAL_FAN
    case CoralFanVariant.DEAD_HORN   => Material.DEAD_HORN_CORAL_FAN
    case CoralFanVariant.DEAD_TUBE   => Material.DEAD_TUBE_CORAL_FAN

    case CoralFanVariant.BRAIN_WALL  => Material.BRAIN_CORAL_WALL_FAN
    case CoralFanVariant.BUBBLE_WALL => Material.BUBBLE_CORAL_WALL_FAN
    case CoralFanVariant.FIRE_WALL   => Material.FIRE_CORAL_WALL_FAN
    case CoralFanVariant.HORN_WALL   => Material.HORN_CORAL_WALL_FAN
    case CoralFanVariant.TUBE_WALL   => Material.TUBE_CORAL_WALL_FAN

    case CoralFanVariant.DEAD_BRAIN_WALL  => Material.DEAD_BRAIN_CORAL_WALL_FAN
    case CoralFanVariant.DEAD_BUBBLE_WALL => Material.DEAD_BUBBLE_CORAL_WALL_FAN
    case CoralFanVariant.DEAD_FIRE_WALL   => Material.DEAD_FIRE_CORAL_WALL_FAN
    case CoralFanVariant.DEAD_HORN_WALL   => Material.DEAD_HORN_CORAL_WALL_FAN
    case CoralFanVariant.DEAD_TUBE_WALL   => Material.DEAD_TUBE_CORAL_WALL_FAN

    // FLOWER
    case FlowerVariant.ALLIUM             => Material.ALLIUM
    case FlowerVariant.AZURE_BLUET        => Material.AZURE_BLUET
    case FlowerVariant.BLUE_ORCHID        => Material.BLUE_ORCHID
    case FlowerVariant.CORNFLOWER         => Material.CORNFLOWER
    case FlowerVariant.DANDELION          => Material.DANDELION
    case FlowerVariant.LILY_OF_THE_VALLEY => Material.LILY_OF_THE_VALLEY
    case FlowerVariant.ORANGE_TULIP       => Material.ORANGE_TULIP
    case FlowerVariant.OXEYE_DAISY        => Material.OXEYE_DAISY
    case FlowerVariant.PINK_TULIP         => Material.PINK_TULIP
    case FlowerVariant.POPPY              => Material.POPPY
    case FlowerVariant.RED_TULIP          => Material.RED_TULIP
    case FlowerVariant.WHITE_TULIP        => Material.WHITE_TULIP
    case FlowerVariant.WITHER_ROSE        => Material.WITHER_ROSE

    // FLOWER_POT
    case FlowerPotVariant.EMPTY => Material.FLOWER_POT

    case FlowerPotVariant.ALLIUM             => Material.POTTED_ALLIUM
    case FlowerPotVariant.AZURE_BLUET        => Material.POTTED_AZURE_BLUET
    case FlowerPotVariant.BLUE_ORCHID        => Material.POTTED_BLUE_ORCHID
    case FlowerPotVariant.CORNFLOWER         => Material.POTTED_CORNFLOWER
    case FlowerPotVariant.DANDELION          => Material.POTTED_DANDELION
    case FlowerPotVariant.ORANGE_TULIP       => Material.POTTED_ORANGE_TULIP
    case FlowerPotVariant.OXEYE_DAISY        => Material.POTTED_OXEYE_DAISY
    case FlowerPotVariant.PINK_TULIP         => Material.POTTED_PINK_TULIP
    case FlowerPotVariant.POPPY              => Material.POTTED_POPPY
    case FlowerPotVariant.RED_TULIP          => Material.POTTED_RED_TULIP
    case FlowerPotVariant.WHITE_TULIP        => Material.POTTED_WHITE_TULIP
    case FlowerPotVariant.WITHER_ROSE        => Material.POTTED_WITHER_ROSE
    case FlowerPotVariant.LILY_OF_THE_VALLEY => Material.POTTED_LILY_OF_THE_VALLEY

    case FlowerPotVariant.BROWN_MUSHROOM => Material.POTTED_BROWN_MUSHROOM
    case FlowerPotVariant.RED_MUSHROOM   => Material.POTTED_RED_MUSHROOM

    case SaplingVariant.ACACIA   => Material.POTTED_ACACIA_SAPLING
    case SaplingVariant.BIRCH    => Material.POTTED_BIRCH_SAPLING
    case SaplingVariant.DARK_OAK => Material.POTTED_DARK_OAK_SAPLING
    case SaplingVariant.JUNGLE   => Material.POTTED_JUNGLE_SAPLING
    case SaplingVariant.OAK      => Material.POTTED_OAK_SAPLING
    case SaplingVariant.SPRUCE   => Material.POTTED_SPRUCE_SAPLING

    case SaplingVariant.BAMBOO      => Material.POTTED_BAMBOO
    case FlowerPotVariant.CACTUS    => Material.POTTED_CACTUS
    case FlowerPotVariant.DEAD_BUSH => Material.POTTED_DEAD_BUSH
    case FlowerPotVariant.FERN      => Material.POTTED_FERN

    // INFESTED_BLOCK
    case InfestedBlockVariant.STONE       => Material.INFESTED_STONE
    case InfestedBlockVariant.COBBLESTONE => Material.INFESTED_COBBLESTONE

    case InfestedBlockVariant.STONE_BRICK =>
      Material.INFESTED_STONE_BRICKS
    case InfestedBlockVariant.CHISELED_STONE_BRICK =>
      Material.INFESTED_CHISELED_STONE_BRICKS
    case InfestedBlockVariant.CRACKED_STONE_BRICK =>
      Material.INFESTED_CRACKED_STONE_BRICKS
    case InfestedBlockVariant.MOSSY_STONE_BRICK =>
      Material.INFESTED_MOSSY_STONE_BRICKS

    // ICE
    case IceVariant.NORMAL => Material.ICE
    case IceVariant.PACKED => Material.PACKED_ICE
    case IceVariant.BLUE   => Material.BLUE_ICE

    // LEAVES
    case LeavesVariant.ACACIA   => Material.ACACIA_LEAVES
    case LeavesVariant.BIRCH    => Material.BIRCH_LEAVES
    case LeavesVariant.DARK_OAK => Material.DARK_OAK_LEAVES
    case LeavesVariant.JUNGLE   => Material.JUNGLE_LEAVES
    case LeavesVariant.OAK      => Material.OAK_LEAVES
    case LeavesVariant.SPRUCE   => Material.SPRUCE_LEAVES

    // MOB_HEAD
    case MobHeadVariant.CREEPER         => Material.CREEPER_HEAD
    case MobHeadVariant.DRAGON          => Material.DRAGON_HEAD
    case MobHeadVariant.PLAYER          => Material.PLAYER_HEAD
    case MobHeadVariant.SKELETON        => Material.SKELETON_SKULL
    case MobHeadVariant.WITHER_SKELETON => Material.WITHER_SKELETON_SKULL
    case MobHeadVariant.ZOMBIE          => Material.ZOMBIE_HEAD

    case MobHeadVariant.CREEPER_WALL         => Material.CREEPER_WALL_HEAD
    case MobHeadVariant.DRAGON_WALL          => Material.DRAGON_WALL_HEAD
    case MobHeadVariant.PLAYER_WALL          => Material.PLAYER_WALL_HEAD
    case MobHeadVariant.SKELETON_WALL        => Material.SKELETON_WALL_SKULL
    case MobHeadVariant.WITHER_SKELETON_WALL => Material.WITHER_SKELETON_WALL_SKULL
    case MobHeadVariant.ZOMBIE_WALL          => Material.ZOMBIE_WALL_HEAD

    // MUSHROOM
    case MushroomVariant.BROWN => Material.BROWN_MUSHROOM
    case MushroomVariant.RED   => Material.RED_MUSHROOM

    // MUSHROOM_BLOCK
    case MushroomBlockVariant.BROWN => Material.BROWN_MUSHROOM_BLOCK
    case MushroomBlockVariant.RED   => Material.RED_MUSHROOM_BLOCK
    case MushroomBlockVariant.STEM  => Material.MUSHROOM_STEM

    // PLANKS
    case PlanksVariant.ACACIA   => Material.ACACIA_PLANKS
    case PlanksVariant.BIRCH    => Material.BIRCH_PLANKS
    case PlanksVariant.DARK_OAK => Material.DARK_OAK_PLANKS
    case PlanksVariant.JUNGLE   => Material.JUNGLE_PLANKS
    case PlanksVariant.OAK      => Material.OAK_PLANKS
    case PlanksVariant.SPRUCE   => Material.SPRUCE_PLANKS

    // QUARTZ_BLOCK
    case QuartzBlockVariant.NORMAL   => Material.QUARTZ_BLOCK
    case QuartzBlockVariant.CHISELED => Material.CHISELED_QUARTZ_BLOCK
    case QuartzBlockVariant.SMOOTH   => Material.SMOOTH_QUARTZ

    // RAIL
    case RailVariant.NORMAL    => Material.RAIL
    case RailVariant.ACTIVATOR => Material.ACTIVATOR_RAIL
    case RailVariant.DETECTOR  => Material.DETECTOR_RAIL
    case RailVariant.POWERED   => Material.POWERED_RAIL

    // SANDSTONE
    case SandstoneVariant.NORMAL   => Material.SANDSTONE
    case SandstoneVariant.CHISELED => Material.CHISELED_SANDSTONE
    case SandstoneVariant.CUT      => Material.CUT_SANDSTONE
    case SandstoneVariant.SMOOTH   => Material.SMOOTH_SANDSTONE

    case SandstoneVariant.RED          => Material.RED_SANDSTONE
    case SandstoneVariant.CHISELED_RED => Material.CHISELED_RED_SANDSTONE
    case SandstoneVariant.CUT_RED      => Material.CUT_RED_SANDSTONE
    case SandstoneVariant.SMOOTH_RED   => Material.SMOOTH_RED_SANDSTONE

    // SAPLING
    case SaplingVariant.ACACIA   => Material.ACACIA_SAPLING
    case SaplingVariant.BIRCH    => Material.BIRCH_SAPLING
    case SaplingVariant.DARK_OAK => Material.DARK_OAK_SAPLING
    case SaplingVariant.JUNGLE   => Material.JUNGLE_SAPLING
    case SaplingVariant.OAK      => Material.OAK_SAPLING
    case SaplingVariant.SPRUCE   => Material.SPRUCE_SAPLING

    case SaplingVariant.BAMBOO => Material.BAMBOO_SAPLING

    // SIGN
    case SignVariant.ACACIA   => Material.ACACIA_SIGN
    case SignVariant.BIRCH    => Material.BIRCH_SIGN
    case SignVariant.DARK_OAK => Material.DARK_OAK_SIGN
    case SignVariant.JUNGLE   => Material.JUNGLE_SIGN
    case SignVariant.OAK      => Material.OAK_SIGN
    case SignVariant.SPRUCE   => Material.SPRUCE_SIGN

    case SignVariant.ACACIA_WALL   => Material.ACACIA_WALL_SIGN
    case SignVariant.BIRCH_WALL    => Material.BIRCH_WALL_SIGN
    case SignVariant.DARK_OAK_WALL => Material.DARK_OAK_WALL_SIGN
    case SignVariant.JUNGLE_WALL   => Material.JUNGLE_WALL_SIGN
    case SignVariant.OAK_WALL      => Material.OAK_WALL_SIGN
    case SignVariant.SPRUCE_WALL   => Material.SPRUCE_WALL_SIGN

    // SLAB
    case SlabVariant.BRICK            => Material.BRICK_SLAB
    case SlabVariant.NETHER_BRICK     => Material.NETHER_BRICK_SLAB
    case SlabVariant.RED_NETHER_BRICK => Material.RED_NETHER_BRICK_SLAB

    case SlabVariant.COBBLESTONE       => Material.COBBLESTONE_SLAB
    case SlabVariant.MOSSY_COBBLESTONE => Material.MOSSY_COBBLESTONE_SLAB

    case SlabVariant.END_STONE_BRICK => Material.END_STONE_BRICK_SLAB

    case SlabVariant.PETRIFIED_OAK => Material.PETRIFIED_OAK_SLAB

    case SlabVariant.PRISMARINE       => Material.PRISMARINE_SLAB
    case SlabVariant.PRISMARINE_BRICK => Material.PRISMARINE_BRICK_SLAB
    case SlabVariant.DARK_PRISMARINE  => Material.DARK_PRISMARINE_SLAB

    case SlabVariant.PURPUR => Material.PURPUR_SLAB

    case SlabVariant.QUARTZ        => Material.QUARTZ_SLAB
    case SlabVariant.SMOOTH_QUARTZ => Material.SMOOTH_QUARTZ_SLAB

    case SlabVariant.SANDSTONE        => Material.SANDSTONE_SLAB
    case SlabVariant.CUT_SANDSTONE    => Material.CUT_SANDSTONE_SLAB
    case SlabVariant.SMOOTH_SANDSTONE => Material.SMOOTH_SANDSTONE_SLAB

    case SlabVariant.RED_SANDSTONE        => Material.RED_SANDSTONE_SLAB
    case SlabVariant.CUT_RED_SANDSTONE    => Material.CUT_RED_SANDSTONE_SLAB
    case SlabVariant.SMOOTH_RED_SANDSTONE => Material.SMOOTH_RED_SANDSTONE_SLAB

    case SlabVariant.STONE        => Material.STONE_SLAB
    case SlabVariant.SMOOTH_STONE => Material.SMOOTH_STONE_SLAB

    case SlabVariant.STONE_BRICK       => Material.STONE_BRICK_SLAB
    case SlabVariant.MOSSY_STONE_BRICK => Material.MOSSY_STONE_BRICK_SLAB

    case SlabVariant.ANDESITE          => Material.ANDESITE_SLAB
    case SlabVariant.POLISHED_ANDESITE => Material.POLISHED_ANDESITE_SLAB

    case SlabVariant.DIORITE          => Material.DIORITE_SLAB
    case SlabVariant.POLISHED_DIORITE => Material.POLISHED_DIORITE_SLAB

    case SlabVariant.GRANITE          => Material.GRANITE_SLAB
    case SlabVariant.POLISHED_GRANITE => Material.POLISHED_GRANITE_SLAB

    case SlabVariant.ACACIA   => Material.ACACIA_SLAB
    case SlabVariant.BIRCH    => Material.BIRCH_SLAB
    case SlabVariant.DARK_OAK => Material.DARK_OAK_SLAB
    case SlabVariant.JUNGLE   => Material.JUNGLE_SLAB
    case SlabVariant.OAK      => Material.OAK_SLAB
    case SlabVariant.SPRUCE   => Material.SPRUCE_SLAB

    // STAIRS
    case StairsVariant.BRICK            => Material.BRICK_STAIRS
    case StairsVariant.NETHER_BRICK     => Material.NETHER_BRICK_STAIRS
    case StairsVariant.RED_NETHER_BRICK => Material.RED_NETHER_BRICK_STAIRS

    case StairsVariant.COBBLESTONE       => Material.COBBLESTONE_STAIRS
    case StairsVariant.MOSSY_COBBLESTONE => Material.MOSSY_COBBLESTONE_STAIRS

    case StairsVariant.END_STONE_BRICK => Material.END_STONE_BRICK_STAIRS

    case StairsVariant.PRISMARINE       => Material.PRISMARINE_STAIRS
    case StairsVariant.PRISMARINE_BRICK => Material.PRISMARINE_BRICK_STAIRS
    case StairsVariant.DARK_PRISMARINE  => Material.DARK_PRISMARINE_STAIRS

    case StairsVariant.PURPUR => Material.PURPUR_STAIRS

    case StairsVariant.QUARTZ        => Material.QUARTZ_STAIRS
    case StairsVariant.SMOOTH_QUARTZ => Material.SMOOTH_QUARTZ_STAIRS

    case StairsVariant.SANDSTONE        => Material.SANDSTONE_STAIRS
    case StairsVariant.SMOOTH_SANDSTONE => Material.SMOOTH_SANDSTONE_STAIRS

    case StairsVariant.RED_SANDSTONE        => Material.RED_SANDSTONE_STAIRS
    case StairsVariant.SMOOTH_RED_SANDSTONE => Material.SMOOTH_RED_SANDSTONE_STAIRS

    case StairsVariant.STONE             => Material.STONE_STAIRS
    case StairsVariant.STONE_BRICK       => Material.STONE_BRICK_STAIRS
    case StairsVariant.MOSSY_STONE_BRICK => Material.MOSSY_STONE_BRICK_STAIRS

    case StairsVariant.ANDESITE          => Material.ANDESITE_STAIRS
    case StairsVariant.POLISHED_ANDESITE => Material.POLISHED_ANDESITE_STAIRS

    case StairsVariant.DIORITE          => Material.DIORITE_STAIRS
    case StairsVariant.POLISHED_DIORITE => Material.POLISHED_DIORITE_STAIRS

    case StairsVariant.GRANITE          => Material.GRANITE_STAIRS
    case StairsVariant.POLISHED_GRANITE => Material.POLISHED_GRANITE_STAIRS

    case StairsVariant.ACACIA   => Material.ACACIA_STAIRS
    case StairsVariant.BIRCH    => Material.BIRCH_STAIRS
    case StairsVariant.DARK_OAK => Material.DARK_OAK_STAIRS
    case StairsVariant.JUNGLE   => Material.JUNGLE_STAIRS
    case StairsVariant.OAK      => Material.OAK_STAIRS
    case StairsVariant.SPRUCE   => Material.SPRUCE_STAIRS

    // STRUCTURE_BLOCK
    case StructureBlockVariant.VOID => Material.STRUCTURE_VOID
    case _: StructureBlockVariant   => Material.STRUCTURE_BLOCK

    // WALL
    case WallVariant.BRICK            => Material.BRICK_WALL
    case WallVariant.NETHER_BRICK     => Material.NETHER_BRICK_WALL
    case WallVariant.RED_NETHER_BRICK => Material.RED_NETHER_BRICK_WALL

    case WallVariant.COBBLESTONE       => Material.COBBLESTONE_WALL
    case WallVariant.MOSSY_COBBLESTONE => Material.MOSSY_COBBLESTONE_WALL

    case WallVariant.END_STONE_BRICK => Material.END_STONE_BRICK_WALL

    case WallVariant.PRISMARINE => Material.PRISMARINE_WALL

    case WallVariant.SANDSTONE     => Material.SANDSTONE_WALL
    case WallVariant.RED_SANDSTONE => Material.RED_SANDSTONE_WALL

    case WallVariant.STONE_BRICK       => Material.STONE_BRICK_WALL
    case WallVariant.MOSSY_STONE_BRICK => Material.MOSSY_STONE_BRICK_WALL

    case WallVariant.ANDESITE => Material.ANDESITE_WALL
    case WallVariant.DIORITE  => Material.DIORITE_WALL
    case WallVariant.GRANITE  => Material.GRANITE_WALL

    // WEIGHTED_PRESSURE_PLATE
    case WeightedPressurePlateVariant.LIGHT => Material.LIGHT_WEIGHTED_PRESSURE_PLATE
    case WeightedPressurePlateVariant.HEAVY => Material.HEAVY_WEIGHTED_PRESSURE_PLATE
  }

  def map(block: VariableBlock[_ <: BlockVariant]): Material = {
    case _: Bamboo     => Material.BAMBOO
    case _: Comparator => Material.COMPARATOR
    case _: NoteBlock  => Material.NOTE_BLOCK

    // TODO map WALL_SIGN
    // TODO map WALL_MOB_HEAD
    // TODO map CORAL types

    case it: VariableBlock[_] => map(it.variant)
  }

  def map(block: VariableBlock[_], data: SpigotBlockData): Unit = {
    case Bamboo(_, variant, _, _) =>
      val leaves = mapBambooLeaves(variant)
      data.asInstanceOf[SpigotBamboo].setLeaves(leaves)

    case Comparator(_, variant, _, _) =>
      val mode = mapComparatorMode(variant)
      data.asInstanceOf[SpigotComparator].setMode(mode)

    case NoteBlock(_, variant, _, _) =>
      val instrument = mapNoteBlock(variant)
      data.asInstanceOf[SpigotNoteBlock].setInstrument(instrument)

    case StructureBlock(_, variant) =>
      if (variant != StructureBlockVariant.VOID) {
        val mode = mapStructureMode(variant)
        data.asInstanceOf[SpigotStructureBlock].setMode(mode)
      }
  }

  def mapBambooLeaves(leaves: SpigotBambooLeaves): BambooVariant = {
    case SpigotBambooLeaves.NONE  => BambooVariant.NO_LEAVES
    case SpigotBambooLeaves.SMALL => BambooVariant.SMALL_LEAVES
    case SpigotBambooLeaves.LARGE => BambooVariant.LARGE_LEAVES
  }

  def mapBambooLeaves(variant: BambooVariant): SpigotBambooLeaves = {
    case BambooVariant.NO_LEAVES    => SpigotBambooLeaves.NONE
    case BambooVariant.SMALL_LEAVES => SpigotBambooLeaves.SMALL
    case BambooVariant.LARGE_LEAVES => SpigotBambooLeaves.LARGE
  }

  def mapComparatorMode(mode: SpigotComparatorMode): ComparatorVariant = {
    case SpigotComparatorMode.COMPARE  => ComparatorVariant.COMPARE
    case SpigotComparatorMode.SUBTRACT => ComparatorVariant.SUBTRACT
  }

  def mapComparatorMode(variant: ComparatorVariant): SpigotComparatorMode = {
    case ComparatorVariant.COMPARE  => SpigotComparatorMode.COMPARE
    case ComparatorVariant.SUBTRACT => SpigotComparatorMode.SUBTRACT
  }

  def mapNoteBlock(instrument: SpigotInstrument): NoteBlockVariant = {
    case SpigotInstrument.BANJO          => NoteBlockVariant.BANJO
    case SpigotInstrument.BASS_DRUM      => NoteBlockVariant.BASS_DRUM
    case SpigotInstrument.BASS_GUITAR    => NoteBlockVariant.BASS_GUITAR
    case SpigotInstrument.BELL           => NoteBlockVariant.BELL
    case SpigotInstrument.BIT            => NoteBlockVariant.BIT
    case SpigotInstrument.CHIME          => NoteBlockVariant.CHIME
    case SpigotInstrument.COW_BELL       => NoteBlockVariant.COW_BELL
    case SpigotInstrument.DIDGERIDOO     => NoteBlockVariant.DIDGERIDOO
    case SpigotInstrument.FLUTE          => NoteBlockVariant.FLUTE
    case SpigotInstrument.GUITAR         => NoteBlockVariant.GUITAR
    case SpigotInstrument.IRON_XYLOPHONE => NoteBlockVariant.IRON_XYLOPHONE
    case SpigotInstrument.PIANO          => NoteBlockVariant.HAT
    case SpigotInstrument.PLING          => NoteBlockVariant.PLING
    case SpigotInstrument.SNARE_DRUM     => NoteBlockVariant.SNARE_DRUM
    case SpigotInstrument.STICKS         => NoteBlockVariant.HARP
    case SpigotInstrument.XYLOPHONE      => NoteBlockVariant.XYLOPHONE
  }

  def mapNoteBlock(variant: NoteBlockVariant): SpigotInstrument = {
    case NoteBlockVariant.BANJO          => SpigotInstrument.BANJO
    case NoteBlockVariant.BASS_DRUM      => SpigotInstrument.BASS_DRUM
    case NoteBlockVariant.BASS_GUITAR    => SpigotInstrument.BASS_GUITAR
    case NoteBlockVariant.BELL           => SpigotInstrument.BELL
    case NoteBlockVariant.BIT            => SpigotInstrument.BIT
    case NoteBlockVariant.CHIME          => SpigotInstrument.CHIME
    case NoteBlockVariant.COW_BELL       => SpigotInstrument.COW_BELL
    case NoteBlockVariant.DIDGERIDOO     => SpigotInstrument.DIDGERIDOO
    case NoteBlockVariant.FLUTE          => SpigotInstrument.FLUTE
    case NoteBlockVariant.GUITAR         => SpigotInstrument.GUITAR
    case NoteBlockVariant.IRON_XYLOPHONE => SpigotInstrument.IRON_XYLOPHONE
    case NoteBlockVariant.HAT            => SpigotInstrument.PIANO
    case NoteBlockVariant.PLING          => SpigotInstrument.PLING
    case NoteBlockVariant.SNARE_DRUM     => SpigotInstrument.SNARE_DRUM
    case NoteBlockVariant.HARP           => SpigotInstrument.STICKS
    case NoteBlockVariant.XYLOPHONE      => SpigotInstrument.XYLOPHONE
  }

//
//  def mapStairs(mat: StairsMaterial, variant: Option[StairsVariant]): Material =
//    mat match {
//      // BRICK
//      case BrickMaterial.BRICK            => Material.BRICK_STAIRS
//      case BrickMaterial.NETHER_BRICK     => Material.NETHER_BRICK_STAIRS
//      case BrickMaterial.RED_NETHER_BRICK => Material.RED_NETHER_BRICK_STAIRS
//
//      // COBBLESTONE
//      case _: CobblestoneMaterial =>
//        variant match {
//          case Some(CobblestoneVariant.MOSSY) =>
//            Material.MOSSY_COBBLESTONE_STAIRS
//          case _ => Material.COBBLESTONE_STAIRS
//        }
//
//      // END_STONE
//      case _: EndStoneMaterial => Material.END_STONE_BRICK_STAIRS
//
//      // PRISMARINE
//      case PrismarineMaterial.PRISMARINE      => Material.PRISMARINE_STAIRS
//      case PrismarineMaterial.DARK_PRISMARINE => Material.DARK_PRISMARINE_STAIRS
//      case PrismarineMaterial.PRISMARINE_BRICK =>
//        Material.PRISMARINE_BRICK_STAIRS
//
//      // PURPUR
//      case _: PurpurMaterial => Material.PURPUR_STAIRS
//
//      // QUARTZ
//      case QuartzMaterial.QUARTZ =>
//        variant match {
//          case Some(QuartzVariant.SMOOTH) => Material.SMOOTH_QUARTZ_STAIRS
//          case _                          => Material.QUARTZ_STAIRS
//        }
//
//      // SANDSTONE
//      case SandstoneMaterial.SANDSTONE =>
//        variant match {
//          case Some(SandstoneVariant.SMOOTH) => Material.SMOOTH_SANDSTONE_STAIRS
//          case _                             => Material.SANDSTONE_STAIRS
//        }
//
//      case SandstoneMaterial.RED_SANDSTONE =>
//        variant match {
//          case Some(SandstoneVariant.SMOOTH) =>
//            Material.SMOOTH_RED_SANDSTONE_STAIRS
//          case _ => Material.RED_SANDSTONE_STAIRS
//        }
//
//      // STONE
//      case StoneMaterial.STONE => Material.STONE_STAIRS
//      case StoneMaterial.STONE_BRICK =>
//        variant match {
//          case Some(StoneVariant.MOSSY) => Material.MOSSY_STONE_BRICK_STAIRS
//          case _                        => Material.STONE_BRICK_STAIRS
//        }
//
//      // STONITE
//      case StoniteMaterial.ANDESITE => Material.ANDESITE_STAIRS
//      case StoniteMaterial.DIORITE  => Material.DIORITE_STAIRS
//      case StoniteMaterial.GRANITE  => Material.GRANITE_STAIRS
//
//      // WOOD
//      case WoodMaterial.ACACIA   => Material.ACACIA_STAIRS
//      case WoodMaterial.BIRCH    => Material.BIRCH_STAIRS
//      case WoodMaterial.DARK_OAK => Material.DARK_OAK_STAIRS
//      case WoodMaterial.JUNGLE   => Material.JUNGLE_STAIRS
//      case WoodMaterial.OAK      => Material.OAK_STAIRS
//      case WoodMaterial.SPRUCE   => Material.SPRUCE_STAIRS
//    }
//
//  def mapStone(mat: StoneMaterial, variant: StoneVariant): Material = {
//      case StoneMaterial.STONE =>
//        variant match {
//          case StoneVariant.SMOOTH => Material.SMOOTH_STONE
//          case _                   => Material.STONE
//        }
//      case StoneMaterial.STONE_BRICK =>
//        variant match {
//          case StoneVariant.CHISELED => Material.CHISELED_STONE_BRICKS
//          case StoneVariant.CRACKED  => Material.CRACKED_STONE_BRICKS
//          case StoneVariant.MOSSY    => Material.MOSSY_STONE_BRICKS
//          case _                     => Material.STONE_BRICKS
//        }
//    }
//
//  def mapStonite(mat: StoniteMaterial, variant: StoniteVariant): Material = {
//    case StoniteMaterial.ANDESITE =>
//      variant match {
//        case StoniteVariant.NORMAL   => Material.ANDESITE
//        case StoniteVariant.POLISHED => Material.POLISHED_ANDESITE
//      }
//    case StoniteMaterial.DIORITE =>
//      variant match {
//        case StoniteVariant.NORMAL   => Material.DIORITE
//        case StoniteVariant.POLISHED => Material.POLISHED_DIORITE
//      }
//    case StoniteMaterial.GRANITE =>
//      variant match {
//        case StoniteVariant.NORMAL   => Material.GRANITE
//        case StoniteVariant.POLISHED => Material.POLISHED_GRANITE
//      }
//  }

  def mapStructureMode(mode: SpigotStructureBlockMode): StructureBlockVariant = {
    case SpigotStructureBlockMode.CORNER => StructureBlockVariant.CORNER
    case SpigotStructureBlockMode.DATA   => StructureBlockVariant.DATA
    case SpigotStructureBlockMode.LOAD   => StructureBlockVariant.LOAD
    case SpigotStructureBlockMode.SAVE   => StructureBlockVariant.SAVE
  }

  def mapStructureMode(variant: StructureBlockVariant): SpigotStructureBlockMode = {
    case StructureBlockVariant.CORNER => SpigotStructureBlockMode.CORNER
    case StructureBlockVariant.DATA   => SpigotStructureBlockMode.DATA
    case StructureBlockVariant.LOAD   => SpigotStructureBlockMode.LOAD
    case StructureBlockVariant.SAVE   => SpigotStructureBlockMode.SAVE
    case StructureBlockVariant.VOID   => null
  }

//  def mapWall(mat: WallMaterial, variant: Option[WallVariant]): Material =
//    mat match {
//      // BRICK
//      case BrickMaterial.BRICK            => Material.BRICK_WALL
//      case BrickMaterial.NETHER_BRICK     => Material.NETHER_BRICK_WALL
//      case BrickMaterial.RED_NETHER_BRICK => Material.RED_NETHER_BRICK_WALL
//
//      // COBBLESTONE
//      case _: CobblestoneMaterial =>
//        variant match {
//          case Some(CobblestoneVariant.MOSSY) => Material.MOSSY_COBBLESTONE_WALL
//          case _                              => Material.COBBLESTONE_WALL
//        }
//
//      // END_STONE
//      case _: EndStoneMaterial => Material.END_STONE_BRICK_WALL
//
//      // PRISMARINE
//      case _: PrismarineMaterial => Material.PRISMARINE_WALL
//
//      // SANDSTONE
//      case SandstoneMaterial.SANDSTONE     => Material.SANDSTONE_WALL
//      case SandstoneMaterial.RED_SANDSTONE => Material.RED_SANDSTONE_WALL
//
//      // STONE
//      case _: StoneMaterial =>
//        variant match {
//          case Some(StoneVariant.MOSSY) => Material.MOSSY_STONE_BRICK_WALL
//          case _                        => Material.STONE_BRICK_WALL
//        }
//
//      // STONITE
//      case StoniteMaterial.ANDESITE => Material.ANDESITE_WALL
//      case StoniteMaterial.DIORITE  => Material.DIORITE_WALL
//      case StoniteMaterial.GRANITE  => Material.GRANITE_WALL
//    }
}
