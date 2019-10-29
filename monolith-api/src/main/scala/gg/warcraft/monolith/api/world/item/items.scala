package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.material._
import gg.warcraft.monolith.api.world.block.variant._
import gg.warcraft.monolith.api.world.item.variant._

final case class Andesite(
    variant: StoniteVariant,
    data: ItemData
) extends VariedItem[StoniteVariant] {
  val `type` = ItemType.ANDESITE
}

final case class Anvil(
    variant: AnvilVariant,
    data: ItemData
) extends VariedItem[AnvilVariant] {
  val `type` = ItemType.ANVIL
}

final case class Apple(data: ItemData) extends Item {
  val `type` = ItemType.APPLE
  override val edible = true
}

final case class ArmorStand(data: ItemData) extends Item {
  val `type` = ItemType.ARMOR_STAND
  override val maxStackSize = 16
}

//final case class Arrow extends Item {
//  // TODO
//}
//
//final case class Axe extends Item {
//  // TODO
//}

final case class Bamboo(data: ItemData) extends Item {
  val `type` = ItemType.BAMBOO
}

final case class Banner(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.BANNER
  override val maxStackSize = 16
}

final case class BannerPattern(
    variant: BannerPatternVariant,
    data: ItemData
) extends Item {
  val `type` = ItemType.BANNER_PATTERN
  override val maxStackSize = 1
}

final case class Barrel(data: ItemData) extends Item {
  val `type` = ItemType.BARREL
}

final case class Barrier(data: ItemData) extends Item {
  val `type` = ItemType.BARRIER
}

final case class Beacon(data: ItemData) extends Item {
  val `type` = ItemType.BEACON
}

final case class Bed(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.BED
}

final case class Bedrock(data: ItemData) extends Item {
  val `type` = ItemType.BEDROCK
}

//final case class Beef extends Item {
//  // TODO cookable
//}
//
//final case class Beetroot extends Item {
//  // TODO edible?
//}

final case class Bell(data: ItemData) extends Item {
  val `type` = ItemType.BELL
}

final case class BlastFurnace(data: ItemData) extends Item {
  val `type` = ItemType.BLAST_FURNACE
}

final case class BlazePowder(data: ItemData) extends Item {
  val `type` = ItemType.BLAZE_POWDER
}

final case class BlazeRod(data: ItemData) extends Item {
  val `type` = ItemType.BLAZE_ROD
}

final case class Boat(
    material: WoodMaterial,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.BOAT
}

final case class Bone(data: ItemData) extends Item {
  val `type` = ItemType.BONE
}

final case class BoneBlock(data: ItemData) extends Item {
  val `type` = ItemType.BONE_BLOCK
}

final case class BoneMeal(data: ItemData) extends Item {
  val `type` = ItemType.BONE_MEAL
}

//final case class BottleOfEnchanting extends Item {
//  // TODO Bottle 'o Enchanting
//}
//
//final case class Book extends Item {
//  // TODO other book variants
//}

final case class Bookshelf(data: ItemData) extends Item {
  val `type` = ItemType.BOOKSHELF
}

//final case class Boots extends Item {
//  // TODO
//}

final case class Bow(data: ItemData) extends Item {
  val `type` = ItemType.BOW
  override val maxStackSize = 1
}

final case class Bowl(data: ItemData) extends Item {
  val `type` = ItemType.BOWL
}

final case class Bread(data: ItemData) extends Item {
  val `type` = ItemType.BREAD
}

final case class BrewingStand(data: ItemData) extends Item {
  val `type` = ItemType.BREWING_STAND
}

final case class Brick(
    material: BrickMaterial,
    data: ItemData
) extends MaterialItem[BrickMaterial] {
  val `type` = ItemType.BRICK
}

final case class BrickBlock(
    material: BrickMaterial,
    data: ItemData
) extends MaterialItem[BrickMaterial] {
  val `type` = ItemType.BRICK_BLOCK
}

final case class Bucket(
    variant: BucketVariant,
    data: ItemData
) extends VariedItem[BucketVariant] {
  val `type` = ItemType.BUCKET
  override val maxStackSize = 16
}

