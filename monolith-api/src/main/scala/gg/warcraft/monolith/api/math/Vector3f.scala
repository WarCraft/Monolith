package gg.warcraft.monolith.api.math

case class Vector3f(
    x: Float = 0,
    y: Float = 0,
    z: Float = 0
) {
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

  def toVector3i: Vector3i =
    Vector3i(x.toInt, y.toInt, z.toInt)

  /* Java interop */

  def this() = this(0, 0, 0)

  def this(x: Double, y: Double, z: Double) =
    this(x.toFloat, y.toFloat, z.toFloat)

  def withX(x: Float): Vector3f = copy(x = x)
  def withY(y: Float): Vector3f = copy(y = y)
  def withZ(z: Float): Vector3f = copy(z = z)
}

object Vector3f {
  val ZERO_PITCH_YAW = Vector3f(0, 0, 1)

  def apply(x: Double, y: Double, z: Double): Vector3f =
    Vector3f(x.toFloat, y.toFloat, z.toFloat)

  def apply(pitch: Float, yaw: Float): Vector3f = {
    if (pitch < -90f) {
      throw new IllegalArgumentException(s"pitch $pitch must be >= -90")
    } else if (pitch > 90f) {
      throw new IllegalArgumentException(s"pitch $pitch must be <= 90")
    }

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
}
