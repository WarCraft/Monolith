package gg.warcraft.monolith.app.entity.handler;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import gg.warcraft.monolith.api.entity.EntityProfile;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.EntityDeathEvent;
import gg.warcraft.monolith.api.entity.EntitySpawnEvent;
import gg.warcraft.monolith.api.entity.service.EntityProfileRepository;
import gg.warcraft.monolith.app.entity.SimpleEntityProfile;

import java.util.HashMap;
import java.util.UUID;

public class EntityProfileInitializationHandler {
    private final EntityProfileRepository entityProfileRepository;

    @Inject
    public EntityProfileInitializationHandler(EntityProfileRepository entityProfileRepository) {
        this.entityProfileRepository = entityProfileRepository;
    }

    @Subscribe
    public void onEntitySpawn(EntitySpawnEvent event) {
        EntityType entityType = event.entityType();
        if (entityType != EntityType.PLAYER && entityType != EntityType.ARMOR_STAND) {
            UUID entityId = event.entityId();
            EntityProfile profile = entityProfileRepository.get(entityId);
            if (profile == null) {
                EntityProfile newProfile = new SimpleEntityProfile(entityId, new HashMap<>());
                entityProfileRepository.save(newProfile);
            }
        }
    }

    @Subscribe
    public void onEntityDeathEvent(EntityDeathEvent event) {
        EntityType entityType = event.entityType();
        if (entityType != EntityType.PLAYER) {
            entityProfileRepository.delete(event.entityId());
        }
    }
}
