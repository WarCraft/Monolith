package gg.warcraft.monolith.api.world.block.state

import gg.warcraft.monolith.api.world.block.BlockState

case class TurtleEggState(
    age: TurtleEggAge,
    count: TurtleEggCount
) extends BlockState {
  // TODO fix this design inconsistency
  override def toInt: Int = throw new IllegalArgumentException()

  /* Java interop */
  def withAge(age: TurtleEggAge): TurtleEggState = copy(age = age)
  def withCount(count: TurtleEggCount): TurtleEggState = copy(count = count)
}
