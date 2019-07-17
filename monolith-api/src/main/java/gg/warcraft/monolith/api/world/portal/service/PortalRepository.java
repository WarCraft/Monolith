package gg.warcraft.monolith.api.world.portal.service;

import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.portal.Portal;

import java.util.List;

public interface PortalRepository {

    Portal getFor(Location entryLocation);

    List<Portal> getAll();

    void save(Portal portal);

    void delete(Portal portal);
}
