package gg.warcraft.monolith.api.entity.team

import gg.warcraft.monolith.api.core.ColorCode
import gg.warcraft.monolith.api.core.event.Event
import gg.warcraft.monolith.api.effect.Particle

case class TeamRegisteredEvent(
    name: String,
    chatColor: ColorCode,
    particleColor: Particle.Color
) extends Event
