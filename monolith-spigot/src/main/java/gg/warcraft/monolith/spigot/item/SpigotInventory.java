package gg.warcraft.monolith.spigot.item;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.item.Inventory;
import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.item.ItemType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
        return Arrays.stream(inventory.getContents())
                .filter(Objects::nonNull)
                .map(itemMapper::map)
                .collect(Collectors.toList());
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
        return excessItems.values().stream()
                .map(itemMapper::map)
                .collect(Collectors.toList());
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
        return inventory.contains(spigotItem, item.getStackSize());
    }

    @Override
    public boolean remove(Item item) {
        ItemStack spigotItem = itemMapper.map(item);
        if (inventory.containsAtLeast(spigotItem, item.getStackSize())) {
            inventory.removeItem(spigotItem);
            return true;
        }
        return false;
    }
}
