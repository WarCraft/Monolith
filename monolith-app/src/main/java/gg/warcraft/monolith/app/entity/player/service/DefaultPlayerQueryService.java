package gg.warcraft.monolith.app.entity.player.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.entity.EntityFactory;
import gg.warcraft.monolith.api.entity.attribute.service.AttributeQueryService;
import gg.warcraft.monolith.api.entity.player.Player;
import gg.warcraft.monolith.api.entity.player.PlayerServerData;
import gg.warcraft.monolith.api.entity.player.service.PlayerProfileRepository;
import gg.warcraft.monolith.api.entity.player.service.PlayerQueryService;
import gg.warcraft.monolith.api.entity.player.service.PlayerServerAdapter;
import gg.warcraft.monolith.api.entity.status.StatusService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DefaultPlayerQueryService implements PlayerQueryService {
    private final PlayerProfileRepository playerProfileRepository;
    private final AttributeQueryService attributeQueryService;
    private final StatusService statusService;
    private final EntityFactory entityFactory;
    private final PlayerServerAdapter playerServerAdapter;

    @Inject
    public DefaultPlayerQueryService(PlayerProfileRepository playerProfileRepository,
                                     AttributeQueryService attributeQueryService, StatusService statusService,
                                     EntityFactory entityFactory, PlayerServerAdapter playerServerAdapter) {
        this.playerProfileRepository = playerProfileRepository;
        this.attributeQueryService = attributeQueryService;
        this.statusService = statusService;
        this.entityFactory = entityFactory;
        this.playerServerAdapter = playerServerAdapter;
    }

    @Override
    public Player getPlayer(UUID playerId) {
        PlayerServerData serverData = playerServerAdapter.getPlayerServerData(playerId);
        if (serverData != null) {
            return entityFactory.createPlayer(
                    () -> playerProfileRepository.get(playerId),
                    () -> serverData,
                    () -> attributeQueryService.getAttributes(playerId),
                    () -> statusService.getStatus(playerId));
        }

        return null;
    }

    @Override
    public List<? extends Player> getOnlinePlayers() {
        return playerServerAdapter.getOnlinePlayers().stream()
                .map(this::getPlayer)
                .collect(Collectors.toList());
    }

    @Override
    public UUID resolvePlayerId(String minecraftName) {
        return playerServerAdapter.resolvePlayerId(minecraftName);
    }
}
