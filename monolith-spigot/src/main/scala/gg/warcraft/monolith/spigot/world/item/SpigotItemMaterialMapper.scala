package gg.warcraft.monolith.spigot.world.item

import java.util

import gg.warcraft.monolith.api.core.Extensions._
import gg.warcraft.monolith.api.world.item._
import gg.warcraft.monolith.api.world.item.material.{ ArmorMaterial, ToolMaterial }
import gg.warcraft.monolith.spigot.world.block.SpigotBlockMaterialMapper
import javax.inject.Inject
import org.bukkit.Material

class SpigotItemMaterialMapper @Inject()(
    private val colorMapper: SpigotItemColorMapper,
    private val variantMapper: SpigotItemVariantMapper,
    private val blockMapper: SpigotBlockMaterialMapper
) {
  private val cache =
    new util.EnumMap[Material, ItemMaterial](classOf[Material])

  def map(material: Material): ItemMaterial = cache.computeIfAbsent(material, {
    case _ if !material.isItem => throw new IllegalArgumentException(s"$material")
    case _ if material.isBlock => blockMapper.map(material).asInstanceOf[ItemMaterial]

    case Material.DIAMOND_BOOTS | Material.DIAMOND_CHESTPLATE |
         Material.DIAMOND_HELMET | Material.DIAMOND_HORSE_ARMOR |
         Material.DIAMOND_LEGGINGS =>
      ArmorMaterial.DIAMOND

    case Material.DIAMOND_AXE | Material.DIAMOND_HOE |
         Material.DIAMOND_PICKAXE | Material.DIAMOND_SHOVEL |
         Material.DIAMOND_SWORD =>
      ToolMaterial.DIAMOND

    case Material.GOLDEN_BOOTS | Material.GOLDEN_CHESTPLATE |
         Material.GOLDEN_HELMET | Material.GOLDEN_HORSE_ARMOR |
         Material.GOLDEN_LEGGINGS =>
      ArmorMaterial.GOLD

    case Material.GOLDEN_AXE | Material.GOLDEN_HOE |
         Material.GOLDEN_PICKAXE | Material.GOLDEN_SHOVEL |
         Material.GOLDEN_SWORD =>
      ToolMaterial.GOLD

    case Material.IRON_BOOTS | Material.IRON_CHESTPLATE |
         Material.IRON_HELMET | Material.IRON_HORSE_ARMOR |
         Material.IRON_LEGGINGS =>
      ArmorMaterial.IRON

    case Material.IRON_AXE | Material.IRON_HOE |
         Material.IRON_PICKAXE | Material.IRON_SHOVEL |
         Material.IRON_SWORD =>
      ToolMaterial.IRON

    case _ => material.name match {
      case r"CHAINMAIL.*" => ArmorMaterial.CHAINMAIL
      case r"LEATHER.*"   => ArmorMaterial.LEATHER
      case r"STONE.*"     => ToolMaterial.STONE
      case r"WOODEN.*"    => ToolMaterial.WOOD

      case _ => throw new IllegalArgumentException(s"$material")
    }
  })

  def map(item: Item): Material = item match {
    case it: ColoredItem     => colorMapper.map(it)
    case it: VariedItem[_]   => variantMapper.map(it)
    case it: VariableItem[_] => variantMapper.map(it)

    case _: Barrel           => Material.BARREL
    case _: Barrier          => Material.BARRIER
    case _: Beacon           => Material.BEACON
    case _: Bedrock          => Material.BEDROCK
    case _: Beetroot         => Material.BEETROOT
    case _: Bell             => Material.BELL
    case _: BlastFurnace     => Material.BLAST_FURNACE
    case _: Bone             => Material.BONE_BLOCK
    case _: Bookshelf        => Material.BOOKSHELF
    case _: BrewingStand     => Material.BREWING_STAND
    case _: Brick            => Material.BRICK
    case _: Cactus           => Material.CACTUS
    case _: Cake             => Material.CAKE
    case _: Campfire         => Material.CAMPFIRE
    case _: Carrot           => Material.CARROT
    case _: CartographyTable => Material.CARTOGRAPHY_TABLE
    case _: Cauldron         => Material.CAULDRON
    case _: ChorusFlower     => Material.CHORUS_FLOWER
    case _: ChorusPlant      => Material.CHORUS_PLANT
    case _: Clay             => Material.CLAY
    case _: Coal             => Material.COAL_BLOCK
    case _: CoalOre          => Material.COAL_ORE
    case _: Cobweb           => Material.COBWEB
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
    case _: EndPortalFrame   => Material.END_PORTAL_FRAME
    case _: EndRod           => Material.END_ROD
    case _: Farmland         => Material.FARMLAND
    case _: FletchingTable   => Material.FLETCHING_TABLE
    case _: Furnace          => Material.FURNACE
    case _: Glowstone        => Material.GLOWSTONE
    case _: GoldBlock        => Material.GOLD_BLOCK // TODO rename block
    case _: GoldOre          => Material.GOLD_ORE
    case _: GrassBlock       => Material.GRASS_BLOCK
    case _: GrassPath        => Material.GRASS_PATH
    case _: Gravel           => Material.GRAVEL
    case _: Grindstone       => Material.GRINDSTONE
    case _: HayBale          => Material.HAY_BLOCK
    case _: Hopper           => Material.HOPPER
    case _: IronBlock        => Material.IRON_BLOCK // TODO rename block
    case _: IronBars         => Material.IRON_BARS
    case _: IronOre          => Material.IRON_ORE
    case _: JigsawBlock      => Material.JIGSAW // TODO rename block
    case _: Jukebox          => Material.JUKEBOX
    case _: Kelp             => Material.KELP
    case _: Ladder           => Material.LADDER
    case _: Lantern          => Material.LANTERN
    case _: Lapis            => Material.LAPIS_BLOCK
    case _: LapisOre         => Material.LAPIS_ORE
    case _: Lectern          => Material.LECTERN
    case _: Lever            => Material.LEVER
    case _: LilyPad          => Material.LILY_PAD
    case _: Loom             => Material.LOOM
    case _: MagmaBlock       => Material.MAGMA_BLOCK // TODO rename block
    case _: Melon            => Material.MELON
    case _: Mycelium         => Material.MYCELIUM
    case _: Netherrack       => Material.NETHERRACK
    case _: NetherWart       => Material.NETHER_WART
    case _: NetherWartBlock  => Material.NETHER_WART_BLOCK
    case _: Observer         => Material.OBSERVER
    case _: Obsidian         => Material.OBSIDIAN
    case _: Podzol           => Material.PODZOL
    case _: Potato           => Material.POTATO
    case _: Prismarine       => Material.PRISMARINE
    case _: Pumpkin          => Material.PUMPKIN
    case _: PurpurBlock      => Material.PURPUR_BLOCK // TODO rename block
    case _: QuartzOre        => Material.NETHER_QUARTZ_ORE
    case _: Redstone         => Material.REDSTONE_BLOCK
    case _: RedstoneLamp     => Material.REDSTONE_LAMP
    case _: RedstoneOre      => Material.REDSTONE_ORE
    case _: RedstoneTorch    => Material.REDSTONE_TORCH
    case _: Repeater         => Material.REPEATER
    case _: Scaffolding      => Material.SCAFFOLDING // TODO rename block
    case _: Seagrass         => Material.SEAGRASS
    case _: SeaLantern       => Material.SEA_LANTERN
    case _: SeaPickle        => Material.SEA_PICKLE
    case _: SlimeBlock       => Material.SLIME_BLOCK // TODO rename block
    case _: SmithingTable    => Material.SMITHING_TABLE
    case _: Smoker           => Material.SMOKER
    case _: Snow             => Material.SNOW
    case _: SnowBlock        => Material.SNOW_BLOCK
    case _: Spawner          => Material.SPAWNER
    case _: Stonecutter      => Material.STONECUTTER // TODO rename block
    case _: SugarCane        => Material.SUGAR_CANE
    case _: SweetBerries     => Material.SWEET_BERRIES
    case _: TNT              => Material.TNT
    case _: Torch            => Material.TORCH
    case _: TurtleEgg        => Material.TURTLE_EGG
    case _: Vine             => Material.VINE
    case _: Wheat            => Material.WHEAT

    case it: Button        => blockMapper.mapButton(it.material)
    case it: Brick         => blockMapper.mapBrick(it.material)
    case it: Door          => blockMapper.mapDoor(it.material)
    case it: EndStone      => blockMapper.mapEndStone(it.material)
    case it: Fence         => blockMapper.mapFence(it.material)
    case it: Fern          => blockMapper.mapFern(it.tall)
    case it: Gate          => blockMapper.mapGate(it.material)
    case it: Grass         => blockMapper.mapGrass(it.tall)
    case it: Leaves        => blockMapper.mapLeaves(it.material)
    case it: Pillar        => blockMapper.mapPillar(it.material)
    case it: Piston        => blockMapper.mapPiston(it.sticky)
    case it: Planks        => blockMapper.mapPlanks(it.material)
    case it: PressurePlate => blockMapper.mapPressurePlate(it.material)
    case it: Sand          => blockMapper.mapSand(it.material)
    case it: Sandstone     => blockMapper.mapSandstone(it.material)
    case it: Sign          => blockMapper.mapSign(it.material)
    case it: Sponge        => blockMapper.mapSponge(it.wet)
    case it: Trapdoor      => blockMapper.mapTrapdoor(it.material)

    case it: Log =>
      if (it.stripped) blockMapper.mapStrippedLog(it.material)
      else blockMapper.mapLog(it.material)

    case it: Wood =>
      if (it.stripped) blockMapper.mapStrippedWood(it.material)
      else blockMapper.mapWood(it.material)
  }
}
