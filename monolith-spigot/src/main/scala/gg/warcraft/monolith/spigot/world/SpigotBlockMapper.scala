package gg.warcraft.monolith.spigot.world

import com.google.inject.Inject
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.spigot.world.block.SpigotBlockFaceMapper
import org.bukkit.block.data.Bisected.{Half => SpigotBisection}
import org.bukkit.block.data._
import org.bukkit.block.data.`type`.{Jukebox => SpigotJukebox, Lectern => SpigotLectern, TNT => SpigotTNT}
import org.bukkit.block.{Block => SpigotBlock, BlockFace => SpigotBlockFace}
import org.bukkit.{Axis, Material}

class SpigotBlockMapper @Inject()(
  private val worldMapper: SpigotWorldMapper,
  private val locationMapper: SpigotLocationMapper,
  private val materialMapper: SpigotMaterialMapper,
  private val blockFaceMapper: SpigotBlockFaceMapper,
  private val directionMapper: SpigotDirectionMapper
) {

  def map(block: Block): SpigotBlock = locationMapper.map(block.location).getBlock

  def map(block: SpigotBlock): Block = {
    val location = locationMapper.map(block.getLocation).toBlockLocation
    lazy val color = { materialMapper.mapColor(block.getType) }
    lazy val facing = { mapFace(block.getState.asInstanceOf[Directional].getFacing) }
    lazy val lit = { block.getState.asInstanceOf[Lightable].isLit }
    lazy val material = { materialMapper.map(block.getType) }
    lazy val open = { block.getState.asInstanceOf[Openable].isOpen }
    lazy val orientation = { mapOrientation(block.getState.asInstanceOf[Orientable].getAxis) }
    lazy val potted = { block.getType.name.startsWith("POTTED_") }
    lazy val powered = { block.getState.asInstanceOf[Powerable].isPowered }
    lazy val section = { mapBisection(block.getState.asInstanceOf[Bisected].getHalf) }
    lazy val state = { materialMapper.mapState(block.getType) }
    lazy val stripped = { block.getType.name.startsWith("STRIPPED_") }
    lazy val wet = { block.getState.asInstanceOf[Waterlogged].isWaterlogged }
    block.getType match {
      case Material.ACTIVATOR_RAIL => null
      case Material.AIR => Air(location)
      case Material.ANDESITE => null
      case Material.ANDESITE_WALL => null

      // ANVIL
      case Material.ANVIL | Material.CHIPPED_ANVIL | Material.DAMAGED_ANVIL =>
        Anvil(location, state.asInstanceOf[AnvilState], facing)

      case Material.BAMBOO => null
      case Material.BAMBOO_SAPLING => null

      // BANNER
      case Material.BLACK_BANNER | Material.BLACK_WALL_BANNER |
           Material.BLUE_BANNER | Material.BLUE_WALL_BANNER |
           Material.BROWN_BANNER | Material.BROWN_WALL_BANNER |
           Material.CYAN_BANNER | Material.CYAN_WALL_BANNER |
           Material.GRAY_BANNER | Material.GRAY_WALL_BANNER |
           Material.GREEN_BANNER | Material.GREEN_WALL_BANNER |
           Material.LIGHT_BLUE_BANNER | Material.LIGHT_BLUE_WALL_BANNER |
           Material.LIGHT_GRAY_BANNER | Material.LIGHT_GRAY_WALL_BANNER |
           Material.LIME_BANNER | Material.LIME_WALL_BANNER |
           Material.MAGENTA_BANNER | Material.MAGENTA_WALL_BANNER |
           Material.ORANGE_BANNER | Material.ORANGE_WALL_BANNER |
           Material.PINK_BANNER | Material.PINK_WALL_BANNER |
           Material.PURPLE_BANNER | Material.PURPLE_WALL_BANNER |
           Material.RED_BANNER | Material.RED_WALL_BANNER |
           Material.WHITE_BANNER | Material.WHITE_WALL_BANNER |
           Material.YELLOW_BANNER | Material.YELLOW_WALL_BANNER =>
        null

      case Material.BARREL => Barrel(location, facing, open)
      case Material.BARRIER => Barrier(location)
      case Material.BEACON => Beacon(location)

      // BED
      case Material.BLACK_BED | Material.BLUE_BED | Material.BROWN_BED | Material.CYAN_BED |
           Material.GRAY_BED | Material.GREEN_BED | Material.LIGHT_BLUE_BED | Material.LIGHT_GRAY_BED |
           Material.LIME_BED | Material.MAGENTA_BED | Material.ORANGE_BED | Material.PINK_BED |
           Material.PURPLE_BED | Material.RED_BED | Material.WHITE_BED | Material.YELLOW_BED =>
        Bed(location, color, facing, section)

      case Material.BEDROCK => Bedrock(location)
      case Material.BEETROOTS => null
      case Material.BELL => Bell(location, facing)
      case Material.BLAST_FURNACE => BlastFurnace(location, facing, lit)
      case Material.BLUE_ICE => null
      case Material.BONE_BLOCK => Bone(location, orientation)
      case Material.BOOKSHELF => Bookshelf(location)
      case Material.BREWING_STAND => BrewingStand(location)
      case Material.BRICKS => null
      case Material.BRICK_WALL => null
      case Material.BROWN_MUSHROOM => null
      case Material.BROWN_MUSHROOM_BLOCK => null
      case Material.BUBBLE_COLUMN => null

      // BUTTON
      case Material.ACACIA_BUTTON | Material.BIRCH_BUTTON | Material.DARK_OAK_BUTTON |
           Material.JUNGLE_BUTTON | Material.OAK_BUTTON | Material.SPRUCE_BUTTON =>
        null

      case Material.CACTUS => null
      case Material.CAKE => null
      case Material.CAMPFIRE => null

      // CARPET
      case Material.BLACK_CARPET | Material.BLUE_CARPET | Material.BROWN_CARPET | Material.CYAN_CARPET |
           Material.GRAY_CARPET | Material.GREEN_CARPET | Material.LIGHT_BLUE_CARPET | Material.LIGHT_GRAY_CARPET |
           Material.LIME_CARPET | Material.MAGENTA_CARPET | Material.ORANGE_CARPET | Material.PINK_CARPET |
           Material.PURPLE_CARPET | Material.RED_CARPET | Material.WHITE_CARPET | Material.YELLOW_CARPET =>
        val color = materialMapper.mapColor(block.getType)
        Carpet(location, color)

      case Material.CARROTS => null
      case Material.CARTOGRAPHY_TABLE => CartographyTable(location)
      case Material.CARVED_PUMPKIN => null
      case Material.CAULDRON => null
      case Material.CAVE_AIR => null
      case Material.CHAIN_COMMAND_BLOCK => null
      case Material.CHEST => null
      case Material.CHISELED_QUARTZ_BLOCK => null
      case Material.CHISELED_STONE_BRICKS => null
      case Material.CHORUS_FLOWER => null
      case Material.CHORUS_PLANT => null
      case Material.CLAY => null
      case Material.COARSE_DIRT => null
      case Material.COBBLESTONE => null
      case Material.COBBLESTONE_WALL => null
      case Material.COBWEB => Cobweb(location)
      case Material.COCOA => null
      case Material.COMMAND_BLOCK => CommandBlock(location)
      case Material.COMPARATOR => null
      case Material.COMPOSTER => null

      // CONCRETE
      case Material.BLACK_CONCRETE | Material.BLUE_CONCRETE | Material.BROWN_CONCRETE | Material.CYAN_CONCRETE |
           Material.GRAY_CONCRETE | Material.GREEN_CONCRETE | Material.LIGHT_BLUE_CONCRETE | Material.LIGHT_GRAY_CONCRETE |
           Material.LIME_CONCRETE | Material.MAGENTA_CONCRETE | Material.ORANGE_CONCRETE | Material.PINK_CONCRETE |
           Material.PURPLE_CONCRETE | Material.RED_CONCRETE | Material.WHITE_CONCRETE | Material.YELLOW_CONCRETE =>
        Concrete(location, color)

      // CONCRETE_POWDER
      case Material.BLACK_CONCRETE_POWDER | Material.BLUE_CONCRETE_POWDER | Material.BROWN_CONCRETE_POWDER |
           Material.CYAN_CONCRETE_POWDER | Material.GRAY_CONCRETE_POWDER | Material.GREEN_CONCRETE_POWDER |
           Material.LIGHT_BLUE_CONCRETE_POWDER | Material.LIGHT_GRAY_CONCRETE_POWDER | Material.LIME_CONCRETE_POWDER |
           Material.MAGENTA_CONCRETE_POWDER | Material.ORANGE_CONCRETE_POWDER | Material.PINK_CONCRETE_POWDER |
           Material.PURPLE_CONCRETE_POWDER | Material.RED_CONCRETE_POWDER | Material.WHITE_CONCRETE_POWDER |
           Material.YELLOW_CONCRETE_POWDER =>
        ConcretePowder(location, color)

      case Material.CONDUIT => Conduit(location, wet)

      // CORAL
      case Material.BRAIN_CORAL | Material.BUBBLE_CORAL | Material.FIRE_CORAL |
           Material.HORN_CORAL | Material.TUBE_CORAL |

           Material.DEAD_BRAIN_CORAL | Material.DEAD_BUBBLE_CORAL | Material.DEAD_FIRE_CORAL |
           Material.DEAD_HORN_CORAL | Material.DEAD_TUBE_CORAL =>
        null

      // CORAL_BLOCK
      case Material.BRAIN_CORAL_BLOCK | Material.DEAD_BRAIN_CORAL_BLOCK |
           Material.BUBBLE_CORAL_BLOCK | Material.DEAD_BUBBLE_CORAL_BLOCK |
           Material.FIRE_CORAL_BLOCK | Material.DEAD_FIRE_CORAL_BLOCK |
           Material.HORN_CORAL_BLOCK | Material.DEAD_HORN_CORAL_BLOCK |
           Material.TUBE_CORAL_BLOCK | Material.DEAD_TUBE_CORAL_BLOCK =>
        null

      // CORAL_FAN
      case Material.BRAIN_CORAL_FAN | Material.DEAD_BRAIN_CORAL_FAN |
           Material.BUBBLE_CORAL_FAN | Material.DEAD_BUBBLE_CORAL_FAN |
           Material.FIRE_CORAL_FAN | Material.DEAD_FIRE_CORAL_FAN |
           Material.HORN_CORAL_FAN | Material.DEAD_HORN_CORAL_FAN |
           Material.TUBE_CORAL_FAN | Material.DEAD_TUBE_CORAL_FAN =>
        null

      // CORAL_WALL_FAN
      case Material.BRAIN_CORAL_WALL_FAN | Material.DEAD_BRAIN_CORAL_WALL_FAN |
           Material.BUBBLE_CORAL_WALL_FAN | Material.DEAD_BUBBLE_CORAL_WALL_FAN |
           Material.FIRE_CORAL_WALL_FAN | Material.DEAD_FIRE_CORAL_WALL_FAN |
           Material.HORN_CORAL_WALL_FAN | Material.DEAD_HORN_CORAL_WALL_FAN |
           Material.TUBE_CORAL_WALL_FAN | Material.DEAD_TUBE_CORAL_WALL_FAN =>
        null

      case Material.CRACKED_STONE_BRICKS => null
      case Material.CRAFTING_TABLE => CraftingTable(location)
      case Material.DARK_PRISMARINE => null
      case Material.DAYLIGHT_DETECTOR => DaylightDetector(location)
      case Material.DEAD_BUSH => null
      case Material.DETECTOR_RAIL => null
      case Material.DIORITE => null
      case Material.DIORITE_WALL => null
      case Material.DIRT => Dirt(location)
      case Material.DISPENSER => null

      // DOOR
      case Material.ACACIA_DOOR | Material.BIRCH_DOOR | Material.DARK_OAK_DOOR |
           Material.JUNGLE_DOOR | Material.OAK_DOOR | Material.SPRUCE_DOOR =>
        null

      case Material.DRAGON_EGG => null
      case Material.DRIED_KELP_BLOCK => DriedKelp(location)
      case Material.DROPPER => null
      case Material.ENCHANTING_TABLE => EnchantingTable(location)
      case Material.ENDER_CHEST => null
      case Material.END_GATEWAY => null
      case Material.END_PORTAL => null
      case Material.END_PORTAL_FRAME => null
      case Material.END_ROD => EndRod(location, facing)
      case Material.END_STONE => null
      case Material.END_STONE_BRICKS => null
      case Material.END_STONE_BRICK_WALL => null
      case Material.FARMLAND => Farmland(location)

      // FENCE
      case Material.ACACIA_FENCE | Material.BIRCH_FENCE | Material.DARK_OAK_FENCE |
           Material.JUNGLE_FENCE | Material.OAK_FENCE | Material.SPRUCE_FENCE =>
        null

      // FENCE_GATE
      case Material.ACACIA_FENCE_GATE | Material.BIRCH_FENCE_GATE | Material.DARK_OAK_FENCE_GATE |
           Material.JUNGLE_FENCE_GATE | Material.OAK_FENCE_GATE | Material.SPRUCE_FENCE_GATE =>
        null

      case Material.FERN => null
      case Material.FIRE => Fire(location)
      case Material.FLETCHING_TABLE => FletchingTable(location)

      // FLOWER
      case Material.ALLIUM | Material.POTTED_ALLIUM |
           Material.AZURE_BLUET | Material.POTTED_AZURE_BLUET |
           Material.BLUE_ORCHID | Material.POTTED_BLUE_ORCHID |
           Material.CORNFLOWER | Material.POTTED_CORNFLOWER |
           Material.DANDELION | Material.POTTED_DANDELION |
           Material.LILY_OF_THE_VALLEY | Material.POTTED_LILY_OF_THE_VALLEY |
           Material.ORANGE_TULIP | Material.POTTED_ORANGE_TULIP |
           Material.OXEYE_DAISY | Material.POTTED_OXEYE_DAISY |
           Material.PINK_TULIP | Material.POTTED_PINK_TULIP |
           Material.POPPY | Material.POTTED_POPPY |
           Material.RED_TULIP | Material.POTTED_RED_TULIP |
           Material.WHITE_TULIP | Material.POTTED_WHITE_TULIP |
           Material.WITHER_ROSE | Material.POTTED_WITHER_ROSE =>
        Flower(location, material.asInstanceOf[FlowerMaterial], potted)

      case Material.FLOWER_POT => null
      case Material.FROSTED_ICE => null
      case Material.FURNACE => Furnace(location, facing, lit)

      // GLASS
      case Material.GLASS |
           Material.BLACK_STAINED_GLASS | Material.BLUE_STAINED_GLASS | Material.BROWN_STAINED_GLASS | Material.CYAN_STAINED_GLASS |
           Material.GRAY_STAINED_GLASS | Material.GREEN_STAINED_GLASS | Material.LIGHT_BLUE_STAINED_GLASS | Material.LIGHT_GRAY_STAINED_GLASS |
           Material.LIME_STAINED_GLASS | Material.MAGENTA_STAINED_GLASS | Material.ORANGE_STAINED_GLASS | Material.PINK_STAINED_GLASS |
           Material.PURPLE_STAINED_GLASS | Material.RED_STAINED_GLASS | Material.WHITE_STAINED_GLASS | Material.YELLOW_STAINED_GLASS =>
        Glass(location, Option(color))

      // GLASS_PANE
      case Material.GLASS_PANE |
           Material.BLACK_STAINED_GLASS_PANE | Material.BLUE_STAINED_GLASS_PANE | Material.BROWN_STAINED_GLASS_PANE | Material.CYAN_STAINED_GLASS_PANE |
           Material.GRAY_STAINED_GLASS_PANE | Material.GREEN_STAINED_GLASS_PANE | Material.LIGHT_BLUE_STAINED_GLASS_PANE | Material.LIGHT_GRAY_STAINED_GLASS_PANE |
           Material.LIME_STAINED_GLASS_PANE | Material.MAGENTA_STAINED_GLASS_PANE | Material.ORANGE_STAINED_GLASS_PANE | Material.PINK_STAINED_GLASS_PANE |
           Material.PURPLE_STAINED_GLASS_PANE | Material.RED_STAINED_GLASS_PANE | Material.WHITE_STAINED_GLASS_PANE | Material.YELLOW_STAINED_GLASS_PANE =>
        GlassPane(location, Option(color)) // TODO add orientation

      // GLAZED_TERRACOTTA
      case Material.BLACK_GLAZED_TERRACOTTA | Material.BLUE_GLAZED_TERRACOTTA | Material.BROWN_GLAZED_TERRACOTTA | Material.CYAN_GLAZED_TERRACOTTA |
           Material.GRAY_GLAZED_TERRACOTTA | Material.GREEN_GLAZED_TERRACOTTA | Material.LIGHT_BLUE_GLAZED_TERRACOTTA | Material.LIGHT_GRAY_GLAZED_TERRACOTTA |
           Material.LIME_GLAZED_TERRACOTTA | Material.MAGENTA_GLAZED_TERRACOTTA | Material.ORANGE_GLAZED_TERRACOTTA | Material.PINK_GLAZED_TERRACOTTA |
           Material.PURPLE_GLAZED_TERRACOTTA | Material.RED_GLAZED_TERRACOTTA | Material.WHITE_GLAZED_TERRACOTTA | Material.YELLOW_GLAZED_TERRACOTTA =>
        GlazedTerracotta(location, color)

      case Material.GLOWSTONE => Glowstone(location)
      case Material.GRANITE => null
      case Material.GRANITE_WALL => null
      case Material.GRASS => null
      case Material.GRASS_BLOCK => null
      case Material.GRASS_PATH => null
      case Material.GRAVEL => null
      case Material.GRINDSTONE => null
      case Material.HAY_BLOCK => HayBale(location, orientation)
      case Material.HOPPER => null
      case Material.ICE => null
      case Material.INFESTED_CHISELED_STONE_BRICKS => null
      case Material.INFESTED_COBBLESTONE => null
      case Material.INFESTED_CRACKED_STONE_BRICKS => null
      case Material.INFESTED_MOSSY_STONE_BRICKS => null
      case Material.INFESTED_STONE => null
      case Material.INFESTED_STONE_BRICKS => null
      case Material.IRON_BARS => null
      case Material.IRON_DOOR => null
      case Material.IRON_TRAPDOOR => null
      case Material.JACK_O_LANTERN => null
      case Material.JIGSAW => Jigsaw(location, facing)
      case Material.JUKEBOX =>
        val record = block.getState.asInstanceOf[SpigotJukebox].hasRecord
        Jukebox(location, record)
      case Material.KELP => null
      case Material.KELP_PLANT => null
      case Material.LADDER => null
      case Material.LANTERN => null
      case Material.LARGE_FERN => null
      case Material.LAVA => null

      // LEAVES
      case Material.ACACIA_LEAVES | Material.BIRCH_LEAVES | Material.DARK_OAK_LEAVES |
           Material.JUNGLE_LEAVES | Material.OAK_LEAVES | Material.SPRUCE_LEAVES =>
        Leaves(location, material.asInstanceOf[WoodMaterial])

      case Material.LECTERN =>
        val book = block.getState.asInstanceOf[SpigotLectern].hasBook
        Lectern(location, facing, powered, book)
      case Material.LEVER => null
      case Material.LILAC => null
      case Material.LILY_PAD => LilyPad(location)

      // LOG
      case Material.ACACIA_LOG | Material.STRIPPED_ACACIA_LOG |
           Material.BIRCH_LOG | Material.STRIPPED_BIRCH_LOG |
           Material.DARK_OAK_LOG | Material.STRIPPED_DARK_OAK_LOG |
           Material.JUNGLE_LOG | Material.STRIPPED_JUNGLE_LOG |
           Material.OAK_LOG | Material.STRIPPED_OAK_LOG |
           Material.SPRUCE_LOG | Material.STRIPPED_SPRUCE_LOG =>
        Log(location, orientation, stripped)

      case Material.LOOM => Loom(location, facing)
      case Material.MAGMA_BLOCK => Magma(location)
      case Material.MELON => Melon(location)
      case Material.MELON_STEM | Material.ATTACHED_MELON_STEM => null

      // MINERAL
      case Material.COAL_BLOCK | Material.DIAMOND_BLOCK | Material.EMERALD_BLOCK | Material.GOLD_BLOCK |
           Material.IRON_BLOCK | Material.LAPIS_BLOCK | Material.QUARTZ_BLOCK | Material.REDSTONE_BLOCK =>
        Mineral(location, material.asInstanceOf[MineralMaterial])

      // MOB_HEAD
      case Material.CREEPER_HEAD | Material.CREEPER_WALL_HEAD |
           Material.DRAGON_HEAD | Material.DRAGON_WALL_HEAD |
           Material.PLAYER_HEAD | Material.PLAYER_WALL_HEAD |
           Material.SKELETON_SKULL | Material.SKELETON_WALL_SKULL |
           Material.WITHER_SKELETON_SKULL | Material.WITHER_SKELETON_WALL_SKULL |
           Material.ZOMBIE_HEAD | Material.ZOMBIE_WALL_HEAD =>
        MobHead(location, material.asInstanceOf[MobHeadMaterial]) // TODO map WALL MobHeads / attachedTo

      case Material.MOSSY_COBBLESTONE => null
      case Material.MOSSY_COBBLESTONE_WALL => null
      case Material.MOSSY_STONE_BRICKS => null
      case Material.MOSSY_STONE_BRICK_WALL => null
      case Material.MOVING_PISTON => null
      case Material.MUSHROOM_STEM => null
      case Material.MYCELIUM => null
      case Material.NETHERRACK => null
      case Material.NETHER_BRICKS => null
      case Material.NETHER_BRICK_FENCE => null
      case Material.NETHER_BRICK_WALL => null
      case Material.NETHER_PORTAL => null
      case Material.NETHER_WART => null
      case Material.NETHER_WART_BLOCK => null
      case Material.NOTE_BLOCK => null
      case Material.OBSERVER => Observer(location, facing, powered)
      case Material.OBSIDIAN => Obsidian(location)

      // ORE
      case Material.COAL_ORE | Material.DIAMOND_ORE | Material.EMERALD_ORE | Material.GOLD_ORE |
           Material.IRON_ORE | Material.LAPIS_ORE | Material.NETHER_QUARTZ_ORE | Material.REDSTONE_ORE =>
        Ore(location, material.asInstanceOf[OreMaterial])

      case Material.PACKED_ICE => null
      case Material.PEONY => null
      case Material.PISTON => null
      case Material.PISTON_HEAD => null

      // PLANKS
      case Material.ACACIA_PLANKS | Material.BIRCH_PLANKS | Material.DARK_OAK_PLANKS |
           Material.JUNGLE_PLANKS | Material.OAK_PLANKS | Material.SPRUCE_PLANKS =>
        Planks(location, material.asInstanceOf[WoodMaterial])

      case Material.PODZOL => null
      case Material.POLISHED_ANDESITE => null
      case Material.POLISHED_DIORITE => null
      case Material.POLISHED_GRANITE => null
      case Material.POTATOES => null
      case Material.POTTED_BAMBOO => null
      case Material.POTTED_BROWN_MUSHROOM => null
      case Material.POTTED_CACTUS => null
      case Material.POTTED_DEAD_BUSH => null
      case Material.POTTED_FERN => null
      case Material.POTTED_RED_MUSHROOM => null
      case Material.POWERED_RAIL => null

      // PRESSURE_PLATE
      case Material.STONE_PRESSURE_PLATE => null
      case Material.LIGHT_WEIGHTED_PRESSURE_PLATE => null
      case Material.HEAVY_WEIGHTED_PRESSURE_PLATE => null
      case Material.ACACIA_PRESSURE_PLATE | Material.BIRCH_PRESSURE_PLATE | Material.DARK_OAK_PRESSURE_PLATE |
           Material.JUNGLE_PRESSURE_PLATE | Material.OAK_PRESSURE_PLATE | Material.SPRUCE_PRESSURE_PLATE =>
        null

      case Material.PRISMARINE => null
      case Material.PRISMARINE_BRICKS => null
      case Material.PRISMARINE_WALL => null
      case Material.PUMPKIN => null
      case Material.PUMPKIN_STEM | Material.ATTACHED_PUMPKIN_STEM => null
      case Material.PURPUR_BLOCK => null
      case Material.PURPUR_PILLAR => null
      case Material.QUARTZ_PILLAR => null
      case Material.RAIL => null
      case Material.REDSTONE_LAMP => null
      case Material.REDSTONE_TORCH => null
      case Material.REDSTONE_WALL_TORCH => null
      case Material.REDSTONE_WIRE => null
      case Material.RED_MUSHROOM => null
      case Material.RED_MUSHROOM_BLOCK => null
      case Material.RED_NETHER_BRICKS => null
      case Material.RED_NETHER_BRICK_WALL => null
      case Material.RED_SANDSTONE_WALL => null
      case Material.REPEATER => null
      case Material.REPEATING_COMMAND_BLOCK => null
      case Material.ROSE_BUSH => null

      // SAND
      case Material.RED_SAND | Material.SAND | Material.SOUL_SAND =>
        Sand(location, material.asInstanceOf[SandMaterial])

      // SANDSTONE
      case Material.SANDSTONE | Material.RED_SANDSTONE |
           Material.CHISELED_SANDSTONE | Material.CHISELED_RED_SANDSTONE |
           Material.CUT_SANDSTONE | Material.CUT_RED_SANDSTONE |
           Material.SMOOTH_SANDSTONE | Material.SMOOTH_RED_SANDSTONE =>
        Sandstone(location, material.asInstanceOf[SandstoneMaterial], state.asInstanceOf[SandstoneState])

      case Material.SANDSTONE_WALL => null

      // SAPLING
      case Material.ACACIA_SAPLING | Material.POTTED_ACACIA_SAPLING |
           Material.BIRCH_SAPLING | Material.POTTED_BIRCH_SAPLING |
           Material.DARK_OAK_SAPLING | Material.POTTED_DARK_OAK_SAPLING |
           Material.JUNGLE_SAPLING | Material.POTTED_JUNGLE_SAPLING |
           Material.OAK_SAPLING | Material.POTTED_OAK_SAPLING |
           Material.SPRUCE_SAPLING | Material.POTTED_SPRUCE_SAPLING =>
        Sapling(location, potted)

      case Material.SCAFFOLDING => Scaffold(location)
      case Material.SEAGRASS => null
      case Material.SEA_LANTERN => SeaLantern(location)
      case Material.SEA_PICKLE => null

      // SHULKER_BOX
      case Material.SHULKER_BOX |
           Material.BLACK_SHULKER_BOX | Material.BLUE_SHULKER_BOX | Material.BROWN_SHULKER_BOX | Material.CYAN_SHULKER_BOX |
           Material.GRAY_SHULKER_BOX | Material.GREEN_SHULKER_BOX | Material.LIGHT_BLUE_SHULKER_BOX | Material.LIGHT_GRAY_SHULKER_BOX |
           Material.LIME_SHULKER_BOX | Material.MAGENTA_SHULKER_BOX | Material.ORANGE_SHULKER_BOX | Material.PINK_SHULKER_BOX |
           Material.PURPLE_SHULKER_BOX | Material.RED_SHULKER_BOX | Material.WHITE_SHULKER_BOX | Material.YELLOW_SHULKER_BOX =>
        ShulkerBox(location, Option(color))

      // SIGN
      case Material.ACACIA_SIGN | Material.BIRCH_SIGN | Material.DARK_OAK_SIGN |
           Material.JUNGLE_SIGN | Material.OAK_SIGN | Material.SPRUCE_SIGN =>
        null

      // WALL_SIGN
      case Material.ACACIA_WALL_SIGN | Material.BIRCH_WALL_SIGN | Material.DARK_OAK_WALL_SIGN |
           Material.JUNGLE_WALL_SIGN | Material.OAK_WALL_SIGN | Material.SPRUCE_WALL_SIGN =>
        null

      // SLAB
      case Material.BRICK_SLAB | Material.NETHER_BRICK_SLAB | Material.RED_NETHER_BRICK_SLAB |

           Material.PRISMARINE_SLAB | Material.PRISMARINE_BRICK_SLAB | Material.DARK_PRISMARINE_SLAB |

           Material.PURPUR_SLAB |

           Material.SANDSTONE_SLAB | Material.CUT_SANDSTONE_SLAB | Material.SMOOTH_SANDSTONE_SLAB |
           Material.RED_SANDSTONE_SLAB | Material.CUT_RED_SANDSTONE_SLAB | Material.SMOOTH_RED_SANDSTONE_SLAB |

           Material.STONE_SLAB | Material.SMOOTH_STONE_SLAB | Material.STONE_BRICK_SLAB | Material.MOSSY_STONE_BRICK_SLAB |
           Material.COBBLESTONE_SLAB | Material.MOSSY_COBBLESTONE_SLAB | Material.END_STONE_BRICK_SLAB |
           Material.ANDESITE_SLAB | Material.DIORITE_SLAB | Material.GRANITE_SLAB |
           Material.POLISHED_ANDESITE_SLAB | Material.POLISHED_DIORITE_SLAB | Material.POLISHED_GRANITE_SLAB |

           Material.QUARTZ_SLAB | Material.SMOOTH_QUARTZ_SLAB |

           Material.ACACIA_SLAB | Material.BIRCH_SLAB | Material.DARK_OAK_SLAB |
           Material.JUNGLE_SLAB | Material.OAK_SLAB | Material.SPRUCE_SLAB | Material.PETRIFIED_OAK_SLAB =>
        Slab(location, material.asInstanceOf[SlabMaterial], section)

      case Material.SLIME_BLOCK => Slime(location)
      case Material.SMITHING_TABLE => SmithingTable(location)
      case Material.SMOKER => Smoker(location, facing, lit)
      case Material.SMOOTH_QUARTZ => null
      case Material.SMOOTH_STONE => null
      case Material.SNOW => SnowLayer(location)
      case Material.SNOW_BLOCK => Snow(location)
      case Material.SPAWNER => Spawner(location)
      case Material.SPONGE | Material.WET_SPONGE =>
        val wet = block.getType.name.startsWith("WET_")
        Sponge(location, wet)

      // STAIRS
      case Material.BRICK_STAIRS | Material.NETHER_BRICK_STAIRS | Material.RED_NETHER_BRICK_STAIRS |

           Material.PRISMARINE_STAIRS | Material.PRISMARINE_BRICK_STAIRS | Material.DARK_PRISMARINE_STAIRS |

           Material.PURPUR_STAIRS |

           Material.SANDSTONE_STAIRS | Material.SMOOTH_SANDSTONE_STAIRS |
           Material.RED_SANDSTONE_STAIRS | Material.SMOOTH_RED_SANDSTONE_STAIRS |

           Material.STONE_STAIRS | Material.STONE_BRICK_STAIRS | Material.MOSSY_STONE_BRICK_STAIRS |
           Material.COBBLESTONE_STAIRS | Material.MOSSY_COBBLESTONE_STAIRS | Material.END_STONE_BRICK_STAIRS |
           Material.ANDESITE_STAIRS | Material.DIORITE_STAIRS | Material.GRANITE_STAIRS |
           Material.POLISHED_ANDESITE_STAIRS | Material.POLISHED_DIORITE_STAIRS | Material.POLISHED_GRANITE_STAIRS |

           Material.QUARTZ_STAIRS | Material.SMOOTH_QUARTZ_STAIRS |

           Material.ACACIA_STAIRS | Material.BIRCH_STAIRS | Material.DARK_OAK_STAIRS |
           Material.JUNGLE_STAIRS | Material.OAK_STAIRS | Material.SPRUCE_STAIRS =>
        Stairs(location, material.asInstanceOf[StairsMaterial], null, false) // TODO map attachedTo and inverted

      case Material.STICKY_PISTON => null
      case Material.STONE => null
      case Material.STONECUTTER => null
      case Material.STONE_BRICKS => null
      case Material.STONE_BRICK_WALL => null
      case Material.STONE_BUTTON => null
      case Material.STRUCTURE_BLOCK => null
      case Material.STRUCTURE_VOID => null
      case Material.SUGAR_CANE => null
      case Material.SUNFLOWER => null
      case Material.SWEET_BERRY_BUSH => null
      case Material.TALL_GRASS => null
      case Material.TALL_SEAGRASS => null

      // TERRACOTTA
      case Material.TERRACOTTA |
           Material.BLACK_TERRACOTTA | Material.BLUE_TERRACOTTA | Material.BROWN_TERRACOTTA | Material.CYAN_TERRACOTTA |
           Material.GRAY_TERRACOTTA | Material.GREEN_TERRACOTTA | Material.LIGHT_BLUE_TERRACOTTA | Material.LIGHT_GRAY_TERRACOTTA |
           Material.LIME_TERRACOTTA | Material.MAGENTA_TERRACOTTA | Material.ORANGE_TERRACOTTA | Material.PINK_TERRACOTTA |
           Material.PURPLE_TERRACOTTA | Material.RED_TERRACOTTA | Material.WHITE_TERRACOTTA | Material.YELLOW_TERRACOTTA =>
        Terracotta(location, Option(color))

      case Material.TNT =>
        val unstable = block.getState.asInstanceOf[SpigotTNT].isUnstable
        TNT(location, unstable)
      case Material.TORCH => null

      // TRAPDOOR
      case Material.ACACIA_TRAPDOOR | Material.BIRCH_TRAPDOOR | Material.DARK_OAK_TRAPDOOR |
           Material.JUNGLE_TRAPDOOR | Material.OAK_TRAPDOOR | Material.SPRUCE_TRAPDOOR =>
        null

      case Material.TRAPPED_CHEST => null
      case Material.TRIPWIRE => null
      case Material.TRIPWIRE_HOOK => null
      case Material.TURTLE_EGG => null
      case Material.VINE => null
      case Material.VOID_AIR => null

      case Material.WALL_TORCH => null
      case Material.WATER => null
      case Material.WHEAT => null

      // WOOD
      case Material.ACACIA_WOOD | Material.STRIPPED_ACACIA_WOOD |
           Material.BIRCH_WOOD | Material.STRIPPED_BIRCH_WOOD |
           Material.DARK_OAK_WOOD | Material.STRIPPED_DARK_OAK_WOOD |
           Material.JUNGLE_WOOD | Material.STRIPPED_JUNGLE_WOOD |
           Material.OAK_WOOD | Material.STRIPPED_OAK_WOOD |
           Material.SPRUCE_WOOD | Material.STRIPPED_SPRUCE_WOOD =>
        Wood(location, material.asInstanceOf[WoodMaterial], stripped)

      // WOOL
      case Material.BLACK_WOOL | Material.BLUE_WOOL | Material.BROWN_WOOL | Material.CYAN_WOOL |
           Material.GRAY_WOOL | Material.GREEN_WOOL | Material.LIGHT_BLUE_WOOL | Material.LIGHT_GRAY_WOOL |
           Material.LIME_WOOL | Material.MAGENTA_WOOL | Material.ORANGE_WOOL | Material.PINK_WOOL |
           Material.PURPLE_WOOL | Material.RED_WOOL | Material.WHITE_WOOL | Material.YELLOW_WOOL =>
        Wool(location, color)

      case _ => throw new IllegalArgumentException(s"Failed to map block with type: ${ block.getType }")
    }
  }

  def mapBisection(section: SpigotBisection): BlockBisection = section match {
    case SpigotBisection.BOTTOM => BlockBisection.BOTTOM
    case SpigotBisection.TOP => BlockBisection.TOP
  }

  def mapBisection(section: BlockBisection): SpigotBisection = section match {
    case BlockBisection.BOTTOM => SpigotBisection.BOTTOM
    case BlockBisection.TOP => SpigotBisection.TOP
  }

  def mapFace(face: SpigotBlockFace): BlockFace = face match {
    case SpigotBlockFace.NORTH => BlockFace.NORTH
    case SpigotBlockFace.EAST => BlockFace.EAST
    case SpigotBlockFace.SOUTH => BlockFace.SOUTH
    case SpigotBlockFace.WEST => BlockFace.WEST
    case SpigotBlockFace.UP => BlockFace.UP
    case SpigotBlockFace.DOWN => BlockFace.DOWN
    case _ => throw new IllegalArgumentException(s"Failed to map block face for block: $face")
  }

  def mapFace(face: BlockFace): SpigotBlockFace = face match {
    case BlockFace.NORTH => SpigotBlockFace.NORTH
    case BlockFace.EAST => SpigotBlockFace.EAST
    case BlockFace.SOUTH => SpigotBlockFace.SOUTH
    case BlockFace.WEST => SpigotBlockFace.WEST
    case BlockFace.UP => SpigotBlockFace.UP
    case BlockFace.DOWN => SpigotBlockFace.DOWN
  }

  def mapOrientation(orientation: Axis): BlockOrientation = orientation match {
    case Axis.X => BlockOrientation.X_AXIS
    case Axis.Y => BlockOrientation.Y_AXIS
    case Axis.Z => BlockOrientation.Z_AXIS
  }

  def mapOrientation(orientation: BlockOrientation): Axis = orientation match {
    case BlockOrientation.X_AXIS => Axis.X
    case BlockOrientation.Y_AXIS => Axis.Y
    case BlockOrientation.Z_AXIS => Axis.Z
  }
}
