package gg.warcraft.monolith.api.item;

import gg.warcraft.monolith.api.world.item.Item;
import gg.warcraft.monolith.api.world.item.ItemType;

import java.util.List;

public interface Inventory {

    /**
     * @return All items in this inventory. Never null, but can be empty.
     */
    List<Item> getItems();

    /**
     * @return The amount of empty slots in this inventory.
     */
    int getSpace();

    /**
     * @param items The items to add to the inventory. Can not be null, but can be empty. Items can not be null.
     * @return A list of items that could not be added to the inventory. Never null, but can be empty.
     */
    List<Item> add(List<Item> items);

    /**
     * Clears the inventory.
     */
    void clear();

    /**
     * @param type The item type. Can not be null.
     * @return True if this inventory contains at least one item of the given item type, false otherwise.
     */
    boolean contains(ItemType type);

    /**
     * @param item The item. Can not be null.
     * @return True if this inventory contains at least one item that fully matches the item, false otherwise.
     */
    boolean contains(Item item);

    /**
     * Removes items from the inventory that fully match the given item. The amount of items removed is equal to the
     * stack size of the given item.
     *
     * @param item The item. Can not be null.
     * @return True if the specified item could be removed, false otherwise.
     */
    boolean remove(Item item);
}
