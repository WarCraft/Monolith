package gg.warcraft.monolith.api.effect;

import gg.warcraft.monolith.api.util.Cancellable;
import gg.warcraft.monolith.api.core.Duration;

/**
 * An effect is a collection of particle renderers that runs repeatedly until cancelled.
 */
public interface Effect extends Runnable, Cancellable {

    /**
     * Adds the effect renderer to this effect.
     *
     * @param renderer The effect renderer.
     */
    void addRenderer(EffectRenderer renderer);

    /**
     * Clears all effect renderers from this effect.
     */
    void clearRenderers();

    /**
     * Cancels this effect after the given delay.
     *
     * @param delay The delay.
     */
    void cancel(Duration delay);
}
