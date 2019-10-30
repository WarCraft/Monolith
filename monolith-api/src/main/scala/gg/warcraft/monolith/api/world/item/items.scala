package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.material._
import gg.warcraft.monolith.api.world.block.variant._
import gg.warcraft.monolith.api.world.item.material.{ ArmorMaterial, ToolMaterial }
import gg.warcraft.monolith.api.world.item.variant._

final case class Andesite(
    variant: StoniteVariant,
    name: String,
    tooltip: Array[String],
    attributes: List[String],
    count: Int
) extends VariedItem[StoniteVariant] with StackableItem {
  val `type` = ItemType.ANDESITE
}

final case class Anvil(
    variant: AnvilVariant,

) extends VariedItem[AnvilVariant] with StackableItem {
  val `type` = ItemType.ANVIL
}

final case class Apple() extends StackableItem {
  val `type` = ItemType.APPLE
  override val edible = true
}

final case class ArmorStand() extends StackableItem {
  val `type` = ItemType.ARMOR_STAND
  override val maxCount = 16
}

final case class Arrow(
    variant: ArrowVariant,

) extends StackableItem {
  val `type` = ItemType.ARROW
}

final case class Axe(
    material: ToolMaterial,

) extends StackableItem {
  val `type` = ItemType.AXE
}

final case class Bamboo() extends StackableItem {
  val `type` = ItemType.BAMBOO
}

final case class Banner(
    color: BlockColor,

) extends ColoredItem with StackableItem {
  val `type` = ItemType.BANNER
  override val maxCount = 16
}

final case class BannerPattern(
    variant: BannerPatternVariant,

) extends Item {
  val `type` = ItemType.BANNER_PATTERN
}

final case class Barrel() extends StackableItem {
  val `type` = ItemType.BARREL
}

final case class Barrier() extends StackableItem {
  val `type` = ItemType.BARRIER
}

final case class Beacon() extends StackableItem {
  val `type` = ItemType.BEACON
}

final case class Bed(
    color: BlockColor,

) extends ColoredItem with StackableItem {
  val `type` = ItemType.BED
}

final case class Bedrock() extends StackableItem {
  val `type` = ItemType.BEDROCK
}

final case class Beef(
    cooked: Boolean,

) extends StackableItem {
  val `type` = ItemType.BEEF
  override val edible = true
}

final case class Beetroot() extends StackableItem {
  val `type` = ItemType.BEETROOT
  override val edible = true
}

final case class Bell() extends StackableItem {
  val `type` = ItemType.BELL
}

final case class BlastFurnace() extends StackableItem {
  val `type` = ItemType.BLAST_FURNACE
}

final case class BlazePowder() extends StackableItem {
  val `type` = ItemType.BLAZE_POWDER
}

final case class BlazeRod() extends StackableItem {
  val `type` = ItemType.BLAZE_ROD
}

final case class Boat(
    material: WoodMaterial,

) extends MaterialItem[WoodMaterial] with StackableItem {
  val `type` = ItemType.BOAT
}

final case class Bone() extends StackableItem {
  val `type` = ItemType.BONE
}

final case class BoneBlock() extends StackableItem {
  val `type` = ItemType.BONE_BLOCK
}

final case class BoneMeal() extends StackableItem {
  val `type` = ItemType.BONE_MEAL
}

final case class BottleOfEnchanting() extends StackableItem {
  val `type` = ItemType.BOTTLE_OF_ENCHANTING
}

//final case class Book extends Item {
//  // TODO other book variants
//}

final case class Bookshelf() extends StackableItem {
  val `type` = ItemType.BOOKSHELF
}

final case class Boots(
    material: ArmorMaterial,

) extends StackableItem {
  val `type` = ItemType.BOOTS
}

final case class Bow() extends Item {
  val `type` = ItemType.BOW
  override val maxDurability = 384
}

final case class Bowl() extends StackableItem {
  val `type` = ItemType.BOWL
}

final case class Bread() extends StackableItem {
  val `type` = ItemType.BREAD
  override val edible = true
}

final case class BrewingStand() extends StackableItem {
  val `type` = ItemType.BREWING_STAND
}

final case class Brick(
    material: BrickMaterial,

) extends MaterialItem[BrickMaterial] with StackableItem {
  val `type` = ItemType.BRICK
}

final case class BrickBlock(
    material: BrickMaterial,

) extends MaterialItem[BrickMaterial] with StackableItem {
  val `type` = ItemType.BRICK_BLOCK
}

