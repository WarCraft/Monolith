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

package gg.warcraft.monolith.spigot.block

import gg.warcraft.monolith.api.block.BlockAttachment
import org.bukkit.block.data.`type`.Switch

// TODO Switch.Face deprecated, update to FaceAttachable
class SpigotBlockAttachmentMapper {
  def map(switch: Switch): BlockAttachment = switch.getFace match {
    case Switch.Face.CEILING => BlockAttachment.CEILING
    case Switch.Face.FLOOR   => BlockAttachment.FLOOR
    case Switch.Face.WALL    => BlockAttachment.WALL
  }

  def map(attachment: BlockAttachment): Switch.Face = attachment match {
    case BlockAttachment.CEILING => Switch.Face.CEILING
    case BlockAttachment.FLOOR   => Switch.Face.FLOOR
    case BlockAttachment.WALL    => Switch.Face.WALL
  }
}
