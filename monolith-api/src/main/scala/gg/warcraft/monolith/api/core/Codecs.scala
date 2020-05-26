/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

    def enumDecoder[T <: Enum[T]](f: String => T): Decoder[T] =
      Decoder.decodeString emapTry { it => { Try { f apply it } } }
    def enumEncoder[T <: Enum[T]]: Encoder[T] =
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

    def enumDecoder[T <: Enum[T]](f: String => T): MappedEncoding[String, T] =
      MappedEncoding { f }
    def enumEncoder[T <: Enum[T]]: MappedEncoding[T, String] =
      MappedEncoding { _.name }

    def teamDecoder(
        implicit teamService: TeamService
    ): MappedEncoding[String, Option[Team]] =
      MappedEncoding { teamService.teams.get }
    def teamEncoder: MappedEncoding[Team, String] =
      MappedEncoding { _.name }
  }
}
