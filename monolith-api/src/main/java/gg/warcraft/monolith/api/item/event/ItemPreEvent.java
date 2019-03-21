package gg.warcraft.monolith.api.item.event;

import gg.warcraft.monolith.api.core.PreEvent;
import gg.warcraft.monolith.api.item.Item;

import java.util.UUID;

public interface ItemPreEvent extends PreEvent {

    UUID getItemId();

    Item getItem();
}
