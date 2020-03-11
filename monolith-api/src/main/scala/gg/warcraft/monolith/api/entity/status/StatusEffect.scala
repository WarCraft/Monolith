package gg.warcraft.monolith.api.entity.status

import gg.warcraft.monolith.api.combat.CombatSource
import gg.warcraft.monolith.api.core.Duration
import gg.warcraft.monolith.api.core.event.Event

trait StatusEffect extends Event.Handler {
  val source: CombatSource
  val name: String
  val group: String
  val duration: Option[Duration]
}
