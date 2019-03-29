package gg.warcraft.monolith.api.item.service;

import gg.warcraft.monolith.api.item.Item;

import java.util.UUID;

public interface ItemStorageCommandService {

    void storeItem(Item item, UUID playerId);

    void claimItem(Item item, UUID playerId);
}
