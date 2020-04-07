package gg.warcraft.monolith.api.core

import gg.warcraft.monolith.api.block.BlockTypeVariantOrState
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import gg.warcraft.monolith.api.item.{ItemService, ItemTypeOrVariant}
import gg.warcraft.monolith.api.world.WorldService
import io.circe.{Decoder, Encoder}
import io.getquill.MappedEncoding

import scala.util.Try

object Codecs {
  object Circe {
    def enumerDecoder[T <: Enumeration](f: String => T): Decoder[T] =
      Decoder.decodeString emapTry { it => { Try { f apply it } } }
    def enumerEncoder[T <: Enumeration]: Encoder[T] =
      Encoder.encodeString contramap [T] { _.toString }

    def enumDecoder[T: Enum](f: String => T): Decoder[T] =
      Decoder.decodeString emapTry { it => { Try { f apply it } } }
    def enumEncoder[T <: Enum[_]]: Encoder[T] =
      Encoder.encodeString contramap [T] { _.name }

    def blockDataDecoder(
        implicit worldService: WorldService
    ): Decoder[BlockTypeVariantOrState] =
      Decoder.decodeString emapTry { it => { Try { worldService parseData it } } }
    def blockDataEncoder: Encoder[BlockTypeVariantOrState] =
      Encoder.encodeString contramap [BlockTypeVariantOrState] { _.toString }

    def itemDataDecoder(
        implicit itemService: ItemService
    ): Decoder[ItemTypeOrVariant] =
      Decoder.decodeString emapTry { it => { Try { itemService parseData it } } }
    def itemDataEncoder: Encoder[ItemTypeOrVariant] =
      Encoder.encodeString contramap [ItemTypeOrVariant] { _.toString }
  }

  object Quill {
    def enumerDecoder[T <: Enumeration](f: String => T): MappedEncoding[String, T] =
      MappedEncoding { f }
    def enumerEncoder[T <: Enumeration]: MappedEncoding[T, String] =
      MappedEncoding { _.toString }

    def enumDecoder[T: Enum](f: String => T): MappedEncoding[String, T] =
      MappedEncoding { f }
    def enumEncoder[T <: Enum[_]]: MappedEncoding[T, String] =
      MappedEncoding { _.name }

    def teamDecoder(
        implicit teamService: TeamService
    ): MappedEncoding[String, Option[Team]] =
      MappedEncoding { teamService.getTeam }
    def teamEncoder: MappedEncoding[Team, String] =
      MappedEncoding { _.name }
  }
}
