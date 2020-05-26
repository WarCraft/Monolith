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

package gg.warcraft.monolith.api.combat

import gg.warcraft.monolith.api.core.Duration

object PotionEffect extends Enumeration {
  type Type = Value
  val ABSORPTION, BAD_OMEN, BLINDNESS, CONDUIT_POWER, CONFUSION, DAMAGE_RESISTANCE,
      DOLPHINS_GRACE, FAST_DIGGING, FIRE_RESISTANCE, GLOWING, HARM, HEAL,
      HEALTH_BOOST, HERO_OF_THE_VILLAGE, HUNGER, INCREASE_DAMAGE, INVISIBILITY, JUMP,
      LEVITATION, LUCK, NIGHT_VISION, POISON, REGENERATION, SATURATION, SLOW,
      SLOW_DIGGING, SLOW_FALLING, SPEED, UNLUCK, WATER_BREATHING, WEAKNESS, WITHER = Value

  def ambient(
      typed: PotionEffect.Type,
      level: Int,
      duration: Duration
  ): PotionEffect =
    PotionEffect(typed, level, duration, particles = true, ambient = true)

  def visual(
      typed: PotionEffect.Type,
      level: Int,
      duration: Duration,
      ambient: Boolean = false
  ): PotionEffect = PotionEffect(typed, level, duration, particles = true, ambient)
}

case class PotionEffect(
    typed: PotionEffect.Type,
    level: Int,
    duration: Duration,
    particles: Boolean = false,
    ambient: Boolean = false
)
