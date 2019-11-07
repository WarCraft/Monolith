package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.variant._
import org.bukkit.{Material, Instrument => SpigotInstrument}
import org.bukkit.block.data.`type`.Bamboo.{Leaves => SpigotBambooLeaves}
import org.bukkit.block.data.`type`.Comparator.{Mode => SpigotComparatorMode}
import org.bukkit.block.data.`type`.StructureBlock.{Mode => SpigotStructureBlockMode}
import org.bukkit.block.data.`type`.TechnicalPiston.{Type => SpigotPistonType}

import scala.annotation.switch

class SpigotBlockVariantMapper {
  def map(material: Material): BlockVariant = (material: @switch) match {
    // AIR
    case Material.AIR      => AirVariant.NORMAL
    case Material.CAVE_AIR => AirVariant.CAVE
    case Material.VOID_AIR => AirVariant.VOID

    // ANDESITE
    case Material.ANDESITE          => AndesiteVariant.NORMAL
    case Material.POLISHED_ANDESITE => AndesiteVariant.POLISHED

    // ANVIL
    case Material.ANVIL         => AnvilVariant.NORMAL
    case Material.CHIPPED_ANVIL => AnvilVariant.CHIPPED
    case Material.DAMAGED_ANVIL => AnvilVariant.DAMAGED

    // BANNER
    case Material.BLACK_BANNER      => BannerVariant.BLACK
    case Material.BLUE_BANNER       => BannerVariant.BLUE
    case Material.BROWN_BANNER      => BannerVariant.BROWN
    case Material.CYAN_BANNER       => BannerVariant.CYAN
    case Material.GRAY_BANNER       => BannerVariant.GRAY
    case Material.GREEN_BANNER      => BannerVariant.GREEN
    case Material.LIGHT_BLUE_BANNER => BannerVariant.LIGHT_BLUE
    case Material.LIGHT_GRAY_BANNER => BannerVariant.LIGHT_GRAY
    case Material.LIME_BANNER       => BannerVariant.LIME
    case Material.MAGENTA_BANNER    => BannerVariant.MAGENTA
    case Material.ORANGE_BANNER     => BannerVariant.ORANGE
    case Material.PINK_BANNER       => BannerVariant.PINK
    case Material.PURPLE_BANNER     => BannerVariant.PURPLE
    case Material.RED_BANNER        => BannerVariant.RED
    case Material.WHITE_BANNER      => BannerVariant.WHITE
    case Material.YELLOW_BANNER     => BannerVariant.YELLOW

    case Material.BLACK_WALL_BANNER      => BannerVariant.BLACK
    case Material.BLUE_WALL_BANNER       => BannerVariant.BLUE
    case Material.BROWN_WALL_BANNER      => BannerVariant.BROWN
    case Material.CYAN_WALL_BANNER       => BannerVariant.CYAN
    case Material.GRAY_WALL_BANNER       => BannerVariant.GRAY
    case Material.GREEN_WALL_BANNER      => BannerVariant.GREEN
    case Material.LIGHT_BLUE_WALL_BANNER => BannerVariant.LIGHT_BLUE
    case Material.LIGHT_GRAY_WALL_BANNER => BannerVariant.LIGHT_GRAY
    case Material.LIME_WALL_BANNER       => BannerVariant.LIME
    case Material.MAGENTA_WALL_BANNER    => BannerVariant.MAGENTA
    case Material.ORANGE_WALL_BANNER     => BannerVariant.ORANGE
    case Material.PINK_WALL_BANNER       => BannerVariant.PINK
    case Material.PURPLE_WALL_BANNER     => BannerVariant.PURPLE
    case Material.RED_WALL_BANNER        => BannerVariant.RED
    case Material.WHITE_WALL_BANNER      => BannerVariant.WHITE
    case Material.YELLOW_WALL_BANNER     => BannerVariant.YELLOW

    // BED
    case Material.BLACK_BED      => BedVariant.BLACK
    case Material.BLUE_BED       => BedVariant.BLUE
    case Material.BROWN_BED      => BedVariant.BROWN
    case Material.CYAN_BED       => BedVariant.CYAN
    case Material.GRAY_BED       => BedVariant.GRAY
    case Material.GREEN_BED      => BedVariant.GREEN
    case Material.LIGHT_BLUE_BED => BedVariant.LIGHT_BLUE
    case Material.LIGHT_GRAY_BED => BedVariant.LIGHT_GRAY
    case Material.LIME_BED       => BedVariant.LIME
    case Material.MAGENTA_BED    => BedVariant.MAGENTA
    case Material.ORANGE_BED     => BedVariant.ORANGE
    case Material.PINK_BED       => BedVariant.PINK
    case Material.PURPLE_BED     => BedVariant.PURPLE
    case Material.RED_BED        => BedVariant.RED
    case Material.WHITE_BED      => BedVariant.WHITE
    case Material.YELLOW_BED     => BedVariant.YELLOW

    // BRICK_BLOCK
    case Material.BRICKS            => BrickBlockVariant.NORMAL
    case Material.NETHER_BRICKS     => BrickBlockVariant.NETHER
    case Material.RED_NETHER_BRICKS => BrickBlockVariant.RED_NETHER

    // BUTTON
    case Material.ACACIA_BUTTON   => ButtonVariant.ACACIA
    case Material.BIRCH_BUTTON    => ButtonVariant.BIRCH
    case Material.DARK_OAK_BUTTON => ButtonVariant.DARK_OAK
    case Material.JUNGLE_BUTTON   => ButtonVariant.JUNGLE
    case Material.OAK_BUTTON      => ButtonVariant.OAK
    case Material.SPRUCE_BUTTON   => ButtonVariant.SPRUCE

    case Material.STONE_BUTTON => ButtonVariant.STONE

    // CARPET
    case Material.BLACK_CARPET      => CarpetVariant.BLACK
    case Material.BLUE_CARPET       => CarpetVariant.BLUE
    case Material.BROWN_CARPET      => CarpetVariant.BROWN
    case Material.CYAN_CARPET       => CarpetVariant.CYAN
    case Material.GRAY_CARPET       => CarpetVariant.GRAY
    case Material.GREEN_CARPET      => CarpetVariant.GREEN
    case Material.LIGHT_BLUE_CARPET => CarpetVariant.LIGHT_BLUE
    case Material.LIGHT_GRAY_CARPET => CarpetVariant.LIGHT_GRAY
    case Material.LIME_CARPET       => CarpetVariant.LIME
    case Material.MAGENTA_CARPET    => CarpetVariant.MAGENTA
    case Material.ORANGE_CARPET     => CarpetVariant.ORANGE
    case Material.PINK_CARPET       => CarpetVariant.PINK
    case Material.PURPLE_CARPET     => CarpetVariant.PURPLE
    case Material.RED_CARPET        => CarpetVariant.RED
    case Material.WHITE_CARPET      => CarpetVariant.WHITE
    case Material.YELLOW_CARPET     => CarpetVariant.YELLOW

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

    // CONCRETE
    case Material.BLACK_CONCRETE      => ConcreteVariant.BLACK
    case Material.BLUE_CONCRETE       => ConcreteVariant.BLUE
    case Material.BROWN_CONCRETE      => ConcreteVariant.BROWN
    case Material.CYAN_CONCRETE       => ConcreteVariant.CYAN
    case Material.GRAY_CONCRETE       => ConcreteVariant.GRAY
    case Material.GREEN_CONCRETE      => ConcreteVariant.GREEN
    case Material.LIGHT_BLUE_CONCRETE => ConcreteVariant.LIGHT_BLUE
    case Material.LIGHT_GRAY_CONCRETE => ConcreteVariant.LIGHT_GRAY
    case Material.LIME_CONCRETE       => ConcreteVariant.LIME
    case Material.MAGENTA_CONCRETE    => ConcreteVariant.MAGENTA
    case Material.ORANGE_CONCRETE     => ConcreteVariant.ORANGE
    case Material.PINK_CONCRETE       => ConcreteVariant.PINK
    case Material.PURPLE_CONCRETE     => ConcreteVariant.PURPLE
    case Material.RED_CONCRETE        => ConcreteVariant.RED
    case Material.WHITE_CONCRETE      => ConcreteVariant.WHITE
    case Material.YELLOW_CONCRETE     => ConcreteVariant.YELLOW

    // CONCRETE_POWDER
    case Material.BLACK_CONCRETE_POWDER      => ConcretePowderVariant.BLACK
    case Material.BLUE_CONCRETE_POWDER       => ConcretePowderVariant.BLUE
    case Material.BROWN_CONCRETE_POWDER      => ConcretePowderVariant.BROWN
    case Material.CYAN_CONCRETE_POWDER       => ConcretePowderVariant.CYAN
    case Material.GRAY_CONCRETE_POWDER       => ConcretePowderVariant.GRAY
    case Material.GREEN_CONCRETE_POWDER      => ConcretePowderVariant.GREEN
    case Material.LIGHT_BLUE_CONCRETE_POWDER => ConcretePowderVariant.LIGHT_BLUE
    case Material.LIGHT_GRAY_CONCRETE_POWDER => ConcretePowderVariant.LIGHT_GRAY
    case Material.LIME_CONCRETE_POWDER       => ConcretePowderVariant.LIME
    case Material.MAGENTA_CONCRETE_POWDER    => ConcretePowderVariant.MAGENTA
    case Material.ORANGE_CONCRETE_POWDER     => ConcretePowderVariant.ORANGE
    case Material.PINK_CONCRETE_POWDER       => ConcretePowderVariant.PINK
    case Material.PURPLE_CONCRETE_POWDER     => ConcretePowderVariant.PURPLE
    case Material.RED_CONCRETE_POWDER        => ConcretePowderVariant.RED
    case Material.WHITE_CONCRETE_POWDER      => ConcretePowderVariant.WHITE
    case Material.YELLOW_CONCRETE_POWDER     => ConcretePowderVariant.YELLOW

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

    case Material.BRAIN_CORAL_WALL_FAN  => CoralFanVariant.BRAIN
    case Material.BUBBLE_CORAL_WALL_FAN => CoralFanVariant.BUBBLE
    case Material.FIRE_CORAL_WALL_FAN   => CoralFanVariant.FIRE
    case Material.HORN_CORAL_WALL_FAN   => CoralFanVariant.HORN
    case Material.TUBE_CORAL_WALL_FAN   => CoralFanVariant.TUBE

    case Material.DEAD_BRAIN_CORAL_WALL_FAN  => CoralFanVariant.DEAD_BRAIN
    case Material.DEAD_BUBBLE_CORAL_WALL_FAN => CoralFanVariant.DEAD_BUBBLE
    case Material.DEAD_FIRE_CORAL_WALL_FAN   => CoralFanVariant.DEAD_FIRE
    case Material.DEAD_HORN_CORAL_WALL_FAN   => CoralFanVariant.DEAD_HORN
    case Material.DEAD_TUBE_CORAL_WALL_FAN   => CoralFanVariant.DEAD_TUBE

    // DIORITE
    case Material.DIORITE          => DioriteVariant.NORMAL
    case Material.POLISHED_DIORITE => DioriteVariant.POLISHED

    // DIRT
    case Material.DIRT        => DirtVariant.NORMAL
    case Material.COARSE_DIRT => DirtVariant.COARSE

    // DOOR
    case Material.ACACIA_DOOR   => DoorVariant.ACACIA
    case Material.BIRCH_DOOR    => DoorVariant.BIRCH
    case Material.DARK_OAK_DOOR => DoorVariant.DARK_OAK
    case Material.JUNGLE_DOOR   => DoorVariant.JUNGLE
    case Material.OAK_DOOR      => DoorVariant.OAK
    case Material.SPRUCE_DOOR   => DoorVariant.SPRUCE

    case Material.IRON_DOOR => DoorVariant.IRON

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

    // FERN
    case Material.FERN       => FernVariant.NORMAL
    case Material.LARGE_FERN => FernVariant.TALL

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

    // GLASS
    case Material.GLASS                    => GlassVariant.NORMAL
    case Material.BLACK_STAINED_GLASS      => GlassVariant.BLACK
    case Material.BLUE_STAINED_GLASS       => GlassVariant.BLUE
    case Material.BROWN_STAINED_GLASS      => GlassVariant.BROWN
    case Material.CYAN_STAINED_GLASS       => GlassVariant.CYAN
    case Material.GRAY_STAINED_GLASS       => GlassVariant.GRAY
    case Material.GREEN_STAINED_GLASS      => GlassVariant.GREEN
    case Material.LIGHT_BLUE_STAINED_GLASS => GlassVariant.LIGHT_BLUE
    case Material.LIGHT_GRAY_STAINED_GLASS => GlassVariant.LIGHT_GRAY
    case Material.LIME_STAINED_GLASS       => GlassVariant.LIME
    case Material.MAGENTA_STAINED_GLASS    => GlassVariant.MAGENTA
    case Material.ORANGE_STAINED_GLASS     => GlassVariant.ORANGE
    case Material.PINK_STAINED_GLASS       => GlassVariant.PINK
    case Material.PURPLE_STAINED_GLASS     => GlassVariant.PURPLE
    case Material.RED_STAINED_GLASS        => GlassVariant.RED
    case Material.WHITE_STAINED_GLASS      => GlassVariant.WHITE
    case Material.YELLOW_STAINED_GLASS     => GlassVariant.YELLOW

    // GLASS_PANE
    case Material.GLASS_PANE                    => GlassPaneVariant.NORMAL
    case Material.BLACK_STAINED_GLASS_PANE      => GlassPaneVariant.BLACK
    case Material.BLUE_STAINED_GLASS_PANE       => GlassPaneVariant.BLUE
    case Material.BROWN_STAINED_GLASS_PANE      => GlassPaneVariant.BROWN
    case Material.CYAN_STAINED_GLASS_PANE       => GlassPaneVariant.CYAN
    case Material.GRAY_STAINED_GLASS_PANE       => GlassPaneVariant.GRAY
    case Material.GREEN_STAINED_GLASS_PANE      => GlassPaneVariant.GREEN
    case Material.LIGHT_BLUE_STAINED_GLASS_PANE => GlassPaneVariant.LIGHT_BLUE
    case Material.LIGHT_GRAY_STAINED_GLASS_PANE => GlassPaneVariant.LIGHT_GRAY
    case Material.LIME_STAINED_GLASS_PANE       => GlassPaneVariant.LIME
    case Material.MAGENTA_STAINED_GLASS_PANE    => GlassPaneVariant.MAGENTA
    case Material.ORANGE_STAINED_GLASS_PANE     => GlassPaneVariant.ORANGE
    case Material.PINK_STAINED_GLASS_PANE       => GlassPaneVariant.PINK
    case Material.PURPLE_STAINED_GLASS_PANE     => GlassPaneVariant.PURPLE
    case Material.RED_STAINED_GLASS_PANE        => GlassPaneVariant.RED
    case Material.WHITE_STAINED_GLASS_PANE      => GlassPaneVariant.WHITE
    case Material.YELLOW_STAINED_GLASS_PANE     => GlassPaneVariant.YELLOW

    // GLAZED_TERRACOTTA
    case Material.BLACK_GLAZED_TERRACOTTA      => GlazedTerracottaVariant.BLACK
    case Material.BLUE_GLAZED_TERRACOTTA       => GlazedTerracottaVariant.BLUE
    case Material.BROWN_GLAZED_TERRACOTTA      => GlazedTerracottaVariant.BROWN
    case Material.CYAN_GLAZED_TERRACOTTA       => GlazedTerracottaVariant.CYAN
    case Material.GRAY_GLAZED_TERRACOTTA       => GlazedTerracottaVariant.GRAY
    case Material.GREEN_GLAZED_TERRACOTTA      => GlazedTerracottaVariant.GREEN
    case Material.LIGHT_BLUE_GLAZED_TERRACOTTA => GlazedTerracottaVariant.LIGHT_BLUE
    case Material.LIGHT_GRAY_GLAZED_TERRACOTTA => GlazedTerracottaVariant.LIGHT_GRAY
    case Material.LIME_GLAZED_TERRACOTTA       => GlazedTerracottaVariant.LIME
    case Material.MAGENTA_GLAZED_TERRACOTTA    => GlazedTerracottaVariant.MAGENTA
    case Material.ORANGE_GLAZED_TERRACOTTA     => GlazedTerracottaVariant.ORANGE
    case Material.PINK_GLAZED_TERRACOTTA       => GlazedTerracottaVariant.PINK
    case Material.PURPLE_GLAZED_TERRACOTTA     => GlazedTerracottaVariant.PURPLE
    case Material.RED_GLAZED_TERRACOTTA        => GlazedTerracottaVariant.RED
    case Material.WHITE_GLAZED_TERRACOTTA      => GlazedTerracottaVariant.WHITE
    case Material.YELLOW_GLAZED_TERRACOTTA     => GlazedTerracottaVariant.YELLOW

    // GRANITE
    case Material.GRANITE          => GraniteVariant.NORMAL
    case Material.POLISHED_GRANITE => GraniteVariant.POLISHED

    // GRASS
    case Material.GRASS      => GrassVariant.NORMAL
    case Material.TALL_GRASS => GrassVariant.TALL

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

    case Material.CREEPER_WALL_HEAD          => MobHeadVariant.CREEPER
    case Material.DRAGON_WALL_HEAD           => MobHeadVariant.DRAGON
    case Material.PLAYER_WALL_HEAD           => MobHeadVariant.PLAYER
    case Material.SKELETON_WALL_SKULL        => MobHeadVariant.SKELETON
    case Material.WITHER_SKELETON_WALL_SKULL => MobHeadVariant.WITHER_SKELETON
    case Material.ZOMBIE_WALL_HEAD           => MobHeadVariant.ZOMBIE

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

    // PISTON
    case Material.PISTON        => PistonVariant.NORMAL
    case Material.STICKY_PISTON => PistonVariant.STICKY

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

    // QUARTZ_BLOCK
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

    // SHULKER_BOX
    case Material.SHULKER_BOX            => ShulkerBoxVariant.NORMAL
    case Material.BLACK_SHULKER_BOX      => ShulkerBoxVariant.BLACK
    case Material.BLUE_SHULKER_BOX       => ShulkerBoxVariant.BLUE
    case Material.BROWN_SHULKER_BOX      => ShulkerBoxVariant.BROWN
    case Material.CYAN_SHULKER_BOX       => ShulkerBoxVariant.CYAN
    case Material.GRAY_SHULKER_BOX       => ShulkerBoxVariant.GRAY
    case Material.GREEN_SHULKER_BOX      => ShulkerBoxVariant.GREEN
    case Material.LIGHT_BLUE_SHULKER_BOX => ShulkerBoxVariant.LIGHT_BLUE
    case Material.LIGHT_GRAY_SHULKER_BOX => ShulkerBoxVariant.LIGHT_GRAY
    case Material.LIME_SHULKER_BOX       => ShulkerBoxVariant.LIME
    case Material.MAGENTA_SHULKER_BOX    => ShulkerBoxVariant.MAGENTA
    case Material.ORANGE_SHULKER_BOX     => ShulkerBoxVariant.ORANGE
    case Material.PINK_SHULKER_BOX       => ShulkerBoxVariant.PINK
    case Material.PURPLE_SHULKER_BOX     => ShulkerBoxVariant.PURPLE
    case Material.RED_SHULKER_BOX        => ShulkerBoxVariant.RED
    case Material.WHITE_SHULKER_BOX      => ShulkerBoxVariant.WHITE
    case Material.YELLOW_SHULKER_BOX     => ShulkerBoxVariant.YELLOW

    // SIGN
    case Material.ACACIA_SIGN   => SignVariant.ACACIA
    case Material.BIRCH_SIGN    => SignVariant.BIRCH
    case Material.DARK_OAK_SIGN => SignVariant.DARK_OAK
    case Material.JUNGLE_SIGN   => SignVariant.JUNGLE
    case Material.OAK_SIGN      => SignVariant.OAK
    case Material.SPRUCE_SIGN   => SignVariant.SPRUCE

    case Material.ACACIA_WALL_SIGN   => SignVariant.ACACIA
    case Material.BIRCH_WALL_SIGN    => SignVariant.BIRCH
    case Material.DARK_OAK_WALL_SIGN => SignVariant.DARK_OAK
    case Material.JUNGLE_WALL_SIGN   => SignVariant.JUNGLE
    case Material.OAK_WALL_SIGN      => SignVariant.OAK
    case Material.SPRUCE_WALL_SIGN   => SignVariant.SPRUCE

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

    // SPONGE
    case Material.SPONGE     => SpongeVariant.NORMAL
    case Material.WET_SPONGE => SpongeVariant.WET

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

    // STONE
    case Material.STONE        => StoneVariant.NORMAL
    case Material.SMOOTH_STONE => StoneVariant.SMOOTH

    // STONE_BRICK
    case Material.STONE_BRICKS          => StoneBrickVariant.NORMAL
    case Material.CHISELED_STONE_BRICKS => StoneBrickVariant.CHISELED
    case Material.CRACKED_STONE_BRICKS  => StoneBrickVariant.CRACKED
    case Material.MOSSY_STONE_BRICKS    => StoneBrickVariant.MOSSY

    // STRUCTURE_BLOCK
    case Material.STRUCTURE_VOID => StructureBlockVariant.VOID

    // TERRACOTTA
    case Material.TERRACOTTA            => TerracottaVariant.NORMAL
    case Material.BLACK_TERRACOTTA      => TerracottaVariant.BLACK
    case Material.BLUE_TERRACOTTA       => TerracottaVariant.BLUE
    case Material.BROWN_TERRACOTTA      => TerracottaVariant.BROWN
    case Material.CYAN_TERRACOTTA       => TerracottaVariant.CYAN
    case Material.GRAY_TERRACOTTA       => TerracottaVariant.GRAY
    case Material.GREEN_TERRACOTTA      => TerracottaVariant.GREEN
    case Material.LIGHT_BLUE_TERRACOTTA => TerracottaVariant.LIGHT_BLUE
    case Material.LIGHT_GRAY_TERRACOTTA => TerracottaVariant.LIGHT_GRAY
    case Material.LIME_TERRACOTTA       => TerracottaVariant.LIME
    case Material.MAGENTA_TERRACOTTA    => TerracottaVariant.MAGENTA
    case Material.ORANGE_TERRACOTTA     => TerracottaVariant.ORANGE
    case Material.PINK_TERRACOTTA       => TerracottaVariant.PINK
    case Material.PURPLE_TERRACOTTA     => TerracottaVariant.PURPLE
    case Material.RED_TERRACOTTA        => TerracottaVariant.RED
    case Material.WHITE_TERRACOTTA      => TerracottaVariant.WHITE
    case Material.YELLOW_TERRACOTTA     => TerracottaVariant.YELLOW

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

    case Material.STRIPPED_ACACIA_WOOD   => WoodVariant.STRIPPED_ACACIA
    case Material.STRIPPED_BIRCH_WOOD    => WoodVariant.STRIPPED_BIRCH
    case Material.STRIPPED_DARK_OAK_WOOD => WoodVariant.STRIPPED_DARK_OAK
    case Material.STRIPPED_JUNGLE_WOOD   => WoodVariant.STRIPPED_JUNGLE
    case Material.STRIPPED_OAK_WOOD      => WoodVariant.STRIPPED_OAK
    case Material.STRIPPED_SPRUCE_WOOD   => WoodVariant.STRIPPED_SPRUCE

    // WOOL
    case Material.BLACK_WOOL      => WoolVariant.BLACK
    case Material.BLUE_WOOL       => WoolVariant.BLUE
    case Material.BROWN_WOOL      => WoolVariant.BROWN
    case Material.CYAN_WOOL       => WoolVariant.CYAN
    case Material.GRAY_WOOL       => WoolVariant.GRAY
    case Material.GREEN_WOOL      => WoolVariant.GREEN
    case Material.LIGHT_BLUE_WOOL => WoolVariant.LIGHT_BLUE
    case Material.LIGHT_GRAY_WOOL => WoolVariant.LIGHT_GRAY
    case Material.LIME_WOOL       => WoolVariant.LIME
    case Material.MAGENTA_WOOL    => WoolVariant.MAGENTA
    case Material.ORANGE_WOOL     => WoolVariant.ORANGE
    case Material.PINK_WOOL       => WoolVariant.PINK
    case Material.PURPLE_WOOL     => WoolVariant.PURPLE
    case Material.RED_WOOL        => WoolVariant.RED
    case Material.WHITE_WOOL      => WoolVariant.WHITE
    case Material.YELLOW_WOOL     => WoolVariant.YELLOW

    case _ => throw new IllegalArgumentException(s"$material")
  }

