package gg.warcraft.monolith.api.combat.event;

import gg.warcraft.monolith.api.combat.ProjectileType;

import java.util.UUID;

public abstract class AbstractProjectileEvent implements ProjectileEvent {
    private final UUID projectileId;
    private final ProjectileType projectileType;
    private final UUID shooterId;

    public AbstractProjectileEvent(UUID projectileId,
                                   ProjectileType projectileType,
                                   UUID shooterId) {
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
