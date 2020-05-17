package gg.warcraft.monolith.api.entity.team

import gg.warcraft.monolith.api.core.ColorCode
import gg.warcraft.monolith.api.effect.Particle

trait Team {
  val name: String
  val chatColor: ColorCode
  val particleColor: Particle.Color

  override def toString: String = name
}
