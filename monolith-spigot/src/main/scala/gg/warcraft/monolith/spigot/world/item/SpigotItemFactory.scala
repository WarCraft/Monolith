package gg.warcraft.monolith.spigot.world.item

import gg.warcraft.monolith.api.world.block.variant.{
  SlabVariant,
  StairsVariant,
  StoniteVariant,
  WallVariant
}
import gg.warcraft.monolith.api.world.item.{
  Item,
  ItemFactory,
  ItemType,
  ItemVariant,
  VariableItem
}
import javax.inject.Inject
import org.bukkit.Material

class SpigotItemFactory @Inject() (
    itemMapper: SpigotItemMapper,
    itemTypeMapper: SpigotItemTypeMapper,
    variantMapper: SpigotItemVariantMapper
) extends ItemFactory {
  override def create(`type`: ItemType): Item = {
    val material = itemTypeMapper.map(`type`)
    val item = new SpigotItemStack(material)
    itemMapper.map(item).get
  }

  override def create[T <: ItemVariant](variant: T): VariableItem[T] = itemOf({
    case _: SlabVariant    => itemOf(Material.OAK_SLAB)
    case _: StairsVariant  => itemOf(Material.OAK_STAIRS)
    case _: StoniteVariant => itemOf(Material.ANDESITE)
    case _: WallVariant    => itemOf(Material.COBBLESTONE_WALL)

    case it: ItemVariant => variantMapper.map(it)
  }).asInstanceOf[VariableItem[T]]
}
