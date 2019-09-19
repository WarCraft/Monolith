package gg.warcraft.monolith.api.world.block.state

import gg.warcraft.monolith.api.world.block.BlockState

case class TurtleEggState(
  age: TurtleEggAge,
  count: TurtleEggCount,
) extends BlockState {

  /* Java interop */

  def withAge(age: TurtleEggAge): TurtleEggState = copy(age = age)
  def withCount(count: TurtleEggCount): TurtleEggState = copy(count = count)
}
