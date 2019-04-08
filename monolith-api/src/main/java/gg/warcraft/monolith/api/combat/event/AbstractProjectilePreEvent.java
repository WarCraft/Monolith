package gg.warcraft.monolith.api.combat.event;

import gg.warcraft.monolith.api.combat.ProjectileType;
import gg.warcraft.monolith.api.core.event.AbstractPreEvent;

import java.util.UUID;

public abstract class AbstractProjectilePreEvent extends AbstractPreEvent implements ProjectilePreEvent {
    private final UUID projectileId;
    private final ProjectileType projectileType;
    private final UUID shooterId;

    public AbstractProjectilePreEvent(UUID projectileId,
                                      ProjectileType projectileType,
                                      UUID shooterId,
                                      boolean cancelled) {
        super(cancelled);
        this.projectileId = projectileId;
        this.projectileType = projectileType;
        this.shooterId = shooterId;
    }

    @Override
    public UUID getProjectileId() {
        return projectileId;
    }

    @Override
    public ProjectileType getProjectileType() {
        return projectileType;
    }

    @Override
    public UUID getShooterId() {
        return shooterId;
    }
}
