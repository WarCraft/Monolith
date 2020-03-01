package gg.warcraft.monolith.api.math

import io.getquill.Embedded

object Vector3i {
  implicit def fromInts(ints: (Int, Int, Int)): Vector3i =
    Vector3i(ints._1, ints._2, ints._3)

  implicit def toInts(vec: Vector3i): (Int, Int, Int) =
    (vec.x, vec.y, vec.z)
  implicit def toVector3f(vec: Vector3i): Vector3f =
    Vector3f(vec.x.toFloat, vec.y.toFloat, vec.z.toFloat)
}

case class Vector3i(
    x: Int = 0,
    y: Int = 0,
    z: Int = 0
) extends Embedded {
  def add(x: Int, y: Int, z: Int): Vector3i =
    copy(x = this.x + x, y = this.y + y, z = this.z + z)
  def add(vec: Vector3i): Vector3i = add(vec.x, vec.y, vec.z)

  def subtract(x: Int, y: Int, z: Int): Vector3i =
    copy(x = this.x - x, y = this.y - y, z = this.z - z)
  def subtract(vec: Vector3i): Vector3i = subtract(vec.x, vec.y, vec.z)

  def multiply(scalar: Int): Vector3i =
    copy(x = x * scalar, y = y * scalar, z = z * scalar)
  def multiply(vec: Vector3i): Vector3i =
    copy(x = x * vec.x, y = y * vec.y, z = z * vec.z)

  // TODO remove after entire project is converted to Scala
  def withX(x: Int): Vector3i = copy(x = x)
  def withY(y: Int): Vector3i = copy(y = y)
  def withZ(z: Int): Vector3i = copy(z = z)
}
