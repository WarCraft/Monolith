package gg.warcraft.monolith.api.item.event;

import gg.warcraft.monolith.api.core.Event;
import gg.warcraft.monolith.api.item.Item;

import java.util.UUID;

public interface ItemEvent extends Event {

    UUID getItemId();

    Item getItem();
}
