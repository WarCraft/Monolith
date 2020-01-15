package gg.warcraft.monolith.spigot.entity.handler;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import gg.warcraft.monolith.api.player.PlayerConnectEvent;
import org.bukkit.Server;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class GenericAttributeModifierHandler {
    private final Server server;

    @Inject
    public GenericAttributeModifierHandler(Server server) {
        this.server = server;
    }

    @Subscribe
    public void onPlayerConnectEvent(PlayerConnectEvent event) {
        Player player = server.getPlayer(event.playerId());
        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        // TODO attribute
    }
}
