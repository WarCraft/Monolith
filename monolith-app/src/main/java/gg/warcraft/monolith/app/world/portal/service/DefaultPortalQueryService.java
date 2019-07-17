package gg.warcraft.monolith.app.world.portal.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.portal.Portal;
import gg.warcraft.monolith.api.world.portal.service.PortalQueryService;
import gg.warcraft.monolith.api.world.portal.service.PortalRepository;

import java.util.List;

public class DefaultPortalQueryService implements PortalQueryService {
    private final PortalRepository repository;

    @Inject
    public DefaultPortalQueryService(PortalRepository repository) {
        this.repository = repository;
    }

    @Override
    public Portal getPortalAt(Location entryLocation) {
        return repository.getFor(entryLocation);
    }

    @Override
    public List<Portal> getAllPortals() {
        return repository.getAll();
    }
}
