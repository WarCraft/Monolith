package gg.warcraft.monolith.spigot.world.block

import java.util

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.material._
import gg.warcraft.monolith.api.world.block.state.KelpState
import javax.inject.Inject
import org.bukkit.Material

class SpigotBlockMaterialMapper @Inject()(
    private val colorMapper: SpigotBlockColorMapper,
    private val variantMapper: SpigotBlockVariantMapper
) {

  private val cache =
    new util.EnumMap[Material, BlockMaterial](classOf[Material])

  def map(material: Material): BlockMaterial =
    cache.computeIfAbsent(material, compute)

  private def compute(material: Material): BlockMaterial = material match {
    case it if !it.isBlock => throw new IllegalArgumentException(s"$material")

    // BRICK
    case it if it $is "BRICK"            => BrickMaterial.BRICK
    case it if it $is "NETHER_BRICK"     => BrickMaterial.NETHER_BRICK
    case it if it $is "RED_NETHER_BRICK" => BrickMaterial.RED_NETHER_BRICK

    // COBBLESTONE
    case it if it is "COBBLESTONE" => CobblestoneMaterial.COBBLESTONE

    // END_STONE
    case it if it $is "END_STONE_BRICK" => EndStoneMaterial.END_STONE_BRICK
    case it if it $is "END_STONE"       => EndStoneMaterial.END_STONE

    // INFESTED_BLOCK
    case it if it $is "INFESTED_COBBLESTONE" => CobblestoneMaterial.COBBLESTONE
    case it if it $is "INFESTED_STONE_BRICK" => StoneMaterial.STONE_BRICK
    case it if it $is "INFESTED_STONE"       => StoneMaterial.STONE

    // IRON
    case Material.IRON_DOOR => IronMaterial.IRON

    // PRISMARINE
    case it if it $is "DARK_PRISMARINE"  => PrismarineMaterial.DARK_PRISMARINE
    case it if it $is "PRISMARINE_BRICK" => PrismarineMaterial.PRISMARINE_BRICK
    case it if it $is "PRISMARINE"       => PrismarineMaterial.PRISMARINE

    // PURPUR
    case it if it $is "PURPUR" => PurpurMaterial.PURPUR

    // QUARTZ
    case it if it is "QUARTZ" => QuartzMaterial.QUARTZ

    // SAND
    case Material.SAND      => SandMaterial.SAND
    case Material.RED_SAND  => SandMaterial.RED_SAND

    // SANDSTONE
    case it if it is "RED_SANDSTONE" => SandstoneMaterial.RED_SANDSTONE
    case it if it is "SANDSTONE"     => SandstoneMaterial.SANDSTONE

    // STONE
    case it if it is "STONE_BRICK" => StoneMaterial.STONE_BRICK
    case it if it is "STONE"       => StoneMaterial.STONE

    // STONITE
    case it if it is "ANDESITE" => StoniteMaterial.ANDESITE
    case it if it is "DIORITE"  => StoniteMaterial.DIORITE
    case it if it is "GRANITE"  => StoniteMaterial.GRANITE

    // WOOD
    case it if it is "ACACIA"   => WoodMaterial.ACACIA
    case it if it is "BIRCH"    => WoodMaterial.BIRCH
    case it if it is "DARK_OAK" => WoodMaterial.DARK_OAK
    case it if it is "JUNGLE"   => WoodMaterial.JUNGLE
    case it if it is "OAK"      => WoodMaterial.OAK
    case it if it is "SPRUCE"   => WoodMaterial.SPRUCE
  }

  def map(block: Block): Material = block match {
    case it: ColoredBlock   => colorMapper.map(it)
    case it: VariedBlock[_] => variantMapper.map(it)
    case it: VariableBlock[_] => variantMapper.map(it)

    case _: Barrel           => Material.BARREL
    case _: Barrier          => Material.BARRIER
    case _: Beacon           => Material.BEACON
    case _: Bedrock          => Material.BEDROCK
    case _: Beetroots        => Material.BEETROOTS
    case _: Bell             => Material.BELL
    case _: BlastFurnace     => Material.BLAST_FURNACE
    case _: Bone             => Material.BONE_BLOCK
    case _: Bookshelf        => Material.BOOKSHELF
    case _: BrewingStand     => Material.BREWING_STAND
    case _: Brick            => Material.BRICK
    case _: BubbleColumn     => Material.BUBBLE_COLUMN
    case _: Cactus           => Material.CACTUS
    case _: Cake             => Material.CAKE
    case _: Campfire         => Material.CAMPFIRE
    case _: Carrots          => Material.CARROTS
    case _: CartographyTable => Material.CARTOGRAPHY_TABLE
    case _: Cauldron         => Material.CAULDRON
    case _: ChorusFlower     => Material.CHORUS_FLOWER
    case _: ChorusPlant      => Material.CHORUS_PLANT
    case _: Clay             => Material.CLAY
    case _: Coal             => Material.COAL_BLOCK
    case _: CoalOre          => Material.COAL_ORE
    case _: Cobweb           => Material.COBWEB
    case _: CocoaPod         => Material.COCOA
    case _: Comparator       => Material.COMPARATOR
    case _: Composter        => Material.COMPOSTER
    case _: Conduit          => Material.CONDUIT
    case _: CraftingTable    => Material.CRAFTING_TABLE
    case _: DaylightDetector => Material.DAYLIGHT_DETECTOR
    case _: DeadBush         => Material.DEAD_BUSH
    case _: Diamond          => Material.DIAMOND_BLOCK
    case _: DiamondOre       => Material.DIAMOND_ORE
    case _: Dirt             => Material.DIRT
    case _: Dispenser        => Material.DISPENSER
    case _: DragonEgg        => Material.DRAGON_EGG
    case _: DriedKelp        => Material.DRIED_KELP_BLOCK
    case _: Dropper          => Material.DROPPER
    case _: Emerald          => Material.EMERALD_BLOCK
    case _: EmeraldOre       => Material.EMERALD_ORE
    case _: EnchantingTable  => Material.ENCHANTING_TABLE
    case _: EndGateway       => Material.END_GATEWAY
    case _: EndPortal        => Material.END_PORTAL
    case _: EndPortalFrame   => Material.END_PORTAL_FRAME
    case _: EndRod           => Material.END_ROD
    case _: Farmland         => Material.FARMLAND
    case _: Fire             => Material.FIRE
    case _: FletchingTable   => Material.FLETCHING_TABLE
    case _: Furnace          => Material.FURNACE
    case _: Glowstone        => Material.GLOWSTONE
    case _: Gold             => Material.GOLD_BLOCK
    case _: GoldOre          => Material.GOLD_ORE
    case _: GrassBlock       => Material.GRASS_BLOCK
    case _: GrassPath        => Material.GRASS_PATH
    case _: Gravel           => Material.GRAVEL
    case _: Grindstone       => Material.GRINDSTONE
    case _: HayBale          => Material.HAY_BLOCK
    case _: Hopper           => Material.HOPPER
    case _: Iron             => Material.IRON_BLOCK
    case _: IronBars         => Material.IRON_BARS
    case _: IronOre          => Material.IRON_ORE
    case _: Jigsaw           => Material.JIGSAW
    case _: Jukebox          => Material.JUKEBOX
    case _: Ladder           => Material.LADDER
    case _: Lantern          => Material.LANTERN
    case _: Lapis            => Material.LAPIS_BLOCK
    case _: LapisOre         => Material.LAPIS_ORE
    case _: Lava             => Material.LAVA
    case _: Lectern          => Material.LECTERN
    case _: Lever            => Material.LEVER
    case _: LilyPad          => Material.LILY_PAD
    case _: Loom             => Material.LOOM
    case _: Magma            => Material.MAGMA_BLOCK
    case _: Melon            => Material.MELON
    case _: MelonStem        => Material.MELON_STEM
    case _: Mycelium         => Material.MYCELIUM
    case _: NetherPortal     => Material.NETHER_PORTAL
    case _: Netherrack       => Material.NETHERRACK
    case _: NetherWarts      => Material.NETHER_WART
    case _: NetherWartBlock  => Material.NETHER_WART_BLOCK
    case _: NoteBlock        => Material.NOTE_BLOCK
    case _: Observer         => Material.OBSERVER
    case _: Obsidian         => Material.OBSIDIAN
    case _: Podzol           => Material.PODZOL
    case _: Potatoes         => Material.POTATOES
    case _: Prismarine       => Material.PRISMARINE
    case _: Pumpkin          => Material.PUMPKIN
    case _: PumpkinStem      => Material.PUMPKIN_STEM
    case _: Purpur           => Material.PURPUR_BLOCK
    case _: Quartz           => Material.QUARTZ_BLOCK
    case _: QuartzOre        => Material.NETHER_QUARTZ_ORE
    case _: Redstone         => Material.REDSTONE_BLOCK
    case _: RedstoneLamp     => Material.REDSTONE_LAMP
    case _: RedstoneOre      => Material.REDSTONE_ORE
    case _: RedstoneWire     => Material.REDSTONE_WIRE
    case _: Repeater         => Material.REPEATER
    case _: Scaffold         => Material.SCAFFOLDING
    case _: SeaLantern       => Material.SEA_LANTERN
    case _: SeaPickle        => Material.SEA_PICKLE
    case _: Slime       => Material.SLIME_BLOCK
    case _: SmithingTable    => Material.SMITHING_TABLE
    case _: Smoker           => Material.SMOKER
    case _: Snow             => Material.SNOW
    case _: SnowBlock        => Material.SNOW_BLOCK
    case _: Spawner          => Material.SPAWNER
    case _: StoneCutter      => Material.STONECUTTER
    case _: StructureBlock   => Material.STRUCTURE_BLOCK
    case _: SugarCane        => Material.SUGAR_CANE
    case _: SweetBerryBush   => Material.SWEET_BERRY_BUSH
    case _: TNT              => Material.TNT
    case _: Torch            => Material.TORCH
    case _: TurtleEgg        => Material.TURTLE_EGG
    case _: Vine             => Material.VINE
    case _: Water            => Material.WATER
    case _: Wheat            => Material.WHEAT

    case it: Button =>
      it.material match {
        case _: StoneMaterial      => Material.STONE_BUTTON
        case WoodMaterial.ACACIA   => Material.ACACIA_BUTTON
        case WoodMaterial.BIRCH    => Material.BIRCH_BUTTON
        case WoodMaterial.DARK_OAK => Material.DARK_OAK_BUTTON
        case WoodMaterial.JUNGLE   => Material.JUNGLE_BUTTON
        case WoodMaterial.OAK      => Material.OAK_BUTTON
        case WoodMaterial.SPRUCE   => Material.SPRUCE_BUTTON
      }

    case it: Brick => it.material match {
      case BrickMaterial.BRICK            => Material.BRICKS
      case BrickMaterial.NETHER_BRICK     => Material.NETHER_BRICKS
      case BrickMaterial.RED_NETHER_BRICK => Material.RED_NETHER_BRICKS
    }

    case it: Door =>
      it.material match {
        case _: IronMaterial       => Material.IRON_DOOR
        case WoodMaterial.ACACIA   => Material.ACACIA_DOOR
        case WoodMaterial.BIRCH    => Material.BIRCH_DOOR
        case WoodMaterial.DARK_OAK => Material.DARK_OAK_DOOR
        case WoodMaterial.JUNGLE   => Material.ACACIA_DOOR
        case WoodMaterial.OAK      => Material.ACACIA_DOOR
        case WoodMaterial.SPRUCE   => Material.ACACIA_DOOR
      }

    case it: EndStone => it.material match {
      case EndStoneMaterial.END_STONE       => Material.END_STONE
      case EndStoneMaterial.END_STONE_BRICK => Material.END_STONE_BRICKS
    }

    case it: Fence =>
      it.material match {
        case _: BrickMaterial      => Material.NETHER_BRICK_FENCE
        case WoodMaterial.ACACIA   => Material.ACACIA_FENCE
        case WoodMaterial.BIRCH    => Material.BIRCH_FENCE
        case WoodMaterial.DARK_OAK => Material.DARK_OAK_FENCE
        case WoodMaterial.JUNGLE   => Material.ACACIA_FENCE
        case WoodMaterial.OAK      => Material.ACACIA_FENCE
        case WoodMaterial.SPRUCE   => Material.ACACIA_FENCE
      }

    case it: Fern => if (it.tall) Material.LARGE_FERN else Material.FERN

    case it: Gate =>
      it.material match {
        case WoodMaterial.ACACIA   => Material.ACACIA_FENCE_GATE
        case WoodMaterial.BIRCH    => Material.BIRCH_FENCE_GATE
        case WoodMaterial.DARK_OAK => Material.DARK_OAK_FENCE_GATE
        case WoodMaterial.JUNGLE   => Material.JUNGLE_FENCE_GATE
        case WoodMaterial.OAK      => Material.OAK_FENCE_GATE
        case WoodMaterial.SPRUCE   => Material.SPRUCE_FENCE_GATE
      }

    case it: Grass => if (it.tall) Material.TALL_GRASS else Material.GRASS

    case it: Kelp =>
      if(it.state == KelpState.AGE_25) Material.KELP_PLANT
      else Material.KELP

    case it: Leaves =>
      it.material match {
        case WoodMaterial.ACACIA   => Material.ACACIA_LEAVES
        case WoodMaterial.BIRCH    => Material.BIRCH_LEAVES
        case WoodMaterial.DARK_OAK => Material.DARK_OAK_LEAVES
        case WoodMaterial.JUNGLE   => Material.JUNGLE_LEAVES
        case WoodMaterial.OAK      => Material.OAK_LEAVES
        case WoodMaterial.SPRUCE   => Material.SPRUCE_LEAVES
      }

    case it: Log =>
      if (it.stripped) {
        it.material match {
          case WoodMaterial.ACACIA   => Material.STRIPPED_ACACIA_LOG
          case WoodMaterial.BIRCH    => Material.STRIPPED_BIRCH_LOG
          case WoodMaterial.DARK_OAK => Material.STRIPPED_DARK_OAK_LOG
          case WoodMaterial.JUNGLE   => Material.STRIPPED_JUNGLE_LOG
          case WoodMaterial.OAK      => Material.STRIPPED_OAK_LOG
          case WoodMaterial.SPRUCE   => Material.STRIPPED_SPRUCE_LOG
        }
      } else {
        it.material match {
          case WoodMaterial.ACACIA   => Material.ACACIA_LOG
          case WoodMaterial.BIRCH    => Material.BIRCH_LOG
          case WoodMaterial.DARK_OAK => Material.DARK_OAK_LOG
          case WoodMaterial.JUNGLE   => Material.JUNGLE_LOG
          case WoodMaterial.OAK      => Material.OAK_LOG
          case WoodMaterial.SPRUCE   => Material.SPRUCE_LOG
        }
      }

    case it: Pillar =>
      it.material match {
        case _: PurpurMaterial => Material.PURPUR_PILLAR
        case _: QuartzMaterial => Material.QUARTZ_PILLAR
      }

    case it: Piston =>
      if (it.sticky) Material.STICKY_PISTON else Material.PISTON

    case it: Planks =>
      it.material match {
        case WoodMaterial.ACACIA   => Material.ACACIA_PLANKS
        case WoodMaterial.BIRCH    => Material.BIRCH_PLANKS
        case WoodMaterial.DARK_OAK => Material.DARK_OAK_PLANKS
        case WoodMaterial.JUNGLE   => Material.JUNGLE_PLANKS
        case WoodMaterial.OAK      => Material.OAK_PLANKS
        case WoodMaterial.SPRUCE   => Material.SPRUCE_PLANKS
      }

    case it: PressurePlate =>
      it.material match {
        case _: StoneMaterial      => Material.STONE_PRESSURE_PLATE
        case WoodMaterial.ACACIA   => Material.ACACIA_PRESSURE_PLATE
        case WoodMaterial.BIRCH    => Material.BIRCH_PRESSURE_PLATE
        case WoodMaterial.DARK_OAK => Material.DARK_OAK_PRESSURE_PLATE
        case WoodMaterial.JUNGLE   => Material.JUNGLE_PRESSURE_PLATE
        case WoodMaterial.OAK      => Material.OAK_PRESSURE_PLATE
        case WoodMaterial.SPRUCE   => Material.SPRUCE_PRESSURE_PLATE
      }

    case it: RedstoneTorch =>
      if (it.direction.isEmpty) Material.REDSTONE_TORCH
      else Material.REDSTONE_WALL_TORCH

    case it: Sand =>
      it.material match {
        case SandMaterial.SAND      => Material.SAND
        case SandMaterial.RED_SAND  => Material.RED_SAND
      }

    case it: Sandstone =>
      it.material match {
        case SandstoneMaterial.SANDSTONE     => Material.SANDSTONE
        case SandstoneMaterial.RED_SANDSTONE => Material.RED_SANDSTONE
      }

    case it: Seagrass =>
      if (it.tall) Material.TALL_SEAGRASS else Material.SEAGRASS

    case it: Sign =>
      if (it.direction.isEmpty) {
        it.material match {
          case WoodMaterial.ACACIA   => Material.ACACIA_SIGN
          case WoodMaterial.BIRCH    => Material.BIRCH_SIGN
          case WoodMaterial.DARK_OAK => Material.DARK_OAK_SIGN
          case WoodMaterial.JUNGLE   => Material.JUNGLE_SIGN
          case WoodMaterial.OAK      => Material.OAK_SIGN
          case WoodMaterial.SPRUCE   => Material.SPRUCE_SIGN
        }
      } else {
        it.material match {
          case WoodMaterial.ACACIA   => Material.ACACIA_WALL_SIGN
          case WoodMaterial.BIRCH    => Material.BIRCH_WALL_SIGN
          case WoodMaterial.DARK_OAK => Material.DARK_OAK_WALL_SIGN
          case WoodMaterial.JUNGLE   => Material.JUNGLE_WALL_SIGN
          case WoodMaterial.OAK      => Material.OAK_WALL_SIGN
          case WoodMaterial.SPRUCE   => Material.SPRUCE_WALL_SIGN
        }
      }

    case it: Sponge => if (it.wet) Material.WET_SPONGE else Material.SPONGE

    case it: Trapdoor =>
      it.material match {
        case _: IronMaterial       => Material.IRON_TRAPDOOR
        case WoodMaterial.ACACIA   => Material.ACACIA_TRAPDOOR
        case WoodMaterial.BIRCH    => Material.BIRCH_TRAPDOOR
        case WoodMaterial.DARK_OAK => Material.DARK_OAK_TRAPDOOR
        case WoodMaterial.JUNGLE   => Material.JUNGLE_TRAPDOOR
        case WoodMaterial.OAK      => Material.OAK_TRAPDOOR
        case WoodMaterial.SPRUCE   => Material.SPRUCE_TRAPDOOR
      }

    case it: Wood =>
      if (it.stripped) {
        it.material match {
          case WoodMaterial.ACACIA   => Material.STRIPPED_ACACIA_WOOD
          case WoodMaterial.BIRCH    => Material.STRIPPED_BIRCH_WOOD
          case WoodMaterial.DARK_OAK => Material.STRIPPED_DARK_OAK_WOOD
          case WoodMaterial.JUNGLE   => Material.STRIPPED_JUNGLE_WOOD
          case WoodMaterial.OAK      => Material.STRIPPED_DARK_OAK_WOOD
          case WoodMaterial.SPRUCE   => Material.STRIPPED_SPRUCE_WOOD
        }
      } else {
        it.material match {
          case WoodMaterial.ACACIA   => Material.ACACIA_WOOD
          case WoodMaterial.BIRCH    => Material.BIRCH_WOOD
          case WoodMaterial.DARK_OAK => Material.DARK_OAK_WOOD
          case WoodMaterial.JUNGLE   => Material.JUNGLE_WOOD
          case WoodMaterial.OAK      => Material.DARK_OAK_WOOD
          case WoodMaterial.SPRUCE   => Material.SPRUCE_WOOD
        }
      }
  }
}
