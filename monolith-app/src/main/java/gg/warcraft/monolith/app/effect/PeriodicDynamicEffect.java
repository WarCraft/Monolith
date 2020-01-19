package gg.warcraft.monolith.app.effect;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.core.TaskService;
import gg.warcraft.monolith.api.core.Duration;
import gg.warcraft.monolith.api.util.TimeUtils;
import gg.warcraft.monolith.api.world.Location;

import java.util.function.Supplier;

public class PeriodicDynamicEffect extends PeriodicEffect {
    private final Runnable runnable;

    @Inject
    public PeriodicDynamicEffect(TaskService taskService, TimeUtils timeUtils,
                                 @Assisted Supplier<Location> location, @Assisted Duration period,
                                 @Assisted Runnable runnable) {
        super(taskService, timeUtils, location, period);
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();
        super.run();
    }
}
