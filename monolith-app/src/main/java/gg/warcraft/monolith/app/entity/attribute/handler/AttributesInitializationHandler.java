package gg.warcraft.monolith.app.entity.attribute.handler;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.attribute.Attributes;
import gg.warcraft.monolith.api.entity.attribute.GenericAttribute;
import gg.warcraft.monolith.api.entity.attribute.service.AttributeCommandService;
import gg.warcraft.monolith.api.entity.attribute.service.AttributeRepository;
import gg.warcraft.monolith.api.entity.EntityDeathEvent;
import gg.warcraft.monolith.api.entity.EntitySpawnEvent;
import gg.warcraft.monolith.api.player.PlayerConnectEvent;
import gg.warcraft.monolith.api.player.PlayerDisconnectEvent;
import gg.warcraft.monolith.api.entity.player.service.PlayerQueryService;
import gg.warcraft.monolith.api.entity.service.EntityServerAdapter;
import gg.warcraft.monolith.api.item.ItemReaderFactory;
import gg.warcraft.monolith.app.entity.attribute.LazyAttributes;

import java.util.HashMap;
import java.util.UUID;

public class AttributesInitializationHandler {
    private final AttributeCommandService attributeCommandService;
    private final AttributeRepository attributeRepository;
    private final EntityServerAdapter entityServerAdapter;
    private final float baseHealth;

    @Inject
    public AttributesInitializationHandler(AttributeCommandService attributeCommandService,
                                           AttributeRepository attributeRepository,
                                           PlayerQueryService playerQueryService,
                                           EntityServerAdapter entityServerAdapter, ItemReaderFactory itemReaderFactory,
                                           @Named("BaseHealth") Float baseHealth) {
        this.attributeCommandService = attributeCommandService;
        this.attributeRepository = attributeRepository;
        this.entityServerAdapter = entityServerAdapter;
        this.baseHealth = baseHealth;
    }

    @Subscribe
    public void onPlayerConnectEvent(PlayerConnectEvent event) {
        UUID playerId = event.playerId();
        Attributes attributes = attributeRepository.getAttributes(playerId);
        if (attributes == null) {
            attributes = new LazyAttributes(attributeCommandService, entityServerAdapter, playerId, new HashMap<>());
            attributeRepository.save(attributes);
        }
        attributeCommandService.addAttributeModifier(playerId, GenericAttribute.MAX_HEALTH, baseHealth);
    }

    @Subscribe
    public void onPlayerDisconnectEvent(PlayerDisconnectEvent event) {
        attributeRepository.delete(event.playerId());
    }

    @Subscribe
    public void onEntitySpawnEvent(EntitySpawnEvent event) {
        if (event.entityType() != EntityType.PLAYER) {
            Attributes attributes = new LazyAttributes(attributeCommandService, entityServerAdapter,
                    event.entityId(), new HashMap<>());
            attributeRepository.save(attributes);
        }
    }

    @Subscribe
    public void onEntityDeathEvent(EntityDeathEvent event) {
        if (event.entityType() != EntityType.PLAYER) {
            attributeRepository.delete(event.entityId());
        }
    }
}