final case class Button(
    material: ButtonMaterial,
    data: ItemData
) extends MaterialItem[ButtonMaterial] {
  val `type` = ItemType.BUTTON
}

final case class Cactus(data: ItemData) extends Item {
  val `type` = ItemType.CACTUS
}

final case class Cake(data: ItemData) extends Item {
  val `type` = ItemType.CAKE
  override val maxStackSize = 1
}

final case class Campfire(data: ItemData) extends Item {
  val `type` = ItemType.CAMPFIRE
}

final case class Carpet(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.CARPET
}

final case class Carrot(data: ItemData) extends Item {
  val `type` = ItemType.CARROT
}

final case class CarrotOnAStick(data: ItemData) extends Item {
  val `type` = ItemType.CARROT_ON_A_STICK
  override val maxStackSize = 1
}

final case class CartographyTable(data: ItemData) extends Item {
  val `type` = ItemType.CARTOGRAPHY_TABLE
}

final case class Charcoal(data: ItemData) extends Item {
  val `type` = ItemType.CHARCOAL
}

final case class Cauldron(data: ItemData) extends Item {
  val `type` = ItemType.CAULDRON
}

final case class Chest(
    variant: ChestVariant,
    data: ItemData
) extends Item {
  val `type` = ItemType.CHEST
}

//final case class ChestPlate extends Item {
//  // TODO
//}
//
//final case class Chicken extends Item {
//  // TODO cookable
//}

final case class ChorusFlower(data: ItemData) extends Item {
  val `type` = ItemType.CHORUS_FLOWER
}

final case class ChorusFruit(
    popped: Boolean,
    data: ItemData
) extends Item {
  val `type` = ItemType.CHORUS_FRUIT
  override val edible: Boolean = !popped
  def withPopped(popped: Boolean): ChorusFruit =
    copyWith("popped", popped)
}

final case class ChorusPlant(data: ItemData) extends Item {
  val `type` = ItemType.CHORUS_PLANT
}

final case class Clay(data: ItemData) extends Item {
  val `type` = ItemType.CLAY
}

final case class ClayBlock(data: ItemData) extends Item {
  val `type` = ItemType.CLAY_BLOCK
}

final case class Clock(data: ItemData) extends Item {
  val `type` = ItemType.CLOCK
}

final case class Coal(data: ItemData) extends Item {
  val `type` = ItemType.COAL
}

final case class CoalBlock(data: ItemData) extends Item {
  val `type` = ItemType.COAL_BLOCK
}

final case class CoalOre(data: ItemData) extends Item {
  val `type` = ItemType.COAL_ORE
}

final case class Cobblestone(
    variant: CobblestoneVariant,
    data: ItemData
) extends VariedItem[CobblestoneVariant] {
  val `type` = ItemType.COBBLESTONE
}

final case class Cobweb(data: ItemData) extends Item {
  val `type` = ItemType.COBWEB
}

final case class CocoaBeans(data: ItemData) extends Item {
  val `type` = ItemType.COCOA_BEANS
}

//final case class Cod extends Item {
//  // TODO cookable
//}

final case class CommandBlock(
    variant: CommandBlockVariant,
    data: ItemData
) extends VariedItem[CommandBlockVariant] {
  val `type` = ItemType.COMMAND_BLOCK
}

final case class Comparator(data: ItemData) extends Item {
  val `type` = ItemType.COMPARATOR
}

final case class Compass(data: ItemData) extends Item {
  val `type` = ItemType.COMPASS
}

final case class Composter(data: ItemData) extends Item {
  val `type` = ItemType.COMPOSTER
}

final case class Concrete(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.CONCRETE
}

final case class ConcretePowder(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.CONCRETE_POWDER
}

final case class Conduit(data: ItemData) extends Item {
  val `type` = ItemType.CONDUIT
}

final case class Cookie(data: ItemData) extends Item {
  val `type` = ItemType.COOKIE
}

final case class Coral(
    variant: CoralVariant,
    data: ItemData
) extends VariedItem[CoralVariant] {
  val `type` = ItemType.CORAL
}

final case class CoralBlock(
    variant: CoralVariant,
    data: ItemData
) extends VariedItem[CoralVariant] {
  val `type` = ItemType.CORAL_BLOCK
}

