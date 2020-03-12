package gg.warcraft.monolith.api.core

object FormatCode extends Enumeration {
  type Type = FormatCode.Value

  implicit def toVal(v: Value): Val = v.asInstanceOf[Val]
  protected case class Val(code: String) extends super.Val {
    override def toString(): String = code
  }

  val OBFUSCATED = Val("§k")
  val BOLD = Val("§l")
  val STRIKETHROUGH = Val("§m")
  val UNDERLINE = Val("§n")
  val ITALIC = Val("§o")
  val RESET = Val("§r")

  def strip(s: String): String = s.replaceAll("§[k-r]", "")
  def stripAll(s: String): String = s.replaceAll("§[0-9a-z]", "")
}
