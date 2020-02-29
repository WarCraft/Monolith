package gg.warcraft.monolith.api.core

import gg.warcraft.monolith.api.util.ColorCode
import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.api.world.block.BlockTypeVariantOrState
import gg.warcraft.monolith.api.world.item.ItemTypeOrVariant
import io.circe.{Decoder, Encoder}
import io.getquill.MappedEncoding

import scala.util.Try

object Codecs {
  object Circe {
    implicit val colorDec: Decoder[ColorCode] =
      Decoder.decodeString.emapTry(it => { Try(ColorCode.valueOf(it)) })
    implicit val colorEnc: Encoder[ColorCode] =
      Encoder.encodeString.contramap[ColorCode](_.toString)

    implicit val blockDataDec: Decoder[BlockTypeVariantOrState] =
      Decoder.decodeString.emapTry(it => { Try(worldService.parse(it)) })
    implicit val blockDataEnc: Encoder[BlockTypeVariantOrState] =
      Encoder.encodeString.contramap[BlockTypeVariantOrState](_.toString)

    implicit val itemDataDec: Decoder[ItemTypeOrVariant] =
      Decoder.decodeString.emapTry(it => { Try(itemService.parse(it)) })
    implicit val itemDataEnc: Encoder[ItemTypeOrVariant] =
      Encoder.encodeString.contramap[ItemTypeOrVariant](_.toString)
  }

  object Quill {
    implicit val worldDec: MappedEncoding[String, World] =
      MappedEncoding(World.valueOf)
    implicit val worldEnc: MappedEncoding[World, String] =
      MappedEncoding(_.name)
  }
}