  def map(block: SpigotBlock): BlockVariant = {
    lazy val data: SpigotBlockData = block.getState.getBlockData

    (block.getType: @switch) match {
      case Material.BAMBOO_SAPLING => BambooVariant.SAPLING
      case Material.BAMBOO =>
        data.asInstanceOf[SpigotBamboo].getLeaves match {
          case SpigotBambooLeaves.NONE  => BambooVariant.NO_LEAVES
          case SpigotBambooLeaves.SMALL => BambooVariant.SMALL_LEAVES
          case SpigotBambooLeaves.LARGE => BambooVariant.LARGE_LEAVES
        }

      case Material.COMPARATOR =>
        data.asInstanceOf[SpigotComparator].getMode match {
          case SpigotComparatorMode.COMPARE  => ComparatorVariant.COMPARE
          case SpigotComparatorMode.SUBTRACT => ComparatorVariant.SUBTRACT
        }

      case Material.NOTE_BLOCK =>
        data.asInstanceOf[SpigotNoteBlock].getInstrument match {
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

      case Material.PISTON_HEAD =>
        data.asInstanceOf[SpigotPistonHead].getType match {
          case SpigotPistonType.NORMAL => PistonHeadVariant.NORMAL
          case SpigotPistonType.STICKY => PistonHeadVariant.STICKY
        }

      case Material.STRUCTURE_BLOCK =>
        data.asInstanceOf[SpigotStructureBlock].getMode match {
          case SpigotStructureBlockMode.CORNER => StructureBlockVariant.CORNER
          case SpigotStructureBlockMode.DATA   => StructureBlockVariant.DATA
          case SpigotStructureBlockMode.LOAD   => StructureBlockVariant.LOAD
          case SpigotStructureBlockMode.SAVE   => StructureBlockVariant.SAVE
        }

      case it => map(it)
    }
  }

  def map(variant: BlockVariant): Material = (variant: @switch) match {
    // AIR
    case AirVariant.NORMAL => Material.AIR
    case AirVariant.CAVE   => Material.CAVE_AIR
    case AirVariant.VOID   => Material.VOID_AIR

    // ANDESITE
    case AndesiteVariant.NORMAL   => Material.ANDESITE
    case AndesiteVariant.POLISHED => Material.POLISHED_ANDESITE

    // ANVIL
    case AnvilVariant.NORMAL  => Material.ANVIL
    case AnvilVariant.CHIPPED => Material.CHIPPED_ANVIL
    case AnvilVariant.DAMAGED => Material.DAMAGED_ANVIL

    // BANNER
    case BannerVariant.BLACK      => Material.BLACK_BANNER
    case BannerVariant.BLUE       => Material.BLUE_BANNER
    case BannerVariant.BROWN      => Material.BROWN_BANNER
    case BannerVariant.CYAN       => Material.CYAN_BANNER
    case BannerVariant.GRAY       => Material.GRAY_BANNER
    case BannerVariant.GREEN      => Material.GREEN_BANNER
    case BannerVariant.LIGHT_BLUE => Material.LIGHT_BLUE_BANNER
    case BannerVariant.LIGHT_GRAY => Material.LIGHT_GRAY_BANNER
    case BannerVariant.LIME       => Material.LIME_BANNER
    case BannerVariant.MAGENTA    => Material.MAGENTA_BANNER
    case BannerVariant.ORANGE     => Material.ORANGE_BANNER
    case BannerVariant.PINK       => Material.PINK_BANNER
    case BannerVariant.PURPLE     => Material.PURPLE_BANNER
    case BannerVariant.RED        => Material.RED_BANNER
    case BannerVariant.WHITE      => Material.WHITE_BANNER
    case BannerVariant.YELLOW     => Material.YELLOW_BANNER

    // BED
    case BedVariant.BLACK      => Material.BLACK_BED
    case BedVariant.BLUE       => Material.BLUE_BED
    case BedVariant.BROWN      => Material.BROWN_BED
    case BedVariant.CYAN       => Material.CYAN_BED
    case BedVariant.GRAY       => Material.GRAY_BED
    case BedVariant.GREEN      => Material.GREEN_BED
    case BedVariant.LIGHT_BLUE => Material.LIGHT_BLUE_BED
    case BedVariant.LIGHT_GRAY => Material.LIGHT_GRAY_BED
    case BedVariant.LIME       => Material.LIME_BED
    case BedVariant.MAGENTA    => Material.MAGENTA_BED
    case BedVariant.ORANGE     => Material.ORANGE_BED
    case BedVariant.PINK       => Material.PINK_BED
    case BedVariant.PURPLE     => Material.PURPLE_BED
    case BedVariant.RED        => Material.RED_BED
    case BedVariant.WHITE      => Material.WHITE_BED
    case BedVariant.YELLOW     => Material.YELLOW_BED

    // BRICK_BLOCK
    case BrickBlockVariant.NORMAL     => Material.BRICKS
    case BrickBlockVariant.NETHER     => Material.NETHER_BRICKS
    case BrickBlockVariant.RED_NETHER => Material.RED_NETHER_BRICKS

    // BUTTON
    case ButtonVariant.ACACIA   => Material.ACACIA_BUTTON
    case ButtonVariant.BIRCH    => Material.BIRCH_BUTTON
    case ButtonVariant.DARK_OAK => Material.DARK_OAK_BUTTON
    case ButtonVariant.JUNGLE   => Material.JUNGLE_BUTTON
    case ButtonVariant.OAK      => Material.OAK_BUTTON
    case ButtonVariant.SPRUCE   => Material.SPRUCE_BUTTON

    case ButtonVariant.STONE => Material.STONE_BUTTON

    // CARPET
    case CarpetVariant.BLACK      => Material.BLACK_CARPET
    case CarpetVariant.BLUE       => Material.BLUE_CARPET
    case CarpetVariant.BROWN      => Material.BROWN_CARPET
    case CarpetVariant.CYAN       => Material.CYAN_CARPET
    case CarpetVariant.GRAY       => Material.GRAY_CARPET
    case CarpetVariant.GREEN      => Material.GREEN_CARPET
    case CarpetVariant.LIGHT_BLUE => Material.LIGHT_BLUE_CARPET
    case CarpetVariant.LIGHT_GRAY => Material.LIGHT_GRAY_CARPET
    case CarpetVariant.LIME       => Material.LIME_CARPET
    case CarpetVariant.MAGENTA    => Material.MAGENTA_CARPET
    case CarpetVariant.ORANGE     => Material.ORANGE_CARPET
    case CarpetVariant.PINK       => Material.PINK_CARPET
    case CarpetVariant.PURPLE     => Material.PURPLE_CARPET
    case CarpetVariant.RED        => Material.RED_CARPET
    case CarpetVariant.WHITE      => Material.WHITE_CARPET
    case CarpetVariant.YELLOW     => Material.YELLOW_CARPET

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

    // CONCRETE
    case ConcreteVariant.BLACK      => Material.BLACK_CONCRETE
    case ConcreteVariant.BLUE       => Material.BLUE_CONCRETE
    case ConcreteVariant.BROWN      => Material.BROWN_CONCRETE
    case ConcreteVariant.CYAN       => Material.CYAN_CONCRETE
    case ConcreteVariant.GRAY       => Material.GRAY_CONCRETE
    case ConcreteVariant.GREEN      => Material.GREEN_CONCRETE
    case ConcreteVariant.LIGHT_BLUE => Material.LIGHT_BLUE_CONCRETE
    case ConcreteVariant.LIGHT_GRAY => Material.LIGHT_GRAY_CONCRETE
    case ConcreteVariant.LIME       => Material.LIME_CONCRETE
    case ConcreteVariant.MAGENTA    => Material.MAGENTA_CONCRETE
    case ConcreteVariant.ORANGE     => Material.ORANGE_CONCRETE
    case ConcreteVariant.PINK       => Material.PINK_CONCRETE
    case ConcreteVariant.PURPLE     => Material.PURPLE_CONCRETE
    case ConcreteVariant.RED        => Material.RED_CONCRETE
    case ConcreteVariant.WHITE      => Material.WHITE_CONCRETE
    case ConcreteVariant.YELLOW     => Material.YELLOW_CONCRETE

    // CONCRETE_POWDER
    case ConcretePowderVariant.BLACK      => Material.BLACK_CONCRETE_POWDER
    case ConcretePowderVariant.BLUE       => Material.BLUE_CONCRETE_POWDER
    case ConcretePowderVariant.BROWN      => Material.BROWN_CONCRETE_POWDER
    case ConcretePowderVariant.CYAN       => Material.CYAN_CONCRETE_POWDER
    case ConcretePowderVariant.GRAY       => Material.GRAY_CONCRETE_POWDER
    case ConcretePowderVariant.GREEN      => Material.GREEN_CONCRETE_POWDER
    case ConcretePowderVariant.LIGHT_BLUE => Material.LIGHT_BLUE_CONCRETE_POWDER
    case ConcretePowderVariant.LIGHT_GRAY => Material.LIGHT_GRAY_CONCRETE_POWDER
    case ConcretePowderVariant.LIME       => Material.LIME_CONCRETE_POWDER
    case ConcretePowderVariant.MAGENTA    => Material.MAGENTA_CONCRETE_POWDER
    case ConcretePowderVariant.ORANGE     => Material.ORANGE_CONCRETE_POWDER
    case ConcretePowderVariant.PINK       => Material.PINK_CONCRETE_POWDER
    case ConcretePowderVariant.PURPLE     => Material.PURPLE_CONCRETE_POWDER
    case ConcretePowderVariant.RED        => Material.RED_CONCRETE_POWDER
    case ConcretePowderVariant.WHITE      => Material.WHITE_CONCRETE_POWDER
    case ConcretePowderVariant.YELLOW     => Material.YELLOW_CONCRETE_POWDER

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

    // DIORITE
    case DioriteVariant.NORMAL   => Material.DIORITE
    case DioriteVariant.POLISHED => Material.POLISHED_DIORITE

    // DIRT
    case DirtVariant.NORMAL => Material.DIRT
    case DirtVariant.COARSE => Material.COARSE_DIRT

    // DOOR
    case DoorVariant.ACACIA   => Material.ACACIA_DOOR
    case DoorVariant.BIRCH    => Material.BIRCH_DOOR
    case DoorVariant.DARK_OAK => Material.DARK_OAK_DOOR
    case DoorVariant.JUNGLE   => Material.JUNGLE_DOOR
    case DoorVariant.OAK      => Material.OAK_DOOR
    case DoorVariant.SPRUCE   => Material.SPRUCE_DOOR

    case DoorVariant.IRON => Material.IRON_DOOR

    // FENCE
    case FenceVariant.ACACIA   => Material.ACACIA_FENCE
    case FenceVariant.BIRCH    => Material.BIRCH_FENCE
    case FenceVariant.DARK_OAK => Material.DARK_OAK_FENCE
    case FenceVariant.JUNGLE   => Material.JUNGLE_FENCE
    case FenceVariant.OAK      => Material.OAK_FENCE
    case FenceVariant.SPRUCE   => Material.SPRUCE_FENCE

    case FenceVariant.NETHER_BRICK => Material.NETHER_BRICK_FENCE

    // FENCE_GATE
    case FenceGateVariant.ACACIA   => Material.ACACIA_FENCE_GATE
    case FenceGateVariant.BIRCH    => Material.BIRCH_FENCE_GATE
    case FenceGateVariant.DARK_OAK => Material.DARK_OAK_FENCE_GATE
    case FenceGateVariant.JUNGLE   => Material.JUNGLE_FENCE_GATE
    case FenceGateVariant.OAK      => Material.OAK_FENCE_GATE
    case FenceGateVariant.SPRUCE   => Material.SPRUCE_FENCE_GATE

    // FERN
    case FernVariant.NORMAL => Material.FERN
    case FernVariant.TALL   => Material.LARGE_FERN

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

    case FlowerPotVariant.ACACIA   => Material.POTTED_ACACIA_SAPLING
    case FlowerPotVariant.BIRCH    => Material.POTTED_BIRCH_SAPLING
    case FlowerPotVariant.DARK_OAK => Material.POTTED_DARK_OAK_SAPLING
    case FlowerPotVariant.JUNGLE   => Material.POTTED_JUNGLE_SAPLING
    case FlowerPotVariant.OAK      => Material.POTTED_OAK_SAPLING
    case FlowerPotVariant.SPRUCE   => Material.POTTED_SPRUCE_SAPLING

    case FlowerPotVariant.BAMBOO    => Material.POTTED_BAMBOO
    case FlowerPotVariant.CACTUS    => Material.POTTED_CACTUS
    case FlowerPotVariant.DEAD_BUSH => Material.POTTED_DEAD_BUSH
    case FlowerPotVariant.FERN      => Material.POTTED_FERN

    // GLASS
    case GlassVariant.NORMAL     => Material.GLASS
    case GlassVariant.BLACK      => Material.BLACK_STAINED_GLASS
    case GlassVariant.BLUE       => Material.BLUE_STAINED_GLASS
    case GlassVariant.BROWN      => Material.BROWN_STAINED_GLASS
    case GlassVariant.CYAN       => Material.CYAN_STAINED_GLASS
    case GlassVariant.GRAY       => Material.GRAY_STAINED_GLASS
    case GlassVariant.GREEN      => Material.GREEN_STAINED_GLASS
    case GlassVariant.LIGHT_BLUE => Material.LIGHT_BLUE_STAINED_GLASS
    case GlassVariant.LIGHT_GRAY => Material.LIGHT_GRAY_STAINED_GLASS
    case GlassVariant.LIME       => Material.LIME_STAINED_GLASS
    case GlassVariant.MAGENTA    => Material.MAGENTA_STAINED_GLASS
    case GlassVariant.ORANGE     => Material.ORANGE_STAINED_GLASS
    case GlassVariant.PINK       => Material.PINK_STAINED_GLASS
    case GlassVariant.PURPLE     => Material.PURPLE_STAINED_GLASS
    case GlassVariant.RED        => Material.RED_STAINED_GLASS
    case GlassVariant.WHITE      => Material.WHITE_STAINED_GLASS
    case GlassVariant.YELLOW     => Material.YELLOW_STAINED_GLASS

    // GLASS_PANE
    case GlassPaneVariant.NORMAL     => Material.GLASS_PANE
    case GlassPaneVariant.BLACK      => Material.BLACK_STAINED_GLASS_PANE
    case GlassPaneVariant.BLUE       => Material.BLUE_STAINED_GLASS_PANE
    case GlassPaneVariant.BROWN      => Material.BROWN_STAINED_GLASS_PANE
    case GlassPaneVariant.CYAN       => Material.CYAN_STAINED_GLASS_PANE
    case GlassPaneVariant.GRAY       => Material.GRAY_STAINED_GLASS_PANE
    case GlassPaneVariant.GREEN      => Material.GREEN_STAINED_GLASS_PANE
    case GlassPaneVariant.LIGHT_BLUE => Material.LIGHT_BLUE_STAINED_GLASS_PANE
    case GlassPaneVariant.LIGHT_GRAY => Material.LIGHT_GRAY_STAINED_GLASS_PANE
    case GlassPaneVariant.LIME       => Material.LIME_STAINED_GLASS_PANE
    case GlassPaneVariant.MAGENTA    => Material.MAGENTA_STAINED_GLASS_PANE
    case GlassPaneVariant.ORANGE     => Material.ORANGE_STAINED_GLASS_PANE
    case GlassPaneVariant.PINK       => Material.PINK_STAINED_GLASS_PANE
    case GlassPaneVariant.PURPLE     => Material.PURPLE_STAINED_GLASS_PANE
    case GlassPaneVariant.RED        => Material.RED_STAINED_GLASS_PANE
    case GlassPaneVariant.WHITE      => Material.WHITE_STAINED_GLASS_PANE
    case GlassPaneVariant.YELLOW     => Material.YELLOW_STAINED_GLASS_PANE

    // GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.BLACK      => Material.BLACK_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.BLUE       => Material.BLUE_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.BROWN      => Material.BROWN_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.CYAN       => Material.CYAN_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.GRAY       => Material.GRAY_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.GREEN      => Material.GREEN_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.LIGHT_BLUE => Material.LIGHT_BLUE_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.LIGHT_GRAY => Material.LIGHT_GRAY_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.LIME       => Material.LIME_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.MAGENTA    => Material.MAGENTA_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.ORANGE     => Material.ORANGE_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.PINK       => Material.PINK_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.PURPLE     => Material.PURPLE_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.RED        => Material.RED_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.WHITE      => Material.WHITE_GLAZED_TERRACOTTA
    case GlazedTerracottaVariant.YELLOW     => Material.YELLOW_GLAZED_TERRACOTTA

    // GRANITE
    case GraniteVariant.NORMAL   => Material.GRANITE
    case GraniteVariant.POLISHED => Material.POLISHED_GRANITE

    // GRASS
    case GrassVariant.NORMAL => Material.GRASS
    case GrassVariant.TALL   => Material.TALL_GRASS

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

    // LOG
    case LogVariant.ACACIA   => Material.ACACIA_LOG
    case LogVariant.BIRCH    => Material.BIRCH_LOG
    case LogVariant.DARK_OAK => Material.DARK_OAK_LOG
    case LogVariant.JUNGLE   => Material.JUNGLE_LOG
    case LogVariant.OAK      => Material.OAK_LOG
    case LogVariant.SPRUCE   => Material.SPRUCE_LOG

    case LogVariant.STRIPPED_ACACIA   => Material.STRIPPED_ACACIA_LOG
    case LogVariant.STRIPPED_BIRCH    => Material.STRIPPED_BIRCH_LOG
    case LogVariant.STRIPPED_DARK_OAK => Material.STRIPPED_DARK_OAK_LOG
    case LogVariant.STRIPPED_JUNGLE   => Material.STRIPPED_JUNGLE_LOG
    case LogVariant.STRIPPED_OAK      => Material.STRIPPED_OAK_LOG
    case LogVariant.STRIPPED_SPRUCE   => Material.STRIPPED_SPRUCE_LOG

    // MOB_HEAD
    case MobHeadVariant.CREEPER         => Material.CREEPER_HEAD
    case MobHeadVariant.DRAGON          => Material.DRAGON_HEAD
    case MobHeadVariant.PLAYER          => Material.PLAYER_HEAD
    case MobHeadVariant.SKELETON        => Material.SKELETON_SKULL
    case MobHeadVariant.WITHER_SKELETON => Material.WITHER_SKELETON_SKULL
    case MobHeadVariant.ZOMBIE          => Material.ZOMBIE_HEAD

    // MUSHROOM
    case MushroomVariant.BROWN => Material.BROWN_MUSHROOM
    case MushroomVariant.RED   => Material.RED_MUSHROOM

    // MUSHROOM_BLOCK
    case MushroomBlockVariant.BROWN => Material.BROWN_MUSHROOM_BLOCK
    case MushroomBlockVariant.RED   => Material.RED_MUSHROOM_BLOCK
    case MushroomBlockVariant.STEM  => Material.MUSHROOM_STEM

    // PILLAR
    case PillarVariant.PURPUR => Material.PURPUR_PILLAR
    case PillarVariant.QUARTZ => Material.QUARTZ_PILLAR

    // PISTON
    case PistonVariant.NORMAL => Material.PISTON
    case PistonVariant.STICKY => Material.STICKY_PISTON

    // PISTON_HEAD
    case PistonHeadVariant.NORMAL => Material.PISTON_HEAD
    case PistonHeadVariant.STICKY => Material.PISTON_HEAD

    // PLANKS
    case PlanksVariant.ACACIA   => Material.ACACIA_PLANKS
    case PlanksVariant.BIRCH    => Material.BIRCH_PLANKS
    case PlanksVariant.DARK_OAK => Material.DARK_OAK_PLANKS
    case PlanksVariant.JUNGLE   => Material.JUNGLE_PLANKS
    case PlanksVariant.OAK      => Material.OAK_PLANKS
    case PlanksVariant.SPRUCE   => Material.SPRUCE_PLANKS

    // PLANT
    case PlantVariant.SUNFLOWER => Material.SUNFLOWER
    case PlantVariant.LILAC     => Material.LILAC
    case PlantVariant.PEONY     => Material.PEONY
    case PlantVariant.ROSE_BUSH => Material.ROSE_BUSH

    // PRESSURE_PLATE
    case PressurePlateVariant.ACACIA   => Material.ACACIA_PRESSURE_PLATE
    case PressurePlateVariant.BIRCH    => Material.BIRCH_PRESSURE_PLATE
    case PressurePlateVariant.DARK_OAK => Material.DARK_OAK_PRESSURE_PLATE
    case PressurePlateVariant.JUNGLE   => Material.JUNGLE_PRESSURE_PLATE
    case PressurePlateVariant.OAK      => Material.OAK_PRESSURE_PLATE
    case PressurePlateVariant.SPRUCE   => Material.SPRUCE_PRESSURE_PLATE

    case PressurePlateVariant.STONE => Material.STONE_PRESSURE_PLATE

    // PRISMARINE
    case PrismarineVariant.NORMAL => Material.PRISMARINE
    case PrismarineVariant.BRICK  => Material.PRISMARINE_BRICKS
    case PrismarineVariant.DARK   => Material.DARK_PRISMARINE

    // QUARTZ_BLOCK
    case QuartzBlockVariant.NORMAL   => Material.QUARTZ_BLOCK
    case QuartzBlockVariant.CHISELED => Material.CHISELED_QUARTZ_BLOCK
    case QuartzBlockVariant.SMOOTH   => Material.SMOOTH_QUARTZ

    // RAIL
    case RailVariant.NORMAL    => Material.RAIL
    case RailVariant.ACTIVATOR => Material.ACTIVATOR_RAIL
    case RailVariant.DETECTOR  => Material.DETECTOR_RAIL
    case RailVariant.POWERED   => Material.POWERED_RAIL

    // SAND
    case SandVariant.NORMAL => Material.SAND
    case SandVariant.RED    => Material.RED_SAND

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

    // SHULKER_BOX
    case ShulkerBoxVariant.NORMAL     => Material.SHULKER_BOX
    case ShulkerBoxVariant.BLACK      => Material.BLACK_SHULKER_BOX
    case ShulkerBoxVariant.BLUE       => Material.BLUE_SHULKER_BOX
    case ShulkerBoxVariant.BROWN      => Material.BROWN_SHULKER_BOX
    case ShulkerBoxVariant.CYAN       => Material.CYAN_SHULKER_BOX
    case ShulkerBoxVariant.GRAY       => Material.GRAY_SHULKER_BOX
    case ShulkerBoxVariant.GREEN      => Material.GREEN_SHULKER_BOX
    case ShulkerBoxVariant.LIGHT_BLUE => Material.LIGHT_BLUE_SHULKER_BOX
    case ShulkerBoxVariant.LIGHT_GRAY => Material.LIGHT_GRAY_SHULKER_BOX
    case ShulkerBoxVariant.LIME       => Material.LIME_SHULKER_BOX
    case ShulkerBoxVariant.MAGENTA    => Material.MAGENTA_SHULKER_BOX
    case ShulkerBoxVariant.ORANGE     => Material.ORANGE_SHULKER_BOX
    case ShulkerBoxVariant.PINK       => Material.PINK_SHULKER_BOX
    case ShulkerBoxVariant.PURPLE     => Material.PURPLE_SHULKER_BOX
    case ShulkerBoxVariant.RED        => Material.RED_SHULKER_BOX
    case ShulkerBoxVariant.WHITE      => Material.WHITE_SHULKER_BOX
    case ShulkerBoxVariant.YELLOW     => Material.YELLOW_SHULKER_BOX

    // SIGN
    case SignVariant.ACACIA   => Material.ACACIA_SIGN
    case SignVariant.BIRCH    => Material.BIRCH_SIGN
    case SignVariant.DARK_OAK => Material.DARK_OAK_SIGN
    case SignVariant.JUNGLE   => Material.JUNGLE_SIGN
    case SignVariant.OAK      => Material.OAK_SIGN
    case SignVariant.SPRUCE   => Material.SPRUCE_SIGN

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

    // SPONGE
    case SpongeVariant.NORMAL => Material.SPONGE
    case SpongeVariant.WET    => Material.WET_SPONGE

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

    // STONE
    case StoneVariant.NORMAL => Material.STONE
    case StoneVariant.SMOOTH => Material.SMOOTH_STONE

    // STONE_BRICK
    case StoneBrickVariant.NORMAL   => Material.STONE_BRICKS
    case StoneBrickVariant.CHISELED => Material.CHISELED_STONE_BRICKS
    case StoneBrickVariant.CRACKED  => Material.CRACKED_STONE_BRICKS
    case StoneBrickVariant.MOSSY    => Material.MOSSY_STONE_BRICKS

    // STRUCTURE_BLOCK
    case StructureBlockVariant.VOID => Material.STRUCTURE_VOID
    case _: StructureBlockVariant   => Material.STRUCTURE_BLOCK

    // TERRACOTTA
    case TerracottaVariant.NORMAL     => Material.TERRACOTTA
    case TerracottaVariant.BLACK      => Material.BLACK_TERRACOTTA
    case TerracottaVariant.BLUE       => Material.BLUE_TERRACOTTA
    case TerracottaVariant.BROWN      => Material.BROWN_TERRACOTTA
    case TerracottaVariant.CYAN       => Material.CYAN_TERRACOTTA
    case TerracottaVariant.GRAY       => Material.GRAY_TERRACOTTA
    case TerracottaVariant.GREEN      => Material.GREEN_TERRACOTTA
    case TerracottaVariant.LIGHT_BLUE => Material.LIGHT_BLUE_TERRACOTTA
    case TerracottaVariant.LIGHT_GRAY => Material.LIGHT_GRAY_TERRACOTTA
    case TerracottaVariant.LIME       => Material.LIME_TERRACOTTA
    case TerracottaVariant.MAGENTA    => Material.MAGENTA_TERRACOTTA
    case TerracottaVariant.ORANGE     => Material.ORANGE_TERRACOTTA
    case TerracottaVariant.PINK       => Material.PINK_TERRACOTTA
    case TerracottaVariant.PURPLE     => Material.PURPLE_TERRACOTTA
    case TerracottaVariant.RED        => Material.RED_TERRACOTTA
    case TerracottaVariant.WHITE      => Material.WHITE_TERRACOTTA
    case TerracottaVariant.YELLOW     => Material.YELLOW_TERRACOTTA

    // TRAPDOOR
    case TrapdoorVariant.ACACIA   => Material.ACACIA_TRAPDOOR
    case TrapdoorVariant.BIRCH    => Material.BIRCH_TRAPDOOR
    case TrapdoorVariant.DARK_OAK => Material.DARK_OAK_TRAPDOOR
    case TrapdoorVariant.JUNGLE   => Material.JUNGLE_TRAPDOOR
    case TrapdoorVariant.OAK      => Material.OAK_TRAPDOOR
    case TrapdoorVariant.SPRUCE   => Material.SPRUCE_TRAPDOOR

    case TrapdoorVariant.IRON => Material.IRON_TRAPDOOR

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

    // WOOD
    case WoodVariant.ACACIA   => Material.ACACIA_WOOD
    case WoodVariant.BIRCH    => Material.BIRCH_WOOD
    case WoodVariant.DARK_OAK => Material.DARK_OAK_WOOD
    case WoodVariant.JUNGLE   => Material.JUNGLE_WOOD
    case WoodVariant.OAK      => Material.OAK_WOOD
    case WoodVariant.SPRUCE   => Material.SPRUCE_WOOD

    case WoodVariant.STRIPPED_ACACIA   => Material.STRIPPED_ACACIA_WOOD
    case WoodVariant.STRIPPED_BIRCH    => Material.STRIPPED_BIRCH_WOOD
    case WoodVariant.STRIPPED_DARK_OAK => Material.STRIPPED_DARK_OAK_WOOD
    case WoodVariant.STRIPPED_JUNGLE   => Material.STRIPPED_JUNGLE_WOOD
    case WoodVariant.STRIPPED_OAK      => Material.STRIPPED_OAK_WOOD
    case WoodVariant.STRIPPED_SPRUCE   => Material.STRIPPED_SPRUCE_WOOD

    // WOOL
    case WoolVariant.BLACK      => Material.BLACK_WOOL
    case WoolVariant.BLUE       => Material.BLUE_WOOL
    case WoolVariant.BROWN      => Material.BROWN_WOOL
    case WoolVariant.CYAN       => Material.CYAN_WOOL
    case WoolVariant.GRAY       => Material.GRAY_WOOL
    case WoolVariant.GREEN      => Material.GREEN_WOOL
    case WoolVariant.LIGHT_BLUE => Material.LIGHT_BLUE_WOOL
    case WoolVariant.LIGHT_GRAY => Material.LIGHT_GRAY_WOOL
    case WoolVariant.LIME       => Material.LIME_WOOL
    case WoolVariant.MAGENTA    => Material.MAGENTA_WOOL
    case WoolVariant.ORANGE     => Material.ORANGE_WOOL
    case WoolVariant.PINK       => Material.PINK_WOOL
    case WoolVariant.PURPLE     => Material.PURPLE_WOOL
    case WoolVariant.RED        => Material.RED_WOOL
    case WoolVariant.WHITE      => Material.WHITE_WOOL
    case WoolVariant.YELLOW     => Material.YELLOW_WOOL
  }

  def map(block: VariableBlock[_ <: BlockVariant]): Material = block match {
    case _: Comparator => Material.COMPARATOR
    case _: NoteBlock  => Material.NOTE_BLOCK

    case Bamboo(_, BambooVariant.SAPLING, _, _) => Material.BAMBOO_SAPLING
    case _: Bamboo                              => Material.BAMBOO

    case Banner(_, variant, None, _) =>
      variant match {
        case BannerVariant.BLACK      => Material.BLACK_WALL_BANNER
        case BannerVariant.BLUE       => Material.BLUE_WALL_BANNER
        case BannerVariant.BROWN      => Material.BROWN_WALL_BANNER
        case BannerVariant.CYAN       => Material.CYAN_WALL_BANNER
        case BannerVariant.GRAY       => Material.GRAY_WALL_BANNER
        case BannerVariant.GREEN      => Material.GREEN_WALL_BANNER
        case BannerVariant.LIGHT_BLUE => Material.LIGHT_BLUE_WALL_BANNER
        case BannerVariant.LIGHT_GRAY => Material.LIGHT_GRAY_WALL_BANNER
        case BannerVariant.LIME       => Material.LIME_WALL_BANNER
        case BannerVariant.MAGENTA    => Material.MAGENTA_WALL_BANNER
        case BannerVariant.ORANGE     => Material.ORANGE_WALL_BANNER
        case BannerVariant.PINK       => Material.PINK_WALL_BANNER
        case BannerVariant.PURPLE     => Material.PURPLE_WALL_BANNER
        case BannerVariant.RED        => Material.RED_WALL_BANNER
        case BannerVariant.WHITE      => Material.WHITE_WALL_BANNER
        case BannerVariant.YELLOW     => Material.YELLOW_WALL_BANNER
      }

    case CoralFan(_, variant, Some(_), _) =>
      variant match {
        case CoralFanVariant.BRAIN  => Material.BRAIN_CORAL_WALL_FAN
        case CoralFanVariant.BUBBLE => Material.BUBBLE_CORAL_WALL_FAN
        case CoralFanVariant.FIRE   => Material.FIRE_CORAL_WALL_FAN
        case CoralFanVariant.HORN   => Material.HORN_CORAL_WALL_FAN
        case CoralFanVariant.TUBE   => Material.TUBE_CORAL_WALL_FAN

        case CoralFanVariant.DEAD_BRAIN  => Material.DEAD_BRAIN_CORAL_WALL_FAN
        case CoralFanVariant.DEAD_BUBBLE => Material.DEAD_BUBBLE_CORAL_WALL_FAN
        case CoralFanVariant.DEAD_FIRE   => Material.DEAD_FIRE_CORAL_WALL_FAN
        case CoralFanVariant.DEAD_HORN   => Material.DEAD_HORN_CORAL_WALL_FAN
        case CoralFanVariant.DEAD_TUBE   => Material.DEAD_TUBE_CORAL_WALL_FAN
      }

    case MobHead(_, variant, Some(_), _) =>
      variant match {
        case MobHeadVariant.CREEPER         => Material.CREEPER_WALL_HEAD
        case MobHeadVariant.DRAGON          => Material.DRAGON_WALL_HEAD
        case MobHeadVariant.PLAYER          => Material.PLAYER_WALL_HEAD
        case MobHeadVariant.SKELETON        => Material.SKELETON_WALL_SKULL
        case MobHeadVariant.WITHER_SKELETON => Material.WITHER_SKELETON_WALL_SKULL
        case MobHeadVariant.ZOMBIE          => Material.ZOMBIE_WALL_HEAD
      }

    case Sign(_, variant, Some(_), _, _, _, _) =>
      variant match {
        case SignVariant.ACACIA   => Material.ACACIA_WALL_SIGN
        case SignVariant.BIRCH    => Material.BIRCH_WALL_SIGN
        case SignVariant.DARK_OAK => Material.DARK_OAK_WALL_SIGN
        case SignVariant.JUNGLE   => Material.JUNGLE_WALL_SIGN
        case SignVariant.OAK      => Material.OAK_WALL_SIGN
        case SignVariant.SPRUCE   => Material.SPRUCE_WALL_SIGN
      }

    case it: VariableBlock[_] => map(it.variant)
  }

  def map(block: VariableBlock[_], data: SpigotBlockData): Unit = block match {
    case Bamboo(_, variant, _, _) =>
      if (variant != BambooVariant.SAPLING) {
        val leaves = variant match {
          case BambooVariant.NO_LEAVES    => SpigotBambooLeaves.NONE
          case BambooVariant.SMALL_LEAVES => SpigotBambooLeaves.SMALL
          case BambooVariant.LARGE_LEAVES => SpigotBambooLeaves.LARGE
          case BambooVariant.SAPLING      => null
        }
        data.asInstanceOf[SpigotBamboo].setLeaves(leaves)
      }

    case Comparator(_, variant, _, _) =>
      val mode = variant match {
        case ComparatorVariant.COMPARE  => SpigotComparatorMode.COMPARE
        case ComparatorVariant.SUBTRACT => SpigotComparatorMode.SUBTRACT
      }
      data.asInstanceOf[SpigotComparator].setMode(mode)

    case NoteBlock(_, variant, _, _) =>
      val instrument = variant match {
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
      data.asInstanceOf[SpigotNoteBlock].setInstrument(instrument)

    case PistonHead(_, variant, _, _) =>
      val `type` = variant match {
        case PistonHeadVariant.NORMAL => SpigotPistonType.NORMAL
        case PistonHeadVariant.STICKY => SpigotPistonType.STICKY
      }
      data.asInstanceOf[SpigotPistonHead].setType(`type`)

    case StructureBlock(_, variant) =>
      if (variant != StructureBlockVariant.VOID) {
        val mode = variant match {
          case StructureBlockVariant.CORNER => SpigotStructureBlockMode.CORNER
          case StructureBlockVariant.DATA   => SpigotStructureBlockMode.DATA
          case StructureBlockVariant.LOAD   => SpigotStructureBlockMode.LOAD
          case StructureBlockVariant.SAVE   => SpigotStructureBlockMode.SAVE
          case StructureBlockVariant.VOID   => null
        }
        data.asInstanceOf[SpigotStructureBlock].setMode(mode)
      }
  }
}
