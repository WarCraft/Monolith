package gg.warcraft.monolith.spigot.world.block

import java.util

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.variant._
import org.bukkit.Material
import org.bukkit.block.{Block => SpigotBlock}
import org.bukkit.block.data.{BlockData => SpigotBlockData}
import org.bukkit.block.data.`type`.{
  Bamboo => SpigotBamboo,
  Comparator => SpigotComparator,
  StructureBlock => SpigotStructureBlock
}

class SpigotBlockVariantMapper {

  private val cache =
    new util.EnumMap[Material, BlockVariant](classOf[Material])

  def map(block: SpigotBlock): BlockVariant =
    cache.computeIfAbsent(block.getType, _ => compute(block))

  private def compute(block: SpigotBlock): BlockVariant = {
    def dataAs[T <: SpigotBlockData]: T =
      block.getState.getBlockData.asInstanceOf[T]

    block.getType match {

      // AIR
      case Material.AIR      => AirVariant.NORMAL
      case Material.CAVE_AIR => AirVariant.CAVE
      case Material.VOID_AIR => AirVariant.VOID

      // ANVIL
      case Material.ANVIL         => AnvilVariant.PRISTINE
      case Material.CHIPPED_ANVIL => AnvilVariant.CHIPPED
      case Material.DAMAGED_ANVIL => AnvilVariant.DAMAGED

      //BAMBOO
      case Material.BAMBOO =>
        dataAs[SpigotBamboo].getLeaves match {
          case SpigotBamboo.Leaves.NONE  => BambooVariant.NO_LEAVES
          case SpigotBamboo.Leaves.SMALL => BambooVariant.SMALL_LEAVES
          case SpigotBamboo.Leaves.LARGE => BambooVariant.LARGE_LEAVES
        }

      // CHEST
      case Material.CHEST         => ChestVariant.NORMAL
      case Material.ENDER_CHEST   => ChestVariant.ENDER
      case Material.TRAPPED_CHEST => ChestVariant.TRAPPED

      // COBBLESTONE
      case it if it is "COBBLESTONE"       => CobblestoneVariant.NORMAL
      case it if it is "MOSSY_COBBLESTONE" => CobblestoneVariant.MOSSY

      // COMMAND_BLOCK
      case Material.COMMAND_BLOCK           => CommandBlockVariant.NORMAL
      case Material.CHAIN_COMMAND_BLOCK     => CommandBlockVariant.CHAIN
      case Material.REPEATING_COMMAND_BLOCK => CommandBlockVariant.REPEATING

      // COMPARATOR
      case Material.COMPARATOR =>
        dataAs[SpigotComparator].getMode match {
          case SpigotComparator.Mode.COMPARE  => ComparatorVariant.COMPARE
          case SpigotComparator.Mode.SUBTRACT => ComparatorVariant.SUBTRACT
        }

      // CORAL
      case it if it is "BRAIN_CORAL"  => CoralVariant.BRAIN
      case it if it is "BUBBLE_CORAL" => CoralVariant.BUBBLE
      case it if it is "FIRE_CORAL"   => CoralVariant.FIRE
      case it if it is "HORN_CORAL"   => CoralVariant.HORN
      case it if it is "TUBE_CORAL"   => CoralVariant.TUBE

      case it if it is "DEAD_BRAIN_CORAL"  => CoralVariant.DEAD_BRAIN
      case it if it is "DEAD_BUBBLE_CORAL" => CoralVariant.DEAD_BUBBLE
      case it if it is "DEAD_FIRE_CORAL"   => CoralVariant.DEAD_FIRE
      case it if it is "DEAD_HORN_CORAL"   => CoralVariant.DEAD_HORN
      case it if it is "DEAD_TUBE_CORAL"   => CoralVariant.DEAD_TUBE

      // FLOWER_POT
      case Material.FLOWER_POT                => null
      case Material.POTTED_ALLIUM             => null
      case Material.POTTED_AZURE_BLUET        => null
      case Material.POTTED_BLUE_ORCHID        => null
      case Material.POTTED_CORNFLOWER         => null
      case Material.POTTED_DANDELION          => null
      case Material.POTTED_LILY_OF_THE_VALLEY => null
      case Material.POTTED_ORANGE_TULIP       => null
      case Material.POTTED_OXEYE_DAISY        => null
      case Material.POTTED_PINK_TULIP         => null
      case Material.POTTED_POPPY              => null
      case Material.POTTED_RED_TULIP          => null
      case Material.POTTED_WHITE_TULIP        => null
      case Material.POTTED_WITHER_ROSE        => null
      case Material.POTTED_BAMBOO             => null
      case Material.POTTED_BROWN_MUSHROOM     => null
      case Material.POTTED_CACTUS             => null
      case Material.POTTED_DEAD_BUSH          => null
      case Material.POTTED_FERN               => null
      case Material.POTTED_RED_MUSHROOM       => null
      case Material.POTTED_ACACIA_SAPLING     => null
      case Material.POTTED_BIRCH_SAPLING      => null
      case Material.POTTED_DARK_OAK_SAPLING   => null
      case Material.POTTED_JUNGLE_SAPLING     => null
      case Material.POTTED_OAK_SAPLING        => null
      case Material.POTTED_SPRUCE_SAPLING     => null

      // IGNEOUS_ROCK
      case it if it is "ANDESITE" => IgneousRockVariant.NORMAL
      case it if it is "DIORITE"  => IgneousRockVariant.NORMAL
      case it if it is "GRANITE"  => IgneousRockVariant.NORMAL

      case it if it is "POLISHED_ANDESITE" => IgneousRockVariant.POLISHED
      case it if it is "POLISHED_DIORITE"  => IgneousRockVariant.POLISHED
      case it if it is "POLISHED_GRANITE"  => IgneousRockVariant.POLISHED

      // MOB_HEAD
      case it if it is "CREEPER"         => MobHeadVariant.CREEPER
      case it if it is "DRAGON"          => MobHeadVariant.DRAGON
      case it if it is "PLAYER"          => MobHeadVariant.PLAYER
      case it if it is "SKELETON"        => MobHeadVariant.SKELETON
      case it if it is "WITHER_SKELETON" => MobHeadVariant.WITHER_SKELETON
      case it if it is "ZOMBIE"          => MobHeadVariant.ZOMBIE

      // NOTE_BLOCK

      // QUARTZ
      case it if it is "QUARTZ"          => QuartzVariant.NORMAL
      case it if it is "CHISELED_QUARTZ" => QuartzVariant.CHISELED
      case it if it is "SMOOTH_QUARTZ"   => QuartzVariant.SMOOTH

      // RAILS
      case Material.RAIL           => RailsVariant.NORMAL
      case Material.ACTIVATOR_RAIL => RailsVariant.ACTIVATOR
      case Material.DETECTOR_RAIL  => RailsVariant.DETECTOR
      case Material.POWERED_RAIL   => RailsVariant.POWERED

      // SANDSTONE
      case it if it is "SANDSTONE"     => SandstoneVariant.NORMAL
      case it if it is "RED_SANDSTONE" => SandstoneVariant.NORMAL

      case it if it is "CHISELED_SANDSTONE"     => SandstoneVariant.CHISELED
      case it if it is "CHISELED_RED_SANDSTONE" => SandstoneVariant.CHISELED

      case it if it is "CUT_SANDSTONE"     => SandstoneVariant.CUT
      case it if it is "CUT_RED_SANDSTONE" => SandstoneVariant.CUT

      case it if it is "SMOOTH_SANDSTONE"     => SandstoneVariant.SMOOTH
      case it if it is "SMOOTH_RED_SANDSTONE" => SandstoneVariant.SMOOTH

      // STONE
      case it if it is "STONE_BRICK" => StoneVariant.NORMAL
      case it if it is "STONE"       => StoneVariant.NORMAL

      case it if it is "CHISELED_STONE_BRICK" => StoneVariant.CHISELED
      case it if it is "CHISELED_STONE"       => StoneVariant.CHISELED

      case it if it is "CRACKED_STONE_BRICK" => StoneVariant.CRACKED
      case it if it is "CRACKED_STONE"       => StoneVariant.CRACKED

      case it if it is "MOSSY_STONE_BRICK" => StoneVariant.MOSSY
      case it if it is "MOSSY_STONE"       => StoneVariant.MOSSY

      case it if it is "SMOOTH_STONE_BRICK" => StoneVariant.SMOOTH
      case it if it is "SMOOTH_STONE"       => StoneVariant.SMOOTH

      // STRUCTURE_BLOCK
      case Material.STRUCTURE_BLOCK =>
        dataAs[SpigotStructureBlock].getMode match {
          case SpigotStructureBlock.Mode.CORNER => StructureBlockVariant.CORNER
          case SpigotStructureBlock.Mode.DATA   => StructureBlockVariant.DATA
          case SpigotStructureBlock.Mode.LOAD   => StructureBlockVariant.LOAD
          case SpigotStructureBlock.Mode.SAVE   => StructureBlockVariant.SAVE
        }

      // WEIGHTED_PRESSURE_PLATE
      case Material.LIGHT_WEIGHTED_PRESSURE_PLATE =>
        WeightedPressurePlateVariant.LIGHT

      case Material.HEAVY_WEIGHTED_PRESSURE_PLATE =>
        WeightedPressurePlateVariant.HEAVY

      case _ => throw new IllegalArgumentException(s"${block.getType}")
    }
  }

  def map(
      block: StatefulBlock[_ <: BlockState],
      data: SpigotBlockData
  ): Unit = {}
}
