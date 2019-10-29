package gg.warcraft.monolith.api.core

object Extensions {
  implicit class Regex(context: StringContext) {
    def r = new util.matching.Regex(
      context.parts.mkString,
      context.parts.tail.map(_ => "x"): _*
    )
  }
}
