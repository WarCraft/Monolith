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
import gg.warcraft.monolith.api.core.Duration._
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import gg.warcraft.monolith.api.item.{ItemService, ItemTypeOrVariant}
import gg.warcraft.monolith.api.world.{Direction, World, WorldService}
import io.circe.{Decoder, Encoder}
import io.getquill.MappedEncoding

import scala.util.Try

object Codecs {
  object Circe {
    def enumerDecoder[T <: Enumeration](f: String => T): Decoder[T] =
      Decoder.decodeString.emapTry { it => { Try { f(it) } } }
    def enumerEncoder[T <: Enumeration]: Encoder[T] =
      Encoder.encodeString.contramap[T] { _.toString }

    def enumDecoder[T <: Enum[T]](f: String => T): Decoder[T] =
      Decoder.decodeString.emapTry { it => { Try { f(it) } } }
    def enumEncoder[T <: Enum[T]]: Encoder[T] =
      Encoder.encodeString.contramap[T] { _.name }

    def blockDataDecoder(implicit
        worldService: WorldService
    ): Decoder[BlockTypeVariantOrState] =
      Decoder.decodeString.emapTry { it => { Try { worldService.parseData(it) } } }
    implicit lazy val blockDataEncoder: Encoder[BlockTypeVariantOrState] =
      Encoder.encodeString.contramap { _.toString }

    implicit lazy val directionDecoder: Decoder[Direction] =
      enumDecoder(Direction.valueOf)
    implicit lazy val directionEncoder: Encoder[Direction] =
      enumEncoder[Direction]

    implicit lazy val durationDecoder: Decoder[Duration] =
      Decoder.decodeInt.emapTry { it => { Try { it.seconds } } }
    // NOTE not sure what default durationEncoder to use

    def itemDataDecoder(implicit
        itemService: ItemService
    ): Decoder[ItemTypeOrVariant] =
      Decoder.decodeString.emapTry { it => { Try { itemService.parseData(it) } } }
    implicit lazy val itemDataEncoder: Encoder[ItemTypeOrVariant] =
      Encoder.encodeString.contramap { _.toString }

    def teamDecoder(implicit
        teamService: TeamService
    ): Decoder[Option[Team]] =
      Decoder.decodeString.emapTry { it => { Try { teamService.teams.get(it) } } }
    implicit lazy val teamEncoder: Encoder[Team] =
      Encoder.encodeString.contramap { _.name }

    def worldDecoder(implicit worldService: WorldService): Decoder[World] =
      Decoder.decodeString.emapTry { it => Try { worldService.getWorld(it) } }
    implicit lazy val worldEncoder: Encoder[World] =
      Encoder.encodeString.contramap { _.name }
  }

  object Quill {
    def enumerDecoder[T <: Enumeration](f: String => T): MappedEncoding[String, T] =
      MappedEncoding(f)
    def enumerEncoder[T <: Enumeration]: MappedEncoding[T, String] =
      MappedEncoding { _.toString }

    def enumDecoder[T <: Enum[T]](f: String => T): MappedEncoding[String, T] =
      MappedEncoding(f)
    def enumEncoder[T <: Enum[T]]: MappedEncoding[T, String] =
      MappedEncoding { _.name }

    implicit lazy val directionDecoder: MappedEncoding[String, Direction] =
      enumDecoder(Direction.valueOf)
    implicit lazy val directionEncoder: MappedEncoding[Direction, String] =
      enumEncoder[Direction]

    implicit lazy val durationDecoder: MappedEncoding[Int, Duration] =
      MappedEncoding { _.millis }
    implicit lazy val durationEncoder: MappedEncoding[Duration, Int] =
      MappedEncoding { _.millis }

    def teamDecoder(implicit
        teamService: TeamService
    ): MappedEncoding[String, Team] =
      MappedEncoding { teamService.teams(_) }
    implicit lazy val teamEncoder: MappedEncoding[Team, String] =
      MappedEncoding { _.name }

    def worldDecoder(implicit
        worldService: WorldService
    ): MappedEncoding[String, World] =
      MappedEncoding(worldService.getWorld)
    implicit lazy val worldEncoder: MappedEncoding[World, String] =
      MappedEncoding { _.name }
  }
}
