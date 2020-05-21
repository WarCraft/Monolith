package gg.warcraft.monolith.api.player.currency

class Currencies(private[currency] val all: Map[String, Currency]) {
  def get(currency: String): Int =
    all.get(currency).map { _.amount }.getOrElse(0)

  def getLifetime(currency: String): Int =
    all.get(currency).map { _.lifetime }.getOrElse(0)
}
