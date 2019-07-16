package gg.warcraft.monolith.api.world

import org.joml.{Vector3f, Vector3fc}

package object location {

  def validateWorld(world: World): Unit = {
    if (world == null) {
      throw new IllegalArgumentException("world can not be null")
    }
  }

  def validateCoordinate(coordinate: Number): Unit = {
    if (coordinate == null) {
      throw new IllegalArgumentException("coordinate can not be null")
    }
  }

  def validatePitch(pitch: Float): Unit = {
    if (pitch < -90) {
      throw new IllegalArgumentException(s"pitch must be >= -90, but was $pitch")
    } else if (pitch > 90) {
      throw new IllegalArgumentException(s"pitch must be <= 90, but was $pitch")
    }
  }

  def clampYaw(yaw: Float): Float = {
    var clampedYaw = yaw
    while (clampedYaw < -180) clampedYaw += 360
    while (clampedYaw >= 180) clampedYaw -= 360
    clampedYaw
  }

  def orientationToDirection(pitch: Float, yaw: Float): Vector3f = {
    validatePitch(pitch)
    val clampedYaw = clampYaw(yaw)

    // Add 90 degrees to correct for an extra rotation Minecraft added
    val pitchRad = Math.toRadians(pitch + 90)
    val yawRad = Math.toRadians(clampedYaw + 90)

    val sinPitchRad = Math.sin(pitchRad)
    val x = sinPitchRad * Math.cos(yawRad)
    val y = sinPitchRad * Math.sin(yawRad)
    val z = Math.cos(pitchRad)

    // Normally the Z-axis defines height, but in Minecraft this is the Y-axis
    new Vector3f(x.toFloat, z.toFloat, y.toFloat)
  }

  def directionToOrientation(direction: Vector3fc): (Float, Float) = {
    val normalizedDir = direction.normalize(new Vector3f)
    val pitchRad = Math.asin(-normalizedDir.y)
    val yawRad = Math.atan2(normalizedDir.x, normalizedDir.z)

    val pitch = Math.toDegrees(pitchRad)
    val unboundYaw = -Math.toDegrees(yawRad)
    val yaw = if (unboundYaw == 180) -180 else unboundYaw
    (pitch.toFloat, yaw.toFloat)
  }
}
