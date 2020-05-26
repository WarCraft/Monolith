/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.api.effect

import gg.warcraft.monolith.api.world.Location

object EffectRenderer {
  def apply(particle: Particle, vectors: EffectVectors)(implicit
      particleAdapter: ParticleAdapter
  ): EffectRenderer = new EffectRenderer(particle, vectors)

  def iterative(particle: Particle, vectors: EffectVectors)(implicit
      particleAdapter: ParticleAdapter
  ): EffectRenderer = new IterativeEffectRenderer(particle, vectors)
}

class EffectRenderer private[effect] (
    particle: Particle,
    vectors: EffectVectors
)(implicit
    particleAdapter: ParticleAdapter
) {
  def render(location: Location): Unit =
    vectors.iterator.foreach { vector =>
      val translation = location + vector
      particle.display(translation)
    }
}

private class IterativeEffectRenderer(
    particle: Particle,
    vectors: EffectVectors
)(implicit
    particleAdapter: ParticleAdapter
) extends EffectRenderer(particle, vectors) {
  private var iterator = vectors.iterator

  override def render(location: Location): Unit = {
    if (!iterator.hasNext) iterator = vectors.iterator
    val translation = location + iterator.next
    particle.display(translation)
  }
}
