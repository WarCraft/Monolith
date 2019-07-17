package gg.warcraft.monolith.api.effect;

import gg.warcraft.monolith.api.world.Location;

/**
 * A particle is a visual effect that can be displayed to players.
 */
public interface Particle {

    /**
     * Displays this particle at the given location.
     *
     * @param location The location.
     */
    void display(Location location);
}