final case class CoralFan(
    variant: CoralVariant,
    data: ItemData
) extends VariedItem[CoralVariant] {
  val `type` = ItemType.CORAL_FAN
}

final case class CraftingTable(data: ItemData) extends Item {
  val `type` = ItemType.CRAFTING_TABLE
}

final case class Crossbow(data: ItemData) extends Item {
  val `type` = ItemType.CROSSBOW
  override val maxStackSize = 1
}

final case class DaylightDetector(data: ItemData) extends Item {
  val `type` = ItemType.DAYLIGHT_DETECTOR
}

final case class DeadBush(data: ItemData) extends Item {
  val `type` = ItemType.DEAD_BUSH
}

final case class DebugStick(data: ItemData) extends Item {
  val `type` = ItemType.DEBUG_STICK
  override val maxStackSize = 1
}

final case class Diamond(data: ItemData) extends Item {
  val `type` = ItemType.DIAMOND
}

final case class DiamondBlock(data: ItemData) extends Item {
  val `type` = ItemType.DIAMOND_BLOCK
}

final case class DiamondOre(data: ItemData) extends Item {
  val `type` = ItemType.DIAMOND_ORE
}

final case class Diorite(
    variant: StoniteVariant,
    data: ItemData
) extends VariedItem[StoniteVariant] {
  val `type` = ItemType.DIORITE
}

final case class Dirt(
    coarse: Boolean,
    data: ItemData
) extends Item {
  val `type` = ItemType.DIRT
  def this(coarse: Boolean) =
    this(coarse, ItemData(coarse, "Coarse", "Dirt"))
  // TODO withCoarse
}

final case class Dispenser(data: ItemData) extends Item {
  val `type` = ItemType.DISPENSER
}

final case class Door(
    material: DoorMaterial,
    data: ItemData
) extends MaterialItem[DoorMaterial] {
  val `type` = ItemType.DOOR
}

final case class DragonBreath(data: ItemData) extends Item {
  val `type` = ItemType.DRAGON_BREATH
}

final case class DragonEgg(data: ItemData) extends Item {
  val `type` = ItemType.DRAGON_EGG
}

final case class DriedKelp(data: ItemData) extends Item {
  val `type` = ItemType.DRIED_KELP
}

final case class DriedKelpBlock(data: ItemData) extends Item {
  val `type` = ItemType.DRIED_KELP_BLOCK
}

final case class Dropper(data: ItemData) extends Item {
  val `type` = ItemType.DROPPER
}

final case class Dye(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.DYE
}

final case class Egg(data: ItemData) extends Item {
  val `type` = ItemType.EGG
}

final case class Elytra(data: ItemData) extends Item {
  val `type` = ItemType.ELYTRA
  override val maxStackSize = 1
}

final case class Emerald(data: ItemData) extends Item {
  val `type` = ItemType.EMERALD
}

final case class EmeraldBlock(data: ItemData) extends Item {
  val `type` = ItemType.EMERALD_BLOCK
}

final case class EmeraldOre(data: ItemData) extends Item {
  val `type` = ItemType.EMERALD_ORE
}

final case class EnchantingTable(data: ItemData) extends Item {
  val `type` = ItemType.ENCHANTING_TABLE
}

final case class EndCrystal(data: ItemData) extends Item {
  val `type` = ItemType.END_CRYSTAL
}

final case class EndPortalFrame(data: ItemData) extends Item {
  val `type` = ItemType.END_PORTAL_FRAME
}

final case class EndRod(data: ItemData) extends Item {
  val `type` = ItemType.END_ROD
}

final case class EndStone(
    material: EndStoneMaterial,
    data: ItemData
) extends MaterialItem[EndStoneMaterial] {
  val `type` = ItemType.END_STONE // TODO rename EndStoneMaterial.END_STONE > NORMAL?
}

final case class EnderEye(data: ItemData) extends Item {
  val `type` = ItemType.ENDER_EYE
}

final case class EnderPearl(data: ItemData) extends Item {
  val `type` = ItemType.ENDER_PEARL
  override val maxStackSize = 16
}

