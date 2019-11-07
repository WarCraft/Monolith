package gg.warcraft.monolith.spigot.world.block;

import gg.warcraft.monolith.api.world.block.BlockType;
import org.bukkit.Material;

public class JSpigotBlockTypeMapper extends SpigotBlockTypeMapper {
    @Override
    public Material map(BlockType type) {
        switch (type) {
            case BRICK_BLOCK:
                return Material.BRICKS;
            case CLAY_BLOCK:
                return Material.CLAY;
            case COCOA_POD:
                return Material.COCOA;
            case END_STONE_BRICK:
                return Material.END_STONE_BRICKS;
            case FROST:
                return Material.FROSTED_ICE;
            case HAY_BALE:
                return Material.HAY_BLOCK;
            case INFESTED_BLOCK:
                return Material.INFESTED_STONE;
            case JIGSAW_BLOCK:
                return Material.JIGSAW;
            case NETHER_WARTS:
                return Material.NETHER_WART;
            case QUARTZ_ORE:
                return Material.NETHER_QUARTZ_ORE;

            // variable blocks with no clear default
            case BANNER:
                return Material.RED_BANNER;
            case BED:
                return Material.RED_BED;
            case BUTTON:
                return Material.OAK_BUTTON;
            case CARPET:
                return Material.RED_CARPET;
            case CONCRETE:
                return Material.RED_CONCRETE;
            case CONCRETE_POWDER:
                return Material.RED_CONCRETE_POWDER;
            case CORAL:
                return Material.BRAIN_CORAL;
            case CORAL_BLOCK:
                return Material.BRAIN_CORAL_BLOCK;
            case CORAL_FAN:
                return Material.BRAIN_CORAL_FAN;
            case DOOR:
                return Material.OAK_DOOR;
            case FENCE:
                return Material.OAK_FENCE;
            case FLOWER:
                return Material.POPPY;
            case FENCE_GATE:
                return Material.OAK_FENCE_GATE;
            case GLAZED_TERRACOTTA:
                return Material.RED_GLAZED_TERRACOTTA;
            case LEAVES:
                return Material.OAK_LEAVES;
            case LOG:
                return Material.OAK_LOG;
            case MOB_HEAD:
                return Material.CREEPER_HEAD;
            case MUSHROOM:
                return Material.RED_MUSHROOM;
            case MUSHROOM_BLOCK:
                return Material.RED_MUSHROOM_BLOCK;
            case PILLAR:
                return Material.QUARTZ_PILLAR;
            case PLANKS:
                return Material.OAK_PLANKS;
            case PLANT:
                return Material.LILAC;
            case PRESSURE_PLATE:
                return Material.OAK_PRESSURE_PLATE;
            case SAPLING:
                return Material.OAK_SAPLING;
            case SIGN:
                return Material.OAK_SIGN;
            case SLAB:
                return Material.OAK_SLAB;
            case STAIRS:
                return Material.OAK_STAIRS;
            case TRAPDOOR:
                return Material.OAK_TRAPDOOR;
            case WALL:
                return Material.COBBLESTONE_WALL;
            case WEIGHTED_PRESSURE_PLATE:
                return Material.LIGHT_WEIGHTED_PRESSURE_PLATE;
            case WOOD:
                return Material.OAK_WOOD;
            case WOOL:
                return Material.RED_WOOL;

            default:
                return Material.valueOf(type.name());
        }
    }
}
