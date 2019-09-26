package gg.warcraft.monolith.spigot.world

import gg.warcraft.monolith.api.world.block.{ Block, BlockMaterial, ColoredBlock }
import gg.warcraft.monolith.api.world.block.`type`._
import gg.warcraft.monolith.api.world.block.material._
import gg.warcraft.monolith.api.world.block.state.AnvilState
import gg.warcraft.monolith.spigot.world.block.SpigotBlockColorMapper
import javax.inject.Inject
import org.bukkit.Material

class SpigotMaterialMapper @Inject()(
  private val colorMapper: SpigotBlockColorMapper
) {

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
    case _: Fern => null
    case _: Fire => Material.FIRE
    case _: FletchingTable => Material.FLETCHING_TABLE
    case _: Flower => null
    case _: FlowerPot => null
    case _: Frost => null
    case _: Furnace => Material.FURNACE
    case _: Gate => null
    case _: Glowstone => Material.GLOWSTONE
    case _: Grass => null
    case _: GrassBlock => Material.GRASS_BLOCK
    case _: GrassPath => Material.GRASS_PATH
    case _: Gravel => Material.GRAVEL
    case _: Grindstone => Material.GRINDSTONE
    case _: HardenedClay => null
    case _: HayBale => Material.HAY_BLOCK
    case _: Hopper => Material.HOPPER
    case _: Ice => null
    case _: InfestedBlock => null
    case _: IronBars => Material.IRON_BARS
    case _: Jigsaw => Material.JIGSAW
    case _: Jukebox => Material.JUKEBOX
    case _: Kelp => null
    case _: Ladder => Material.LADDER
    case _: Lantern => Material.LANTERN
    case _: Lava => Material.LAVA
    case _: Leaves => null
    case _: Lectern => Material.LECTERN
    case _: Lever => Material.LEVER
    case _: LilyPad => Material.LILY_PAD
    case _: Log => null
    case _: Loom => Material.LOOM
    case _: Magma => Material.MAGMA_BLOCK
    case _: Melon => Material.MELON
    case _: MelonStem => Material.MELON_STEM
    case _: Mineral => null
    case _: MobHead => null
    case _: Mushroom => null
    case _: MushroomBlock => null
    case _: Mycelium => Material.MYCELIUM
    case _: NetherPortal => Material.NETHER_PORTAL
    case _: Netherrack => Material.NETHERRACK
    case _: NetherWartBlock => Material.NETHER_WART_BLOCK
    case _: NetherWarts => null
    case _: NoteBlock => Material.NOTE_BLOCK
    case _: Observer => Material.OBSERVER
    case _: Obsidian => Material.OBSIDIAN
    case _: Ore => null
    case _: Pillar => null
    case _: Piston => null
    case _: Planks => null
    case _: Podzol => Material.PODZOL
    case _: Potatoes => Material.POTATOES
    case _: PressurePlate => null
    case _: Prismarine => Material.PRISMARINE
    case _: Pumpkin => Material.PUMPKIN
    case _: PumpkinStem => Material.PUMPKIN_STEM
    case _: Purpur => Material.PURPUR_BLOCK
    case _: Quartz => Material.QUARTZ_BLOCK
    case _: Rails => null
    case _: RedstoneLamp => Material.REDSTONE_LAMP
    case _: RedstoneTorch => null
    case _: RedstoneWire => Material.REDSTONE_WIRE
    case _: Repeater => Material.REPEATER
    case _: Sand => null
    case _: Sandstone => null
    case _: Sapling => null
    case _: Scaffold => Material.SCAFFOLDING
    case _: Seagrass => null
    case _: SeaLantern => Material.SEA_LANTERN
    case _: SeaPickle => Material.SEA_PICKLE
    case _: Sign => null
    case _: Slab => null
    case _: SlimeBlock => Material.SLIME_BLOCK
    case _: SmithingTable => Material.SMITHING_TABLE
    case _: Smoker => Material.SMOKER
    case _: Snow => Material.SNOW
    case _: SnowBlock => Material.SNOW_BLOCK
    case _: Spawner => Material.SPAWNER
    case _: Sponge => null
    case _: Stairs => null
    case _: Stone => null
    case _: StoneCutter => Material.STONECUTTER
    case _: StructureBlock => Material.STRUCTURE_BLOCK
    case _: SugarCane => Material.SUGAR_CANE
    case _: SweetBerryBush => Material.SWEET_BERRY_BUSH
    case _: TNT => Material.TNT
    case _: Torch => Material.TORCH
    case _: Trapdoor => null
    case _: TurtleEgg => Material.TURTLE_EGG
    case _: Vine => Material.VINE
    case _: Wall => null
    case _: Water => Material.WATER
    case _: WeightedPressurePlate => null
    case _: Wheat => Material.WHEAT
    case _: Wood => null

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

    case it: Chest => null

    case it: Coral => null

    case it: CoralBlock => null

    case it: CoralFan => null

    case it: Door => null

    case it: Fence => null
  }
}

// TODO implement StatefulBlock twice on TurtleEgg to remove nesting of state obj?
// TODO rename all state enums that dont need it to make sense, so keep AnvilState
// TODO but rename WeightedPressurePlateState to WeightedPressurePlatePower
// TODO and TurtleEggCount plus TurtleEggAge