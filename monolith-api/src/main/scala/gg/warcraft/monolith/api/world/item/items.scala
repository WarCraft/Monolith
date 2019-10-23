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
  def this(variant: StoniteVariant) =
    this(variant, ItemData(variant, "Andesite"))
}

final case class Anvil(
    variant: AnvilVariant,
    data: ItemData
) extends VariedItem[AnvilVariant] {
  val `type` = ItemType.ANVIL
  def this(variant: AnvilVariant) =
    this(variant, ItemData(variant, "Anvil"))
}

final case class ArmorStand(data: ItemData) extends Item {
  val `type` = ItemType.ARMOR_STAND
  def this() = this(ItemData("Armor Stand"))
}

final case class Bamboo(data: ItemData) extends Item {
  val `type` = ItemType.BAMBOO
  def this() = this(ItemData("Bamboo"))
}

final case class Banner(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.BANNER
  def this(color: BlockColor) =
    this(color, ItemData(color, "Banner"))
}

final case class Barrel(data: ItemData) extends Item {
  val `type` = ItemType.BARREL
  def this() = this(ItemData("Barrel"))
}

final case class Barrier(data: ItemData) extends Item {
  val `type` = ItemType.BARRIER
  def this() = this(ItemData("Barrier"))
}

final case class Beacon(data: ItemData) extends Item {
  val `type` = ItemType.BEACON
  def this() = this(ItemData("Beacon"))
}

final case class Bed(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.BED
  def this(color: BlockColor) =
    this(color, ItemData(color, "Bed"))
}

final case class Bedrock(data: ItemData) extends Item {
  val `type` = ItemType.BEDROCK
  def this() = this(ItemData("Bedrock"))
}

final case class Bell(data: ItemData) extends Item {
  val `type` = ItemType.BELL
  def this() = this(ItemData("Bell"))
}

final case class BlastFurnace(data: ItemData) extends Item {
  val `type` = ItemType.BLAST_FURNACE
  def this() = this(ItemData("Blast Furnace"))
}

final case class BlazePowder(data: ItemData) extends Item {
  val `type` = ItemType.BLAZE_POWDER
  def this() = this(ItemData("Blaze Powder"))
}

final case class BlazeRod(data: ItemData) extends Item {
  val `type` = ItemType.BLAZE_ROD
  def this() = this(ItemData("Blaze Rod"))
}

final case class Boat(
    material: WoodMaterial,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.BOAT
  def this(material: WoodMaterial) =
    this(material, ItemData(material, "Boat"))
}

final case class Bookshelf(data: ItemData) extends Item {
  val `type` = ItemType.BOOKSHELF
  def this() = this(ItemData("Bookshelf"))
}

final case class Bowl(data: ItemData) extends Item {
  val `type` = ItemType.BOWL
  def this() = this(ItemData("Bowl"))
}

final case class Bread(data: ItemData) extends Item {
  val `type` = ItemType.BREAD
  def this() = this(ItemData("Bread"))
}

final case class BrewingStand(data: ItemData) extends Item {
  val `type` = ItemType.BREWING_STAND
  def this() = this(ItemData("Brewing Stand"))
}

final case class Brick(
    material: BrickMaterial,
    data: ItemData
) extends MaterialItem[BrickMaterial] {
  val `type` = ItemType.BRICK
  def this(material: BrickMaterial) =
    this(material, ItemData(material, "Brick"))
}

final case class BrickBlock(
    material: BrickMaterial,
    data: ItemData
) extends MaterialItem[BrickMaterial] {
  val `type` = ItemType.BRICK_BLOCK
  def this(material: BrickMaterial) =
    this(material, ItemData(material, "Brick Block"))
}

final case class Bucket(
    variant: BucketVariant,
    data: ItemData
) extends VariedItem[BucketVariant] {
  val `type` = ItemType.BUCKET
  def this(variant: BucketVariant) =
    this(variant, ItemData(variant, "Bucket"))
}

final case class Button(
    material: ButtonMaterial,
    data: ItemData
) extends MaterialItem[ButtonMaterial] {
  val `type` = ItemType.BUTTON
  def this(material: ButtonMaterial) =
    this(material, ItemData(material, "Button"))
}

