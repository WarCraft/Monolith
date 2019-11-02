package gg.warcraft.monolith.spigot

import java.util

import org.bukkit.Material

object Extensions {
  implicit class MaterialExtensions(material: Material) {
    private final val air = util.EnumSet.of(
      Material.AIR,
      Material.CAVE_AIR,
      Material.VOID_AIR
    )

    private final val anvil = util.EnumSet.of(
      Material.ANVIL,
      Material.CHIPPED_ANVIL,
      Material.DAMAGED_ANVIL
    )

    private final val arrow = util.EnumSet.of(
      Material.ARROW,
      Material.SPECTRAL_ARROW,
      Material.TIPPED_ARROW
    )

    private final val axe = util.EnumSet.of(
      Material.WOODEN_AXE,
      Material.STONE_AXE,
      Material.IRON_AXE,
      Material.GOLDEN_AXE,
      Material.DIAMOND_AXE
    )

    private final val banner = util.EnumSet.of(
      Material.BLACK_BANNER,
      Material.BLUE_BANNER,
      Material.BROWN_BANNER,
      Material.CYAN_BANNER,
      Material.GRAY_BANNER,
      Material.GREEN_BANNER,
      Material.LIGHT_BLUE_BANNER,
      Material.LIGHT_GRAY_BANNER,
      Material.LIME_BANNER,
      Material.MAGENTA_BANNER,
      Material.ORANGE_BANNER,
      Material.PINK_BANNER,
      Material.PURPLE_BANNER,
      Material.RED_BANNER,
      Material.WHITE_BANNER,
      Material.YELLOW_BANNER
    )

    private final val wallBanner = util.EnumSet.of(
      Material.BLACK_WALL_BANNER,
      Material.BLUE_WALL_BANNER,
      Material.BROWN_WALL_BANNER,
      Material.CYAN_WALL_BANNER,
      Material.GRAY_WALL_BANNER,
      Material.GREEN_WALL_BANNER,
      Material.LIGHT_BLUE_WALL_BANNER,
      Material.LIGHT_GRAY_WALL_BANNER,
      Material.LIME_WALL_BANNER,
      Material.MAGENTA_WALL_BANNER,
      Material.ORANGE_WALL_BANNER,
      Material.PINK_WALL_BANNER,
      Material.PURPLE_WALL_BANNER,
      Material.RED_WALL_BANNER,
      Material.WHITE_WALL_BANNER,
      Material.YELLOW_WALL_BANNER
    )

    private final val bannerPattern = util.EnumSet.of(
      Material.CREEPER_BANNER_PATTERN,
      Material.FLOWER_BANNER_PATTERN,
      Material.GLOBE_BANNER_PATTERN,
      Material.MOJANG_BANNER_PATTERN,
      Material.SKULL_BANNER_PATTERN
    )

    private final val bed = util.EnumSet.of(
      Material.BLACK_BED,
      Material.BLUE_BED,
      Material.BROWN_BED,
      Material.CYAN_BED,
      Material.GRAY_BED,
      Material.GREEN_BED,
      Material.LIGHT_BLUE_BED,
      Material.LIGHT_GRAY_BED,
      Material.LIME_BED,
      Material.MAGENTA_BED,
      Material.ORANGE_BED,
      Material.PINK_BED,
      Material.PURPLE_BED,
      Material.RED_BED,
      Material.WHITE_BED,
      Material.YELLOW_BED
    )

    private final val beef = util.EnumSet.of(
      Material.BEEF,
      Material.COOKED_BEEF
    )

    private final val boat = util.EnumSet.of(
      Material.ACACIA_BOAT,
      Material.BIRCH_BOAT,
      Material.DARK_OAK_BOAT,
      Material.JUNGLE_BOAT,
      Material.OAK_BOAT,
      Material.SPRUCE_BOAT
    )

    private final val boots = util.EnumSet.of(
      Material.LEATHER_BOOTS,
      Material.CHAINMAIL_BOOTS,
      Material.IRON_BOOTS,
      Material.GOLDEN_BOOTS,
      Material.DIAMOND_BOOTS
    )

    private final val brick = util.EnumSet.of(
      Material.BRICK,
      Material.NETHER_BRICK
    )

    private final val brickBlock = util.EnumSet.of(
      Material.BRICKS,
      Material.NETHER_BRICKS,
      Material.RED_NETHER_BRICKS
    )

    private final val bucket = util.EnumSet.of(
      Material.BUCKET,
      Material.COD_BUCKET,
      Material.PUFFERFISH_BUCKET,
      Material.SALMON_BUCKET,
      Material.TROPICAL_FISH_BUCKET,
      Material.LAVA_BUCKET,
      Material.MILK_BUCKET,
      Material.WATER_BUCKET
    )

    private final val button = util.EnumSet.of(
      Material.STONE_BUTTON,
      Material.ACACIA_BUTTON,
      Material.BIRCH_BUTTON,
      Material.DARK_OAK_BUTTON,
      Material.JUNGLE_BUTTON,
      Material.OAK_BUTTON,
      Material.SPRUCE_BUTTON
    )

    private final val carpet = util.EnumSet.of(
      Material.BLACK_CARPET,
      Material.BLUE_CARPET,
      Material.BROWN_CARPET,
      Material.CYAN_CARPET,
      Material.GRAY_CARPET,
      Material.GREEN_CARPET,
      Material.LIGHT_BLUE_CARPET,
      Material.LIGHT_GRAY_CARPET,
      Material.LIME_CARPET,
      Material.MAGENTA_CARPET,
      Material.ORANGE_CARPET,
      Material.PINK_CARPET,
      Material.PURPLE_CARPET,
      Material.RED_CARPET,
      Material.WHITE_CARPET,
      Material.YELLOW_CARPET
    )

    private final val chicken = util.EnumSet.of(
      Material.CHICKEN,
      Material.COOKED_CHICKEN
    )

    private final val cobblestone = util.EnumSet.of(
      Material.COBBLESTONE,
      Material.MOSSY_COBBLESTONE
    )

    private final val cod = util.EnumSet.of(
      Material.COD,
      Material.COOKED_COD
    )

    private final val chest = util.EnumSet.of(
      Material.CHEST,
      Material.ENDER_CHEST,
      Material.TRAPPED_CHEST
    )

    private final val chestplate = util.EnumSet.of(
      Material.LEATHER_CHESTPLATE,
      Material.CHAINMAIL_CHESTPLATE,
      Material.IRON_CHESTPLATE,
      Material.GOLDEN_CHESTPLATE,
      Material.DIAMOND_CHESTPLATE
    )

    private final val chorusFruit = util.EnumSet.of(
      Material.CHORUS_FRUIT,
      Material.POPPED_CHORUS_FRUIT
    )

    private final val commandBlock = util.EnumSet.of(
      Material.COMMAND_BLOCK,
      Material.CHAIN_COMMAND_BLOCK,
      Material.REPEATING_COMMAND_BLOCK
    )

    private final val concrete = util.EnumSet.of(
      Material.BLACK_CONCRETE,
      Material.BLUE_CONCRETE,
      Material.BROWN_CONCRETE,
      Material.CYAN_CONCRETE,
      Material.GRAY_CONCRETE,
      Material.GREEN_CONCRETE,
      Material.LIGHT_BLUE_CONCRETE,
      Material.LIGHT_GRAY_CONCRETE,
      Material.LIME_CONCRETE,
      Material.MAGENTA_CONCRETE,
      Material.ORANGE_CONCRETE,
      Material.PINK_CONCRETE,
      Material.PURPLE_CONCRETE,
      Material.RED_CONCRETE,
      Material.WHITE_CONCRETE,
      Material.YELLOW_CONCRETE
    )

    private final val concretePowder = util.EnumSet.of(
      Material.BLACK_CONCRETE_POWDER,
      Material.BLUE_CONCRETE_POWDER,
      Material.BROWN_CONCRETE_POWDER,
      Material.CYAN_CONCRETE_POWDER,
      Material.GRAY_CONCRETE_POWDER,
      Material.GREEN_CONCRETE_POWDER,
      Material.LIGHT_BLUE_CONCRETE_POWDER,
      Material.LIGHT_GRAY_CONCRETE_POWDER,
      Material.LIME_CONCRETE_POWDER,
      Material.MAGENTA_CONCRETE_POWDER,
      Material.ORANGE_CONCRETE_POWDER,
      Material.PINK_CONCRETE_POWDER,
      Material.PURPLE_CONCRETE_POWDER,
      Material.RED_CONCRETE_POWDER,
      Material.WHITE_CONCRETE_POWDER,
      Material.YELLOW_CONCRETE_POWDER
    )

    private final val coral = util.EnumSet.of(
      Material.BRAIN_CORAL,
      Material.BUBBLE_CORAL,
      Material.FIRE_CORAL,
      Material.HORN_CORAL,
      Material.TUBE_CORAL,
      Material.DEAD_BRAIN_CORAL,
      Material.DEAD_BUBBLE_CORAL,
      Material.DEAD_FIRE_CORAL,
      Material.DEAD_HORN_CORAL,
      Material.DEAD_TUBE_CORAL
    )

    private final val coralBlock = util.EnumSet.of(
      Material.BRAIN_CORAL_BLOCK,
      Material.DEAD_BRAIN_CORAL_BLOCK,
      Material.BUBBLE_CORAL_BLOCK,
      Material.DEAD_BUBBLE_CORAL_BLOCK,
      Material.FIRE_CORAL_BLOCK,
      Material.DEAD_FIRE_CORAL_BLOCK,
      Material.HORN_CORAL_BLOCK,
      Material.DEAD_HORN_CORAL_BLOCK,
      Material.TUBE_CORAL_BLOCK,
      Material.DEAD_TUBE_CORAL_BLOCK
    )

    private final val coralFan = util.EnumSet.of(
      Material.BRAIN_CORAL_FAN,
      Material.DEAD_BRAIN_CORAL_FAN,
      Material.BUBBLE_CORAL_FAN,
      Material.DEAD_BUBBLE_CORAL_FAN,
      Material.FIRE_CORAL_FAN,
      Material.DEAD_FIRE_CORAL_FAN,
      Material.HORN_CORAL_FAN,
      Material.DEAD_HORN_CORAL_FAN,
      Material.TUBE_CORAL_FAN,
      Material.DEAD_TUBE_CORAL_FAN
    )

    private final val coralWallFan = util.EnumSet.of(
      Material.BRAIN_CORAL_WALL_FAN,
      Material.DEAD_BRAIN_CORAL_WALL_FAN,
      Material.BUBBLE_CORAL_WALL_FAN,
      Material.DEAD_BUBBLE_CORAL_WALL_FAN,
      Material.FIRE_CORAL_WALL_FAN,
      Material.DEAD_FIRE_CORAL_WALL_FAN,
      Material.HORN_CORAL_WALL_FAN,
      Material.DEAD_HORN_CORAL_WALL_FAN,
      Material.TUBE_CORAL_WALL_FAN,
      Material.DEAD_TUBE_CORAL_WALL_FAN
    )

    private final val dirt = util.EnumSet.of(
      Material.DIRT,
      Material.COARSE_DIRT
    )

    private final val door = util.EnumSet.of(
      Material.ACACIA_DOOR,
      Material.BIRCH_DOOR,
      Material.DARK_OAK_DOOR,
      Material.JUNGLE_DOOR,
      Material.OAK_DOOR,
      Material.SPRUCE_DOOR,
      Material.IRON_DOOR
    )

    private final val dye = util.EnumSet.of(
      Material.BLACK_DYE,
      Material.BLUE_DYE,
      Material.BROWN_DYE,
      Material.CYAN_DYE,
      Material.GRAY_DYE,
      Material.GREEN_DYE,
      Material.LIGHT_BLUE_DYE,
      Material.LIGHT_GRAY_DYE,
      Material.LIME_DYE,
      Material.MAGENTA_DYE,
      Material.ORANGE_DYE,
      Material.PINK_DYE,
      Material.PURPLE_DYE,
      Material.RED_DYE,
      Material.WHITE_DYE,
      Material.YELLOW_DYE
    )

    private final val endStone = util.EnumSet.of(
      Material.END_STONE,
      Material.END_STONE_BRICKS
    )

    private final val fence = util.EnumSet.of(
      Material.ACACIA_FENCE,
      Material.BIRCH_FENCE,
      Material.DARK_OAK_FENCE,
      Material.JUNGLE_FENCE,
      Material.OAK_FENCE,
      Material.SPRUCE_FENCE,
      Material.NETHER_BRICK_FENCE
    )

    private final val fenceGate = util.EnumSet.of(
      Material.ACACIA_FENCE_GATE,
      Material.BIRCH_FENCE_GATE,
      Material.DARK_OAK_FENCE_GATE,
      Material.JUNGLE_FENCE_GATE,
      Material.OAK_FENCE_GATE,
      Material.SPRUCE_FENCE_GATE
    )

    private final val flower = util.EnumSet.of(
      Material.ALLIUM,
      Material.AZURE_BLUET,
      Material.BLUE_ORCHID,
      Material.CORNFLOWER,
      Material.DANDELION,
      Material.LILY_OF_THE_VALLEY,
      Material.ORANGE_TULIP,
      Material.OXEYE_DAISY,
      Material.PINK_TULIP,
      Material.POPPY,
      Material.RED_TULIP,
      Material.WHITE_TULIP,
      Material.WITHER_ROSE
    )

    private final val flowerPot = util.EnumSet.of(
      Material.FLOWER_POT,
      Material.POTTED_ALLIUM,
      Material.POTTED_AZURE_BLUET,
      Material.POTTED_BLUE_ORCHID,
      Material.POTTED_CORNFLOWER,
      Material.POTTED_DANDELION,
      Material.POTTED_LILY_OF_THE_VALLEY,
      Material.POTTED_ORANGE_TULIP,
      Material.POTTED_OXEYE_DAISY,
      Material.POTTED_PINK_TULIP,
      Material.POTTED_POPPY,
      Material.POTTED_RED_TULIP,
      Material.POTTED_WHITE_TULIP,
      Material.POTTED_WITHER_ROSE,
      Material.POTTED_BAMBOO,
      Material.POTTED_BROWN_MUSHROOM,
      Material.POTTED_RED_MUSHROOM,
      Material.POTTED_CACTUS,
      Material.POTTED_DEAD_BUSH,
      Material.POTTED_FERN,
      Material.POTTED_ACACIA_SAPLING,
      Material.POTTED_BIRCH_SAPLING,
      Material.POTTED_DARK_OAK_SAPLING,
      Material.POTTED_JUNGLE_SAPLING,
      Material.POTTED_OAK_SAPLING,
      Material.POTTED_SPRUCE_SAPLING
    )

    private final val glass = util.EnumSet.of(
      Material.GLASS,
      Material.BLACK_STAINED_GLASS,
      Material.BLUE_STAINED_GLASS,
      Material.BROWN_STAINED_GLASS,
      Material.CYAN_STAINED_GLASS,
      Material.GRAY_STAINED_GLASS,
      Material.GREEN_STAINED_GLASS,
      Material.LIGHT_BLUE_STAINED_GLASS,
      Material.LIGHT_GRAY_STAINED_GLASS,
      Material.LIME_STAINED_GLASS,
      Material.MAGENTA_STAINED_GLASS,
      Material.ORANGE_STAINED_GLASS,
      Material.PINK_STAINED_GLASS,
      Material.PURPLE_STAINED_GLASS,
      Material.RED_STAINED_GLASS,
      Material.WHITE_STAINED_GLASS,
      Material.YELLOW_STAINED_GLASS
    )

    private final val glassPane = util.EnumSet.of(
      Material.GLASS_PANE,
      Material.BLACK_STAINED_GLASS_PANE,
      Material.BLUE_STAINED_GLASS_PANE,
      Material.BROWN_STAINED_GLASS_PANE,
      Material.CYAN_STAINED_GLASS_PANE,
      Material.GRAY_STAINED_GLASS_PANE,
      Material.GREEN_STAINED_GLASS_PANE,
      Material.LIGHT_BLUE_STAINED_GLASS_PANE,
      Material.LIGHT_GRAY_STAINED_GLASS_PANE,
      Material.LIME_STAINED_GLASS_PANE,
      Material.MAGENTA_STAINED_GLASS_PANE,
      Material.ORANGE_STAINED_GLASS_PANE,
      Material.PINK_STAINED_GLASS_PANE,
      Material.PURPLE_STAINED_GLASS_PANE,
      Material.RED_STAINED_GLASS_PANE,
      Material.WHITE_STAINED_GLASS_PANE,
      Material.YELLOW_STAINED_GLASS_PANE
    )

    private final val glazedTerracotta = util.EnumSet.of(
      Material.BLACK_GLAZED_TERRACOTTA,
      Material.BLUE_GLAZED_TERRACOTTA,
      Material.BROWN_GLAZED_TERRACOTTA,
      Material.CYAN_GLAZED_TERRACOTTA,
      Material.GRAY_GLAZED_TERRACOTTA,
      Material.GREEN_GLAZED_TERRACOTTA,
      Material.LIGHT_BLUE_GLAZED_TERRACOTTA,
      Material.LIGHT_GRAY_GLAZED_TERRACOTTA,
      Material.LIME_GLAZED_TERRACOTTA,
      Material.MAGENTA_GLAZED_TERRACOTTA,
      Material.ORANGE_GLAZED_TERRACOTTA,
      Material.PINK_GLAZED_TERRACOTTA,
      Material.PURPLE_GLAZED_TERRACOTTA,
      Material.RED_GLAZED_TERRACOTTA,
      Material.WHITE_GLAZED_TERRACOTTA,
      Material.YELLOW_GLAZED_TERRACOTTA
    )

    private final val grass = util.EnumSet.of(
      Material.GRASS,
      Material.TALL_GRASS
    )

    private final val helmet = util.EnumSet.of(
      Material.LEATHER_HELMET,
      Material.CHAINMAIL_HELMET,
      Material.IRON_HELMET,
      Material.GOLDEN_HELMET,
      Material.DIAMOND_HELMET
    )

    private final val hoe = util.EnumSet.of(
      Material.WOODEN_HOE,
      Material.STONE_HOE,
      Material.IRON_HOE,
      Material.GOLDEN_HOE,
      Material.DIAMOND_HOE
    )

    private final val horseArmor = util.EnumSet.of(
      Material.LEATHER_HORSE_ARMOR,
      Material.IRON_HORSE_ARMOR,
      Material.GOLDEN_HORSE_ARMOR,
      Material.DIAMOND_HORSE_ARMOR
    )

    private final val ice = util.EnumSet.of(
      Material.ICE,
      Material.BLUE_ICE,
      Material.PACKED_ICE
    )

    private final val infestedBlock = util.EnumSet.of(
      Material.INFESTED_STONE,
      Material.INFESTED_COBBLESTONE,
      Material.INFESTED_STONE_BRICKS,
      Material.INFESTED_CRACKED_STONE_BRICKS,
      Material.INFESTED_CHISELED_STONE_BRICKS,
      Material.INFESTED_MOSSY_STONE_BRICKS
    )

    private final val leaves = util.EnumSet.of(
      Material.ACACIA_LEAVES,
      Material.BIRCH_LEAVES,
      Material.DARK_OAK_LEAVES,
      Material.JUNGLE_LEAVES,
      Material.OAK_LEAVES,
      Material.SPRUCE_LEAVES
    )

    private final val leggings = util.EnumSet.of(
      Material.LEATHER_LEGGINGS,
      Material.CHAINMAIL_LEGGINGS,
      Material.IRON_LEGGINGS,
      Material.GOLDEN_LEGGINGS,
      Material.DIAMOND_LEGGINGS
    )

    private final val log = util.EnumSet.of(
      Material.ACACIA_LOG,
      Material.BIRCH_LOG,
      Material.DARK_OAK_LOG,
      Material.JUNGLE_LOG,
      Material.OAK_LOG,
      Material.SPRUCE_LOG,
      Material.STRIPPED_ACACIA_LOG,
      Material.STRIPPED_BIRCH_LOG,
      Material.STRIPPED_DARK_OAK_LOG,
      Material.STRIPPED_JUNGLE_LOG,
      Material.STRIPPED_OAK_LOG,
      Material.STRIPPED_SPRUCE_LOG
    )

    private final val minecart = util.EnumSet.of(
      Material.MINECART,
      Material.CHEST_MINECART,
      Material.COMMAND_BLOCK_MINECART,
      Material.FURNACE_MINECART,
      Material.HOPPER_MINECART,
      Material.TNT_MINECART
    )

    private final val mobHead = util.EnumSet.of(
      Material.CREEPER_HEAD,
      Material.DRAGON_HEAD,
      Material.PLAYER_HEAD,
      Material.SKELETON_SKULL,
      Material.WITHER_SKELETON_SKULL,
      Material.ZOMBIE_HEAD
    )

    private final val wallMobHead = util.EnumSet.of(
      Material.CREEPER_WALL_HEAD,
      Material.DRAGON_WALL_HEAD,
      Material.PLAYER_WALL_HEAD,
      Material.SKELETON_WALL_SKULL,
      Material.WITHER_SKELETON_WALL_SKULL,
      Material.ZOMBIE_WALL_HEAD
    )

    private final val mushroom = util.EnumSet.of(
      Material.BROWN_MUSHROOM,
      Material.RED_MUSHROOM
    )

    private final val mushroomBlock = util.EnumSet.of(
      Material.BROWN_MUSHROOM_BLOCK,
      Material.RED_MUSHROOM_BLOCK,
      Material.MUSHROOM_STEM
    )

    private final val musicDisc = util.EnumSet.of(
      Material.MUSIC_DISC_11,
      Material.MUSIC_DISC_13,
      Material.MUSIC_DISC_BLOCKS,
      Material.MUSIC_DISC_CAT,
      Material.MUSIC_DISC_CHIRP,
      Material.MUSIC_DISC_FAR,
      Material.MUSIC_DISC_MALL,
      Material.MUSIC_DISC_MELLOHI,
      Material.MUSIC_DISC_STAL,
      Material.MUSIC_DISC_STRAD,
      Material.MUSIC_DISC_WAIT,
      Material.MUSIC_DISC_WARD
    )

    private final val mutton = util.EnumSet.of(
      Material.MUTTON,
      Material.COOKED_MUTTON
    )

    private final val pickaxe = util.EnumSet.of(
      Material.WOODEN_PICKAXE,
      Material.STONE_PICKAXE,
      Material.IRON_PICKAXE,
      Material.GOLDEN_PICKAXE,
      Material.DIAMOND_PICKAXE
    )

    private final val pillar = util.EnumSet.of(
      Material.PURPUR_PILLAR,
      Material.QUARTZ_PILLAR
    )

    private final val piston = util.EnumSet.of(
      Material.PISTON,
      Material.STICKY_PISTON
    )

    private final val planks = util.EnumSet.of(
      Material.ACACIA_PLANKS,
      Material.BIRCH_PLANKS,
      Material.DARK_OAK_PLANKS,
      Material.JUNGLE_PLANKS,
      Material.OAK_PLANKS,
      Material.SPRUCE_PLANKS
    )

    private final val plant = util.EnumSet.of(
      Material.LILAC,
      Material.PEONY,
      Material.ROSE_BUSH,
      Material.SUNFLOWER
    )

    private final val porkchop = util.EnumSet.of(
      Material.PORKCHOP,
      Material.COOKED_PORKCHOP
    )

    private final val potato = util.EnumSet.of(
      Material.POTATO,
      Material.BAKED_POTATO,
      Material.POISONOUS_POTATO
    )

    private final val potion = util.EnumSet.of(
      Material.POTION,
      Material.LINGERING_POTION,
      Material.SPLASH_POTION
    )

    private final val pressurePlate = util.EnumSet.of(
      Material.ACACIA_PRESSURE_PLATE,
      Material.BIRCH_PRESSURE_PLATE,
      Material.DARK_OAK_PRESSURE_PLATE,
      Material.JUNGLE_PRESSURE_PLATE,
      Material.OAK_PRESSURE_PLATE,
      Material.SPRUCE_PRESSURE_PLATE,
      Material.STONE_PRESSURE_PLATE
    )

    private final val prismarine = util.EnumSet.of(
      Material.PRISMARINE,
      Material.PRISMARINE_BRICKS,
      Material.DARK_PRISMARINE
    )

    private final val quartzBlock = util.EnumSet.of(
      Material.QUARTZ_BLOCK,
      Material.CHISELED_QUARTZ_BLOCK,
      Material.SMOOTH_QUARTZ
    )

    private final val rabbit = util.EnumSet.of(
      Material.RABBIT,
      Material.COOKED_RABBIT
    )

    private final val rail = util.EnumSet.of(
      Material.RAIL,
      Material.ACTIVATOR_RAIL,
      Material.DETECTOR_RAIL,
      Material.POWERED_RAIL
    )

    private final val salmon = util.EnumSet.of(
      Material.SALMON,
      Material.COOKED_SALMON
    )

    private final val sand = util.EnumSet.of(
      Material.SAND,
      Material.RED_SAND
    )

    private final val sandstone = util.EnumSet.of(
      Material.SANDSTONE,
      Material.RED_SANDSTONE,
      Material.CHISELED_SANDSTONE,
      Material.CHISELED_RED_SANDSTONE,
      Material.CUT_SANDSTONE,
      Material.CUT_RED_SANDSTONE,
      Material.SMOOTH_SANDSTONE,
      Material.SMOOTH_RED_SANDSTONE
    )

    private final val sapling = util.EnumSet.of(
      Material.ACACIA_SAPLING,
      Material.BIRCH_SAPLING,
      Material.DARK_OAK_SAPLING,
      Material.JUNGLE_SAPLING,
      Material.OAK_SAPLING,
      Material.SPRUCE_SAPLING,
      Material.BAMBOO_SAPLING
    )

    private final val seagrass = util.EnumSet.of(
      Material.SEAGRASS,
      Material.TALL_SEAGRASS
    )

    private final val seeds = util.EnumSet.of(
      Material.BEETROOT_SEEDS,
      Material.MELON_SEEDS,
      Material.PUMPKIN_SEEDS,
      Material.WHEAT_SEEDS
    )

    private final val shovel = util.EnumSet.of(
      Material.WOODEN_SHOVEL,
      Material.STONE_SHOVEL,
      Material.IRON_SHOVEL,
      Material.GOLDEN_SHOVEL,
      Material.DIAMOND_SHOVEL
    )

    private final val shulkerBox = util.EnumSet.of(
      Material.SHULKER_BOX,
      Material.BLACK_SHULKER_BOX,
      Material.BLUE_SHULKER_BOX,
      Material.BROWN_SHULKER_BOX,
      Material.CYAN_SHULKER_BOX,
      Material.GRAY_SHULKER_BOX,
      Material.GREEN_SHULKER_BOX,
      Material.LIGHT_BLUE_SHULKER_BOX,
      Material.LIGHT_GRAY_SHULKER_BOX,
      Material.LIME_SHULKER_BOX,
      Material.MAGENTA_SHULKER_BOX,
      Material.ORANGE_SHULKER_BOX,
      Material.PINK_SHULKER_BOX,
      Material.PURPLE_SHULKER_BOX,
      Material.RED_SHULKER_BOX,
      Material.WHITE_SHULKER_BOX,
      Material.YELLOW_SHULKER_BOX
    )

    private final val sign = util.EnumSet.of(
      Material.ACACIA_SIGN,
      Material.BIRCH_SIGN,
      Material.DARK_OAK_SIGN,
      Material.JUNGLE_SIGN,
      Material.OAK_SIGN,
      Material.SPRUCE_SIGN
    )

    private final val wallSign = util.EnumSet.of(
      Material.ACACIA_WALL_SIGN,
      Material.BIRCH_WALL_SIGN,
      Material.DARK_OAK_WALL_SIGN,
      Material.JUNGLE_WALL_SIGN,
      Material.OAK_WALL_SIGN,
      Material.SPRUCE_WALL_SIGN
    )

    private final val slab = util.EnumSet.of(
      Material.BRICK_SLAB,
      Material.NETHER_BRICK_SLAB,
      Material.RED_NETHER_BRICK_SLAB,
      Material.SANDSTONE_SLAB,
      Material.RED_SANDSTONE_SLAB,
      Material.STONE_SLAB,
      Material.STONE_BRICK_SLAB,
      Material.COBBLESTONE_SLAB,
      Material.ANDESITE_SLAB,
      Material.DIORITE_SLAB,
      Material.GRANITE_SLAB,
      Material.END_STONE_BRICK_SLAB,
      Material.PRISMARINE_SLAB,
      Material.PRISMARINE_BRICK_SLAB,
      Material.DARK_PRISMARINE_SLAB,
      Material.PURPUR_SLAB,
      Material.QUARTZ_SLAB,
      Material.ACACIA_SLAB,
      Material.BIRCH_SLAB,
      Material.DARK_OAK_SLAB,
      Material.JUNGLE_SLAB,
      Material.OAK_SLAB,
      Material.SPRUCE_SLAB,
      Material.PETRIFIED_OAK_SLAB,
      Material.CUT_SANDSTONE_SLAB,
      Material.SMOOTH_SANDSTONE_SLAB,
      Material.CUT_RED_SANDSTONE_SLAB,
      Material.SMOOTH_RED_SANDSTONE_SLAB,
      Material.SMOOTH_STONE_SLAB,
      Material.MOSSY_STONE_BRICK_SLAB,
      Material.MOSSY_COBBLESTONE_SLAB,
      Material.POLISHED_ANDESITE_SLAB,
      Material.POLISHED_DIORITE_SLAB,
      Material.POLISHED_GRANITE_SLAB,
      Material.SMOOTH_QUARTZ_SLAB
    )

    private final val spawnEgg = util.EnumSet.of(
      Material.BAT_SPAWN_EGG,
      Material.BLAZE_SPAWN_EGG,
      Material.CAT_SPAWN_EGG,
      Material.CAVE_SPIDER_SPAWN_EGG,
      Material.CHICKEN_SPAWN_EGG,
      Material.COD_SPAWN_EGG,
      Material.COW_SPAWN_EGG,
      Material.CREEPER_SPAWN_EGG,
      Material.DOLPHIN_SPAWN_EGG,
      Material.DONKEY_SPAWN_EGG,
      Material.DROWNED_SPAWN_EGG,
      Material.ELDER_GUARDIAN_SPAWN_EGG,
      Material.ENDERMAN_SPAWN_EGG,
      Material.ENDERMITE_SPAWN_EGG,
      Material.EVOKER_SPAWN_EGG,
      Material.FOX_SPAWN_EGG,
      Material.GHAST_SPAWN_EGG,
      Material.GUARDIAN_SPAWN_EGG,
      Material.HORSE_SPAWN_EGG,
      Material.HUSK_SPAWN_EGG,
      Material.LLAMA_SPAWN_EGG,
      Material.MAGMA_CUBE_SPAWN_EGG,
      Material.MOOSHROOM_SPAWN_EGG,
      Material.MULE_SPAWN_EGG,
      Material.OCELOT_SPAWN_EGG,
      Material.PANDA_SPAWN_EGG,
      Material.PARROT_SPAWN_EGG,
      Material.PHANTOM_SPAWN_EGG,
      Material.PIG_SPAWN_EGG,
      Material.PILLAGER_SPAWN_EGG,
      Material.POLAR_BEAR_SPAWN_EGG,
      Material.PUFFERFISH_SPAWN_EGG,
      Material.RABBIT_SPAWN_EGG,
      Material.RAVAGER_SPAWN_EGG,
      Material.SALMON_SPAWN_EGG,
      Material.SHEEP_SPAWN_EGG,
      Material.SHULKER_SPAWN_EGG,
      Material.SILVERFISH_SPAWN_EGG,
      Material.SKELETON_HORSE_SPAWN_EGG,
      Material.SKELETON_SPAWN_EGG,
      Material.SLIME_SPAWN_EGG,
      Material.SPIDER_SPAWN_EGG,
      Material.SQUID_SPAWN_EGG,
      Material.STRAY_SPAWN_EGG,
      Material.TRADER_LLAMA_SPAWN_EGG,
      Material.TROPICAL_FISH_SPAWN_EGG,
      Material.TURTLE_SPAWN_EGG,
      Material.VEX_SPAWN_EGG,
      Material.VILLAGER_SPAWN_EGG,
      Material.VINDICATOR_SPAWN_EGG,
      Material.WANDERING_TRADER_SPAWN_EGG,
      Material.WITCH_SPAWN_EGG,
      Material.WITHER_SKELETON_SPAWN_EGG,
      Material.WOLF_SPAWN_EGG,
      Material.ZOMBIE_HORSE_SPAWN_EGG,
      Material.ZOMBIE_PIGMAN_SPAWN_EGG,
      Material.ZOMBIE_SPAWN_EGG,
      Material.ZOMBIE_VILLAGER_SPAWN_EGG
    )

    private final val spiderEye = util.EnumSet.of(
      Material.SPIDER_EYE,
      Material.FERMENTED_SPIDER_EYE
    )

    private final val sponge = util.EnumSet.of(
      Material.SPONGE,
      Material.WET_SPONGE
    )

    private final val stairs = util.EnumSet.of(
      Material.BRICK_STAIRS,
      Material.NETHER_BRICK_STAIRS,
      Material.RED_NETHER_BRICK_STAIRS,
      Material.SANDSTONE_STAIRS,
      Material.RED_SANDSTONE_STAIRS,
      Material.STONE_STAIRS,
      Material.STONE_BRICK_STAIRS,
      Material.COBBLESTONE_STAIRS,
      Material.ANDESITE_STAIRS,
      Material.DIORITE_STAIRS,
      Material.GRANITE_STAIRS,
      Material.END_STONE_BRICK_STAIRS,
      Material.PRISMARINE_STAIRS,
      Material.PRISMARINE_BRICK_STAIRS,
      Material.DARK_PRISMARINE_STAIRS,
      Material.PURPUR_STAIRS,
      Material.QUARTZ_STAIRS,
      Material.ACACIA_STAIRS,
      Material.BIRCH_STAIRS,
      Material.DARK_OAK_STAIRS,
      Material.JUNGLE_STAIRS,
      Material.OAK_STAIRS,
      Material.SPRUCE_STAIRS,
      Material.SMOOTH_SANDSTONE_STAIRS,
      Material.SMOOTH_RED_SANDSTONE_STAIRS,
      Material.MOSSY_STONE_BRICK_STAIRS,
      Material.MOSSY_COBBLESTONE_STAIRS,
      Material.POLISHED_ANDESITE_STAIRS,
      Material.POLISHED_DIORITE_STAIRS,
      Material.POLISHED_GRANITE_STAIRS,
      Material.SMOOTH_QUARTZ_STAIRS
    )

    private final val stew = util.EnumSet.of(
      Material.BEETROOT_SOUP,
      Material.MUSHROOM_STEW,
      Material.RABBIT_STEW,
      Material.SUSPICIOUS_STEW
    )

    private final val stone = util.EnumSet.of(
      Material.STONE,
      Material.SMOOTH_STONE,
      Material.STONE_BRICKS,
      Material.CHISELED_STONE_BRICKS,
      Material.CRACKED_STONE_BRICKS,
      Material.MOSSY_STONE_BRICKS
    )

    private final val stonite = util.EnumSet.of(
      Material.ANDESITE,
      Material.DIORITE,
      Material.GRANITE,
      Material.POLISHED_ANDESITE,
      Material.POLISHED_DIORITE,
      Material.POLISHED_GRANITE
    )

    private final val structureBlock = util.EnumSet.of(
      Material.STRUCTURE_BLOCK,
      Material.STRUCTURE_VOID
    )

    private final val sword = util.EnumSet.of(
      Material.WOODEN_SWORD,
      Material.STONE_SWORD,
      Material.IRON_SWORD,
      Material.GOLDEN_SWORD,
      Material.DIAMOND_SWORD
    )

    private final val terracotta = util.EnumSet.of(
      Material.TERRACOTTA,
      Material.BLACK_TERRACOTTA,
      Material.BLUE_TERRACOTTA,
      Material.BROWN_TERRACOTTA,
      Material.CYAN_TERRACOTTA,
      Material.GRAY_TERRACOTTA,
      Material.GREEN_TERRACOTTA,
      Material.LIGHT_BLUE_TERRACOTTA,
      Material.LIGHT_GRAY_TERRACOTTA,
      Material.LIME_TERRACOTTA,
      Material.MAGENTA_TERRACOTTA,
      Material.ORANGE_TERRACOTTA,
      Material.PINK_TERRACOTTA,
      Material.PURPLE_TERRACOTTA,
      Material.RED_TERRACOTTA,
      Material.WHITE_TERRACOTTA,
      Material.YELLOW_TERRACOTTA
    )

    private final val trapdoor = util.EnumSet.of(
      Material.ACACIA_TRAPDOOR,
      Material.BIRCH_TRAPDOOR,
      Material.DARK_OAK_TRAPDOOR,
      Material.JUNGLE_TRAPDOOR,
      Material.OAK_TRAPDOOR,
      Material.SPRUCE_TRAPDOOR,
      Material.IRON_TRAPDOOR
    )

    private final val wall = util.EnumSet.of(
      Material.BRICK_WALL,
      Material.NETHER_BRICK_WALL,
      Material.RED_NETHER_BRICK_WALL,
      Material.PRISMARINE_WALL,
      Material.SANDSTONE_WALL,
      Material.RED_SANDSTONE_WALL,
      Material.STONE_BRICK_WALL,
      Material.END_STONE_BRICK_WALL,
      Material.COBBLESTONE_WALL,
      Material.ANDESITE_WALL,
      Material.DIORITE_WALL,
      Material.GRANITE_WALL,
      Material.MOSSY_STONE_BRICK_WALL,
      Material.MOSSY_COBBLESTONE_WALL
    )

    private final val weightedPressurePlate = util.EnumSet.of(
      Material.LIGHT_WEIGHTED_PRESSURE_PLATE,
      Material.HEAVY_WEIGHTED_PRESSURE_PLATE
    )

    private final val wood = util.EnumSet.of(
      Material.ACACIA_WOOD,
      Material.BIRCH_WOOD,
      Material.DARK_OAK_WOOD,
      Material.JUNGLE_WOOD,
      Material.OAK_WOOD,
      Material.SPRUCE_WOOD,
      Material.STRIPPED_ACACIA_WOOD,
      Material.STRIPPED_BIRCH_WOOD,
      Material.STRIPPED_DARK_OAK_WOOD,
      Material.STRIPPED_JUNGLE_WOOD,
      Material.STRIPPED_OAK_WOOD,
      Material.STRIPPED_SPRUCE_WOOD
    )

    private final val wool = util.EnumSet.of(
      Material.BLACK_WOOL,
      Material.BLUE_WOOL,
      Material.BROWN_WOOL,
      Material.CYAN_WOOL,
      Material.GRAY_WOOL,
      Material.GREEN_WOOL,
      Material.LIGHT_BLUE_WOOL,
      Material.LIGHT_GRAY_WOOL,
      Material.LIME_WOOL,
      Material.MAGENTA_WOOL,
      Material.ORANGE_WOOL,
      Material.PINK_WOOL,
      Material.PURPLE_WOOL,
      Material.RED_WOOL,
      Material.WHITE_WOOL,
      Material.YELLOW_WOOL
    )

    def isAir: Boolean = air.contains(material)
    def isAnvil: Boolean = anvil.contains(material)
    def isArrow: Boolean = arrow.contains(material)
    def isAxe: Boolean = axe.contains(material)
    def isBanner: Boolean = banner.contains(material)
    def isWallBanner: Boolean = wallBanner.contains(material)
    def isBeef: Boolean = beef.contains(material)
    def isBoots: Boolean = boots.contains(material)
    def isBucket: Boolean = bucket.contains(material)
    def isBannerPattern: Boolean = bannerPattern.contains(material)
    def isBed: Boolean = bed.contains(material)
    def isBoat: Boolean = boat.contains(material)
    def isBrick: Boolean = brick.contains(material)
    def isBrickBlock: Boolean = brickBlock.contains(material)
    def isButton: Boolean = button.contains(material)
    def isCarpet: Boolean = carpet.contains(material)
    def isCobblestone: Boolean = cobblestone.contains(material)
    def isChest: Boolean = chest.contains(material)
    def isChestplate: Boolean = chestplate.contains(material)
    def isChicken: Boolean = chicken.contains(material)
    def isChorusFruit: Boolean = chorusFruit.contains(material)
    def isCod: Boolean = cod.contains(material)
    def isCommandBlock: Boolean = commandBlock.contains(material)
    def isConcrete: Boolean = concrete.contains(material)
    def isConcretePowder: Boolean = concretePowder.contains(material)
    def isCoral: Boolean = coral.contains(material)
    def isCoralBlock: Boolean = coralBlock.contains(material)
    def isCoralFan: Boolean = coralFan.contains(material)
    def isCoralWallFan: Boolean = coralWallFan.contains(material)
    def isDirt: Boolean = dirt.contains(material)
    def isDoor: Boolean = door.contains(material)
    def isDye: Boolean = dye.contains(material)
    def isEndStone: Boolean = endStone.contains(material)
    def isFence: Boolean = fence.contains(material)
    def isFenceGate: Boolean = fenceGate.contains(material)
    def isFlower: Boolean = flower.contains(material)
    def isFlowerPot: Boolean = flowerPot.contains(material)
    def isGlass: Boolean = glass.contains(material)
    def isGlassPane: Boolean = glassPane.contains(material)
    def isGlazedTerracotta: Boolean = glazedTerracotta.contains(material)
    def isGrass: Boolean = grass.contains(material)
    def isHelmet: Boolean = helmet.contains(material)
    def isHoe: Boolean = hoe.contains(material)
    def isHorseArmor: Boolean = horseArmor.contains(material)
    def isIce: Boolean = ice.contains(material)
    def isInfestedBlock: Boolean = infestedBlock.contains(material)
    def isLeaves: Boolean = leaves.contains(material)
    def isLeggings: Boolean = leggings.contains(material)
    def isLog: Boolean = log.contains(material)
    def isMinecart: Boolean = minecart.contains(material)
    def isMobHead: Boolean = mobHead.contains(material)
    def isWallMobHead: Boolean = wallMobHead.contains(material)
    def isMushroom: Boolean = mushroom.contains(material)
    def isMushroomBlock: Boolean = mushroomBlock.contains(material)
    def isMusicDisc: Boolean = musicDisc.contains(material)
    def isMutton: Boolean = mutton.contains(material)
    def isPickaxe: Boolean = pickaxe.contains(material)
    def isPillar: Boolean = pillar.contains(material)
    def isPiston: Boolean = piston.contains(material)
    def isPlanks: Boolean = planks.contains(material)
    def isPlant: Boolean = plant.contains(material)
    def isPorkchop: Boolean = porkchop.contains(material)
    def isPotato: Boolean = potato.contains(material)
    def isPotion: Boolean = potion.contains(material)
    def isPressurePlate: Boolean = pressurePlate.contains(material)
    def isPrismarine: Boolean = prismarine.contains(material)
    def isQuartzBlock: Boolean = quartzBlock.contains(material)
    def isRabbit: Boolean = rabbit.contains(material)
    def isRail: Boolean = rail.contains(material)
    def isSalmon: Boolean = salmon.contains(material)
    def isSand: Boolean = sand.contains(material)
    def isSandstone: Boolean = sandstone.contains(material)
    def isSapling: Boolean = sapling.contains(material)
    def isSeagrass: Boolean = seagrass.contains(material)
    def isSeeds: Boolean = seeds.contains(material)
    def isShovel: Boolean = shovel.contains(material)
    def isShulkerBox: Boolean = shulkerBox.contains(material)
    def isSign: Boolean = sign.contains(material)
    def isSpawnEgg: Boolean = spawnEgg.contains(material)
    def isSpiderEye: Boolean = spiderEye.contains(material)
    def isStew: Boolean = stew.contains(material)
    def isWallSign: Boolean = wallSign.contains(material)
    def isSlab: Boolean = slab.contains(material)
    def isSponge: Boolean = sponge.contains(material)
    def isStairs: Boolean = stairs.contains(material)
    def isStone: Boolean = stone.contains(material)
    def isStonite: Boolean = stonite.contains(material)
    def isStructureBlock: Boolean = structureBlock.contains(material)
    def isSword: Boolean = sword.contains(material)
    def isTerracotta: Boolean = terracotta.contains(material)
    def isTrapdoor: Boolean = trapdoor.contains(material)
    def isWall: Boolean = wall.contains(material)
    def isWeightedPressurePlate: Boolean = weightedPressurePlate.contains(material)
    def isWood: Boolean = wood.contains(material)
    def isWool: Boolean = wool.contains(material)
  }
}