final case class Bucket(
    variant: BucketVariant,

) extends VariedItem[BucketVariant] with StackableItem {
  val `type` = ItemType.BUCKET
  override val maxCount = 16
}

final case class Button(
    material: ButtonMaterial,

) extends MaterialItem[ButtonMaterial] with StackableItem {
  val `type` = ItemType.BUTTON
}

final case class Cactus() extends StackableItem {
  val `type` = ItemType.CACTUS
}

final case class Cake() extends Item {
  val `type` = ItemType.CAKE
}

final case class Campfire() extends StackableItem {
  val `type` = ItemType.CAMPFIRE
}

final case class Carpet(
    color: BlockColor,

) extends ColoredItem with StackableItem {
  val `type` = ItemType.CARPET
}

final case class Carrot() extends StackableItem {
  val `type` = ItemType.CARROT
  override val edible = true
}

final case class CarrotOnAStick() extends Item {
  val `type` = ItemType.CARROT_ON_A_STICK
  override val maxDurability = 25
}

final case class CartographyTable() extends StackableItem {
  val `type` = ItemType.CARTOGRAPHY_TABLE
}

final case class Charcoal() extends StackableItem {
  val `type` = ItemType.CHARCOAL
}

final case class Cauldron() extends StackableItem {
  val `type` = ItemType.CAULDRON
}

final case class Chest(
    variant: ChestVariant,

) extends StackableItem {
  val `type` = ItemType.CHEST
}

final case class Chestplate(
    material: ArmorMaterial,

) extends StackableItem {
  val `type` = ItemType.CHESTPLATE
}

final case class Chicken(
    cooked: Boolean,

) extends StackableItem {
  val `type` = ItemType.CHICKEN
  override val edible = true
}

final case class ChorusFlower() extends StackableItem {
  val `type` = ItemType.CHORUS_FLOWER
}

final case class ChorusFruit(
    popped: Boolean,

) extends StackableItem {
  val `type` = ItemType.CHORUS_FRUIT
  override val edible: Boolean = !popped
  def withPopped(popped: Boolean): ChorusFruit =
    copyWith("popped", popped)
}

final case class ChorusPlant() extends StackableItem {
  val `type` = ItemType.CHORUS_PLANT
}

final case class Clay() extends StackableItem {
  val `type` = ItemType.CLAY
}

final case class ClayBlock() extends StackableItem {
  val `type` = ItemType.CLAY_BLOCK
}

final case class Clock() extends StackableItem {
  val `type` = ItemType.CLOCK
}

final case class Coal() extends StackableItem {
  val `type` = ItemType.COAL
}

final case class CoalBlock() extends StackableItem {
  val `type` = ItemType.COAL_BLOCK
}

final case class CoalOre() extends StackableItem {
  val `type` = ItemType.COAL_ORE
}

final case class Cobblestone(
    variant: CobblestoneVariant,

) extends VariedItem[CobblestoneVariant] with StackableItem {
  val `type` = ItemType.COBBLESTONE
}

final case class Cobweb() extends StackableItem {
  val `type` = ItemType.COBWEB
}

final case class CocoaBeans() extends StackableItem {
  val `type` = ItemType.COCOA_BEANS
}

final case class Cod(
    cooked: Boolean,

) extends StackableItem {
  val `type` = ItemType.CHICKEN
  override val edible = true
}

final case class CommandBlock(
    variant: CommandBlockVariant,

) extends VariedItem[CommandBlockVariant] with StackableItem {
  val `type` = ItemType.COMMAND_BLOCK
}

final case class Comparator() extends StackableItem {
  val `type` = ItemType.COMPARATOR
}

final case class Compass() extends StackableItem {
  val `type` = ItemType.COMPASS
}

final case class Composter() extends StackableItem {
  val `type` = ItemType.COMPOSTER
}

final case class Concrete(
    color: BlockColor,

) extends ColoredItem with StackableItem {
  val `type` = ItemType.CONCRETE
}

final case class ConcretePowder(
    color: BlockColor,

) extends ColoredItem with StackableItem {
  val `type` = ItemType.CONCRETE_POWDER
}

final case class Conduit() extends StackableItem {
  val `type` = ItemType.CONDUIT
}

final case class Cookie() extends StackableItem {
  val `type` = ItemType.COOKIE
  override val edible = true
}

