package gg.warcraft.monolith.api.core;

/**
 * This service is injectable.
 * <p>
 * The TaskService allows for easy scheduling of tasks, whether delayed, recurring, asynchronous, or all of those.
 */
public interface TaskService {

    /**
     * Schedules a task to run the runnable after the delay.
     *
     * @param runnable The runnable. Can not be null.
     * @param delay    The delay. Can not be null.
     * @return The scheduled task. Never null.
     */
    Task runLater(Runnable runnable, Duration delay);

    /**
     * Schedules a task to run the runnable asynchronously after the delay.
     *
     * @param runnable The runnable. Can not be null.
     * @param delay    The delay. Can not be null.
     * @return The scheduled task. Never null.
     */
    Task runLaterAsync(Runnable runnable, Duration delay);

    /**
     * Schedules a task to run the runnable on the next tick.
     *
     * @param runnable The runnable. Can not be null.
     * @return The scheduled task. Never null.
     */
    Task runNextTick(Runnable runnable);

    /**
     * Schedules a task to run the runnable asynchronously on the next tick.
     *
     * @param runnable The runnable. Can not be null.
     * @return The scheduled task. Never null.
     */
    Task runNextTickAsync(Runnable runnable);

    /**
     * Schedules a task to run the runnable repeatedly after the delay each period.
     *
     * @param runnable The runnable. Can not be null.
     * @param delay    The delay. Can not be null.
     * @param period   The period. Can not be null.
     * @return The scheduled task. Never null.
     */
    Task runTask(Runnable runnable, Duration delay, Duration period);

    /**
     * Schedules a task to run the runnable repeatedly asynchronously after the delay each period.
     *
     * @param runnable The runnable. Can not be null.
     * @param delay    The delay. Can not be null.
     * @param period   The period. Can not be null.
     * @return The scheduled task. Never null.
     */
    Task runTaskAsync(Runnable runnable, Duration delay, Duration period);

    /**
     * Schedules a task to run the runnable on the next tick and each tick thereafter.
     *
     * @param runnable The runnable. Can not be null.
     * @return The scheduled task. Never null.
     */
    Task runEachTick(Runnable runnable);

    /**
     * Schedules a task to run the runnable asynchronously on the next tick and each tick thereafter.
     *
     * @param runnable The runnable. Can not be null.
     * @return The scheduled task. Never null.
     */
    Task runEachTickAsync(Runnable runnable);
}
