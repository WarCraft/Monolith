package gg.warcraft.monolith.api.item.service;

import gg.warcraft.monolith.api.item.Item;

import java.util.List;
import java.util.UUID;

public interface ItemStorageRepository {

    List<Item> getStoredItems(UUID playerId);

    void storeItem(Item item, UUID playerId);

    void claimItem(Item item, UUID playerId);
}
