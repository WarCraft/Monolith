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

package gg.warcraft.monolith.api.util

import gg.warcraft.monolith.api.block.BlockTypeVariantOrState
import gg.warcraft.monolith.api.core.ColorCode
import gg.warcraft.monolith.api.effect.Particle
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import gg.warcraft.monolith.api.item.{ItemService, ItemTypeOrVariant}
import gg.warcraft.monolith.api.world.{Direction, World, WorldService}
import io.circe.{Decoder, Encoder, KeyDecoder}
import io.getquill.MappedEncoding

import scala.util.Try

object codecs {

  object monolith {
    implicit def blockDataDecoder(implicit
        service: WorldService
    ): String => BlockTypeVariantOrState =
      service.parseData
    implicit val blockDataEncoder: BlockTypeVariantOrState => String =
      _.toString

    implicit val colorCodeDecoder: String => ColorCode =
      ColorCode.valueOf
    implicit val colorCodeEncoder: ColorCode => String =
      _.name

    implicit val directionDecoder: String => Direction =
      Direction.valueOf
    implicit val directionEncoder: Direction => String =
      _.name

    implicit def itemDataDecoder(implicit
        service: ItemService
    ): String => ItemTypeOrVariant =
      service.parseData
    implicit val itemDataEncoder: ItemTypeOrVariant => String =
      _.toString

    implicit val particleColorDecoder: String => Particle.Color =
      Particle.Color.valueOf

    implicit def teamDecoder(implicit
        service: TeamService
    ): String => Team =
      service.teams
    implicit def teamOptionDecoder(implicit
        service: TeamService
    ): String => Option[Team] =
      service.teams.get _
    implicit val teamEncoder: Team => String =
      _.id

    implicit def worldDecoder(implicit
        service: WorldService
    ): String => World =
      service.getWorld
    implicit val worldEncoder: World => String =
      _.name
  }

  object circe {
    implicit def circeDecoder[T](implicit f: String => T): Decoder[T] =
      Decoder.decodeString.emapTry { it => { Try { f(it) } } }
    implicit def circeKeyDecoder[T](implicit f: String => T): KeyDecoder[T] =
      KeyDecoder.instance { it => Option(f(it)) }
    implicit def circeEncoder[T](implicit f: T => String): Encoder[T] =
      Encoder.encodeString.contramap[T](f)
    implicit def circeEnumEncoder[T <: Enum[T]]: Encoder[T] =
      Encoder.encodeString.contramap[T] { _.name }

    implicit def circeIntDecoder[T](implicit f: Int => T): Decoder[T] =
      Decoder.decodeInt.emapTry { it => { Try { f(it) } } }
    implicit def circeIntEncoder[T](implicit f: T => Int): Encoder[T] =
      Encoder.encodeInt.contramap[T](f)
  }

  object quill {
    implicit def quillDecoder[T](implicit
        f: String => T
    ): MappedEncoding[String, T] =
      MappedEncoding(f)
    implicit def quillEncoder[T](implicit
        f: T => String
    ): MappedEncoding[T, String] =
      MappedEncoding(f)
    implicit def quillEnumEncoder[T <: Enum[T]]: MappedEncoding[T, String] =
      MappedEncoding { _.name }

    implicit def quillIntDecoder[T](implicit
        f: Int => T
    ): MappedEncoding[Int, T] =
      MappedEncoding(f)
    implicit def quillIntEncoder[T](implicit
        f: T => Int
    ): MappedEncoding[T, Int] =
      MappedEncoding(f)
  }
}
