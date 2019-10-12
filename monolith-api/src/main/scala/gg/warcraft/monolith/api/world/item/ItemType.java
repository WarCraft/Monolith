package gg.warcraft.monolith.api.world.item;

public enum ItemType {
    // TODO No AIR item?

    SPAWN_EGG,
    MUSIC_DISC,
    RAIL,
    BUTTON,
    DOOR,
    TRAPDOOR,
    FLOWER,
    SLAB,
    STAIRS,
    WALL,
    ICE,
    STONITE, // ANDESITE, DIORITE, GRANITE (/w polished variant)
    ARMOR_STAND,
    ANVIL, // with CHIPPED and DAMAGED variants
    SPONGE, // wet and not
    WEIGHTED_PRESSURE_PLATE,

    /* wood */
    BOAT,
    FENCE, // also has nether brick material
    GATE,
    LEAVES,
    PLANKS,
    PRESSURE_PLATE, // also has stone variant
    SAPLING,
    SIGN,

    LOG, // stripped variant
    WOOD, // stripped variant

    /* color */
    BANNER,
    BED,
    CARPET,
    CONCRETE,
    CONCRETE_POWDER,
    DYE,
    GLAZED_TERRACOTTA,
    SHULKER_BOX, // incl no color
    GLASS, // incl no color
    GLASS_PANE, // incl no color
    TERRACOTTA, // incl no color
    WOOL,


    /* equipment */    // all can have iron, gold, diamond
    AXE,         // weapons can have stone and wood
    BOOTS,
    CHESTPLATE, // armor can have chainmail and leather (also horse armor)
    HELMET,
    HOE,
    HORSE_ARMOR,
    LEGGINGS,
    PICKAXE,
    SHOVEL,
    SWORD,


    BARRIER,
    BEACON,
    BEDROCK,
    CORAL,
    CORAL_FAN,
    CORAL_BLOCK,
    INFESTED_BLOCK,


    APPLE,
    ARROW,
    BOW,
    BARREL,
    BELL,
    BLAST_FURNACE,
    BLAZE_POWDER,
    BLAZE_ROD,
    BONE,
    BONE_BLOCK,
    BONE_MEAL,
    BOOKSHELF,
    BOWL,
    BREAD,
    BREWING_STAND,
    CAMPFIRE,

    END_CRYSTAL,
    END_PORTAL_FRAME,
    END_ROD,

    CHORUS_FLOWER,
    CHORUS_FRUIT,       // edible
    POPPED_CHORUS_FRUIT, // inedible
    CHORUS_PLANT,

    MUSHROOM, // red, brown
    MUSHROOM_BLOCK, // red, brown, stem

    BUCKET,

    CAKE,
    CARROT,
    CARROT_ON_A_STICK,
    CARTOGRAPHY_TABLE,
    CAULDRON,
    CHARCOAL,
    CHEST,
    CLOCK,
    COBWEB,
    COMMAND_BLOCK,
    COMPARATOR,
    COMPASS,
    COMPOSTER,
    CONDUIT,
    CROSSBOW,
    DAYLIGHT_DETECTOR,
    DEBUG_STICK,
    DISPENSER,
    DROPPER,
    ELYTRA,
    FEATHER,
    FISHING_ROD,
    FLETCHING_TABLE,
    FLINT,
    FLINT_AND_STEEL,
    FURNACE,
    GLOWSTONE,
    GLOWSTONE_DUST,
    GRINDSTONE,
    GUNPOWDER,
    HAY_BALE,
    HOPPER,
    REPEATER,

    MINECART,
    MOB_HEAD,

    BANNER_PATTERN,

    CRAFTING_TABLE,
    DEAD_BUSH,
    DRIED_KELP,
    DRIED_KELP_BLOCK,
    EGG,
    ENDER_EYE,
    ENDER_PEARL,

