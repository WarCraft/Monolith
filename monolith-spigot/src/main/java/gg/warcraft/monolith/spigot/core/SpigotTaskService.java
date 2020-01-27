package gg.warcraft.monolith.spigot.core;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.core.Task;
import gg.warcraft.monolith.api.core.TaskService;
import gg.warcraft.monolith.api.core.Duration;
import gg.warcraft.monolith.api.util.TimeUtils;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class SpigotTaskService implements TaskService {
    private final Plugin plugin;

    @Inject
    public SpigotTaskService(Plugin plugin) {
        this.plugin = plugin;
    }

    private BukkitRunnable wrapBukkitRunnable(Runnable runnable) {
        return new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        };
    }

    @Override
    public Task runLater(Runnable runnable, Duration delay) {
        BukkitRunnable bukkitRunnable = wrapBukkitRunnable(runnable);
        BukkitTask bukkitTask = bukkitRunnable.runTaskLater(plugin, delay.inTicks());
        return new SpigotTask(bukkitTask);
    }

    @Override
    public Task runLaterAsync(Runnable runnable, Duration delay) {
        BukkitRunnable bukkitRunnable = wrapBukkitRunnable(runnable);
        BukkitTask bukkitTask = bukkitRunnable.runTaskLaterAsynchronously(plugin, delay.inTicks());
        return new SpigotTask(bukkitTask);
    }

    @Override
    public Task runNextTick(Runnable runnable) {
        BukkitRunnable bukkitRunnable = wrapBukkitRunnable(runnable);
        BukkitTask bukkitTask = bukkitRunnable.runTask(plugin);
        return new SpigotTask(bukkitTask);
    }

    @Override
    public Task runNextTickAsync(Runnable runnable) {
        BukkitRunnable bukkitRunnable = wrapBukkitRunnable(runnable);
        BukkitTask bukkitTask = bukkitRunnable.runTaskAsynchronously(plugin);
        return new SpigotTask(bukkitTask);
    }

    @Override
    public Task runTask(Runnable runnable, Duration delay, Duration period) {
        BukkitRunnable bukkitRunnable = wrapBukkitRunnable(runnable);
        BukkitTask bukkitTask = bukkitRunnable.runTaskTimer(plugin, delay.inTicks(), period.inTicks());
        return new SpigotTask(bukkitTask);
    }

    @Override
    public Task runTaskAsync(Runnable runnable, Duration delay, Duration period) {
        BukkitRunnable bukkitRunnable = wrapBukkitRunnable(runnable);
        BukkitTask bukkitTask = bukkitRunnable.runTaskTimerAsynchronously(plugin, delay.inTicks(), period.inTicks());
        return new SpigotTask(bukkitTask);
    }

    @Override
    public Task runEachTick(Runnable runnable) {
        return runTask(runnable, Duration.oneTick(), Duration.oneTick());
    }

    @Override
    public Task runEachTickAsync(Runnable runnable) {
        return runTaskAsync(runnable, Duration.oneTick(), Duration.oneTick());
    }
}
