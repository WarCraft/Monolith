package gg.warcraft.monolith.api

import gg.warcraft.monolith.api.core.EventService

object Implicits {
  implicit lazy val eventService: EventService = new EventService
}
