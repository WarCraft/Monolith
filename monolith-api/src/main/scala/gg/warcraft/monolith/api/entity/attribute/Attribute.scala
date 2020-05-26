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

package gg.warcraft.monolith.api.entity.attribute

trait Attribute {
  val name: String
}

object Attribute {
  case class Modifier(
      attribute: Attribute,
      typed: Modifier.Type,
      value: Float
  )

  object Modifier extends Enumeration {
    type Type = Value
    val FLAT, PERCENT = Value

    def flat(attribute: Attribute, value: Float): Modifier =
      Modifier(attribute, FLAT, value)

    def percent(attribute: Attribute, value: Float): Modifier =
      Modifier(attribute, PERCENT, value)
  }

  sealed abstract class Generic(override val name: String) extends Attribute
  object Generic {
    case object ARMOR extends Generic("Armor")
    case object ARMOR_TOUGHNESS extends Generic("Armor Toughness")
    case object ATTACK_DAMAGE extends Generic("Attack Damage")
    case object ATTACK_SPEED extends Generic("Attack Speed")
    case object FLYING_SPEED extends Generic("Flying Speed")
    case object FOLLOW_RANGE extends Generic("Follow Range")
    case object KNOCKBACK_RESISTANCE extends Generic("Knockback Resistance")
    case object LUCK extends Generic("Luck")
    case object MAX_HEALTH extends Generic("Max Health")
    case object MOVEMENT_SPEED extends Generic("Movement Speed")
  }

  sealed abstract class Horse(override val name: String) extends Attribute
  object Horse {
    case object JUMP_STRENGTH extends Horse("Jump Strength")
  }

  sealed abstract class Zombie(override val name: String) extends Attribute
  object Zombie {
    case object SPAWN_REINFORCEMENTS extends Horse("Spawn Reinforcements")
  }
}
