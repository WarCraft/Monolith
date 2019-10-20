package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.world.block.BlockColor
import gg.warcraft.monolith.api.world.block.variant.AnvilVariant

case class Anvil(
    variant: AnvilVariant,
    data: ItemData
) extends VariedItem[AnvilVariant] {
  val `type` = ItemType.ANVIL
  def this(variant: AnvilVariant) =
    this(variant, ItemData(variant))
}

case class Banner(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.BANNER
  def this(color: BlockColor) =
    this(color, ItemData(color, "Banner"))
}

case class Bed(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.BED
  def this(color: BlockColor) =
    this(color, ItemData(color, "Bed"))
}

case class Campfire(
    data: ItemData = ItemData("Campfire")
) extends Item

case class Carpet(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.CARPET
  def this(color: BlockColor) =
    this(color, ItemData(color, "Carpet"))
}

case class Concrete(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.CONCRETE
  def this(color: BlockColor) =
    this(color, ItemData(color, "Concrete"))
}
case class ConcretePowder(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.CONCRETE_POWDER
  def this(color: BlockColor) =
    this(color, ItemData(color, "Concrete Powder"))
}

case class Glass(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.GLASS
  def this(color: Option[BlockColor]) =
    this(color, ItemData(color, "Glass"))
}

case class GlassPane(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.GLASS_PANE
  def this(color: Option[BlockColor]) =
    this(color, ItemData(color, "Glass Pane"))
}

case class GlazedTerracotta(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.GLAZED_TERRACOTTA
  def this(color: BlockColor) =
    this(color, ItemData(color, "Glazed Terracotta"))
}

case class ShulkerBox(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.SHULKER_BOX
  def this(color: Option[BlockColor]) =
    this(color, ItemData(color, "Shulker Box"))
}

case class Terracotta(
    color: Option[BlockColor],
    data: ItemData
) extends ColorableItem {
  val `type` = ItemType.TERRACOTTA
  def this(color: Option[BlockColor]) =
    this(color, ItemData(color, "Terracotta"))
}

case class Wool(
    color: BlockColor,
    data: ItemData
) extends ColoredItem {
  val `type` = ItemType.WOOL
  def this(color: BlockColor) =
    this(color, ItemData(color, "Wool"))
}
