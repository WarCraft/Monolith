package gg.warcraft.monolith.api.player.statistic

class Statistics(private[statistic] val all: Map[String, Statistic]) {
  def get(statistic: String): Long =
    all.get(statistic).map { _.value }.getOrElse(0)
}
