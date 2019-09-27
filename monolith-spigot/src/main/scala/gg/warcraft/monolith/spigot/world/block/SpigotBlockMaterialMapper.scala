package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block.{ Block, BlockMaterial, ColoredBlock }
import gg.warcraft.monolith.api.world.block.`type`._
import gg.warcraft.monolith.api.world.block.material._
import gg.warcraft.monolith.api.world.block.state.{ AnvilState, FlowerPotState }
import javax.inject.Inject
import org.bukkit.Material

class SpigotBlockMaterialMapper @Inject()(
  private val colorMapper: SpigotBlockColorMapper
) {

  def map(material: Material): BlockMaterial = {
    if (!material.isBlock) {
      throw new IllegalArgumentException("material is not a block")
    }

    material match {

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
  }

  def map(block: Block): Material = block match {
    case it: ColoredBlock => colorMapper.map(it)

    case _: Bamboo => Material.BAMBOO
    case _: Barrel => Material.BARREL
    case _: Barrier => Material.BARRIER
    case _: Beacon => Material.BEACON
    case _: Bedrock => Material.BEDROCK
    case _: Beetroots => Material.BEETROOTS
    case _: Bell => Material.BELL
    case _: BlastFurnace => Material.BLAST_FURNACE
    case _: BoneBlock => Material.BONE_BLOCK
    case _: Bookshelf => Material.BOOKSHELF
    case _: BrewingStand => Material.BREWING_STAND
    case _: Brick => Material.BRICK
    case _: BubbleColumn => Material.BUBBLE_COLUMN
    case _: Cactus => Material.CACTUS
    case _: Cake => Material.CAKE
    case _: Campfire => Material.CAMPFIRE
    case _: Carrots => Material.CARROTS
    case _: CartographyTable => Material.CARTOGRAPHY_TABLE
    case _: Cauldron => Material.CAULDRON
    case _: ChorusFlower => Material.CHORUS_FLOWER
    case _: ChorusPlant => Material.CHORUS_PLANT
    case _: Clay => Material.CLAY
    case _: Cobweb => Material.COBWEB
    case _: Cocoa => Material.COCOA
    case _: CommandBlock => Material.COMMAND_BLOCK
    case _: Comparator => Material.COMPARATOR
    case _: Composter => Material.COMPOSTER
    case _: Conduit => Material.CONDUIT
    case _: CraftingTable => Material.CRAFTING_TABLE
    case _: DaylightDetector => Material.DAYLIGHT_DETECTOR
    case _: DeadBush => Material.DEAD_BUSH
    case _: Dirt => Material.DIRT
    case _: Dispenser => Material.DISPENSER
    case _: DragonEgg => Material.DRAGON_EGG
    case _: DriedKelp => Material.DRIED_KELP_BLOCK
    case _: Dropper => Material.DROPPER
    case _: EnchantingTable => Material.ENCHANTING_TABLE
    case _: EndGateway => Material.END_GATEWAY
    case _: EndPortal => Material.END_PORTAL
    case _: EndPortalFrame => Material.END_PORTAL_FRAME
    case _: EndRod => Material.END_ROD
    case _: Farmland => Material.FARMLAND
    case _: Fire => Material.FIRE
    case _: FletchingTable => Material.FLETCHING_TABLE
    case _: Frost => Material.FROSTED_ICE // TODO should this be another version of IceMaterial?
    case _: Furnace => Material.FURNACE
    case _: Glowstone => Material.GLOWSTONE
    case _: GrassBlock => Material.GRASS_BLOCK
    case _: GrassPath => Material.GRASS_PATH
    case _: Gravel => Material.GRAVEL
    case _: Grindstone => Material.GRINDSTONE
    case _: HayBale => Material.HAY_BLOCK
    case _: Hopper => Material.HOPPER
    case _: IronBars => Material.IRON_BARS
    case _: Jigsaw => Material.JIGSAW
    case _: Jukebox => Material.JUKEBOX
    case _: Ladder => Material.LADDER
    case _: Lantern => Material.LANTERN
    case _: Lava => Material.LAVA
    case _: Lectern => Material.LECTERN
    case _: Lever => Material.LEVER
    case _: LilyPad => Material.LILY_PAD
    case _: Loom => Material.LOOM
    case _: Magma => Material.MAGMA_BLOCK
    case _: Melon => Material.MELON
    case _: MelonStem => Material.MELON_STEM
    case _: Mycelium => Material.MYCELIUM
    case _: NetherPortal => Material.NETHER_PORTAL
    case _: Netherrack => Material.NETHERRACK
    case _: NetherWarts => Material.NETHER_WART
    case _: NetherWartBlock => Material.NETHER_WART_BLOCK
    case _: NoteBlock => Material.NOTE_BLOCK
    case _: Observer => Material.OBSERVER
    case _: Obsidian => Material.OBSIDIAN
    case _: Podzol => Material.PODZOL
    case _: Potatoes => Material.POTATOES
    case _: Prismarine => Material.PRISMARINE
    case _: Pumpkin => Material.PUMPKIN
    case _: PumpkinStem => Material.PUMPKIN_STEM
    case _: Purpur => Material.PURPUR_BLOCK
    case _: Quartz => Material.QUARTZ_BLOCK
    case _: RedstoneLamp => Material.REDSTONE_LAMP
    case _: RedstoneWire => Material.REDSTONE_WIRE
    case _: Repeater => Material.REPEATER
    case _: Scaffold => Material.SCAFFOLDING
    case _: SeaLantern => Material.SEA_LANTERN
    case _: SeaPickle => Material.SEA_PICKLE
    case _: SlimeBlock => Material.SLIME_BLOCK
    case _: SmithingTable => Material.SMITHING_TABLE
    case _: Smoker => Material.SMOKER
    case _: Snow => Material.SNOW
    case _: SnowBlock => Material.SNOW_BLOCK
    case _: Spawner => Material.SPAWNER
    case _: StoneCutter => Material.STONECUTTER
    case _: StructureBlock => Material.STRUCTURE_BLOCK
    case _: SugarCane => Material.SUGAR_CANE
    case _: SweetBerryBush => Material.SWEET_BERRY_BUSH
    case _: TNT => Material.TNT
    case _: Torch => Material.TORCH
    case _: TurtleEgg => Material.TURTLE_EGG
    case _: Vine => Material.VINE
    case _: Water => Material.WATER
    case _: Wheat => Material.WHEAT

    case it: Air => it.material match {
      case AirMaterial.NORMAL => Material.AIR
      case AirMaterial.CAVE => Material.CAVE_AIR
      case AirMaterial.VOID => Material.VOID_AIR
    }

    case it: Anvil => it.state match {
      case AnvilState.PRISTINE => Material.ANVIL
      case AnvilState.CHIPPED => Material.CHIPPED_ANVIL
      case AnvilState.DAMAGED => Material.DAMAGED_ANVIL
    }

    case it: Button => it.material match {
      case _: StoneMaterial => Material.STONE_BUTTON
      case WoodMaterial.ACACIA => Material.ACACIA_BUTTON
      case WoodMaterial.BIRCH => Material.BIRCH_BUTTON
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_BUTTON
      case WoodMaterial.JUNGLE => Material.JUNGLE_BUTTON
      case WoodMaterial.OAK => Material.OAK_BUTTON
      case WoodMaterial.SPRUCE => Material.SPRUCE_BUTTON
    }

    case it: Chest => it.material match {
      case ChestMaterial.NORMAL => Material.CHEST
      case ChestMaterial.ENDER => Material.ENDER_CHEST
      case ChestMaterial.TRAPPED => Material.TRAPPED_CHEST
    }

    case it: Coral => it.material match {
      case CoralMaterial.BRAIN => Material.BRAIN_CORAL
      case CoralMaterial.BUBBLE => Material.BUBBLE_CORAL
      case CoralMaterial.FIRE => Material.FIRE_CORAL
      case CoralMaterial.HORN => Material.HORN_CORAL
      case CoralMaterial.TUBE => Material.TUBE_CORAL
      case CoralMaterial.DEAD_BRAIN => Material.DEAD_BRAIN_CORAL
      case CoralMaterial.DEAD_BUBBLE => Material.DEAD_BUBBLE_CORAL
      case CoralMaterial.DEAD_FIRE => Material.DEAD_FIRE_CORAL
      case CoralMaterial.DEAD_HORN => Material.DEAD_HORN_CORAL
      case CoralMaterial.DEAD_TUBE => Material.DEAD_TUBE_CORAL
    }

    case it: CoralBlock => it.material match {
      case CoralMaterial.BRAIN => Material.BRAIN_CORAL_BLOCK
      case CoralMaterial.BUBBLE => Material.BUBBLE_CORAL_BLOCK
      case CoralMaterial.FIRE => Material.FIRE_CORAL_BLOCK
      case CoralMaterial.HORN => Material.HORN_CORAL_BLOCK
      case CoralMaterial.TUBE => Material.TUBE_CORAL_BLOCK
      case CoralMaterial.DEAD_BRAIN => Material.DEAD_BRAIN_CORAL_BLOCK
      case CoralMaterial.DEAD_BUBBLE => Material.DEAD_BUBBLE_CORAL_BLOCK
      case CoralMaterial.DEAD_FIRE => Material.DEAD_FIRE_CORAL_BLOCK
      case CoralMaterial.DEAD_HORN => Material.DEAD_HORN_CORAL_BLOCK
      case CoralMaterial.DEAD_TUBE => Material.DEAD_TUBE_CORAL_BLOCK
    }

    case it: CoralFan =>
      if (it.facing.isEmpty) {
        it.material match {
          case CoralMaterial.BRAIN => Material.BRAIN_CORAL_FAN
          case CoralMaterial.BUBBLE => Material.BUBBLE_CORAL_FAN
          case CoralMaterial.FIRE => Material.FIRE_CORAL_FAN
          case CoralMaterial.HORN => Material.HORN_CORAL_FAN
          case CoralMaterial.TUBE => Material.TUBE_CORAL_FAN
          case CoralMaterial.DEAD_BRAIN => Material.DEAD_BRAIN_CORAL_FAN
          case CoralMaterial.DEAD_BUBBLE => Material.DEAD_BUBBLE_CORAL_FAN
          case CoralMaterial.DEAD_FIRE => Material.DEAD_FIRE_CORAL_FAN
          case CoralMaterial.DEAD_HORN => Material.DEAD_HORN_CORAL_FAN
          case CoralMaterial.DEAD_TUBE => Material.DEAD_TUBE_CORAL_FAN
        }
      } else {
        it.material match {
          case CoralMaterial.BRAIN => Material.BRAIN_CORAL_WALL_FAN
          case CoralMaterial.BUBBLE => Material.BUBBLE_CORAL_WALL_FAN
          case CoralMaterial.FIRE => Material.FIRE_CORAL_WALL_FAN
          case CoralMaterial.HORN => Material.HORN_CORAL_WALL_FAN
          case CoralMaterial.TUBE => Material.TUBE_CORAL_WALL_FAN
          case CoralMaterial.DEAD_BRAIN => Material.DEAD_BRAIN_CORAL_WALL_FAN
          case CoralMaterial.DEAD_BUBBLE => Material.DEAD_BUBBLE_CORAL
          case CoralMaterial.DEAD_FIRE => Material.DEAD_FIRE_CORAL_WALL_FAN
          case CoralMaterial.DEAD_HORN => Material.DEAD_HORN_CORAL_WALL_FAN
          case CoralMaterial.DEAD_TUBE => Material.DEAD_TUBE_CORAL_WALL_FAN
        }
      }

    case it: Door => it.material match {
      case _: IronMaterial => Material.IRON_DOOR
      case WoodMaterial.ACACIA => Material.ACACIA_DOOR
      case WoodMaterial.BIRCH => Material.BIRCH_DOOR
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_DOOR
      case WoodMaterial.JUNGLE => Material.ACACIA_DOOR
      case WoodMaterial.OAK => Material.ACACIA_DOOR
      case WoodMaterial.SPRUCE => Material.ACACIA_DOOR
    }

    case it: Fence => it.material match {
      case _: BrickMaterial => Material.NETHER_BRICK_FENCE
      case WoodMaterial.ACACIA => Material.ACACIA_FENCE
      case WoodMaterial.BIRCH => Material.BIRCH_FENCE
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_FENCE
      case WoodMaterial.JUNGLE => Material.ACACIA_FENCE
      case WoodMaterial.OAK => Material.ACACIA_FENCE
      case WoodMaterial.SPRUCE => Material.ACACIA_FENCE
    }

    case it: Fern => if (it.tall) Material.LARGE_FERN else Material.FERN

    case it: Flower => it.material match {
      case FlowerMaterial.ALLIUM => Material.ALLIUM
      case FlowerMaterial.AZURE_BLUET => Material.AZURE_BLUET
      case FlowerMaterial.BLUE_ORCHID => Material.BLUE_ORCHID
      case FlowerMaterial.CORNFLOWER => Material.CORNFLOWER
      case FlowerMaterial.DANDELION => Material.DANDELION
      case FlowerMaterial.LILY_OF_THE_VALLEY => Material.LILY_OF_THE_VALLEY
      case FlowerMaterial.ORANGE_TULIP => Material.ORANGE_TULIP
      case FlowerMaterial.OXEYE_DAISY => Material.OXEYE_DAISY
      case FlowerMaterial.PINK_TULIP => Material.PINK_TULIP
      case FlowerMaterial.POPPY => Material.POPPY
      case FlowerMaterial.RED_TULIP => Material.RED_TULIP
      case FlowerMaterial.WHITE_TULIP => Material.WHITE_TULIP
      case FlowerMaterial.WITHER_ROSE => Material.WITHER_ROSE
    }

    case it: FlowerPot => it.state match { // TODO fern, dead bush and cactus are missing
      case FlowerPotState.EMPTY => Material.FLOWER_POT
      case FlowerPotState.POTTED => it.material match {
        case FlowerMaterial.ALLIUM => Material.POTTED_ALLIUM
        case FlowerMaterial.AZURE_BLUET => Material.POTTED_AZURE_BLUET
        case FlowerMaterial.BLUE_ORCHID => Material.POTTED_BLUE_ORCHID
        case FlowerMaterial.CORNFLOWER => Material.POTTED_CORNFLOWER
        case FlowerMaterial.DANDELION => Material.POTTED_DANDELION
        case FlowerMaterial.LILY_OF_THE_VALLEY => Material.POTTED_LILY_OF_THE_VALLEY
        case FlowerMaterial.ORANGE_TULIP => Material.POTTED_ORANGE_TULIP
        case FlowerMaterial.OXEYE_DAISY => Material.POTTED_OXEYE_DAISY
        case FlowerMaterial.PINK_TULIP => Material.POTTED_PINK_TULIP
        case FlowerMaterial.POPPY => Material.POTTED_POPPY
        case FlowerMaterial.RED_TULIP => Material.POTTED_RED_TULIP
        case FlowerMaterial.WHITE_TULIP => Material.POTTED_WHITE_TULIP
        case FlowerMaterial.WITHER_ROSE => Material.POTTED_WITHER_ROSE

        case MushroomMaterial.BROWN => Material.POTTED_BROWN_MUSHROOM
        case MushroomMaterial.RED => Material.POTTED_RED_MUSHROOM

        case BambooMaterial.BAMBOO => Material.POTTED_BAMBOO
        case WoodMaterial.ACACIA => Material.POTTED_ACACIA_SAPLING
        case WoodMaterial.BIRCH => Material.POTTED_BIRCH_SAPLING
        case WoodMaterial.DARK_OAK => Material.POTTED_DARK_OAK_SAPLING
        case WoodMaterial.JUNGLE => Material.POTTED_JUNGLE_SAPLING
        case WoodMaterial.OAK => Material.POTTED_OAK_SAPLING
        case WoodMaterial.SPRUCE => Material.POTTED_SPRUCE_SAPLING
      }
    }

    case it: Gate => it.material match {
      case WoodMaterial.ACACIA => Material.ACACIA_FENCE_GATE
      case WoodMaterial.BIRCH => Material.BIRCH_FENCE_GATE
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_FENCE_GATE
      case WoodMaterial.JUNGLE => Material.ACACIA_FENCE_GATE
      case WoodMaterial.OAK => Material.ACACIA_FENCE_GATE
      case WoodMaterial.SPRUCE => Material.ACACIA_FENCE_GATE
    }

    case it: Grass => if (it.tall) Material.TALL_GRASS else Material.GRASS

    case it: Ice => it.material match {
      case IceMaterial.NORMAL => Material.ICE
      case IceMaterial.PACKED => Material.PACKED_ICE
      case IceMaterial.BLUE => Material.BLUE_ICE
    }

    case it: InfestedBlock => it.material match {
      case StoneMaterial.STONE => Material.INFESTED_STONE
      case StoneMaterial.STONE_BRICK => Material.INFESTED_STONE_BRICKS
      case StoneMaterial.CHISELED_STONE_BRICK => Material.INFESTED_CHISELED_STONE_BRICKS
      case StoneMaterial.CRACKED_STONE_BRICK => Material.INFESTED_CRACKED_STONE_BRICKS
      case StoneMaterial.MOSSY_STONE_BRICK => Material.INFESTED_MOSSY_STONE_BRICKS
      case StoneMaterial.COBBLESTONE => Material.INFESTED_COBBLESTONE
    }

    case it: Kelp =>

    case it: Leaves => it.material match {
      case WoodMaterial.ACACIA => Material.ACACIA_LEAVES
      case WoodMaterial.BIRCH => Material.BIRCH_LEAVES
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_LEAVES
      case WoodMaterial.JUNGLE => Material.JUNGLE_LEAVES
      case WoodMaterial.OAK => Material.OAK_LEAVES
      case WoodMaterial.SPRUCE => Material.SPRUCE_LEAVES
    }

    case it: Log =>
      if (it.stripped) {
        it.material match {
          case WoodMaterial.ACACIA => Material.STRIPPED_ACACIA_LOG
          case WoodMaterial.BIRCH => Material.STRIPPED_BIRCH_LOG
          case WoodMaterial.DARK_OAK => Material.STRIPPED_DARK_OAK_LOG
          case WoodMaterial.JUNGLE => Material.STRIPPED_JUNGLE_LOG
          case WoodMaterial.OAK => Material.STRIPPED_OAK_LOG
          case WoodMaterial.SPRUCE => Material.STRIPPED_SPRUCE_LOG
        }
      } else {
        it.material match {
          case WoodMaterial.ACACIA => Material.ACACIA_LOG
          case WoodMaterial.BIRCH => Material.BIRCH_LOG
          case WoodMaterial.DARK_OAK => Material.DARK_OAK_LOG
          case WoodMaterial.JUNGLE => Material.JUNGLE_LOG
          case WoodMaterial.OAK => Material.OAK_LOG
          case WoodMaterial.SPRUCE => Material.SPRUCE_LOG
        }
      }

    case it: Mineral => it.material match { // TODO rename Mineral to OreBlock?
      case ResourceMaterial.COAL => Material.COAL_BLOCK
      case ResourceMaterial.DIAMOND => Material.DIAMOND_BLOCK
      case ResourceMaterial.EMERALD => Material.EMERALD_BLOCK
      case ResourceMaterial.GOLD => Material.GOLD_BLOCK
      case ResourceMaterial.IRON => Material.IRON_BLOCK
      case ResourceMaterial.LAPIS_LAZULI => Material.LAPIS_BLOCK
      case ResourceMaterial.NETHER_QUARTZ => Material.QUARTZ_BLOCK
    }

    case it: MobHead =>
      if (it.facing.isEmpty) {
        it.material match {
          case MobHeadMaterial.CREEPER => Material.CREEPER_HEAD
          case MobHeadMaterial.DRAGON => Material.DRAGON_HEAD
          case MobHeadMaterial.PLAYER => Material.PLAYER_HEAD
          case MobHeadMaterial.SKELETON => Material.SKELETON_SKULL
          case MobHeadMaterial.WITHER_SKELETON => Material.WITHER_SKELETON_SKULL
          case MobHeadMaterial.ZOMBIE => Material.ZOMBIE_HEAD
        }
      } else {
        it.material match {
          case MobHeadMaterial.CREEPER => Material.CREEPER_WALL_HEAD
          case MobHeadMaterial.DRAGON => Material.DRAGON_WALL_HEAD
          case MobHeadMaterial.PLAYER => Material.PLAYER_WALL_HEAD
          case MobHeadMaterial.SKELETON => Material.SKELETON_WALL_SKULL
          case MobHeadMaterial.WITHER_SKELETON => Material.WITHER_SKELETON_WALL_SKULL
          case MobHeadMaterial.ZOMBIE => Material.ZOMBIE_WALL_HEAD
        }
      }

    case it: Mushroom => it.material match {
      case MushroomMaterial.BROWN => Material.BROWN_MUSHROOM
      case MushroomMaterial.RED => Material.RED_MUSHROOM
    }

    case it: MushroomBlock => it.material match {
      case MushroomBlockMaterial.BROWN => Material.BROWN_MUSHROOM_BLOCK
      case MushroomBlockMaterial.RED => Material.RED_MUSHROOM_BLOCK
      case MushroomBlockMaterial.STEM => Material.MUSHROOM_STEM
    }

    case it: Ore => it.material match {
      case ResourceMaterial.COAL => Material.COAL_ORE
      case ResourceMaterial.DIAMOND => Material.DIAMOND_ORE
      case ResourceMaterial.EMERALD => Material.EMERALD_ORE
      case ResourceMaterial.GOLD => Material.GOLD_ORE
      case ResourceMaterial.IRON => Material.IRON_ORE
      case ResourceMaterial.LAPIS_LAZULI => Material.LAPIS_ORE
      case ResourceMaterial.NETHER_QUARTZ => Material.NETHER_QUARTZ_ORE
    }

    case it: Pillar => it.material match {
      case _: PurpurMaterial => Material.PURPUR_PILLAR
      case _: QuartzMaterial => Material.QUARTZ_PILLAR
    }

    case it: Piston => if (it.sticky) Material.STICKY_PISTON else Material.PISTON

    case it: Planks => it.material match {
      case WoodMaterial.ACACIA => Material.ACACIA_PLANKS
      case WoodMaterial.BIRCH => Material.BIRCH_PLANKS
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_PLANKS
      case WoodMaterial.JUNGLE => Material.JUNGLE_PLANKS
      case WoodMaterial.OAK => Material.OAK_PLANKS
      case WoodMaterial.SPRUCE => Material.SPRUCE_PLANKS
    }

    case it: PressurePlate => it.material match {
      case _: StoneMaterial => Material.STONE_PRESSURE_PLATE
      case WoodMaterial.ACACIA => Material.ACACIA_PRESSURE_PLATE
      case WoodMaterial.BIRCH => Material.BIRCH_PRESSURE_PLATE
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_PRESSURE_PLATE
      case WoodMaterial.JUNGLE => Material.JUNGLE_PRESSURE_PLATE
      case WoodMaterial.OAK => Material.OAK_PRESSURE_PLATE
      case WoodMaterial.SPRUCE => Material.SPRUCE_PRESSURE_PLATE
    }

    case it: Rails => it.material match {
      case RailsMaterial.NORMAL => Material.RAIL
      case RailsMaterial.ACTIVATOR => Material.ACTIVATOR_RAIL
      case RailsMaterial.DETECTOR => Material.DETECTOR_RAIL
      case RailsMaterial.POWERED => Material.POWERED_RAIL
    }

    case it: RedstoneTorch =>
      if (it.facing.isEmpty) Material.REDSTONE_TORCH else Material.REDSTONE_WALL_TORCH

    case it: Sand => it.material match {
      case SandMaterial.SAND => Material.SAND
      case SandMaterial.RED_SAND => Material.RED_SAND
      case SandMaterial.SOUL_SAND => Material.SOUL_SAND
    }

    case it: Sandstone => it.material match {
      case SandstoneMaterial.SANDSTONE => Material.SANDSTONE
      case SandstoneMaterial.RED_SANDSTONE => Material.RED_SANDSTONE
    }

    case it: Sapling => it.material match {
      case _: BambooMaterial => Material.BAMBOO_SAPLING
      case WoodMaterial.ACACIA => Material.ACACIA_SAPLING
      case WoodMaterial.BIRCH => Material.BIRCH_SAPLING
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_SAPLING
      case WoodMaterial.JUNGLE => Material.JUNGLE_SAPLING
      case WoodMaterial.OAK => Material.OAK_SAPLING
      case WoodMaterial.SPRUCE => Material.SPRUCE_SAPLING
    }

    case it: Seagrass => if (it.tall) Material.TALL_SEAGRASS else Material.SEAGRASS

    case it: Sign =>
      if (it.facing.isEmpty) {
        it.material match {
          case WoodMaterial.ACACIA => Material.ACACIA_SIGN
          case WoodMaterial.BIRCH => Material.BIRCH_SIGN
          case WoodMaterial.DARK_OAK => Material.DARK_OAK_SIGN
          case WoodMaterial.JUNGLE => Material.JUNGLE_SIGN
          case WoodMaterial.OAK => Material.OAK_SIGN
          case WoodMaterial.SPRUCE => Material.SPRUCE_SIGN
        }
      } else {
        it.material match {
          case WoodMaterial.ACACIA => Material.ACACIA_WALL_SIGN
          case WoodMaterial.BIRCH => Material.BIRCH_WALL_SIGN
          case WoodMaterial.DARK_OAK => Material.DARK_OAK_WALL_SIGN
          case WoodMaterial.JUNGLE => Material.JUNGLE_WALL_SIGN
          case WoodMaterial.OAK => Material.OAK_WALL_SIGN
          case WoodMaterial.SPRUCE => Material.SPRUCE_WALL_SIGN
        }
      }

    case it: Slab => it.material match {
      case BrickMaterial.BRICK => Material.BRICK_SLAB
      case BrickMaterial.NETHER_BRICK => Material.NETHER_BRICK_SLAB
      case BrickMaterial.RED_NETHER_BRICK => Material.RED_NETHER_BRICK_SLAB

      case PrismarineMaterial.PRISMARINE => Material.PRISMARINE_SLAB
      case PrismarineMaterial.PRISMARINE_BRICK => Material.PRISMARINE_BRICK_SLAB
      case PrismarineMaterial.DARK_PRISMARINE => Material.DARK_PRISMARINE_SLAB

      case _: PurpurMaterial => Material.PURPUR_SLAB

      case QuartzMaterial.SMOOTH_QUARTZ => Material.SMOOTH_QUARTZ_SLAB
      case _: QuartzMaterial => Material.QUARTZ_SLAB

      case SandstoneMaterial.SANDSTONE => // TODO move state into shape val and let state be SMOOTH etc?
      // TODO or make SMOOTH_SANDSTONE a material like POLISHED_GRANITE
      case SandstoneMaterial.RED_SANDSTONE =>

      case StoneMaterial.STONE => Material.STONE_SLAB
      case StoneMaterial.STONE_BRICK |
           StoneMaterial.CHISELED_STONE_BRICK |
           StoneMaterial.CRACKED_STONE_BRICK => Material.STONE_BRICK_SLAB
      case StoneMaterial.MOSSY_STONE_BRICK => Material.MOSSY_STONE_BRICK_SLAB
      case StoneMaterial.MOSSY_COBBLESTONE => Material.MOSSY_COBBLESTONE_SLAB
      case StoneMaterial.ANDESITE => Material.ANDESITE_SLAB
      case StoneMaterial.POLISHED_ANDESITE => Material.POLISHED_ANDESITE_SLAB
      case StoneMaterial.DIORITE => Material.DIORITE_SLAB
      case StoneMaterial.POLISHED_DIORITE => Material.POLISHED_DIORITE_SLAB
      case StoneMaterial.GRANITE => Material.GRANITE_SLAB
      case StoneMaterial.POLISHED_GRANITE => Material.POLISHED_GRANITE_SLAB
      case StoneMaterial.END_STONE |
           StoneMaterial.END_STONE_BRICK => Material.END_STONE_BRICK_SLAB
      case _: StoneMaterial => Material.COBBLESTONE_SLAB

      case WoodMaterial.ACACIA => Material.ACACIA_SLAB
      case WoodMaterial.BIRCH => Material.BIRCH_SLAB
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_SLAB
      case WoodMaterial.JUNGLE => Material.JUNGLE_SLAB
      case WoodMaterial.OAK => Material.OAK_SLAB
      case WoodMaterial.SPRUCE => Material.SPRUCE_SLAB
    }

    case it: Sponge => if (it.wet) Material.WET_SPONGE else Material.SPONGE

    case it: Stairs => it.material match {
      case BrickMaterial.BRICK => Material.BRICK_STAIRS
      case BrickMaterial.NETHER_BRICK => Material.NETHER_BRICK_STAIRS
      case BrickMaterial.RED_NETHER_BRICK => Material.RED_NETHER_BRICK_STAIRS

      case PrismarineMaterial.PRISMARINE => Material.PRISMARINE_STAIRS
      case PrismarineMaterial.PRISMARINE_BRICK => Material.PRISMARINE_BRICK_STAIRS
      case PrismarineMaterial.DARK_PRISMARINE => Material.DARK_PRISMARINE_STAIRS

      case _: PurpurMaterial => Material.PURPUR_STAIRS

      case QuartzMaterial.SMOOTH_QUARTZ => Material.SMOOTH_QUARTZ_STAIRS
      case _: QuartzMaterial => Material.QUARTZ_STAIRS

      case SandstoneMaterial.SANDSTONE => // TODO move state into shape val and let state be SMOOTH etc?
      // TODO or make SMOOTH_SANDSTONE a material like POLISHED_GRANITE
      case SandstoneMaterial.RED_SANDSTONE =>

      case StoneMaterial.STONE => Material.STONE_STAIRS
      case StoneMaterial.STONE_BRICK |
           StoneMaterial.CHISELED_STONE_BRICK |
           StoneMaterial.CRACKED_STONE_BRICK => Material.STONE_BRICK_STAIRS
      case StoneMaterial.MOSSY_STONE_BRICK => Material.MOSSY_STONE_BRICK_STAIRS
      case StoneMaterial.MOSSY_COBBLESTONE => Material.MOSSY_COBBLESTONE_STAIRS
      case StoneMaterial.ANDESITE => Material.ANDESITE_STAIRS
      case StoneMaterial.POLISHED_ANDESITE => Material.POLISHED_ANDESITE_STAIRS
      case StoneMaterial.DIORITE => Material.DIORITE_STAIRS
      case StoneMaterial.POLISHED_DIORITE => Material.POLISHED_DIORITE_STAIRS
      case StoneMaterial.GRANITE => Material.GRANITE_STAIRS
      case StoneMaterial.POLISHED_GRANITE => Material.POLISHED_GRANITE_STAIRS
      case StoneMaterial.END_STONE |
           StoneMaterial.END_STONE_BRICK => Material.END_STONE_BRICK_STAIRS
      case _: StoneMaterial => Material.COBBLESTONE_STAIRS

      case WoodMaterial.ACACIA => Material.ACACIA_STAIRS
      case WoodMaterial.BIRCH => Material.BIRCH_STAIRS
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_STAIRS
      case WoodMaterial.JUNGLE => Material.JUNGLE_STAIRS
      case WoodMaterial.OAK => Material.OAK_STAIRS
      case WoodMaterial.SPRUCE => Material.SPRUCE_STAIRS
    }

    case it: Stone => null

    case it: Trapdoor => it.material match {
      case _: IronMaterial => Material.IRON_TRAPDOOR
      case WoodMaterial.ACACIA => Material.ACACIA_TRAPDOOR
      case WoodMaterial.BIRCH => Material.BIRCH_TRAPDOOR
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_TRAPDOOR
      case WoodMaterial.JUNGLE => Material.JUNGLE_TRAPDOOR
      case WoodMaterial.OAK => Material.OAK_TRAPDOOR
      case WoodMaterial.SPRUCE => Material.SPRUCE_TRAPDOOR
    }

    case it: Wall => it.material match {
      case BrickMaterial.BRICK => Material.BRICK_WALL
      case BrickMaterial.NETHER_BRICK => Material.NETHER_BRICK_WALL
      case BrickMaterial.RED_NETHER_BRICK => Material.RED_NETHER_BRICK_WALL

      case _: PrismarineMaterial => Material.PRISMARINE_WALL

      case SandstoneMaterial.SANDSTONE => Material.SANDSTONE_WALL
      case SandstoneMaterial.RED_SANDSTONE => Material.RED_SANDSTONE_WALL

      case StoneMaterial.STONE_BRICK |
           StoneMaterial.CHISELED_STONE_BRICK |
           StoneMaterial.CRACKED_STONE_BRICK => Material.STONE_BRICK_WALL
      case StoneMaterial.MOSSY_STONE_BRICK => Material.MOSSY_STONE_BRICK_WALL
      case StoneMaterial.MOSSY_COBBLESTONE => Material.MOSSY_COBBLESTONE_WALL
      case StoneMaterial.ANDESITE |
           StoneMaterial.POLISHED_ANDESITE => Material.ANDESITE_WALL
      case StoneMaterial.DIORITE |
           StoneMaterial.POLISHED_DIORITE => Material.DIORITE_WALL
      case StoneMaterial.GRANITE |
           StoneMaterial.POLISHED_GRANITE => Material.GRANITE_WALL
      case StoneMaterial.END_STONE |
           StoneMaterial.END_STONE_BRICK => Material.END_STONE_BRICK_WALL
      case _: StoneMaterial => Material.COBBLESTONE_WALL
    }

    case it: WeightedPressurePlate => it.material match {
      case WeightedPressurePlateMaterial.LIGHT => Material.LIGHT_WEIGHTED_PRESSURE_PLATE
      case WeightedPressurePlateMaterial.HEAVY => Material.HEAVY_WEIGHTED_PRESSURE_PLATE
    }

    case it: Wood =>
      if (it.stripped) {
        it.material match {
          case WoodMaterial.ACACIA => Material.STRIPPED_ACACIA_WOOD
          case WoodMaterial.BIRCH => Material.STRIPPED_BIRCH_WOOD
          case WoodMaterial.DARK_OAK => Material.STRIPPED_DARK_OAK_WOOD
          case WoodMaterial.JUNGLE => Material.STRIPPED_JUNGLE_WOOD
          case WoodMaterial.OAK => Material.STRIPPED_DARK_OAK_WOOD
          case WoodMaterial.SPRUCE => Material.STRIPPED_SPRUCE_WOOD
        }
      } else {
        it.material match {
          case WoodMaterial.ACACIA => Material.ACACIA_WOOD
          case WoodMaterial.BIRCH => Material.BIRCH_WOOD
          case WoodMaterial.DARK_OAK => Material.DARK_OAK_WOOD
          case WoodMaterial.JUNGLE => Material.JUNGLE_WOOD
          case WoodMaterial.OAK => Material.DARK_OAK_WOOD
          case WoodMaterial.SPRUCE => Material.SPRUCE_WOOD
        }
      }
  }
}
