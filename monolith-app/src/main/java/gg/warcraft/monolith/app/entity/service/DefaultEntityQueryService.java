package gg.warcraft.monolith.app.entity.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.entity.EntityFactory;
import gg.warcraft.monolith.api.entity.EntityServerData;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.attribute.service.AttributeQueryService;
import gg.warcraft.monolith.api.entity.player.service.PlayerQueryService;
import gg.warcraft.monolith.api.entity.service.EntityProfileRepository;
import gg.warcraft.monolith.api.entity.service.EntityQueryService;
import gg.warcraft.monolith.api.entity.service.EntityServerAdapter;
import gg.warcraft.monolith.api.entity.status.StatusService;
import gg.warcraft.monolith.api.world.Location;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DefaultEntityQueryService implements EntityQueryService {
    private final EntityProfileRepository entityProfileRepository;
    private final AttributeQueryService attributeQueryService;
    private final StatusService statusService;
    private final EntityServerAdapter entityServerAdapter;
    private final PlayerQueryService playerQueryService;
    private final EntityFactory entityFactory;

    @Inject
    public DefaultEntityQueryService(EntityProfileRepository entityProfileRepository,
                                     AttributeQueryService attributeQueryService, StatusService statusService,
                                     EntityServerAdapter entityServerAdapter, PlayerQueryService playerQueryService,
                                     EntityFactory entityFactory) {
        this.entityProfileRepository = entityProfileRepository;
        this.attributeQueryService = attributeQueryService;
        this.statusService = statusService;
        this.entityServerAdapter = entityServerAdapter;
        this.playerQueryService = playerQueryService;
        this.entityFactory = entityFactory;
    }

    Entity getEntity(EntityServerData serverData) {
        if (serverData.getType() == EntityType.PLAYER) {
            return playerQueryService.getPlayer(serverData.getEntityId());
        }

        UUID entityId = serverData.getEntityId();
        return entityFactory.createEntity(
                () -> entityProfileRepository.get(entityId),
                () -> serverData,
                () -> attributeQueryService.getAttributes(entityId),
                () -> statusService.getStatus(entityId));
    }

    @Override
    public Entity getEntity(UUID entityId) {
        EntityServerData serverData = entityServerAdapter.getEntityServerData(entityId);
        if (serverData != null) {
            return getEntity(serverData);
        }
        return null;
    }

    @Override
    public List<Entity> getNearbyEntities(Location location, float radius) {
        return getNearbyEntities(location, radius, radius, radius);
    }

    @Override
    public List<Entity> getNearbyEntities(Location location, float deltaX, float deltaY, float deltaZ) {
        return entityServerAdapter.getNearbyEntitiesServerData(location, deltaX, deltaY, deltaZ).stream()
                .map(this::getEntity)
                .collect(Collectors.toList());
    }
}
