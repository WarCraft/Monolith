package gg.warcraft.monolith.api.util

import gg.warcraft.monolith.api.util.types.tags._
import shapeless.tag.@@

import java.time.{LocalDate, LocalDateTime}

object types {
  type MonolithDate = LocalDate @@ MonolithDateTag
  type MonolithDateTime = LocalDateTime @@ MonolithDateTimeTag

  object tags {
    trait MonolithDateTag
    trait MonolithDateTimeTag
  }
}
