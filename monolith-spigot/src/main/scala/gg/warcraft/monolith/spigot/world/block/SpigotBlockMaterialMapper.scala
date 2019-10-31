package gg.warcraft.monolith.spigot.world.block

import java.util

import gg.warcraft.monolith.api.core.Extensions._
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

  def map(material: Material): BlockMaterial = cache.computeIfAbsent(material, {
    case _ if !material.isBlock => throw new IllegalArgumentException(s"$material")

    // IRON
    case Material.IRON_DOOR => IronMaterial.IRON

    // SAND
    case Material.SAND     => SandMaterial.SAND
    case Material.RED_SAND => SandMaterial.RED_SAND

    case _ => material.name match {

      // BRICK
      case r"BRICK.*"            => BrickMaterial.BRICK
      case r"NETHER_BRICK.*"     => BrickMaterial.NETHER_BRICK
      case r"RED_NETHER_BRICK.*" => BrickMaterial.RED_NETHER_BRICK

      // COBBLESTONE
      case r".*COBBLESTONE.*" => CobblestoneMaterial.COBBLESTONE

      // END_STONE
      case r"END_STONE_BRICK.*" => EndStoneMaterial.END_STONE_BRICK
      case r"END_STONE.*"       => EndStoneMaterial.END_STONE

      // INFESTED_BLOCK
      case r"INFESTED_COBBLESTONE.*" => CobblestoneMaterial.COBBLESTONE
      case r"INFESTED_STONE_BRICK.*" => StoneMaterial.STONE_BRICK
      case r"INFESTED_STONE.*"       => StoneMaterial.STONE

      // PRISMARINE
      case r"DARK_PRISMARINE.*"  => PrismarineMaterial.DARK_PRISMARINE
      case r"PRISMARINE_BRICK.*" => PrismarineMaterial.PRISMARINE_BRICK
      case r"PRISMARINE.*"       => PrismarineMaterial.PRISMARINE

      // PURPUR
      case r"PURPUR.*" => PurpurMaterial.PURPUR

      // QUARTZ
      case r".*QUARTZ.*" => QuartzMaterial.QUARTZ

      // SANDSTONE
      case r".*RED_SANDSTONE.*" => SandstoneMaterial.RED_SANDSTONE
      case r".*SANDSTONE.*"     => SandstoneMaterial.SANDSTONE

      // STONE
      case r".*STONE_BRICK.*" => StoneMaterial.STONE_BRICK
      case r".*STONE.*"       => StoneMaterial.STONE

      // STONITE
      case r".*ANDESITE.*" => StoniteMaterial.ANDESITE
      case r".*DIORITE.*"  => StoniteMaterial.DIORITE
      case r".*GRANITE.*"  => StoniteMaterial.GRANITE

      // WOOD
      case r".*ACACIA.*"   => WoodMaterial.ACACIA
      case r".*BIRCH.*"    => WoodMaterial.BIRCH
      case r".*DARK_OAK.*" => WoodMaterial.DARK_OAK
      case r".*JUNGLE.*"   => WoodMaterial.JUNGLE
      case r".*OAK.*"      => WoodMaterial.OAK
      case r".*SPRUCE.*"   => WoodMaterial.SPRUCE

      case _ => throw new IllegalArgumentException(s"$material")
    }
  })

  def map(block: Block): Material = block match {
    case it: ColoredBlock     => colorMapper.map(it)
    case it: VariedBlock[_]   => variantMapper.map(it)
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
    case _: Observer         => Material.OBSERVER
    case _: Obsidian         => Material.OBSIDIAN
    case _: Podzol           => Material.PODZOL
    case _: Potatoes         => Material.POTATOES
    case _: Prismarine       => Material.PRISMARINE
    case _: Pumpkin          => Material.PUMPKIN
    case _: PumpkinStem      => Material.PUMPKIN_STEM
    case _: Purpur           => Material.PURPUR_BLOCK
    case _: QuartzOre        => Material.NETHER_QUARTZ_ORE
    case _: Redstone         => Material.REDSTONE_BLOCK
    case _: RedstoneLamp     => Material.REDSTONE_LAMP
    case _: RedstoneOre      => Material.REDSTONE_ORE
    case _: RedstoneWire     => Material.REDSTONE_WIRE
    case _: Repeater         => Material.REPEATER
    case _: Scaffold         => Material.SCAFFOLDING
    case _: SeaLantern       => Material.SEA_LANTERN
    case _: SeaPickle        => Material.SEA_PICKLE
    case _: Slime            => Material.SLIME_BLOCK
    case _: SmithingTable    => Material.SMITHING_TABLE
    case _: Smoker           => Material.SMOKER
    case _: Snow             => Material.SNOW
    case _: SnowBlock        => Material.SNOW_BLOCK
    case _: Spawner          => Material.SPAWNER
    case _: StoneCutter      => Material.STONECUTTER
    case _: SugarCane        => Material.SUGAR_CANE
    case _: SweetBerryBush   => Material.SWEET_BERRY_BUSH
    case _: TNT              => Material.TNT
    case _: Torch            => Material.TORCH
    case _: TurtleEgg        => Material.TURTLE_EGG
    case _: Vine             => Material.VINE
    case _: Water            => Material.WATER
    case _: Wheat            => Material.WHEAT

    case it: Button        => mapButton(it.material)
    case it: Brick         => mapBrick(it.material)
    case it: Door          => mapDoor(it.material)
    case it: EndStone      => mapEndStone(it.material)
    case it: Fence         => mapFence(it.material)
    case it: Fern          => mapFern(it.tall)
    case it: Gate          => mapGate(it.material)
    case it: Grass         => mapGrass(it.tall)
    case it: Kelp          => mapKelp(it.state)
    case it: Leaves        => mapLeaves(it.material)
    case it: Pillar        => mapPillar(it.material)
    case it: Piston        => mapPiston(it.sticky)
    case it: Planks        => mapPlanks(it.material)
    case it: PressurePlate => mapPressurePlate(it.material)
    case it: RedstoneTorch => mapRedstoneTorch(it.direction)
    case it: Sand          => mapSand(it.material)
    case it: Sandstone     => mapSandstone(it.material)
    case it: Seagrass      => mapSeagrass(it.tall)
    case it: Sponge        => mapSponge(it.wet)
    case it: Trapdoor      => mapTrapdoor(it.material)

    case it: Log =>
      if (it.stripped) mapStrippedLog(it.material)
      else mapLog(it.material)

    case it: Sign =>
      if (it.direction.isEmpty) mapSign(it.material)
      else mapWallSign(it.material)

    case it: Wood =>
      if (it.stripped) mapStrippedWood(it.material)
      else mapWood(it.material)
  }

  def mapButton(material: ButtonMaterial): Material = material match {
    case _: StoneMaterial      => Material.STONE_BUTTON
    case WoodMaterial.ACACIA   => Material.ACACIA_BUTTON
    case WoodMaterial.BIRCH    => Material.BIRCH_BUTTON
    case WoodMaterial.DARK_OAK => Material.DARK_OAK_BUTTON
    case WoodMaterial.JUNGLE   => Material.JUNGLE_BUTTON
    case WoodMaterial.OAK      => Material.OAK_BUTTON
    case WoodMaterial.SPRUCE   => Material.SPRUCE_BUTTON
  }

  def mapBrick(material: BrickMaterial): Material = material match {
    case BrickMaterial.BRICK            => Material.BRICKS
    case BrickMaterial.NETHER_BRICK     => Material.NETHER_BRICKS
    case BrickMaterial.RED_NETHER_BRICK => Material.RED_NETHER_BRICKS
  }

  def mapDoor(material: DoorMaterial): Material = material match {
    case _: IronMaterial       => Material.IRON_DOOR
    case WoodMaterial.ACACIA   => Material.ACACIA_DOOR
    case WoodMaterial.BIRCH    => Material.BIRCH_DOOR
    case WoodMaterial.DARK_OAK => Material.DARK_OAK_DOOR
    case WoodMaterial.JUNGLE   => Material.ACACIA_DOOR
    case WoodMaterial.OAK      => Material.ACACIA_DOOR
    case WoodMaterial.SPRUCE   => Material.ACACIA_DOOR
  }

  def mapEndStone(material: EndStoneMaterial): Material = material match {
    case EndStoneMaterial.END_STONE       => Material.END_STONE
    case EndStoneMaterial.END_STONE_BRICK => Material.END_STONE_BRICKS
  }

  def mapFence(material: FenceMaterial): Material = material match {
    case _: BrickMaterial      => Material.NETHER_BRICK_FENCE
    case WoodMaterial.ACACIA   => Material.ACACIA_FENCE
    case WoodMaterial.BIRCH    => Material.BIRCH_FENCE
    case WoodMaterial.DARK_OAK => Material.DARK_OAK_FENCE
    case WoodMaterial.JUNGLE   => Material.ACACIA_FENCE
    case WoodMaterial.OAK      => Material.ACACIA_FENCE
    case WoodMaterial.SPRUCE   => Material.ACACIA_FENCE
  }

  def mapFern(tall: Boolean): Material =
    if (tall) Material.LARGE_FERN
    else Material.FERN

  def mapGate(material: WoodMaterial): Material = material match { // TODO rename Fence Gate
    case WoodMaterial.ACACIA   => Material.ACACIA_FENCE_GATE
    case WoodMaterial.BIRCH    => Material.BIRCH_FENCE_GATE
    case WoodMaterial.DARK_OAK => Material.DARK_OAK_FENCE_GATE
    case WoodMaterial.JUNGLE   => Material.JUNGLE_FENCE_GATE
    case WoodMaterial.OAK      => Material.OAK_FENCE_GATE
    case WoodMaterial.SPRUCE   => Material.SPRUCE_FENCE_GATE
  }

  def mapGrass(tall: Boolean): Material =
    if (tall) Material.TALL_GRASS
    else Material.GRASS

  def mapKelp(state: KelpState): Material =
    if (state == KelpState.AGE_25) Material.KELP_PLANT
    else Material.KELP

  def mapLeaves(material: WoodMaterial): Material = material match {
    case WoodMaterial.ACACIA   => Material.ACACIA_LEAVES
    case WoodMaterial.BIRCH    => Material.BIRCH_LEAVES
    case WoodMaterial.DARK_OAK => Material.DARK_OAK_LEAVES
    case WoodMaterial.JUNGLE   => Material.JUNGLE_LEAVES
    case WoodMaterial.OAK      => Material.OAK_LEAVES
    case WoodMaterial.SPRUCE   => Material.SPRUCE_LEAVES
  }

  def mapLog(material: WoodMaterial): Material = material match {
    case WoodMaterial.ACACIA   => Material.ACACIA_LOG
    case WoodMaterial.BIRCH    => Material.BIRCH_LOG
    case WoodMaterial.DARK_OAK => Material.DARK_OAK_LOG
    case WoodMaterial.JUNGLE   => Material.JUNGLE_LOG
    case WoodMaterial.OAK      => Material.OAK_LOG
    case WoodMaterial.SPRUCE   => Material.SPRUCE_LOG
  }

  def mapStrippedLog(material: WoodMaterial): Material = material match {
    case WoodMaterial.ACACIA   => Material.STRIPPED_ACACIA_LOG
    case WoodMaterial.BIRCH    => Material.STRIPPED_BIRCH_LOG
    case WoodMaterial.DARK_OAK => Material.STRIPPED_DARK_OAK_LOG
    case WoodMaterial.JUNGLE   => Material.STRIPPED_JUNGLE_LOG
    case WoodMaterial.OAK      => Material.STRIPPED_OAK_LOG
    case WoodMaterial.SPRUCE   => Material.STRIPPED_SPRUCE_LOG
  }

  def mapPillar(material: PillarMaterial): Material = material match {
    case _: PurpurMaterial => Material.PURPUR_PILLAR
    case _: QuartzMaterial => Material.QUARTZ_PILLAR
  }

  def mapPiston(sticky: Boolean): Material =
    if (sticky) Material.STICKY_PISTON
    else Material.PISTON

  def mapPlanks(material: WoodMaterial): Material = material match {
    case WoodMaterial.ACACIA   => Material.ACACIA_PLANKS
    case WoodMaterial.BIRCH    => Material.BIRCH_PLANKS
    case WoodMaterial.DARK_OAK => Material.DARK_OAK_PLANKS
    case WoodMaterial.JUNGLE   => Material.JUNGLE_PLANKS
    case WoodMaterial.OAK      => Material.OAK_PLANKS
    case WoodMaterial.SPRUCE   => Material.SPRUCE_PLANKS
  }

  def mapPressurePlate(material: PressurePlateMaterial): Material = material match {
    case _: StoneMaterial      => Material.STONE_PRESSURE_PLATE
    case WoodMaterial.ACACIA   => Material.ACACIA_PRESSURE_PLATE
    case WoodMaterial.BIRCH    => Material.BIRCH_PRESSURE_PLATE
    case WoodMaterial.DARK_OAK => Material.DARK_OAK_PRESSURE_PLATE
    case WoodMaterial.JUNGLE   => Material.JUNGLE_PRESSURE_PLATE
    case WoodMaterial.OAK      => Material.OAK_PRESSURE_PLATE
    case WoodMaterial.SPRUCE   => Material.SPRUCE_PRESSURE_PLATE
  }

  def mapRedstoneTorch(direction: Option[BlockFace]): Material =
    if (direction.isEmpty) Material.REDSTONE_TORCH
    else Material.REDSTONE_WALL_TORCH

  def mapSand(material: SandMaterial): Material = material match {
    case SandMaterial.SAND     => Material.SAND
    case SandMaterial.RED_SAND => Material.RED_SAND
  }

  def mapSandstone(material: SandstoneMaterial): Material = material match {
    case SandstoneMaterial.SANDSTONE     => Material.SANDSTONE
    case SandstoneMaterial.RED_SANDSTONE => Material.RED_SANDSTONE
  }

  def mapSeagrass(tall: Boolean): Material =
    if (tall) Material.TALL_SEAGRASS
    else Material.SEAGRASS

  def mapSign(material: WoodMaterial): Material = material match {
    case WoodMaterial.ACACIA   => Material.ACACIA_SIGN
    case WoodMaterial.BIRCH    => Material.BIRCH_SIGN
    case WoodMaterial.DARK_OAK => Material.DARK_OAK_SIGN
    case WoodMaterial.JUNGLE   => Material.JUNGLE_SIGN
    case WoodMaterial.OAK      => Material.OAK_SIGN
    case WoodMaterial.SPRUCE   => Material.SPRUCE_SIGN
  }

  def mapWallSign(material: WoodMaterial): Material = material match {
    case WoodMaterial.ACACIA   => Material.ACACIA_WALL_SIGN
    case WoodMaterial.BIRCH    => Material.BIRCH_WALL_SIGN
    case WoodMaterial.DARK_OAK => Material.DARK_OAK_WALL_SIGN
    case WoodMaterial.JUNGLE   => Material.JUNGLE_WALL_SIGN
    case WoodMaterial.OAK      => Material.OAK_WALL_SIGN
    case WoodMaterial.SPRUCE   => Material.SPRUCE_WALL_SIGN
  }

  def mapSponge(wet: Boolean): Material =
    if (wet) Material.WET_SPONGE
    else Material.SPONGE

  def mapTrapdoor(material: TrapdoorMaterial): Material = material match {
    case _: IronMaterial       => Material.IRON_TRAPDOOR
    case WoodMaterial.ACACIA   => Material.ACACIA_TRAPDOOR
    case WoodMaterial.BIRCH    => Material.BIRCH_TRAPDOOR
    case WoodMaterial.DARK_OAK => Material.DARK_OAK_TRAPDOOR
    case WoodMaterial.JUNGLE   => Material.JUNGLE_TRAPDOOR
    case WoodMaterial.OAK      => Material.OAK_TRAPDOOR
    case WoodMaterial.SPRUCE   => Material.SPRUCE_TRAPDOOR
  }

  def mapWood(material: WoodMaterial): Material = material match {
    case WoodMaterial.ACACIA   => Material.ACACIA_WOOD
    case WoodMaterial.BIRCH    => Material.BIRCH_WOOD
    case WoodMaterial.DARK_OAK => Material.DARK_OAK_WOOD
    case WoodMaterial.JUNGLE   => Material.JUNGLE_WOOD
    case WoodMaterial.OAK      => Material.OAK_WOOD
    case WoodMaterial.SPRUCE   => Material.SPRUCE_WOOD
  }

  def mapStrippedWood(material: WoodMaterial): Material = material match {
    case WoodMaterial.ACACIA   => Material.STRIPPED_ACACIA_WOOD
    case WoodMaterial.BIRCH    => Material.STRIPPED_BIRCH_WOOD
    case WoodMaterial.DARK_OAK => Material.STRIPPED_DARK_OAK_WOOD
    case WoodMaterial.JUNGLE   => Material.STRIPPED_JUNGLE_WOOD
    case WoodMaterial.OAK      => Material.STRIPPED_OAK_WOOD
    case WoodMaterial.SPRUCE   => Material.STRIPPED_SPRUCE_WOOD
  }
}
