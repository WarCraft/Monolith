package gg.warcraft.monolith.api.core

object ColorCode extends Enumeration {
  type Type = ColorCode.Value

  implicit def toVal(v: Value): Val = v.asInstanceOf[Val]
  protected case class Val(code: String) extends super.Val {
    override def toString(): String = code
  }

  val BLACK = Val("§0")
  val DARK_BLUE = Val("§1")
  val DARK_GREEN = Val("§2")
  val DARK_AQUA = Val("§3")
  val DARK_RED = Val("§4")
  val DARK_PURPLE = Val("§5")
  val GOLD = Val("§6")
  val GRAY = Val("§7")
  val DARK_GRAY = Val("§8")
  val BLUE = Val("§9")
  val GREEN = Val("§a")
  val AQUA = Val("§b")
  val RED = Val("§c")
  val LIGHT_PURPLE = Val("§d")
  val YELLOW = Val("§e")
  val WHITE = Val("§f")

  def strip(s: String): String = s.replaceAll("§[0-9a-f]", "")
  def stripAll(s: String): String = s.replaceAll("§[0-9a-z]", "")
}
