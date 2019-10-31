package gg.warcraft.monolith.spigot.world.item

import gg.warcraft.monolith.api.world.block.{ DoorMaterial, SlabMaterial, SlabVariant,
  StairsMaterial, StairsVariant, WallMaterial, WallVariant }
import gg.warcraft.monolith.api.world.block.variant.{ CoralVariant, StoniteVariant }
import gg.warcraft.monolith.api.world.item.{ Coral, CoralBlock, CoralFan, Door, Item,
  ItemFactory, ItemType, ItemVariant, Slab, Stairs, VariedItem, Wall }
import gg.warcraft.monolith.spigot.world.block.{ SpigotBlockMaterialMapper,
  SpigotBlockVariantMapper }
import javax.inject.Inject
import org.bukkit.Material

class SpigotItemFactory @Inject()(
    itemMapper: SpigotItemMapper,
    materialMapper: SpigotItemMaterialMapper,
    variantMapper: SpigotItemVariantMapper,
    blockMaterialMapper: SpigotBlockMaterialMapper,
    blockVariantMapper: SpigotBlockVariantMapper
) extends ItemFactory {

  private def itemOf(material: Material): Item = {
    val item = new SpigotItemStack(material)
    itemMapper.map(item).get
  }

  override def create(`type`: ItemType): Item = `type` match {
    case ItemType.AXE                     => itemOf(Material.WOODEN_AXE)
    case ItemType.BANNER                  => itemOf(Material.BLACK_BANNER)
    case ItemType.BANNER_PATTERN          => itemOf(Material.CREEPER_BANNER_PATTERN)
    case ItemType.BED                     => itemOf(Material.RED_BED)
    case ItemType.BOAT                    => itemOf(Material.OAK_BOAT)
    case ItemType.BOOK_AND_QUILL          => itemOf(Material.WRITABLE_BOOK)
    case ItemType.BOOTS                   => itemOf(Material.LEATHER_BOOTS)
    case ItemType.BOTTLE_OF_ENCHANTING    => itemOf(Material.EXPERIENCE_BOTTLE)
    case ItemType.BRICK_BLOCK             => itemOf(Material.BRICKS)
    case ItemType.BUTTON                  => itemOf(Material.OAK_BUTTON)
    case ItemType.CARPET                  => itemOf(Material.BLACK_CARPET)
    case ItemType.CHESTPLATE              => itemOf(Material.LEATHER_CHESTPLATE)
    case ItemType.CLAY                    => itemOf(Material.CLAY_BALL)
    case ItemType.CLAY_BLOCK              => itemOf(Material.CLAY)
    case ItemType.CONCRETE                => itemOf(Material.BLACK_CONCRETE)
    case ItemType.CONCRETE_POWDER         => itemOf(Material.BLACK_CONCRETE_POWDER)
    case ItemType.CORAL                   => itemOf(Material.BRAIN_CORAL)
    case ItemType.CORAL_BLOCK             => itemOf(Material.BRAIN_CORAL_BLOCK)
    case ItemType.CORAL_FAN               => itemOf(Material.BRAIN_CORAL_FAN)
    case ItemType.DOOR                    => itemOf(Material.OAK_DOOR)
    case ItemType.DYE                     => itemOf(Material.BLACK_DYE)
    case ItemType.FENCE                   => itemOf(Material.OAK_FENCE)
    case ItemType.FLOWER                  => itemOf(Material.POPPY)
    case ItemType.GATE                    => itemOf(Material.OAK_FENCE_GATE)
    case ItemType.GLAZED_TERRACOTTA       => itemOf(Material.BLACK_GLAZED_TERRACOTTA)
    case ItemType.GOLDEN_MELON_SLICE      => itemOf(Material.GLISTERING_MELON_SLICE)
    case ItemType.HELMET                  => itemOf(Material.LEATHER_HELMET)
    case ItemType.HOE                     => itemOf(Material.WOODEN_HOE)
    case ItemType.HORSE_ARMOR             => itemOf(Material.LEATHER_HORSE_ARMOR)
    case ItemType.INFESTED_BLOCK          => itemOf(Material.INFESTED_STONE)
    case ItemType.JACK_OF_THE_LANTERN     => itemOf(Material.JACK_O_LANTERN)
    case ItemType.JIGSAW_BLOCK            => itemOf(Material.JIGSAW)
    case ItemType.LAPIS                   => itemOf(Material.LAPIS_LAZULI)
    case ItemType.LEAVES                  => itemOf(Material.OAK_LEAVES)
    case ItemType.LEGGINGS                => itemOf(Material.LEATHER_LEGGINGS)
    case ItemType.LOG                     => itemOf(Material.OAK_LOG)
    case ItemType.MOB_HEAD                => itemOf(Material.ZOMBIE_HEAD)
    case ItemType.MUSHROOM                => itemOf(Material.RED_MUSHROOM)
    case ItemType.MUSHROOM_BLOCK          => itemOf(Material.RED_MUSHROOM_BLOCK)
    case ItemType.MUSIC_DISC              => itemOf(Material.MUSIC_DISC_CAT)
    case ItemType.PICKAXE                 => itemOf(Material.WOODEN_PICKAXE)
    case ItemType.PILLAR                  => itemOf(Material.QUARTZ_PILLAR)
    case ItemType.PLANKS                  => itemOf(Material.OAK_PLANKS)
    case ItemType.PLANT                   => itemOf(Material.LILAC)
    case ItemType.PRESSURE_PLATE          => itemOf(Material.OAK_PRESSURE_PLATE)
    case ItemType.QUARTZ_ORE              => itemOf(Material.NETHER_QUARTZ_ORE)
    case ItemType.RAILS                   => itemOf(Material.RAIL) // TODO rename and remove
    case ItemType.SAPLING                 => itemOf(Material.OAK_SAPLING)
    case ItemType.SEEDS                   => itemOf(Material.WHEAT_SEEDS)
    case ItemType.SHOVEL                  => itemOf(Material.WOODEN_SHOVEL)
    case ItemType.SIGN                    => itemOf(Material.OAK_SIGN)
    case ItemType.SLAB                    => itemOf(Material.OAK_SLAB)
    case ItemType.SLIMEBALL               => itemOf(Material.SLIME_BALL)
    case ItemType.SPAWN_EGG               => itemOf(Material.CAT_SPAWN_EGG)
    case ItemType.STAIRS                  => itemOf(Material.OAK_STAIRS)
    case ItemType.STEW                    => itemOf(Material.MUSHROOM_STEW)
    case ItemType.SWORD                   => itemOf(Material.WOODEN_SWORD)
    case ItemType.TRAPDOOR                => itemOf(Material.OAK_TRAPDOOR)
    case ItemType.WALL                    => itemOf(Material.COBBLESTONE_WALL)
    case ItemType.WEIGHTED_PRESSURE_PLATE => itemOf(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)
    case ItemType.WOOD                    => itemOf(Material.OAK_WOOD)
    case ItemType.WOOL                    => itemOf(Material.BLACK_WOOL)

    case it =>
      val material = Material.valueOf(it.name)
      itemOf(material)
  }

  override def create[T <: ItemVariant](variant: T): VariedItem[T] = {
    val item = variant match {
      case _: SlabVariant    => itemOf(Material.OAK_SLAB)
      case _: StairsVariant  => itemOf(Material.OAK_STAIRS)
      case _: StoniteVariant => itemOf(Material.ANDESITE)
      case _: WallVariant    => itemOf(Material.COBBLESTONE_WALL)

      case _ =>
        val material = variantMapper.map(variant)
        itemOf(material)
    }

    item.asInstanceOf[VariedItem[T]]
  }

  def createDoor(material: DoorMaterial): Door = {
    val material = blockMaterialMapper.mapDoor(material)
    itemOf(material).asInstanceOf[Door]
  }

  def createCoral(variant: CoralVariant): Coral = {
    val material = blockVariantMapper.mapCoral(variant)
    itemOf(material).asInstanceOf[Coral]
  }

  def createCoralBlock(variant: CoralVariant): CoralBlock = {
    val material = blockVariantMapper.mapCoralBlock(variant)
    itemOf(material).asInstanceOf[CoralBlock]
  }

  def createCoralFan(variant: CoralVariant): CoralFan = {
    val material = blockVariantMapper.mapCoralFan(variant)
    itemOf(material).asInstanceOf[CoralFan]
  }

  def createSlab(mat: SlabMaterial, variant: Option[SlabVariant]): Slab = mat match {
    case _ => throw new IllegalStateException()
  }

  def createStairs(mat: StairsMaterial, variant: Option[StairsVariant]): Stairs = mat match {
    case _ => throw new IllegalStateException()
  }

  def createWall(mat: WallMaterial, variant: Option[WallVariant]): Wall = mat match {
    case _ => throw new IllegalStateException()
  }
}
