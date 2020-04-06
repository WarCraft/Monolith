package gg.warcraft.monolith.api.entity.attribute

import java.util.UUID

import scala.collection.mutable

abstract class AttributeService {
  private final val emptyAttributes = Attributes(Set.empty)

  private val attributes = mutable.Map[UUID, Attributes]()

  def getAttributes(entityId: UUID): Attributes =
    attributes getOrElse (entityId, emptyAttributes)

  def addAttribute(entityId: UUID, attribute: Attribute*): Unit = {
    val attributes = getAttributes(entityId)
    val updated = attributes.copy(attributes = attributes.attributes ++ attribute)
    this.attributes += (entityId -> updated)
  }

  // TODO update generic attributes ingame
  def removeAttribute(entityId: UUID, attribute: Attribute*): Unit = {
    val attributes = getAttributes(entityId)
    val updated = attributes.copy(attributes = attributes.attributes -- attribute)
    this.attributes += (entityId -> updated)
  }
}
