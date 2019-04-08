package gg.warcraft.monolith.app.combat.event;

import gg.warcraft.monolith.api.combat.ProjectileType;
import gg.warcraft.monolith.api.combat.event.AbstractProjectilePreEvent;
import gg.warcraft.monolith.api.combat.event.ProjectilePrePickupEvent;

import java.util.UUID;

public class SimpleProjectilePrePickupEvent extends AbstractProjectilePreEvent implements ProjectilePrePickupEvent {
    private final UUID pickupEntityId;

    public SimpleProjectilePrePickupEvent(UUID projectileId,
                                          ProjectileType projectileType,
                                          UUID shooterId,
                                          UUID pickupEntityId,
                                          boolean cancelled) {
        super(projectileId, projectileType, shooterId, cancelled);
        this.pickupEntityId = pickupEntityId;
    }

    @Override
    public UUID getPickupEntityId() {
        return pickupEntityId;
    }
}
