package gg.warcraft.monolith.api.item.service;

import gg.warcraft.monolith.api.world.item.Item;

import java.util.List;
import java.util.UUID;

public interface ItemStorageQueryService {

    List<Item> getStoredItems(UUID playerId);
}
