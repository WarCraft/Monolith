package gg.warcraft.monolith.api.effect

import gg.warcraft.monolith.api.world.Location

object EffectRenderer {
  def apply(particle: Particle, vectors: EffectVectors): EffectRenderer =
    new EffectRenderer(particle, vectors)

  def iterative(particle: Particle, vectors: EffectVectors): EffectRenderer =
    new IterativeEffectRenderer(particle, vectors)
}

class EffectRenderer private[effect] (particle: Particle, vectors: EffectVectors) {
  def render(location: Location): Unit = {
    vectors.iterator foreach { vector =>
      val translation = location add vector
      particle display translation
    }
  }
}

private class IterativeEffectRenderer private[effect] (
    particle: Particle,
    vectors: EffectVectors
) extends EffectRenderer(particle, vectors) {
  private var iterator = vectors.iterator

  override def render(location: Location): Unit = {
    if (!iterator.hasNext) iterator = vectors.iterator
    val translation = location add iterator.next
    particle display translation
  }
}
