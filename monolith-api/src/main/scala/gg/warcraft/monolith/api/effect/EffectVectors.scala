package gg.warcraft.monolith.api.effect

import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.util.MathUtils

object EffectVectors {
  private def randomVector: Vector3f = MathUtils.randomVector

  def circle(radius: Float, count: Int): EffectVectors = {
    val vectors = (0 to count) map { _ =>
      (randomVector * radius) copy (y = 0)
    }
    new EffectVectors(vectors.toList)
  }

  def dome(radius: Float, count: Int): EffectVectors = {
    val vectors = (0 to count) map { _ =>
      val vector = randomVector
      (vector * radius) copy (y = Math abs vector.y)
    }
    new EffectVectors(vectors.toList)
  }

  def line(origin: Vector3f, target: Vector3f, count: Int): EffectVectors = {
    val delta = (target - origin) / count
    val vectors = (0 to count) map { i => origin + (delta * i) }
    new EffectVectors(vectors.toList)
  }

  def origin(): EffectVectors = new EffectVectors(Vector3f() :: Nil)

  def point(point: Vector3f): EffectVectors = new EffectVectors(point :: Nil)

  def ring(radius: Float, count: Int): EffectVectors = {
    var angle = 0f
    val delta = 2 * Math.PI.toFloat / count
    val vectors = (0 to count) map { _ =>
      val x = radius * (Math cos angle)
      val z = radius * (Math sin angle)
      angle += delta
      Vector3f(x, 0, z)
    }
    new EffectVectors(vectors.toList)
  }

  def sphere(radius: Float, count: Int): EffectVectors = {
    val vectors = (0 to count) map { _ => randomVector * radius }
    new EffectVectors(vectors.toList)
  }
}

class EffectVectors(vectors: List[Vector3f]) extends Iterable[Vector3f] {
  override def iterator: Iterator[Vector3f] = vectors.iterator

  def add(offset: Vector3f): EffectVectors = {
    val newVectors = vectors map { _ + offset }
    new EffectVectors(newVectors)
  }

  def rotateAxisX(angle: Float): EffectVectors = {
    val newVectors = vectors map { _ rotateX angle }
    new EffectVectors(newVectors)
  }

  def rotateAxisY(angle: Float): EffectVectors = {
    val newVectors = vectors map { _ rotateY angle }
    new EffectVectors(newVectors)
  }

  def rotateAxisZ(angle: Float): EffectVectors = {
    val newVectors = vectors map { _ rotateZ angle }
    new EffectVectors(newVectors)
  }
}