final case class Farmland(data: ItemData) extends Item {
  val `type` = ItemType.FARMLAND
}

final case class Feather(data: ItemData) extends Item {
  val `type` = ItemType.FEATHER
}

final case class Fence(
    material: FenceMaterial,
    data: ItemData
) extends MaterialItem[FenceMaterial] {
  val `type` = ItemType.FENCE
}

//final case class FireCharge extends Item {
//  // TODO
//}
//
//final case class FireworkRocket extends Item {
//  // TODO
//}
//
//final case class FireworkStar extends Item {
//  // TODO
//}

final case class FishingRod(data: ItemData) extends Item {
  val `type` = ItemType.FISHING_ROD
  override val maxStackSize = 1
}

final case class FletchingTable(data: ItemData) extends Item {
  val `type` = ItemType.FLETCHING_TABLE
}

final case class Flint(data: ItemData) extends Item {
  val `type` = ItemType.FLINT
}

final case class FlintAndSteel(data: ItemData) extends Item {
  val `type` = ItemType.FLINT_AND_STEEL
  override val maxStackSize = 1
}

//final case class Flower extends Item {
//  // TODO
//}

final case class FlowerPot(data: ItemData) extends Item {
  val `type` = ItemType.FLOWER_POT
}

final case class Furnace(data: ItemData) extends Item {
  val `type` = ItemType.FURNACE
}

final case class Gate(
    material: WoodMaterial,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.GATE
}

final case class GhastTear(data: ItemData) extends Item {
  val `type` = ItemType.GHAST_TEAR
}

final case class Glass(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.GLASS
}

final case class GlassBottle(data: ItemData) extends Item {
  val `type` = ItemType.GLASS_BOTTLE
}

final case class GlassPane(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.GLASS_PANE
}

final case class GlazedTerracotta(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.GLAZED_TERRACOTTA
}

final case class Glowstone(data: ItemData) extends Item {
  val `type` = ItemType.GLOWSTONE
}

final case class GlowstoneDust(data: ItemData) extends Item {
  val `type` = ItemType.GLOWSTONE_DUST
}

final case class GoldBlock(data: ItemData) extends Item {
  val `type` = ItemType.GOLD_BLOCK
}

final case class GoldIngot(data: ItemData) extends Item {
  val `type` = ItemType.GOLD_INGOT
}

final case class GoldNugget(data: ItemData) extends Item {
  val `type` = ItemType.GOLD_NUGGET
}

final case class GoldOre(data: ItemData) extends Item {
  val `type` = ItemType.GOLD_ORE
}

final case class GoldenApple(
    enchanted: Boolean,
    data: ItemData
) extends Item {
  val `type` = ItemType.GOLDEN_APPLE
  override val edible = true
}

final case class GoldenCarrot(data: ItemData) extends Item {
  val `type` = ItemType.GOLDEN_CARROT
  override val edible = true
}

final case class GoldenMelonSlice(data: ItemData) extends Item {
  val `type` = ItemType.GOLDEN_MELON_SLICE
}

final case class Granite(
    variant: StoniteVariant,
    data: ItemData
) extends VariedItem[StoniteVariant] {
  val `type` = ItemType.GRANITE
}

final case class Grass(data: ItemData) extends Item {
  val `type` = ItemType.GRASS
}

final case class GrassBlock(data: ItemData) extends Item {
  val `type` = ItemType.GRASS_BLOCK
}

final case class GrassPath(data: ItemData) extends Item {
  val `type` = ItemType.GRASS_PATH
}

final case class Gravel(data: ItemData) extends Item {
  val `type` = ItemType.GRAVEL
}

final case class Grindstone(data: ItemData) extends Item {
  val `type` = ItemType.GRINDSTONE
}

final case class Gunpowder(data: ItemData) extends Item {
  val `type` = ItemType.GUNPOWDER
}

final case class HayBale(data: ItemData) extends Item {
  val `type` = ItemType.HAY_BALE
}

final case class HeartOfTheSea(data: ItemData) extends Item {
  val `type` = ItemType.HEART_OF_THE_SEA
}

//final case class Helmet extends Item {
//  // TODO
//}
//
//final case class Hoe extends Item {
//  // TODO
//}

