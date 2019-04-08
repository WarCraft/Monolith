package gg.warcraft.monolith.api.combat.event;

import gg.warcraft.monolith.api.combat.ProjectileType;
import gg.warcraft.monolith.api.core.Event;

import java.util.UUID;

public interface ProjectileEvent extends Event {

    UUID getProjectileId();

    ProjectileType getProjectileType();

    UUID getShooterId();
}
