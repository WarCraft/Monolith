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

package gg.warcraft.monolith.spigot.core

import gg.warcraft.monolith.api.core.Color
import org.bukkit.{Color => SpigotColor}

class SpigotColorMapper {
  def map(color: Color): SpigotColor = color match {
    case Color.Aqua    => SpigotColor.AQUA
    case Color.Black   => SpigotColor.BLACK
    case Color.Blue    => SpigotColor.BLUE
    case Color.Fuchsia => SpigotColor.FUCHSIA
    case Color.Gray    => SpigotColor.GRAY
    case Color.Green   => SpigotColor.GREEN
    case Color.Lime    => SpigotColor.LIME
    case Color.Maroon  => SpigotColor.MAROON
    case Color.Navy    => SpigotColor.NAVY
    case Color.Olive   => SpigotColor.OLIVE
    case Color.Orange  => SpigotColor.ORANGE
    case Color.Purple  => SpigotColor.PURPLE
    case Color.Red     => SpigotColor.RED
    case Color.Silver  => SpigotColor.SILVER
    case Color.Teal    => SpigotColor.TEAL
    case Color.White   => SpigotColor.WHITE
    case Color.Yellow  => SpigotColor.YELLOW
  }

  private val spigotColors = List(
    SpigotColor.AQUA,
    SpigotColor.BLACK,
    SpigotColor.BLUE,
    SpigotColor.FUCHSIA,
    SpigotColor.GRAY,
    SpigotColor.GREEN,
    SpigotColor.LIME,
    SpigotColor.MAROON,
    SpigotColor.NAVY,
    SpigotColor.OLIVE,
    SpigotColor.ORANGE,
    SpigotColor.PURPLE,
    SpigotColor.RED,
    SpigotColor.SILVER,
    SpigotColor.TEAL,
    SpigotColor.WHITE,
    SpigotColor.YELLOW
  )

  def map(color: SpigotColor): Option[Color] = {
    spigotColors.find { _ == color }.flatMap {
      case SpigotColor.AQUA    => Some(Color.Aqua)
      case SpigotColor.BLACK   => Some(Color.Black)
      case SpigotColor.BLUE    => Some(Color.Blue)
      case SpigotColor.FUCHSIA => Some(Color.Fuchsia)
      case SpigotColor.GRAY    => Some(Color.Gray)
      case SpigotColor.GREEN   => Some(Color.Green)
      case SpigotColor.LIME    => Some(Color.Lime)
      case SpigotColor.MAROON  => Some(Color.Maroon)
      case SpigotColor.NAVY    => Some(Color.Navy)
      case SpigotColor.OLIVE   => Some(Color.Olive)
      case SpigotColor.ORANGE  => Some(Color.Orange)
      case SpigotColor.PURPLE  => Some(Color.Purple)
      case SpigotColor.RED     => Some(Color.Red)
      case SpigotColor.SILVER  => Some(Color.Silver)
      case SpigotColor.TEAL    => Some(Color.Teal)
      case SpigotColor.WHITE   => Some(Color.White)
      case SpigotColor.YELLOW  => Some(Color.Yellow)
      case _                   => None
    }
  }
}
