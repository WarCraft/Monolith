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

package gg.warcraft.monolith.api.core.command

import gg.warcraft.monolith.api.core.Message
import gg.warcraft.monolith.api.core.auth.Principal

case class Command(
    name: String,
    aliases: List[String] = Nil,
    usage: Option[Message] = None
)

object Command {
  sealed trait Result

  case object success extends Result
  final case class success(messages: List[Message]) extends Result

  case object invalid extends Result
  case object consoleOnly extends Result
  case object playersOnly extends Result

  trait Handler { // TODO change args to List[String]
    def handle(executor: Principal, command: Command, args: String*): Command.Result
  }

  def apply(name: String, aliases: List[String], usage: String): Command = {
    val usageMessage = Message.server(usage)
    Command(name, aliases, Some(usageMessage))
  }
}
