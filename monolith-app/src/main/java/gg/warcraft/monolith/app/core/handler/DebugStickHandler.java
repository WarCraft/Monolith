package gg.warcraft.monolith.app.core.handler;

import gg.warcraft.monolith.api.core.AuthorizationService;
import gg.warcraft.monolith.api.core.event.Event;
import gg.warcraft.monolith.api.entity.EntityInteractEvent;
import gg.warcraft.monolith.api.entity.service.EntityCommandService;

import javax.inject.Inject;

public class DebugStickHandler implements Event.Handler {
    private final AuthorizationService authorizationService;
    private final EntityCommandService entityCommandService;

    @Inject
    public DebugStickHandler(AuthorizationService authorizationService,
                             EntityCommandService entityCommandService) {
        this.authorizationService = authorizationService;
        this.entityCommandService = entityCommandService;
    }

    @Override
    public void handle(Event event) {
        if (event instanceof EntityInteractEvent) {
            onInteractEvent((EntityInteractEvent) event);
        }
    }

    public void onInteractEvent(EntityInteractEvent event) {
        if (authorizationService.isDebugging(event.playerId())) {
            entityCommandService.removeEntity(event.entityId());
        }
    }
}