final case class Hopper(data: ItemData) extends Item {
  val `type` = ItemType.HOPPER
}

//final case class HorseArmor extends Item {
//  // TODO
//}

final case class Ice(
    variant: IceVariant,
    data: ItemData
) extends VariedItem[IceVariant] {
  val `type` = ItemType.ICE
}

final case class InfestedBlock(
    material: InfestedMaterial,
    variant: Option[InfestedVariant],
    data: ItemData
) extends MaterialItem[InfestedMaterial] {
  val `type` = ItemType.INFESTED_BLOCK
}

final case class InkSac(data: ItemData) extends Item {
  val `type` = ItemType.INK_SAC
}

final case class IronBars(data: ItemData) extends Item {
  val `type` = ItemType.IRON_BARS
}

final case class IronBlock(data: ItemData) extends Item {
  val `type` = ItemType.IRON_BLOCK
}

final case class IronIngot(data: ItemData) extends Item {
  val `type` = ItemType.IRON_INGOT
}

final case class IronNugget(data: ItemData) extends Item {
  val `type` = ItemType.IRON_NUGGET
}

final case class IronOre(data: ItemData) extends Item {
  val `type` = ItemType.IRON_ORE
}

final case class ItemFrame(data: ItemData) extends Item {
  val `type` = ItemType.ITEM_FRAME
}

final case class JackOfTheLantern(data: ItemData) extends Item {
  val `type` = ItemType.JACK_OF_THE_LANTERN
}

final case class JigsawBlock(data: ItemData) extends Item {
  val `type` = ItemType.JIGSAW_BLOCK
}

final case class Jukebox(data: ItemData) extends Item {
  val `type` = ItemType.JUKEBOX
}

final case class Kelp(data: ItemData) extends Item {
  val `type` = ItemType.KELP
}

final case class Ladder(data: ItemData) extends Item {
  val `type` = ItemType.LADDER
}

final case class Lantern(data: ItemData) extends Item {
  val `type` = ItemType.LANTERN
}

final case class Lapis(data: ItemData) extends Item {
  val `type` = ItemType.LAPIS
}

final case class LapisBlock(data: ItemData) extends Item {
  val `type` = ItemType.LAPIS_BLOCK
}

final case class LapisOre(data: ItemData) extends Item {
  val `type` = ItemType.LAPIS_ORE
}

final case class Lead(data: ItemData) extends Item {
  val `type` = ItemType.LEAD
}

final case class Leather(data: ItemData) extends Item {
  val `type` = ItemType.LEATHER
}

final case class Leaves(
    material: WoodMaterial,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.LEAVES
}

final case class Lectern(data: ItemData) extends Item {
  val `type` = ItemType.LECTERN
}

//final case class Leggings extends Item {
//  // TODO
//}

final case class Lever(data: ItemData) extends Item {
  val `type` = ItemType.LEVER
}

final case class LilyPad(data: ItemData) extends Item {
  val `type` = ItemType.LILY_PAD
}

final case class Log(
    material: WoodMaterial,
    stripped: Boolean,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.LOG
}

final case class Loom(data: ItemData) extends Item {
  val `type` = ItemType.LOOM
}

final case class MagmaBlock(data: ItemData) extends Item {
  val `type` = ItemType.MAGMA_BLOCK
}

final case class MagmaCream(data: ItemData) extends Item {
  val `type` = ItemType.MAGMA_CREAM
}

//final case class Map extends Item {
//  // TODO filled variant
//}
//
//final case class Melon extends Item {
//  // TODO
//}
//
//final case class MelonSlice extends Item {
//  // TODO edible
//}

final case class Minecart(
    variant: MinecartVariant,
    data: ItemData
) extends VariedItem[MinecartVariant] {
  val `type` = ItemType.MINECART
}

final case class MobHead(
    variant: MobHeadVariant,
    data: ItemData
) extends Item {
  val `type` = ItemType.MOB_HEAD
}

final case class Mushroom(
    variant: MushroomVariant,
    data: ItemData
) extends VariedItem[MushroomVariant] {
  val `type` = ItemType.MUSHROOM
}

