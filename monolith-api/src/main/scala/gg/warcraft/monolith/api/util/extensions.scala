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

import gg.warcraft.monolith.api.core.task.TaskService
import gg.warcraft.monolith.api.util.chaining._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

object chaining {
  @inline implicit final class ChainingOps[A](private val self: A) extends AnyVal {
    def |>[B](f: A => B): B = f apply self
  }

  @inline implicit final class FunctionOps[A, B](
      private val self: A => B
  ) extends AnyVal {
    def <|(it: A): B = self apply it
  }

  @inline implicit final class NullableOps[A](private val self: A) extends AnyVal {
    def ??[B](f: A => B): B = if (self != null) self |> f else null.asInstanceOf[B]
    def ?:(f: => A): A = if (self == null) f else self
  }
}

object future {
  @inline implicit final class FutureOps[T](
      private val self: Future[T]
  ) extends AnyVal {
    def immediatelyOrOnComplete[U](f: Try[T] => U)(implicit
        context: ExecutionContext,
        taskService: TaskService
    ): Unit = self.value match {
      case Some(result) => result |> f
      case _            => self.onComplete(it => taskService.evalNextTick(it |> f))
    }

    def getOrThrow: T = self.value match {
      case Some(result) =>
        result match {
          case Success(value)     => value
          case Failure(exception) => throw exception
        }
      case None => throw new IllegalStateException
    }
  }
}

object number {
  @inline implicit final class DoubleOps(private val self: Double) extends AnyVal {
    def >=<(bound: (Double, Double)): Boolean = self >= bound._1 && self <= bound._2
  }

  @inline implicit final class FloatOps(private val self: Float) extends AnyVal {
    def >=<(bound: (Float, Float)): Boolean = self >= bound._1 && self <= bound._2
  }

  @inline implicit final class IntOps(private val self: Int) extends AnyVal {
    def >=<(bound: (Int, Int)): Boolean = self >= bound._1 && self <= bound._2
  }
}

object option {
  @inline implicit def toOption[T](self: T): Option[T] = Some(self)
  @inline implicit def fromOption[T](self: Some[T]): T = self.get
}

object string {
  @inline implicit final class CharOps(private val self: Char) extends AnyVal {
    def isVowel: Boolean = "aeiou".contains(self)
  }

  @inline implicit final class StringOps(private val self: String) extends AnyVal {
    def stripChatCodes: String = self.replaceAll("§[0-9a-z]", "")
  }
}

object typing {
  @inline implicit final class TypeOps[A](private val self: A) extends AnyVal {
    def is[B]: Boolean = self.isInstanceOf[B]
    def as[B]: B = self.asInstanceOf[B]
  }
}
