package gg.warcraft.monolith.app.entity.player.handler;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import gg.warcraft.monolith.api.entity.player.Player;
import gg.warcraft.monolith.api.entity.player.PlayerProfile;
import gg.warcraft.monolith.api.entity.player.event.PlayerConnectEvent;
import gg.warcraft.monolith.api.entity.player.event.PlayerDisconnectEvent;
import gg.warcraft.monolith.api.entity.player.service.PlayerCommandService;
import gg.warcraft.monolith.api.entity.player.service.PlayerProfileRepository;
import gg.warcraft.monolith.api.entity.player.service.PlayerQueryService;

import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;

public class PlayerProfileUpdateHandler implements Runnable {
    private final PlayerQueryService playerQueryService;
    private final PlayerCommandService playerCommandService;
    private final PlayerProfileRepository playerProfileRepository;

    private long startTime;
    private Iterator<UUID> playerIds;

    @Inject
    public PlayerProfileUpdateHandler(PlayerQueryService playerQueryService,
                                      PlayerCommandService playerCommandService,
                                      PlayerProfileRepository playerProfileRepository) {
        this.playerQueryService = playerQueryService;
        this.playerCommandService = playerCommandService;
        this.playerProfileRepository = playerProfileRepository;
        this.startTime = System.currentTimeMillis();
        this.playerIds = Collections.emptyIterator();
    }

    @Override
    public void run() {
        if (!playerIds.hasNext()) {
            if ((System.currentTimeMillis() - startTime) < 2000) {
                return;
            }
            startTime = System.currentTimeMillis();
            playerIds = playerQueryService.getOnlinePlayers().stream()
                    .map(Player::getId)
                    .iterator();
        }

        if (playerIds.hasNext()) {
            UUID playerId = playerIds.next();
            playerCommandService.update(playerId, false);
        }
    }

    @Subscribe
    public void onPlayerConnectEvent(PlayerConnectEvent event) {
        UUID playerId = event.getPlayerId();
        PlayerProfile profile = playerProfileRepository.get(playerId);
        if (profile == null) {
            throw new IllegalArgumentException("Failed to update profile for player with id " + playerId +
                    ", profile doesn't exist");
        }

        int newTimeLastSeen = (int) (System.currentTimeMillis() / 1000);
        PlayerProfile newProfile = profile.getCopyer()
                .withTimeLastSeen(newTimeLastSeen)
                .copy();
        playerProfileRepository.save(newProfile);
    }

    @Subscribe
    public void onPlayerDisconnectEvent(PlayerDisconnectEvent event) {
        playerCommandService.update(event.getPlayerId(), true);
    }
}
