package gg.warcraft.monolith.api.effect;

import com.google.inject.name.Named;
import gg.warcraft.monolith.api.util.Duration;
import gg.warcraft.monolith.api.world.Location;

import java.util.function.Supplier;

/**
 * This factory is injectable.
 * <p>
 * EffectFactory serves as a point of entry into the effect implementation. It allows for easy creation of
 * {@code Effect} objects.
 * <p>
 * Effects start running the next tick after creation and will run each tick thereafter (or each period if created as a
 * timed effect). To stop an effect call one of its cancel methods. To pause an effect cancel it and recreate it anew
 * when it is required again.
 */
public interface EffectFactory {

    /**
     * Creates a new {@code Effect}.
     * <p>
     * An {@code Effect} will call all of its effect renderers to display their particles at the location
     * currently provided by the supplier.
     *
     * @param location The location supplier.
     * @return A new {@code Effect}. Never null.
     */
    @Named("simple")
    Effect createSimpleEffect(Supplier<Location> location);

    /**
     * Creates a new periodic {@code Effect}.
     * <p>
     * A periodic {@code Effect} will call all of its effect renderers to display their particles at the location
     * currently provided by the supplier once every period.
     *
     * @param location The location supplier.
     * @param period   The period.
     * @return A new periodic {@code Effect}. Never null.
     */
    @Named("periodic")
    Effect createPeriodicEffect(Supplier<Location> location, Duration period);

    /**
     * Creates a new dynamic {@code Effect}.
     * <p>
     * A dynamic {@code Effect} will call the provided runnable each time before calling all of its renderers. This
     * allows for dynamic updating of the effect without having to schedule a new task for it.
     *
     * @param location The location supplier.
     * @param runnable The runnable.
     * @return A new dynamic {@code Effect}. Never null.
     */
    @Named("dynamic")
    Effect createDynamicEffect(Supplier<Location> location, Runnable runnable);

    /**
     * Creates a new periodic and dynamic {@code Effect}.
     * <p>
     * A periodic {@code Effect} will call all of its effect renderers to display their particles at the location
     * currently provided by the supplier once every period.
     * A dynamic {@code Effect} will call the provided runnable each time before calling all of its renderers. This
     * allows for dynamic updating of the effect without having to schedule a new task for it.
     *
     * @param location The location supplier.
     * @param period   The period.
     * @param runnable The runnable.
     * @return A new periodic and dynamic {@code Effect}. Never null.
     */
    @Named("periodynamic")
    Effect createPeriodicDynamicEffect(Supplier<Location> location, Duration period, Runnable runnable);
}
