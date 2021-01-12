package gg.warcraft.monolith.api.player.statistic.archive

import gg.warcraft.monolith.api.player.statistic.StatisticRepository
import gg.warcraft.monolith.api.util.types.MonolithDate

import scala.concurrent.ExecutionContext.Implicits.global

class StatisticArchiveService(implicit
    statisticRepository: StatisticRepository,
    statisticArchiveRepository: StatisticArchiveRepository
) {
  def archiveStatistics(like: String, day: MonolithDate): Unit =
    statisticRepository
      .queryAll(like)
      .map { _.map { _.archive(day) } }
      .flatMap(statisticArchiveRepository.save)
      .flatMap { _ => statisticRepository.deleteAll(like) }
}
