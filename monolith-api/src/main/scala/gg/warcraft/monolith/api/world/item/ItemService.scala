package gg.warcraft.monolith.api.world.item

import java.util.UUID

import gg.warcraft.monolith.api.world.Location

import scala.annotation.varargs

trait ItemService {
  def parseData(data: String): Any

  def create(`type`: ItemType): Item
  def create[T <: ItemVariant](variant: T): VariableItem[T]
  def create(data: Any): Item

  def giveTo(playerId: UUID, `type`: ItemType, count: Int): Boolean
  def giveTo(playerId: UUID, variant: ItemVariant, count: Int): Boolean
  @varargs def giveTo(playerId: UUID, items: Item*): Boolean

  def takeFrom(playerId: UUID, `type`: ItemType, count: Int): Boolean
  def takeFrom(playerId: UUID, variant: ItemVariant, count: Int): Boolean
  @varargs def takeFrom(playerId: UUID, items: Item*): Boolean

  @varargs def dropItems(location: Location, items: Item*)
}
