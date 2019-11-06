package gg.warcraft.monolith.api.menu;

import gg.warcraft.monolith.api.menu.service.MenuCommandService;
import gg.warcraft.monolith.api.world.item.ItemType;
import gg.warcraft.monolith.api.world.item.ItemTypeOrVariant;

import java.util.UUID;
import java.util.function.Function;

public abstract class AbstractMenuFactory {
    private final MenuCommandService menuCommandService;
    private final MenuBuilderFactory menuBuilderFactory;
    private final ButtonBuilderFactory buttonBuilderFactory;

    public AbstractMenuFactory(MenuCommandService menuCommandService,
                               MenuBuilderFactory menuBuilderFactory,
                               ButtonBuilderFactory buttonBuilderFactory) {
        this.menuCommandService = menuCommandService;
        this.menuBuilderFactory = menuBuilderFactory;
        this.buttonBuilderFactory = buttonBuilderFactory;
    }

    protected void showMenu(Menu menu, UUID playerId) {
        menuCommandService.showMenu(menu, playerId);
    }

    protected void closeMenu(UUID playerId) {
        menuCommandService.closeMenu(playerId);
    }

    protected MenuBuilder createMenuBuilder(String title, MenuSize size) {
        return menuBuilderFactory.createMenuBuilder(title, size);
    }

    protected ButtonBuilder createSimpleButtonBuilder(ItemTypeOrVariant icon, String title) {
        return buttonBuilderFactory.createSimpleButtonBuilder(icon, title);
    }

    protected ButtonBuilder createSkullButtonBuilder(String skullName, String title) {
        return buttonBuilderFactory.createSkullButtonBuilder(skullName, title);
    }

    protected Button createBackButton(UUID playerId, Function<UUID, Menu> menuFactory) {
        ButtonBuilder builder = createSimpleButtonBuilder(ItemType.DOOR, "Back");
        builder.addTooltip("[CLICK] To go back to");
        builder.addTooltip("the previous menu");

        builder.withAction(action -> {
            Menu menu = menuFactory.apply(playerId);
            showMenu(menu, playerId);
        });
        return builder.build();
    }
}
