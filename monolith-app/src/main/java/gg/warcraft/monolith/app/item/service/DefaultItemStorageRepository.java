package gg.warcraft.monolith.app.item.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gg.warcraft.monolith.api.core.JsonMapper;
import gg.warcraft.monolith.api.core.PersistenceService;
import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.item.ItemBuilder;
import gg.warcraft.monolith.api.item.ItemBuilderFactory;
import gg.warcraft.monolith.api.item.ItemType;
import gg.warcraft.monolith.api.item.service.ItemStorageRepository;
import gg.warcraft.monolith.app.item.persistence.ItemItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class DefaultItemStorageRepository implements ItemStorageRepository {
    private static final String ITEM_STORAGE_KEY_PREFIX = "itemstorage:";

    private final PersistenceService persistenceService;
    private final ItemBuilderFactory itemBuilderFactory;
    private final ObjectMapper jsonMapper;

    @Inject
    public DefaultItemStorageRepository(PersistenceService persistenceService,
                                        ItemBuilderFactory itemBuilderFactory,
                                        @JsonMapper ObjectMapper jsonMapper) {
        this.persistenceService = persistenceService;
        this.itemBuilderFactory = itemBuilderFactory;
        this.jsonMapper = jsonMapper;
    }

    private String getKeyFor(UUID playerId) {
        return ITEM_STORAGE_KEY_PREFIX + playerId;
    }

    Item mapItemItemToItem(ItemItem item) {
        ItemType type = ItemType.valueOf(item.getType());
        String name = item.getName();
        ItemBuilder builder = itemBuilderFactory.createItemBuilder(type, name)
                .withDamage(item.getDamage())
                .withStackSize(item.getStackSize());
        item.getLore().forEach(builder::addLore);
        return builder.build();
    }

    ItemItem mapItemToItemItem(Item item) {
        return new ItemItem(item.getType().name(), item.getName(),
                item.getStackSize(), item.getDamage(), item.getLore());
    }

    @Override
    public List<Item> getStoredItems(UUID playerId) {
        String playerKey = getKeyFor(playerId);
        String currentItemsJson = persistenceService.get(playerKey);
        if (currentItemsJson == null) {
            return Collections.emptyList();
        }

        try {
            List<ItemItem> currentItems = jsonMapper.readValue(currentItemsJson, new TypeReference<List<ItemItem>>() {
            });
            if (currentItems == null) {
                currentItems = new ArrayList<>();
            }
            return currentItems.stream()
                    .map(this::mapItemItemToItem)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println("Failed to retrieve stored items for " + playerId + ": " + ex.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void storeItem(Item item, UUID playerId) {
        String playerKey = getKeyFor(playerId);
        ItemItem itemToStore = mapItemToItemItem(item);
        String currentItemsJson = persistenceService.get(playerKey);
        try {
            List<ItemItem> currentItems = jsonMapper.readValue(currentItemsJson, new TypeReference<List<ItemItem>>() {
            });
            if (currentItems == null) {
                currentItems = new ArrayList<>();
            }
            currentItems.add(itemToStore);
            String newItemsJson = jsonMapper.writeValueAsString(currentItems);
            persistenceService.set(playerKey, newItemsJson);
        } catch (Exception ex) {
            System.out.println("Failed to store item " + item + " for " + playerId + ": " + ex.getMessage());
        }
    }

    @Override
    public void claimItem(Item item, UUID playerId) {
        String playerKey = getKeyFor(playerId);
        ItemItem itemToClaim = mapItemToItemItem(item);
        String currentItemsJson = persistenceService.get(playerKey);
        try {
            List<ItemItem> currentItems = jsonMapper.readValue(currentItemsJson, new TypeReference<List<ItemItem>>() {
            });
            if (currentItems == null) {
                currentItems = new ArrayList<>();
            }
            currentItems.remove(itemToClaim);
            String newItemsJson = jsonMapper.writeValueAsString(currentItems);
            persistenceService.set(playerKey, newItemsJson);
        } catch (Exception ex) {
            System.out.println("Failed to remove item " + item + " for " + playerId + ": " + ex.getMessage());
        }
    }
}
