package gg.warcraft.monolith.spigot.menu;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.menu.Button;
import gg.warcraft.monolith.api.menu.Menu;
import gg.warcraft.monolith.api.world.item.Item;
import gg.warcraft.monolith.api.world.item.ItemService;
import gg.warcraft.monolith.app.menu.SkullButton;
import gg.warcraft.monolith.spigot.world.item.SpigotItemMapper;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class SpigotMenuMapper {
    private final Server server;
    private final ItemService itemService;
    private final SpigotItemMapper itemMapper;

    @Inject
    public SpigotMenuMapper(Server server, ItemService itemService, SpigotItemMapper itemMapper) {
        this.server = server;
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    public Inventory map(Menu menu, UUID viewerId) {
        Player player = server.getPlayer(viewerId);
        MonolithMenuHolder menuHolder = new MonolithMenuHolder(player);
        Inventory inventory = server.createInventory(menuHolder, menu.getSize().getNumberOfSlots(), menu.getTitle());
        menu.getButtons().forEach((slot, button) -> {
            ItemStack item = map(button);
            inventory.setItem(slot, item);
        });
        return inventory;
    }

    public ItemStack mapSkull(SkullButton button) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setDisplayName(button.getTitle());
        itemMeta.setLore(button.getTooltip());
        itemMeta.setOwner(button.getPlayerName());
        itemMeta.addItemFlags(ItemFlag.values());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack map(Button button) {
        if (button instanceof SkullButton) {
            return mapSkull((SkullButton) button);
        }

        Item item = itemService.create(button.getIcon())
                .withName(button.getTitle())
                .withTooltip(button.getTooltip().toArray(new String[0]))
                .withHideAttributes(true);

        return itemMapper.map(item);
    }
}
