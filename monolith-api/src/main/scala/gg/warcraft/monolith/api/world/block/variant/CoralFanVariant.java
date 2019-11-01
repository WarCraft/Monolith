package gg.warcraft.monolith.api.world.block.variant;

import gg.warcraft.monolith.api.world.block.BlockVariant;
import gg.warcraft.monolith.api.world.item.ItemVariant;

public enum CoralFanVariant implements BlockVariant, ItemVariant {
    BRAIN(true, false, false, false, false, false, false),
    BUBBLE(false, true, false, false, false, false, false),
    FIRE(false, false, true, false, false, false, false),
    HORN(false, false, false, true, false, false, false),
    TUBE(false, false, false, false, true, false, false),

    DEAD_BRAIN(true, false, false, false, false, true, false),
    DEAD_BUBBLE(false, true, false, false, false, true, false),
    DEAD_FIRE(false, false, true, false, false, true, false),
    DEAD_HORN(false, false, false, true, false, true, false),
    DEAD_TUBE(false, false, false, false, true, true, false),

    BRAIN_WALL(true, false, false, false, false, false, true),
    BUBBLE_WALL(false, true, false, false, false, false, true),
    FIRE_WALL(false, false, true, false, false, false, true),
    HORN_WALL(false, false, false, true, false, false, true),
    TUBE_WALL(false, false, false, false, true, false, true),

    DEAD_BRAIN_WALL(true, false, false, false, false, true, true),
    DEAD_BUBBLE_WALL(false, true, false, false, false, true, true),
    DEAD_FIRE_WALL(false, false, true, false, false, true, true),
    DEAD_HORN_WALL(false, false, false, true, false, true, true),
    DEAD_TUBE_WALL(false, false, false, false, true, true, true);

    public final boolean brain;
    public final boolean bubble;
    public final boolean fire;
    public final boolean horn;
    public final boolean tube;
    public final boolean dead;
    public final boolean wall;

    CoralFanVariant(
            boolean brain,
            boolean bubble,
            boolean fire,
            boolean horn,
            boolean tube,
            boolean dead,
            boolean wall
    ) {
        this.brain = brain;
        this.bubble = bubble;
        this.fire = fire;
        this.horn = horn;
        this.tube = tube;
        this.dead = dead;
        this.wall = wall;
    }
}
