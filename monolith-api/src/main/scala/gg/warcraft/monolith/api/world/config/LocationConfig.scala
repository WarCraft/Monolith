package gg.warcraft.monolith.api.world.config

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.{Location, World}

@JsonCreator
case class LocationConfig(
  @JsonProperty("world") world: World,
  @JsonProperty("translation") translation: Vector3f,
  @JsonProperty("rotation") rotation: Vector3f
) {
  def toLocation: Location = Location(world, translation, rotation)

  /* Java interop */

  def getWorld: World = world
  def getTranslation: Vector3f = translation
  def getRotation: Vector3f = rotation
}
