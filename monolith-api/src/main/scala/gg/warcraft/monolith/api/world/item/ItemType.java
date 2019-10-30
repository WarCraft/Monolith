package gg.warcraft.monolith.api.world.item;

public enum ItemType {
    // TODO No AIR item?
    APPLE,

    SPAWN_EGG,
    MUSIC_DISC,
    RAILS, // TODO rename Rail as per Minecraft's implementation?
    BUTTON,
    DOOR,
    TRAPDOOR,
    FLOWER,
    SLAB,
    STAIRS,
    WALL,
    ICE,
    ANDESITE, // w polished variant TODO remove NORMAL from variants and make VariableBlock?
    DIORITE, // w polished variant
    GRANITE, // w polished variant
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

    MUSHROOM, // red, brown
    MUSHROOM_BLOCK, // red, brown, stem

    BUCKET,

    CHORUS_FLOWER,
    CHORUS_FRUIT,
    POPPED_CHORUS_FRUIT,
    CHORUS_PLANT,

    INFESTED_BLOCK,
    BOOK,
    BOOK_AND_QUILL,
    ENCHANTED_BOOK,
    KNOWLEDGE_BOOK,
    WRITTEN_BOOK,

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
    MAGMA_BLOCK, // TODO rename slime block to SLIME_BLOCK? Item is called Slime Block ingame
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
    SLIMEBALL,
    SLIME_BLOCK, // TODO this is called Slime Block in MC, rename block type to SlimeBlock too?
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

    COBBLESTONE, // mossy variant
    SANDSTONE, // RED material, chiseled, cut, smooth variants
    STONE, // NORMAL / BRICKS materials, CHISELED, CRACKED, MOSSY, SMOOTH variants

    GHAST_TEAR,
    ENCHANTING_TABLE,

    BAMBOO,
    CACTUS,

    PRISMARINE_CRYSTALS,
    PRISMARINE_SHARD,

    PRISMARINE, // DARK / BRICKS materials
    END_STONE, // NORMAL / BRICKS materials
    BRICK, // NORMAL, NETHER materials
    BRICK_BLOCK, // NORMAL, NETHER, RED_NETHER materials // TODO what to do with Block naming here
    PURPUR,
    QUARTZ,
    QUARTZ_BLOCK, // chiseled and smooth variant
    PILLAR, // quartz and purpur materials
    SAND, // RED material

    DRAGON_BREATH,
    DRAGON_EGG,
    SPIDER_EYE, // fermented option
    GLASS_BOTTLE,

    GOLDEN_APPLE, // enchanted option
    GOLDEN_CARROT,

    TRIPWIRE_HOOK,
    IRON_BARS,

    GOLD_INGOT, GOLD_NUGGET, GOLD_BLOCK, GOLD_ORE,
    IRON_INGOT, IRON_NUGGET, IRON_BLOCK, IRON_ORE,
    COAL, COAL_BLOCK, COAL_ORE, // TODO to keep parity with blocks should the block.Diamond be renamed block.DiamondBlock?
    // TODO right now an ItemType.GOLD_BLOCK turns into a BlockType.GOLD, maybe makes sense?
    // TODO maybe suffix all proper block items with BLOCK? GOLD_ORE_BLOCK etc
    // TODO ItemType.SLIME_BLOCK then becomes BlockType.SLIME and ItemType.SLIME remains the Slime Ball
    DIAMOND, DIAMOND_BLOCK, DIAMOND_ORE,
    EMERALD, EMERALD_BLOCK, EMERALD_ORE,
    LAPIS, LAPIS_BLOCK, LAPIS_ORE,
    REDSTONE, REDSTONE_BLOCK, REDSTONE_ORE,
    QUARTZ_ORE, // QUARTZ and QUARTZ_BLOCK defined above

    PISTON, // STICKY variant

    NETHER_WART,
    NETHER_WART_BLOCK,

    REDSTONE_LAMP,
    REDSTONE_TORCH,

    POTION,

    MAP, // FILLED variant

    BOTTLE_OF_ENCHANTING,
    FIREWORK_ROCKET,
    FIREWORK_STAR,
    FIRE_CHARGE,

    SEAGRASS,

    MYCELIUM,
    PODZOL,

    COOKIE,

    /* cookable item */
    BEEF, // TODO cooked: STEAK
    CHICKEN,
    COD,
    MUTTON,
    PORKCHOP,
    RABBIT,
    SALMON,
    POTATO, // TODO cooked: BAKED

    POISONOUS_POTATO,

    TROPICAL_FISH,
    PUFFERFISH,

    SWEET_BERRIES,
    COCOA_BEANS,

    BEETROOT,
    MELON,
    MELON_SLICE,
    GOLDEN_MELON_SLICE,
    PUMPKIN, // carved variant
    PUMPKIN_PIE,
    WHEAT,
    JACK_OF_THE_LANTERN,

    PLANT,
}

/* food

MELON_SEEDS
BEETROOT_SEEDS
PUMPKIN_SEEDS
WHEAT_SEEDS

MUSHROOM_STEW
RABBIT_STEW
SUSPICIOUS_STEW
BEETROOT_SOUP

FERN
LARGE_FERN

STRUCTURE_BLOCK
STRUCTURE_VOID // TODO do we want this merged with Air?

 */
