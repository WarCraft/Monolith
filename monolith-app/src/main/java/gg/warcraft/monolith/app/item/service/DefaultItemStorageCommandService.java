package gg.warcraft.monolith.app.item.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.item.service.ItemStorageCommandService;
import gg.warcraft.monolith.api.item.service.ItemStorageRepository;

import java.util.UUID;

public class DefaultItemStorageCommandService implements ItemStorageCommandService {
    private final ItemStorageRepository itemStorageRepository;

    @Inject
    public DefaultItemStorageCommandService(ItemStorageRepository itemStorageRepository) {
        this.itemStorageRepository = itemStorageRepository;
    }

    @Override
    public void storeItem(Item item, UUID playerId) {
        itemStorageRepository.storeItem(item, playerId);
    }

    @Override
    public void claimItem(Item item, UUID playerId) {
        itemStorageRepository.claimItem(item, playerId);
    }
}
