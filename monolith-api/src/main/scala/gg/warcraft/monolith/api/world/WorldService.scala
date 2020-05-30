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

package gg.warcraft.monolith.api.world

import java.util.UUID

import gg.warcraft.monolith.api.block.{Block, BlockType, BlockTypeVariantOrState}
import gg.warcraft.monolith.api.math.Vector3f

trait WorldService {
  protected type Data = BlockTypeVariantOrState

  def parseData(data: String): Data

  def getWorld(name: String): World
  def getWorlds(typ: World.Type): List[World]

  def getBlock(loc: BlockLocation): Block
  def getBlockIfType(loc: BlockLocation, types: BlockType*): Option[Block]
  def getHighestBlock(loc: BlockLocation): Block

  def setBlock(loc: BlockLocation, data: Data): Unit
  def setBlock(loc: BlockLocation, block: Block): Unit
  def updateBlock(block: Block): Unit = setBlock(block.location, block)

  def playSound(
      location: Location,
      sound: Sound,
      category: SoundCategory,
      volume: Float = 1,
      pitch: Float = 1
  ): Unit

  def strikeLightning(location: Location, ambient: Boolean): Unit

  def createExplosion(location: Location, ambient: Boolean): Unit

  def createArrow(
      shooterId: UUID,
      location: Location,
      direction: Vector3f,
      speed: Float,
      spread: Float
  ): UUID
}
