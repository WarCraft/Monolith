package gg.warcraft.monolith.api.world.location

import gg.warcraft.monolith.api.world.location.config.OrientationConfig
import org.joml.{Vector3f, Vector3fc}

case class Orientation(
  pitch: Float,
  yaw: Float,
) {
  validatePitch(pitch)
  validateYaw(yaw)

  def toVector: Vector3f = orientationToDirection(pitch, yaw)

  def withPitch(pitch: Float): Orientation = copy(pitch = pitch)

  def withYaw(yaw: Float): Orientation = copy(yaw = yaw)
}

object Orientation {
  def apply(direction: Vector3fc) {
    val (pitch, yaw) = directionToOrientation(direction)
    Orientation(pitch, yaw)
  }

  def apply(config: OrientationConfig) {
    Orientation(config.pitch, config.yaw)
  }
}
