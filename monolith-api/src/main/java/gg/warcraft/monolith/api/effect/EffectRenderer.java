package gg.warcraft.monolith.api.effect;

import gg.warcraft.monolith.api.world.Location;

/**
 * An effect renderer is a collection of particles that can be displayed at a location.
 */
public interface EffectRenderer {

    /**
     * Renders all particles registered with this renderer at the location.
     *
     * @param location The location.
     */
    void renderAt(Location location);
}