final case class Cactus(data: ItemData) extends Item {
  val `type` = ItemType.CACTUS
  def this() = this(ItemData("Cactus"))
}

final case class Campfire(data: ItemData) extends Item {
  val `type` = ItemType.CAMPFIRE
  def this() = this(ItemData("Campfire"))
}

final case class Carpet(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.CARPET
  def this(color: BlockColor) =
    this(color, ItemData(color, "Carpet"))
}

final case class Coal(data: ItemData) extends Item {
  val `type` = ItemType.COAL
  def this() = this(ItemData("Coal"))
}

final case class CoalBlock(data: ItemData) extends Item {
  val `type` = ItemType.COAL_BLOCK
  def this() = this(ItemData("Coal Block"))
}

final case class CoalOre(data: ItemData) extends Item {
  val `type` = ItemType.COAL_ORE
  def this() = this(ItemData("Coal Ore"))
}

final case class Cobblestone(
    variant: CobblestoneVariant,
    data: ItemData
) extends VariedItem[CobblestoneVariant] {
  val `type` = ItemType.COBBLESTONE
  def this(variant: CobblestoneVariant) =
    this(variant, ItemData(variant, "Cobblestone"))
}

final case class Concrete(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.CONCRETE
  def this(color: BlockColor) =
    this(color, ItemData(color, "Concrete"))
}

final case class ConcretePowder(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.CONCRETE_POWDER
  def this(color: BlockColor) =
    this(color, ItemData(color, "Concrete Powder"))
}

final case class Coral(
    variant: CoralVariant,
    data: ItemData
) extends VariedItem[CoralVariant] {
  val `type` = ItemType.CORAL
  def this(variant: CoralVariant) =
    this(variant, ItemData(variant, "Coral"))
}

final case class CoralBlock(
    variant: CoralVariant,
    data: ItemData
) extends VariedItem[CoralVariant] {
  val `type` = ItemType.CORAL_BLOCK
  def this(variant: CoralVariant) =
    this(variant, ItemData(variant, "Coral Block"))
}

final case class CoralFan(
    variant: CoralVariant,
    data: ItemData
) extends VariedItem[CoralVariant] {
  val `type` = ItemType.CORAL_FAN
  def this(variant: CoralVariant) =
    this(variant, ItemData(variant, "Coral Fan"))
}

final case class Diamond(data: ItemData) extends Item {
  val `type` = ItemType.DIAMOND
  def this() = this(ItemData("Diamond"))
}

final case class DiamondBlock(data: ItemData) extends Item {
  val `type` = ItemType.DIAMOND_BLOCK
  def this() = this(ItemData("Diamond Block"))
}

final case class DiamondOre(data: ItemData) extends Item {
  val `type` = ItemType.DIAMOND_ORE
  def this() = this(ItemData("Diamond Ore"))
}

final case class Diorite(
    variant: StoniteVariant,
    data: ItemData
) extends VariedItem[StoniteVariant] {
  val `type` = ItemType.DIORITE
  def this(variant: StoniteVariant) =
    this(variant, ItemData(variant, "Diorite"))
}

final case class Door(
    material: DoorMaterial,
    data: ItemData
) extends MaterialItem[DoorMaterial] {
  val `type` = ItemType.DOOR
  def this(material: DoorMaterial) =
    this(material, ItemData(material, "Door"))
}

final case class DragonBreath(data: ItemData) extends Item {
  val `type` = ItemType.DRAGON_BREATH
  def this() = this(ItemData("Dragon Breath"))
}

final case class DragonEgg(data: ItemData) extends Item {
  val `type` = ItemType.DRAGON_EGG
  def this() = this(ItemData("Dragon Egg"))
}

final case class Emerald(data: ItemData) extends Item {
  val `type` = ItemType.EMERALD
  def this() = this(ItemData("Emerald"))
}

final case class EmeraldBlock(data: ItemData) extends Item {
  val `type` = ItemType.EMERALD_BLOCK
  def this() = this(ItemData("Emerald Block"))
}

final case class EmeraldOre(data: ItemData) extends Item {
  val `type` = ItemType.EMERALD_ORE
  def this() = this(ItemData("Emerald Ore"))
}

final case class EnchantingTable(data: ItemData) extends Item {
  val `type` = ItemType.ENCHANTING_TABLE
  def this() = this(ItemData("Enchanting Table"))
}

