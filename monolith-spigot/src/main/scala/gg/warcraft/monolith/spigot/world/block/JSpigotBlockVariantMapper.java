package gg.warcraft.monolith.spigot.world.block;

import gg.warcraft.monolith.api.world.block.BlockVariant;
import gg.warcraft.monolith.api.world.block.variant.*;
import org.bukkit.Material;

public class JSpigotBlockVariantMapper extends SpigotBlockVariantMapper {
    @Override
    public BlockVariant map(Material material) {
        switch (material) {
            // AIR
            case AIR:
                return AirVariant.NORMAL;
            case CAVE_AIR:
                return AirVariant.CAVE;
            case VOID_AIR:
                return AirVariant.VOID;

            // ANDESITE
            case ANDESITE:
                return AndesiteVariant.NORMAL;
            case POLISHED_ANDESITE:
                return AndesiteVariant.POLISHED;

            // ANVIL
            case ANVIL:
                return AnvilVariant.NORMAL;
            case CHIPPED_ANVIL:
                return AnvilVariant.CHIPPED;
            case DAMAGED_ANVIL:
                return AnvilVariant.DAMAGED;

            // BANNER
            case BLACK_BANNER:
                return BannerVariant.BLACK;
            case BLUE_BANNER:
                return BannerVariant.BLUE;
            case BROWN_BANNER:
                return BannerVariant.BROWN;
            case CYAN_BANNER:
                return BannerVariant.CYAN;
            case GRAY_BANNER:
                return BannerVariant.GRAY;
            case GREEN_BANNER:
                return BannerVariant.GREEN;
            case LIGHT_BLUE_BANNER:
                return BannerVariant.LIGHT_BLUE;
            case LIGHT_GRAY_BANNER:
                return BannerVariant.LIGHT_GRAY;
            case LIME_BANNER:
                return BannerVariant.LIME;
            case MAGENTA_BANNER:
                return BannerVariant.MAGENTA;
            case ORANGE_BANNER:
                return BannerVariant.ORANGE;
            case PINK_BANNER:
                return BannerVariant.PINK;
            case PURPLE_BANNER:
                return BannerVariant.PURPLE;
            case RED_BANNER:
                return BannerVariant.RED;
            case WHITE_BANNER:
                return BannerVariant.WHITE;
            case YELLOW_BANNER:
                return BannerVariant.YELLOW;

            case BLACK_WALL_BANNER:
                return BannerVariant.BLACK;
            case BLUE_WALL_BANNER:
                return BannerVariant.BLUE;
            case BROWN_WALL_BANNER:
                return BannerVariant.BROWN;
            case CYAN_WALL_BANNER:
                return BannerVariant.CYAN;
            case GRAY_WALL_BANNER:
                return BannerVariant.GRAY;
            case GREEN_WALL_BANNER:
                return BannerVariant.GREEN;
            case LIGHT_BLUE_WALL_BANNER:
                return BannerVariant.LIGHT_BLUE;
            case LIGHT_GRAY_WALL_BANNER:
                return BannerVariant.LIGHT_GRAY;
            case LIME_WALL_BANNER:
                return BannerVariant.LIME;
            case MAGENTA_WALL_BANNER:
                return BannerVariant.MAGENTA;
            case ORANGE_WALL_BANNER:
                return BannerVariant.ORANGE;
            case PINK_WALL_BANNER:
                return BannerVariant.PINK;
            case PURPLE_WALL_BANNER:
                return BannerVariant.PURPLE;
            case RED_WALL_BANNER:
                return BannerVariant.RED;
            case WHITE_WALL_BANNER:
                return BannerVariant.WHITE;
            case YELLOW_WALL_BANNER:
                return BannerVariant.YELLOW;

            // BED
            case BLACK_BED:
                return BedVariant.BLACK;
            case BLUE_BED:
                return BedVariant.BLUE;
            case BROWN_BED:
                return BedVariant.BROWN;
            case CYAN_BED:
                return BedVariant.CYAN;
            case GRAY_BED:
                return BedVariant.GRAY;
            case GREEN_BED:
                return BedVariant.GREEN;
            case LIGHT_BLUE_BED:
                return BedVariant.LIGHT_BLUE;
            case LIGHT_GRAY_BED:
                return BedVariant.LIGHT_GRAY;
            case LIME_BED:
                return BedVariant.LIME;
            case MAGENTA_BED:
                return BedVariant.MAGENTA;
            case ORANGE_BED:
                return BedVariant.ORANGE;
            case PINK_BED:
                return BedVariant.PINK;
            case PURPLE_BED:
                return BedVariant.PURPLE;
            case RED_BED:
                return BedVariant.RED;
            case WHITE_BED:
                return BedVariant.WHITE;
            case YELLOW_BED:
                return BedVariant.YELLOW;

            // BRICK_BLOCK
            case BRICKS:
                return BrickBlockVariant.NORMAL;
            case NETHER_BRICKS:
                return BrickBlockVariant.NETHER;
            case RED_NETHER_BRICKS:
                return BrickBlockVariant.RED_NETHER;

            // BUTTON
            case ACACIA_BUTTON:
                return ButtonVariant.ACACIA;
            case BIRCH_BUTTON:
                return ButtonVariant.BIRCH;
            case DARK_OAK_BUTTON:
                return ButtonVariant.DARK_OAK;
            case JUNGLE_BUTTON:
                return ButtonVariant.JUNGLE;
            case OAK_BUTTON:
                return ButtonVariant.OAK;
            case SPRUCE_BUTTON:
                return ButtonVariant.SPRUCE;

            case STONE_BUTTON:
                return ButtonVariant.STONE;

            // CARPET
            case BLACK_CARPET:
                return CarpetVariant.BLACK;
            case BLUE_CARPET:
                return CarpetVariant.BLUE;
            case BROWN_CARPET:
                return CarpetVariant.BROWN;
            case CYAN_CARPET:
                return CarpetVariant.CYAN;
            case GRAY_CARPET:
                return CarpetVariant.GRAY;
            case GREEN_CARPET:
                return CarpetVariant.GREEN;
            case LIGHT_BLUE_CARPET:
                return CarpetVariant.LIGHT_BLUE;
            case LIGHT_GRAY_CARPET:
                return CarpetVariant.LIGHT_GRAY;
            case LIME_CARPET:
                return CarpetVariant.LIME;
            case MAGENTA_CARPET:
                return CarpetVariant.MAGENTA;
            case ORANGE_CARPET:
                return CarpetVariant.ORANGE;
            case PINK_CARPET:
                return CarpetVariant.PINK;
            case PURPLE_CARPET:
                return CarpetVariant.PURPLE;
            case RED_CARPET:
                return CarpetVariant.RED;
            case WHITE_CARPET:
                return CarpetVariant.WHITE;
            case YELLOW_CARPET:
                return CarpetVariant.YELLOW;

            // CHEST
            case CHEST:
                return ChestVariant.NORMAL;
            case ENDER_CHEST:
                return ChestVariant.ENDER;
            case TRAPPED_CHEST:
                return ChestVariant.TRAPPED;

            // COBBLESTONE
            case COBBLESTONE:
                return CobblestoneVariant.NORMAL;
            case MOSSY_COBBLESTONE:
                return CobblestoneVariant.MOSSY;

            // COMMAND_BLOCK
            case COMMAND_BLOCK:
                return CommandBlockVariant.NORMAL;
            case CHAIN_COMMAND_BLOCK:
                return CommandBlockVariant.CHAIN;
            case REPEATING_COMMAND_BLOCK:
                return CommandBlockVariant.REPEATING;

            // CONCRETE
            case BLACK_CONCRETE:
                return ConcreteVariant.BLACK;
            case BLUE_CONCRETE:
                return ConcreteVariant.BLUE;
            case BROWN_CONCRETE:
                return ConcreteVariant.BROWN;
            case CYAN_CONCRETE:
                return ConcreteVariant.CYAN;
            case GRAY_CONCRETE:
                return ConcreteVariant.GRAY;
            case GREEN_CONCRETE:
                return ConcreteVariant.GREEN;
            case LIGHT_BLUE_CONCRETE:
                return ConcreteVariant.LIGHT_BLUE;
            case LIGHT_GRAY_CONCRETE:
                return ConcreteVariant.LIGHT_GRAY;
            case LIME_CONCRETE:
                return ConcreteVariant.LIME;
            case MAGENTA_CONCRETE:
                return ConcreteVariant.MAGENTA;
            case ORANGE_CONCRETE:
                return ConcreteVariant.ORANGE;
            case PINK_CONCRETE:
                return ConcreteVariant.PINK;
            case PURPLE_CONCRETE:
                return ConcreteVariant.PURPLE;
            case RED_CONCRETE:
                return ConcreteVariant.RED;
            case WHITE_CONCRETE:
                return ConcreteVariant.WHITE;
            case YELLOW_CONCRETE:
                return ConcreteVariant.YELLOW;

            // CONCRETE_POWDER
            case BLACK_CONCRETE_POWDER:
                return ConcretePowderVariant.BLACK;
            case BLUE_CONCRETE_POWDER:
                return ConcretePowderVariant.BLUE;
            case BROWN_CONCRETE_POWDER:
                return ConcretePowderVariant.BROWN;
            case CYAN_CONCRETE_POWDER:
                return ConcretePowderVariant.CYAN;
            case GRAY_CONCRETE_POWDER:
                return ConcretePowderVariant.GRAY;
            case GREEN_CONCRETE_POWDER:
                return ConcretePowderVariant.GREEN;
            case LIGHT_BLUE_CONCRETE_POWDER:
                return ConcretePowderVariant.LIGHT_BLUE;
            case LIGHT_GRAY_CONCRETE_POWDER:
                return ConcretePowderVariant.LIGHT_GRAY;
            case LIME_CONCRETE_POWDER:
                return ConcretePowderVariant.LIME;
            case MAGENTA_CONCRETE_POWDER:
                return ConcretePowderVariant.MAGENTA;
            case ORANGE_CONCRETE_POWDER:
                return ConcretePowderVariant.ORANGE;
            case PINK_CONCRETE_POWDER:
                return ConcretePowderVariant.PINK;
            case PURPLE_CONCRETE_POWDER:
                return ConcretePowderVariant.PURPLE;
            case RED_CONCRETE_POWDER:
                return ConcretePowderVariant.RED;
            case WHITE_CONCRETE_POWDER:
                return ConcretePowderVariant.WHITE;
            case YELLOW_CONCRETE_POWDER:
                return ConcretePowderVariant.YELLOW;

            // CORAL
            case BRAIN_CORAL:
                return CoralVariant.BRAIN;
            case BUBBLE_CORAL:
                return CoralVariant.BUBBLE;
            case FIRE_CORAL:
                return CoralVariant.FIRE;
            case HORN_CORAL:
                return CoralVariant.HORN;
            case TUBE_CORAL:
                return CoralVariant.TUBE;

            case DEAD_BRAIN_CORAL:
                return CoralVariant.DEAD_BRAIN;
            case DEAD_BUBBLE_CORAL:
                return CoralVariant.DEAD_BUBBLE;
            case DEAD_FIRE_CORAL:
                return CoralVariant.DEAD_FIRE;
            case DEAD_HORN_CORAL:
                return CoralVariant.DEAD_HORN;
            case DEAD_TUBE_CORAL:
                return CoralVariant.DEAD_TUBE;

            // CORAL_BLOCK
            case BRAIN_CORAL_BLOCK:
                return CoralBlockVariant.BRAIN;
            case BUBBLE_CORAL_BLOCK:
                return CoralBlockVariant.BUBBLE;
            case FIRE_CORAL_BLOCK:
                return CoralBlockVariant.FIRE;
            case HORN_CORAL_BLOCK:
                return CoralBlockVariant.HORN;
            case TUBE_CORAL_BLOCK:
                return CoralBlockVariant.TUBE;

            case DEAD_BRAIN_CORAL_BLOCK:
                return CoralBlockVariant.DEAD_BRAIN;
            case DEAD_BUBBLE_CORAL_BLOCK:
                return CoralBlockVariant.DEAD_BUBBLE;
            case DEAD_FIRE_CORAL_BLOCK:
                return CoralBlockVariant.DEAD_FIRE;
            case DEAD_HORN_CORAL_BLOCK:
                return CoralBlockVariant.DEAD_HORN;
            case DEAD_TUBE_CORAL_BLOCK:
                return CoralBlockVariant.DEAD_TUBE;

            // CORAL_FAN
            case BRAIN_CORAL_FAN:
                return CoralFanVariant.BRAIN;
            case BUBBLE_CORAL_FAN:
                return CoralFanVariant.BUBBLE;
            case FIRE_CORAL_FAN:
                return CoralFanVariant.FIRE;
            case HORN_CORAL_FAN:
                return CoralFanVariant.HORN;
            case TUBE_CORAL_FAN:
                return CoralFanVariant.TUBE;

            case DEAD_BRAIN_CORAL_FAN:
                return CoralFanVariant.DEAD_BRAIN;
            case DEAD_BUBBLE_CORAL_FAN:
                return CoralFanVariant.DEAD_BUBBLE;
            case DEAD_FIRE_CORAL_FAN:
                return CoralFanVariant.DEAD_FIRE;
            case DEAD_HORN_CORAL_FAN:
                return CoralFanVariant.DEAD_HORN;
            case DEAD_TUBE_CORAL_FAN:
                return CoralFanVariant.DEAD_TUBE;

            case BRAIN_CORAL_WALL_FAN:
                return CoralFanVariant.BRAIN;
            case BUBBLE_CORAL_WALL_FAN:
                return CoralFanVariant.BUBBLE;
            case FIRE_CORAL_WALL_FAN:
                return CoralFanVariant.FIRE;
            case HORN_CORAL_WALL_FAN:
                return CoralFanVariant.HORN;
            case TUBE_CORAL_WALL_FAN:
                return CoralFanVariant.TUBE;

            case DEAD_BRAIN_CORAL_WALL_FAN:
                return CoralFanVariant.DEAD_BRAIN;
            case DEAD_BUBBLE_CORAL_WALL_FAN:
                return CoralFanVariant.DEAD_BUBBLE;
            case DEAD_FIRE_CORAL_WALL_FAN:
                return CoralFanVariant.DEAD_FIRE;
            case DEAD_HORN_CORAL_WALL_FAN:
                return CoralFanVariant.DEAD_HORN;
            case DEAD_TUBE_CORAL_WALL_FAN:
                return CoralFanVariant.DEAD_TUBE;

            // DIORITE
            case DIORITE:
                return DioriteVariant.NORMAL;
            case POLISHED_DIORITE:
                return DioriteVariant.POLISHED;

            // DIRT
            case DIRT:
                return DirtVariant.NORMAL;
            case COARSE_DIRT:
                return DirtVariant.COARSE;

            // DOOR
            case ACACIA_DOOR:
                return DoorVariant.ACACIA;
            case BIRCH_DOOR:
                return DoorVariant.BIRCH;
            case DARK_OAK_DOOR:
                return DoorVariant.DARK_OAK;
            case JUNGLE_DOOR:
                return DoorVariant.JUNGLE;
            case OAK_DOOR:
                return DoorVariant.OAK;
            case SPRUCE_DOOR:
                return DoorVariant.SPRUCE;

            case IRON_DOOR:
                return DoorVariant.IRON;

            // FENCE
            case ACACIA_FENCE:
                return FenceVariant.ACACIA;
            case BIRCH_FENCE:
                return FenceVariant.BIRCH;
            case DARK_OAK_FENCE:
                return FenceVariant.DARK_OAK;
            case JUNGLE_FENCE:
                return FenceVariant.JUNGLE;
            case OAK_FENCE:
                return FenceVariant.OAK;
            case SPRUCE_FENCE:
                return FenceVariant.SPRUCE;

            case NETHER_BRICK_FENCE:
                return FenceVariant.NETHER_BRICK;

            // FENCE_GATE
            case ACACIA_FENCE_GATE:
                return FenceGateVariant.ACACIA;
            case BIRCH_FENCE_GATE:
                return FenceGateVariant.BIRCH;
            case DARK_OAK_FENCE_GATE:
                return FenceGateVariant.DARK_OAK;
            case JUNGLE_FENCE_GATE:
                return FenceGateVariant.JUNGLE;
            case OAK_FENCE_GATE:
                return FenceGateVariant.OAK;
            case SPRUCE_FENCE_GATE:
                return FenceGateVariant.SPRUCE;

            // FERN
            case FERN:
                return FernVariant.NORMAL;
            case LARGE_FERN:
                return FernVariant.TALL;

            // FLOWER
            case ALLIUM:
                return FlowerVariant.ALLIUM;
            case AZURE_BLUET:
                return FlowerVariant.AZURE_BLUET;
            case BLUE_ORCHID:
                return FlowerVariant.BLUE_ORCHID;
            case CORNFLOWER:
                return FlowerVariant.CORNFLOWER;
            case DANDELION:
                return FlowerVariant.DANDELION;
            case LILY_OF_THE_VALLEY:
                return FlowerVariant.LILY_OF_THE_VALLEY;
            case ORANGE_TULIP:
                return FlowerVariant.ORANGE_TULIP;
            case OXEYE_DAISY:
                return FlowerVariant.OXEYE_DAISY;
            case PINK_TULIP:
                return FlowerVariant.PINK_TULIP;
            case POPPY:
                return FlowerVariant.POPPY;
            case RED_TULIP:
                return FlowerVariant.RED_TULIP;
            case WHITE_TULIP:
                return FlowerVariant.WHITE_TULIP;
            case WITHER_ROSE:
                return FlowerVariant.WITHER_ROSE;

            // FLOWER_POT
            case FLOWER_POT:
                return FlowerPotVariant.EMPTY;

            case POTTED_ALLIUM:
                return FlowerPotVariant.ALLIUM;
            case POTTED_AZURE_BLUET:
                return FlowerPotVariant.AZURE_BLUET;
            case POTTED_BLUE_ORCHID:
                return FlowerPotVariant.BLUE_ORCHID;
            case POTTED_CORNFLOWER:
                return FlowerPotVariant.CORNFLOWER;
            case POTTED_DANDELION:
                return FlowerPotVariant.DANDELION;
            case POTTED_ORANGE_TULIP:
                return FlowerPotVariant.ORANGE_TULIP;
            case POTTED_OXEYE_DAISY:
                return FlowerPotVariant.OXEYE_DAISY;
            case POTTED_PINK_TULIP:
                return FlowerPotVariant.PINK_TULIP;
            case POTTED_POPPY:
                return FlowerPotVariant.POPPY;
            case POTTED_RED_TULIP:
                return FlowerPotVariant.RED_TULIP;
            case POTTED_WHITE_TULIP:
                return FlowerPotVariant.WHITE_TULIP;
            case POTTED_WITHER_ROSE:
                return FlowerPotVariant.WITHER_ROSE;
            case POTTED_LILY_OF_THE_VALLEY:
                return FlowerPotVariant.LILY_OF_THE_VALLEY;

            case POTTED_BROWN_MUSHROOM:
                return FlowerPotVariant.BROWN_MUSHROOM;
            case POTTED_RED_MUSHROOM:
                return FlowerPotVariant.RED_MUSHROOM;

            case POTTED_ACACIA_SAPLING:
                return FlowerPotVariant.ACACIA;
            case POTTED_BIRCH_SAPLING:
                return FlowerPotVariant.BIRCH;
            case POTTED_DARK_OAK_SAPLING:
                return FlowerPotVariant.DARK_OAK;
            case POTTED_JUNGLE_SAPLING:
                return FlowerPotVariant.JUNGLE;
            case POTTED_OAK_SAPLING:
                return FlowerPotVariant.OAK;
            case POTTED_SPRUCE_SAPLING:
                return FlowerPotVariant.SPRUCE;

            case POTTED_BAMBOO:
                return FlowerPotVariant.BAMBOO;
            case POTTED_CACTUS:
                return FlowerPotVariant.CACTUS;
            case POTTED_DEAD_BUSH:
                return FlowerPotVariant.DEAD_BUSH;
            case POTTED_FERN:
                return FlowerPotVariant.FERN;

            // GLASS
            case GLASS:
                return GlassVariant.NORMAL;
            case BLACK_STAINED_GLASS:
                return GlassVariant.BLACK;
            case BLUE_STAINED_GLASS:
                return GlassVariant.BLUE;
            case BROWN_STAINED_GLASS:
                return GlassVariant.BROWN;
            case CYAN_STAINED_GLASS:
                return GlassVariant.CYAN;
            case GRAY_STAINED_GLASS:
                return GlassVariant.GRAY;
            case GREEN_STAINED_GLASS:
                return GlassVariant.GREEN;
            case LIGHT_BLUE_STAINED_GLASS:
                return GlassVariant.LIGHT_BLUE;
            case LIGHT_GRAY_STAINED_GLASS:
                return GlassVariant.LIGHT_GRAY;
            case LIME_STAINED_GLASS:
                return GlassVariant.LIME;
            case MAGENTA_STAINED_GLASS:
                return GlassVariant.MAGENTA;
            case ORANGE_STAINED_GLASS:
                return GlassVariant.ORANGE;
            case PINK_STAINED_GLASS:
                return GlassVariant.PINK;
            case PURPLE_STAINED_GLASS:
                return GlassVariant.PURPLE;
            case RED_STAINED_GLASS:
                return GlassVariant.RED;
            case WHITE_STAINED_GLASS:
                return GlassVariant.WHITE;
            case YELLOW_STAINED_GLASS:
                return GlassVariant.YELLOW;

            // GLASS_PANE
            case GLASS_PANE:
                return GlassPaneVariant.NORMAL;
            case BLACK_STAINED_GLASS_PANE:
                return GlassPaneVariant.BLACK;
            case BLUE_STAINED_GLASS_PANE:
                return GlassPaneVariant.BLUE;
            case BROWN_STAINED_GLASS_PANE:
                return GlassPaneVariant.BROWN;
            case CYAN_STAINED_GLASS_PANE:
                return GlassPaneVariant.CYAN;
            case GRAY_STAINED_GLASS_PANE:
                return GlassPaneVariant.GRAY;
            case GREEN_STAINED_GLASS_PANE:
                return GlassPaneVariant.GREEN;
            case LIGHT_BLUE_STAINED_GLASS_PANE:
                return GlassPaneVariant.LIGHT_BLUE;
            case LIGHT_GRAY_STAINED_GLASS_PANE:
                return GlassPaneVariant.LIGHT_GRAY;
            case LIME_STAINED_GLASS_PANE:
                return GlassPaneVariant.LIME;
            case MAGENTA_STAINED_GLASS_PANE:
                return GlassPaneVariant.MAGENTA;
            case ORANGE_STAINED_GLASS_PANE:
                return GlassPaneVariant.ORANGE;
            case PINK_STAINED_GLASS_PANE:
                return GlassPaneVariant.PINK;
            case PURPLE_STAINED_GLASS_PANE:
                return GlassPaneVariant.PURPLE;
            case RED_STAINED_GLASS_PANE:
                return GlassPaneVariant.RED;
            case WHITE_STAINED_GLASS_PANE:
                return GlassPaneVariant.WHITE;
            case YELLOW_STAINED_GLASS_PANE:
                return GlassPaneVariant.YELLOW;

            // GLAZED_TERRACOTTA
            case BLACK_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.BLACK;
            case BLUE_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.BLUE;
            case BROWN_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.BROWN;
            case CYAN_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.CYAN;
            case GRAY_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.GRAY;
            case GREEN_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.GREEN;
            case LIGHT_BLUE_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.LIGHT_BLUE;
            case LIGHT_GRAY_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.LIGHT_GRAY;
            case LIME_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.LIME;
            case MAGENTA_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.MAGENTA;
            case ORANGE_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.ORANGE;
            case PINK_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.PINK;
            case PURPLE_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.PURPLE;
            case RED_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.RED;
            case WHITE_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.WHITE;
            case YELLOW_GLAZED_TERRACOTTA:
                return GlazedTerracottaVariant.YELLOW;

            // GRANITE
            case GRANITE:
                return GraniteVariant.NORMAL;
            case POLISHED_GRANITE:
                return GraniteVariant.POLISHED;

            // GRASS
            case GRASS:
                return GrassVariant.NORMAL;
            case TALL_GRASS:
                return GrassVariant.TALL;

            // ICE
            case ICE:
                return IceVariant.NORMAL;
            case BLUE_ICE:
                return IceVariant.BLUE;
            case PACKED_ICE:
                return IceVariant.PACKED;

            // INFESTED_BLOCK
            case INFESTED_STONE:
                return InfestedBlockVariant.STONE;
            case INFESTED_COBBLESTONE:
                return InfestedBlockVariant.COBBLESTONE;

            case INFESTED_STONE_BRICKS:
                return
                        InfestedBlockVariant.STONE_BRICK;
            case INFESTED_CHISELED_STONE_BRICKS:
                return
                        InfestedBlockVariant.CHISELED_STONE_BRICK;
            case INFESTED_CRACKED_STONE_BRICKS:
                return
                        InfestedBlockVariant.CRACKED_STONE_BRICK;
            case INFESTED_MOSSY_STONE_BRICKS:
                return
                        InfestedBlockVariant.MOSSY_STONE_BRICK;

            // LEAVES
            case ACACIA_LEAVES:
                return LeavesVariant.ACACIA;
            case BIRCH_LEAVES:
                return LeavesVariant.BIRCH;
            case DARK_OAK_LEAVES:
                return LeavesVariant.DARK_OAK;
            case JUNGLE_LEAVES:
                return LeavesVariant.JUNGLE;
            case OAK_LEAVES:
                return LeavesVariant.OAK;
            case SPRUCE_LEAVES:
                return LeavesVariant.SPRUCE;

            // LOG
            case ACACIA_LOG:
                return LogVariant.ACACIA;
            case BIRCH_LOG:
                return LogVariant.BIRCH;
            case DARK_OAK_LOG:
                return LogVariant.DARK_OAK;
            case JUNGLE_LOG:
                return LogVariant.JUNGLE;
            case OAK_LOG:
                return LogVariant.OAK;
            case SPRUCE_LOG:
                return LogVariant.SPRUCE;

            case STRIPPED_ACACIA_LOG:
                return LogVariant.STRIPPED_ACACIA;
            case STRIPPED_BIRCH_LOG:
                return LogVariant.STRIPPED_BIRCH;
            case STRIPPED_DARK_OAK_LOG:
                return LogVariant.STRIPPED_DARK_OAK;
            case STRIPPED_JUNGLE_LOG:
                return LogVariant.STRIPPED_JUNGLE;
            case STRIPPED_OAK_LOG:
                return LogVariant.STRIPPED_OAK;
            case STRIPPED_SPRUCE_LOG:
                return LogVariant.STRIPPED_SPRUCE;

            // MOB_HEAD
            case CREEPER_HEAD:
                return MobHeadVariant.CREEPER;
            case DRAGON_HEAD:
                return MobHeadVariant.DRAGON;
            case PLAYER_HEAD:
                return MobHeadVariant.PLAYER;
            case SKELETON_SKULL:
                return MobHeadVariant.SKELETON;
            case WITHER_SKELETON_SKULL:
                return MobHeadVariant.WITHER_SKELETON;
            case ZOMBIE_HEAD:
                return MobHeadVariant.ZOMBIE;

            case CREEPER_WALL_HEAD:
                return MobHeadVariant.CREEPER;
            case DRAGON_WALL_HEAD:
                return MobHeadVariant.DRAGON;
            case PLAYER_WALL_HEAD:
                return MobHeadVariant.PLAYER;
            case SKELETON_WALL_SKULL:
                return MobHeadVariant.SKELETON;
            case WITHER_SKELETON_WALL_SKULL:
                return MobHeadVariant.WITHER_SKELETON;
            case ZOMBIE_WALL_HEAD:
                return MobHeadVariant.ZOMBIE;

            // MUSHROOM
            case BROWN_MUSHROOM:
                return MushroomVariant.BROWN;
            case RED_MUSHROOM:
                return MushroomVariant.RED;

            // MUSHROOM_BLOCK
            case BROWN_MUSHROOM_BLOCK:
                return MushroomBlockVariant.BROWN;
            case RED_MUSHROOM_BLOCK:
                return MushroomBlockVariant.RED;
            case MUSHROOM_STEM:
                return MushroomBlockVariant.STEM;

            // PILLAR
            case PURPUR_PILLAR:
                return PillarVariant.PURPUR;
            case QUARTZ_PILLAR:
                return PillarVariant.QUARTZ;

            // PISTON
            case PISTON:
                return PistonVariant.NORMAL;
            case STICKY_PISTON:
                return PistonVariant.STICKY;

            // PLANKS
            case ACACIA_PLANKS:
                return PlanksVariant.ACACIA;
            case BIRCH_PLANKS:
                return PlanksVariant.BIRCH;
            case DARK_OAK_PLANKS:
                return PlanksVariant.DARK_OAK;
            case JUNGLE_PLANKS:
                return PlanksVariant.JUNGLE;
            case OAK_PLANKS:
                return PlanksVariant.OAK;
            case SPRUCE_PLANKS:
                return PlanksVariant.SPRUCE;

            // PLANT
            case SUNFLOWER:
                return PlantVariant.SUNFLOWER;
            case LILAC:
                return PlantVariant.LILAC;
            case PEONY:
                return PlantVariant.PEONY;
            case ROSE_BUSH:
                return PlantVariant.ROSE_BUSH;

            // PRESSURE_PLATE
            case ACACIA_PRESSURE_PLATE:
                return PressurePlateVariant.ACACIA;
            case BIRCH_PRESSURE_PLATE:
                return PressurePlateVariant.BIRCH;
            case DARK_OAK_PRESSURE_PLATE:
                return PressurePlateVariant.DARK_OAK;
            case JUNGLE_PRESSURE_PLATE:
                return PressurePlateVariant.JUNGLE;
            case OAK_PRESSURE_PLATE:
                return PressurePlateVariant.OAK;
            case SPRUCE_PRESSURE_PLATE:
                return PressurePlateVariant.SPRUCE;

            case STONE_PRESSURE_PLATE:
                return PressurePlateVariant.STONE;

            // PRISMARINE
            case PRISMARINE:
                return PrismarineVariant.NORMAL;
            case DARK_PRISMARINE:
                return PrismarineVariant.DARK;
            case PRISMARINE_BRICKS:
                return PrismarineVariant.BRICK;

            // QUARTZ_BLOCK
            case QUARTZ_BLOCK:
                return QuartzBlockVariant.NORMAL;
            case CHISELED_QUARTZ_BLOCK:
                return QuartzBlockVariant.CHISELED;
            case SMOOTH_QUARTZ:
                return QuartzBlockVariant.SMOOTH;

            // RAIL
            case RAIL:
                return RailVariant.NORMAL;
            case ACTIVATOR_RAIL:
                return RailVariant.ACTIVATOR;
            case DETECTOR_RAIL:
                return RailVariant.DETECTOR;
            case POWERED_RAIL:
                return RailVariant.POWERED;

            // SANDSTONE
            case SANDSTONE:
                return SandstoneVariant.NORMAL;
            case CHISELED_SANDSTONE:
                return SandstoneVariant.CHISELED;
            case CUT_SANDSTONE:
                return SandstoneVariant.CUT;
            case SMOOTH_SANDSTONE:
                return SandstoneVariant.SMOOTH;

            case RED_SANDSTONE:
                return SandstoneVariant.RED;
            case CHISELED_RED_SANDSTONE:
                return SandstoneVariant.CHISELED_RED;
            case CUT_RED_SANDSTONE:
                return SandstoneVariant.CUT_RED;
            case SMOOTH_RED_SANDSTONE:
                return SandstoneVariant.SMOOTH_RED;

            // SAND
            case SAND:
                return SandVariant.NORMAL;
            case RED_SAND:
                return SandVariant.RED;

            // SAPLING
            case ACACIA_SAPLING:
                return SaplingVariant.ACACIA;
            case BIRCH_SAPLING:
                return SaplingVariant.BIRCH;
            case DARK_OAK_SAPLING:
                return SaplingVariant.DARK_OAK;
            case JUNGLE_SAPLING:
                return SaplingVariant.JUNGLE;
            case OAK_SAPLING:
                return SaplingVariant.OAK;
            case SPRUCE_SAPLING:
                return SaplingVariant.SPRUCE;

            // SHULKER_BOX
            case SHULKER_BOX:
                return ShulkerBoxVariant.NORMAL;
            case BLACK_SHULKER_BOX:
                return ShulkerBoxVariant.BLACK;
            case BLUE_SHULKER_BOX:
                return ShulkerBoxVariant.BLUE;
            case BROWN_SHULKER_BOX:
                return ShulkerBoxVariant.BROWN;
            case CYAN_SHULKER_BOX:
                return ShulkerBoxVariant.CYAN;
            case GRAY_SHULKER_BOX:
                return ShulkerBoxVariant.GRAY;
            case GREEN_SHULKER_BOX:
                return ShulkerBoxVariant.GREEN;
            case LIGHT_BLUE_SHULKER_BOX:
                return ShulkerBoxVariant.LIGHT_BLUE;
            case LIGHT_GRAY_SHULKER_BOX:
                return ShulkerBoxVariant.LIGHT_GRAY;
            case LIME_SHULKER_BOX:
                return ShulkerBoxVariant.LIME;
            case MAGENTA_SHULKER_BOX:
                return ShulkerBoxVariant.MAGENTA;
            case ORANGE_SHULKER_BOX:
                return ShulkerBoxVariant.ORANGE;
            case PINK_SHULKER_BOX:
                return ShulkerBoxVariant.PINK;
            case PURPLE_SHULKER_BOX:
                return ShulkerBoxVariant.PURPLE;
            case RED_SHULKER_BOX:
                return ShulkerBoxVariant.RED;
            case WHITE_SHULKER_BOX:
                return ShulkerBoxVariant.WHITE;
            case YELLOW_SHULKER_BOX:
                return ShulkerBoxVariant.YELLOW;

            // SIGN
            case ACACIA_SIGN:
                return SignVariant.ACACIA;
            case BIRCH_SIGN:
                return SignVariant.BIRCH;
            case DARK_OAK_SIGN:
                return SignVariant.DARK_OAK;
            case JUNGLE_SIGN:
                return SignVariant.JUNGLE;
            case OAK_SIGN:
                return SignVariant.OAK;
            case SPRUCE_SIGN:
                return SignVariant.SPRUCE;

            case ACACIA_WALL_SIGN:
                return SignVariant.ACACIA;
            case BIRCH_WALL_SIGN:
                return SignVariant.BIRCH;
            case DARK_OAK_WALL_SIGN:
                return SignVariant.DARK_OAK;
            case JUNGLE_WALL_SIGN:
                return SignVariant.JUNGLE;
            case OAK_WALL_SIGN:
                return SignVariant.OAK;
            case SPRUCE_WALL_SIGN:
                return SignVariant.SPRUCE;

            // SLAB
            case BRICK_SLAB:
                return SlabVariant.BRICK;
            case NETHER_BRICK_SLAB:
                return SlabVariant.NETHER_BRICK;
            case RED_NETHER_BRICK_SLAB:
                return SlabVariant.RED_NETHER_BRICK;

            case COBBLESTONE_SLAB:
                return SlabVariant.COBBLESTONE;
            case MOSSY_COBBLESTONE_SLAB:
                return SlabVariant.MOSSY_COBBLESTONE;

            case END_STONE_BRICK_SLAB:
                return SlabVariant.END_STONE_BRICK;

            case PETRIFIED_OAK_SLAB:
                return SlabVariant.PETRIFIED_OAK;

            case PRISMARINE_SLAB:
                return SlabVariant.PRISMARINE;
            case PRISMARINE_BRICK_SLAB:
                return SlabVariant.PRISMARINE_BRICK;
            case DARK_PRISMARINE_SLAB:
                return SlabVariant.DARK_PRISMARINE;

            case PURPUR_SLAB:
                return SlabVariant.PURPUR;

            case QUARTZ_SLAB:
                return SlabVariant.QUARTZ;
            case SMOOTH_QUARTZ_SLAB:
                return SlabVariant.SMOOTH_QUARTZ;

            case SANDSTONE_SLAB:
                return SlabVariant.SANDSTONE;
            case CUT_SANDSTONE_SLAB:
                return SlabVariant.CUT_SANDSTONE;
            case SMOOTH_SANDSTONE_SLAB:
                return SlabVariant.SMOOTH_SANDSTONE;

            case RED_SANDSTONE_SLAB:
                return SlabVariant.RED_SANDSTONE;
            case CUT_RED_SANDSTONE_SLAB:
                return SlabVariant.CUT_RED_SANDSTONE;
            case SMOOTH_RED_SANDSTONE_SLAB:
                return SlabVariant.SMOOTH_RED_SANDSTONE;

            case STONE_SLAB:
                return SlabVariant.STONE;
            case SMOOTH_STONE_SLAB:
                return SlabVariant.SMOOTH_STONE;

            case STONE_BRICK_SLAB:
                return SlabVariant.STONE_BRICK;
            case MOSSY_STONE_BRICK_SLAB:
                return SlabVariant.MOSSY_STONE_BRICK;

            case ANDESITE_SLAB:
                return SlabVariant.ANDESITE;
            case POLISHED_ANDESITE_SLAB:
                return SlabVariant.POLISHED_ANDESITE;

            case DIORITE_SLAB:
                return SlabVariant.DIORITE;
            case POLISHED_DIORITE_SLAB:
                return SlabVariant.POLISHED_DIORITE;

            case GRANITE_SLAB:
                return SlabVariant.GRANITE;
            case POLISHED_GRANITE_SLAB:
                return SlabVariant.POLISHED_GRANITE;

            case ACACIA_SLAB:
                return SlabVariant.ACACIA;
            case BIRCH_SLAB:
                return SlabVariant.BIRCH;
            case DARK_OAK_SLAB:
                return SlabVariant.DARK_OAK;
            case JUNGLE_SLAB:
                return SlabVariant.JUNGLE;
            case OAK_SLAB:
                return SlabVariant.OAK;
            case SPRUCE_SLAB:
                return SlabVariant.SPRUCE;

            // SPONGE
            case SPONGE:
                return SpongeVariant.NORMAL;
            case WET_SPONGE:
                return SpongeVariant.WET;

            // STAIRS
            case BRICK_STAIRS:
                return StairsVariant.BRICK;
            case NETHER_BRICK_STAIRS:
                return StairsVariant.NETHER_BRICK;
            case RED_NETHER_BRICK_STAIRS:
                return StairsVariant.RED_NETHER_BRICK;

            case COBBLESTONE_STAIRS:
                return StairsVariant.COBBLESTONE;
            case MOSSY_COBBLESTONE_STAIRS:
                return StairsVariant.MOSSY_COBBLESTONE;

            case END_STONE_BRICK_STAIRS:
                return StairsVariant.END_STONE_BRICK;

            case PRISMARINE_STAIRS:
                return StairsVariant.PRISMARINE;
            case PRISMARINE_BRICK_STAIRS:
                return StairsVariant.PRISMARINE_BRICK;
            case DARK_PRISMARINE_STAIRS:
                return StairsVariant.DARK_PRISMARINE;

            case PURPUR_STAIRS:
                return StairsVariant.PURPUR;

            case QUARTZ_STAIRS:
                return StairsVariant.QUARTZ;
            case SMOOTH_QUARTZ_STAIRS:
                return StairsVariant.SMOOTH_QUARTZ;

            case SANDSTONE_STAIRS:
                return StairsVariant.SANDSTONE;
            case SMOOTH_SANDSTONE_STAIRS:
                return StairsVariant.SMOOTH_SANDSTONE;

            case RED_SANDSTONE_STAIRS:
                return StairsVariant.RED_SANDSTONE;
            case SMOOTH_RED_SANDSTONE_STAIRS:
                return StairsVariant.SMOOTH_RED_SANDSTONE;

            case STONE_STAIRS:
                return StairsVariant.STONE;
            case STONE_BRICK_STAIRS:
                return StairsVariant.STONE_BRICK;
            case MOSSY_STONE_BRICK_STAIRS:
                return StairsVariant.MOSSY_STONE_BRICK;

            case ANDESITE_STAIRS:
                return StairsVariant.ANDESITE;
            case POLISHED_ANDESITE_STAIRS:
                return StairsVariant.POLISHED_ANDESITE;

            case DIORITE_STAIRS:
                return StairsVariant.DIORITE;
            case POLISHED_DIORITE_STAIRS:
                return StairsVariant.POLISHED_DIORITE;

            case GRANITE_STAIRS:
                return StairsVariant.GRANITE;
            case POLISHED_GRANITE_STAIRS:
                return StairsVariant.POLISHED_GRANITE;

            case ACACIA_STAIRS:
                return StairsVariant.ACACIA;
            case BIRCH_STAIRS:
                return StairsVariant.BIRCH;
            case DARK_OAK_STAIRS:
                return StairsVariant.DARK_OAK;
            case JUNGLE_STAIRS:
                return StairsVariant.JUNGLE;
            case OAK_STAIRS:
                return StairsVariant.OAK;
            case SPRUCE_STAIRS:
                return StairsVariant.SPRUCE;

            // STONE
            case STONE:
                return StoneVariant.NORMAL;
            case SMOOTH_STONE:
                return StoneVariant.SMOOTH;

            // STONE_BRICK
            case STONE_BRICKS:
                return StoneBrickVariant.NORMAL;
            case CHISELED_STONE_BRICKS:
                return StoneBrickVariant.CHISELED;
            case CRACKED_STONE_BRICKS:
                return StoneBrickVariant.CRACKED;
            case MOSSY_STONE_BRICKS:
                return StoneBrickVariant.MOSSY;

            // STRUCTURE_BLOCK
            case STRUCTURE_VOID:
                return StructureBlockVariant.VOID;

            // TERRACOTTA
            case TERRACOTTA:
                return TerracottaVariant.NORMAL;
            case BLACK_TERRACOTTA:
                return TerracottaVariant.BLACK;
            case BLUE_TERRACOTTA:
                return TerracottaVariant.BLUE;
            case BROWN_TERRACOTTA:
                return TerracottaVariant.BROWN;
            case CYAN_TERRACOTTA:
                return TerracottaVariant.CYAN;
            case GRAY_TERRACOTTA:
                return TerracottaVariant.GRAY;
            case GREEN_TERRACOTTA:
                return TerracottaVariant.GREEN;
            case LIGHT_BLUE_TERRACOTTA:
                return TerracottaVariant.LIGHT_BLUE;
            case LIGHT_GRAY_TERRACOTTA:
                return TerracottaVariant.LIGHT_GRAY;
            case LIME_TERRACOTTA:
                return TerracottaVariant.LIME;
            case MAGENTA_TERRACOTTA:
                return TerracottaVariant.MAGENTA;
            case ORANGE_TERRACOTTA:
                return TerracottaVariant.ORANGE;
            case PINK_TERRACOTTA:
                return TerracottaVariant.PINK;
            case PURPLE_TERRACOTTA:
                return TerracottaVariant.PURPLE;
            case RED_TERRACOTTA:
                return TerracottaVariant.RED;
            case WHITE_TERRACOTTA:
                return TerracottaVariant.WHITE;
            case YELLOW_TERRACOTTA:
                return TerracottaVariant.YELLOW;

            // TRAPDOOR
            case ACACIA_TRAPDOOR:
                return TrapdoorVariant.ACACIA;
            case BIRCH_TRAPDOOR:
                return TrapdoorVariant.BIRCH;
            case DARK_OAK_TRAPDOOR:
                return TrapdoorVariant.DARK_OAK;
            case JUNGLE_TRAPDOOR:
                return TrapdoorVariant.JUNGLE;
            case OAK_TRAPDOOR:
                return TrapdoorVariant.OAK;
            case SPRUCE_TRAPDOOR:
                return TrapdoorVariant.SPRUCE;

            case IRON_TRAPDOOR:
                return TrapdoorVariant.IRON;

            // WALL
            case BRICK_WALL:
                return WallVariant.BRICK;
            case NETHER_BRICK_WALL:
                return WallVariant.NETHER_BRICK;
            case RED_NETHER_BRICK_WALL:
                return WallVariant.RED_NETHER_BRICK;

            case COBBLESTONE_WALL:
                return WallVariant.COBBLESTONE;
            case MOSSY_COBBLESTONE_WALL:
                return WallVariant.MOSSY_COBBLESTONE;

            case END_STONE_BRICK_WALL:
                return WallVariant.END_STONE_BRICK;

            case PRISMARINE_WALL:
                return WallVariant.PRISMARINE;

            case SANDSTONE_WALL:
                return WallVariant.SANDSTONE;
            case RED_SANDSTONE_WALL:
                return WallVariant.RED_SANDSTONE;

            case STONE_BRICK_WALL:
                return WallVariant.STONE_BRICK;
            case MOSSY_STONE_BRICK_WALL:
                return WallVariant.MOSSY_STONE_BRICK;

            case ANDESITE_WALL:
                return WallVariant.ANDESITE;
            case DIORITE_WALL:
                return WallVariant.DIORITE;
            case GRANITE_WALL:
                return WallVariant.GRANITE;

            // WEIGHTED_PRESSURE_PLATE
            case LIGHT_WEIGHTED_PRESSURE_PLATE:
                return WeightedPressurePlateVariant.LIGHT;
            case HEAVY_WEIGHTED_PRESSURE_PLATE:
                return WeightedPressurePlateVariant.HEAVY;

            // WOOD
            case ACACIA_WOOD:
                return WoodVariant.ACACIA;
            case BIRCH_WOOD:
                return WoodVariant.BIRCH;
            case DARK_OAK_WOOD:
                return WoodVariant.DARK_OAK;
            case JUNGLE_WOOD:
                return WoodVariant.JUNGLE;
            case OAK_WOOD:
                return WoodVariant.OAK;
            case SPRUCE_WOOD:
                return WoodVariant.SPRUCE;

            case STRIPPED_ACACIA_WOOD:
                return WoodVariant.STRIPPED_ACACIA;
            case STRIPPED_BIRCH_WOOD:
                return WoodVariant.STRIPPED_BIRCH;
            case STRIPPED_DARK_OAK_WOOD:
                return WoodVariant.STRIPPED_DARK_OAK;
            case STRIPPED_JUNGLE_WOOD:
                return WoodVariant.STRIPPED_JUNGLE;
            case STRIPPED_OAK_WOOD:
                return WoodVariant.STRIPPED_OAK;
            case STRIPPED_SPRUCE_WOOD:
                return WoodVariant.STRIPPED_SPRUCE;

            // WOOL
            case BLACK_WOOL:
                return WoolVariant.BLACK;
            case BLUE_WOOL:
                return WoolVariant.BLUE;
            case BROWN_WOOL:
                return WoolVariant.BROWN;
            case CYAN_WOOL:
                return WoolVariant.CYAN;
            case GRAY_WOOL:
                return WoolVariant.GRAY;
            case GREEN_WOOL:
                return WoolVariant.GREEN;
            case LIGHT_BLUE_WOOL:
                return WoolVariant.LIGHT_BLUE;
            case LIGHT_GRAY_WOOL:
                return WoolVariant.LIGHT_GRAY;
            case LIME_WOOL:
                return WoolVariant.LIME;
            case MAGENTA_WOOL:
                return WoolVariant.MAGENTA;
            case ORANGE_WOOL:
                return WoolVariant.ORANGE;
            case PINK_WOOL:
                return WoolVariant.PINK;
            case PURPLE_WOOL:
                return WoolVariant.PURPLE;
            case RED_WOOL:
                return WoolVariant.RED;
            case WHITE_WOOL:
                return WoolVariant.WHITE;
            case YELLOW_WOOL:
                return WoolVariant.YELLOW;

            default:
                throw new IllegalArgumentException(material.name());
        }
    }
}
