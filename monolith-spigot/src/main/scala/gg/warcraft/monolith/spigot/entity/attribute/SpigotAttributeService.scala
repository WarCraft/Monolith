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
