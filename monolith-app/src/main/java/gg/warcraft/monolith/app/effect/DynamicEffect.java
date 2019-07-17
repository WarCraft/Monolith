package gg.warcraft.monolith.app.effect;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.core.TaskService;
import gg.warcraft.monolith.api.util.TimeUtils;
import gg.warcraft.monolith.api.world.Location;

import java.util.function.Supplier;

public class DynamicEffect extends SimpleEffect {
    private final Runnable runnable;

    @Inject
    public DynamicEffect(TaskService taskService, TimeUtils timeUtils,
                         @Assisted Supplier<Location> location, @Assisted Runnable runnable) {
        super(taskService, timeUtils, location);
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();
        super.run();
    }
}
