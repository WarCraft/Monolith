package gg.warcraft.monolith.spigot.world.block

import java.util

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.`type`._
import gg.warcraft.monolith.api.world.block.material.{ SandstoneMaterial, StoneMaterial, StoniteMaterial }
import gg.warcraft.monolith.api.world.block.variant._
import org.bukkit.Material
import org.bukkit.block.{ Block => SpigotBlock }
import org.bukkit.block.data.{ BlockData => SpigotBlockData }
import org.bukkit.block.data.`type`.{ Bamboo => SpigotBamboo, Comparator => SpigotComparator, StructureBlock => SpigotStructureBlock }

class SpigotBlockVariantMapper {

  private val cache =
    new util.EnumMap[Material, BlockVariant](classOf[Material])

  private def dataAs[T <: SpigotBlockData](implicit data: SpigotBlockData): T =
    data.asInstanceOf[T]

  def map(block: SpigotBlock): BlockVariant =
    cache.computeIfAbsent(block.getType, _ => compute(block))

  private def compute(block: SpigotBlock): BlockVariant = {
    implicit val data: SpigotBlockData = block.getState.getBlockData

    block.getType match {

      // AIR
      case Material.AIR      => AirVariant.NORMAL
      case Material.CAVE_AIR => AirVariant.CAVE
      case Material.VOID_AIR => AirVariant.VOID

      // ANVIL
      case Material.ANVIL         => AnvilVariant.NORMAL
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
      case it if it is "ANDESITE" => StoniteVariant.NORMAL
      case it if it is "DIORITE"  => StoniteVariant.NORMAL
      case it if it is "GRANITE"  => StoniteVariant.NORMAL

      case it if it is "POLISHED_ANDESITE" => StoniteVariant.POLISHED
      case it if it is "POLISHED_DIORITE"  => StoniteVariant.POLISHED
      case it if it is "POLISHED_GRANITE"  => StoniteVariant.POLISHED

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

  def map(block: VariedBlock[_ <: BlockVariant]): Material = block match {

    case Air(_, AirVariant.NORMAL) => Material.AIR
    case Air(_, AirVariant.CAVE)   => Material.CAVE_AIR
    case Air(_, AirVariant.VOID)   => Material.VOID_AIR
    // TODO what is the diff between VOID_AIR and STRUCTURE_VOID?

    case Anvil(_, AnvilVariant.NORMAL, _)  => Material.ANVIL
    case Anvil(_, AnvilVariant.CHIPPED, _) => Material.CHIPPED_ANVIL
    case Anvil(_, AnvilVariant.DAMAGED, _) => Material.DAMAGED_ANVIL

    case Chest(_, ChestVariant.NORMAL, _)  => Material.CHEST
    case Chest(_, ChestVariant.ENDER, _)   => Material.ENDER_CHEST
    case Chest(_, ChestVariant.TRAPPED, _) => Material.TRAPPED_CHEST

    case Cobblestone(_, CobblestoneVariant.NORMAL) => Material.COBBLESTONE
    case Cobblestone(_, CobblestoneVariant.MOSSY)  => Material.MOSSY_COBBLESTONE

    // COMMAND_BLOCK
    case CommandBlock(_, CommandBlockVariant.NORMAL, _, _) =>
      Material.COMMAND_BLOCK
    case CommandBlock(_, CommandBlockVariant.CHAIN, _, _) =>
      Material.CHAIN_COMMAND_BLOCK
    case CommandBlock(_, CommandBlockVariant.REPEATING, _, _) =>
      Material.REPEATING_COMMAND_BLOCK

    // CORAL
    case Coral(_, CoralVariant.BRAIN, _)  => Material.BRAIN_CORAL
    case Coral(_, CoralVariant.BUBBLE, _) => Material.BUBBLE_CORAL
    case Coral(_, CoralVariant.FIRE, _)   => Material.FIRE_CORAL
    case Coral(_, CoralVariant.HORN, _)   => Material.HORN_CORAL
    case Coral(_, CoralVariant.TUBE, _)   => Material.TUBE_CORAL

    case Coral(_, CoralVariant.DEAD_BRAIN, _)  => Material.DEAD_BRAIN_CORAL
    case Coral(_, CoralVariant.DEAD_BUBBLE, _) => Material.DEAD_BUBBLE_CORAL
    case Coral(_, CoralVariant.DEAD_FIRE, _)   => Material.DEAD_FIRE_CORAL
    case Coral(_, CoralVariant.DEAD_HORN, _)   => Material.DEAD_HORN_CORAL
    case Coral(_, CoralVariant.DEAD_TUBE, _)   => Material.DEAD_TUBE_CORAL

    // CORAL_BLOCK
    case CoralBlock(_, CoralVariant.BRAIN)  => Material.BRAIN_CORAL
    case CoralBlock(_, CoralVariant.BUBBLE) => Material.BUBBLE_CORAL
    case CoralBlock(_, CoralVariant.FIRE)   => Material.FIRE_CORAL
    case CoralBlock(_, CoralVariant.HORN)   => Material.HORN_CORAL
    case CoralBlock(_, CoralVariant.TUBE)   => Material.TUBE_CORAL

    case CoralBlock(_, CoralVariant.DEAD_BRAIN)  => Material.DEAD_BRAIN_CORAL
    case CoralBlock(_, CoralVariant.DEAD_BUBBLE) => Material.DEAD_BUBBLE_CORAL
    case CoralBlock(_, CoralVariant.DEAD_FIRE)   => Material.DEAD_FIRE_CORAL
    case CoralBlock(_, CoralVariant.DEAD_HORN)   => Material.DEAD_HORN_CORAL
    case CoralBlock(_, CoralVariant.DEAD_TUBE)   => Material.DEAD_TUBE_CORAL

    // CORAL_FAN
    case CoralFan(_, CoralVariant.BRAIN, dir, _) => dir match {
      case None    => Material.BRAIN_CORAL_FAN
      case Some(_) => Material.BRAIN_CORAL_WALL_FAN
    }

    case CoralFan(_, CoralVariant.BUBBLE, dir, _) => dir match {
      case None    => Material.BUBBLE_CORAL_FAN
      case Some(_) => Material.BUBBLE_CORAL_WALL_FAN
    }

    case CoralFan(_, CoralVariant.FIRE, dir, _) => dir match {
      case None    => Material.FIRE_CORAL_FAN
      case Some(_) => Material.FIRE_CORAL_WALL_FAN
    }

    case CoralFan(_, CoralVariant.HORN, dir, _) => dir match {
      case None    => Material.HORN_CORAL_FAN
      case Some(_) => Material.HORN_CORAL_WALL_FAN
    }

    case CoralFan(_, CoralVariant.TUBE, dir, _) => dir match {
      case None    => Material.TUBE_CORAL_FAN
      case Some(_) => Material.TUBE_CORAL_WALL_FAN
    }

    case CoralFan(_, CoralVariant.DEAD_BRAIN, dir, _) => dir match {
      case None    => Material.DEAD_BRAIN_CORAL_FAN
      case Some(_) => Material.DEAD_BRAIN_CORAL_WALL_FAN
    }

    case CoralFan(_, CoralVariant.DEAD_BUBBLE, dir, _) => dir match {
      case None    => Material.DEAD_BUBBLE_CORAL_FAN
      case Some(_) => Material.DEAD_BUBBLE_CORAL_WALL_FAN
    }

    case CoralFan(_, CoralVariant.DEAD_FIRE, dir, _) => dir match {
      case None    => Material.DEAD_FIRE_CORAL_FAN
      case Some(_) => Material.DEAD_FIRE_CORAL_WALL_FAN
    }

    case CoralFan(_, CoralVariant.DEAD_HORN, dir, _) => dir match {
      case None    => Material.DEAD_HORN_CORAL_FAN
      case Some(_) => Material.DEAD_HORN_CORAL_WALL_FAN
    }

    case CoralFan(_, CoralVariant.DEAD_TUBE, dir, _) => dir match {
      case None    => Material.DEAD_TUBE_CORAL_FAN
      case Some(_) => Material.DEAD_TUBE_CORAL_WALL_FAN
    }

    case it: FlowerPot => null // TODO

    // MOB_HEAD
    case MobHead(_, MobHeadVariant.CREEPER, dir, _) => dir match {
      case None    => Material.CREEPER_HEAD
      case Some(_) => Material.CREEPER_WALL_HEAD
    }

    case MobHead(_, MobHeadVariant.DRAGON, dir, _) => dir match {
      case None    => Material.DRAGON_HEAD
      case Some(_) => Material.DRAGON_WALL_HEAD
    }

    case MobHead(_, MobHeadVariant.PLAYER, dir, _) => dir match {
      case None    => Material.PLAYER_HEAD
      case Some(_) => Material.PLAYER_WALL_HEAD
    }

    case MobHead(_, MobHeadVariant.SKELETON, dir, _) => dir match {
      case None    => Material.SKELETON_SKULL
      case Some(_) => Material.SKELETON_WALL_SKULL
    }

    case MobHead(_, MobHeadVariant.WITHER_SKELETON, dir, _) => dir match {
      case None    => Material.WITHER_SKELETON_SKULL
      case Some(_) => Material.WITHER_SKELETON_WALL_SKULL
    }

    case MobHead(_, MobHeadVariant.ZOMBIE, dir, _) => dir match {
      case None    => Material.ZOMBIE_HEAD
      case Some(_) => Material.ZOMBIE_WALL_HEAD
    }

    // NOTE_BLOCK
    case it: NoteBlock => null

    // QUARTZ
    case Quartz(_, QuartzVariant.NORMAL) => Material.QUARTZ_BLOCK
    case Quartz(_, QuartzVariant.CHISELED) => Material.CHISELED_QUARTZ_BLOCK
    case Quartz(_, QuartzVariant.SMOOTH) => Material.SMOOTH_QUARTZ

    // RAILS
    case Rails(_, RailsVariant.NORMAL, _, _)    => Material.RAIL
    case Rails(_, RailsVariant.ACTIVATOR, _, _) => Material.ACTIVATOR_RAIL
    case Rails(_, RailsVariant.DETECTOR, _, _)  => Material.DETECTOR_RAIL
    case Rails(_, RailsVariant.POWERED, _, _)   => Material.POWERED_RAIL

    // SANDSTONE
    case Sandstone(_, SandstoneMaterial.SANDSTONE, variant) => variant match {
      case SandstoneVariant.NORMAL   => Material.SANDSTONE
      case SandstoneVariant.CHISELED => Material.CHISELED_SANDSTONE
      case SandstoneVariant.CUT      => Material.CUT_SANDSTONE
      case SandstoneVariant.SMOOTH   => Material.SMOOTH_SANDSTONE
    }

    case Sandstone(_, SandstoneMaterial.RED_SANDSTONE, v) => v match {
      case SandstoneVariant.NORMAL   => Material.RED_SANDSTONE
      case SandstoneVariant.CHISELED => Material.CHISELED_RED_SANDSTONE
      case SandstoneVariant.CUT      => Material.CUT_RED_SANDSTONE
      case SandstoneVariant.SMOOTH   => Material.SMOOTH_RED_SANDSTONE
    }

    // STONE
    case Stone(_, StoneMaterial.STONE, variant) => variant match {
      case StoneVariant.SMOOTH => Material.SMOOTH_STONE
      case _                   => Material.STONE
    }

    case Stone(_, StoneMaterial.STONE_BRICK, variant) => variant match {
      case StoneVariant.CHISELED => Material.CHISELED_STONE_BRICKS
      case StoneVariant.CRACKED  => Material.CRACKED_STONE_BRICKS
      case StoneVariant.MOSSY    => Material.MOSSY_STONE_BRICKS
      case _                     => Material.STONE_BRICKS
    }

    // STONITE
    case Stonite(_, StoniteMaterial.ANDESITE, variant) => variant match {
      case StoniteVariant.NORMAL   => Material.ANDESITE
      case StoniteVariant.POLISHED => Material.POLISHED_ANDESITE
    }

    case Stonite(_, StoniteMaterial.DIORITE, variant) => variant match {
      case StoniteVariant.NORMAL   => Material.DIORITE
      case StoniteVariant.POLISHED => Material.POLISHED_DIORITE
    }

    case Stonite(_, StoniteMaterial.GRANITE, variant) => variant match {
      case StoniteVariant.NORMAL   => Material.GRANITE
      case StoniteVariant.POLISHED => Material.POLISHED_GRANITE
    }

    // WEIGHTED_PRESSURE_PLATE
    case WeightedPressurePlate(_, WeightedPressurePlateVariant.LIGHT, _) =>
      Material.LIGHT_WEIGHTED_PRESSURE_PLATE
    case WeightedPressurePlate(_, WeightedPressurePlateVariant.HEAVY, _) =>
      Material.HEAVY_WEIGHTED_PRESSURE_PLATE
  }

  def map(
      block: VariedBlock[_ <: BlockVariant],
      data: SpigotBlockData
  ): Unit = {
    implicit val data: SpigotBlockData = data

    block match {

      case it: Bamboo         => null // TODO

      case Comparator(_, variant, _, _) =>
        val mode = map(variant)
        dataAs[SpigotComparator].setMode(mode)

      case it: NoteBlock      => null // TODO

      case StructureBlock(_, variant) =>
        val mode = map(variant)
        dataAs[SpigotStructureBlock].setMode(mode)
    }
  }

  def map(
      block: VariableBlock[_ <: BlockVariant],
      data: SpigotBlockData
  ): Unit = {
    implicit val data: SpigotBlockData = data

    block match {
      // TODO case FlowerPot
      case _ => null
    }
  }

  def map(variant: ComparatorVariant): SpigotComparator.Mode = variant match {
    case ComparatorVariant.COMPARE  => SpigotComparator.Mode.COMPARE
    case ComparatorVariant.SUBTRACT => SpigotComparator.Mode.SUBTRACT
  }

  def map(v: StructureBlockVariant): SpigotStructureBlock.Mode = v match {
    case StructureBlockVariant.CORNER => SpigotStructureBlock.Mode.CORNER
    case StructureBlockVariant.DATA   => SpigotStructureBlock.Mode.DATA
    case StructureBlockVariant.LOAD   => SpigotStructureBlock.Mode.LOAD
    case StructureBlockVariant.SAVE   => SpigotStructureBlock.Mode.SAVE
  }
}
