package gg.warcraft.monolith.spigot.item;

import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.world.Location;

import java.util.List;
import java.util.UUID;

public interface SpigotItemService {
    /**
     * @param items    The items to drop. Can not be null, but can be empty. Items can not be null.
     * @param location The location to drop at. Can not be null.
     * @return A list of ids of the dropped items. Never null, but can be empty.
     */
    List<UUID> dropItemsAt(List<Item> items, Location location);
}
