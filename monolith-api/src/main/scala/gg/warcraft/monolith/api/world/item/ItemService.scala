package gg.warcraft.monolith.api.world.item

import java.util.UUID

import scala.annotation.varargs

trait ItemService {
  def create(`type`: ItemType): Item
  def create[T <: ItemVariant](variant: T): VariableItem[T]
  def create(`type`: String): Item

  def giveTo(playerId: UUID, `type`: ItemType, count: Int): Boolean
  def giveTo(playerId: UUID, variant: ItemVariant, count: Int): Boolean
  @varargs def giveTo(playerId: UUID, items: Item*): Boolean

  def takeFrom(playerId: UUID, `type`: ItemType, count: Int): Boolean
  def takeFrom(playerId: UUID, variant: ItemVariant, count: Int): Boolean
  @varargs def takeFrom(playerId: UUID, items: Item*): Boolean
}
