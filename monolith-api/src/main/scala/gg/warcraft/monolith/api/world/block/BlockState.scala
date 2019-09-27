package gg.warcraft.monolith.api.world.block

trait BlockState

trait IntegerBlockState extends BlockState {
  def toInt: Int
}
