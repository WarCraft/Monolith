package gg.warcraft.monolith.app.item.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.item.service.ItemStorageQueryService;
import gg.warcraft.monolith.api.item.service.ItemStorageRepository;

import java.util.List;
import java.util.UUID;

public class DefaultItemStorageQueryService implements ItemStorageQueryService {
    private final ItemStorageRepository itemStorageRepository;

    @Inject
    public DefaultItemStorageQueryService(ItemStorageRepository itemStorageRepository) {
        this.itemStorageRepository = itemStorageRepository;
    }

    @Override
    public List<Item> getStoredItems(UUID playerId) {
        return itemStorageRepository.getStoredItems(playerId);
    }
}
