package gg.warcraft.monolith.java.math

object Vector3i {
  private[math] implicit def fromScala(vec: ScalaVector3i): Vector3i =
    new Vector3i(vec)
}

class Vector3i private (val asScala: ScalaVector3i) {
  val x: Int = asScala.x
  val y: Int = asScala.y
  val z: Int = asScala.z

  def this(x: Int, y: Int, z: Int) = this(new ScalaVector3i(x, y, z))
  def this() = this(new ScalaVector3i)

  def withX(x: Int): Vector3i = asScala.copy(x = x)
  def withY(y: Int): Vector3i = asScala.copy(y = y)
  def withZ(z: Int): Vector3i = asScala.copy(z = z)

  def add(x: Int, y: Int, z: Int): Vector3i = asScala.add(x, y, z)
  def add(vec: Vector3i): Vector3i = asScala.add(vec)

  def subtract(x: Int, y: Int, z: Int): Vector3i = asScala.subtract(x, y, z)
  def subtract(vec: Vector3i): Vector3i = asScala.subtract(vec)

  def multiply(scalar: Int): Vector3i = asScala.multiply(scalar)
  def multiply(vec: Vector3i): Vector3i = asScala.multiply(vec)

  def toVector3f: Vector3f =
    gg.warcraft.monolith.api.math.Vector3i.toVector3f(asScala)
}
