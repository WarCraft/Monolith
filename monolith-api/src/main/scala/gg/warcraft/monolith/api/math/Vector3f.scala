package gg.warcraft.monolith.api.math

import io.getquill.Embedded

object Vector3f {
  val ZERO_PITCH_YAW: Vector3f = Vector3f(0, 0, 1)

  def apply(x: Double, y: Double, z: Double): Vector3f =
    Vector3f(x.toFloat, y.toFloat, z.toFloat)

  def apply(pitch: Float, yaw: Float): Vector3f = {
    require(pitch >= -90 && pitch <= 90, {
      s"pitch is $pitch, must be >= -90 and <= 90"
    })

    var clampedYaw = yaw
    while (clampedYaw < -180f) clampedYaw += 360f
    while (clampedYaw >= 180f) clampedYaw -= 360f

    // Add 90 degrees to correct for an extra rotation Minecraft added
    val pitchRad = Math.toRadians(pitch + 90f)
    val yawRad = Math.toRadians(clampedYaw + 90f)

    val sinPitchRad = Math.sin(pitchRad)
    val x = sinPitchRad * Math.cos(yawRad)
    val y = sinPitchRad * Math.sin(yawRad)
    val z = Math.cos(pitchRad)

    // Normally the Z-axis defines height, but in Minecraft this is the Y-axis
    Vector3f(x.toFloat, z.toFloat, y.toFloat)
  }

  implicit def fromFloats(floats: (Float, Float, Float)): Vector3f =
    Vector3f(floats._1, floats._2, floats._3)
  implicit def fromJomlVector3f(vec: JomlVector3fc): Vector3f =
    Vector3f(vec.x, vec.y, vec.z)

  implicit def toFloats(vec: Vector3f): (Float, Float, Float) =
    (vec.x, vec.y, vec.z)
  implicit def toVector3i(vec: Vector3f): Vector3i =
    Vector3i(vec.x.toInt, vec.y.toInt, vec.z.toInt)
  implicit def toJomlVector3f(vec: Vector3f): JomlVector3f =
    new JomlVector3f(vec.x, vec.y, vec.z)
}

case class Vector3f(
    x: Float = 0,
    y: Float = 0,
    z: Float = 0
) extends Embedded {
  private lazy val jomlVector = new JomlVector3f(x, y, z)
  private lazy val jomlOut = new JomlVector3f()

  lazy val lengthSquared: Float = x * x + y * y + z * z
  lazy val length: Float = Math.sqrt(lengthSquared).toFloat
  lazy val inverseLength: Float = 1f / length

  def add(x: Float, y: Float, z: Float): Vector3f =
    copy(x = this.x + x, y = this.y + y, z = this.z + z)
  def add(vec: Vector3f): Vector3f = add(vec.x, vec.y, vec.z)

  def subtract(x: Float, y: Float, z: Float): Vector3f =
    copy(x = this.x - x, y = this.y - y, z = this.z - z)
  def subtract(vec: Vector3f): Vector3f = subtract(vec.x, vec.y, vec.z)

  def multiply(scalar: Float): Vector3f =
    copy(x = x * scalar, y = y * scalar, z = z * scalar)
  def multiply(vec: Vector3f): Vector3f =
    copy(x = x * vec.x, y = y * vec.y, z = z * vec.z)

  def divide(scalar: Float): Vector3f =
    copy(x = x / scalar, y = y / scalar, z = z / scalar)
  def divide(vec: Vector3f): Vector3f =
    copy(x = x / vec.x, y = y / vec.y, z = z / vec.z)

  def rotateX(angle: Float): Vector3f = {
    jomlVector.rotateX(angle, jomlOut)
    jomlOut
  }

  def rotateY(angle: Float): Vector3f = {
    jomlVector.rotateY(angle, jomlOut)
    jomlOut
  }

  def rotateZ(angle: Float): Vector3f = {
    jomlVector.rotateZ(angle, jomlOut)
    jomlOut
  }

  def distanceTo(target: Vector3f): Float = target.subtract(this).length

  def normalize: Vector3f =
    Vector3f(x * inverseLength, y * inverseLength, z * inverseLength)

  def toPitchYaw: (Float, Float) = {
    val normalized = normalize
    val pitchRad = Math.asin(-normalized.y)
    val yawRad = Math.atan2(normalized.x, normalized.z)

    val pitch = Math.toDegrees(pitchRad)
    val unboundYaw = -Math.toDegrees(yawRad)
    val yaw = if (unboundYaw == 180f) -180f else unboundYaw
    (pitch.toFloat, yaw.toFloat)
  }

  // TODO remove after entire project is converted to Scala
  def withX(x: Float): Vector3f = copy(x = x)
  def withY(y: Float): Vector3f = copy(y = y)
  def withZ(z: Float): Vector3f = copy(z = z)
}
