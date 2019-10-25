package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.core.CaseClass

final case class ItemStack(
    item: Item,
    size: Int
) extends CaseClass {
  def withSize(size: Int): this.type =
    copyWith("size", size)

  require(size > 0 && size <= item.maxStackSize, {
    s"size is $size, must be > 0 and <= ${item.maxStackSize}"
  })
}