    FLOWER_POT,
    GRASS,
    GRASS_BLOCK,
    GRASS_PATH,
    GRAVEL,
    INK_SAC,
    ITEM_FRAME,
    JIGSAW_BLOCK,
    JUKEBOX,
    KELP,
    LADDER,
    LANTERN,
    LEATHER,
    LECTERN,
    LEVER,
    LILY_PAD,
    LOOM,
    MAGMA_BLOCK, // TODO rename block variant (Magma) to MagmaBlock? Or name all items that are clearly blocks _BLOCK? ObisidianBlock?
    MAGMA_CREAM,
    NETHERRACK,
    NOTE_BLOCK,
    OBSIDIAN,
    PAINTING,
    PAPER,
    SADDLE,
    SCAFFOLDING,
    SHEARS,
    SHIELD,
    SHULKER_SHELL,
    SMITHING_TABLE,
    SMOKER,
    STICK,
    STONECUTTER,
    STRING,
    TNT,
    TORCH,
    TOTEM_OF_UNDYING,
    TRIDENT,

    CLAY_BLOCK, // CLAY
    CLAY, // CLAY_BALL

    DIRT, // coarse variant
    FARMLAND,
    HEART_OF_THE_SEA,
    LEAD,
    NAME_TAG,
    NAUTILUS_SHELL,
    NETHER_STAR,
    OBSERVER,
    PHANTOM_MEMBRANE,
    RABBIT_FOOT,
    RABBIT_HIDE,
    ROTTEN_FLESH,
    SCUTE,
    SEA_LANTERN,
    SEA_PICKLE,
    SLIME_BALL, // TODO what to name CLAY and SLIME? CLAY_BALL SLIME_BALL or leave it?
    SLIME_BLOCK,
    SNOW,
    SNOWBALL,
    SNOW_BLOCK,
    SOUL_SAND,
    SPAWNER,
    SUGAR,
    SUGAR_CANE,
    TURTLE_EGG,
    TURTLE_HELMET,
    VINE,

    GHAST_TEAR,
    ENCHANTING_TABLE,

    PRISMARINE_CRYSTALS,
    PRISMARINE_SHARD,

    BAMBOO,
    CACTUS,

    COBBLESTONE, // mossy variant
    SANDSTONE, // RED material, chiseled, cut, smooth variants
    STONE, // NORMAL / BRICKS materials, CHISELED, CRACKED, MOSSY, SMOOTH variants
    PRISMARINE, // DARK / BRICKS materials
    END_STONE, // NORMAL / BRICHS materials
    BRICK, // NORMAL, NETHER materials
    BRICKS, // NORMAL, NETHER, RED_NETHER materials
    PURPUR_BLOCK,
    QUARTZ,
    QUARTZ_BLOCK, // chiseled and smooth variant
    PILLAR, // quartz and purpur materials
    SAND, // RED material

    /* cookable item */
    BEEF,
    CHICKEN,
    COD,
    MUTTON,
    PORKCHOP,
    RABBIT,
    SALMON,

    DRAGON_BREATH,
    DRAGON_EGG,
    SPIDER_EYE, // fermented option
    GLASS_BOTTLE,

    GOLDEN_APPLE, // enchanted option
    GOLDEN_CARROT,

    TRIPWIRE_HOOK,

    BOOK, // enchanted option, quil (writable) option, written (uneditable) option,
    KNOWLEDGE_BOOK,

    IRON_BARS,


    ORE, // COAL, DIAMOND, EMERALD, GOLD, IRON, LAPIS, REDSTONE, QUARTZ materials
    INGOT, // GOLD, IRON materials
    NUGGET, // GOLD, IRON materials

    COAL,
    DIAMOND,
    EMERALD,
    LAPIS,
    REDSTONE,
    MINERAL, // ? DIAMOND, EMERALD, GOLD, IRON, LAPIS, REDSTONE, COAL materials

    PISTON, // STICKY variant

    NETHER_WART,
    NETHER_WART_BLOCK,

    REDSTONE_LAMP,
    REDSTONE_TORCH,

    POTION,

    MAP, // FILLED variant

    EXPERIENCE_BOTTLE,
    FIREWORK_ROCKET,
    FIREWORK_STAR,
    FIRE_CHARGE,

    SEAGRASS,

    MYCELIUM,
    PODZOL,
}

