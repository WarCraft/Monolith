package gg.warcraft.monolith.api.world.portal.service;

import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.util.Cancellable;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.Location;

import java.util.function.Predicate;

public interface PortalCommandService {

    void createPortal(Location entryLocation, Location exitLocation, Direction exitOrientation,
                      Predicate<Entity> predicate, Cancellable effect);

    void deletePortal(Location entryLocation);
}
