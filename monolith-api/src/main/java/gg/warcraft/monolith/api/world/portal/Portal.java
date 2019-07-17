package gg.warcraft.monolith.api.world.portal;

import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.util.Cancellable;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.Location;

import java.util.function.Predicate;

public interface Portal {

    Location getEntryLocation();

    Location getExitLocation();

    Direction getExitOrientation();

    Predicate<Entity> getPredicate();

    Cancellable getEffect();
}
