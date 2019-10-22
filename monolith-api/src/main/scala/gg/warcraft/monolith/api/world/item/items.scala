package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.material.WoodMaterial
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

final case class Glass(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.GLASS
  def this(color: Option[BlockColor]) =
    this(color, ItemData(color, "Glass"))
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

final case class Granite(
    variant: StoniteVariant,
    data: ItemData
) extends VariedItem[StoniteVariant] {
  val `type` = ItemType.GRANITE
  def this(variant: StoniteVariant) =
    this(variant, ItemData(variant, "Granite"))
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

final case class PressurePlate(
    material: PressurePlateMaterial,
    data: ItemData
) extends MaterialItem[PressurePlateMaterial] {
  val `type` = ItemType.PRESSURE_PLATE
  def this(material: WoodMaterial) =
    this(material, ItemData(material, "Pressure Plate"))
}

final case class Rails(
    variant: RailsVariant,
    data: ItemData
) extends VariedItem[RailsVariant] {
  val `type` = ItemType.RAILS
  def this(variant: RailsVariant) =
    this(variant, ItemData(variant, "Rail"))
}

final case class Sapling(
    variant: SaplingVariant, // TODO would kinda like this to be material again
    data: ItemData
) extends VariedItem[SaplingVariant] {
  val `type` = ItemType.SAPLING
  def this(variant: SaplingVariant) =
    this(variant, ItemData(variant, "Sapling"))
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

final case class SpawnEgg(
    variant: SpawnEggVariant,
    data: ItemData
) extends VariedItem[SpawnEggVariant] {
  val `type` = ItemType.SPAWN_EGG // TODO MonsterEgg?
  def this(variant: SpawnEggVariant) =
    this(variant, ItemData(variant, "Spawn Egg"))
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
