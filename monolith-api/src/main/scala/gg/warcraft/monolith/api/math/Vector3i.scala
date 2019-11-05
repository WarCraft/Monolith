package gg.warcraft.monolith.api.math

case class Vector3i(
    x: Int = 0,
    y: Int = 0,
    z: Int = 0
) {
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

  def toVector3f: Vector3f = Vector3f(x.toFloat, y.toFloat, z.toFloat)

  /* Java interop */

  def this() = this(0, 0, 0)

  def withX(x: Int): Vector3i = copy(x = x)
  def withY(y: Int): Vector3i = copy(y = y)
  def withZ(z: Int): Vector3i = copy(z = z)
}
