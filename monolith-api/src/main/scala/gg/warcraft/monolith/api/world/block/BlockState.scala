package gg.warcraft.monolith.api.world.block

trait BlockState

trait NumericalBlockState extends BlockState {
  def toInt: Int
}
