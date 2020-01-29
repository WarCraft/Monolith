package gg.warcraft.monolith.api.core

import gg.warcraft.monolith.api.util.ColorCode
import gg.warcraft.monolith.api.world.World
import io.circe.{Decoder, Encoder}
import io.getquill.MappedEncoding

import scala.util.Try

object Codecs {
  object Circe {
    implicit val colorDec: Decoder[ColorCode] =
      Decoder.decodeString.emapTry(it => { Try(ColorCode.valueOf(it)) })
    implicit val colorEnc: Encoder[ColorCode] =
      Encoder.encodeString.contramap[ColorCode](_.name)
  }

  object Quill {
    implicit val worldDec: MappedEncoding[String, World] =
      MappedEncoding(World.valueOf)
    implicit val worldEnc: MappedEncoding[World, String] =
      MappedEncoding(_.name)
  }
}
