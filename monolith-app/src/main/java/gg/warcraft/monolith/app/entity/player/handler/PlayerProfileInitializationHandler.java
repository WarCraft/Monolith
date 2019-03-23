package gg.warcraft.monolith.app.entity.player.handler;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import gg.warcraft.monolith.api.entity.player.MonolithPlayerData;
import gg.warcraft.monolith.api.entity.player.PlayerProfile;
import gg.warcraft.monolith.api.entity.player.event.PlayerPreConnectEvent;
import gg.warcraft.monolith.api.entity.player.service.PlayerProfileRepository;
import gg.warcraft.monolith.app.entity.player.SimplePlayerProfile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerProfileInitializationHandler {
    private final PlayerProfileRepository playerProfileRepository;

    @Inject
    public PlayerProfileInitializationHandler(PlayerProfileRepository playerProfileRepository) {
        this.playerProfileRepository = playerProfileRepository;
    }

    @Subscribe
    public void onPlayerPreConnect(PlayerPreConnectEvent event) {
        UUID playerId = event.getPlayerId();
        int now = (int) (System.currentTimeMillis() / 1000);
        PlayerProfile profile = playerProfileRepository.get(playerId);
        Map<String, String> data = new HashMap<>();
        data.put(MonolithPlayerData.TIME_CONNECTED.getKey(), "" + now);
        data.put(MonolithPlayerData.TIME_FIRST_CONNECTED.getKey(), "" + now);
        data.put(MonolithPlayerData.TIME_LAST_SEEN.getKey(), "" + now);
        if (profile == null) {
            PlayerProfile newProfile = new SimplePlayerProfile(playerId, new HashMap<>(),
                    new HashMap<>(), new HashMap<>(), data);
            playerProfileRepository.save(newProfile);
        } else {
            PlayerProfile newProfile = profile.getCopyer()
                    .withTimeConnected(now)
                    .withTimeLastSeen(now)
                    .copy();
            playerProfileRepository.save(newProfile);
        }
    }
}
