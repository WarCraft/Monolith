package gg.warcraft.monolith.api.block

trait BlockState extends BlockTypeVariantOrState {
  def toInt: Int
}
