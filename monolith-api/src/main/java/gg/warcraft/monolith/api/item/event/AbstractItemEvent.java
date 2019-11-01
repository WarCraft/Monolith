package gg.warcraft.monolith.api.item.event;

import gg.warcraft.monolith.api.world.item.Item;

import java.util.UUID;

public abstract class AbstractItemEvent implements ItemEvent {
    private final UUID itemId;
    private final Item item;

    public AbstractItemEvent(UUID itemId, Item item) {
        this.itemId = itemId;
        this.item = item;
    }

    @Override
    public UUID getItemId() {
        return itemId;
    }

    @Override
    public Item getItem() {
        return item;
    }
}
