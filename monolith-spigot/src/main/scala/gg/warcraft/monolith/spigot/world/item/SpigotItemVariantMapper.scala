package gg.warcraft.monolith.spigot.world.item

import java.util

import gg.warcraft.monolith.api.world.item._
import gg.warcraft.monolith.spigot.world.block.SpigotBlockVariantMapper
import javax.inject.Inject
import org.bukkit.Material

class SpigotItemVariantMapper @Inject()(
    blockMapper: SpigotBlockVariantMapper
) {
  private val cache =
    new util.EnumMap[Material, ItemVariant](classOf[Material])

  def map(item: SpigotItemStack): ItemVariant =
    cache.computeIfAbsent(item.getType, _ => compute(item))

  private def compute(item: SpigotItemStack): ItemVariant = item.getType match {

    case _ => null // TODO expose private compute def
  }

  def map(item: Item): Material = item match {
    case it: Anvil                 => blockMapper.mapAnvil(it.variant)
    case it: Chest                 => blockMapper.mapChest(it.variant)
    case it: Cobblestone           => blockMapper.mapCobblestone(it.variant)
    case it: CommandBlock          => blockMapper.mapCommandBlock(it.variant)
    case it: Coral                 => blockMapper.mapCoral(it.variant)
    case it: CoralBlock            => blockMapper.mapCoralBlock(it.variant)
    case it: CoralFan              => blockMapper.mapCoralFan(it.variant)
    case it: Flower                => blockMapper.mapFlower(it.variant)
    case it: InfestedBlock         => blockMapper.mapInfestedBlock(it.material, it.variant)
    case it: Ice                   => blockMapper.mapIce(it.variant)
    case it: MobHead               => blockMapper.mapMobHead(it.variant)
    case it: Mushroom              => blockMapper.mapMushroom(it.variant)
    case it: MushroomBlock         => blockMapper.mapMushroomBlock(it.variant)
    case it: QuartzBlock           => blockMapper.mapQuartzBlock(it.variant)
    case it: Rails                 => blockMapper.mapRail(it.variant)
    case it: Sandstone             => blockMapper.mapSandstone(it.material, it.variant)
    case it: Sapling               => blockMapper.mapSapling(it.variant)
    case it: Stone                 => blockMapper.mapStone(it.material, it.variant)
    case it: Stonite               => blockMapper.mapStonite(it.material, it.variant)
    case it: StructureBlock        => blockMapper.mapStructureBlock(it.variant)
    case it: WeightedPressurePlate => blockMapper.mapWeightedPressurePlate(it.variant)
  }
}
