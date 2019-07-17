package gg.warcraft.monolith.api.world.config

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import gg.warcraft.monolith.api.math.Vector3i
import gg.warcraft.monolith.api.world.{BlockLocation, World}

@JsonCreator
case class BlockLocationConfig(
  @JsonProperty("world") world: World,
  @JsonProperty("translation") translation: Vector3i
) {
  def toBlockLocation: BlockLocation = BlockLocation(world, translation)

  /* Java interop */

  def getWorld: World = world
  def getTranslation: Vector3i = translation
}
