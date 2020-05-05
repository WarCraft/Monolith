package gg.warcraft.monolith.api.util

import gg.warcraft.monolith.api.core.task.TaskService

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

object Ops { // TODO rename chaining like scala.util.chaining
  @inline implicit final class ChainingOps[A](private val self: A) extends AnyVal {
    def |>[B](f: A => B): B = f apply self
  }

  @inline implicit final class FunctionOps[A, B](
      private val self: A => B
  ) extends AnyVal {
    def <|(it: A): B = self apply it
  }

  @inline implicit final class DoubleOps(private val self: Double) extends AnyVal {
    def >=<(bound: (Double, Double)): Boolean = self >= bound._1 && self <= bound._2
  }

  @inline implicit final class FloatOps(private val self: Float) extends AnyVal {
    def >=<(bound: (Float, Float)): Boolean = self >= bound._1 && self <= bound._2
  }

  @inline implicit final class IntOps(private val self: Int) extends AnyVal {
    def >=<(bound: (Int, Int)): Boolean = self >= bound._1 && self <= bound._2
  }

  @inline implicit final class NullableOps[A](private val self: A) extends AnyVal {
    def ??[B](f: A => B): B = if (self != null) self |> f else null.asInstanceOf[B]
    def ?:(f: => A): A = if (self == null) f else self
  }

  @inline implicit final class FutureOps[T](
      private val self: Future[T]
  ) extends AnyVal {
    def immediatelyOrOnComplete[U](f: Try[T] => U)(
        implicit context: ExecutionContext,
        taskService: TaskService
    ): Unit = self.value match {
      case Some(result) => result |> f
      case _            => self onComplete (it => taskService evalNextTick (it |> f))
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
