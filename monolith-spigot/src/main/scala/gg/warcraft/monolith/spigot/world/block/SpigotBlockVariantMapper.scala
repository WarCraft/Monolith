package gg.warcraft.monolith.spigot.world.block

import java.util

import gg.warcraft.monolith.api.core.Extensions._
import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.material.{
  BrickMaterial,
  CobblestoneMaterial,
  EndStoneMaterial,
  PrismarineMaterial,
  PurpurMaterial,
  QuartzMaterial,
  SandstoneMaterial,
  StoneMaterial,
  StoniteMaterial,
  WoodMaterial
}
import gg.warcraft.monolith.api.world.block.variant._
import org.bukkit.{Material, Instrument => SpigotInstrument}
import org.bukkit.block.data.`type`.Bamboo.{Leaves => SpigotBambooLeaves}
import org.bukkit.block.data.`type`.Comparator.{Mode => SpigotComparatorMode}
import org.bukkit.block.data.`type`.StructureBlock.{Mode => SpigotStructureBlockMode}

class SpigotBlockVariantMapper {
  private val cache =
    new util.EnumMap[Material, BlockVariant](classOf[Material])

  def map(block: SpigotBlock): BlockVariant = {
    lazy val data: SpigotBlockData = block.getState.getBlockData

    block.getType match {
      case Material.BAMBOO =>
        val leaves = data.asInstanceOf[SpigotBamboo].getLeaves
        mapBambooLeaves(leaves)

      case Material.COMPARATOR =>
        val mode = data.asInstanceOf[SpigotComparator].getMode
        mapComparatorMode(mode)

      case Material.NOTE_BLOCK =>
        val instrument = data.asInstanceOf[SpigotNoteBlock].getInstrument
        mapNoteBlock(instrument)

      case Material.STRUCTURE_BLOCK =>
        val mode = data.asInstanceOf[SpigotStructureBlock].getMode
        mapStructureBlockMode(mode)

      case _ => cache.computeIfAbsent(block.getType, map)
    }
  }