final case class Coral(
    variant: CoralVariant,

) extends VariedItem[CoralVariant] with StackableItem {
  val `type` = ItemType.CORAL
}

final case class CoralBlock(
    variant: CoralVariant,

) extends VariedItem[CoralVariant] with StackableItem {
  val `type` = ItemType.CORAL_BLOCK
}

final case class CoralFan(
    variant: CoralVariant,

) extends VariedItem[CoralVariant] with StackableItem {
  val `type` = ItemType.CORAL_FAN
}

final case class CraftingTable() extends StackableItem {
  val `type` = ItemType.CRAFTING_TABLE
}

final case class Crossbow() extends Item {
  val `type` = ItemType.CROSSBOW
  override val maxDurability = 326
}

final case class DaylightDetector() extends StackableItem {
  val `type` = ItemType.DAYLIGHT_DETECTOR
}

final case class DeadBush() extends StackableItem {
  val `type` = ItemType.DEAD_BUSH
}

final case class DebugStick() extends Item {
  val `type` = ItemType.DEBUG_STICK
}

final case class Diamond() extends StackableItem {
  val `type` = ItemType.DIAMOND
}

final case class DiamondBlock() extends StackableItem {
  val `type` = ItemType.DIAMOND_BLOCK
}

final case class DiamondOre() extends StackableItem {
  val `type` = ItemType.DIAMOND_ORE
}

final case class Diorite(
    variant: StoniteVariant,

) extends VariedItem[StoniteVariant] with StackableItem {
  val `type` = ItemType.DIORITE
}

final case class Dirt(
    coarse: Boolean,

) extends StackableItem {
  val `type` = ItemType.DIRT
  def withCoarse(coarse: Boolean): Dirt =
    copyWith("coarse", coarse)
}

final case class Dispenser() extends StackableItem {
  val `type` = ItemType.DISPENSER
}

final case class Door(
    material: DoorMaterial,

) extends MaterialItem[DoorMaterial] with StackableItem {
  val `type` = ItemType.DOOR
}

final case class DragonBreath() extends StackableItem {
  val `type` = ItemType.DRAGON_BREATH
}

final case class DragonEgg() extends StackableItem {
  val `type` = ItemType.DRAGON_EGG
}

final case class DriedKelp() extends StackableItem {
  val `type` = ItemType.DRIED_KELP
  override val edible = true
}

final case class DriedKelpBlock() extends StackableItem {
  val `type` = ItemType.DRIED_KELP_BLOCK
}

final case class Dropper() extends StackableItem {
  val `type` = ItemType.DROPPER
}

final case class Dye(
    color: BlockColor,

) extends ColoredItem with StackableItem {
  val `type` = ItemType.DYE
}

final case class Egg() extends StackableItem {
  val `type` = ItemType.EGG
}

final case class Elytra() extends Item {
  val `type` = ItemType.ELYTRA
  override val maxDurability = 432
}

final case class Emerald() extends StackableItem {
  val `type` = ItemType.EMERALD
}

final case class EmeraldBlock() extends StackableItem {
  val `type` = ItemType.EMERALD_BLOCK
}

final case class EmeraldOre() extends StackableItem {
  val `type` = ItemType.EMERALD_ORE
}

final case class EnchantingTable() extends StackableItem {
  val `type` = ItemType.ENCHANTING_TABLE
}

final case class EndCrystal() extends StackableItem {
  val `type` = ItemType.END_CRYSTAL
}

final case class EndPortalFrame() extends StackableItem {
  val `type` = ItemType.END_PORTAL_FRAME
}

final case class EndRod() extends StackableItem {
  val `type` = ItemType.END_ROD
}

final case class EndStone(
    material: EndStoneMaterial,

) extends MaterialItem[EndStoneMaterial] with StackableItem {
  val `type` = ItemType.END_STONE // TODO rename EndStoneMaterial.END_STONE > NORMAL?
}

final case class EnderEye() extends StackableItem {
  val `type` = ItemType.ENDER_EYE
}

final case class EnderPearl() extends StackableItem {
  val `type` = ItemType.ENDER_PEARL
  override val maxCount = 16
}

final case class Farmland() extends StackableItem {
  val `type` = ItemType.FARMLAND
}

final case class Feather() extends StackableItem {
  val `type` = ItemType.FEATHER
}

