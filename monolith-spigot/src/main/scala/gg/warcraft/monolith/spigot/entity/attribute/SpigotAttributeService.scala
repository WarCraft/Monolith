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

package gg.warcraft.monolith.spigot.entity.attribute

import java.util.UUID

import gg.warcraft.monolith.api.entity.attribute.{Attribute, AttributeService}
import gg.warcraft.monolith.spigot.entity.SpigotEntity
import org.bukkit.Server
import org.bukkit.attribute.{AttributeModifier => SpigotAttributeModifier}

class SpigotAttributeService(implicit
    server: Server,
    attributeMapper: SpigotAttributeMapper
) extends AttributeService {
  private var _spigotModifiers: Map[Attribute.Modifier, SpigotAttributeModifier] =
    Map.empty

  override def addModifier(
      entityId: UUID,
      modifier: Attribute.Modifier*
  ): Unit = {
    super.addModifier(entityId, modifier: _*)
    server.getEntity(entityId) match {
      case spigotEntity: SpigotEntity =>
        modifier.foreach {
          case modifier @ Attribute.Modifier(attribute: Attribute.Generic, _, _) =>
            val spigotAttribute = attributeMapper.map(attribute)
            val spigotModifier = attributeMapper.map(modifier)
            _spigotModifiers += (modifier -> spigotModifier)
            spigotEntity
              .getAttribute(spigotAttribute)
              .addModifier(spigotModifier)
          case _ =>
        }
      case _ =>
    }
  }

  override def removeModifier(
      entityId: UUID,
      modifier: Attribute.Modifier*
  ): Unit = {
    super.removeModifier(entityId, modifier: _*)
    server.getEntity(entityId) match {
      case spigotEntity: SpigotEntity =>
        modifier.foreach {
          case modifier @ Attribute.Modifier(attribute: Attribute.Generic, _, _) =>
            lazy val spigotAttribute = attributeMapper.map(attribute)
            _spigotModifiers.get(modifier) match {
              case Some(spigotModifier) =>
                _spigotModifiers -= modifier
                spigotEntity
                  .getAttribute(spigotAttribute)
                  .removeModifier(spigotModifier)
              case None =>
            }
          case _ =>
        }
      case _ =>
    }
  }
}
