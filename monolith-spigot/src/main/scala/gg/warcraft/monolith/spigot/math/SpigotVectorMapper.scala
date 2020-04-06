package gg.warcraft.monolith.spigot.math

import gg.warcraft.monolith.api.math.{Vector3f, Vector3i}

class SpigotVectorMapper {
  def map(vector: SpigotVector): Vector3f =
    Vector3f(vector.getX, vector.getY, vector.getZ)

  def map(vector: SpigotVector): Vector3i =
    Vector3i(vector.getX.toInt, vector.getY.toInt, vector.getZ.toInt)

  def map(vector: Vector3f): SpigotVector =
    new SpigotVector(vector.x, vector.y, vector.z)

  def map(vector: Vector3i): SpigotVector =
    new SpigotVector(vector.x, vector.y, vector.z)
}
