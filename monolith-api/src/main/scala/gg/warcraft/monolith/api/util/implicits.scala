package gg.warcraft.monolith.api.util

object option {
  implicit def toOption[A](self: A): Option[A] = Option(self)
}
