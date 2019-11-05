package gg.warcraft.monolith.api.world.config

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import gg.warcraft.monolith.api.math.config.Vector3fConfig
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.{Location, World}

@JsonCreator
case class LocationConfig(
    @JsonProperty("world") world: World,
    @JsonProperty("translation") translation: Vector3fConfig,
    @JsonProperty("pitch") pitch: Float,
    @JsonProperty("yaw") yaw: Float
) {
  def toLocation: Location = {
    val rotation = Vector3f(pitch, yaw)
    Location(world, translation.toVector3f, rotation)
  }
}
