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

package gg.warcraft.monolith.api.player.currency

import java.util.UUID

private[currency] case class Currency(
    playerId: UUID,
    currency: String,
    amount: Int = 0,
    lifetime: Int = 0
) {
  def add(amount: Int): Currency = copy(
    amount = this.amount + amount,
    lifetime = this.lifetime + amount
  )

  def remove(amount: Int): Currency = {
    if (this.amount < amount) {
      val err = s"Can not remove $amount from ${this.amount} $currency for $playerId"
      throw new IllegalArgumentException(err)
    } else copy(amount = this.amount - amount)
  }

  def revoke(amount: Int): Currency = copy(
    amount = this.amount - amount,
    lifetime = this.lifetime - amount
  )
}
