package gg.warcraft.monolith.api.player.statistic

case class Statistics private (statistics: Map[String, Statistic]) {
  def get(statistic: String): Int =
    statistics
      .get(statistic)
      .map { _.value }
      .getOrElse(0)
}
