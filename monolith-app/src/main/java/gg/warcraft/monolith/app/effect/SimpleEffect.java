package gg.warcraft.monolith.app.effect;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.core.Duration;
import gg.warcraft.monolith.api.core.TaskService;
import gg.warcraft.monolith.api.effect.Effect;
import gg.warcraft.monolith.api.util.TimeUtils;
import gg.warcraft.monolith.api.world.Location;

import java.util.function.Supplier;

public class SimpleEffect extends PeriodicEffect implements Effect {

    @Inject
    public SimpleEffect(TaskService taskService, TimeUtils timeUtils,
                        @Assisted Supplier<Location> location) {
        super(taskService, timeUtils, location, Duration.oneTick());
    }
}