  def map(material: Material): BlockVariant = material match {
    // AIR
    case Material.AIR      => AirVariant.NORMAL
    case Material.CAVE_AIR => AirVariant.CAVE
    case Material.VOID_AIR => AirVariant.VOID

    // ANVIL
    case Material.ANVIL         => AnvilVariant.NORMAL
    case Material.CHIPPED_ANVIL => AnvilVariant.CHIPPED
    case Material.DAMAGED_ANVIL => AnvilVariant.DAMAGED

    // CHEST
    case Material.CHEST         => ChestVariant.NORMAL
    case Material.ENDER_CHEST   => ChestVariant.ENDER
    case Material.TRAPPED_CHEST => ChestVariant.TRAPPED

    // COMMAND_BLOCK
    case Material.COMMAND_BLOCK           => CommandBlockVariant.NORMAL
    case Material.CHAIN_COMMAND_BLOCK     => CommandBlockVariant.CHAIN
    case Material.REPEATING_COMMAND_BLOCK => CommandBlockVariant.REPEATING

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
    case Material.POTTED_ALLIUM             => FlowerVariant.ALLIUM
    case Material.POTTED_AZURE_BLUET        => FlowerVariant.AZURE_BLUET
    case Material.POTTED_BLUE_ORCHID        => FlowerVariant.BLUE_ORCHID
    case Material.POTTED_CORNFLOWER         => FlowerVariant.CORNFLOWER
    case Material.POTTED_DANDELION          => FlowerVariant.DANDELION
    case Material.POTTED_ORANGE_TULIP       => FlowerVariant.ORANGE_TULIP
    case Material.POTTED_OXEYE_DAISY        => FlowerVariant.OXEYE_DAISY
    case Material.POTTED_PINK_TULIP         => FlowerVariant.PINK_TULIP
    case Material.POTTED_POPPY              => FlowerVariant.POPPY
    case Material.POTTED_RED_TULIP          => FlowerVariant.RED_TULIP
    case Material.POTTED_WHITE_TULIP        => FlowerVariant.WHITE_TULIP
    case Material.POTTED_WITHER_ROSE        => FlowerVariant.WITHER_ROSE
    case Material.POTTED_LILY_OF_THE_VALLEY => FlowerVariant.LILY_OF_THE_VALLEY

    case Material.POTTED_BROWN_MUSHROOM => MushroomVariant.BROWN
    case Material.POTTED_RED_MUSHROOM   => MushroomVariant.RED

    case Material.POTTED_CACTUS    => PlantVariant.CACTUS
    case Material.POTTED_DEAD_BUSH => PlantVariant.DEAD_BUSH
    case Material.POTTED_FERN      => PlantVariant.FERN

    case Material.POTTED_BAMBOO           => SaplingVariant.BAMBOO
    case Material.POTTED_ACACIA_SAPLING   => SaplingVariant.ACACIA
    case Material.POTTED_BIRCH_SAPLING    => SaplingVariant.BIRCH
    case Material.POTTED_DARK_OAK_SAPLING => SaplingVariant.DARK_OAK
    case Material.POTTED_JUNGLE_SAPLING   => SaplingVariant.JUNGLE
    case Material.POTTED_OAK_SAPLING      => SaplingVariant.OAK
    case Material.POTTED_SPRUCE_SAPLING   => SaplingVariant.SPRUCE

    // ICE
    case Material.ICE        => IceVariant.NORMAL
    case Material.BLUE_ICE   => IceVariant.BLUE
    case Material.PACKED_ICE => IceVariant.PACKED

    // INFESTED_BLOCK
    case Material.INFESTED_COBBLESTONE           => CobblestoneVariant.NORMAL
    case Material.INFESTED_STONE                 => StoneVariant.NORMAL
    case Material.INFESTED_STONE_BRICKS          => StoneVariant.NORMAL
    case Material.INFESTED_CHISELED_STONE_BRICKS => StoneVariant.CHISELED
    case Material.INFESTED_CRACKED_STONE_BRICKS  => StoneVariant.CRACKED
    case Material.INFESTED_MOSSY_STONE_BRICKS    => StoneVariant.MOSSY

    // MUSHROOM
    case Material.BROWN_MUSHROOM => MushroomVariant.BROWN
    case Material.RED_MUSHROOM   => MushroomVariant.RED

    // MUSHROOM_BLOCK
    case Material.BROWN_MUSHROOM_BLOCK => MushroomBlockVariant.BROWN
    case Material.RED_MUSHROOM_BLOCK   => MushroomBlockVariant.RED
    case Material.MUSHROOM_STEM        => MushroomBlockVariant.STEM

    // RAILS
    case Material.RAIL           => RailVariant.NORMAL
    case Material.ACTIVATOR_RAIL => RailVariant.ACTIVATOR
    case Material.DETECTOR_RAIL  => RailVariant.DETECTOR
    case Material.POWERED_RAIL   => RailVariant.POWERED

    // SAPLING
    case Material.BAMBOO_SAPLING => SaplingVariant.BAMBOO

    case Material.ACACIA_SAPLING   => SaplingVariant.ACACIA
    case Material.BIRCH_SAPLING    => SaplingVariant.BIRCH
    case Material.DARK_OAK_SAPLING => SaplingVariant.DARK_OAK
    case Material.JUNGLE_SAPLING   => SaplingVariant.JUNGLE
    case Material.OAK_SAPLING      => SaplingVariant.OAK
    case Material.SPRUCE_SAPLING   => SaplingVariant.SPRUCE

    // WEIGHTED_PRESSURE_PLATE
    case Material.LIGHT_WEIGHTED_PRESSURE_PLATE =>
      WeightedPressurePlateVariant.LIGHT
    case Material.HEAVY_WEIGHTED_PRESSURE_PLATE =>
      WeightedPressurePlateVariant.HEAVY

    case _ =>
      material.name match {
        // COBBLESTONE
        case r"COBBLESTONE.*"       => CobblestoneVariant.NORMAL
        case r"MOSSY_COBBLESTONE.*" => CobblestoneVariant.MOSSY

        // CORAL
        case r"BRAIN_CORAL.*"  => CoralVariant.BRAIN
        case r"BUBBLE_CORAL.*" => CoralVariant.BUBBLE
        case r"FIRE_CORAL.*"   => CoralVariant.FIRE
        case r"HORN_CORAL.*"   => CoralVariant.HORN
        case r"TUBE_CORAL.*"   => CoralVariant.TUBE

        case r"DEAD_BRAIN_CORAL.*"  => CoralVariant.DEAD_BRAIN
        case r"DEAD_BUBBLE_CORAL.*" => CoralVariant.DEAD_BUBBLE
        case r"DEAD_FIRE_CORAL.*"   => CoralVariant.DEAD_FIRE
        case r"DEAD_HORN_CORAL.*"   => CoralVariant.DEAD_HORN
        case r"DEAD_TUBE_CORAL.*"   => CoralVariant.DEAD_TUBE

        // MOB_HEAD
        case r"CREEPER.*"         => MobHeadVariant.CREEPER
        case r"DRAGON.*"          => MobHeadVariant.DRAGON
        case r"PLAYER.*"          => MobHeadVariant.PLAYER
        case r"SKELETON.*"        => MobHeadVariant.SKELETON
        case r"WITHER_SKELETON.*" => MobHeadVariant.WITHER_SKELETON
        case r"ZOMBIE.*"          => MobHeadVariant.ZOMBIE

        // QUARTZ
        case r"QUARTZ.*"          => QuartzVariant.NORMAL
        case r"CHISELED_QUARTZ.*" => QuartzVariant.CHISELED
        case r"SMOOTH_QUARTZ.*"   => QuartzVariant.SMOOTH

        // SANDSTONE
        case r"SANDSTONE.*"     => SandstoneVariant.NORMAL
        case r"RED_SANDSTONE.*" => SandstoneVariant.NORMAL

        case r"CHISELED_SANDSTONE.*"     => SandstoneVariant.CHISELED
        case r"CHISELED_RED_SANDSTONE.*" => SandstoneVariant.CHISELED

        case r"CUT_SANDSTONE.*"     => SandstoneVariant.CUT
        case r"CUT_RED_SANDSTONE.*" => SandstoneVariant.CUT

        case r"SMOOTH_SANDSTONE.*"     => SandstoneVariant.SMOOTH
        case r"SMOOTH_RED_SANDSTONE.*" => SandstoneVariant.SMOOTH

        // STONE
        case r"STONE_BRICK.*" => StoneVariant.NORMAL
        case r"STONE.*"       => StoneVariant.NORMAL

        case r"CHISELED_STONE_BRICK.*" => StoneVariant.CHISELED
        case r"CHISELED_STONE.*"       => StoneVariant.CHISELED

        case r"CRACKED_STONE_BRICK.*" => StoneVariant.CRACKED
        case r"CRACKED_STONE.*"       => StoneVariant.CRACKED

        case r"MOSSY_STONE_BRICK.*" => StoneVariant.MOSSY
        case r"MOSSY_STONE.*"       => StoneVariant.MOSSY

        case r"SMOOTH_STONE_BRICK.*" => StoneVariant.SMOOTH
        case r"SMOOTH_STONE.*"       => StoneVariant.SMOOTH

        // STONITE
        case r"ANDESITE.*" => StoniteVariant.NORMAL
        case r"DIORITE.*"  => StoniteVariant.NORMAL
        case r"GRANITE.*"  => StoniteVariant.NORMAL

        case r"POLISHED_ANDESITE.*" => StoniteVariant.POLISHED
        case r"POLISHED_DIORITE.*"  => StoniteVariant.POLISHED
        case r"POLISHED_GRANITE.*"  => StoniteVariant.POLISHED

        case _ => throw new IllegalArgumentException(s"$material")
      }
  }

