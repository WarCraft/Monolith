package gg.warcraft.monolith.api.core.handler

import java.time.{Instant, ZoneOffset}

import gg.warcraft.monolith.api.core.data.ServerDataService
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.core.DailyTickEvent

import scala.util.chaining._

class DailyTicker(implicit
    eventService: EventService,
    dataService: ServerDataService
) extends Runnable {

  import dataService._

  override def run(): Unit = {
    val today = Instant.now().atOffset(ZoneOffset.UTC).toLocalDate
    if (lastDailyTick != today) {
      updateLastDailyTick()
      DailyTickEvent().pipe { eventService.publish }
    }
  }
}