final case class Fence(
    material: FenceMaterial,

) extends MaterialItem[FenceMaterial] with StackableItem {
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

final case class FishingRod() extends Item {
  val `type` = ItemType.FISHING_ROD
  override val maxDurability = 64
}

final case class FletchingTable() extends StackableItem {
  val `type` = ItemType.FLETCHING_TABLE
}

final case class Flint() extends StackableItem {
  val `type` = ItemType.FLINT
}

final case class FlintAndSteel() extends Item {
  val `type` = ItemType.FLINT_AND_STEEL
  override val maxDurability = 64
}

final case class Flower(
    variant: FlowerVariant,

) extends VariedItem[FlowerVariant] with StackableItem {
  val `type` = ItemType.FLOWER
}

final case class FlowerPot() extends StackableItem {
  val `type` = ItemType.FLOWER_POT
}

final case class Furnace() extends StackableItem {
  val `type` = ItemType.FURNACE
}

final case class Gate(
    material: WoodMaterial,

) extends MaterialItem[WoodMaterial] with StackableItem {
  val `type` = ItemType.GATE
}

final case class GhastTear() extends StackableItem {
  val `type` = ItemType.GHAST_TEAR
}

final case class Glass(
    color: Option[BlockColor],

) extends ColorableItem with StackableItem {
  val `type` = ItemType.GLASS
}

final case class GlassBottle() extends StackableItem {
  val `type` = ItemType.GLASS_BOTTLE
}

final case class GlassPane(
    color: Option[BlockColor],

) extends ColorableItem with StackableItem {
  val `type` = ItemType.GLASS_PANE
}

final case class GlazedTerracotta(
    color: BlockColor,

) extends ColoredItem with StackableItem {
  val `type` = ItemType.GLAZED_TERRACOTTA
}

final case class Glowstone() extends StackableItem {
  val `type` = ItemType.GLOWSTONE
}

final case class GlowstoneDust() extends StackableItem {
  val `type` = ItemType.GLOWSTONE_DUST
}

final case class GoldBlock() extends StackableItem {
  val `type` = ItemType.GOLD_BLOCK
}

final case class GoldIngot() extends StackableItem {
  val `type` = ItemType.GOLD_INGOT
}

final case class GoldNugget() extends StackableItem {
  val `type` = ItemType.GOLD_NUGGET
}

final case class GoldOre() extends StackableItem {
  val `type` = ItemType.GOLD_ORE
}

final case class GoldenApple(
    enchanted: Boolean,

) extends Item with StackableItem {
  val `type` = ItemType.GOLDEN_APPLE
  override val edible = true
  // TODO with enchanted
}

final case class GoldenCarrot() extends StackableItem {
  val `type` = ItemType.GOLDEN_CARROT
  override val edible = true
}

final case class GoldenMelonSlice() extends StackableItem {
  val `type` = ItemType.GOLDEN_MELON_SLICE
}

final case class Granite(
    variant: StoniteVariant,

) extends VariedItem[StoniteVariant] with StackableItem {
  val `type` = ItemType.GRANITE
}

final case class Grass(
    tall: Boolean,

) extends StackableItem {
  val `type` = ItemType.GRASS
  def withTall(tall: Boolean): Grass =
    copyWith("tall", tall)
}

final case class GrassBlock() extends StackableItem {
  val `type` = ItemType.GRASS_BLOCK
}

final case class GrassPath() extends StackableItem {
  val `type` = ItemType.GRASS_PATH
}

final case class Gravel() extends StackableItem {
  val `type` = ItemType.GRAVEL
}

final case class Grindstone() extends StackableItem {
  val `type` = ItemType.GRINDSTONE
}

final case class Gunpowder() extends StackableItem {
  val `type` = ItemType.GUNPOWDER
}

final case class HayBale() extends StackableItem {
  val `type` = ItemType.HAY_BALE
}

final case class HeartOfTheSea() extends StackableItem {
  val `type` = ItemType.HEART_OF_THE_SEA
}

final case class Helmet(
    material: ArmorMaterial,

) extends StackableItem {
  val `type` = ItemType.HELMET
}

final case class Hoe(
    material: ToolMaterial,

) extends StackableItem {
  val `type` = ItemType.HOE
}

final case class Hopper() extends StackableItem {
  val `type` = ItemType.HOPPER
}

final case class HorseArmor(
    variant: HorseArmorVariant,

) extends StackableItem {
  val `type` = ItemType.HORSE_ARMOR
}

final case class Ice(
    variant: IceVariant,

) extends VariedItem[IceVariant] with StackableItem {
  val `type` = ItemType.ICE
}

final case class InfestedBlock(
    material: InfestedMaterial,
    variant: Option[InfestedVariant],

) extends MaterialItem[InfestedMaterial] with StackableItem {
  val `type` = ItemType.INFESTED_BLOCK
}

final case class InkSac() extends StackableItem {
  val `type` = ItemType.INK_SAC
}

final case class IronBars() extends StackableItem {
  val `type` = ItemType.IRON_BARS
}

final case class IronBlock() extends StackableItem {
  val `type` = ItemType.IRON_BLOCK
}

final case class IronIngot() extends StackableItem {
  val `type` = ItemType.IRON_INGOT
}

final case class IronNugget() extends StackableItem {
  val `type` = ItemType.IRON_NUGGET
}

final case class IronOre() extends StackableItem {
  val `type` = ItemType.IRON_ORE
}

final case class ItemFrame() extends StackableItem {
  val `type` = ItemType.ITEM_FRAME
}

final case class JackOfTheLantern() extends StackableItem {
  val `type` = ItemType.JACK_OF_THE_LANTERN
}

final case class JigsawBlock() extends StackableItem {
  val `type` = ItemType.JIGSAW_BLOCK
}

final case class Jukebox() extends StackableItem {
  val `type` = ItemType.JUKEBOX
}

final case class Kelp() extends StackableItem {
  val `type` = ItemType.KELP
}

final case class Ladder() extends StackableItem {
  val `type` = ItemType.LADDER
}

final case class Lantern() extends StackableItem {
  val `type` = ItemType.LANTERN
}

final case class Lapis() extends StackableItem {
  val `type` = ItemType.LAPIS
}

final case class LapisBlock() extends StackableItem {
  val `type` = ItemType.LAPIS_BLOCK
}

final case class LapisOre() extends StackableItem {
  val `type` = ItemType.LAPIS_ORE
}

final case class Lead() extends StackableItem {
  val `type` = ItemType.LEAD
}

final case class Leather() extends StackableItem {
  val `type` = ItemType.LEATHER
}

final case class Leaves(
    material: WoodMaterial,

) extends MaterialItem[WoodMaterial] with StackableItem {
  val `type` = ItemType.LEAVES
}

final case class Lectern() extends StackableItem {
  val `type` = ItemType.LECTERN
}

final case class Leggings(
    material: ArmorMaterial,

) extends StackableItem {
  val `type` = ItemType.LEGGINGS
}

final case class Lever() extends StackableItem {
  val `type` = ItemType.LEVER
}

final case class LilyPad() extends StackableItem {
  val `type` = ItemType.LILY_PAD
}

final case class Log(
    material: WoodMaterial,
    stripped: Boolean,

) extends MaterialItem[WoodMaterial] with StackableItem {
  val `type` = ItemType.LOG
}

final case class Loom() extends StackableItem {
  val `type` = ItemType.LOOM
}

final case class MagmaBlock() extends StackableItem {
  val `type` = ItemType.MAGMA_BLOCK
}

final case class MagmaCream() extends StackableItem {
  val `type` = ItemType.MAGMA_CREAM
}

//final case class Map extends Item {
//  // TODO filled variant
//}

final case class Melon() extends StackableItem {
  val `type` = ItemType.MELON
}

final case class MelonSlice() extends StackableItem {
  val `type` = ItemType.MELON_SLICE
  override val edible = true
}

final case class Minecart(
    variant: MinecartVariant,

) extends VariedItem[MinecartVariant] with StackableItem {
  val `type` = ItemType.MINECART
}

final case class MobHead(
    variant: MobHeadVariant,

) extends StackableItem {
  val `type` = ItemType.MOB_HEAD
}

final case class Mushroom(
    variant: MushroomVariant,

) extends VariedItem[MushroomVariant] with StackableItem {
  val `type` = ItemType.MUSHROOM
}

final case class MushroomBlock(
    variant: MushroomBlockVariant,

) extends VariedItem[MushroomBlockVariant] with StackableItem {
  val `type` = ItemType.MUSHROOM_BLOCK
}

final case class MusicDisc(
    variant: MusicDiscVariant,

) extends VariedItem[MusicDiscVariant] with StackableItem {
  val `type` = ItemType.MUSIC_DISC
}

final case class Mutton(
    cooked: Boolean,

) extends StackableItem {
  val `type` = ItemType.MUTTON
  override val edible = true
}

final case class Mycelium() extends StackableItem {
  val `type` = ItemType.MYCELIUM
}

final case class NameTag() extends StackableItem {
  val `type` = ItemType.NAME_TAG
}

final case class NautilusShell() extends StackableItem {
  val `type` = ItemType.NAUTILUS_SHELL
}

final case class Netherrack() extends StackableItem {
  val `type` = ItemType.NETHERRACK
}

final case class NetherStar() extends StackableItem {
  val `type` = ItemType.NETHER_STAR
}

final case class NetherWart() extends StackableItem {
  val `type` = ItemType.NETHER_WART
}

final case class NetherWartBlock() extends StackableItem {
  val `type` = ItemType.NETHER_WART_BLOCK
}

final case class NoteBlock() extends StackableItem {
  val `type` = ItemType.NOTE_BLOCK
}

final case class Observer() extends StackableItem {
  val `type` = ItemType.OBSERVER
}

final case class Obsidian() extends StackableItem {
  val `type` = ItemType.OBSIDIAN
}

final case class Painting() extends StackableItem {
  val `type` = ItemType.PAINTING
}

final case class Paper() extends StackableItem {
  val `type` = ItemType.PAPER
}

final case class PhantomMembrane() extends StackableItem {
  val `type` = ItemType.PHANTOM_MEMBRANE
}

final case class Plant(
    variant: PlantVariant,

) extends StackableItem {
  val `type` = ItemType.PLANT
}

final case class Pickaxe(
    material: ToolMaterial,

) extends StackableItem {
  val `type` = ItemType.PICKAXE
}

final case class Pillar(
    material: PillarMaterial,

) extends MaterialItem[PillarMaterial] with StackableItem {
  val `type` = ItemType.PILLAR
}

final case class Piston(
    sticky: Boolean,

) extends StackableItem {
  val `type` = ItemType.PISTON
  def withSticky(sticky: Boolean): Piston =
    copyWith("sticky", sticky)
}

final case class Planks(
    material: WoodMaterial,

) extends MaterialItem[WoodMaterial] with StackableItem {
  val `type` = ItemType.PLANKS
}

final case class Podzol() extends StackableItem {
  val `type` = ItemType.PODZOL
}

final case class PoisonousPotato() extends StackableItem {
  val `type` = ItemType.POISONOUS_POTATO
  override val edible = true
}

final case class Porkchop(
    cooked: Boolean,

) extends StackableItem {
  val `type` = ItemType.PORKCHOP
  override val edible = true
}

final case class Potato(
    cooked: Boolean, // TODO rename this baked? Also cooked items no longer have a withCooked def

) extends StackableItem {
  val `type` = ItemType.POTATO
  override val edible = true
}

final case class Potion(
    hideEffects: Boolean
) extends Item {
  // TODO variants

  def withHideEffects(hideEffects: Boolean): this.type =
    copyWith("hideEffects", hideEffects)
}

final case class PressurePlate(
    material: PressurePlateMaterial,

) extends MaterialItem[PressurePlateMaterial] with StackableItem {
  val `type` = ItemType.PRESSURE_PLATE
}

final case class Prismarine(
    material: PrismarineMaterial,

) extends MaterialItem[PrismarineMaterial] with StackableItem {
  val `type` = ItemType.PRISMARINE
}

final case class PrismarineCrystals() extends StackableItem {
  val `type` = ItemType.PRISMARINE_CRYSTALS
}

final case class PrismarineShard() extends StackableItem {
  val `type` = ItemType.PRISMARINE_SHARD
}

final case class Pufferfish() extends StackableItem {
  val `type` = ItemType.PUFFERFISH
  override val edible = true
}

final case class Pumpkin(
    carved: Boolean,

) extends StackableItem {
  val `type` = ItemType.PUMPKIN
}

final case class PumpkinPie() extends StackableItem {
  val `type` = ItemType.PUMPKIN_PIE
  override val edible = true
}

final case class Purpur() extends StackableItem {
  val `type` = ItemType.PURPUR
}

final case class Quartz() extends StackableItem {
  val `type` = ItemType.QUARTZ
}

final case class QuartzBlock(
    variant: QuartzVariant,

) extends VariedItem[QuartzVariant] with StackableItem {
  val `type` = ItemType.QUARTZ_BLOCK
}

final case class QuartzOre() extends StackableItem {
  val `type` = ItemType.QUARTZ_ORE
}

final case class Rabbit(
    cooked: Boolean,

) extends StackableItem {
  val `type` = ItemType.RABBIT
  override val edible = true
}

final case class RabbitFoot() extends StackableItem {
  val `type` = ItemType.RABBIT_FOOT
}

final case class RabbitHide() extends StackableItem {
  val `type` = ItemType.RABBIT_HIDE
}

final case class Rails(
    variant: RailsVariant,

) extends VariedItem[RailsVariant] with StackableItem {
  val `type` = ItemType.RAILS
}

final case class Repeater() extends StackableItem {
  val `type` = ItemType.REPEATER
}

final case class Redstone() extends StackableItem {
  val `type` = ItemType.REDSTONE
}

final case class RedstoneBlock() extends StackableItem {
  val `type` = ItemType.REDSTONE_BLOCK
}

final case class RedstoneLamp() extends StackableItem {
  val `type` = ItemType.REDSTONE_LAMP
}

final case class RedstoneOre() extends StackableItem {
  val `type` = ItemType.REDSTONE_ORE
}

final case class RedstoneTorch() extends StackableItem {
  val `type` = ItemType.REDSTONE_TORCH
}

final case class RottenFlesh() extends StackableItem {
  val `type` = ItemType.ROTTEN_FLESH
  override val edible = true
}

final case class Saddle() extends Item {
  val `type` = ItemType.SADDLE
}

final case class Salmon(
    cooked: Boolean,

) extends StackableItem {
  val `type` = ItemType.SALMON
  override val edible = true
}

final case class Sand(
    material: SandMaterial, // TODO should this be a variant?

) extends MaterialItem[SandMaterial] with StackableItem {
  val `type` = ItemType.SAND
}

final case class Sandstone(
    material: SandstoneMaterial,
    variant: SandstoneVariant,

) extends MaterialItem[SandstoneMaterial]
  with VariedItem[SandstoneVariant] with StackableItem {
  val `type` = ItemType.SANDSTONE
}

final case class Sapling(
    variant: SaplingVariant, // TODO would kinda like this to be material again, maybe especially
    // since item Saplings cannot be Bamboo

) extends VariedItem[SaplingVariant] with StackableItem {
  val `type` = ItemType.SAPLING
}

final case class Scaffolding() extends StackableItem {
  val `type` = ItemType.SCAFFOLDING
}

final case class Scute() extends StackableItem {
  val `type` = ItemType.SCUTE
}

final case class Seagrass() extends StackableItem {
  val `type` = ItemType.SEAGRASS
}

final case class SeaLantern() extends StackableItem {
  val `type` = ItemType.SEA_LANTERN
}

final case class SeaPickle() extends StackableItem {
  val `type` = ItemType.SEA_PICKLE
}

final case class Shears() extends Item {
  val `type` = ItemType.SHEARS
  override val maxDurability = 238
}

final case class Shield() extends Item {
  val `type` = ItemType.SHIELD
  override val maxDurability = 336
}

final case class Shovel(
    material: ToolMaterial,

) extends StackableItem {
  val `type` = ItemType.SHOVEL
}

final case class ShulkerBox(
    color: Option[BlockColor],

) extends ColorableItem with StackableItem {
  val `type` = ItemType.SHULKER_BOX
}

final case class ShulkerShell() extends StackableItem {
  val `type` = ItemType.SHULKER_SHELL
}

final case class Sign(
    material: WoodMaterial,

) extends MaterialItem[WoodMaterial] with StackableItem {
  val `type` = ItemType.SIGN
  override val maxCount = 16
}

final case class Slab(
    material: SlabMaterial,
    variant: Option[SlabVariant],

) extends MaterialItem[SlabMaterial]
  with VariableItem[SlabVariant] with StackableItem {
  val `type` = ItemType.SLAB
}

final case class Slimeball() extends StackableItem {
  val `type` = ItemType.SLIMEBALL
}

final case class SlimeBlock() extends StackableItem {
  val `type` = ItemType.SLIME_BLOCK
}

final case class SmithingTable() extends StackableItem {
  val `type` = ItemType.SMITHING_TABLE
}

final case class Smoker() extends StackableItem {
  val `type` = ItemType.SMOKER
}

final case class Snow() extends StackableItem {
  val `type` = ItemType.SNOW
}

final case class Snowball() extends StackableItem {
  val `type` = ItemType.SNOWBALL
  override val maxCount = 16
}

final case class SnowBlock() extends StackableItem {
  val `type` = ItemType.SNOW_BLOCK
}

final case class SoulSand() extends StackableItem {
  val `type` = ItemType.SOUL_SAND
}

final case class SpawnEgg(
    variant: SpawnEggVariant,

) extends VariedItem[SpawnEggVariant] with StackableItem {
  val `type` = ItemType.SPAWN_EGG // TODO MonsterEgg?
}

final case class Spawner() extends StackableItem {
  val `type` = ItemType.SPAWNER
}

final case class SpiderEye(
    fermented: Boolean,

) extends StackableItem {
  val `type` = ItemType.SPIDER_EYE
  override val edible = !fermented
}

final case class Sponge(
    wet: Boolean,

) extends StackableItem {
  val `type` = ItemType.SPONGE
}

final case class Stairs(
    material: StairsMaterial,
    variant: Option[StairsVariant],

) extends MaterialItem[StairsMaterial]
  with VariableItem[StairsVariant] with StackableItem {
  val `type` = ItemType.SLAB
}

final case class Stick() extends StackableItem {
  val `type` = ItemType.STICK
}

final case class Stone(
    material: StoneMaterial,
    variant: StoneVariant,

) extends MaterialItem[StoneMaterial]
  with VariedItem[StoneVariant] with StackableItem {
  val `type` = ItemType.STONE
}

final case class Stonecutter() extends StackableItem {
  val `type` = ItemType.STONECUTTER
}

final case class PieceOfString() extends StackableItem {
  val `type` = ItemType.STRING
}

final case class Sugar() extends StackableItem {
  val `type` = ItemType.SUGAR
}

final case class SugarCane() extends StackableItem {
  val `type` = ItemType.SUGAR_CANE
}

final case class SweetBerries() extends StackableItem {
  val `type` = ItemType.SWEET_BERRIES
  override val edible = true
}

final case class Sword(
    material: ToolMaterial,

) extends StackableItem {
  val `type` = ItemType.SWORD
}

final case class Terracotta(
    color: Option[BlockColor],

) extends ColorableItem with StackableItem {
  val `type` = ItemType.TERRACOTTA
}

final case class TNT() extends StackableItem {
  val `type` = ItemType.TNT
}

final case class Torch() extends StackableItem {
  val `type` = ItemType.TORCH
}

final case class TotemOfUndying() extends Item {
  val `type` = ItemType.TOTEM_OF_UNDYING
}

final case class TrapDoor(
    material: TrapdoorMaterial,

) extends MaterialItem[TrapdoorMaterial] with StackableItem {
  val `type` = ItemType.TRAPDOOR
}

final case class Trident() extends Item {
  val `type` = ItemType.TRIDENT
  override val maxDurability = 250
}

final case class TripwireHook() extends StackableItem {
  val `type` = ItemType.TRIPWIRE_HOOK
}

final case class TropicalFish() extends StackableItem {
  val `type` = ItemType.TROPICAL_FISH
  override val edible = true
}

final case class TurtleEgg() extends StackableItem {
  val `type` = ItemType.TURTLE_EGG
}

final case class TurtleHelmet() extends Item {
  val `type` = ItemType.TURTLE_HELMET
  override val maxDurability = 275
}

final case class Vine() extends StackableItem {
  val `type` = ItemType.VINE
}

final case class Wall(
    material: WallMaterial,
    variant: Option[WallVariant],

) extends MaterialItem[WallMaterial]
  with VariableItem[WallVariant] with StackableItem {
  val `type` = ItemType.WALL
}

final case class WeightedPressurePlate(
    variant: WeightedPressurePlateVariant,

) extends VariedItem[WeightedPressurePlateVariant] with StackableItem {
  val `type` = ItemType.WEIGHTED_PRESSURE_PLATE
}

final case class Wheat() extends StackableItem {
  val `type` = ItemType.WHEAT
}

final case class Wood(
    material: WoodMaterial,
    stripped: Boolean,

) extends MaterialItem[WoodMaterial] with StackableItem {
  val `type` = ItemType.WOOD
}

final case class Wool(
    color: BlockColor,

) extends ColoredItem with StackableItem {
  val `type` = ItemType.WOOL
}
