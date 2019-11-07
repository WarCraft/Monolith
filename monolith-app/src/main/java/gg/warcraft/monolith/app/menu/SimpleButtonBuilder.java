package gg.warcraft.monolith.app.menu;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.menu.Button;
import gg.warcraft.monolith.api.menu.ButtonBuilder;
import gg.warcraft.monolith.api.menu.Click;
import gg.warcraft.monolith.api.util.ColorCode;
import gg.warcraft.monolith.api.world.item.ItemType;
import gg.warcraft.monolith.api.world.item.ItemTypeOrVariant;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SimpleButtonBuilder implements ButtonBuilder {
    private final ItemTypeOrVariant icon;
    private final String title;

    private List<String> tooltip;
    private Consumer<Click> action;

    @Inject
    public SimpleButtonBuilder(@Assisted ItemTypeOrVariant icon, @Assisted String title) {
        this.icon = icon;
        this.title = ColorCode.WHITE + title;
        this.tooltip = new ArrayList<>();
        this.action = click -> {
        };
    }

    @Override
    public void addTooltip(String tooltip) {
        Preconditions.checkNotNull(tooltip);
        this.tooltip.add(ColorCode.GRAY + tooltip);
    }

    @Override
    public void withTooltip(List<String> tooltip) {
        Preconditions.checkNotNull(tooltip);
        this.tooltip = tooltip.stream()
                .map(line -> ColorCode.GRAY + line)
                .collect(Collectors.toList());
    }

    @Override
    public void withAction(Consumer<Click> action) {
        Preconditions.checkNotNull(action);
        this.action = action;
    }

    @Override
    public Button build() {
        return new SimpleButton(icon, title, tooltip, action);
    }
}
