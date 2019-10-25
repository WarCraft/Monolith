package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.material._
import gg.warcraft.monolith.api.world.block.variant._
import gg.warcraft.monolith.api.world.item.variant._

final case class Andesite(
    variant: StoniteVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[StoniteVariant]
    with StackableItem {
  val `type` = ItemType.ANDESITE
  def this(variant: StoniteVariant) =
    this(variant, 1, ItemData(variant, "Andesite"))
}

final case class Anvil(
    variant: AnvilVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[AnvilVariant]
    with StackableItem {
  val `type` = ItemType.ANVIL
  def this(variant: AnvilVariant) =
    this(variant, 1, ItemData(variant, "Anvil"))
}

final case class ArmorStand(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.ARMOR_STAND
  override val maxStackSize = 16
  def this() = this(1, ItemData("Armor Stand"))
}

final case class Bamboo(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BAMBOO
  def this() = this(1, ItemData("Bamboo"))
}

final case class Banner(
    color: BlockColor,
    stackSize: Int,
    data: ItemData
) extends ColoredItem
    with StackableItem {
  val `type` = ItemType.BANNER
  override val maxStackSize = 16
  def this(color: BlockColor) =
    this(color, 1, ItemData(color, "Banner"))
}

final case class BannerPattern(data: ItemData) extends Item {
  val `type` = ItemType.BANNER_PATTERN
  def this() = this(ItemData("Banner Pattern"))
}

final case class Barrel(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BARREL
  def this() = this(1, ItemData("Barrel"))
}

final case class Barrier(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BARRIER
  def this() = this(1, ItemData("Barrier"))
}

final case class Beacon(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BEACON
  def this() = this(1, ItemData("Beacon"))
}

final case class Bed(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.BED
  def this(color: BlockColor) =
    this(color, ItemData(color, "Bed"))
}

final case class Bedrock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BEDROCK
  def this() = this(1, ItemData("Bedrock"))
}

final case class Bell(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BELL
  def this() = this(1, ItemData("Bell"))
}

final case class BlastFurnace(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BLAST_FURNACE
  def this() = this(1, ItemData("Blast Furnace"))
}

final case class BlazePowder(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BLAZE_POWDER
  def this() = this(1, ItemData("Blaze Powder"))
}

final case class BlazeRod(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BLAZE_ROD
  def this() = this(1, ItemData("Blaze Rod"))
}

final case class Boat(
    material: WoodMaterial,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.BOAT
  def this(material: WoodMaterial) =
    this(material, ItemData(material, "Boat"))
}

final case class Bookshelf(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BOOKSHELF
  def this() = this(1, ItemData("Bookshelf"))
}

final case class Bow(data: ItemData) extends Item {
  val `type` = ItemType.BOW
  def this() = this(ItemData("Bow"))
}

final case class Bowl(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BOWL
  def this() = this(1, ItemData("Bowl"))
}

final case class Bread(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BREAD
  def this() = this(1, ItemData("Bread"))
}

final case class BrewingStand(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.BREWING_STAND
  def this() = this(1, ItemData("Brewing Stand"))
}

final case class Brick(
    material: BrickMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[BrickMaterial]
    with StackableItem {
  val `type` = ItemType.BRICK
  def this(material: BrickMaterial) =
    this(material, 1, ItemData(material, "Brick"))
}

final case class BrickBlock(
    material: BrickMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[BrickMaterial]
    with StackableItem {
  val `type` = ItemType.BRICK_BLOCK
  def this(material: BrickMaterial) =
    this(material, 1, ItemData(material, "Brick Block"))
}

final case class Bucket(
    variant: BucketVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[BucketVariant]
    with StackableItem {
  val `type` = ItemType.BUCKET
  override val maxStackSize = 16
  def this(variant: BucketVariant) =
    this(variant, 1, ItemData(variant, "Bucket"))
}

final case class Button(
    material: ButtonMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[ButtonMaterial]
    with StackableItem {
  val `type` = ItemType.BUTTON
  def this(material: ButtonMaterial) =
    this(material, 1, ItemData(material, "Button"))
}

final case class Cactus(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CACTUS
  def this() = this(1, ItemData("Cactus"))
}

final case class Cake(data: ItemData) extends Item {
  val `type` = ItemType.CAKE
  def this() = this(ItemData("Cake"))
}

final case class Campfire(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CAMPFIRE
  def this() = this(1, ItemData("Campfire"))
}

final case class Carpet(
    color: BlockColor,
    stackSize: Int,
    data: ItemData
) extends ColoredItem
    with StackableItem {
  val `type` = ItemType.CARPET
  def this(color: BlockColor) =
    this(color, 1, ItemData(color, "Carpet"))
}

final case class Carrot(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CARROT
  def this() = this(1, ItemData("Carrot"))
}

final case class CarrotOnAStick(data: ItemData) extends Item {
  val `type` = ItemType.CARROT_ON_A_STICK
  def this() = this(ItemData("Carrot On A Stick"))
}

final case class CartographyTable(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CARTOGRAPHY_TABLE
  def this() = this(1, ItemData("Cartography Table"))
}

final case class Charcoal(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CHARCOAL
  def this() = this(1, ItemData("Charcoal"))
}

final case class Cauldron(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CAULDRON
  def this() = this(1, ItemData("Cauldron"))
}

final case class Chest(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CHEST
  def this() = this(1, ItemData("Chest"))
}

final case class ChorusFlower(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CHORUS_FLOWER
  def this() = this(1, ItemData("Chorus Flower"))
}

final case class ChorusFruit(
    popped: Boolean,
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CHORUS_FRUIT
  override val edible: Boolean = !popped
  def this(popped: Boolean) =
    this(popped, 1, ItemData(popped, "Popped", "Chorus Fruit"))
  def withPopped(popped: Boolean): this.type =
    copyWith("popped", popped)
}

final case class ChorusPlant(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CHORUS_PLANT
  def this() = this(1, ItemData("Chorus Plant"))
}

final case class Clay(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CLAY
  def this() = this(1, ItemData("Clay"))
}

final case class ClayBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CLAY_BLOCK
  def this() = this(1, ItemData("Clay Block"))
}

final case class Clock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CLOCK
  def this() = this(1, ItemData("Clock"))
}

final case class Coal(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.COAL
  def this() = this(1, ItemData("Coal"))
}

final case class CoalBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.COAL_BLOCK
  def this() = this(1, ItemData("Coal Block"))
}

final case class CoalOre(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.COAL_ORE
  def this() = this(1, ItemData("Coal Ore"))
}

final case class Cobblestone(
    variant: CobblestoneVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[CobblestoneVariant]
    with StackableItem {
  val `type` = ItemType.COBBLESTONE
  def this(variant: CobblestoneVariant) =
    this(variant, 1, ItemData(variant, "Cobblestone"))
}

final case class Cobweb(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.COBWEB
  def this() = this(1, ItemData("Cobweb"))
}

final case class CommandBlock(
    variant: CommandBlockVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[CommandBlockVariant]
    with StackableItem {
  val `type` = ItemType.COMMAND_BLOCK
  def this(variant: CommandBlockVariant) =
    this(variant, 1, ItemData(variant, "Command Block"))
}

final case class Comparator(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.COMPARATOR
  def this() = this(1, ItemData("Comparator"))
}

final case class Compass(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.COMPASS
  def this() = this(1, ItemData("Compass"))
}

final case class Composter(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.COMPOSTER
  def this() = this(1, ItemData("Composter"))
}

final case class Concrete(
    color: BlockColor,
    stackSize: Int,
    data: ItemData
) extends ColoredItem
    with StackableItem {
  val `type` = ItemType.CONCRETE
  def this(color: BlockColor) =
    this(color, 1, ItemData(color, "Concrete"))
}

final case class ConcretePowder(
    color: BlockColor,
    stackSize: Int,
    data: ItemData
) extends ColoredItem
    with StackableItem {
  val `type` = ItemType.CONCRETE_POWDER
  def this(color: BlockColor) =
    this(color, 1, ItemData(color, "Concrete Powder"))
}

final case class Conduit(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CONDUIT
  def this() = this(1, ItemData("Conduit"))
}

final case class Coral(
    variant: CoralVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[CoralVariant]
    with StackableItem {
  val `type` = ItemType.CORAL
  def this(variant: CoralVariant) =
    this(variant, 1, ItemData(variant, "Coral"))
}

final case class CoralBlock(
    variant: CoralVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[CoralVariant]
    with StackableItem {
  val `type` = ItemType.CORAL_BLOCK
  def this(variant: CoralVariant) =
    this(variant, 1, ItemData(variant, "Coral Block"))
}

final case class CoralFan(
    variant: CoralVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[CoralVariant]
    with StackableItem {
  val `type` = ItemType.CORAL_FAN
  def this(variant: CoralVariant) =
    this(variant, 1, ItemData(variant, "Coral Fan"))
}

final case class CraftingTable(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.CRAFTING_TABLE
  def this() = this(1, ItemData("Crafting Table"))
}

final case class Crossbow(data: ItemData) extends Item {
  val `type` = ItemType.CROSSBOW
  def this() = this(ItemData("Crossbow"))
}

final case class DaylightDetector(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.DAYLIGHT_DETECTOR
  def this() = this(1, ItemData("Daylight Detector"))
}

final case class DeadBush(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.DEAD_BUSH
  def this() = this(1, ItemData("Dead Bush"))
}

final case class DebugStick(data: ItemData) extends Item {
  val `type` = ItemType.DEBUG_STICK
  def this() = this(ItemData("Debug Stick"))
}

final case class Diamond(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.DIAMOND
  def this() = this(1, ItemData("Diamond"))
}

final case class DiamondBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.DIAMOND_BLOCK
  def this() = this(1, ItemData("Diamond Block"))
}

final case class DiamondOre(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.DIAMOND_ORE
  def this() = this(1, ItemData("Diamond Ore"))
}

final case class Diorite(
    variant: StoniteVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[StoniteVariant]
    with StackableItem {
  val `type` = ItemType.DIORITE
  def this(variant: StoniteVariant) =
    this(variant, 1, ItemData(variant, "Diorite"))
}

final case class Dirt(
    coarse: Boolean,
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.DIRT
  def this(coarse: Boolean) =
    this(coarse, 1, ItemData(coarse, "Coarse", "Dirt"))
  // TODO withCoarse
}

final case class Dispenser(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.DISPENSER
  def this() = this(1, ItemData("Dispenser"))
}

final case class Door(
    material: DoorMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[DoorMaterial]
    with StackableItem {
  val `type` = ItemType.DOOR
  def this(material: DoorMaterial) =
    this(material, 1, ItemData(material, "Door"))
}

final case class DragonBreath(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.DRAGON_BREATH
  def this() = this(1, ItemData("Dragon Breath"))
}

final case class DragonEgg(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.DRAGON_EGG
  def this() = this(1, ItemData("Dragon Egg"))
}

final case class DriedKelp(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.DRIED_KELP
  def this() = this(1, ItemData("Dried Kelp"))
}

final case class DriedKelpBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.DRIED_KELP_BLOCK
  def this() = this(1, ItemData("Dried Kelp Block"))
}

final case class Dropper(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.DROPPER
  def this() = this(1, ItemData("Dropper"))
}

final case class Egg(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.EGG
  def this() = this(1, ItemData("Egg"))
}

final case class Elytra(data: ItemData) extends Item {
  val `type` = ItemType.ELYTRA
  def this() = this(ItemData("Elytra"))
}

final case class Emerald(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.EMERALD
  def this() = this(1, ItemData("Emerald"))
}

final case class EmeraldBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.EMERALD_BLOCK
  def this() = this(1, ItemData("Emerald Block"))
}

final case class EmeraldOre(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.EMERALD_ORE
  def this() = this(1, ItemData("Emerald Ore"))
}

final case class EnchantingTable(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.ENCHANTING_TABLE
  def this() = this(1, ItemData("Enchanting Table"))
}

final case class EndCrystal(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.END_CRYSTAL
  def this() = this(1, ItemData("End Crystal"))
}

final case class EndPortalFrame(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.END_PORTAL_FRAME
  def this() = this(1, ItemData("End Portal Frame"))
}

final case class EndRod(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.END_ROD
  def this() = this(1, ItemData("End Rod"))
}

final case class EndStone(
    material: EndStoneMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[EndStoneMaterial]
    with StackableItem {
  val `type` = ItemType.END_STONE // TODO rename EndStoneMaterial.END_STONE > NORMAL?
  def this(material: EndStoneMaterial) =
    this(material, 1, ItemData("End Stone", material))
}

final case class EnderEye(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.ENDER_EYE
  def this() = this(1, ItemData("Ender Eye"))
}

final case class EnderPearl(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.ENDER_PEARL
  override val maxStackSize = 16
  def this() = this(1, ItemData("Ender Pearl"))
}

final case class FarmLand(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.FARMLAND
  def this() = this(1, ItemData("Farmland"))
}

final case class Feather(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.FEATHER
  def this() = this(1, ItemData("Feather"))
}

final case class Fence(
    material: FenceMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[FenceMaterial]
    with StackableItem {
  val `type` = ItemType.FENCE
  def this(material: FenceMaterial) =
    this(material, 1, ItemData(material, "Fence"))
}

final case class FishingRod(data: ItemData) extends Item {
  val `type` = ItemType.FISHING_ROD
  def this() = this(ItemData("Fishing Rod"))
}

final case class FletchingTable(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.FLETCHING_TABLE
  def this() = this(1, ItemData("Fletching Table"))
}

final case class Flint(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.FLINT
  def this() = this(1, ItemData("Flint"))
}

final case class FlintAndSteel(data: ItemData) extends Item {
  val `type` = ItemType.FLINT_AND_STEEL
  def this() = this(ItemData("Flint and Steel"))
}

final case class FlowerPot(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.FLOWER_POT
  def this() = this(1, ItemData("Flower Pot"))
}

final case class Furnace(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.FURNACE
  def this() = this(1, ItemData("Furnace"))
}

final case class Gate(
    material: WoodMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[WoodMaterial]
    with StackableItem {
  val `type` = ItemType.GATE
  def this(material: WoodMaterial) =
    this(material, 1, ItemData(material, "Gate"))
}

final case class GhastTear(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GHAST_TEAR
  def this() = this(1, ItemData("Ghast Tear"))
}

final case class Glass(
    color: Option[BlockColor],
    stackSize: Int,
    data: ItemData
) extends ColorableItem
    with StackableItem {
  val `type` = ItemType.GLASS
  def this(color: Option[BlockColor]) =
    this(color, 1, ItemData(color, "Glass"))
}

final case class GlassBottle(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GLASS_BOTTLE
  def this() = this(1, ItemData("Glass Bottle"))
}

final case class GlassPane(
    color: Option[BlockColor],
    stackSize: Int,
    data: ItemData
) extends ColorableItem
    with StackableItem {
  val `type` = ItemType.GLASS_PANE
  def this(color: Option[BlockColor]) =
    this(color, 1, ItemData(color, "Glass Pane"))
}

final case class GlazedTerracotta(
    color: BlockColor,
    stackSize: Int,
    data: ItemData
) extends ColoredItem
    with StackableItem {
  val `type` = ItemType.GLAZED_TERRACOTTA
  def this(color: BlockColor) =
    this(color, 1, ItemData(color, "Glazed Terracotta"))
}

final case class Glowstone(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GLOWSTONE
  def this() = this(1, ItemData("Glowstone"))
}

final case class GlowstoneDust(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GLOWSTONE_DUST
  def this() = this(1, ItemData("Glowstone Dust"))
}

final case class GoldBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GOLD_BLOCK
  def this() = this(1, ItemData("Gold Block"))
}

final case class GoldIngot(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GOLD_INGOT
  def this() = this(1, ItemData("Gold Ingot"))
}

final case class GoldNugget(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GOLD_NUGGET
  def this() = this(1, ItemData("Gold Nugget"))
}

final case class GoldOre(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GOLD_ORE
  def this() = this(1, ItemData("Gold Ore"))
}

final case class GoldenApple(
    enchanted: Boolean,
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GOLDEN_APPLE
  override val edible = true
  def this() =
    this(enchanted, 1, ItemData(enchanted, "Enchanted", "Golden Apple"))
}

final case class GoldenCarrot(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GOLDEN_CARROT
  override val edible = true
  def this() = this(1, ItemData("Golden Carrot"))
}

final case class Granite(
    variant: StoniteVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[StoniteVariant]
    with StackableItem {
  val `type` = ItemType.GRANITE
  def this(variant: StoniteVariant) =
    this(variant, 1, ItemData(variant, "Granite"))
}

final case class Grass(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GRASS
  def this() = this(1, ItemData("Grass"))
}

final case class GrassBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GRASS_BLOCK
  def this() = this(1, ItemData("Grass Block"))
}

final case class Grindstone(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GRINDSTONE
  def this() = this(1, ItemData("Grindstone"))
}

final case class GrassPath(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GRASS_PATH
  def this() = this(1, ItemData("Grass Path"))
}

final case class Gravel(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GRAVEL
  def this() = this(1, ItemData("Gravel"))
}

final case class Gunpowder(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.GUNPOWDER
  def this() = this(1, ItemData("Gunpowder"))
}

final case class HayBale(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.HAY_BALE
  def this() = this(1, ItemData("Hay Bale"))
}

final case class HeartOfTheSea(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.HEART_OF_THE_SEA
  def this() = this(1, ItemData("Heart of The Sea"))
}

final case class Hopper(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.HOPPER
  def this() = this(1, ItemData("Hopper"))
}

final case class Ice(
    variant: IceVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[IceVariant]
    with StackableItem {
  val `type` = ItemType.ICE
  def this(variant: IceVariant) =
    this(variant, 1, ItemData(variant, "Ice"))
}

final case class InfestedBlock(
    material: InfestedMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[InfestedMaterial]
    with StackableItem {
  val `type` = ItemType.INFESTED_BLOCK
  def this(material: InfestedMaterial) =
    this(material, 1, ItemData(material, "Infested Block")) // TODO fix naming order
}

final case class InkSac(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.INK_SAC
  def this() = this(1, ItemData("Ink Sac"))
}

final case class IronBars(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.IRON_BARS
  def this() = this(1, ItemData("Iron Bars"))
}

final case class IronBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.IRON_BLOCK
  def this() = this(1, ItemData("Iron Block"))
}

final case class IronIngot(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.IRON_INGOT
  def this() = this(1, ItemData("Iron Ingot"))
}

final case class IronNugget(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.IRON_NUGGET
  def this() = this(1, ItemData("Iron Nugget"))
}

final case class IronOre(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.IRON_ORE
  def this() = this(1, ItemData("Iron Ore"))
}

final case class ItemFrame(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.ITEM_FRAME
  def this() = this(1, ItemData("Item Frame"))
}

final case class JigsawBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.JIGSAW_BLOCK
  def this() = this(1, ItemData("Jigsaw Block"))
}

final case class Jukebox(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.JUKEBOX
  def this() = this(1, ItemData("Jukebox"))
}

final case class Kelp(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.KELP
  def this() = this(1, ItemData("Kelp"))
}

final case class Ladder(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.LADDER
  def this() = this(1, ItemData("Ladder"))
}

final case class Lantern(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.LANTERN
  def this() = this(1, ItemData("Lantern"))
}

final case class Lapis(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.LAPIS
  def this() = this(1, ItemData("Lapis"))
}

final case class LapisBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.LAPIS_BLOCK
  def this() = this(1, ItemData("Lapis Block"))
}

final case class LapisOre(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.LAPIS_ORE
  def this() = this(1, ItemData("Lapis Ore"))
}

final case class Lead(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.LEAD
  def this() = this(1, ItemData("Lead"))
}

final case class Leather(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.LEATHER
  def this() = this(1, ItemData("Leather"))
}

final case class Leaves(
    material: WoodMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[WoodMaterial]
    with StackableItem {
  val `type` = ItemType.LEAVES
  def this(material: WoodMaterial) =
    this(material, 1, ItemData(material, "Leaves"))
}

final case class Lectern(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.LECTERN
  def this() = this(1, ItemData("Lectern"))
}

final case class Lever(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.LEVER
  def this() = this(1, ItemData("Lever"))
}

final case class LilyPad(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.LILY_PAD
  def this() = this(1, ItemData("Lily Pad"))
}

final case class Log(
    material: WoodMaterial,
    stripped: Boolean,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[WoodMaterial]
    with StackableItem {
  val `type` = ItemType.LOG
  def this(material: WoodMaterial, stripped: Boolean) =
    this(material, stripped, 1, ItemData(material, "Log"))
  // this(material, stripped, 1, ItemData(material, stripped, "Stripped", "Log")) // TODO
}

final case class Loom(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.LOOM
  def this() = this(1, ItemData("Loom"))
}

final case class MagmaBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.MAGMA_BLOCK
  def this() = this(1, ItemData("Magma Block"))
}

final case class MagmaCream(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.MAGMA_CREAM
  def this() = this(1, ItemData("Magma Cream"))
}

final case class Minecart(
    variant: MinecartVariant,
    data: ItemData
) extends VariedItem[MinecartVariant] {
  val `type` = ItemType.MINECART
  def this(variant: MinecartVariant) =
    this(variant, ItemData(variant, "Minecart"))
}

final case class MobHead(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.MOB_HEAD
  def this() = this(1, ItemData("Mob Head")) // TODO correct naming
}

final case class Mushroom(
    variant: MushroomVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[MushroomVariant]
    with StackableItem {
  val `type` = ItemType.MUSHROOM
  def this(variant: MushroomVariant) =
    this(variant, 1, ItemData(variant, "Mushroom"))
}

final case class MushroomBlock(
    variant: MushroomBlockVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[MushroomBlockVariant]
    with StackableItem {
  val `type` = ItemType.MUSHROOM_BLOCK
  def this(variant: MushroomBlockVariant) =
    this(variant, 1, ItemData("Mushroom Block"))
}

final case class MusicDisc(
    variant: MusicDiscVariant,
    data: ItemData
) extends VariedItem[MusicDiscVariant] {
  val `type` = ItemType.MUSIC_DISC
  def this(variant: MusicDiscVariant) =
    this(variant, ItemData(variant, "Music Disc"))
}

final case class NameTag(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.NAME_TAG
  def this() = this(1, ItemData("Name Tag"))
}

final case class NautilusShell(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.NAUTILUS_SHELL
  def this() = this(1, ItemData("Nautilus Shell"))
}

final case class Netherrack(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.NETHERRACK
  def this() = this(1, ItemData("Netherrack"))
}

final case class NetherStar(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.NETHER_STAR
  def this() = this(1, ItemData("Nether Star"))
}

final case class NoteBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.NOTE_BLOCK
  def this() = this(1, ItemData("Note Block"))
}

final case class Observer(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.OBSERVER
  def this() = this(1, ItemData("Observer"))
}

final case class Obsidian(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.OBSIDIAN
  def this() = this(1, ItemData("Obsidian"))
}

final case class Painting(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.PAINTING
  def this() = this(1, ItemData("Painting"))
}

final case class Paper(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.PAPER
  def this() = this(1, ItemData("Paper"))
}

final case class PhantomMembrane(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.PHANTOM_MEMBRANE
  def this() = this(1, ItemData("Phantom Membrane"))
}

final case class Planks(
    material: WoodMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[WoodMaterial]
    with StackableItem {
  val `type` = ItemType.PLANKS
  def this(material: WoodMaterial) =
    this(material, 1, ItemData(material, "Planks"))
}

final case class Pillar(
    material: PillarMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[PillarMaterial]
    with StackableItem {
  val `type` = ItemType.PILLAR
  def this(material: PillarMaterial) =
    this(material, 1, ItemData(material, "Pillar"))
}

final case class PressurePlate(
    material: PressurePlateMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[PressurePlateMaterial]
    with StackableItem {
  val `type` = ItemType.PRESSURE_PLATE
  def this(material: WoodMaterial) =
    this(material, 1, ItemData(material, "Pressure Plate"))
}

final case class Prismarine(
    material: PrismarineMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[PrismarineMaterial]
    with StackableItem {
  val `type` = ItemType.PRISMARINE
  def this(material: PrismarineMaterial) =
    this(material, 1, ItemData(material, "Prismarine"))
}

final case class PrismarineCrystals(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.PRISMARINE_CRYSTALS
  def this() = this(1, ItemData("Prismarine Crystals"))
}

final case class PrismarineShard(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.PRISMARINE_SHARD
  def this() = this(1, ItemData("Prismarine Shard"))
}

final case class Purpur(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.PURPUR
  def this() = this(1, ItemData("Purpur"))
}

final case class Quartz(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.QUARTZ
  def this() = this(1, ItemData("Quartz")) // TODO Nether Quartz?
}

final case class QuartzBlock(
    variant: QuartzVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[QuartzVariant]
    with StackableItem {
  val `type` = ItemType.QUARTZ_BLOCK
  def this(variant: QuartzVariant) =
    this(variant, 1, ItemData(variant, "Quartz Block"))
}

final case class QuartzOre(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.QUARTZ_ORE
  def this() = this(1, ItemData("Quartz Ore"))
}

final case class RabbitFoot(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.RABBIT_FOOT
  def this() = this(1, ItemData("Rabbit Foot"))
}

final case class RabbitHide(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.RABBIT_HIDE
  def this() = this(1, ItemData("Rabbit Hide"))
}

final case class Rails(
    variant: RailsVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[RailsVariant]
    with StackableItem {
  val `type` = ItemType.RAILS
  def this(variant: RailsVariant) =
    this(variant, 1, ItemData(variant, "Rail"))
}

final case class Repeater(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.REPEATER
  def this() = this(1, ItemData("Repeater"))
}

final case class Redstone(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.REDSTONE
  def this() = this(1, ItemData("Redstone"))
}

final case class RedstoneBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.REDSTONE_BLOCK
  def this() = this(1, ItemData("Redstone Block"))
}

final case class RedstoneOre(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.REDSTONE_ORE
  def this() = this(1, ItemData("Redstone Ore"))
}

final case class RottenFlesh(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.ROTTEN_FLESH
  def this() = this(1, ItemData("Rotten Flesh"))
}

final case class Saddle(data: ItemData) extends Item {
  val `type` = ItemType.SADDLE
  def this() = this(ItemData("Saddle"))
}

final case class Sand(
    material: SandMaterial, // TODO should this be a variant?
    stackSize: Int,
    data: ItemData
) extends MaterialItem[SandMaterial]
    with StackableItem {
  val `type` = ItemType.SAND
  def this(material: SandMaterial) =
    this(material, 1, ItemData(material, "Sand"))
}

final case class Sandstone(
    material: SandstoneMaterial,
    variant: SandstoneVariant,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[SandstoneMaterial]
    with VariedItem[SandstoneVariant]
    with StackableItem {
  def this(material: SandstoneMaterial, variant: SandstoneVariant) =
    this(material, variant, 1, ItemData(material, variant, "Sandstone"))
}

final case class Sapling(
    variant: SaplingVariant, // TODO would kinda like this to be material again
    stackSize: Int,
    data: ItemData
) extends VariedItem[SaplingVariant]
    with StackableItem {
  val `type` = ItemType.SAPLING
  def this(variant: SaplingVariant) =
    this(variant, 1, ItemData(variant, "Sapling"))
}

final case class Scaffolding(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SCAFFOLDING
  def this() = this(1, ItemData("Scaffolding"))
}

final case class Scute(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SCUTE
  def this() = this(1, ItemData("Scute"))
}

final case class SeaLantern(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SEA_LANTERN
  def this() = this(1, ItemData("Sea Lantern"))
}

final case class SeaPickle(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SEA_PICKLE
  def this() = this(1, ItemData("Sea Pickle"))
}

final case class Shears(data: ItemData) extends Item {
  val `type` = ItemType.SHEARS
  def this() = this(ItemData("Shears"))
}

final case class Shield(data: ItemData) extends Item {
  val `type` = ItemType.SHIELD
  def this() = this(ItemData("Shield"))
}

final case class ShulkerBox(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.SHULKER_BOX
  def this(color: Option[BlockColor]) =
    this(color, ItemData(color, "Shulker Box"))
}

final case class ShulkerShell(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SHULKER_SHELL
  def this() = this(1, ItemData("Shulker Shell"))
}

final case class Sign(
    material: WoodMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[WoodMaterial]
    with StackableItem {
  val `type` = ItemType.SIGN
  override val maxStackSize = 16
  def this(material: WoodMaterial) =
    this(material, 1, ItemData(material, "Sign"))
}

final case class Slab(
    material: SlabMaterial,
    variant: Option[SlabVariant],
    stackSize: Int,
    data: ItemData
) extends MaterialItem[SlabMaterial]
    with VariableItem[SlabVariant]
    with StackableItem {
  val `type` = ItemType.SLAB
  def this(material: SlabMaterial, variant: Option[SlabVariant]) =
    this(material, variant, 1, ItemData(material, variant, "Slab"))
}

final case class Slimeball(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SLIMEBALL
  def this() = this(1, ItemData("Slimeball"))
}

final case class SlimeBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SLIME_BLOCK
  def this() = this(1, ItemData("Slime Block"))
}

final case class SmithingTable(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SMITHING_TABLE
  def this() = this(1, ItemData("Smithing Table"))
}

final case class Smoker(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SMOKER
  def this() = this(1, ItemData("Smoker"))
}

final case class Snow(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SNOW
  def this() = this(1, ItemData("Snow"))
}

final case class Snowball(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SNOWBALL
  override val maxStackSize = 16
  def this() = this(1, ItemData("Snowball"))
}

final case class SnowBlock(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SNOW_BLOCK
  def this() = this(1, ItemData("Snow Block"))
}

final case class SoulSand(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SOUL_SAND
  def this() = this(1, ItemData("Soul Sand"))
}

final case class SpawnEgg(
    variant: SpawnEggVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[SpawnEggVariant]
    with StackableItem {
  val `type` = ItemType.SPAWN_EGG // TODO MonsterEgg?
  def this(variant: SpawnEggVariant) =
    this(variant, 1, ItemData(variant, "Spawn Egg"))
}

final case class Spawner(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SPAWNER
  def this() = this(1, ItemData("Spawner"))
}

final case class SpiderEye(
    fermented: Boolean,
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SPIDER_EYE
  def this(fermented: Boolean) =
    this(fermented, 1, ItemData(fermented, "Fermented", "Spider Eye"))
}

final case class Sponge(
    wet: Boolean,
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SPONGE
  def this(wet: Boolean) =
    this(wet, 1, ItemData(wet, "Wet", "Sponge"))
}

final case class Stairs(
    material: StairsMaterial,
    variant: Option[StairsVariant],
    stackSize: Int,
    data: ItemData
) extends MaterialItem[StairsMaterial]
    with VariableItem[StairsVariant]
    with StackableItem {
  val `type` = ItemType.SLAB
  def this(material: StairsMaterial, variant: Option[StairsVariant]) =
    this(material, variant, 1, ItemData(material, variant, "Slab"))
}

final case class Stick(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.STICK
  def this() = this(1, ItemData("Stick"))
}

final case class Stone(
    material: StoneMaterial,
    variant: StoneVariant,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[StoneMaterial]
    with VariedItem[StoneVariant]
    with StackableItem {
  val `type` = ItemType.STONE
  def this(material: StoneMaterial, variant: StoneVariant) =
    this(material, variant, 1, ItemData(material, variant, "Stone"))
}

final case class Stonecutter(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.STONECUTTER
  def this() = this(1, ItemData("Stonecutter"))
}

final case class PieceOfString(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.STRING
  def this() = this(1, ItemData("String"))
}

final case class Sugar(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SUGAR
  def this() = this(1, ItemData("Sugar"))
}

final case class SugarCane(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.SUGAR_CANE
  def this() = this(1, ItemData("Sugar Cane"))
}

final case class Terracotta(
    color: Option[BlockColor],
    stackSize: Int,
    data: ItemData
) extends ColorableItem
    with StackableItem {
  val `type` = ItemType.TERRACOTTA
  def this(color: Option[BlockColor]) =
    this(color, 1, ItemData(color, "Terracotta"))
}

final case class TNT(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.TNT
  def this() = this(1, ItemData("TNT"))
}

final case class Torch(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.TORCH
  def this() = this(1, ItemData("Torch"))
}

final case class TotemOfUndying(data: ItemData) extends Item {
  val `type` = ItemType.TOTEM_OF_UNDYING
  def this() = this(ItemData("Totem of Undying"))
}

final case class TrapDoor(
    material: TrapdoorMaterial,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[TrapdoorMaterial]
    with StackableItem {
  val `type` = ItemType.TRAPDOOR
  def this(material: TrapdoorMaterial) =
    this(material, 1, ItemData(material, "Trap Door"))
}

final case class Trident(data: ItemData) extends Item {
  val `type` = ItemType.TRIDENT
  def this() = this(ItemData("Trident"))
}

final case class TripwireHook(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.TRIPWIRE_HOOK
  def this() = this(1, ItemData("Tripwire Hook"))
}

final case class TurtleEgg(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.TURTLE_EGG
  def this() = this(1, ItemData("Turtle Egg"))
}

final case class TurtleHelmet(data: ItemData) extends Item {
  val `type` = ItemType.TURTLE_HELMET
  def this() = this(ItemData("Turtle Helmet"))
}

final case class Vine(
    stackSize: Int,
    data: ItemData
) extends StackableItem {
  val `type` = ItemType.VINE
  def this() = this(1, ItemData("Vine"))
}

final case class Wall(
    material: WallMaterial,
    variant: Option[WallVariant],
    stackSize: Int,
    data: ItemData
) extends MaterialItem[WallMaterial]
    with VariableItem[WallVariant]
    with StackableItem {
  val `type` = ItemType.WALL
  def this(material: WallMaterial, variant: Option[WallVariant]) =
    this(material, variant, 1, ItemData(material, variant, "Wall"))
}

final case class WeightedPressurePlate(
    variant: WeightedPressurePlateVariant,
    stackSize: Int,
    data: ItemData
) extends VariedItem[WeightedPressurePlateVariant]
    with StackableItem {
  val `type` = ItemType.WEIGHTED_PRESSURE_PLATE
  def this(variant: WeightedPressurePlateVariant) =
    this(variant, 1, ItemData(variant, "Weighted Pressure Plate"))
}

final case class Wood(
    material: WoodMaterial,
    stripped: Boolean,
    stackSize: Int,
    data: ItemData
) extends MaterialItem[WoodMaterial]
    with StackableItem {
  val `type` = ItemType.WOOD
  def this(material: WoodMaterial, stripped: Boolean) =
    this(material, stripped, 1, ItemData(material, "Wood"))
  // this(material, stripped, 1, ItemData(material, stripped, "Stripped", "Wood"))
}

final case class Wool(
    color: BlockColor,
    stackSize: Int,
    data: ItemData
) extends ColoredItem
    with StackableItem {
  val `type` = ItemType.WOOL
  def this(color: BlockColor) =
    this(color, 1, ItemData(color, "Wool"))
}
