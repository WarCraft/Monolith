package gg.warcraft.monolith.api.math.config

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import gg.warcraft.monolith.api.math.Vector3i

@JsonCreator
case class Vector3iConfig(
  @JsonProperty("x") x: Int,
  @JsonProperty("y") y: Int,
  @JsonProperty("z") z: Int
) {
  def toVector3i: Vector3i = Vector3i(x, y, z)
}
