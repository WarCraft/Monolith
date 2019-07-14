package gg.warcraft.monolith.api.world.location.config

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import gg.warcraft.monolith.api.world.World

@JsonCreator
case class LocationConfig(
  @JsonProperty("world") world: World,
  @JsonProperty("x") x: Float,
  @JsonProperty("y") y: Float,
  @JsonProperty("z") z: Float
) {
  def getWorld: World = world

  def getX: Float = x

  def getY: Float = y

  def getZ: Float = z
}