  def map(block: VariedBlock[_ <: BlockVariant]): Material = block match {
    case _: Bamboo     => Material.BAMBOO
    case _: Comparator => Material.COMPARATOR
    case _: NoteBlock  => Material.NOTE_BLOCK

    case it: Air                   => mapAir(it.variant)
    case it: Anvil                 => mapAnvil(it.variant)
    case it: Chest                 => mapChest(it.variant)
    case it: Cobblestone           => mapCobblestone(it.variant)
    case it: CommandBlock          => mapCommandBlock(it.variant)
    case it: Coral                 => mapCoral(it.variant)
    case it: CoralBlock            => mapCoralBlock(it.variant)
    case it: Flower                => mapFlower(it.variant)
    case it: InfestedBlock         => mapInfestedBlock(it.material, it.variant)
    case it: Ice                   => mapIce(it.variant)
    case it: Mushroom              => mapMushroom(it.variant)
    case it: MushroomBlock         => mapMushroomBlock(it.variant)
    case it: QuartzBlock           => mapQuartzBlock(it.variant)
    case it: Rail                  => mapRail(it.variant)
    case it: Sandstone             => mapSandstone(it.material, it.variant)
    case it: Sapling               => mapSapling(it.variant)
    case it: Stone                 => mapStone(it.material, it.variant)
    case it: Stonite               => mapStonite(it.material, it.variant)
    case it: StructureBlock        => mapStructureBlock(it.variant)
    case it: WeightedPressurePlate => mapWeightedPressurePlate(it.variant)

    case it: CoralFan =>
      if (it.direction.isEmpty) mapCoralFan(it.variant)
      else mapCoralWallFan(it.variant)

    case it: MobHead =>
      if (it.direction.isEmpty) mapMobHead(it.variant)
      else mapWallMobHead(it.variant)

    // TODO split Stonite into multiple blocks or merge items into 1?
  }

  def map(block: VariableBlock[_ <: BlockVariant]): Material = block match {
    case FlowerPot(_, variant)         => mapFlowerPot(variant)
    case Slab(_, material, variant, _) => mapSlab(material, variant)
    case Stairs(_, material, variant, _, _, _, _) =>
      mapStairs(material, variant)
    case Wall(_, material, variant, _) => mapWall(material, variant)
    // TODO consistently rename material to mat in non-api scopes
  }

  def map(block: VariedBlock[_ <: BlockVariant], data: SpigotBlockData): Unit =
    block match {
      case Bamboo(_, variant, _, _) =>
        val leaves = mapBambooLeaves(variant)
        data.asInstanceOf[SpigotBamboo].setLeaves(leaves)

      case Comparator(_, variant, _, _) =>
        val mode = mapComparatorMode(variant)
        data.asInstanceOf[SpigotComparator].setMode(mode)

      case NoteBlock(_, variant, _, _) =>
        val instrument = mapNoteBlock(variant)
        data.asInstanceOf[SpigotNoteBlock].setInstrument(instrument)

      case StructureBlock(_, variant) =>
        val mode = mapStructureBlockMode(variant)
        mode.foreach(data.asInstanceOf[SpigotStructureBlock].setMode)

      // TODO do we need an empty catch-all here?
    }

  def mapAir(variant: AirVariant): Material = variant match {
    case AirVariant.NORMAL => Material.AIR
    case AirVariant.CAVE   => Material.CAVE_AIR
    case AirVariant.VOID   => Material.VOID_AIR
  }

