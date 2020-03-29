package gg.warcraft.monolith.api.util

import gg.warcraft.monolith.api.core.task.TaskService

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

object Ops {
  @inline implicit final class ChainingOps[A](private val self: A) extends AnyVal {
    def |>[B](f: A => B): B = f apply self
  }

  @inline implicit final class NullableOps[A](private val self: A) extends AnyVal {
    def ??[B](f: A => B): B = if (self != null) self |> f else null.asInstanceOf[B]
    def :?(f: => A): A = if (self == null) f else self
  }

  @inline implicit final class FutureOps[T](
      private val self: Future[T]
  ) extends AnyVal {
    def immediatelyOrOnComplete[U](f: Try[T] => U)(
        implicit context: ExecutionContext,
        taskService: TaskService
    ): Unit = self.value match {
      case Some(result) => result |> f
      case _            => self onComplete (it => taskService runNextTick (it |> f))
    }
  }
}