final case class EndCrystal(data: ItemData) extends Item {
  val `type` = ItemType.END_CRYSTAL
  def this() = this(ItemData("End Crystal"))
}

final case class EndPortalFrame(data: ItemData) extends Item {
  val `type` = ItemType.END_PORTAL_FRAME
  def this() = this(ItemData("End Portal Frame"))
}

final case class EndRod(data: ItemData) extends Item {
  val `type` = ItemType.END_ROD
  def this() = this(ItemData("End Rod"))
}

final case class EndStone(
    material: EndStoneMaterial,
    data: ItemData
) extends MaterialItem[EndStoneMaterial] {
  val `type` = ItemType.END_STONE // TODO rename EndStoneMaterial.END_STONE > NORMAL?
  def this(material: EndStoneMaterial) =
    this(material, ItemData("End Stone", material))
}

final case class Fence(
    material: FenceMaterial,
    data: ItemData
) extends MaterialItem[FenceMaterial] {
  val `type` = ItemType.FENCE
  def this(material: FenceMaterial) =
    this(material, ItemData(material, "Fence"))
}

final case class Gate(
    material: WoodMaterial,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.GATE
  def this(material: WoodMaterial) =
    this(material, ItemData(material, "Gate"))
}

final case class GhastTear(data: ItemData) extends Item {
  val `type` = ItemType.GHAST_TEAR
  def this() = this(ItemData("Ghast Tear"))
}

final case class Glass(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.GLASS
  def this(color: Option[BlockColor]) =
    this(color, ItemData(color, "Glass"))
}

final case class GlassBottle(data: ItemData) extends Item {
  val `type` = ItemType.GLASS_BOTTLE
  def this() = this(ItemData("Glass Bottle"))
}

final case class GlassPane(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.GLASS_PANE
  def this(color: Option[BlockColor]) =
    this(color, ItemData(color, "Glass Pane"))
}

final case class GlazedTerracotta(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.GLAZED_TERRACOTTA
  def this(color: BlockColor) =
    this(color, ItemData(color, "Glazed Terracotta"))
}

final case class GoldBlock(data: ItemData) extends Item {
  val `type` = ItemType.GOLD_BLOCK
  def this() = this(ItemData("Gold Block"))
}

final case class GoldIngot(data: ItemData) extends Item {
  val `type` = ItemType.GOLD_INGOT
  def this() = this(ItemData("Gold Ingot"))
}

final case class GoldNugget(data: ItemData) extends Item {
  val `type` = ItemType.GOLD_NUGGET
  def this() = this(ItemData("Gold Nugget"))
}

final case class GoldOre(data: ItemData) extends Item {
  val `type` = ItemType.GOLD_ORE
  def this() = this(ItemData("Gold Ore"))
}

final case class GoldenApple(
    enchanted: Boolean,
    data: ItemData
) extends Item {
  val `type` = ItemType.GOLDEN_APPLE
  override val edible = true
  def this() = this(enchanted, ItemData(enchanted, "Enchanted", "Golden Apple"))
}

final case class GoldenCarrot(data: ItemData) extends Item {
  val `type` = ItemType.GOLDEN_CARROT
  override val edible = true
  def this() = this(ItemData("Golden Carrot"))
}

final case class Granite(
    variant: StoniteVariant,
    data: ItemData
) extends VariedItem[StoniteVariant] {
  val `type` = ItemType.GRANITE
  def this(variant: StoniteVariant) =
    this(variant, ItemData(variant, "Granite"))
}

final case class Lapis(data: ItemData) extends Item {
  val `type` = ItemType.LAPIS
  def this() = this(ItemData("Lapis"))
}

final case class LapisBlock(data: ItemData) extends Item {
  val `type` = ItemType.LAPIS_BLOCK
  def this() = this(ItemData("Lapis Block"))
}

final case class LapisOre(data: ItemData) extends Item {
  val `type` = ItemType.LAPIS_ORE
  def this() = this(ItemData("Lapis Ore"))
}

final case class Leaves(
    material: WoodMaterial,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.LEAVES
  def this(material: WoodMaterial) =
    this(material, ItemData(material, "Leaves"))
}

