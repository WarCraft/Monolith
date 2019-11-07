package gg.warcraft.monolith.spigot.world.block

import java.util

import gg.warcraft.monolith.api.world.block.BlockType
import org.bukkit.Material

class SpigotBlockTypeMapper {
  def map(`type`: BlockType): Material =
    SpigotBlockTypeMapper.cache.computeIfAbsent(`type`, compute)

  private def compute(`type`: BlockType): Material = `type` match {
    case BlockType.BRICK_BLOCK     => Material.BRICKS
    case BlockType.CLAY_BLOCK      => Material.CLAY
    case BlockType.COCOA_POD       => Material.COCOA
    case BlockType.END_STONE_BRICK => Material.END_STONE_BRICKS
    case BlockType.FROST           => Material.FROSTED_ICE
    case BlockType.HAY_BALE        => Material.HAY_BLOCK
    case BlockType.INFESTED_BLOCK  => Material.INFESTED_STONE
    case BlockType.JIGSAW_BLOCK    => Material.JIGSAW
    case BlockType.NETHER_WARTS    => Material.NETHER_WART
    case BlockType.QUARTZ_ORE      => Material.NETHER_QUARTZ_ORE

    // variable blocks with no clear default
    case BlockType.BANNER                  => Material.RED_BANNER
    case BlockType.BED                     => Material.RED_BED
    case BlockType.BUTTON                  => Material.OAK_BUTTON
    case BlockType.CARPET                  => Material.RED_CARPET
    case BlockType.CONCRETE                => Material.RED_CONCRETE
    case BlockType.CONCRETE_POWDER         => Material.RED_CONCRETE_POWDER
    case BlockType.CORAL                   => Material.BRAIN_CORAL
    case BlockType.CORAL_BLOCK             => Material.BRAIN_CORAL_BLOCK
    case BlockType.CORAL_FAN               => Material.BRAIN_CORAL_FAN
    case BlockType.DOOR                    => Material.OAK_DOOR
    case BlockType.FENCE                   => Material.OAK_FENCE
    case BlockType.FLOWER                  => Material.POPPY
    case BlockType.FENCE_GATE              => Material.OAK_FENCE_GATE
    case BlockType.GLAZED_TERRACOTTA       => Material.RED_GLAZED_TERRACOTTA
    case BlockType.LEAVES                  => Material.OAK_LEAVES
    case BlockType.LOG                     => Material.OAK_LOG
    case BlockType.MOB_HEAD                => Material.CREEPER_HEAD
    case BlockType.MUSHROOM                => Material.RED_MUSHROOM
    case BlockType.MUSHROOM_BLOCK          => Material.RED_MUSHROOM_BLOCK
    case BlockType.PILLAR                  => Material.QUARTZ_PILLAR
    case BlockType.PLANKS                  => Material.OAK_PLANKS
    case BlockType.PLANT                   => Material.LILAC
    case BlockType.PRESSURE_PLATE          => Material.OAK_PRESSURE_PLATE
    case BlockType.SAPLING                 => Material.OAK_SAPLING
    case BlockType.SIGN                    => Material.OAK_SIGN
    case BlockType.SLAB                    => Material.OAK_SLAB
    case BlockType.STAIRS                  => Material.OAK_STAIRS
    case BlockType.TRAPDOOR                => Material.OAK_TRAPDOOR
    case BlockType.WALL                    => Material.COBBLESTONE_WALL
    case BlockType.WEIGHTED_PRESSURE_PLATE => Material.LIGHT_WEIGHTED_PRESSURE_PLATE
    case BlockType.WOOD                    => Material.OAK_WOOD
    case BlockType.WOOL                    => Material.RED_WOOL

    case it => Material.valueOf(it.name)
  }
}

object SpigotBlockTypeMapper {
  private final val cache: util.EnumMap[BlockType, Material] =
    new util.EnumMap(classOf[BlockType])
}