/* food

POTATO
BAKED_POTATO
POISONOUS_POTATO

BEETROOT
BEETROOT_SEEDS

MUSHROOM_STEW
RABBIT_STEW
SUSPICIOUS_STEW
BEETROOT_SOUP

COOKIE

MELON
MELON_SEEDS
MELON_SLICE
GLISTERING_MELON_SLICE

PUMPKIN
PUMPKIN_PIE
PUMPKIN_SEEDS
CARVED_PUMPKIN
JACK_O_LANTERN

WHEAT
WHEAT_SEEDS

COCOA_BEANS

SWEET_BERRIES
TROPICAL_FISH
PUFFERFISH

FERN
LARGE_FERN
LILAC
PEONY
ROSE_BUSH
SUNFLOWER
TALL_GRASS

STRUCTURE_BLOCK
STRUCTURE_VOID // TODO do we want this merged with Air?

 */


/*
ANVIL, // with CHIPPED and DAMAGED variants
    APPLE,
    ARMOR_STAND,
    ARROW,
    AXE,         // weapons can have stone and wood
    BAMBOO,
    BANNER,
    BANNER_PATTERN,
    BARREL,
    BARRIER,
    BEACON,
    BED,
    BEDROCK,
    BEEF,
    BELL,
    BLAST_FURNACE,
    BLAZE_POWDER,
    BLAZE_ROD,
    BOAT,
    BONE,
    BONE_BLOCK,
    BONE_MEAL,
    BOOK, // enchanted option, quil (writable) option, written (uneditable) option,
    BOOKSHELF,
    BOOTS,
    BOW,
    BOWL,
    BREAD,
    BREWING_STAND,
    BRICK, // NORMAL, NETHER materials
    BRICKS, // NORMAL, NETHER, RED_NETHER materials
    BUCKET,
    BUTTON,
    CACTUS,
    CAKE,
    CAMPFIRE,
    CARPET,
    CARROT,
    CARROT_ON_A_STICK,
    CARTOGRAPHY_TABLE,
    CAULDRON,
    CHARCOAL,
    CHEST,
    CHESTPLATE, // armor can have chainmail and leather (also horse armor)
    CHICKEN,
    CHORUS_FLOWER,
    CHORUS_FRUIT,       // edible
    CHORUS_PLANT,
    CLAY, // CLAY_BALL
    CLAY_BLOCK, // CLAY
    CLOCK,
    COAL,
    COBBLESTONE, // mossy variant
    COBWEB,
    COD,
    COMMAND_BLOCK,
    COMPARATOR,
    COMPASS,
    COMPOSTER,
    CONCRETE,
    CONCRETE_POWDER,
    CONDUIT,
    CORAL,
    CORAL_BLOCK,
    CORAL_FAN,
    CRAFTING_TABLE,
    CROSSBOW,
    DAYLIGHT_DETECTOR,
    DEAD_BUSH,
    DEBUG_STICK,
    DIAMOND,
    DIRT, // coarse variant
    DISPENSER,
    DOOR,
    DRAGON_BREATH,
    DRAGON_EGG,
    DRIED_KELP,
    DRIED_KELP_BLOCK,
    DROPPER,
    DYE,
    EGG,
    ELYTRA,
    EMERALD,
    ENCHANTING_TABLE,
    ENDER_EYE,
    ENDER_PEARL,
    END_CRYSTAL,
    END_PORTAL_FRAME,
    END_ROD,
    END_STONE, // NORMAL / BRICHS materials
    EXPERIENCE_BOTTLE,
    FARMLAND,
    FEATHER,
    FENCE, // also has nether brick material
    FENCE_GATE,
    FIREWORK_ROCKET,
    FIREWORK_STAR,
    FIRE_CHARGE,
    FISHING_ROD,
    FLETCHING_TABLE,
    FLINT,
    FLINT_AND_STEEL,
    FLOWER,
    FLOWER_POT,
    FURNACE,
    GHAST_TEAR,
    GLASS, // incl no color
    GLASS_BOTTLE,
    GLASS_PANE, // incl no color
    GLAZED_TERRACOTTA,
    GLOWSTONE,
    GLOWSTONE_DUST,
    GOLDEN_APPLE, // enchanted option
    GOLDEN_CARROT,
    GRASS,
    GRASS_BLOCK,
    GRASS_PATH,
    GRAVEL,
    GRINDSTONE,
    GUNPOWDER,
    HAY_BALE,
    HEART_OF_THE_SEA,
    HELMET,
    HOE,
    HOPPER,
    HORSE_ARMOR,
    ICE,
    INFESTED_BLOCK,
    INGOT, // GOLD, IRON materials
    INK_SAC,
    IRON_BARS,
    ITEM_FRAME,
    JIGSAW_BLOCK,
    JUKEBOX,
    KELP,
    KNOWLEDGE_BOOK,
    LADDER,
    LANTERN,
    LAPIS,
    LEAD,
    LEATHER,
    LEAVES,
    LECTERN,
    LEGGINGS,
    LEVER,
    LILY_PAD,
    LOG, // stripped variant
    LOOM,
    MAGMA_BLOCK, // TODO rename block variant (Magma) to MagmaBlock? Or name all items that are clearly blocks _BLOCK? ObisidianBlock?
    MAGMA_CREAM,
    MAP, // FILLED variant
    MINECART,
    MINERAL, // ? DIAMOND, EMERALD, GOLD, IRON, LAPIS, REDSTONE, COAL materials
    MOB_HEAD,
    MUSHROOM, // red, brown
    MUSHROOM_BLOCK, // red, brown, stem
    MUSIC_DISC,
    MUTTON,
    MYCELIUM,
    NAME_TAG,
    NAUTILUS_SHELL,
    NETHERRACK,
    NETHER_STAR,
    NETHER_WART,
    NETHER_WART_BLOCK,
    NOTE_BLOCK,
    NUGGET, // GOLD, IRON materials
    OBSERVER,
    OBSIDIAN,
    ORE, // COAL, DIAMOND, EMERALD, GOLD, IRON, LAPIS, REDSTONE, QUARTZ materials
    PAINTING,
    PAPER,
    PHANTOM_MEMBRANE,
    PICKAXE,
    PILLAR, // quartz and purpur materials
    PISTON, // STICKY variant
    PLANKS,
    PODZOL,
    POPPED_CHORUS_FRUIT, // inedible
    PORKCHOP,
    POTION,
    PRESSURE_PLATE, // also has stone variant
    PRISMARINE, // DARK / BRICKS materials
    PRISMARINE_CRYSTALS,
    PRISMARINE_SHARD,
    PURPUR_BLOCK,
    QUARTZ,
    QUARTZ_BLOCK, // chiseled and smooth variant
    RABBIT,
    RABBIT_FOOT,
    RABBIT_HIDE,
    RAIL,
    REDSTONE,
    REDSTONE_LAMP,
    REDSTONE_TORCH,
    REPEATER,
    ROTTEN_FLESH,
    SADDLE,
    SALMON,
    SAND, // RED material
    SANDSTONE, // RED material, chiseled, cut, smooth variants
    SAPLING,
    SCAFFOLDING,
    SCUTE,
    SEAGRASS,
    SEA_LANTERN,
    SEA_PICKLE,
    SHEARS,
    SHIELD,
    SHOVEL,
    SHULKER_BOX, // incl no color
    SHULKER_SHELL,
    SIGN,
    SLAB,
    SLIME_BALL, // TODO what to name CLAY and SLIME? CLAY_BALL SLIME_BALL or leave it?
    SLIME_BLOCK,
    SMITHING_TABLE,
    SMOKER,
    SNOW,
    SNOWBALL,
    SNOW_BLOCK,
    SOUL_SAND,
    SPAWNER,
    SPAWN_EGG,
    SPIDER_EYE, // fermented option
    SPONGE, // wet and not
    STAIRS,
    STICK,
    STONE, // NORMAL / BRICKS materials, CHISELED, CRACKED, MOSSY, SMOOTH variants
    STONECUTTER,
    STONITE, // ANDESITE, DIORITE, GRANITE (/w polished variant)
    STRING,
    SUGAR,
    SUGAR_CANE,
    SWORD,
    TERRACOTTA, // incl no color
    TNT,
    TORCH,
    TOTEM_OF_UNDYING,
    TRAPDOOR,
    TRIDENT,
    TRIPWIRE_HOOK,
    TURTLE_EGG,
    TURTLE_HELMET,
    VINE,
    WALL,
    WEIGHTED_PRESSURE_PLATE,
    WOOD, // stripped variant
    WOOL,
 */