final case class Ice(
    variant: IceVariant,
    data: ItemData
) extends VariedItem[IceVariant] {
  val `type` = ItemType.ICE
  def this(variant: IceVariant) =
    this(variant, ItemData(variant, "Ice"))
}

final case class IronBars(data: ItemData) extends Item {
  val `type` = ItemType.IRON_BARS
  def this() = this(ItemData("Iron Bars"))
}

final case class IronBlock(data: ItemData) extends Item {
  val `type` = ItemType.IRON_BLOCK
  def this() = this(ItemData("Iron Block"))
}

final case class IronIngot(data: ItemData) extends Item {
  val `type` = ItemType.IRON_INGOT
  def this() = this(ItemData("Iron Ingot"))
}

final case class IronNugget(data: ItemData) extends Item {
  val `type` = ItemType.IRON_NUGGET
  def this() = this(ItemData("Iron Nugget"))
}

final case class IronOre(data: ItemData) extends Item {
  val `type` = ItemType.IRON_ORE
  def this() = this(ItemData("Iron Ore"))
}

final case class Log(
    material: WoodMaterial,
    stripped: Boolean,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.LOG
  def this(material: WoodMaterial, stripped: Boolean) =
    this(material, stripped, ItemData(material, "Log"))
  // this(material, stripped, ItemData(material, stripped, "Stripped", "Log")) // TODO what is a stripped log called?
}

final case class Mushroom(
    variant: MushroomVariant,
    data: ItemData
) extends VariedItem[MushroomVariant] {
  val `type` = ItemType.MUSHROOM
  def this(variant: MushroomVariant) =
    this(variant, ItemData(variant, "Mushroom"))
}

final case class MushroomBlock(
    variant: MushroomBlockVariant,
    data: ItemData
) extends VariedItem[MushroomBlockVariant] {
  val `type` = ItemType.MUSHROOM_BLOCK
  def this(variant: MushroomBlockVariant) =
    this(variant, ItemData("Mushroom Block"))
}

final case class MusicDisc(
    variant: MusicDiscVariant,
    data: ItemData
) extends VariedItem[MusicDiscVariant] {
  val `type` = ItemType.MUSIC_DISC
  def this(variant: MusicDiscVariant) =
    this(variant, ItemData(variant, "Music Disc"))
}

final case class Planks(
    material: WoodMaterial,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.PLANKS
  def this(material: WoodMaterial) =
    this(material, ItemData(material, "Planks"))
}

final case class Pillar(
    material: PillarMaterial,
    data: ItemData
) extends MaterialItem[PillarMaterial] {
  val `type` = ItemType.PILLAR
  def this(material: PillarMaterial) =
    this(material, ItemData(material, "Pillar"))
}

final case class PressurePlate(
    material: PressurePlateMaterial,
    data: ItemData
) extends MaterialItem[PressurePlateMaterial] {
  val `type` = ItemType.PRESSURE_PLATE
  def this(material: WoodMaterial) =
    this(material, ItemData(material, "Pressure Plate"))
}

final case class Prismarine(
    material: PrismarineMaterial,
    data: ItemData
) extends MaterialItem[PrismarineMaterial] {
  val `type` = ItemType.PRISMARINE
  def this(material: PrismarineMaterial) =
    this(material, ItemData(material, "Prismarine"))
}

final case class PrismarineCrystals(data: ItemData) extends Item {
  val `type` = ItemType.PRISMARINE_CRYSTALS
  def this() = this(ItemData("Prismarine Crystals"))
}

final case class PrismarineShard(data: ItemData) extends Item {
  val `type` = ItemType.PRISMARINE_SHARD
  def this() = this(ItemData("Prismarine Shard"))
}

final case class Purpur(data: ItemData) extends Item {
  val `type` = ItemType.PURPUR
  def this() = this(ItemData("Purpur"))
}

final case class Quartz(data: ItemData) extends Item {
  val `type` = ItemType.QUARTZ
  def this() = this(ItemData("Quartz")) // TODO Nether Quartz?
}

final case class QuartzBlock(
    variant: QuartzVariant,
    data: ItemData
) extends VariedItem[QuartzVariant] {
  val `type` = ItemType.QUARTZ_BLOCK
  def this(variant: QuartzVariant) =
    this(variant, ItemData(variant, "Quartz Block"))
}