final case class MushroomBlock(
    variant: MushroomBlockVariant,
    data: ItemData
) extends VariedItem[MushroomBlockVariant] {
  val `type` = ItemType.MUSHROOM_BLOCK
}

final case class MusicDisc(
    variant: MusicDiscVariant,
    data: ItemData
) extends VariedItem[MusicDiscVariant] {
  val `type` = ItemType.MUSIC_DISC
}

//final case class Mutton extends Item {
//  // TODO cookable
//}
//
//final case class Mycelium extends Item {
//  // TODO
//}

final case class NameTag(data: ItemData) extends Item {
  val `type` = ItemType.NAME_TAG
}

final case class NautilusShell(data: ItemData) extends Item {
  val `type` = ItemType.NAUTILUS_SHELL
}

final case class Netherrack(data: ItemData) extends Item {
  val `type` = ItemType.NETHERRACK
}

final case class NetherStar(data: ItemData) extends Item {
  val `type` = ItemType.NETHER_STAR
}

//final case class NetherWart extends Item {
//  // TODO
//}
//
//final case class NetherWartBlock extends Item {
//  // TODO
//}

final case class NoteBlock(data: ItemData) extends Item {
  val `type` = ItemType.NOTE_BLOCK
}

final case class Observer(data: ItemData) extends Item {
  val `type` = ItemType.OBSERVER
}

final case class Obsidian(data: ItemData) extends Item {
  val `type` = ItemType.OBSIDIAN
}

final case class Painting(data: ItemData) extends Item {
  val `type` = ItemType.PAINTING
}

final case class Paper(data: ItemData) extends Item {
  val `type` = ItemType.PAPER
}

final case class PhantomMembrane(data: ItemData) extends Item {
  val `type` = ItemType.PHANTOM_MEMBRANE
}

//final case class Pickaxe extends Item {
//  // TODO
//}

final case class Pillar(
    material: PillarMaterial,
    data: ItemData
) extends MaterialItem[PillarMaterial] {
  val `type` = ItemType.PILLAR
}

final case class Piston(
    sticky: Boolean,
    data: ItemData
) extends Item {
  val `type` = ItemType.PISTON
  def withSticky(sticky: Boolean): Piston =
    copyWith("sticky", sticky)
}

final case class Planks(
    material: WoodMaterial,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.PLANKS
}

//final case class Podzol extends Item {
//  // TODO
//}

final case class PoisonousPotato(data: ItemData) extends Item {
  val `type` = ItemType.POISONOUS_POTATO
}

//final case class Porkchop extends Item {
//  // TODO cookable
//}
//
//final case class Potato extends Item {
//  // TODO cookable
//}
//
//final case class Potion extends Item {
//  // TODO variants
//}

final case class PressurePlate(
    material: PressurePlateMaterial,
    data: ItemData
) extends MaterialItem[PressurePlateMaterial] {
  val `type` = ItemType.PRESSURE_PLATE
}

final case class Prismarine(
    material: PrismarineMaterial,
    data: ItemData
) extends MaterialItem[PrismarineMaterial] {
  val `type` = ItemType.PRISMARINE
}

final case class PrismarineCrystals(data: ItemData) extends Item {
  val `type` = ItemType.PRISMARINE_CRYSTALS
}

final case class PrismarineShard(data: ItemData) extends Item {
  val `type` = ItemType.PRISMARINE_SHARD
}

final case class Pufferfish(data: ItemData) extends Item {
  val `type` = ItemType.PUFFERFISH
  override val edible = true
}

//final case class Pumpkin extends Item {
//  // TODO
//}
//
//final case class PumpkinPie extends Item {
//  // TODO edible
//}

final case class Purpur(data: ItemData) extends Item {
  val `type` = ItemType.PURPUR
}

final case class Quartz(data: ItemData) extends Item {
  val `type` = ItemType.QUARTZ
}

final case class QuartzBlock(
    variant: QuartzVariant,
    data: ItemData
) extends VariedItem[QuartzVariant] {
  val `type` = ItemType.QUARTZ_BLOCK
}

final case class QuartzOre(data: ItemData) extends Item {
  val `type` = ItemType.QUARTZ_ORE
}

