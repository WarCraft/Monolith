package gg.warcraft.monolith.app.combat.event;

import gg.warcraft.monolith.api.combat.ProjectileType;
import gg.warcraft.monolith.api.combat.event.AbstractProjectilePreEvent;
import gg.warcraft.monolith.api.combat.event.ProjectilePreHitEvent;
import gg.warcraft.monolith.api.world.block.Block;

import java.util.UUID;

public class SimpleProjectilePreHitEvent extends AbstractProjectilePreEvent implements ProjectilePreHitEvent {
    private final Block block;
    private final UUID entityId;

    private boolean bounced;

    public SimpleProjectilePreHitEvent(UUID projectileId, ProjectileType projectileType, Block block, UUID entityId,
                                       boolean cancelled) {
        super(projectileId, projectileType, cancelled);
        this.block = block;
        this.entityId = entityId;
        this.bounced = cancelled;
    }

    @Override
    public Block getBlock() {
        return block;
    }

    @Override
    public UUID getEntityId() {
        return entityId;
    }

    @Override
    public boolean hasBounced() {
        return bounced;
    }

    @Override
    public void setBounced(boolean bounced) {
        this.bounced = bounced;
    }
}
