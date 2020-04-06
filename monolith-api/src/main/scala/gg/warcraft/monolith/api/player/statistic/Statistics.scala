package gg.warcraft.monolith.api.player.statistic

class Statistics(private[statistic] val statistics: Map[String, Statistic]) {
  def get(statistic: String): Long =
    statistics
      .get(statistic)
      .map { _.value }
      .getOrElse(0)
}
