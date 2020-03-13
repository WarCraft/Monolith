package gg.warcraft.monolith.spigot

import gg.warcraft.monolith.api.entity.EntityType
import gg.warcraft.monolith.api.util.ColorCode
import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.api.block.BlockTypeVariantOrState
import gg.warcraft.monolith.api.item.ItemTypeOrVariant
import gg.warcraft.monolith.spigot.Implicits._
import io.circe.{Decoder, Encoder}
import io.getquill.MappedEncoding

import scala.util.Try

object Codecs {
  object Circe {
    implicit val colorDec: Decoder[ColorCode] =
      Decoder.decodeString.emapTry(it => { Try(ColorCode.valueOf(it)) })
    implicit val colorEnc: Encoder[ColorCode] =
      Encoder.encodeString.contramap[ColorCode](_.toString)

    implicit val entityDec: Decoder[EntityType] =
      Decoder.decodeString.emapTry(it => { Try(EntityType.valueOf(it)) })
    implicit val entityEnc: Encoder[EntityType] =
      Encoder.encodeString.contramap[EntityType](_.toString)

    implicit val blockDataDec: Decoder[BlockTypeVariantOrState] =
      Decoder.decodeString.emapTry(it => { Try(worldService.parseData(it)) })
    implicit val blockDataEnc: Encoder[BlockTypeVariantOrState] =
      Encoder.encodeString.contramap[BlockTypeVariantOrState](_.toString)
//
    implicit val itemDataDec: Decoder[ItemTypeOrVariant] =
      Decoder.decodeString.emapTry(it => { Try(itemService.parseData(it)) })
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
