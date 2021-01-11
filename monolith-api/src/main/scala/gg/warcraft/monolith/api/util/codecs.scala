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
import gg.warcraft.monolith.api.core.Duration.DurationOps
import gg.warcraft.monolith.api.core.{Color, ColorCode, Duration}
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import gg.warcraft.monolith.api.item.{ItemService, ItemTypeOrVariant}
import gg.warcraft.monolith.api.util.types.tags.{
  MonolithDateTag, MonolithDateTimeTag
}
import gg.warcraft.monolith.api.util.types.{MonolithDate, MonolithDateTime}
import gg.warcraft.monolith.api.world.{Direction, World, WorldService}
import io.circe.{Decoder, Encoder, KeyDecoder}
import io.getquill.MappedEncoding
import shapeless.tag

import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime}
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

    implicit val colorDecoder: String => Color =
      Color.valueOf

    implicit def teamDecoder(implicit
        service: TeamService
    ): String => Team =
      service.teams
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

    implicit def circeDurationDecoder: Decoder[Duration] =
      Decoder.decodeInt.emapTry { it => { Try { it.seconds } } }
    implicit def circeDurationEncoder: Encoder[Duration] =
      Encoder.encodeInt.contramap { _.seconds }
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

    implicit def quillDurationDecoder: MappedEncoding[Int, Duration] =
      MappedEncoding { _.seconds }
    implicit def quillDurationEncoder: MappedEncoding[Duration, Int] =
      MappedEncoding { _.seconds }

    private val quillDateFormatter = DateTimeFormatter.ISO_DATE
    implicit def quillDateDecoder: MappedEncoding[String, MonolithDate] =
      MappedEncoding { data =>
        val localDate = LocalDate.parse(data, quillDateFormatter)
        tag[MonolithDateTag][LocalDate](localDate)
      }
    implicit def quillDateEncoder: MappedEncoding[MonolithDate, String] =
      MappedEncoding { _.format(quillDateFormatter) }

    private val quillDateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
    implicit def quillDateTimeDecoder: MappedEncoding[String, MonolithDateTime] =
      MappedEncoding { data =>
        val localDateTime = LocalDateTime.parse(data, quillDateTimeFormatter)
        tag[MonolithDateTimeTag][LocalDateTime](localDateTime)
      }
    implicit def quillDateTimeEncoder: MappedEncoding[MonolithDateTime, String] =
      MappedEncoding { _.format(quillDateTimeFormatter) }
  }
}
