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

package gg.warcraft.monolith.api.world.portal

import gg.warcraft.monolith.api.effect.Effect
import gg.warcraft.monolith.api.entity.Entity
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.{Direction, Location}

case class Portal(
    entry: Location,
    exit: Location,
    orientation: Option[Vector3f]
) {
  private var _predicate: Entity => Boolean = _ => false
  def predicate: Entity => Boolean = _predicate
  def predicate_=(predicate: Entity => Boolean): Unit = _predicate = predicate

  private var _effect: Option[Effect] = None
  def effect: Option[Effect] = _effect
  def effect_=(effect: Option[Effect]): Unit = {
    _effect.foreach { _.stop() }
    _effect = effect
  }
}

object Portal {
  case class Config(
      entry: Location,
      exit: Location,
      orientation: Option[Direction]
  )
}
