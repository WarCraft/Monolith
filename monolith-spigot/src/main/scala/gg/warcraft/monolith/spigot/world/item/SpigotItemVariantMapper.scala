package gg.warcraft.monolith.spigot.world.item

import java.util

import gg.warcraft.monolith.api.world.block.variant.{
  AnvilVariant,
  ChestVariant,
  CobblestoneVariant,
  CommandBlockVariant,
  FlowerVariant,
  IceVariant,
  MobHeadVariant,
  MushroomBlockVariant,
  MushroomVariant,
  PlantVariant,
  RailVariant,
  SaplingVariant,
  StructureBlockVariant,
  WeightedPressurePlateVariant
}
import gg.warcraft.monolith.api.world.item._
import gg.warcraft.monolith.api.world.item.variant.{
  ArrowVariant,
  BannerPatternVariant,
  BucketVariant,
  HorseArmorVariant,
  MinecartVariant,
  MusicDiscVariant,
  PotionVariant,
  SeedsVariant,
  SpawnEggVariant,
  StewVariant
}
import gg.warcraft.monolith.spigot.world.block.SpigotBlockVariantMapper
import javax.inject.Inject
import org.bukkit.Material

class SpigotItemVariantMapper @Inject() (
    blockMapper: SpigotBlockVariantMapper
) {
  private val matCache = new util.EnumMap[Material, ItemVariant](classOf[Material])
  private val variantCache = new util.HashMap[ItemVariant, Material]()

  // TODO should this just accept material?
  def map(item: SpigotItemStack): ItemVariant =
    matCache.computeIfAbsent(item.getType, _ match {
      case it if it.isBlock => blockMapper.map(it).asInstanceOf[ItemVariant]

      case _ => null // TODO expose private compute def
    })

  def map(variant: ItemVariant): Material = variantCache.computeIfAbsent(
    variant,
    _ match {
      case it: AnvilVariant                 => blockMapper.mapAnvil(it)
      case it: ChestVariant                 => blockMapper.mapChest(it)
      case it: CobblestoneVariant           => blockMapper.mapCobblestone(it)
      case it: CommandBlockVariant          => blockMapper.mapCommandBlock(it)
      case it: FlowerVariant                => blockMapper.mapFlower(it)
      case it: IceVariant                   => blockMapper.mapIce(it)
      case it: MobHeadVariant               => blockMapper.mapMobHead(it)
      case it: MushroomVariant              => blockMapper.mapMushroom(it)
      case it: MushroomBlockVariant         => blockMapper.mapMushroomBlock(it)
      case it: RailVariant                  => blockMapper.mapRail(it)
      case it: SaplingVariant               => blockMapper.mapSapling(it)
      case it: StructureBlockVariant        => blockMapper.mapStructureBlock(it)
      case it: WeightedPressurePlateVariant => blockMapper.mapWeightedPressurePlate(it)

      case it: BannerPatternVariant => Material.valueOf(s"${it.name}_BANNER_PATTERN")
      case it: HorseArmorVariant    => Material.valueOf(s"${it.name}_HORSE_ARMOR")
      case it: MusicDiscVariant     => Material.valueOf(s"MUSIC_${it.name}")
      case it: SeedsVariant         => Material.valueOf(s"${it.name}_SEEDS")
      case it: SpawnEggVariant      => Material.valueOf(s"${it.name}_SPAWN_EGG")

      case ArrowVariant.NORMAL => Material.ARROW
      case it: ArrowVariant    => Material.valueOf(s"${it.name}_ARROW")

      case BucketVariant.EMPTY => Material.BUCKET
      case it: BucketVariant   => Material.valueOf(s"${it.name}_BUCKET")

      case MinecartVariant.EMPTY => Material.MINECART
      case it: MinecartVariant   => Material.valueOf(s"${it.name}_MINECART")

      case PotionVariant.NORMAL => Material.POTION
      case it: PotionVariant    => Material.valueOf(s"${it.name}_POTION")

      case StewVariant.BEETROOT => Material.BEETROOT_SOUP
      case it: StewVariant      => Material.valueOf(s"${it.name}_STEW")

      // TODO doesn't map tall fern, split into own
      // block => already is own item, or add a secondary PlantVariant which doesnt have item and
      // block classes and only used to set flower pot variant
      case PlantVariant.CACTUS    => Material.CACTUS
      case PlantVariant.DEAD_BUSH => Material.DEAD_BUSH
      case PlantVariant.FERN      => Material.FERN
      case PlantVariant.SUNFLOWER => Material.SUNFLOWER
      case PlantVariant.LILAC     => Material.LILAC
      case PlantVariant.PEONY     => Material.PEONY
      case PlantVariant.ROSE_BUSH => Material.ROSE_BUSH
    }
  )

  def map(item: VariedItem[_ <: ItemVariant]): Material = item match {
    case it: Coral      => blockMapper.mapCoral(it.variant)
    case it: CoralBlock => blockMapper.mapCoralBlock(it.variant)
    case it: CoralFan   => blockMapper.mapCoralFan(it.variant)
    // TODO case it: InfestedBlock => blockMapper.mapInfestedBlock(it.material, it.variant)
    case it: QuartzBlock => blockMapper.mapQuartzBlock(it.variant)
    case it: Sandstone   => blockMapper.mapSandstone(it.material, it.variant)
    case it: Stone       => blockMapper.mapStone(it.material, it.variant)
    // TODO case it: Stonite => blockMapper.mapStonite(it.material, it.variant)

    case _ => map(item.variant)
  }

  def map(item: VariableItem[_ <: ItemVariant]): Material = item match {
    case it: Slab   => blockMapper.mapSlab(it.material, it.variant)
    case it: Stairs => blockMapper.mapStairs(it.material, it.variant)
    case it: Wall   => blockMapper.mapWall(it.material, it.variant)
  }
}