//final case class Rabbit extends Item {
//  // TODO cookable
//}

final case class RabbitFoot(data: ItemData) extends Item {
  val `type` = ItemType.RABBIT_FOOT
}

final case class RabbitHide(data: ItemData) extends Item {
  val `type` = ItemType.RABBIT_HIDE
}

final case class Rails(
    variant: RailsVariant,
    data: ItemData
) extends VariedItem[RailsVariant] {
  val `type` = ItemType.RAILS
}

final case class Repeater(data: ItemData) extends Item {
  val `type` = ItemType.REPEATER
}

final case class Redstone(data: ItemData) extends Item {
  val `type` = ItemType.REDSTONE
}

final case class RedstoneBlock(data: ItemData) extends Item {
  val `type` = ItemType.REDSTONE_BLOCK
}

final case class RedstoneLamp(data: ItemData) extends Item {
  val `type` = ItemType.REDSTONE_LAMP
}

final case class RedstoneOre(data: ItemData) extends Item {
  val `type` = ItemType.REDSTONE_ORE
}

final case class RedstoneTorch(data: ItemData) extends Item {
  val `type` = ItemType.REDSTONE_TORCH
}

final case class RottenFlesh(data: ItemData) extends Item {
  val `type` = ItemType.ROTTEN_FLESH
}

final case class Saddle(data: ItemData) extends Item {
  val `type` = ItemType.SADDLE
  override val maxStackSize = 1
}

//final case class Salmon extends Item {
//  // TODO cookable
//}

final case class Sand(
    material: SandMaterial, // TODO should this be a variant?

    data: ItemData
) extends MaterialItem[SandMaterial] {
  val `type` = ItemType.SAND
}

final case class Sandstone(
    material: SandstoneMaterial,
    variant: SandstoneVariant,
    data: ItemData
) extends MaterialItem[SandstoneMaterial]
  with VariedItem[SandstoneVariant] {
  val `type` = ItemType.SANDSTONE
}

final case class Sapling(
    variant: SaplingVariant, // TODO would kinda like this to be material again
    data: ItemData
) extends VariedItem[SaplingVariant] {
  val `type` = ItemType.SAPLING
}

final case class Scaffolding(data: ItemData) extends Item {
  val `type` = ItemType.SCAFFOLDING
}

final case class Scute(data: ItemData) extends Item {
  val `type` = ItemType.SCUTE
}

//final case class Seagrass extends Item {
//  // TODO
//}

final case class SeaLantern(data: ItemData) extends Item {
  val `type` = ItemType.SEA_LANTERN
}

final case class SeaPickle(data: ItemData) extends Item {
  val `type` = ItemType.SEA_PICKLE
}

final case class Shears(data: ItemData) extends Item {
  val `type` = ItemType.SHEARS
  override val maxStackSize = 1
}

final case class Shield(data: ItemData) extends Item {
  val `type` = ItemType.SHIELD
  override val maxStackSize = 1
}

//final case class Shovel extends Item {
//  // TODO
//}

final case class ShulkerBox(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.SHULKER_BOX
}

final case class ShulkerShell(data: ItemData) extends Item {
  val `type` = ItemType.SHULKER_SHELL
}

final case class Sign(
    material: WoodMaterial,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.SIGN
  override val maxStackSize = 16
}

final case class Slab(
    material: SlabMaterial,
    variant: Option[SlabVariant],
    data: ItemData
) extends MaterialItem[SlabMaterial]
  with VariableItem[SlabVariant] {
  val `type` = ItemType.SLAB
}

final case class Slimeball(data: ItemData) extends Item {
  val `type` = ItemType.SLIMEBALL
}

final case class SlimeBlock(data: ItemData) extends Item {
  val `type` = ItemType.SLIME_BLOCK
}

final case class SmithingTable(data: ItemData) extends Item {
  val `type` = ItemType.SMITHING_TABLE
}

final case class Smoker(data: ItemData) extends Item {
  val `type` = ItemType.SMOKER
}

final case class Snow(data: ItemData) extends Item {
  val `type` = ItemType.SNOW
}

final case class Snowball(data: ItemData) extends Item {
  val `type` = ItemType.SNOWBALL
  override val maxStackSize = 16
}

