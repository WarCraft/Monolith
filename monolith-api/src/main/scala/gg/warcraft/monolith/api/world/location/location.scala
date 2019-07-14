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

  def validateYaw(yaw: Float): Unit = {
    println("[DEBUG] Validating yaw: " + yaw)
    if (yaw < -180) {
      throw new IllegalArgumentException(s"yaw must be >= -180, but was $yaw")
    } else if (yaw >= 360) {
      throw new IllegalArgumentException(s"yaw must be < 360, but was $yaw")
    }
  }

  def orientationToDirection(pitch: Float, yaw: Float): Vector3f = {
    val pitchRadians = Math.toRadians(pitch)
    val yawRadians = Math.toRadians(yaw)
    val cosYawRadians = Math.cos(yawRadians)
    val directionX = -Math.sin(yawRadians)
    val directionY = -(cosYawRadians * Math.sin(pitchRadians))
    val directionZ = cosYawRadians * Math.cos(pitchRadians)
    new Vector3f(directionX.toFloat, directionY.toFloat, directionZ.toFloat)
  }

  def directionToOrientation(direction: Vector3fc): (Float, Float) = {
    val normalizedDirection = direction.normalize(new Vector3f)
    val pitchRadians = Math.asin(-normalizedDirection.y)
    val yawRadians = Math.atan2(normalizedDirection.x, normalizedDirection.z)
    val pitch = Math.toDegrees(pitchRadians)
    val unboundYaw = -Math.toDegrees(yawRadians)
    val yaw = if (unboundYaw == -180) 180 else unboundYaw
    (pitch.toFloat, yaw.toFloat)
  }
}