final case class QuartzOre(data: ItemData) extends Item {
  val `type` = ItemType.QUARTZ_ORE
  def this() = this(ItemData("Quartz Ore"))
}

final case class Rails(
    variant: RailsVariant,
    data: ItemData
) extends VariedItem[RailsVariant] {
  val `type` = ItemType.RAILS
  def this(variant: RailsVariant) =
    this(variant, ItemData(variant, "Rail"))
}

final case class Redstone(data: ItemData) extends Item {
  val `type` = ItemType.REDSTONE
  def this() = this(ItemData("Redstone"))
}

final case class RedstoneBlock(data: ItemData) extends Item {
  val `type` = ItemType.REDSTONE_BLOCK
  def this() = this(ItemData("Redstone Block"))
}

final case class RedstoneOre(data: ItemData) extends Item {
  val `type` = ItemType.REDSTONE_ORE
  def this() = this(ItemData("Redstone Ore"))
}

final case class Sand(
    material: SandMaterial, // TODO should this be a variant?
    data: ItemData
) extends MaterialItem[SandMaterial] {
  val `type` = ItemType.SAND
  def this(material: SandMaterial) =
    this(material, ItemData(material, "Sand"))
}

final case class Sandstone(
    material: SandstoneMaterial,
    variant: SandstoneVariant,
    data: ItemData
) extends MaterialItem[SandstoneMaterial]
    with VariedItem[SandstoneVariant] {
  def this(material: SandstoneMaterial, variant: SandstoneVariant) =
    this(material, variant, ItemData(material, variant, "Sandstone"))
}

final case class Sapling(
    variant: SaplingVariant, // TODO would kinda like this to be material again
    data: ItemData
) extends VariedItem[SaplingVariant] {
  val `type` = ItemType.SAPLING
  def this(variant: SaplingVariant) =
    this(variant, ItemData(variant, "Sapling"))
}

final case class Scute(data: ItemData) extends Item {
  val `type` = ItemType.SCUTE
  def this() = this(ItemData("Scute"))
}

final case class SeaLantern(data: ItemData) extends Item {
  val `type` = ItemType.SEA_LANTERN
  def this() = this(ItemData("Sea Lantern"))
}

final case class SeaPickle(data: ItemData) extends Item {
  val `type` = ItemType.SEA_PICKLE
  def this() = this(ItemData("Sea Pickle"))
}

final case class ShulkerBox(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.SHULKER_BOX
  def this(color: Option[BlockColor]) =
    this(color, ItemData(color, "Shulker Box"))
}

final case class Sign(
    material: WoodMaterial,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.SIGN
  def this(material: WoodMaterial) =
    this(material, ItemData(material, "Sign"))
}

final case class Slab(
    material: SlabMaterial,
    variant: Option[SlabVariant],
    data: ItemData
) extends MaterialItem[SlabMaterial]
    with VariableItem[SlabVariant] {
  val `type` = ItemType.SLAB
  def this(material: SlabMaterial, variant: Option[SlabVariant]) =
    this(material, variant, ItemData(material, variant, "Slab"))
}

final case class Slimeball(data: ItemData) extends Item {
  val `type` = ItemType.SLIMEBALL
  def this() = this(ItemData("Slimeball"))
}

final case class SlimeBlock(data: ItemData) extends Item {
  val `type` = ItemType.SLIME_BLOCK
  def this() = this(ItemData("Slime Block"))
}

final case class Snow(data: ItemData) extends Item {
  val `type` = ItemType.SNOW
  def this() = this(ItemData("Snow"))
}

final case class Snowball(data: ItemData) extends Item {
  val `type` = ItemType.SNOWBALL
  def this() = this(ItemData("Snowball"))
}

final case class SnowBlock(data: ItemData) extends Item {
  val `type` = ItemType.SNOW_BLOCK
  def this() = this(ItemData("Snow Block"))
}

final case class SoulSand(data: ItemData) extends Item {
  val `type` = ItemType.SOUL_SAND
  def this() = this(ItemData("Soul Sand"))
}

final case class SpawnEgg(
    variant: SpawnEggVariant,
    data: ItemData
) extends VariedItem[SpawnEggVariant] {
  val `type` = ItemType.SPAWN_EGG // TODO MonsterEgg?
  def this(variant: SpawnEggVariant) =
    this(variant, ItemData(variant, "Spawn Egg"))
}

