package gg.warcraft.monolith.app.world.portal.service;

import com.google.inject.Singleton;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.portal.Portal;
import gg.warcraft.monolith.api.world.portal.service.PortalRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class DefaultPortalRepository implements PortalRepository {
    private final Map<Location, Portal> portals;

    public DefaultPortalRepository() {
        this.portals = new HashMap<>();
    }

    @Override
    public Portal getFor(Location entryLocation) {
        return portals.get(entryLocation);
    }

    @Override
    public List<Portal> getAll() {
        return new ArrayList<>(portals.values());
    }

    @Override
    public void save(Portal portal) {
        portals.put(portal.getEntryLocation(), portal);
    }

    @Override
    public void delete(Portal portal) {
        portals.remove(portal.getEntryLocation());
    }
}
