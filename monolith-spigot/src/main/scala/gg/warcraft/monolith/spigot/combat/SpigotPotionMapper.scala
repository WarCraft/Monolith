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

package gg.warcraft.monolith.spigot.combat

import gg.warcraft.monolith.api.combat.PotionEffect
import gg.warcraft.monolith.api.core.Duration._
import org.bukkit.potion.{PotionEffectType => SpigotPotionEffectType}

class SpigotPotionMapper {
  def map(typed: SpigotPotionEffectType): PotionEffect.Type =
    PotionEffect.withName(typed.getName)

  def map(typed: PotionEffect.Type): SpigotPotionEffectType =
    SpigotPotionEffectType.getByName(typed.toString)

  def map(effect: PotionEffect): SpigotPotionEffect = {
    import effect._
    new SpigotPotionEffect(map(typed), duration.ticks, level, ambient, particles)
  }

  def map(effect: SpigotPotionEffect): PotionEffect = {
    import effect._
    PotionEffect(
      map(getType),
      getAmplifier,
      getDuration.seconds,
      hasParticles,
      isAmbient
    )
  }
}
