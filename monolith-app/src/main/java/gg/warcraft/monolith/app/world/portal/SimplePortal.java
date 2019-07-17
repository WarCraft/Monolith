package gg.warcraft.monolith.app.world.portal;

import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.util.Cancellable;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.portal.Portal;

import java.util.function.Predicate;

public class SimplePortal implements Portal {
    private final Location entryLocation;
    private final Location exitLocation;
    private final Direction exitOrientation;
    private final Predicate<Entity> predicate;
    private final Cancellable effect;

    public SimplePortal(Location entryLocation, Location exitLocation, Direction exitOrientation,
                        Predicate<Entity> predicate, Cancellable effect) {
        this.entryLocation = entryLocation;
        this.exitLocation = exitLocation;
        this.exitOrientation = exitOrientation;
        this.predicate = predicate;
        this.effect = effect;
    }

    @Override
    public Location getEntryLocation() {
        return entryLocation;
    }

    @Override
    public Location getExitLocation() {
        return exitLocation;
    }

    @Override
    public Direction getExitOrientation() {
        return exitOrientation;
    }

    @Override
    public Predicate<Entity> getPredicate() {
        return predicate;
    }

    @Override
    public Cancellable getEffect() {
        return effect;
    }
}