  def mapAnvil(variant: AnvilVariant): Material = variant match {
    case AnvilVariant.NORMAL  => Material.ANVIL
    case AnvilVariant.CHIPPED => Material.CHIPPED_ANVIL
    case AnvilVariant.DAMAGED => Material.DAMAGED_ANVIL
  }

  def mapBambooLeaves(leaves: SpigotBambooLeaves): BambooVariant =
    leaves match {
      case SpigotBambooLeaves.NONE  => BambooVariant.NO_LEAVES
      case SpigotBambooLeaves.SMALL => BambooVariant.SMALL_LEAVES
      case SpigotBambooLeaves.LARGE => BambooVariant.LARGE_LEAVES
    }

  def mapBambooLeaves(variant: BambooVariant): SpigotBambooLeaves =
    variant match {
      case BambooVariant.NO_LEAVES    => SpigotBambooLeaves.NONE
      case BambooVariant.SMALL_LEAVES => SpigotBambooLeaves.SMALL
      case BambooVariant.LARGE_LEAVES => SpigotBambooLeaves.LARGE
    }

  def mapChest(variant: ChestVariant): Material = variant match {
    case ChestVariant.NORMAL  => Material.CHEST
    case ChestVariant.ENDER   => Material.ENDER_CHEST
    case ChestVariant.TRAPPED => Material.TRAPPED_CHEST
  }

  def mapCobblestone(variant: CobblestoneVariant): Material = variant match {
    case CobblestoneVariant.NORMAL => Material.COBBLESTONE
    case CobblestoneVariant.MOSSY  => Material.MOSSY_COBBLESTONE
  }

  def mapCommandBlock(variant: CommandBlockVariant): Material = variant match {
    case CommandBlockVariant.NORMAL    => Material.COMMAND_BLOCK
    case CommandBlockVariant.CHAIN     => Material.CHAIN_COMMAND_BLOCK
    case CommandBlockVariant.REPEATING => Material.REPEATING_COMMAND_BLOCK
  }

  def mapComparatorMode(mode: SpigotComparatorMode): ComparatorVariant =
    mode match {
      case SpigotComparatorMode.COMPARE  => ComparatorVariant.COMPARE
      case SpigotComparatorMode.SUBTRACT => ComparatorVariant.SUBTRACT
    }

  def mapComparatorMode(variant: ComparatorVariant): SpigotComparatorMode =
    variant match {
      case ComparatorVariant.COMPARE  => SpigotComparatorMode.COMPARE
      case ComparatorVariant.SUBTRACT => SpigotComparatorMode.SUBTRACT
    }

  def mapCoral(variant: CoralVariant): Material = variant match {
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
  }

  def mapCoralBlock(variant: CoralVariant): Material = variant match {
    case CoralVariant.BRAIN  => Material.BRAIN_CORAL_BLOCK
    case CoralVariant.BUBBLE => Material.BUBBLE_CORAL_BLOCK
    case CoralVariant.FIRE   => Material.FIRE_CORAL_BLOCK
    case CoralVariant.HORN   => Material.HORN_CORAL_BLOCK
    case CoralVariant.TUBE   => Material.TUBE_CORAL_BLOCK

    case CoralVariant.DEAD_BRAIN  => Material.DEAD_BRAIN_CORAL_BLOCK
    case CoralVariant.DEAD_BUBBLE => Material.DEAD_BUBBLE_CORAL_BLOCK
    case CoralVariant.DEAD_FIRE   => Material.DEAD_FIRE_CORAL_BLOCK
    case CoralVariant.DEAD_HORN   => Material.DEAD_HORN_CORAL_BLOCK
    case CoralVariant.DEAD_TUBE   => Material.DEAD_TUBE_CORAL_BLOCK
  }

  def mapCoralFan(variant: CoralVariant): Material = variant match {
    case CoralVariant.BRAIN  => Material.BRAIN_CORAL_FAN
    case CoralVariant.BUBBLE => Material.BUBBLE_CORAL_FAN
    case CoralVariant.FIRE   => Material.FIRE_CORAL_FAN
    case CoralVariant.HORN   => Material.HORN_CORAL_FAN
    case CoralVariant.TUBE   => Material.TUBE_CORAL_FAN

    case CoralVariant.DEAD_BRAIN  => Material.DEAD_BRAIN_CORAL_FAN
    case CoralVariant.DEAD_BUBBLE => Material.DEAD_BUBBLE_CORAL_FAN
    case CoralVariant.DEAD_FIRE   => Material.DEAD_FIRE_CORAL_FAN
    case CoralVariant.DEAD_HORN   => Material.DEAD_HORN_CORAL_FAN
    case CoralVariant.DEAD_TUBE   => Material.DEAD_TUBE_CORAL_FAN
  }

