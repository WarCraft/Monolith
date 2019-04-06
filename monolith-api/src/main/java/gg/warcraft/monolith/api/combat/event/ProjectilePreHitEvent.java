package gg.warcraft.monolith.api.combat.event;

import gg.warcraft.monolith.api.world.block.Block;

import java.util.UUID;

public interface ProjectilePreHitEvent extends ProjectilePreEvent {

    Block getBlock();

    UUID getEntityId();

    boolean hasBounced();

    void setBounced(boolean bounced);
}
