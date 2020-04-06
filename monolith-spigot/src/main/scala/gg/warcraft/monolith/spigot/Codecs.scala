package gg.warcraft.monolith.spigot

import gg.warcraft.monolith.api.block.BlockTypeVariantOrState
import gg.warcraft.monolith.api.core.ColorCode
import gg.warcraft.monolith.api.entity.Entity
import gg.warcraft.monolith.api.item.ItemTypeOrVariant
import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.spigot.Implicits._
import io.circe.{Decoder, Encoder}
import io.getquill.MappedEncoding

import scala.util.Try

object Codecs {
  object Circe {
    implicit val colorDec: Decoder[ColorCode.Type] =
      Decoder.decodeString.emapTry(it => { Try(ColorCode.withName(it)) })
    implicit val colorEnc: Encoder[ColorCode.Type] =
      Encoder.encodeString.contramap[ColorCode.Type](_.toString)

    implicit val entityDec: Decoder[Entity.Type] =
      Decoder.decodeString.emapTry(it => { Try(Entity.Type.withName(it)) })
    implicit val entityEnc: Encoder[Entity.Type] =
      Encoder.encodeString.contramap[Entity.Type](_.toString)

    implicit val blockDataDec: Decoder[BlockTypeVariantOrState] =
      Decoder.decodeString.emapTry(it => { Try(worldService.parseData(it)) })
    implicit val blockDataEnc: Encoder[BlockTypeVariantOrState] =
      Encoder.encodeString.contramap[BlockTypeVariantOrState](_.toString)

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