  def mapCoralWallFan(variant: CoralVariant): Material = variant match {
    case CoralVariant.BRAIN  => Material.BRAIN_CORAL_WALL_FAN
    case CoralVariant.BUBBLE => Material.BUBBLE_CORAL_WALL_FAN
    case CoralVariant.FIRE   => Material.FIRE_CORAL_WALL_FAN
    case CoralVariant.HORN   => Material.HORN_CORAL_WALL_FAN
    case CoralVariant.TUBE   => Material.TUBE_CORAL_WALL_FAN

    case CoralVariant.DEAD_BRAIN  => Material.DEAD_BRAIN_CORAL_WALL_FAN
    case CoralVariant.DEAD_BUBBLE => Material.DEAD_BUBBLE_CORAL_WALL_FAN
    case CoralVariant.DEAD_FIRE   => Material.DEAD_FIRE_CORAL_WALL_FAN
    case CoralVariant.DEAD_HORN   => Material.DEAD_HORN_CORAL_WALL_FAN
    case CoralVariant.DEAD_TUBE   => Material.DEAD_TUBE_CORAL_WALL_FAN
  }

  def mapFlower(variant: FlowerVariant): Material = variant match {
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
  }

  def mapFlowerPot(variant: Option[FlowerPotVariant]): Material =
    variant match {
      case None => Material.FLOWER_POT
      case Some(it) =>
        it match {
          // FLOWER
          case FlowerVariant.ALLIUM       => Material.POTTED_ALLIUM
          case FlowerVariant.AZURE_BLUET  => Material.POTTED_AZURE_BLUET
          case FlowerVariant.BLUE_ORCHID  => Material.POTTED_BLUE_ORCHID
          case FlowerVariant.CORNFLOWER   => Material.POTTED_CORNFLOWER
          case FlowerVariant.DANDELION    => Material.POTTED_DANDELION
          case FlowerVariant.ORANGE_TULIP => Material.POTTED_ORANGE_TULIP
          case FlowerVariant.OXEYE_DAISY  => Material.POTTED_OXEYE_DAISY
          case FlowerVariant.PINK_TULIP   => Material.POTTED_PINK_TULIP
          case FlowerVariant.POPPY        => Material.POTTED_POPPY
          case FlowerVariant.RED_TULIP    => Material.POTTED_RED_TULIP
          case FlowerVariant.WHITE_TULIP  => Material.POTTED_WHITE_TULIP
          case FlowerVariant.WITHER_ROSE  => Material.POTTED_WITHER_ROSE
          case FlowerVariant.LILY_OF_THE_VALLEY =>
            Material.POTTED_LILY_OF_THE_VALLEY

          // MUSHROOM
          case MushroomVariant.BROWN => Material.POTTED_BROWN_MUSHROOM
          case MushroomVariant.RED   => Material.POTTED_RED_MUSHROOM

          // PLANT
          case PlantVariant.CACTUS    => Material.POTTED_CACTUS
          case PlantVariant.DEAD_BUSH => Material.POTTED_DEAD_BUSH
          case PlantVariant.FERN      => Material.POTTED_FERN

          // SAPLING
          case SaplingVariant.BAMBOO   => Material.POTTED_BAMBOO
          case SaplingVariant.ACACIA   => Material.POTTED_ACACIA_SAPLING
          case SaplingVariant.BIRCH    => Material.POTTED_BIRCH_SAPLING
          case SaplingVariant.DARK_OAK => Material.POTTED_DARK_OAK_SAPLING
          case SaplingVariant.JUNGLE   => Material.POTTED_JUNGLE_SAPLING
          case SaplingVariant.OAK      => Material.POTTED_OAK_SAPLING
          case SaplingVariant.SPRUCE   => Material.POTTED_SPRUCE_SAPLING
        }
    }

  def mapInfestedBlock(material: InfestedMaterial, variant: InfestedVariant): Material =
    material match {
      case CobblestoneMaterial.COBBLESTONE => Material.INFESTED_COBBLESTONE
      case StoneMaterial.STONE             => Material.INFESTED_STONE
      case StoneMaterial.STONE_BRICK =>
        variant match {
          case StoneVariant.CHISELED => Material.INFESTED_CHISELED_STONE_BRICKS
          case StoneVariant.CRACKED  => Material.INFESTED_CRACKED_STONE_BRICKS
          case StoneVariant.MOSSY    => Material.INFESTED_MOSSY_STONE_BRICKS
          case _                     => Material.INFESTED_STONE_BRICKS
        }
    }

  def mapIce(variant: IceVariant): Material = variant match {
    case IceVariant.NORMAL => Material.ICE
    case IceVariant.PACKED => Material.PACKED_ICE
    case IceVariant.BLUE   => Material.BLUE_ICE
  }

  def mapMobHead(variant: MobHeadVariant): Material = variant match {
    case MobHeadVariant.CREEPER         => Material.CREEPER_HEAD
    case MobHeadVariant.DRAGON          => Material.DRAGON_HEAD
    case MobHeadVariant.PLAYER          => Material.PLAYER_HEAD
    case MobHeadVariant.SKELETON        => Material.SKELETON_SKULL
    case MobHeadVariant.WITHER_SKELETON => Material.WITHER_SKELETON_SKULL
    case MobHeadVariant.ZOMBIE          => Material.ZOMBIE_HEAD
  }

