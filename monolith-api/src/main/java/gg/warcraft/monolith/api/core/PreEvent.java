package gg.warcraft.monolith.api.core;

/**
 * Pre-events replace Minecraft's event priority system by allowing handlers to cancel or explicitly allow them. They
 * are fired before their corresponding read-only events and once explicitly allowed can no longer be cancelled.
 * Pre-events that have been explicitly allowed will not cancel the native Minecraft event regardless of whether it was
 * also cancelled.
 */
public interface PreEvent extends Event {

    /**
     * @return True if this pre-event has been cancelled, false otherwise.
     */
    boolean isCancelled();

    /**
     * @return True if this pre-event has been explicitly allowed, false otherwise.
     */
    boolean isExplicitlyAllowed();

    /**
     * @return True if this pre-event has been explicitly allowed or not been cancelled, false otherwise.
     */
    default boolean isAllowed() {
        return isExplicitlyAllowed() || !isCancelled();
    }

    /**
     * Cancel this pre-event. If this pre-event is not explicitly allowed the native Minecraft event will be cancelled.
     */
    void cancel();

    /**
     * Explicitly allow this pre-event. Regardless of whether this pre-event was also cancelled the native Minecraft
     * event will not.
     */
    void explicitlyAllow();
}
