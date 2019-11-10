package gg.warcraft.monolith.api.core

import gg.warcraft.monolith.api.world.World
import io.getquill.MappedEncoding

object Encoders {
  val worldEnc: MappedEncoding[World, String] = MappedEncoding(_.name)
  val worldDec: MappedEncoding[String, World] = MappedEncoding(World.valueOf)
}
