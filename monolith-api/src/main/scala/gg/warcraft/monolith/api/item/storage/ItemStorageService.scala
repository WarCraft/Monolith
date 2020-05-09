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
    ??? // TODO ItemStorageService.storage.getOrElse(playerId, emptyMap).values

  def storeItem[T <: Item](item: T, playerId: UUID): Future[UUID] =
    ???
//  {
//    val storedItem = StoredItem(
//      UUID.randomUUID(),
//      playerId,
//      item.asJson.noSpaces
//    )
//
//    val items = ItemStorageService.storage.getOrElse(playerId, emptyMap)
//    val newItems = items + (storedItem.id -> storedItem)
//    ItemStorageService.storage += (playerId -> newItems)
//
//    // TODO persist item
//
//    storedItem.id
//  }

  def claimItem(id: UUID, playerId: UUID): Future[Boolean] =
    ???
//  {
//    val json = ""
//    // TODO get item from persistence
//    // val item = decode[Item](json)
//
//    false
//  }
}




/*
public class ItemClaimTask implements Runnable {
    private final ItemStorageService itemStorageService;
    private final LegendsPlayerQueryService playerQueryService;
    private final LegendsPlayerCommandService playerCommandService;
    private final TaskService taskService;
    private final UUID playerId;

    public ItemClaimTask(ItemStorageService itemStorageService,
                         LegendsPlayerQueryService playerQueryService,
                         LegendsPlayerCommandService playerCommandService,
                         TaskService taskService,
                         UUID playerId) {
        this.itemStorageService = itemStorageService;
        this.playerQueryService = playerQueryService;
        this.playerCommandService = playerCommandService;
        this.taskService = taskService;
        this.playerId = playerId;
    }

    @Override
    public void run() {
        // TODO List<Item> storedItems = itemStorageService.getItems(playerId);
        List<Item> storedItems = new ArrayList<>();
        if (storedItems.isEmpty()) {
            return;
        }

        LegendsPlayer player = playerQueryService.getPlayer(playerId);
        if (!player.getInventory().hasSpace(1)) {
            playerCommandService.sendNotification(playerId,
                    "You don't have enough inventory space to claim all of your items. Please free up some space and try again.");
            return;
        }

        // TODO
//        Item itemToClaim = storedItems.get(0);
//        itemStorageService.claimItem(itemToClaim, playerId);
//        playerCommandService.giveItem(playerId, itemToClaim, false);
//        playerCommandService.sendNotification(playerId, "You received " + itemToClaim.getStackSize() +
//                " " + itemToClaim.getName() + "!");
//
//        taskService.runNextTick(this);
    }
}

 */