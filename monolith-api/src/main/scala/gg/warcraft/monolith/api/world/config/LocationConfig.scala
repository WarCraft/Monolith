package gg.warcraft.monolith.api.world.config

import com.fasterxml.jackson.annotation.{ JsonCreator, JsonProperty }
import gg.warcraft.monolith.api.math.config.Vector3fConfig
import gg.warcraft.monolith.api.world.{ Location, World }

@JsonCreator
case class LocationConfig(
  @JsonProperty("world") world: World,
  @JsonProperty("translation") translation: Vector3fConfig,
  @JsonProperty("rotation") rotation: Vector3fConfig
) {
  def toLocation: Location = Location(world, translation.toVector3f, rotation.toVector3f)
}
