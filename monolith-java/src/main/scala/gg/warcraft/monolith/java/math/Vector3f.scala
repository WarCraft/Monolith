package gg.warcraft.monolith.java.math

object Vector3f {
  val ZERO_PITCH_YAW: Vector3f =
    gg.warcraft.monolith.api.math.Vector3f.ZERO_PITCH_YAW

  private[java] implicit def fromScala(vec: ScalaVector3f): Vector3f =
    new Vector3f(vec)
  private[java] implicit def toScala(vec: Vector3f): ScalaVector3f =
    vec.asScala

  def fromPitchYaw(pitch: Float, yaw: Float): Vector3f =
    gg.warcraft.monolith.api.math.Vector3f.apply(pitch, yaw)
}

class Vector3f private[java] (private[java] val asScala: ScalaVector3f) {
  val x: Float = asScala.x
  val y: Float = asScala.y
  val z: Float = asScala.z
  lazy val lengthSquared: Float = asScala.lengthSquared
  lazy val length: Float = asScala.length
  lazy val inverseLength: Float = asScala.inverseLength

  def this(x: Float, y: Float, z: Float) = this(new ScalaVector3f(x, y, z))
  def this() = this(new ScalaVector3f)

  def withX(x: Float): Vector3f = asScala.copy(x = x)
  def withY(y: Float): Vector3f = asScala.copy(y = y)
  def withZ(z: Float): Vector3f = asScala.copy(z = z)

  def add(x: Float, y: Float, z: Float): Vector3f = asScala.add(x, y, z)
  def add(vec: Vector3f): Vector3f = asScala.add(vec)

  def subtract(x: Float, y: Float, z: Float): Vector3f = asScala.subtract(x, y, z)
  def subtract(vec: Vector3f): Vector3f = asScala.subtract(vec)

  def multiply(scalar: Float): Vector3f = asScala.multiply(scalar)
  def multiply(vec: Vector3f): Vector3f = asScala.multiply(vec)

  def distanceTo(target: Vector3f): Float = asScala.distanceTo(target)
  def normalize: Vector3f = asScala.normalize

  def toPitchYaw: Array[Float] =
    asScala.toPitchYaw.productIterator.toArray.asInstanceOf[Array[Float]]
  def toVector3i: Vector3i =
    gg.warcraft.monolith.api.math.Vector3f.toVector3i(asScala)
}
