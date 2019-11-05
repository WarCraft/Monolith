package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.world.block.variant._
import gg.warcraft.monolith.api.world.item.variant.{StructureBlockVariant, _}

case class Andesite(
    variant: AndesiteVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[AndesiteVariant]
    with StackableItem {
  val `type` = ItemType.ANDESITE
}

case class Anvil(
    variant: AnvilVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[AnvilVariant]
    with StackableItem {
  val `type` = ItemType.ANVIL
}

case class Apple(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.APPLE
  override val edible = true
}

case class ArmorStand(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.ARMOR_STAND
  override def maxCount = 16
}

case class Arrow(
    variant: ArrowVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[ArrowVariant]
    with StackableItem {
  val `type` = ItemType.ARROW
}

case class Axe(
    variant: AxeVariant,
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends VariableItem[AxeVariant]
    with DurableItem {
  val `type` = ItemType.AXE
  override def maxDurability: Int = variant match {
    case AxeVariant.WOOD    => 59
    case AxeVariant.STONE   => 131
    case AxeVariant.IRON    => 250
    case AxeVariant.GOLD    => 32
    case AxeVariant.DIAMOND => 1561
  }
}

case class Bamboo(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BAMBOO
}

case class Banner(
    variant: BannerVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[BannerVariant]
    with StackableItem {
  val `type` = ItemType.BANNER
  override def maxCount = 16
}

case class BannerPattern(
    variant: BannerPatternVariant,
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[BannerPatternVariant] {
  val `type` = ItemType.BANNER_PATTERN
}

case class Barrel(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BARREL
}

case class Barrier(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BARRIER
}

case class Beacon(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BEACON
}

case class Bed(
    variant: BedVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[BedVariant]
    with StackableItem {
  val `type` = ItemType.BED
}

case class Bedrock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BEDROCK
}

case class Beef(
    variant: BeefVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[BeefVariant]
    with StackableItem {
  val `type` = ItemType.BEEF
  override val edible = true
}

case class Beetroot(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BEETROOT
  override val edible = true
}

case class Bell(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BELL
}

case class BlastFurnace(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BLAST_FURNACE
}

case class BlazePowder(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BLAZE_POWDER
}

case class BlazeRod(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BLAZE_ROD
}

case class Boat(
    variant: BoatVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[BoatVariant]
    with StackableItem {
  val `type` = ItemType.BOAT
}

case class Bone(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BONE
}

case class BoneBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BONE_BLOCK
}

case class BoneMeal(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BONE_MEAL
}

case class BottleOfEnchanting(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BOTTLE_OF_ENCHANTING
}

case class Book(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BOOK
}

case class BookAndQuill(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean
) extends Item {
  val `type` = ItemType.BOOK_AND_QUILL
}

case class Bookshelf(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BOOKSHELF
}

case class Boots(
    variant: BootsVariant,
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends VariableItem[BootsVariant]
    with DurableItem {
  val `type` = ItemType.BOOTS
  override def maxDurability: Int = variant match {
    case BootsVariant.LEATHER   => 65
    case BootsVariant.CHAINMAIL => 195
    case BootsVariant.IRON      => 195
    case BootsVariant.GOLD      => 91
    case BootsVariant.DIAMOND   => 429
  }
}

case class Bow(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends DurableItem {
  val `type` = ItemType.BOW
  override def maxDurability = 384
}

case class Bowl(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BOWL
}

case class Bread(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BREAD
  override val edible = true
}

case class BrewingStand(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.BREWING_STAND
}

case class Brick(
    variant: BrickVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[BrickVariant]
    with StackableItem {
  val `type` = ItemType.BRICK
}

case class BrickBlock(
    variant: BrickBlockVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[BrickBlockVariant]
    with StackableItem {
  val `type` = ItemType.BRICK_BLOCK
}

case class Bucket(
    variant: BucketVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[BucketVariant]
    with StackableItem {
  val `type` = ItemType.BUCKET
  override def maxCount = 16
}

case class Button(
    variant: ButtonVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[ButtonVariant]
    with StackableItem {
  val `type` = ItemType.BUTTON
}

case class Cactus(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CACTUS
}

case class Cake(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean
) extends Item {
  val `type` = ItemType.CAKE
}

case class Campfire(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CAMPFIRE
}

case class Carpet(
    variant: CarpetVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[CarpetVariant]
    with StackableItem {
  val `type` = ItemType.CARPET
}

case class Carrot(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CARROT
  override val edible = true
}

case class CarrotOnAStick(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends DurableItem {
  val `type` = ItemType.CARROT_ON_A_STICK
  override def maxDurability = 25
}

case class CartographyTable(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CARTOGRAPHY_TABLE
}

case class Charcoal(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CHARCOAL
}

case class Cauldron(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CAULDRON
}

case class Chest(
    variant: ChestVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[ChestVariant]
    with StackableItem {
  val `type` = ItemType.CHEST
}

case class Chestplate(
    variant: ChestplateVariant,
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends VariableItem[ChestplateVariant]
    with DurableItem {
  val `type` = ItemType.CHESTPLATE
  override def maxDurability: Int = variant match {
    case ChestplateVariant.LEATHER   => 80
    case ChestplateVariant.CHAINMAIL => 240
    case ChestplateVariant.IRON      => 240
    case ChestplateVariant.GOLD      => 112
    case ChestplateVariant.DIAMOND   => 528
  }
}

case class Chicken(
    variant: ChickenVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[ChickenVariant]
    with StackableItem {
  val `type` = ItemType.CHICKEN
  override val edible = true
}

case class ChorusFlower(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CHORUS_FLOWER
}

case class ChorusFruit(
    variant: ChorusFruitVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[ChorusFruitVariant]
    with StackableItem {
  val `type` = ItemType.CHORUS_FRUIT
  override val edible: Boolean =
    variant == ChorusFruitVariant.NORMAL
}

case class ChorusPlant(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CHORUS_PLANT
}

case class Clay(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CLAY
}

case class ClayBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CLAY_BLOCK
}

case class Clock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CLOCK
}

case class Coal(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.COAL
}

case class CoalBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.COAL_BLOCK
}

case class CoalOre(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.COAL_ORE
}

case class Cobblestone(
    variant: CobblestoneVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[CobblestoneVariant]
    with StackableItem {
  val `type` = ItemType.COBBLESTONE
}

case class Cobweb(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.COBWEB
}

case class CocoaBeans(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.COCOA_BEANS
}

case class Cod(
    variant: CodVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[CodVariant]
    with StackableItem {
  val `type` = ItemType.CHICKEN
  override val edible = true
}

case class CommandBlock(
    variant: CommandBlockVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[CommandBlockVariant]
    with StackableItem {
  val `type` = ItemType.COMMAND_BLOCK
}

case class Comparator(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.COMPARATOR
}

case class Compass(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.COMPASS
}

case class Composter(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.COMPOSTER
}

case class Concrete(
    variant: ConcreteVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[ConcreteVariant]
    with StackableItem {
  val `type` = ItemType.CONCRETE
}

case class ConcretePowder(
    variant: ConcretePowderVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[ConcretePowderVariant]
    with StackableItem {
  val `type` = ItemType.CONCRETE_POWDER
}

case class Conduit(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CONDUIT
}

case class Cookie(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.COOKIE
  override val edible = true
}

case class Coral(
    variant: CoralVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[CoralVariant]
    with StackableItem {
  val `type` = ItemType.CORAL
}

case class CoralBlock(
    variant: CoralBlockVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[CoralBlockVariant]
    with StackableItem {
  val `type` = ItemType.CORAL_BLOCK
}

case class CoralFan(
    variant: CoralFanVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[CoralFanVariant]
    with StackableItem {
  val `type` = ItemType.CORAL_FAN
}

case class CraftingTable(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.CRAFTING_TABLE
}

case class Crossbow(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends DurableItem {
  val `type` = ItemType.CROSSBOW
  override def maxDurability = 326
}

case class DaylightDetector(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.DAYLIGHT_DETECTOR
}

case class DeadBush(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.DEAD_BUSH
}

case class DebugStick(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean
) extends Item {
  val `type` = ItemType.DEBUG_STICK
}

case class Diamond(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.DIAMOND
}

case class DiamondBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.DIAMOND_BLOCK
}

case class DiamondOre(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.DIAMOND_ORE
}

case class Diorite(
    variant: DioriteVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[DioriteVariant]
    with StackableItem {
  val `type` = ItemType.DIORITE
}

case class Dirt(
    variant: DirtVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[DirtVariant]
    with StackableItem {
  val `type` = ItemType.DIRT
}

case class Dispenser(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.DISPENSER
}

case class Door(
    variant: DoorVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[DoorVariant]
    with StackableItem {
  val `type` = ItemType.DOOR
}

case class DragonBreath(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.DRAGON_BREATH
}

case class DragonEgg(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.DRAGON_EGG
}

case class DriedKelp(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.DRIED_KELP
  override val edible = true
}

case class DriedKelpBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.DRIED_KELP_BLOCK
}

case class Dropper(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.DROPPER
}

case class Dye(
    variant: DyeVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[DyeVariant]
    with StackableItem {
  val `type` = ItemType.DYE
}

case class Egg(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.EGG
}

case class Elytra(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends DurableItem {
  val `type` = ItemType.ELYTRA
  override def maxDurability = 432
}

case class Emerald(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.EMERALD
}

case class EmeraldBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.EMERALD_BLOCK
}

case class EmeraldOre(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.EMERALD_ORE
}

case class EnchantedBook(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean
) extends Item {
  val `type` = ItemType.ENCHANTED_BOOK
}

case class EnchantingTable(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.ENCHANTING_TABLE
}

case class EndCrystal(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.END_CRYSTAL
}

case class EndPortalFrame(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.END_PORTAL_FRAME
}

case class EndRod(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.END_ROD
}

case class EndStone(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.END_STONE
}

case class EndStoneBrick(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.END_STONE
}

case class EnderEye(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.ENDER_EYE
}

case class EnderPearl(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.ENDER_PEARL
  override def maxCount = 16
}

case class Farmland(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.FARMLAND
}

case class Feather(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.FEATHER
}

case class Fence(
    variant: FenceVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[FenceVariant]
    with StackableItem {
  val `type` = ItemType.FENCE
}

case class Fern(
    variant: FernVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[FernVariant]
    with StackableItem {
  val `type` = ItemType.FERN
}

case class FireCharge(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.FIRE_CHARGE
}

case class FireworkRocket(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.FIREWORK_ROCKET
}

case class FireworkStar(
    variant: FireworkStarVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[FireworkStarVariant]
    with StackableItem {
  val `type` = ItemType.FIREWORK_STAR
}

case class FishingRod(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends DurableItem {
  val `type` = ItemType.FISHING_ROD
  override def maxDurability = 64
}

case class FletchingTable(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.FLETCHING_TABLE
}

case class Flint(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.FLINT
}

case class FlintAndSteel(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends DurableItem {
  val `type` = ItemType.FLINT_AND_STEEL
  override def maxDurability = 64
}

case class Flower(
    variant: FlowerVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[FlowerVariant]
    with StackableItem {
  val `type` = ItemType.FLOWER
}

case class FlowerPot(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.FLOWER_POT
}

case class Furnace(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.FURNACE
}

case class FenceGate(
    variant: FenceGateVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[FenceGateVariant]
    with StackableItem {
  val `type` = ItemType.FENCE_GATE
}

case class GhastTear(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GHAST_TEAR
}

case class Glass(
    variant: GlassVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[GlassVariant]
    with StackableItem {
  val `type` = ItemType.GLASS
}

case class GlassBottle(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GLASS_BOTTLE
}

case class GlassPane(
    variant: GlassPaneVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[GlassPaneVariant]
    with StackableItem {
  val `type` = ItemType.GLASS_PANE
}

case class GlazedTerracotta(
    variant: GlazedTerracottaVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[GlazedTerracottaVariant]
    with StackableItem {
  val `type` = ItemType.GLAZED_TERRACOTTA
}

case class Glowstone(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GLOWSTONE
}

case class GlowstoneDust(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GLOWSTONE_DUST
}

case class GoldBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GOLD_BLOCK
}

case class GoldIngot(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GOLD_INGOT
}

case class GoldNugget(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GOLD_NUGGET
}

case class GoldOre(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GOLD_ORE
}

case class GoldenApple(
    enchanted: Boolean,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends Item
    with StackableItem {
  val `type` = ItemType.GOLDEN_APPLE
  override val edible = true
  def withEnchanted(enchanted: Boolean): GoldenApple =
    copyWith("enchanted", enchanted)
}

case class GoldenCarrot(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GOLDEN_CARROT
  override val edible = true
}

case class GoldenMelonSlice(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GOLDEN_MELON_SLICE
}

case class Granite(
    variant: GraniteVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[GraniteVariant]
    with StackableItem {
  val `type` = ItemType.GRANITE
}

case class Grass(
    variant: GrassVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[GrassVariant]
    with StackableItem {
  val `type` = ItemType.GRASS
}

case class GrassBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GRASS_BLOCK
}

case class GrassPath(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GRASS_PATH
}

case class Gravel(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GRAVEL
}

case class Grindstone(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GRINDSTONE
}

case class Gunpowder(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.GUNPOWDER
}

case class HayBale(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.HAY_BALE
}

case class HeartOfTheSea(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.HEART_OF_THE_SEA
}

case class Helmet(
    variant: HelmetVariant,
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends VariableItem[HelmetVariant]
    with DurableItem {
  val `type` = ItemType.HELMET
  override def maxDurability: Int = variant match {
    case HelmetVariant.LEATHER   => 55
    case HelmetVariant.CHAINMAIL => 165
    case HelmetVariant.IRON      => 165
    case HelmetVariant.GOLD      => 77
    case HelmetVariant.DIAMOND   => 363
  }
}

case class Hoe(
    variant: HoeVariant,
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends VariableItem[HoeVariant]
    with DurableItem {
  val `type` = ItemType.HOE
  override def maxDurability: Int = variant match {
    case HoeVariant.WOOD    => 59
    case HoeVariant.STONE   => 131
    case HoeVariant.IRON    => 250
    case HoeVariant.GOLD    => 32
    case HoeVariant.DIAMOND => 1561
  }
}

case class Hopper(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.HOPPER
}

case class HorseArmor(
    variant: HorseArmorVariant,
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[HorseArmorVariant] {
  val `type` = ItemType.HORSE_ARMOR
}

case class Ice(
    variant: IceVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[IceVariant]
    with StackableItem {
  val `type` = ItemType.ICE
}

case class InfestedBlock(
    variant: InfestedBlockVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[InfestedBlockVariant]
    with StackableItem {
  val `type` = ItemType.INFESTED_BLOCK
}

case class InkSac(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.INK_SAC
}

case class IronBars(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.IRON_BARS
}

case class IronBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.IRON_BLOCK
}

case class IronIngot(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.IRON_INGOT
}

case class IronNugget(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.IRON_NUGGET
}

case class IronOre(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.IRON_ORE
}

case class ItemFrame(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.ITEM_FRAME
}

case class JackOfTheLantern(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.JACK_OF_THE_LANTERN
}

case class JigsawBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.JIGSAW_BLOCK
}

case class Jukebox(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.JUKEBOX
}

case class Kelp(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.KELP
}

case class KnowledgeBook(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean
) extends Item {
  val `type` = ItemType.KNOWLEDGE_BOOK
}

case class Ladder(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.LADDER
}

case class Lantern(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.LANTERN
}

case class Lapis(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.LAPIS
}

case class LapisBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.LAPIS_BLOCK
}

case class LapisOre(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.LAPIS_ORE
}

case class Lead(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.LEAD
}

case class Leather(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.LEATHER
}

case class Leaves(
    variant: LeavesVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[LeavesVariant]
    with StackableItem {
  val `type` = ItemType.LEAVES
}

case class Lectern(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.LECTERN
}

case class Leggings(
    variant: LeggingsVariant,
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends VariableItem[LeggingsVariant]
    with DurableItem {
  val `type` = ItemType.LEGGINGS
  override def maxDurability: Int = variant match {
    case LeggingsVariant.LEATHER   => 75
    case LeggingsVariant.CHAINMAIL => 225
    case LeggingsVariant.IRON      => 225
    case LeggingsVariant.GOLD      => 105
    case LeggingsVariant.DIAMOND   => 495
  }
}

case class Lever(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.LEVER
}

case class LilyPad(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.LILY_PAD
}

case class Log(
    variant: LogVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[LogVariant]
    with StackableItem {
  val `type` = ItemType.LOG
}

case class Loom(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.LOOM
}

case class MagmaBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.MAGMA_BLOCK
}

case class MagmaCream(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.MAGMA_CREAM
}

case class Map(
    filled: Boolean,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.MAP
  def withFilled(filled: Boolean): Map =
    copyWith("filled", filled)
}

case class Melon(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.MELON
}

case class MelonSlice(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.MELON_SLICE
  override val edible = true
}

case class Minecart(
    variant: MinecartVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[MinecartVariant]
    with StackableItem {
  val `type` = ItemType.MINECART
}

// TODO add separate playerSkull item with name String
case class MobHead(
    variant: MobHeadVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[MobHeadVariant]
    with StackableItem {
  val `type` = ItemType.MOB_HEAD
}

case class Mushroom(
    variant: MushroomVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[MushroomVariant]
    with StackableItem {
  val `type` = ItemType.MUSHROOM
}

case class MushroomBlock(
    variant: MushroomBlockVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[MushroomBlockVariant]
    with StackableItem {
  val `type` = ItemType.MUSHROOM_BLOCK
}

case class MusicDisc(
    variant: MusicDiscVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[MusicDiscVariant]
    with StackableItem {
  val `type` = ItemType.MUSIC_DISC
}

case class Mutton(
    variant: MuttonVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[MuttonVariant]
    with StackableItem {
  val `type` = ItemType.MUTTON
  override val edible = true
}

case class Mycelium(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.MYCELIUM
}

case class NameTag(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.NAME_TAG
}

case class NautilusShell(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.NAUTILUS_SHELL
}

case class Netherrack(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.NETHERRACK
}

case class NetherStar(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.NETHER_STAR
}

case class NetherWart(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.NETHER_WART
}

case class NetherWartBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.NETHER_WART_BLOCK
}

case class NoteBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.NOTE_BLOCK
}

case class Observer(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.OBSERVER
}

case class Obsidian(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.OBSIDIAN
}

case class Painting(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.PAINTING
}

case class Paper(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.PAPER
}

case class PhantomMembrane(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.PHANTOM_MEMBRANE
}

case class Plant(
    variant: PlantVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[PlantVariant]
    with StackableItem {
  val `type` = ItemType.PLANT
}

case class Pickaxe(
    variant: PickaxeVariant,
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends VariableItem[PickaxeVariant]
    with DurableItem {
  val `type` = ItemType.PICKAXE
  override def maxDurability: Int = variant match {
    case PickaxeVariant.WOOD    => 59
    case PickaxeVariant.STONE   => 131
    case PickaxeVariant.IRON    => 250
    case PickaxeVariant.GOLD    => 32
    case PickaxeVariant.DIAMOND => 1561
  }
}

case class Pillar(
    variant: PillarVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[PillarVariant]
    with StackableItem {
  val `type` = ItemType.PILLAR
}

case class Piston(
    variant: PistonVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[PistonVariant]
    with StackableItem {
  val `type` = ItemType.PISTON
  def withSticky(sticky: Boolean): Piston =
    copyWith("sticky", sticky)
}

case class Planks(
    variant: PlanksVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[PlanksVariant]
    with StackableItem {
  val `type` = ItemType.PLANKS
}

case class Podzol(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.PODZOL
}

case class Porkchop(
    variant: PorkchopVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[PorkchopVariant]
    with StackableItem {
  val `type` = ItemType.PORKCHOP
  override val edible = true
}

case class Potato(
    variant: PotatoVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[PotatoVariant]
    with StackableItem {
  val `type` = ItemType.POTATO
  override val edible = true
}

case class Potion(
    variant: PotionVariant,
    hideEffects: Boolean,
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[PotionVariant] {
  val `type` = ItemType.POTION
  def withHideEffects(hideEffects: Boolean): this.type =
    copyWith("hideEffects", hideEffects)
}

case class PressurePlate(
    variant: PressurePlateVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[PressurePlateVariant]
    with StackableItem {
  val `type` = ItemType.PRESSURE_PLATE
}

case class Prismarine(
    variant: PrismarineVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[PrismarineVariant]
    with StackableItem {
  val `type` = ItemType.PRISMARINE
}

case class PrismarineCrystals(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.PRISMARINE_CRYSTALS
}

case class PrismarineShard(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.PRISMARINE_SHARD
}

case class Pufferfish(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.PUFFERFISH
  override val edible = true
}

case class Pumpkin(
    carved: Boolean,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.PUMPKIN
}

case class PumpkinPie(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.PUMPKIN_PIE
  override val edible = true
}

case class PurpurBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.PURPUR_BLOCK
}

case class Quartz(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.QUARTZ
}

case class QuartzBlock(
    variant: QuartzBlockVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[QuartzBlockVariant]
    with StackableItem {
  val `type` = ItemType.QUARTZ_BLOCK
}

case class QuartzOre(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.QUARTZ_ORE
}

case class Rabbit(
    variant: RabbitVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[RabbitVariant]
    with StackableItem {
  val `type` = ItemType.RABBIT
  override val edible = true
}

case class RabbitFoot(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.RABBIT_FOOT
}

case class RabbitHide(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.RABBIT_HIDE
}

case class Rail(
    variant: RailVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[RailVariant]
    with StackableItem {
  val `type` = ItemType.RAIL
}

case class Repeater(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.REPEATER
}

case class Redstone(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.REDSTONE
}

case class RedstoneBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.REDSTONE_BLOCK
}

case class RedstoneLamp(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.REDSTONE_LAMP
}

case class RedstoneOre(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.REDSTONE_ORE
}

case class RedstoneTorch(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.REDSTONE_TORCH
}

case class RottenFlesh(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.ROTTEN_FLESH
  override val edible = true
}

case class Saddle(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean
) extends Item {
  val `type` = ItemType.SADDLE
}

case class Salmon(
    variant: SalmonVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[SalmonVariant]
    with StackableItem {
  val `type` = ItemType.SALMON
  override val edible = true
}

case class Sand(
    variant: SandVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[SandVariant]
    with StackableItem {
  val `type` = ItemType.SAND
}

case class Sandstone(
    variant: SandstoneVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[SandstoneVariant]
    with StackableItem {
  val `type` = ItemType.SANDSTONE
}

case class Sapling(
    variant: SaplingVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[SaplingVariant]
    with StackableItem {
  val `type` = ItemType.SAPLING
}

case class Scaffolding(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SCAFFOLDING
}

case class Scute(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SCUTE
}

case class Seagrass(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SEAGRASS
}

case class SeaLantern(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SEA_LANTERN
}

case class SeaPickle(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SEA_PICKLE
}

case class Seeds(
    variant: SeedsVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[SeedsVariant]
    with StackableItem {
  val `type` = ItemType.SEEDS
}

case class Shears(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends DurableItem {
  val `type` = ItemType.SHEARS
  override def maxDurability = 238
}

case class Shield(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends DurableItem {
  val `type` = ItemType.SHIELD
  override def maxDurability = 336
}

case class Shovel(
    variant: ShovelVariant,
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends VariableItem[ShovelVariant]
    with DurableItem {
  val `type` = ItemType.SHOVEL
  override def maxDurability: Int = variant match {
    case ShovelVariant.WOOD    => 59
    case ShovelVariant.STONE   => 131
    case ShovelVariant.IRON    => 250
    case ShovelVariant.GOLD    => 32
    case ShovelVariant.DIAMOND => 1561
  }
}

case class ShulkerBox(
    variant: ShulkerBoxVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[ShulkerBoxVariant]
    with StackableItem {
  val `type` = ItemType.SHULKER_BOX
}

case class ShulkerShell(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SHULKER_SHELL
}

case class Sign(
    variant: SignVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[SignVariant]
    with StackableItem {
  val `type` = ItemType.SIGN
  override def maxCount = 16
}

case class Slab(
    variant: SlabVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[SlabVariant]
    with StackableItem {
  val `type` = ItemType.SLAB
}

case class Slimeball(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SLIMEBALL
}

case class SlimeBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SLIME_BLOCK
}

case class SmithingTable(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SMITHING_TABLE
}

case class Smoker(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SMOKER
}

case class Snow(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SNOW
}

case class Snowball(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SNOWBALL
  override def maxCount = 16
}

case class SnowBlock(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SNOW_BLOCK
}

case class SoulSand(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SOUL_SAND
}

case class SpawnEgg(
    variant: SpawnEggVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[SpawnEggVariant]
    with StackableItem {
  val `type` = ItemType.SPAWN_EGG
}

case class Spawner(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SPAWNER
}

case class SpiderEye(
    variant: SpiderEyeVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[SpiderEyeVariant]
    with StackableItem {
  val `type` = ItemType.SPIDER_EYE
  override val edible: Boolean =
    variant == SpiderEyeVariant.NORMAL
}

case class Sponge(
    variant: SpongeVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[SpongeVariant]
    with StackableItem {
  val `type` = ItemType.SPONGE
}

case class Stairs(
    variant: StairsVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[StairsVariant]
    with StackableItem {
  val `type` = ItemType.SLAB
}

case class Stew(
    variant: StewVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[StewVariant]
    with StackableItem {
  val `type` = ItemType.STEW
  override val edible = true
}

case class Stick(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.STICK
}

case class Stone(
    variant: StoneVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[StoneVariant]
    with StackableItem {
  val `type` = ItemType.STONE
}

case class StoneBrick(
    variant: StoneBrickVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[StoneBrickVariant]
    with StackableItem {
  val `type` = ItemType.STONE
}

case class Stonecutter(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.STONECUTTER
}

case class StructureBlock(
    variant: StructureBlockVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[StructureBlockVariant]
    with StackableItem {
  val `type` = ItemType.STRUCTURE_BLOCK
}

case class PieceOfString(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.STRING
}

case class Sugar(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SUGAR
}

case class SugarCane(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SUGAR_CANE
}

case class SweetBerries(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.SWEET_BERRIES
  override val edible = true
}

case class Sword(
    variant: SwordVariant,
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends VariableItem[SwordVariant]
    with DurableItem {
  val `type` = ItemType.SWORD
  override def maxDurability: Int = variant match {
    case SwordVariant.WOOD    => 59
    case SwordVariant.STONE   => 131
    case SwordVariant.IRON    => 250
    case SwordVariant.GOLD    => 32
    case SwordVariant.DIAMOND => 1561
  }
}

case class Terracotta(
    variant: TerracottaVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[TerracottaVariant]
    with StackableItem {
  val `type` = ItemType.TERRACOTTA
}

case class TNT(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.TNT
}

case class Torch(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.TORCH
}

case class TotemOfUndying(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean
) extends Item {
  val `type` = ItemType.TOTEM_OF_UNDYING
}

case class Trapdoor(
    variant: TrapdoorVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[TrapdoorVariant]
    with StackableItem {
  val `type` = ItemType.TRAPDOOR
}

case class Trident(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends DurableItem {
  val `type` = ItemType.TRIDENT
  override def maxDurability = 250
}

case class TripwireHook(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.TRIPWIRE_HOOK
}

case class TropicalFish(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.TROPICAL_FISH
  override val edible = true
}

case class TurtleEgg(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.TURTLE_EGG
}

case class TurtleHelmet(
    name: String,
    tooltip: List[String],
    attributes: Set[String],
    hideAttributes: Boolean,
    durability: Int,
    unbreakable: Boolean,
    hideUnbreakable: Boolean
) extends DurableItem {
  val `type` = ItemType.TURTLE_HELMET
  override def maxDurability = 275
}

case class Vine(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.VINE
}

case class Wall(
    variant: WallVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[WallVariant]
    with StackableItem {
  val `type` = ItemType.WALL
}

case class WeightedPressurePlate(
    variant: WeightedPressurePlateVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[WeightedPressurePlateVariant]
    with StackableItem {
  val `type` = ItemType.WEIGHTED_PRESSURE_PLATE
}

case class Wheat(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.WHEAT
}

case class Wood(
    variant: WoodVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[WoodVariant]
    with StackableItem {
  val `type` = ItemType.WOOD
}

case class Wool(
    variant: WoolVariant,
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends VariableItem[WoolVariant]
    with StackableItem {
  val `type` = ItemType.WOOL
}

case class WrittenBook(
    name: String,
    tooltip: List[String],
    count: Int,
    attributes: Set[String],
    hideAttributes: Boolean
) extends StackableItem {
  val `type` = ItemType.WRITTEN_BOOK
  override def maxCount = 16
}
