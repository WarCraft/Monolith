package gg.warcraft.monolith.spigot.event;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.core.EventService;
import gg.warcraft.monolith.api.core.TaskService;
import gg.warcraft.monolith.api.entity.player.event.PlayerEquipmentChangedEvent;
import gg.warcraft.monolith.api.menu.Button;
import gg.warcraft.monolith.api.menu.Click;
import gg.warcraft.monolith.api.menu.Menu;
import gg.warcraft.monolith.api.menu.service.MenuQueryService;
import gg.warcraft.monolith.app.entity.player.event.SimplePlayerEquipmentChangedEvent;
import gg.warcraft.monolith.app.menu.SimpleClick;
import gg.warcraft.monolith.spigot.menu.MonolithMenuHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerItemHeldEvent;

import java.util.UUID;

public class SpigotInventoryEventMapper implements Listener {
    private final MenuQueryService menuQueryService;
    private final EventService eventService;
    private final TaskService taskService;

    @Inject
    public SpigotInventoryEventMapper(MenuQueryService menuQueryService, EventService eventService,
                                      TaskService taskService) {
        this.menuQueryService = menuQueryService;
        this.eventService = eventService;
        this.taskService = taskService;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClickEvent(InventoryClickEvent event) {
        UUID clickerId = event.getWhoClicked().getUniqueId();
        if (event.getInventory().getHolder() instanceof MonolithMenuHolder) {
            event.setCancelled(true);

            Menu menu = menuQueryService.getMenu(clickerId);
            if (menu != null) {
                if (event.isLeftClick() || event.isRightClick()) {
                    Button button = menu.getButtons().get(event.getRawSlot());
                    if (button != null) {
                        Click click = new SimpleClick(clickerId, event.isLeftClick(), event.isShiftClick());
                        button.getAction().accept(click);
                    }
                    // TODO publish Monolith menu click event
                }
            }
        } else {
            // TODO publish Monolith inventory pre click event
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEquipmentClickEvent(InventoryClickEvent event) {
        UUID clickerId = event.getWhoClicked().getUniqueId();
        InventoryType.SlotType type = event.getSlotType();
        if (type == InventoryType.SlotType.ARMOR) {
            taskService.runNextTick(() -> {
                PlayerEquipmentChangedEvent equipmentChangedEvent = new SimplePlayerEquipmentChangedEvent(clickerId);
                eventService.publish(equipmentChangedEvent);
            });
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        taskService.runNextTick(() -> {
            PlayerEquipmentChangedEvent equipmentChangedEvent = new SimplePlayerEquipmentChangedEvent(playerId);
            eventService.publish(equipmentChangedEvent);
        });
    }
}
