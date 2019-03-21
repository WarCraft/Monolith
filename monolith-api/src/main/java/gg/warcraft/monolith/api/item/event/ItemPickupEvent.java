package gg.warcraft.monolith.api.item.event;

import gg.warcraft.monolith.api.entity.EntityType;

import java.util.UUID;

public interface ItemPickupEvent extends ItemEvent {

    UUID getEntityId();

    EntityType getEntityType();
}
