package gg.warcraft.monolith.api.util

import gg.warcraft.monolith.api.math.Vector3f

import scala.util.Random

object MathUtils {
  private final val TWO_PI = 2 * Math.PI.toFloat

  private val random = new Random(System.currentTimeMillis())

  def randomAngle: Float = random.nextFloat() * TWO_PI

  def randomVector: Vector3f = {
    val x = random.nextFloat() * 2f - 1f
    val y = random.nextFloat() * 2f - 1f
    val z = random.nextFloat() * 2f - 1f
    Vector3f(x, y, z).normalize
  }

  def randomCircleVector: Vector3f = {
    val radian = randomAngle
    val x = Math.cos(radian).toFloat
    val z = Math.sin(radian).toFloat
    Vector3f(x, 0, z)
  }
}
