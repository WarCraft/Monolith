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
  def add(xyz: (Int, Int, Int)): Vector3i =
    copy(x = this.x + xyz._1, y = this.y + xyz._2, z = this.z + xyz._3)

  def subtract(xyz: (Int, Int, Int)): Vector3i =
    copy(x = this.x - xyz._1, y = this.y - xyz._2, z = this.z - xyz._3)

  def multiply(scalar: Int): Vector3i =
    copy(x = x * scalar, y = y * scalar, z = z * scalar)
  def multiply(xyz: (Int, Int, Int)): Vector3i =
    copy(x = x * xyz._1, y = y * xyz._2, z = z * xyz._3)
}
