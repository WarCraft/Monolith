package gg.warcraft.monolith.api.math.config

import com.fasterxml.jackson.annotation.{ JsonCreator, JsonProperty }
import gg.warcraft.monolith.api.math.Vector3f

@JsonCreator
case class Vector3fConfig(
  @JsonProperty("x") x: Float,
  @JsonProperty("y") y: Float,
  @JsonProperty("z") z: Float
) {
  def toVector3f: Vector3f = Vector3f(x, y, z)
}
