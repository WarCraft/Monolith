package gg.warcraft.monolith.app.entity.player.hiding.handler;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import gg.warcraft.monolith.api.player.PlayerConnectEvent;
import gg.warcraft.monolith.api.player.PlayerDisconnectEvent;
import gg.warcraft.monolith.api.entity.player.hiding.PlayerHidingRepository;
import gg.warcraft.monolith.api.entity.player.hiding.PlayerHidingServerAdapter;

import java.util.Set;
import java.util.UUID;

public class PlayerHidingHandler {
    private final PlayerHidingRepository playerHidingRepository;
    private final PlayerHidingServerAdapter playerHidingServerAdapter;

    @Inject
    public PlayerHidingHandler(PlayerHidingRepository playerHidingRepository,
                               PlayerHidingServerAdapter playerHidingServerAdapter) {
        this.playerHidingRepository = playerHidingRepository;
        this.playerHidingServerAdapter = playerHidingServerAdapter;
    }

    @Subscribe
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

    @Subscribe
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
