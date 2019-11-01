package gg.warcraft.monolith.app.menu;

import gg.warcraft.monolith.api.menu.Button;
import gg.warcraft.monolith.api.menu.Click;
import gg.warcraft.monolith.api.world.item.ItemType;

import java.util.List;
import java.util.function.Consumer;

public class SkullButton extends SimpleButton implements Button {
    private final String playerName;

    public SkullButton(String playerName, String title, List<String> tooltip, Consumer<Click> action) {
        super(ItemType.MOB_HEAD, title, tooltip, action);
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
