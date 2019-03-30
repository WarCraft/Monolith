package gg.warcraft.monolith.app.world.block;

import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.BlockTypeUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DefaultBlockTypeUtils implements BlockTypeUtils {
    private static final Map<String, BlockType> types = new HashMap<>();
    private static final Set<BlockType> NON_SOLIDS = new HashSet<>();
    private static final Set<BlockType> LIQUIDS = new HashSet<>();

    static {
        for (BlockType type : BlockType.values()) {
            String typeKey = type.getId() + ":" + type.getData();
            types.put(typeKey, type);
        }

        NON_SOLIDS.add(BlockType.AIR);
        NON_SOLIDS.add(BlockType.DEAD_SHRUB);
        NON_SOLIDS.add(BlockType.TALL_GRASS);
        NON_SOLIDS.add(BlockType.FERN);
        NON_SOLIDS.add(BlockType.DEAD_BUSH);

        LIQUIDS.add(BlockType.FLOWING_WATER);
        LIQUIDS.add(BlockType.STILL_WATER);
        LIQUIDS.add(BlockType.FLOWING_LAVA);
        LIQUIDS.add(BlockType.STILL_LAVA);
    }

    @Override
    public BlockType getType(int id) {
        return getType(id, 0);
    }

    @Override
    public BlockType getType(int id, int data) {
        BlockType type = types.get(id + ":" + data);
        if (type == null) {
            return types.get(id + ":0");
        }
        return type;
    }

    @Override
    public Set<BlockType> getNonSolids() {
        return new HashSet<>(NON_SOLIDS);
    }

    @Override
    public Set<BlockType> getLiquids() {
        return new HashSet<>(LIQUIDS);
    }

    @Override
    public boolean isCarpet(BlockType type) {
        switch (type) {
            case WHITE_CARPET:
            case ORANGE_CARPET:
            case MAGENTA_CARPET:
            case LIGHT_BLUE_CARPET:
            case YELLOW_CARPET:
            case LIME_CARPET:
            case PINK_CARPET:
            case GRAY_CARPET:
            case LIGHT_GRAY_CARPET:
            case CYAN_CARPET:
            case PURPLE_CARPET:
            case BLUE_CARPET:
            case BROWN_CARPET:
            case GREEN_CARPET:
            case RED_CARPET:
            case BLACK_CARPET:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isConcrete(BlockType type) {
        switch (type) {
            case WHITE_CONCRETE:
            case ORANGE_CONCRETE:
            case MAGENTA_CONCRETE:
            case LIGHT_BLUE_CONCRETE:
            case YELLOW_CONCRETE:
            case LIME_CONCRETE:
            case PINK_CONCRETE:
            case GRAY_CONCRETE:
            case LIGHT_GRAY_CONCRETE:
            case CYAN_CONCRETE:
            case PURPLE_CONCRETE:
            case BLUE_CONCRETE:
            case BROWN_CONCRETE:
            case GREEN_CONCRETE:
            case RED_CONCRETE:
            case BLACK_CONCRETE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isConcretePowder(BlockType type) {
        switch (type) {
            case WHITE_CONCRETE_POWDER:
            case ORANGE_CONCRETE_POWDER:
            case MAGENTA_CONCRETE_POWDER:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case YELLOW_CONCRETE_POWDER:
            case LIME_CONCRETE_POWDER:
            case PINK_CONCRETE_POWDER:
            case GRAY_CONCRETE_POWDER:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case CYAN_CONCRETE_POWDER:
            case PURPLE_CONCRETE_POWDER:
            case BLUE_CONCRETE_POWDER:
            case BROWN_CONCRETE_POWDER:
            case GREEN_CONCRETE_POWDER:
            case RED_CONCRETE_POWDER:
            case BLACK_CONCRETE_POWDER:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isDoor(BlockType type) {
        switch (type) {
            case ACACIA_DOOR:
            case BIRCH_DOOR:
            case DARK_OAK_DOOR:
            case IRON_DOOR:
            case JUNGLE_DOOR:
            case OAK_DOOR:
            case SPRUCE_DOOR:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isDoubleSlab(BlockType type) {
        switch (type) {
            case DOUBLE_BRICK_SLAB:
            case DOUBLE_COBBLESTONE_SLAB:
            case DOUBLE_QUARTZ_SLAB:
            case DOUBLE_SANDSTONE_SLAB:
            case DOUBLE_STONE_SLAB:
            case DOUBLE_WOODEN_SLAB:
            case DOUBLE_ACACIA_WOOD_SLAB:
            case DOUBLE_BIRCH_WOOD_SLAB:
            case DOUBLE_JUNGLE_WOOD_SLAB:
            case DOUBLE_NETHER_BRICK_SLAB:
            case DOUBLE_OAK_WOOD_SLAB:
            case DOUBLE_RED_SANDSTONE_SLAB:
            case DOUBLE_SPRUCE_WOOD_SLAB:
            case DOUBLE_STONE_BRICK_SLAB:
            case DOUBLE_DARK_OAK_WOOD_SLAB:
            case PURPUR_DOUBLE_SLAB:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isFence(BlockType type) {
        switch (type) {
            case ACACIA_FENCE:
            case BIRCH_FENCE:
            case DARK_OAK_FENCE:
            case JUNGLE_FENCE:
            case OAK_FENCE:
            case SPRUCE_FENCE:
            case NETHER_BRICK_FENCE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isFenceGate(BlockType type) {
        switch (type) {
            case ACACIA_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case OAK_FENCE_GATE:
            case SPRUCE_FENCE_GATE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isFlower(BlockType type) {
        switch (type) {
            case DANDELION:
            case POPPY:
            case BLUE_ORCHID:
            case ALLIUM:
            case AZURE_BLUET:
            case RED_TULIP:
            case ORANGE_TULIP:
            case WHITE_TULIP:
            case PINK_TULIP:
            case OXEYE_DAISY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isGlassPane(BlockType type) {
        switch (type) {
            case WHITE_STAINED_GLASS_PANE:
            case ORANGE_STAINED_GLASS_PANE:
            case MAGENTA_STAINED_GLASS_PANE:
            case LIGHT_BLUE_STAINED_GLASS_PANE:
            case YELLOW_STAINED_GLASS_PANE:
            case LIME_STAINED_GLASS_PANE:
            case PINK_STAINED_GLASS_PANE:
            case GRAY_STAINED_GLASS_PANE:
            case LIGHT_GRAY_STAINED_GLASS_PANE:
            case CYAN_STAINED_GLASS_PANE:
            case PURPLE_STAINED_GLASS_PANE:
            case BLUE_STAINED_GLASS_PANE:
            case BROWN_STAINED_GLASS_PANE:
            case GREEN_STAINED_GLASS_PANE:
            case RED_STAINED_GLASS_PANE:
            case BLACK_STAINED_GLASS_PANE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isHardenedClay(BlockType type) {
        switch (type) {
            case WHITE_HARDENED_CLAY:
            case ORANGE_HARDENED_CLAY:
            case MAGENTA_HARDENED_CLAY:
            case LIGHT_BLUE_HARDENED_CLAY:
            case YELLOW_HARDENED_CLAY:
            case LIME_HARDENED_CLAY:
            case PINK_HARDENED_CLAY:
            case GRAY_HARDENED_CLAY:
            case LIGHT_GRAY_HARDENED_CLAY:
            case CYAN_HARDENED_CLAY:
            case PURPLE_HARDENED_CLAY:
            case BLUE_HARDENED_CLAY:
            case BROWN_HARDENED_CLAY:
            case GREEN_HARDENED_CLAY:
            case RED_HARDENED_CLAY:
            case BLACK_HARDENED_CLAY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isLeaves(BlockType type) {
        switch (type) {
            case ACACIA_LEAVES:
            case BIRCH_LEAVES:
            case DARK_OAK_LEAVES:
            case JUNGLE_LEAVES:
            case OAK_LEAVES:
            case SPRUCE_LEAVES:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isMonsterEgg(BlockType type) {
        switch (type) {
            case MOSSY_STONE_BRICK_MONSTER_EGG:
            case CHISELED_STONE_BRICK_MONSTER_EGG:
            case COBBLESTONE_MONSTER_EGG:
            case CRACKED_STONE_BRICK_MONSTER_EGG:
            case STONE_BRICK_MONSTER_EGG:
            case STONE_MONSTER_EGG:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isOre(BlockType type) {
        switch (type) {
            case COAL_ORE:
            case DIAMOND_ORE:
            case EMERALD_ORE:
            case GLOWING_REDSTONE_ORE:
            case GOLD_ORE:
            case IRON_ORE:
            case LAPIS_LAZULI_ORE:
            case NETHER_QUARTZ_ORE:
            case REDSTONE_ORE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isPlank(BlockType type) {
        switch (type) {
            case ACACIA_WOOD_PLANK:
            case BIRCH_WOOD_PLANK:
            case DARK_OAK_WOOD_PLANK:
            case JUNGLE_WOOD_PLANK:
            case OAK_WOOD_PLANK:
            case SPRUCE_WOOD_PLANK:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isSapling(BlockType type) {
        switch (type) {
            case ACACIA_SAPLING:
            case BIRCH_SAPLING:
            case DARK_OAK_SAPLING:
            case JUNGLE_SAPLING:
            case OAK_SAPLING:
            case SPRUCE_SAPLING:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isShulkerBox(BlockType type) {
        switch (type) {
            case WHITE_SHULKER_BOX:
            case ORANGE_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case PINK_SHULKER_BOX:
            case GRAY_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case CYAN_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case BROWN_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case BLACK_SHULKER_BOX:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isSlab(BlockType type) {
        switch (type) {
            case BRICK_SLAB:
            case COBBLESTONE_SLAB:
            case QUARTZ_SLAB:
            case SANDSTONE_SLAB:
            case STONE_SLAB:
            case WOODEN_SLAB:
            case ACACIA_WOOD_SLAB:
            case BIRCH_WOOD_SLAB:
            case JUNGLE_WOOD_SLAB:
            case NETHER_BRICK_SLAB:
            case OAK_WOOD_SLAB:
            case RED_SANDSTONE_SLAB:
            case SPRUCE_WOOD_SLAB:
            case STONE_BRICK_SLAB:
            case DARK_OAK_WOOD_SLAB:
            case PURPUR_SLAB:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isStainedGlass(BlockType type) {
        switch (type) {
            case WHITE_STAINED_GLASS:
            case ORANGE_STAINED_GLASS:
            case MAGENTA_STAINED_GLASS:
            case LIGHT_BLUE_STAINED_GLASS:
            case YELLOW_STAINED_GLASS:
            case LIME_STAINED_GLASS:
            case PINK_STAINED_GLASS:
            case GRAY_STAINED_GLASS:
            case LIGHT_GRAY_STAINED_GLASS:
            case CYAN_STAINED_GLASS:
            case PURPLE_STAINED_GLASS:
            case BLUE_STAINED_GLASS:
            case BROWN_STAINED_GLASS:
            case GREEN_STAINED_GLASS:
            case RED_STAINED_GLASS:
            case BLACK_STAINED_GLASS:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isStairs(BlockType type) {
        switch (type) {
            case ACACIA_WOOD_STAIRS:
            case BIRCH_WOOD_STAIRS:
            case DARK_OAK_WOOD_STAIRS:
            case JUNGLE_WOOD_STAIRS:
            case OAK_WOOD_STAIRS:
            case SPRUCE_WOOD_STAIRS:
            case SANDSTONE_STAIRS:
            case STONE_BRICK_STAIRS:
            case BRICK_STAIRS:
            case COBBLESTONE_STAIRS:
            case NETHER_BRICK_STAIRS:
            case PURPUR_STAIRS:
            case QUARTZ_STAIRS:
            case RED_SANDSTONE_STAIRS:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isTerracotta(BlockType type) {
        switch (type) {
            case WHITE_GLAZED_TERRACOTTA:
            case ORANGE_GLAZED_TERRACOTTA:
            case MAGENTA_GLAZED_TERRACOTTA:
            case LIGHT_BLUE_GLAZED_TERRACOTTA:
            case YELLOW_GLAZED_TERRACOTTA:
            case LIME_GLAZED_TERRACOTTA:
            case PINK_GLAZED_TERRACOTTA:
            case GRAY_GLAZED_TERRACOTTA:
            case LIGHT_GRAY_GLAZED_TERRACOTTA:
            case CYAN_GLAZED_TERRACOTTA:
            case PURPLE_GLAZED_TERRACOTTA:
            case BLUE_GLAZED_TERRACOTTA:
            case BROWN_GLAZED_TERRACOTTA:
            case GREEN_GLAZED_TERRACOTTA:
            case RED_GLAZED_TERRACOTTA:
            case BLACK_GLAZED_TERRACOTTA:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isWheat(BlockType type) {
        switch (type) {
            case SEEDED_WHEAT_CROPS:
            case GERMINATED_WHEAT_CROPS:
            case VERY_SMALL_WHEAT_CROPS:
            case SMALL_WHEAT_CROPS:
            case MEDIUM_WHEAT_CROPS:
            case TALL_WHEAT_CROPS:
            case VERY_TALL_WHEAT_CROPS:
            case RIPE_WHEAT_CROPS:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isWood(BlockType type) {
        switch (type) {
            case ACACIA_WOOD:
            case BIRCH_WOOD:
            case DARK_OAK_WOOD:
            case JUNGLE_WOOD:
            case OAK_WOOD:
            case SPRUCE_WOOD:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isWool(BlockType type) {
        switch (type) {
            case WHITE_WOOL:
            case ORANGE_WOOL:
            case MAGENTA_WOOL:
            case LIGHT_BLUE_WOOL:
            case YELLOW_WOOL:
            case LIME_WOOL:
            case PINK_WOOL:
            case GRAY_WOOL:
            case LIGHT_GRAY_WOOL:
            case CYAN_WOOL:
            case PURPLE_WOOL:
            case BLUE_WOOL:
            case BROWN_WOOL:
            case GREEN_WOOL:
            case RED_WOOL:
            case BLACK_WOOL:
                return true;
            default:
                return false;
        }
    }
}
