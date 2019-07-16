package gg.warcraft.monolith.api.world.location.config

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import gg.warcraft.monolith.api.world.World

@JsonCreator
case class BlockLocationConfig(
  @JsonProperty("world") world: World,
  @JsonProperty("x") x: Int,
  @JsonProperty("y") y: Int,
  @JsonProperty("z") z: Int
) {
  def getWorld: World = world
  def getX: Int = x
  def getY: Int = y
  def getZ: Int = z
}
