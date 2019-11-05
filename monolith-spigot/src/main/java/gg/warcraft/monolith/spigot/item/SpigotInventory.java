package gg.warcraft.monolith.spigot.item;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.item.Inventory;
import gg.warcraft.monolith.api.world.item.Item;
import gg.warcraft.monolith.api.world.item.ItemType;
import gg.warcraft.monolith.spigot.world.item.SpigotItemMapper;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class SpigotInventory implements Inventory {
    private final SpigotItemMapper itemMapper;
    private final org.bukkit.inventory.Inventory inventory;

    @Inject
    public SpigotInventory(SpigotItemMapper itemMapper, @Assisted org.bukkit.inventory.Inventory inventory) {
        this.itemMapper = itemMapper;
        this.inventory = inventory;
    }

    @Override
    public List<Item> getItems() {
        System.out.println("WARNING Attempted to read items from inventory, always returning empty");
        return new ArrayList<>(); // TODO Arrays.stream(inventory.getContents())
//                .filter(Objects::nonNull)
//                .map(itemMapper::map)
//                .collect(Collectors.toList());
    }

    @Override
    public int getSpace() {
        return (int) Arrays.stream(inventory.getStorageContents())
                .filter(Objects::isNull)
                .count();
    }

    @Override
    public List<Item> add(List<Item> items) {
        ItemStack[] spigotItems = items.stream()
                .map(itemMapper::map)
                .toArray(ItemStack[]::new);
        Map<Integer, ItemStack> excessItems = inventory.addItem(spigotItems);
        System.out.println("WARNING Attempted to add item to inventory, always returning empty");
        return new ArrayList<>();
        // TODO return excessItems.values().stream()
//                .map(itemMapper::map)
//                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    public boolean contains(ItemType type) {
        // TODO return inventory.contains(type.getId(), type.getData());
        System.out.println("WARNING Attempted to read if " + type + " is in inventory, always returning false");
        return false;
    }

    @Override
    public boolean contains(Item item) {
        ItemStack spigotItem = itemMapper.map(item);
        return inventory.contains(spigotItem, 1); // TODO item.getStackSize());
    }

    @Override
    public boolean remove(Item item) {
        ItemStack spigotItem = itemMapper.map(item);
        if (inventory.containsAtLeast(spigotItem, 1)) { // TODO item.getStackSize())) {
            inventory.removeItem(spigotItem);
            return true;
        }
        return false;
    }
}
