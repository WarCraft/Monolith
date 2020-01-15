package gg.warcraft.monolith.app.entity.player.hiding.handler;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.core.Event;
import gg.warcraft.monolith.api.core.EventHandler;
import gg.warcraft.monolith.api.player.PlayerConnectEvent;
import gg.warcraft.monolith.api.player.PlayerDisconnectEvent;
import gg.warcraft.monolith.api.entity.player.hiding.PlayerHidingRepository;
import gg.warcraft.monolith.api.entity.player.hiding.PlayerHidingServerAdapter;

import java.util.Set;
import java.util.UUID;

public class PlayerHidingHandler implements EventHandler {
    private final PlayerHidingRepository playerHidingRepository;
    private final PlayerHidingServerAdapter playerHidingServerAdapter;

    @Inject
    public PlayerHidingHandler(PlayerHidingRepository playerHidingRepository,
                               PlayerHidingServerAdapter playerHidingServerAdapter) {
        this.playerHidingRepository = playerHidingRepository;
        this.playerHidingServerAdapter = playerHidingServerAdapter;
    }

    @Override
    public void handle(Event event) {
        if (event instanceof PlayerConnectEvent) {
            onPlayerConnectEvent((PlayerConnectEvent) event);
        } else if (event instanceof PlayerDisconnectEvent) {
            onPlayerDisconnectEvent((PlayerDisconnectEvent) event);
        }
    }

    public void onPlayerConnectEvent(PlayerConnectEvent event) {
        UUID playerId = event.playerId();
        Set<UUID> hiddenPlayers = playerHidingRepository.getHiddenPlayers();
        hiddenPlayers.forEach(id -> {
            Set<UUID> newHiddenFrom = playerHidingRepository.getHiddenFrom(id);
            if (newHiddenFrom.add(playerId)) {
                playerHidingServerAdapter.hidePlayer(id, playerId);
                playerHidingRepository.save(id, newHiddenFrom);
            }
        });
    }

    public void onPlayerDisconnectEvent(PlayerDisconnectEvent event) {
        UUID playerId = event.playerId();
        Set<UUID> hiddenPlayers = playerHidingRepository.getHiddenPlayers();
        hiddenPlayers.forEach(id -> {
            Set<UUID> newHiddenFrom = playerHidingRepository.getHiddenFrom(id);
            if (newHiddenFrom.remove(playerId)) {
                playerHidingServerAdapter.revealPlayer(id, playerId);
                playerHidingRepository.save(id, newHiddenFrom);
            }
        });
    }
}
