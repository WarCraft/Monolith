package gg.warcraft.monolith.api.entity.status

import gg.warcraft.monolith.api.combat.CombatSource
import gg.warcraft.monolith.api.core.EventHandler
import gg.warcraft.monolith.api.util.Duration

trait StatusEffect extends EventHandler {
  val source: CombatSource
  val name: String
  val group: String
  val duration: Option[Duration]
  val callback: () => () = () => ()
}
