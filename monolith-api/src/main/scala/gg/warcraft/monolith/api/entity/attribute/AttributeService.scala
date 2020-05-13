package gg.warcraft.monolith.api.entity.attribute

import java.util.UUID

abstract class AttributeService {
  private final val emptyAttributes = Attributes(Set.empty)

  private var _attributes: Map[UUID, Attributes] = Map.empty

  def getAttributes(entityId: UUID): Attributes =
    _attributes.getOrElse(entityId, emptyAttributes)

  def addModifier(entityId: UUID, modifier: Attribute.Modifier*): Unit = {
    val attributes = getAttributes(entityId)
    val updated = attributes.copy(modifiers = attributes.modifiers ++ modifier)
    _attributes += (entityId -> updated)
  }

  def removeModifier(entityId: UUID, modifier: Attribute.Modifier*): Unit = {
    val attributes = getAttributes(entityId)
    val updated = attributes.copy(modifiers = attributes.modifiers -- modifier)
    _attributes += (entityId -> updated)
  }
}
