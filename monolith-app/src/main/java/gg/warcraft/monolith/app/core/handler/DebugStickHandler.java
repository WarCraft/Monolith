package gg.warcraft.monolith.app.core.handler;

import com.google.common.eventbus.Subscribe;
import gg.warcraft.monolith.api.core.AuthorizationService;
import gg.warcraft.monolith.api.entity.event.EntityInteractEvent;
import gg.warcraft.monolith.api.entity.service.EntityCommandService;

import javax.inject.Inject;

public class DebugStickHandler {
    private final AuthorizationService authorizationService;
    private final EntityCommandService entityCommandService;

    @Inject
    public DebugStickHandler(AuthorizationService authorizationService,
                             EntityCommandService entityCommandService) {
        this.authorizationService = authorizationService;
        this.entityCommandService = entityCommandService;
    }

    @Subscribe
    public void onInteractEvent(EntityInteractEvent event) {
        if (authorizationService.isDebugging(event.getPlayerId())) {
            entityCommandService.removeEntity(event.getEntityId());
        }
    }
}
