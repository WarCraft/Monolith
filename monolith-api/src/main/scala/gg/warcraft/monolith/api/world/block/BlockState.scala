package gg.warcraft.monolith.api.world.block

trait BlockState extends BlockTypeVariantOrState {
  def toInt: Int
}
