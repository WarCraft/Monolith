package gg.warcraft.monolith.app.menu;

import gg.warcraft.monolith.api.menu.Button;
import gg.warcraft.monolith.api.menu.Click;
import gg.warcraft.monolith.api.world.block.BlockTypeVariantOrState;
import gg.warcraft.monolith.api.world.item.ItemType;
import gg.warcraft.monolith.api.world.item.ItemTypeOrVariant;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SimpleButton implements Button {
    private final ItemTypeOrVariant icon;
    private final String title;
    private final List<String> tooltip;
    private final Consumer<Click> action;

    public SimpleButton(ItemTypeOrVariant icon, String title, List<String> tooltip, Consumer<Click> action) {
        this.icon = icon;
        this.title = title;
        this.tooltip = tooltip;
        this.action = action;
    }

    @Override
    public ItemTypeOrVariant getIcon() {
        return icon;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<String> getTooltip() {
        return new ArrayList<>(tooltip);
    }

    @Override
    public Consumer<Click> getAction() {
        return action;
    }
}
