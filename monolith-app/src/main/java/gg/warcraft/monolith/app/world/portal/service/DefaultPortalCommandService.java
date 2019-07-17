package gg.warcraft.monolith.app.world.portal.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.util.Cancellable;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.portal.Portal;
import gg.warcraft.monolith.api.world.portal.service.PortalCommandService;
import gg.warcraft.monolith.api.world.portal.service.PortalRepository;
import gg.warcraft.monolith.app.world.portal.SimplePortal;

import java.util.function.Predicate;

public class DefaultPortalCommandService implements PortalCommandService {
    private final PortalRepository portalRepository;

    @Inject
    public DefaultPortalCommandService(PortalRepository portalRepository) {
        this.portalRepository = portalRepository;
    }

    @Override
    public void createPortal(Location entryLocation, Location exitLocation, Direction exitOrientation,
                             Predicate<Entity> predicate, Cancellable effect) {
        Portal portal = new SimplePortal(entryLocation, exitLocation, exitOrientation, predicate, effect);
        portalRepository.save(portal);
    }

    @Override
    public void deletePortal(Location entryLocation) {
        Portal portal = portalRepository.getFor(entryLocation);
        if (portal == null) {
            return;
        }

        portalRepository.delete(portal);
        Cancellable effect = portal.getEffect();
        if (effect != null) {
            effect.cancel();
        }
    }
}
