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
