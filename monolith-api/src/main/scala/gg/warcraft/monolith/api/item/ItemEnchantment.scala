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

package gg.warcraft.monolith.api.item

trait ItemEnchantment {
  val level: Int
  val maxLevel: Int
  require(level > 0 && level <= maxLevel, {
    s"level is $level, must be > 0 and <= $maxLevel"
  })
}

trait BowEnchantment extends ItemEnchantment

trait CrossbowEnchantment extends ItemEnchantment

trait FishingRodEnchantment extends ItemEnchantment

trait ShearsEnchantment extends ItemEnchantment

trait TridentEnchantment extends ItemEnchantment

// ARMOR

trait BootsEnchantment extends ItemEnchantment

trait ChestplateEnchantment extends ItemEnchantment

trait HelmetEnchantment extends ItemEnchantment

trait LeggingsEnchantment extends ItemEnchantment

trait ArmorEnchantment
    extends BootsEnchantment
    with ChestplateEnchantment
    with HelmetEnchantment
    with LeggingsEnchantment

// TOOLS

trait AxeEnchantment extends ItemEnchantment

trait HoeEnchantment extends ItemEnchantment

trait PickaxeEnchantment extends ItemEnchantment

trait ShovelEnchantment extends ItemEnchantment

trait SwordEnchantment extends ItemEnchantment

trait ToolEnchantment
    extends AxeEnchantment
    with HoeEnchantment
    with PickaxeEnchantment
    with ShovelEnchantment
    with SwordEnchantment
