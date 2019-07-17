package gg.warcraft.monolith.api.world.config

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import gg.warcraft.monolith.api.math.config.Vector3iConfig
import gg.warcraft.monolith.api.world.{BlockLocation, World}

@JsonCreator
case class BlockLocationConfig(
  @JsonProperty("world") world: World,
  @JsonProperty("translation") translation: Vector3iConfig
) {
  def toBlockLocation: BlockLocation = BlockLocation(world, translation.toVector3i)

  /* Java interop */

  def getWorld: World = world
  def getTranslation: Vector3iConfig = translation
}
