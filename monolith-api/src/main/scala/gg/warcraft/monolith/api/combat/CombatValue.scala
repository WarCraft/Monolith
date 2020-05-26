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

object CombatValue {
  object Modifier extends Enumeration {
    type Type = Value
    val PERCENT, FLAT, OVERRIDE = Value

    def percent(source: CombatSource, value: Float): Modifier =
      Modifier(source, PERCENT, value)

    def flat(source: CombatSource, value: Float): Modifier =
      Modifier(source, FLAT, value)

    def `override`(source: CombatSource, value: Float): Modifier =
      Modifier(source, OVERRIDE, value)
  }

  case class Modifier(
      source: CombatSource,
      typed: CombatValue.Modifier.Type,
      value: Float
  )
}

case class CombatValue(
    source: CombatSource,
    base: Float,
    modifiers: List[CombatValue.Modifier] = Nil
) {
  lazy val modified: Float = {
    val overrideMods = modifiers filter { _.typed == CombatValue.Modifier.OVERRIDE }
    if (overrideMods.isEmpty) {
      val percentMods = modifiers filter { _.typed == CombatValue.Modifier.PERCENT }
      val flatMods = modifiers filter { _.typed == CombatValue.Modifier.FLAT }
      base + percentMods.map(base * _.value).sum + flatMods.map(_.value).sum
    } else overrideMods.map(_.value).max
  }
}
