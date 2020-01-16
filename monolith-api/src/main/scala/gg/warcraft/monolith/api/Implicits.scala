package gg.warcraft.monolith.api

import gg.warcraft.monolith.api.core.event.EventService

object Implicits {
  implicit lazy val eventService: EventService = new EventService
}
