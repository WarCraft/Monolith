package gg.warcraft.monolith.api.world.location.config

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}

@JsonCreator
case class OrientationConfig(
  @JsonProperty("pitch") pitch: Float,
  @JsonProperty("yaw") yaw: Float,
)
