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

package gg.warcraft.monolith.api.block

import java.util

import gg.warcraft.monolith.api.core.CaseTrait
import gg.warcraft.monolith.api.world.{BlockLocation, Location}

import scala.annotation.varargs
import scala.jdk.CollectionConverters._

object Block {
  case class Intersection(
      block: Block,
      // TODO face: BlockFace,
      location: Location
  )

  implicit def toBlockLocation(block: Block): BlockLocation = block.location
  implicit def toLocation(block: Block): Location = block.location.toLocation
}

trait Block extends CaseTrait {
  val `type`: BlockType
  val solid: Boolean = true
  val liquid: Boolean = false

  val location: BlockLocation
  def withLocation(location: BlockLocation): this.type =
    copyWith("location", location)

  val x: Int = location.x
  val y: Int = location.y
  val z: Int = location.z

  def isVariant(variant: BlockVariant): Boolean = false
  def hasState(state: BlockState): Boolean = false
  def hasData(data: BlockTypeVariantOrState): Boolean = this.`type` == data
}

trait AttachedBlock extends Block {
  val attachment: BlockAttachment
  def withAttached(attachment: BlockAttachment): this.type =
    copyWith("attachment", attachment)
}

trait BisectedBlock extends Block {
  val section: BlockBisection
  def withSection(section: BlockBisection): this.type =
    copyWith("section", section)
}

trait DirectedBlock extends Block {
  val direction: BlockFace
  def withDirection(direction: BlockFace): this.type =
    copyWith("direction", direction)
}

trait DirectableBlock extends Block {
  val direction: Option[BlockFace]
  def withDirection(direction: Option[BlockFace]): this.type =
    copyWith("direction", direction)
}

trait ExtendableBlock extends Block {
  val extensions: Set[BlockFace]
  def getExtensions: util.Set[BlockFace] = extensions.asJava
  @varargs def withExtensions(extensions: BlockFace*): this.type =
    copyWith("extensions", extensions.toSet)
}

trait FloodableBlock extends Block {
  val flooded: Boolean
  override val liquid: Boolean = flooded
  def withFlooded(flooded: Boolean): this.type =
    copyWith("flooded", flooded)
}

trait HingedBlock extends Block {
  val hinge: BlockHinge
  def withHinge(hinge: BlockHinge): this.type =
    copyWith("hinge", hinge)
}

trait LightableBlock extends Block {
  val lit: Boolean
  def withLit(lit: Boolean): this.type =
    copyWith("lit", lit)
}

trait OpenableBlock extends Block {
  val open: Boolean
  def withOpen(open: Boolean): this.type =
    copyWith("open", open)
}

trait OrientedBlock extends Block {
  val orientation: BlockOrientation
  def withOrientation(orientation: BlockOrientation): this.type =
    copyWith("orientation", orientation)
}

trait PowerableBlock extends Block {
  val powered: Boolean
  def withPowered(powered: Boolean): this.type =
    copyWith("powered", powered)
}

trait RotatableBlock extends Block {
  val rotation: Option[BlockRotation]
  def withRotation(rotation: Option[BlockRotation]): this.type =
    copyWith("rotation", rotation)
}

trait ShapedBlock[T <: BlockShape] extends Block {
  val shape: T
  def withShape(shape: T): this.type =
    copyWith("shape", shape)
}

trait SnowableBlock extends Block {
  val snowy: Boolean
  def withSnowy(snowy: Boolean): this.type =
    copyWith("snowy", snowy)
}

trait StatefulBlock[T <: BlockState] extends Block {
  val state: T
  def withState(state: T): this.type =
    copyWith("state", state)

  override def hasState(state: BlockState): Boolean =
    this.state == state
  override def hasData(data: BlockTypeVariantOrState): Boolean =
    this.state == data || super.hasData(data)
}

trait VariableBlock[T <: BlockVariant] extends Block {
  val variant: T
  def withVariant(variant: T): this.type =
    copyWith("variant", variant)

  override def isVariant(variant: BlockVariant): Boolean =
    this.variant == variant
  override def hasData(data: BlockTypeVariantOrState): Boolean =
    this.variant == data || super.hasData(data)
}
