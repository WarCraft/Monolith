package gg.warcraft.monolith.api.item.storage

import java.util.UUID

import gg.warcraft.monolith.api.item.Item
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

import scala.concurrent.Future

class ItemStorageService {
  private final val emptyMap: Map[UUID, StoredItem] = Map.empty

  def getItems(playerId: UUID): Future[Iterable[StoredItem]] =
    ItemStorageService.storage.getOrElse(playerId, emptyMap).values

  def storeItem[T : Item](item: T, playerId: UUID): Future[UUID] = {
    val storedItem = StoredItem(
      UUID.randomUUID(),
      playerId,
      item.asJson.noSpaces
    )

    val items = ItemStorageService.storage.getOrElse(playerId, emptyMap)
    val newItems = items + (storedItem.id -> storedItem)
    ItemStorageService.storage += (playerId -> newItems)

    // TODO persist item

    storedItem.id
  }

  def claimItem(id: UUID, playerId: UUID): Future[Boolean] = {
    val json = ""
    // TODO get item from persistence
    // val item = decode[Item](json)

    false
  }
}
