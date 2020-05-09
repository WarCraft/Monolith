package gg.warcraft.monolith.api.util

object string {
  @inline implicit final class CharOps(private val self: Char) extends AnyVal {
    def isVowel: Boolean = "aeiou".contains(self)
  }

  @inline implicit final class StringOps(private val self: String) extends AnyVal {
    def stripChatCodes: String = self.replace("§", "")
  }
}