  def mapWallMobHead(variant: MobHeadVariant): Material = variant match {
    case MobHeadVariant.CREEPER         => Material.CREEPER_WALL_HEAD
    case MobHeadVariant.DRAGON          => Material.DRAGON_WALL_HEAD
    case MobHeadVariant.PLAYER          => Material.PLAYER_WALL_HEAD
    case MobHeadVariant.SKELETON        => Material.SKELETON_WALL_SKULL
    case MobHeadVariant.WITHER_SKELETON => Material.WITHER_SKELETON_WALL_SKULL
    case MobHeadVariant.ZOMBIE          => Material.ZOMBIE_WALL_HEAD
  }

  def mapMushroom(variant: MushroomVariant): Material = variant match {
    case MushroomVariant.BROWN => Material.BROWN_MUSHROOM
    case MushroomVariant.RED   => Material.RED_MUSHROOM
  }

  def mapMushroomBlock(variant: MushroomBlockVariant): Material =
    variant match {
      case MushroomBlockVariant.BROWN => Material.BROWN_MUSHROOM_BLOCK
      case MushroomBlockVariant.RED   => Material.RED_MUSHROOM_BLOCK
      case MushroomBlockVariant.STEM  => Material.MUSHROOM_STEM
    }

  def mapNoteBlock(instrument: SpigotInstrument): NoteBlockVariant =
    instrument match {
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

  def mapNoteBlock(variant: NoteBlockVariant): SpigotInstrument =
    variant match {
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

  def mapQuartzBlock(variant: QuartzVariant): Material = variant match {
    case QuartzVariant.NORMAL   => Material.QUARTZ_BLOCK
    case QuartzVariant.CHISELED => Material.CHISELED_QUARTZ_BLOCK
    case QuartzVariant.SMOOTH   => Material.SMOOTH_QUARTZ
  }

  def mapRail(variant: RailVariant): Material = variant match {
    case RailVariant.NORMAL    => Material.RAIL
    case RailVariant.ACTIVATOR => Material.ACTIVATOR_RAIL
    case RailVariant.DETECTOR  => Material.DETECTOR_RAIL
    case RailVariant.POWERED   => Material.POWERED_RAIL
  }

  def mapSandstone(mat: SandstoneMaterial, variant: SandstoneVariant): Material =
    mat match {
      case SandstoneMaterial.SANDSTONE =>
        variant match {
          case SandstoneVariant.NORMAL   => Material.SANDSTONE
          case SandstoneVariant.CHISELED => Material.CHISELED_SANDSTONE
          case SandstoneVariant.CUT      => Material.CUT_SANDSTONE
          case SandstoneVariant.SMOOTH   => Material.SMOOTH_SANDSTONE
        }
      case SandstoneMaterial.RED_SANDSTONE =>
        variant match {
          case SandstoneVariant.NORMAL   => Material.RED_SANDSTONE
          case SandstoneVariant.CHISELED => Material.CHISELED_RED_SANDSTONE
          case SandstoneVariant.CUT      => Material.CUT_RED_SANDSTONE
          case SandstoneVariant.SMOOTH   => Material.SMOOTH_RED_SANDSTONE
        }
    }

  def mapSapling(variant: SaplingVariant): Material = variant match {
    case SaplingVariant.BAMBOO   => Material.BAMBOO_SAPLING
    case SaplingVariant.ACACIA   => Material.ACACIA_SAPLING
    case SaplingVariant.BIRCH    => Material.BIRCH_SAPLING
    case SaplingVariant.DARK_OAK => Material.DARK_OAK_SAPLING
    case SaplingVariant.JUNGLE   => Material.JUNGLE_SAPLING
    case SaplingVariant.OAK      => Material.OAK_SAPLING
    case SaplingVariant.SPRUCE   => Material.SPRUCE_SAPLING
  }

  def mapSlab(mat: SlabMaterial, variant: Option[SlabVariant]): Material =
    mat match {
      // BRICK
      case BrickMaterial.BRICK            => Material.BRICK_SLAB
      case BrickMaterial.NETHER_BRICK     => Material.NETHER_BRICK_SLAB
      case BrickMaterial.RED_NETHER_BRICK => Material.RED_NETHER_BRICK_SLAB

      // COBBLESTONE
      case _: CobblestoneMaterial =>
        variant match {
          case Some(CobblestoneVariant.MOSSY) => Material.MOSSY_COBBLESTONE_SLAB
          case _                              => Material.COBBLESTONE_SLAB
        }

      // END_STONE
      case _: EndStoneMaterial => Material.END_STONE_BRICK_SLAB

      // PRISMARINE
      case PrismarineMaterial.PRISMARINE       => Material.PRISMARINE_SLAB
      case PrismarineMaterial.PRISMARINE_BRICK => Material.PRISMARINE_BRICK_SLAB
      case PrismarineMaterial.DARK_PRISMARINE  => Material.DARK_PRISMARINE_SLAB

      // PURPUR
      case _: PurpurMaterial => Material.PURPUR_SLAB

      // QUARTZ
      case QuartzMaterial.QUARTZ =>
        variant match {
          case Some(QuartzVariant.SMOOTH) => Material.SMOOTH_QUARTZ_SLAB
          case _                          => Material.QUARTZ_SLAB
        }

      // SANDSTONE
      case SandstoneMaterial.SANDSTONE =>
        variant match {
          case Some(SandstoneVariant.CUT)    => Material.CUT_SANDSTONE_SLAB
          case Some(SandstoneVariant.SMOOTH) => Material.SMOOTH_SANDSTONE_SLAB
          case _                             => Material.SANDSTONE_SLAB
        }

      case SandstoneMaterial.RED_SANDSTONE =>
        variant match {
          case Some(SandstoneVariant.CUT) => Material.CUT_RED_SANDSTONE_SLAB
          case Some(SandstoneVariant.SMOOTH) =>
            Material.SMOOTH_RED_SANDSTONE_SLAB
          case _ => Material.RED_SANDSTONE_SLAB
        }

      // STONE
      case StoneMaterial.STONE =>
        variant match {
          case Some(StoneVariant.SMOOTH) => Material.SMOOTH_STONE_SLAB
          case _                         => Material.STONE_SLAB
        }

      case StoneMaterial.STONE_BRICK =>
        variant match {
          case Some(StoneVariant.MOSSY) => Material.MOSSY_STONE_BRICK_SLAB
          case _                        => Material.STONE_BRICK_SLAB
        }

      // STONITE
      case StoniteMaterial.ANDESITE =>
        variant match {
          case Some(StoniteVariant.POLISHED) => Material.POLISHED_ANDESITE_SLAB
          case _                             => Material.ANDESITE_SLAB
        }

      case StoniteMaterial.DIORITE =>
        variant match {
          case Some(StoniteVariant.POLISHED) => Material.POLISHED_DIORITE_SLAB
          case _                             => Material.DIORITE_SLAB
        }

      case StoniteMaterial.GRANITE =>
        variant match {
          case Some(StoniteVariant.POLISHED) => Material.POLISHED_GRANITE_SLAB
          case _                             => Material.GRANITE_SLAB
        }

      // WOOD
      case WoodMaterial.ACACIA   => Material.ACACIA_SLAB
      case WoodMaterial.BIRCH    => Material.BIRCH_SLAB
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_SLAB
      case WoodMaterial.JUNGLE   => Material.JUNGLE_SLAB
      case WoodMaterial.OAK      => Material.OAK_SLAB
      case WoodMaterial.SPRUCE   => Material.SPRUCE_SLAB
    }

  def mapStairs(mat: StairsMaterial, variant: Option[StairsVariant]): Material =
    mat match {
      // BRICK
      case BrickMaterial.BRICK            => Material.BRICK_STAIRS
      case BrickMaterial.NETHER_BRICK     => Material.NETHER_BRICK_STAIRS
      case BrickMaterial.RED_NETHER_BRICK => Material.RED_NETHER_BRICK_STAIRS

      // COBBLESTONE
      case _: CobblestoneMaterial =>
        variant match {
          case Some(CobblestoneVariant.MOSSY) =>
            Material.MOSSY_COBBLESTONE_STAIRS
          case _ => Material.COBBLESTONE_STAIRS
        }

      // END_STONE
      case _: EndStoneMaterial => Material.END_STONE_BRICK_STAIRS

      // PRISMARINE
      case PrismarineMaterial.PRISMARINE      => Material.PRISMARINE_STAIRS
      case PrismarineMaterial.DARK_PRISMARINE => Material.DARK_PRISMARINE_STAIRS
      case PrismarineMaterial.PRISMARINE_BRICK =>
        Material.PRISMARINE_BRICK_STAIRS

      // PURPUR
      case _: PurpurMaterial => Material.PURPUR_STAIRS

      // QUARTZ
      case QuartzMaterial.QUARTZ =>
        variant match {
          case Some(QuartzVariant.SMOOTH) => Material.SMOOTH_QUARTZ_STAIRS
          case _                          => Material.QUARTZ_STAIRS
        }

      // SANDSTONE
      case SandstoneMaterial.SANDSTONE =>
        variant match {
          case Some(SandstoneVariant.SMOOTH) => Material.SMOOTH_SANDSTONE_STAIRS
          case _                             => Material.SANDSTONE_STAIRS
        }

      case SandstoneMaterial.RED_SANDSTONE =>
        variant match {
          case Some(SandstoneVariant.SMOOTH) =>
            Material.SMOOTH_RED_SANDSTONE_STAIRS
          case _ => Material.RED_SANDSTONE_STAIRS
        }

      // STONE
      case StoneMaterial.STONE => Material.STONE_STAIRS
      case StoneMaterial.STONE_BRICK =>
        variant match {
          case Some(StoneVariant.MOSSY) => Material.MOSSY_STONE_BRICK_STAIRS
          case _                        => Material.STONE_BRICK_STAIRS
        }

      // STONITE
      case StoniteMaterial.ANDESITE => Material.ANDESITE_STAIRS
      case StoniteMaterial.DIORITE  => Material.DIORITE_STAIRS
      case StoniteMaterial.GRANITE  => Material.GRANITE_STAIRS

      // WOOD
      case WoodMaterial.ACACIA   => Material.ACACIA_STAIRS
      case WoodMaterial.BIRCH    => Material.BIRCH_STAIRS
      case WoodMaterial.DARK_OAK => Material.DARK_OAK_STAIRS
      case WoodMaterial.JUNGLE   => Material.JUNGLE_STAIRS
      case WoodMaterial.OAK      => Material.OAK_STAIRS
      case WoodMaterial.SPRUCE   => Material.SPRUCE_STAIRS
    }

  def mapStone(mat: StoneMaterial, variant: StoneVariant): Material =
    mat match {
      case StoneMaterial.STONE =>
        variant match {
          case StoneVariant.SMOOTH => Material.SMOOTH_STONE
          case _                   => Material.STONE
        }
      case StoneMaterial.STONE_BRICK =>
        variant match {
          case StoneVariant.CHISELED => Material.CHISELED_STONE_BRICKS
          case StoneVariant.CRACKED  => Material.CRACKED_STONE_BRICKS
          case StoneVariant.MOSSY    => Material.MOSSY_STONE_BRICKS
          case _                     => Material.STONE_BRICKS
        }
    }

  def mapStonite(mat: StoniteMaterial, variant: StoniteVariant): Material =
    mat match {
      case StoniteMaterial.ANDESITE =>
        variant match {
          case StoniteVariant.NORMAL   => Material.ANDESITE
          case StoniteVariant.POLISHED => Material.POLISHED_ANDESITE
        }
      case StoniteMaterial.DIORITE =>
        variant match {
          case StoniteVariant.NORMAL   => Material.DIORITE
          case StoniteVariant.POLISHED => Material.POLISHED_DIORITE
        }
      case StoniteMaterial.GRANITE =>
        variant match {
          case StoniteVariant.NORMAL   => Material.GRANITE
          case StoniteVariant.POLISHED => Material.POLISHED_GRANITE
        }
    }

  def mapStructureBlock(variant: StructureBlockVariant): Material =
    variant match {
      case StructureBlockVariant.VOID => Material.STRUCTURE_VOID
      case _                          => Material.STRUCTURE_BLOCK
    }

  def mapStructureBlockMode(
      mode: SpigotStructureBlockMode
  ): StructureBlockVariant =
    mode match {
      case SpigotStructureBlockMode.CORNER => StructureBlockVariant.CORNER
      case SpigotStructureBlockMode.DATA   => StructureBlockVariant.DATA
      case SpigotStructureBlockMode.LOAD   => StructureBlockVariant.LOAD
      case SpigotStructureBlockMode.SAVE   => StructureBlockVariant.SAVE
    }

  def mapStructureBlockMode(
      variant: StructureBlockVariant
  ): Option[SpigotStructureBlockMode] =
    variant match {
      case StructureBlockVariant.CORNER => Some(SpigotStructureBlockMode.CORNER)
      case StructureBlockVariant.DATA   => Some(SpigotStructureBlockMode.DATA)
      case StructureBlockVariant.LOAD   => Some(SpigotStructureBlockMode.LOAD)
      case StructureBlockVariant.SAVE   => Some(SpigotStructureBlockMode.SAVE)
      case StructureBlockVariant.VOID   => None
    }

  def mapWall(mat: WallMaterial, variant: Option[WallVariant]): Material =
    mat match {
      // BRICK
      case BrickMaterial.BRICK            => Material.BRICK_WALL
      case BrickMaterial.NETHER_BRICK     => Material.NETHER_BRICK_WALL
      case BrickMaterial.RED_NETHER_BRICK => Material.RED_NETHER_BRICK_WALL

      // COBBLESTONE
      case _: CobblestoneMaterial =>
        variant match {
          case Some(CobblestoneVariant.MOSSY) => Material.MOSSY_COBBLESTONE_WALL
          case _                              => Material.COBBLESTONE_WALL
        }

      // END_STONE
      case _: EndStoneMaterial => Material.END_STONE_BRICK_WALL

      // PRISMARINE
      case _: PrismarineMaterial => Material.PRISMARINE_WALL

      // SANDSTONE
      case SandstoneMaterial.SANDSTONE     => Material.SANDSTONE_WALL
      case SandstoneMaterial.RED_SANDSTONE => Material.RED_SANDSTONE_WALL

      // STONE
      case _: StoneMaterial =>
        variant match {
          case Some(StoneVariant.MOSSY) => Material.MOSSY_STONE_BRICK_WALL
          case _                        => Material.STONE_BRICK_WALL
        }

      // STONITE
      case StoniteMaterial.ANDESITE => Material.ANDESITE_WALL
      case StoniteMaterial.DIORITE  => Material.DIORITE_WALL
      case StoniteMaterial.GRANITE  => Material.GRANITE_WALL
    }

  def mapWeightedPressurePlate(
      variant: WeightedPressurePlateVariant
  ): Material = variant match {
    case WeightedPressurePlateVariant.LIGHT =>
      Material.LIGHT_WEIGHTED_PRESSURE_PLATE
    case WeightedPressurePlateVariant.HEAVY =>
      Material.HEAVY_WEIGHTED_PRESSURE_PLATE
  }
}
