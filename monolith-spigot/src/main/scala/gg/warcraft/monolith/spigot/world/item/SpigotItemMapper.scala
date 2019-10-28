package gg.warcraft.monolith.spigot.world.item

import java.util

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.material.WoodMaterial
import gg.warcraft.monolith.api.world.block.variant.{ AnvilVariant, CoralVariant, RailsVariant, SaplingVariant, StoniteVariant }
import gg.warcraft.monolith.api.world.item._
import gg.warcraft.monolith.api.world.item.variant.{ ArrowVariant, SpawnEggVariant }
import javax.inject.Inject
import org.bukkit.Material

class SpigotItemMapper @Inject()(
    private val colorMapper: SpigotItemColorMapper,
    private val materialMapper: SpigotItemMaterialMapper,
    private val variantMapper: SpigotItemVariantMapper
) {
  private val cache =
    new util.EnumMap[Material, () => Item](classOf[Material])

  def map(itemStack: SpigotItemStack): ItemStack = {
    implicit val implicitItemStack: SpigotItemStack = itemStack

    def data(implicit itemStack: SpigotItemStack): ItemData = {
      ItemData(itemStack.getItemMeta.getDisplayName)
      // TODO map other vals
    }

    def color(implicit itemStack: SpigotItemStack): BlockColor =
      colorMapper.map(itemStack.getType)

    def material[T <: ItemMaterial](implicit itemStack: SpigotItemStack): T =
      materialMapper.map(itemStack.getType).asInstanceOf[T]

    def variant[T <: ItemVariant](implicit itemStack: SpigotItemStack): T =
      variantMapper.map(itemStack).asInstanceOf[T]

    val itemBuilder = cache.computeIfAbsent(itemStack.getType, _ match {
      case Material.AIR            => () => null
      case Material.ALLIUM         => () => null
      case Material.ANDESITE       => () => Andesite(variant[StoniteVariant], data)
      case Material.ANVIL          => () => Anvil(variant[AnvilVariant], data)
      case Material.APPLE          => () => null
      case Material.ARMOR_STAND    => () => ArmorStand(data)
     // case Material.ARROW          => () => Arrow(variant[ArrowVariant], data)
      case Material.AZURE_BLUET    => () => null
      case Material.BAKED_POTATO   => () => null
      case Material.BAMBOO         => () => null
      case Material.BARREL         => () => Barrel(data)
      case Material.BARRIER        => () => Barrier(data)
      case Material.BEACON         => () => Beacon(data)
      case Material.BEDROCK        => () => Bedrock(data)
      case Material.BEEF           => () => null
      case Material.BEETROOT       => () => null
      case Material.BEETROOT_SEEDS => () => null
      case Material.BEETROOT_SOUP  => () => null
      case Material.BELL           => () => Bell(data)
      case Material.BLAST_FURNACE  => () => BlastFurnace(data)
      case Material.BLAZE_POWDER   => () => BlazeRod(data)
      case Material.BLAZE_ROD      => () => BlazeRod(data)
      case Material.BLUE_ICE       => () => null
      case Material.BLUE_ORCHID    => () => null
//            case Material.BONE                           => () => Bone(data)
//            case Material.BONE_BLOCK                     => () => BoneBlock(data)
//            case Material.BONE_MEAL                      => null
//            case Material.BOOK                           => null
//            case Material.BOOKSHELF                      => null
//            case Material.BOW                            => null
//            case Material.BOWL                           => null
//            case Material.BREAD                          => null
//            case Material.BREWING_STAND                  => null
//            case Material.BRICK                          => null
//            case Material.BRICKS                         => null
//            case Material.BROWN_MUSHROOM                 => null
//            case Material.BROWN_MUSHROOM_BLOCK           => null
//            case Material.BUCKET                         => null
//            case Material.CACTUS                         => null
//            case Material.CAKE                           => null
//            case Material.CAMPFIRE                       => null
      //      case Material.CARROT                         => null
      //      case Material.CARROT_ON_A_STICK              => null
      //      case Material.CARTOGRAPHY_TABLE              => null
      //      case Material.CARVED_PUMPKIN                 => null
      //      case Material.CAULDRON                       => null
      //      case Material.CHAINMAIL_BOOTS                => null
      //      case Material.CHAINMAIL_CHESTPLATE           => null
      //      case Material.CHAINMAIL_HELMET               => null
      //      case Material.CHAINMAIL_LEGGINGS             => null
      //      case Material.CHAIN_COMMAND_BLOCK            => null
      //      case Material.CHARCOAL                       => null
      //      case Material.CHEST                          => null
      //      case Material.CHEST_MINECART                 => null
      //      case Material.CHICKEN                        => null
      //      case Material.CHIPPED_ANVIL                  => null
      //      case Material.CHISELED_QUARTZ_BLOCK          => null
      //      case Material.CHISELED_RED_SANDSTONE         => null
      //      case Material.CHISELED_SANDSTONE             => null
      //      case Material.CHISELED_STONE_BRICKS          => null
      //      case Material.CHORUS_FLOWER                  => null
      //      case Material.CHORUS_FRUIT                   => null
      //      case Material.CHORUS_PLANT                   => null
      //      case Material.CLAY                           => null
      //      case Material.CLAY_BALL                      => null
      //      case Material.CLOCK                          => null
      //      case Material.COAL                           => null
      //      case Material.COAL_BLOCK                     => null
      //      case Material.COAL_ORE                       => null
      //      case Material.COARSE_DIRT                    => null
      //      case Material.COBBLESTONE                    => null
      //      case Material.COBWEB                         => null
      //      case Material.COCOA_BEANS                    => null
      //      case Material.COD                            => null
      //      case Material.COD_BUCKET                     => null
      //      case Material.COMMAND_BLOCK                  => null
      //      case Material.COMMAND_BLOCK_MINECART         => null
      //      case Material.COMPARATOR                     => null
      //      case Material.COMPASS                        => null
      //      case Material.COMPOSTER                      => null
      //      case Material.CONDUIT                        => null
      //      case Material.COOKED_BEEF                    => null
      //      case Material.COOKED_CHICKEN                 => null
      //      case Material.COOKED_COD                     => null
      //      case Material.COOKED_MUTTON                  => null
      //      case Material.COOKED_PORKCHOP                => null
      //      case Material.COOKED_RABBIT                  => null
      //      case Material.COOKED_SALMON                  => null
      //      case Material.COOKIE                         => null
      //      case Material.CORNFLOWER                     => null
      //      case Material.CRACKED_STONE_BRICKS           => null
      //      case Material.CRAFTING_TABLE                 => null
      //      case Material.CREEPER_BANNER_PATTERN         => null
      //      case Material.CREEPER_HEAD                   => null
      //      case Material.CROSSBOW                       => null
      //      case Material.CUT_RED_SANDSTONE              => null
      //      case Material.CUT_SANDSTONE                  => null
      //      case Material.DAMAGED_ANVIL                  => null
      //      case Material.DANDELION                      => null
      //      case Material.DARK_PRISMARINE                => null
      //      case Material.DAYLIGHT_DETECTOR              => null
      //      case Material.DEAD_BUSH                      => null
      //      case Material.DEBUG_STICK                    => null
      //      case Material.DIAMOND                        => null
      //      case Material.DIAMOND_AXE                    => null
      //      case Material.DIAMOND_BLOCK                  => null
      //      case Material.DIAMOND_BOOTS                  => null
      //      case Material.DIAMOND_CHESTPLATE             => null
      //      case Material.DIAMOND_HELMET                 => null
      //      case Material.DIAMOND_HOE                    => null
      //      case Material.DIAMOND_HORSE_ARMOR            => null
      //      case Material.DIAMOND_LEGGINGS               => null
      //      case Material.DIAMOND_ORE                    => null
      //      case Material.DIAMOND_PICKAXE                => null
      //      case Material.DIAMOND_SHOVEL                 => null
      //      case Material.DIAMOND_SWORD                  => null
      //      case Material.DIORITE                        => null
      //      case Material.DIRT                           => null
      //      case Material.DISPENSER                      => null
      //      case Material.DRAGON_BREATH                  => null
      //      case Material.DRAGON_EGG                     => null
      //      case Material.DRAGON_HEAD                    => null
      //      case Material.DRIED_KELP                     => null
      //      case Material.DRIED_KELP_BLOCK               => null
      //      case Material.DROPPER                        => null
      //      case Material.EGG                            => null
      //      case Material.ELYTRA                         => null
      //      case Material.EMERALD                        => null
      //      case Material.EMERALD_BLOCK                  => null
      //      case Material.EMERALD_ORE                    => null
      //      case Material.ENCHANTED_BOOK                 => null
      //      case Material.ENCHANTED_GOLDEN_APPLE         => null
      //      case Material.ENCHANTING_TABLE               => null
      //      case Material.ENDER_CHEST                    => null
      //      case Material.ENDER_EYE                      => null
      //      case Material.ENDER_PEARL                    => null
      //      case Material.END_CRYSTAL                    => null
      //      case Material.END_PORTAL_FRAME               => null
      //      case Material.END_ROD                        => null
      //      case Material.END_STONE                      => null
      //      case Material.END_STONE_BRICKS               => null
      //      case Material.EXPERIENCE_BOTTLE              => null
      //      case Material.FARMLAND                       => null
      //      case Material.FEATHER                        => null
      //      case Material.FERMENTED_SPIDER_EYE           => null
      //      case Material.FERN                           => null
      //      case Material.FILLED_MAP                     => null
      //      case Material.FIREWORK_ROCKET                => null
      //      case Material.FIREWORK_STAR                  => null
      //      case Material.FIRE_CHARGE                    => null
      //      case Material.FISHING_ROD                    => null
      //      case Material.FLETCHING_TABLE                => null
      //      case Material.FLINT                          => null
      //      case Material.FLINT_AND_STEEL                => null
      //      case Material.FLOWER_BANNER_PATTERN          => null
      //      case Material.FLOWER_POT                     => null
      //      case Material.FURNACE                        => null
      //      case Material.FURNACE_MINECART               => null
      //      case Material.GHAST_TEAR                     => null
      //      case Material.GLASS                          => null
      //      case Material.GLASS_BOTTLE                   => null
      //      case Material.GLASS_PANE                     => null
      //      case Material.GLISTERING_MELON_SLICE         => null
      //      case Material.GLOBE_BANNER_PATTERN           => null
      //      case Material.GLOWSTONE                      => null
      //      case Material.GLOWSTONE_DUST                 => null
      //      case Material.GOLDEN_APPLE                   => null
      //      case Material.GOLDEN_AXE                     => null
      //      case Material.GOLDEN_BOOTS                   => null
      //      case Material.GOLDEN_CARROT                  => null
      //      case Material.GOLDEN_CHESTPLATE              => null
      //      case Material.GOLDEN_HELMET                  => null
      //      case Material.GOLDEN_HOE                     => null
      //      case Material.GOLDEN_HORSE_ARMOR             => null
      //      case Material.GOLDEN_LEGGINGS                => null
      //      case Material.GOLDEN_PICKAXE                 => null
      //      case Material.GOLDEN_SHOVEL                  => null
      //      case Material.GOLDEN_SWORD                   => null
      //      case Material.GOLD_BLOCK                     => null
      //      case Material.GOLD_INGOT                     => null
      //      case Material.GOLD_NUGGET                    => null
      //      case Material.GOLD_ORE                       => null
      //      case Material.GRANITE                        => null
      //      case Material.GRASS                          => null
      //      case Material.GRASS_BLOCK                    => null
      //      case Material.GRASS_PATH                     => null
      //      case Material.GRAVEL                         => null
      //      case Material.GRINDSTONE                     => null
      //      case Material.GUNPOWDER                      => null
      //      case Material.HAY_BLOCK                      => null
      //      case Material.HEART_OF_THE_SEA               => null
      //      case Material.HEAVY_WEIGHTED_PRESSURE_PLATE  => null
      //      case Material.HOPPER                         => null
      //      case Material.HOPPER_MINECART                => null
      //      case Material.ICE                            => null
      //      case Material.INFESTED_CHISELED_STONE_BRICKS => null
      //      case Material.INFESTED_COBBLESTONE           => null
      //      case Material.INFESTED_CRACKED_STONE_BRICKS  => null
      //      case Material.INFESTED_MOSSY_STONE_BRICKS    => null
      //      case Material.INFESTED_STONE                 => null
      //      case Material.INFESTED_STONE_BRICKS          => null
      //      case Material.INK_SAC                        => null
      //      case Material.IRON_AXE                       => null
      //      case Material.IRON_BARS                      => null
      //      case Material.IRON_BLOCK                     => null
      //      case Material.IRON_BOOTS                     => null
      //      case Material.IRON_CHESTPLATE                => null
      //      case Material.IRON_HELMET                    => null
      //      case Material.IRON_HOE                       => null
      //      case Material.IRON_HORSE_ARMOR               => null
      //      case Material.IRON_INGOT                     => null
      //      case Material.IRON_LEGGINGS                  => null
      //      case Material.IRON_NUGGET                    => null
      //      case Material.IRON_ORE                       => null
      //      case Material.IRON_PICKAXE                   => null
      //      case Material.IRON_SHOVEL                    => null
      //      case Material.IRON_SWORD                     => null
      //      case Material.ITEM_FRAME                     => null
      //      case Material.JACK_O_LANTERN                 => null
      //      case Material.JIGSAW                         => null
      //      case Material.JUKEBOX                        => null
      //      case Material.KELP                           => null
      //      case Material.KNOWLEDGE_BOOK                 => null
      //      case Material.LADDER                         => null
      //      case Material.LANTERN                        => null
      //      case Material.LAPIS_BLOCK                    => null
      //      case Material.LAPIS_LAZULI                   => null
      //      case Material.LAPIS_ORE                      => null
      //      case Material.LARGE_FERN                     => null
      //      case Material.LAVA_BUCKET                    => null
      //      case Material.LEAD                           => null
      //      case Material.LEATHER                        => null
      //      case Material.LEATHER_BOOTS                  => null
      //      case Material.LEATHER_CHESTPLATE             => null
      //      case Material.LEATHER_HELMET                 => null
      //      case Material.LEATHER_HORSE_ARMOR            => null
      //      case Material.LEATHER_LEGGINGS               => null
      //      case Material.LECTERN                        => null
      //      case Material.LEVER                          => null
      //      case Material.LIGHT_WEIGHTED_PRESSURE_PLATE  => null
      //      case Material.LILAC                          => null
      //      case Material.LILY_OF_THE_VALLEY             => null
      //      case Material.LILY_PAD                       => null
      //      case Material.LINGERING_POTION               => null
      //      case Material.LOOM                           => null
      //      case Material.MAGMA_BLOCK                    => null
      //      case Material.MAGMA_CREAM                    => null
      //      case Material.MAP                            => null
      //      case Material.MELON                          => null
      //      case Material.MELON_SEEDS                    => null
      //      case Material.MELON_SLICE                    => null
      //      case Material.MILK_BUCKET                    => null
      //      case Material.MINECART                       => null
      //      case Material.MOJANG_BANNER_PATTERN          => null
      //      case Material.MOSSY_COBBLESTONE              => null
      //      case Material.MOSSY_STONE_BRICKS             => null
      //      case Material.MUSHROOM_STEM                  => null
      //      case Material.MUSHROOM_STEW                  => null
      //      case Material.MUSIC_DISC_11                  => null
      //      case Material.MUSIC_DISC_13                  => null
      //      case Material.MUSIC_DISC_BLOCKS              => null
      //      case Material.MUSIC_DISC_CAT                 => null
      //      case Material.MUSIC_DISC_CHIRP               => null
      //      case Material.MUSIC_DISC_FAR                 => null
      //      case Material.MUSIC_DISC_MALL                => null
      //      case Material.MUSIC_DISC_MELLOHI             => null
      //      case Material.MUSIC_DISC_STAL                => null
      //      case Material.MUSIC_DISC_STRAD               => null
      //      case Material.MUSIC_DISC_WAIT                => null
      //      case Material.MUSIC_DISC_WARD                => null
      //      case Material.MUTTON                         => null
      //      case Material.MYCELIUM                       => null
      //      case Material.NAME_TAG                       => null
      //      case Material.NAUTILUS_SHELL                 => null
      //      case Material.NETHERRACK                     => null
      //      case Material.NETHER_BRICK                   => null
      //      case Material.NETHER_BRICKS                  => null
      //      case Material.NETHER_QUARTZ_ORE              => null
      //      case Material.NETHER_STAR                    => null
      //      case Material.NETHER_WART                    => null
      //      case Material.NETHER_WART_BLOCK              => null
      //      case Material.NOTE_BLOCK                     => null
      //      case Material.OBSERVER                       => null
      //      case Material.OBSIDIAN                       => null
      //      case Material.ORANGE_TULIP                   => null
      //      case Material.OXEYE_DAISY                    => null
      //      case Material.PACKED_ICE                     => null
      //      case Material.PAINTING                       => null
      //      case Material.PAPER                          => null
      //      case Material.PEONY                          => null
      //      case Material.PETRIFIED_OAK_SLAB             => null
      //      case Material.PHANTOM_MEMBRANE               => null
      //      case Material.PINK_TULIP                     => null
      //      case Material.PISTON                         => null
      //      case Material.PLAYER_HEAD                    => null
      //      case Material.PODZOL                         => null
      //      case Material.POISONOUS_POTATO               => null
      //      case Material.POLISHED_ANDESITE              => null
      //      case Material.POLISHED_DIORITE               => null
      //      case Material.POLISHED_GRANITE               => null
      //      case Material.POPPED_CHORUS_FRUIT            => null
      //      case Material.POPPY                          => null
      //      case Material.PORKCHOP                       => null
      //      case Material.POTATO                         => null
      //      case Material.POTION                         => null
      //      case Material.PRISMARINE                     => null
      //      case Material.PRISMARINE_BRICKS              => null
      //      case Material.PRISMARINE_CRYSTALS            => null
      //      case Material.PRISMARINE_SHARD               => null
      //      case Material.PUFFERFISH                     => null
      //      case Material.PUFFERFISH_BUCKET              => null
      //      case Material.PUMPKIN                        => null
      //      case Material.PUMPKIN_PIE                    => null
      //      case Material.PUMPKIN_SEEDS                  => null
      //      case Material.PURPUR_BLOCK                   => null
      //      case Material.PURPUR_PILLAR                  => null
      //      case Material.QUARTZ                         => null
      //      case Material.QUARTZ_BLOCK                   => null
      //      case Material.QUARTZ_PILLAR                  => null
      //      case Material.RABBIT                         => null
      //      case Material.RABBIT_FOOT                    => null
      //      case Material.RABBIT_HIDE                    => null
      //      case Material.RABBIT_STEW                    => null
      //      case Material.REDSTONE                       => null
      //      case Material.REDSTONE_BLOCK                 => null
      //      case Material.REDSTONE_LAMP                  => null
      //      case Material.REDSTONE_ORE                   => null
      //      case Material.REDSTONE_TORCH                 => null
      //      case Material.RED_MUSHROOM                   => null
      //      case Material.RED_MUSHROOM_BLOCK             => null
      //      case Material.RED_NETHER_BRICKS              => null
      //      case Material.RED_SAND                       => null
      //      case Material.RED_SANDSTONE                  => null
      //      case Material.RED_TULIP                      => null
      //      case Material.REPEATER                       => null
      //      case Material.REPEATING_COMMAND_BLOCK        => null
      //      case Material.ROSE_BUSH                      => null
      //      case Material.ROTTEN_FLESH                   => null
      //      case Material.SADDLE                         => null
      //      case Material.SALMON                         => null
      //      case Material.SALMON_BUCKET                  => null
      //      case Material.SAND                           => null
      //      case Material.SANDSTONE                      => null
      //      case Material.SCAFFOLDING                    => null
      //      case Material.SCUTE                          => null
      //      case Material.SEAGRASS                       => null
      //      case Material.SEA_LANTERN                    => null
      //      case Material.SEA_PICKLE                     => null
      //      case Material.SHEARS                         => null
      //      case Material.SHIELD                         => null
      //      case Material.SHULKER_BOX                    => null
      //      case Material.SHULKER_SHELL                  => null
      //      case Material.SKELETON_SKULL                 => null
      //      case Material.SKULL_BANNER_PATTERN           => null
      //      case Material.SLIME_BALL                     => null
      //      case Material.SLIME_BLOCK                    => null
      //      case Material.SMITHING_TABLE                 => null
      //      case Material.SMOKER                         => null
      //      case Material.SMOOTH_QUARTZ                  => null
      //      case Material.SMOOTH_RED_SANDSTONE           => null
      //      case Material.SMOOTH_SANDSTONE               => null
      //      case Material.SMOOTH_STONE                   => null
      //      case Material.SNOW                           => null
      //      case Material.SNOWBALL                       => null
      //      case Material.SNOW_BLOCK                     => null
      //      case Material.SOUL_SAND                      => null
      //      case Material.SPAWNER                        => null
      //      case Material.SPECTRAL_ARROW                 => null
      //      case Material.SPIDER_EYE                     => null
      //      case Material.SPLASH_POTION                  => null
      //      case Material.SPONGE                         => null
      //      case Material.STICK                          => null
      //      case Material.STICKY_PISTON                  => null
      //      case Material.STONE                          => null
      //      case Material.STONECUTTER                    => null
      //      case Material.STONE_AXE                      => null
      //      case Material.STONE_BRICKS                   => null
      //      case Material.STONE_HOE                      => null
      //      case Material.STONE_PICKAXE                  => null
      //      case Material.STONE_PRESSURE_PLATE           => null
      //      case Material.STONE_SHOVEL                   => null
      //      case Material.STONE_SWORD                    => null
      //      case Material.STRING                         => null
      //      case Material.STRUCTURE_BLOCK                => null
      //      case Material.STRUCTURE_VOID                 => null
      //      case Material.SUGAR                          => null
      //      case Material.SUGAR_CANE                     => null
      //      case Material.SUNFLOWER                      => null
      //      case Material.SUSPICIOUS_STEW                => null
      //      case Material.SWEET_BERRIES                  => null
      //      case Material.TALL_GRASS                     => null
      //      case Material.TERRACOTTA                     => null
      //      case Material.TIPPED_ARROW                   => null
      //      case Material.TNT                            => null
      //      case Material.TNT_MINECART                   => null
      //      case Material.TORCH                          => null
      //      case Material.TOTEM_OF_UNDYING               => null
      //      case Material.TRAPPED_CHEST                  => null
      //      case Material.TRIDENT                        => null
      //      case Material.TRIPWIRE_HOOK                  => null
      //      case Material.TROPICAL_FISH                  => null
      //      case Material.TROPICAL_FISH_BUCKET           => null
      //      case Material.TURTLE_EGG                     => null
      //      case Material.TURTLE_HELMET                  => null
      //      case Material.VINE                           => null
      //      case Material.WATER_BUCKET                   => null
      //      case Material.WET_SPONGE                     => null
      //      case Material.WHEAT                          => null
      //      case Material.WHEAT_SEEDS                    => null
      //      case Material.WHITE_TULIP                    => null
      //      case Material.WITHER_ROSE                    => null
      //      case Material.WITHER_SKELETON_SKULL          => null
      //      case Material.WOODEN_AXE                     => null
      //      case Material.WOODEN_HOE                     => null
      //      case Material.WOODEN_PICKAXE                 => null
      //      case Material.WOODEN_SHOVEL                  => null
      //      case Material.WOODEN_SWORD                   => null
      //      case Material.WRITABLE_BOOK                  => null
      //      case Material.WRITTEN_BOOK                   => null
      //      case Material.ZOMBIE_HEAD                    => null

      case _ endsWith "BANNER"            => () => Banner(color, data)
      case _ endsWith "BED"               => () => Bed(color, data)
      case _ endsWith "BOAT"              => () => Boat(material[WoodMaterial], data)
      case _ endsWith "BUTTON"            => () => Button(material[ButtonMaterial], data)
      case _ endsWith "CARPET"            => () => Carpet(color, data)
      case _ endsWith "CONCRETE"          => () => Concrete(color, data)
      case _ endsWith "CONCRETE_POWDER"   => () => ConcretePowder(color, data)
      case _ endsWith "CORAL"             => () => Coral(variant[CoralVariant], data)
      case _ endsWith "CORAL_BLOCK"       => () => CoralBlock(variant[CoralVariant], data)
      case _ endsWith "CORAL_FAN"         => () => CoralFan(variant[CoralVariant], data)
      case _ endsWith "DOOR"              => () => Door(material[DoorMaterial], data)
      case _ endsWith "DYE"               => () => Dye(color, data)
      case _ endsWith "FENCE"             => () => Fence(material[FenceMaterial], data)
      case _ endsWith "GATE"              => () => Gate(material[WoodMaterial], data)
      case _ endsWith "GLAZED_TERRACOTTA" => () => GlazedTerracotta(color, data) // TODO merge GlazedTerracotta and Terracotta like Log and Wood with stripped?
      case _ endsWith "LEAVES"            => () => Leaves(material[WoodMaterial], data) // TODO or split Log and Wood into StrippedLog and StrippedWood instead?
      case _ endsWith "PLANKS"            => () => Planks(material[WoodMaterial], data)
      case _ endsWith "RAIL"              => () => Rails(variant[RailsVariant], data) // TODO rename Rail?
      case _ endsWith "SAPLING"           => () => Sapling(variant[SaplingVariant], data) // TODO SaplingVariant.BAMBOO is illegal on an item
      case _ endsWith "SHULKER_BOX"       => () => ShulkerBox(Some(color), data)
      case _ endsWith "SIGN"              => () => Sign(material[WoodMaterial], data)
      case _ endsWith "SPAWN_EGG"         => () => SpawnEgg(variant[SpawnEggVariant], data)
      case _ endsWith "GLASS"             => () => Glass(Some(color), data)
      case _ endsWith "GLASS_PANE"        => () => GlassPane(Some(color), data)
      case _ endsWith "TERRACOTTA"        => () => Terracotta(Some(color), data)
      case _ endsWith "TRAPDOOR"          => () => TrapDoor(material[TrapdoorMaterial], data)
      case _ endsWith "WOOL"              => () => Wool(color, data)

      // LOG
      case _ is ("STRIPPED", "LOG") => () =>
        Log(material[WoodMaterial], stripped = true, data)

      case _ endsWith "LOG" => () =>
        Log(material[WoodMaterial], stripped = false, data)

      // PRESSURE_PLATE
      case _ endsWith "PRESSURE_PLATE" => () =>
        PressurePlate(material[PressurePlateMaterial], data)

      // SLAB
      case _ endsWith "SLAB" => () =>
        Slab(material[SlabMaterial], Some(variant[SlabVariant]), data)

      // STAIRS
      case _ endsWith "STAIRS" => () =>
        Stairs(material[StairsMaterial], Some(variant[StairsVariant]), data)

      // WALL
      case _ endsWith "WALL" => () =>
        Wall(material[WallMaterial], Some(variant[WallVariant]), data)

      // WOOD
      case _ is ("STRIPPED", "WOOD") => () =>
        Wood(material[WoodMaterial], stripped = true, data)

      case _ endsWith "WOOD" => () =>
        Wood(material[WoodMaterial], stripped = false, data)

      case _ => throw new IllegalArgumentException(s"${ itemStack.getType }")
    })

    ItemStack(itemBuilder(), itemStack.getAmount)
  }

  def map(item: ItemStack): SpigotItemStack = {
    val spigotItem: SpigotItemStack = item match {
      case _ => null
    }

    spigotItem.setAmount(item.size)
    spigotItem
  }
}
