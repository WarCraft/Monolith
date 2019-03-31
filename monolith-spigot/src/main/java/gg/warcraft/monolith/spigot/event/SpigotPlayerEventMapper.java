package gg.warcraft.monolith.spigot.event;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.core.EventService;
import gg.warcraft.monolith.api.entity.player.event.PlayerConnectEvent;
import gg.warcraft.monolith.api.entity.player.event.PlayerDisconnectEvent;
import gg.warcraft.monolith.api.entity.player.event.PlayerPreConnectEvent;
import gg.warcraft.monolith.api.entity.player.event.PlayerPreRespawnEvent;
import gg.warcraft.monolith.api.entity.player.event.PlayerRespawnEvent;
import gg.warcraft.monolith.api.world.location.Location;
import gg.warcraft.monolith.app.entity.player.event.SimplePlayerConnectEvent;
import gg.warcraft.monolith.app.entity.player.event.SimplePlayerDisconnectEvent;
import gg.warcraft.monolith.app.entity.player.event.SimplePlayerPreConnectEvent;
import gg.warcraft.monolith.app.entity.player.event.SimplePlayerPreRespawnEvent;
import gg.warcraft.monolith.app.entity.player.event.SimplePlayerRespawnEvent;
import gg.warcraft.monolith.spigot.world.location.SpigotLocationMapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class SpigotPlayerEventMapper implements Listener {
    private final EventService eventService;
    private final SpigotLocationMapper locationMapper;

    @Inject
    public SpigotPlayerEventMapper(EventService eventService, SpigotLocationMapper locationMapper) {
        this.eventService = eventService;
        this.locationMapper = locationMapper;
    }

    @EventHandler
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        UUID playerId = event.getUniqueId();
        String name = event.getName();
        PlayerPreConnectEvent preConnectEvent = new SimplePlayerPreConnectEvent(playerId, name);
        eventService.publish(preConnectEvent);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        PlayerConnectEvent connectEvent = new SimplePlayerConnectEvent(playerId);
        eventService.publish(connectEvent);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        PlayerDisconnectEvent disconnectEvent = new SimplePlayerDisconnectEvent(playerId);
        eventService.publish(disconnectEvent);
    }

    @EventHandler
    public void onPlayerKickEvent(PlayerKickEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        PlayerDisconnectEvent disconnectEvent = new SimplePlayerDisconnectEvent(playerId);
        eventService.publish(disconnectEvent);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerPreRespawnEvent(org.bukkit.event.player.PlayerRespawnEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        Location location = locationMapper.map(event.getRespawnLocation());
        PlayerPreRespawnEvent playerPreRespawnEvent =
                new SimplePlayerPreRespawnEvent(playerId, location);
        eventService.publish(playerPreRespawnEvent);

        if (playerPreRespawnEvent.getLocation() != location) {
            org.bukkit.Location newSpawnLocation = locationMapper.map(playerPreRespawnEvent.getLocation());
            event.setRespawnLocation(newSpawnLocation);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerRespawnEvent(org.bukkit.event.player.PlayerRespawnEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        Location location = locationMapper.map(event.getRespawnLocation());
        PlayerRespawnEvent playerRespawnEvent =
                new SimplePlayerRespawnEvent(playerId, location);
        eventService.publish(playerRespawnEvent);
    }
}