final case class SnowBlock(data: ItemData) extends Item {
  val `type` = ItemType.SNOW_BLOCK
}

final case class SoulSand(data: ItemData) extends Item {
  val `type` = ItemType.SOUL_SAND
}

final case class SpawnEgg(
    variant: SpawnEggVariant,
    data: ItemData
) extends VariedItem[SpawnEggVariant] {
  val `type` = ItemType.SPAWN_EGG // TODO MonsterEgg?
}

final case class Spawner(data: ItemData) extends Item {
  val `type` = ItemType.SPAWNER
}

final case class SpiderEye(
    fermented: Boolean,
    data: ItemData
) extends Item {
  val `type` = ItemType.SPIDER_EYE
}

final case class Sponge(
    wet: Boolean,
    data: ItemData
) extends Item {
  val `type` = ItemType.SPONGE
}

final case class Stairs(
    material: StairsMaterial,
    variant: Option[StairsVariant],
    data: ItemData
) extends MaterialItem[StairsMaterial]
  with VariableItem[StairsVariant] {
  val `type` = ItemType.SLAB
}

final case class Stick(data: ItemData) extends Item {
  val `type` = ItemType.STICK
}

final case class Stone(
    material: StoneMaterial,
    variant: StoneVariant,
    data: ItemData
) extends MaterialItem[StoneMaterial]
  with VariedItem[StoneVariant] {
  val `type` = ItemType.STONE
}

final case class Stonecutter(data: ItemData) extends Item {
  val `type` = ItemType.STONECUTTER
}

final case class PieceOfString(data: ItemData) extends Item {
  val `type` = ItemType.STRING
}

final case class Sugar(data: ItemData) extends Item {
  val `type` = ItemType.SUGAR
}

final case class SugarCane(data: ItemData) extends Item {
  val `type` = ItemType.SUGAR_CANE
}

final case class SweetBerries(data: ItemData) extends Item {
  val `type` = ItemType.SWEET_BERRIES
  override val edible = true
}

//final case class Sword extends Item {
//  // TODO
//}

final case class Terracotta(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.TERRACOTTA
}

final case class TNT(data: ItemData) extends Item {
  val `type` = ItemType.TNT
}

final case class Torch(data: ItemData) extends Item {
  val `type` = ItemType.TORCH
}

final case class TotemOfUndying(data: ItemData) extends Item {
  val `type` = ItemType.TOTEM_OF_UNDYING
  override val maxStackSize = 1
}

final case class TrapDoor(
    material: TrapdoorMaterial,
    data: ItemData
) extends MaterialItem[TrapdoorMaterial] {
  val `type` = ItemType.TRAPDOOR
}

final case class Trident(data: ItemData) extends Item {
  val `type` = ItemType.TRIDENT
  override val maxStackSize = 1
}

final case class TripwireHook(data: ItemData) extends Item {
  val `type` = ItemType.TRIPWIRE_HOOK
}

final case class TropicalFish(data: ItemData) extends Item {
  val `type` = ItemType.TROPICAL_FISH
  override val edible = true
}

final case class TurtleEgg(data: ItemData) extends Item {
  val `type` = ItemType.TURTLE_EGG
}

final case class TurtleHelmet(data: ItemData) extends Item {
  val `type` = ItemType.TURTLE_HELMET
  override val maxStackSize = 1
}

final case class Vine(data: ItemData) extends Item {
  val `type` = ItemType.VINE
}

final case class Wall(
    material: WallMaterial,
    variant: Option[WallVariant],
    data: ItemData
) extends MaterialItem[WallMaterial]
  with VariableItem[WallVariant] {
  val `type` = ItemType.WALL
}

final case class WeightedPressurePlate(
    variant: WeightedPressurePlateVariant,
    data: ItemData
) extends VariedItem[WeightedPressurePlateVariant] {
  val `type` = ItemType.WEIGHTED_PRESSURE_PLATE
}

//final case class Wheat extends Item {
//  // TODO
//}

final case class Wood(
    material: WoodMaterial,
    stripped: Boolean,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.WOOD
}

final case class Wool(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.WOOL
}
