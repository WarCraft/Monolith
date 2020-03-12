package gg.warcraft.monolith.api.util

object ConsoleColor extends Enumeration {
  type Type = ConsoleColor.Value

  implicit def toVal(v: Value): Val = v.asInstanceOf[Val]
  protected case class Val(code: String) extends super.Val {
    override def toString(): String = code
  }

  val BLACK = Val("\u001B[30m")
  val BLUE = Val("\u001B[34m")
  val CYAN = Val("\u001B[36m")
  val GREEN = Val("\u001B[32m")
  val PURPLE = Val("\u001B[35m")
  val RED = Val("\u001B[31m")
  val WHITE = Val("\u001B[37m")
  val YELLOW = Val("\u001B[33m")
  val RESET = Val("\u001B[0m")
}
