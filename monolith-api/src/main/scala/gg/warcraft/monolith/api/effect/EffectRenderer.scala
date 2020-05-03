package gg.warcraft.monolith.api.effect

import gg.warcraft.monolith.api.world.Location

object EffectRenderer {
  def apply(particle: Particle, vectors: EffectVectors)(
      implicit particleAdapter: ParticleAdapter
  ): EffectRenderer =
    new EffectRenderer(particle, vectors)

  def iterative(particle: Particle, vectors: EffectVectors)(
      implicit particleAdapter: ParticleAdapter
  ): EffectRenderer =
    new IterativeEffectRenderer(particle, vectors)
}

class EffectRenderer private[effect] (particle: Particle, vectors: EffectVectors)(
    implicit particleAdapter: ParticleAdapter
) {
  def render(location: Location): Unit = {
    vectors.iterator foreach { vector =>
      val translation = location + vector
      particle display translation
    }
  }
}

private class IterativeEffectRenderer(particle: Particle, vectors: EffectVectors)(
    implicit particleAdapter: ParticleAdapter
) extends EffectRenderer(particle, vectors) {
  private var iterator = vectors.iterator

  override def render(location: Location): Unit = {
    if (!iterator.hasNext) iterator = vectors.iterator
    val translation = location + iterator.next
    particle display translation
  }
}
