package gg.warcraft.monolith.api.player.currency

case class Currencies private (currencies: Map[String, Currency]) {
  def get(currency: String): Int =
    currencies
      .get(currency)
      .map { _.amount }
      .getOrElse(0)

  def getLifetime(currency: String): Int =
    currencies
      .get(currency)
      .map { _.lifetime }
      .getOrElse(0)
}
