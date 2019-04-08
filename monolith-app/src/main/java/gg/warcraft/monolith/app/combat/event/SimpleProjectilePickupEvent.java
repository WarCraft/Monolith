package gg.warcraft.monolith.app.combat.event;

import gg.warcraft.monolith.api.combat.ProjectileType;
import gg.warcraft.monolith.api.combat.event.AbstractProjectileEvent;
import gg.warcraft.monolith.api.combat.event.ProjectilePickupEvent;

import java.util.UUID;

public class SimpleProjectilePickupEvent extends AbstractProjectileEvent implements ProjectilePickupEvent {
    private final UUID pickupEntityId;

    public SimpleProjectilePickupEvent(UUID projectileId,
                                       ProjectileType projectileType,
                                       UUID shooterId,
                                       UUID pickupEntityId) {
        super(projectileId, projectileType, shooterId);
        this.pickupEntityId = pickupEntityId;
    }

    @Override
    public UUID getPickupEntityId() {
        return pickupEntityId;
    }
}
