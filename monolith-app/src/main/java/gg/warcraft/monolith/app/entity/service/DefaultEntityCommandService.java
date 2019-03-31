package gg.warcraft.monolith.app.entity.service;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import gg.warcraft.monolith.api.core.EventService;
import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.entity.EntityProfile;
import gg.warcraft.monolith.api.entity.MonolithEntityData;
import gg.warcraft.monolith.api.entity.event.EntityTeamChangedEvent;
import gg.warcraft.monolith.api.entity.service.EntityProfileRepository;
import gg.warcraft.monolith.api.entity.service.EntityQueryService;
import gg.warcraft.monolith.api.entity.service.EntityRepository;
import gg.warcraft.monolith.api.entity.service.EntityServerAdapter;
import gg.warcraft.monolith.api.entity.team.Team;
import gg.warcraft.monolith.api.util.TimeUtils;
import gg.warcraft.monolith.api.world.block.BlockTypeUtils;
import gg.warcraft.monolith.api.world.block.BlockUtils;
import gg.warcraft.monolith.api.world.service.WorldQueryService;
import gg.warcraft.monolith.app.entity.SimpleEntityProfile;
import gg.warcraft.monolith.app.entity.event.SimpleEntityTeamChangedEvent;

import java.util.Map;
import java.util.UUID;

public class DefaultEntityCommandService extends AbstractEntityCommandService {
    private final EntityProfileRepository entityProfileRepository;
    private final EntityQueryService entityQueryService;
    private final EventService eventService;

    @Inject
    public DefaultEntityCommandService(EntityProfileRepository entityProfileRepository, EntityRepository entityRepository,
                                       EntityQueryService entityQueryService, TimeUtils timeUtils,
                                       EntityServerAdapter entityServerAdapter, WorldQueryService worldQueryService,
                                       EventService eventService, BlockUtils blockUtils, BlockTypeUtils blockTypeUtils) {
        super(entityQueryService, entityServerAdapter, entityRepository, entityProfileRepository, worldQueryService,
                eventService, blockUtils, blockTypeUtils, timeUtils);
        this.entityProfileRepository = entityProfileRepository;
        this.entityQueryService = entityQueryService;
        this.eventService = eventService;
    }

    @Override
    public void setTeam(UUID entityId, Team team) {
        Entity entity = entityQueryService.getEntity(entityId);
        if (entity == null) {
            return;
        }

        EntityProfile profile = entityProfileRepository.get(entityId);
        String teamName = team != null ? team.getName() : null;
        Team previousTeam = entity.getTeam();

        Map<String, String> newData = profile.getData();
        newData.put(MonolithEntityData.TEAM.getKey(), teamName);
        EntityProfile newProfile = new SimpleEntityProfile(profile.getEntityId(), newData);
        entityProfileRepository.save(newProfile);

        EntityTeamChangedEvent entityTeamChangedEvent = new SimpleEntityTeamChangedEvent(
                entityId, entity.getType(), previousTeam, team);
        eventService.publish(entityTeamChangedEvent);
    }

    @Override
    public void setData(UUID entityId, String data, String value) {
        Preconditions.checkNotNull(data);
        Preconditions.checkArgument(!data.isEmpty());
        if (value != null) {
            Preconditions.checkArgument(!value.isEmpty());
        }

        EntityProfile profile = entityProfileRepository.get(entityId);
        if (profile == null) {
            throw new IllegalArgumentException("Failed to set data for non-existent non-player entity with id "
                    + entityId + ", did you mean to use PlayerCommandService::setData?");
        }

        Map<String, String> newData = profile.getData();
        if (value != null) {
            newData.put(data, value);
        } else {
            newData.remove(data);
        }
        EntityProfile newProfile = new SimpleEntityProfile(profile.getEntityId(), newData);
        entityProfileRepository.save(newProfile);
    }
}
