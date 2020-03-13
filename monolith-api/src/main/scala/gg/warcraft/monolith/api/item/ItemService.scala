package gg.warcraft.monolith.api.item

import java.util.UUID

import gg.warcraft.monolith.api.world.Location

import scala.annotation.varargs

trait ItemService {
  protected type Data = ItemTypeOrVariant

  def parseData(data: String): Data

  def create(data: Data): Item
  def create[T <: ItemVariant](variant: T): VariableItem[T]

  def giveTo(playerId: UUID, data: Data, count: Int): Boolean
  @varargs def giveTo(playerId: UUID, items: Item*): Boolean

  def takeFrom(playerId: UUID, data: Data, count: Int): Boolean
  @varargs def takeFrom(playerId: UUID, items: Item*): Boolean

  @varargs def dropItems(location: Location, items: Item*): Array[UUID]
}
