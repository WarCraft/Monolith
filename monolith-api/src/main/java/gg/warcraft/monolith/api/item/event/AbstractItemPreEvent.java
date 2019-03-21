package gg.warcraft.monolith.api.item.event;

import gg.warcraft.monolith.api.core.event.AbstractPreEvent;
import gg.warcraft.monolith.api.item.Item;

import java.util.UUID;

public abstract class AbstractItemPreEvent extends AbstractPreEvent implements ItemPreEvent {
    private final UUID itemId;
    private final Item item;

    public AbstractItemPreEvent(UUID itemId, Item item, boolean cancelled) {
        super(cancelled);
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