final case class Spawner(data: ItemData) extends Item {
  val `type` = ItemType.SPAWNER
  def this() = this(ItemData("Spawner"))
}

final case class SpiderEye(
    fermented: Boolean,
    data: ItemData
) extends Item {
  val `type` = ItemType.SPIDER_EYE
  def this(fermented: Boolean) =
    this(fermented, ItemData(fermented, "Fermented", "Spider Eye"))
}

final case class Sponge(
    wet: Boolean,
    data: ItemData
) extends Item {
  val `type` = ItemType.SPONGE
  def this(wet: Boolean) =
    this(wet, ItemData(wet, "Wet", "Sponge"))
}

final case class Stairs(
    material: StairsMaterial,
    variant: Option[StairsVariant],
    data: ItemData
) extends MaterialItem[StairsMaterial]
    with VariableItem[StairsVariant] {
  val `type` = ItemType.SLAB
  def this(material: StairsMaterial, variant: Option[StairsVariant]) =
    this(material, variant, ItemData(material, variant, "Slab"))
}

final case class Stone(
    material: StoneMaterial,
    variant: StoneVariant,
    data: ItemData
) extends MaterialItem[StoneMaterial]
    with VariedItem[StoneVariant] {
  val `type` = ItemType.STONE
  def this(material: StoneMaterial, variant: StoneVariant) =
    this(material, variant, ItemData(material, variant, "Stone"))
}

final case class Sugar(data: ItemData) extends Item {
  val `type` = ItemType.SUGAR
  def this() = this(ItemData("Sugar"))
}

final case class SugarCane(data: ItemData) extends Item {
  val `type` = ItemType.SUGAR_CANE
  def this() = this(ItemData("Sugar Cane"))
}

final case class Terracotta(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.TERRACOTTA
  def this(color: Option[BlockColor]) =
    this(color, ItemData(color, "Terracotta"))
}

final case class TrapDoor(
    material: TrapdoorMaterial,
    data: ItemData
) extends MaterialItem[TrapdoorMaterial] {
  val `type` = ItemType.TRAPDOOR
  def this(material: TrapdoorMaterial) =
    this(material, ItemData(material, "Trap Door"))
}

final case class TripwireHook(data: ItemData) extends Item {
  val `type` = ItemType.TRIPWIRE_HOOK
  def this() = this(ItemData("Tripwire Hook"))
}

final case class TurtleEgg(data: ItemData) extends Item {
  val `type` = ItemType.TURTLE_EGG
  def this() = this(ItemData("Turtle Egg"))
}

final case class TurtleHelmet(data: ItemData) extends Item {
  val `type` = ItemType.TURTLE_HELMET
  def this() = this(ItemData("Turtle Helmet"))
}

final case class Vine(data: ItemData) extends Item {
  val `type` = ItemType.VINE
  def this() = this(ItemData("Vine"))
}

final case class Wall(
    material: WallMaterial,
    variant: Option[WallVariant],
    data: ItemData
) extends MaterialItem[WallMaterial]
    with VariableItem[WallVariant] {
  val `type` = ItemType.WALL
  def this(material: WallMaterial, variant: Option[WallVariant]) =
    this(material, variant, ItemData(material, variant, "Wall"))
}

final case class WeightedPressurePlate(
    variant: WeightedPressurePlateVariant,
    data: ItemData
) extends VariedItem[WeightedPressurePlateVariant] {
  val `type` = ItemType.WEIGHTED_PRESSURE_PLATE
  def this(variant: WeightedPressurePlateVariant) =
    this(variant, ItemData(variant, "Weighted Pressure Plate"))
}

final case class Wood(
    material: WoodMaterial,
    stripped: Boolean,
    data: ItemData
) extends MaterialItem[WoodMaterial] {
  val `type` = ItemType.WOOD
  def this(material: WoodMaterial, stripped: Boolean) =
    this(material, stripped, ItemData(material, "Wood"))
  // this(material, stripped, ItemData(material, stripped, "Stripped", "Wood"))
}

final case class Wool(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.WOOL
  def this(color: BlockColor) =
    this(color, ItemData(color, "Wool"))
}
