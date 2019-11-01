package gg.warcraft.monolith.api.world.item.storage

import java.util.UUID

import gg.warcraft.monolith.api.world.item.Item

class ItemStorageService {
  private final val emptyMap = Map.empty[UUID, StoredItem]

  def getItems(playerId: UUID): Iterable[StoredItem] =
    ItemStorageService.storage.getOrElse(playerId, emptyMap).values

  def store(item: Item, playerId: UUID): UUID = {
    val clazz = item.getClass
    val storedItem = StoredItem(
      UUID.randomUUID(),
      "", // TODO item.asJson.noSpaces,
      item.getClass.getCanonicalName
    )

    val items = ItemStorageService.storage.getOrElse(playerId, emptyMap)
    val newItems = items + (storedItem.id -> storedItem)
    ItemStorageService.storage += (playerId -> newItems)

    // TODO persist item

    storedItem.id
  }

  def claim(itemId: UUID, playerId: UUID): Boolean = {
    val json = ""
    // TODO get item from persistence
    // val item = decode[Item](json)

    false
  }
}

private object ItemStorageService {
  private final var storage = Map[UUID, Map[UUID, StoredItem]]()
}
