package gg.warcraft.monolith.api.world.location

import org.joml.Vector3f

trait Orientable {
  val pitch: Float
  val yaw: Float

  validatePitch(pitch)
  validateYaw(yaw)

  def getPitch: Float = pitch

  def getYaw: Float = yaw

  def toVector: Vector3f = orientationToDirection(pitch, yaw)

  def withPitch(pitch: Float): Orientation

  def withYaw(yaw: Float): Orientation
}
