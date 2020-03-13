package gg.warcraft.monolith.api.block.variant;

import gg.warcraft.monolith.api.block.BlockVariant;
import gg.warcraft.monolith.api.item.ItemVariant;

public enum CoralFanVariant implements BlockVariant, ItemVariant {
    BRAIN(true, false, false, false, false, false),
    BUBBLE(false, true, false, false, false, false),
    FIRE(false, false, true, false, false, false),
    HORN(false, false, false, true, false, false),
    TUBE(false, false, false, false, true, false),

    DEAD_BRAIN(true, false, false, false, false, true),
    DEAD_BUBBLE(false, true, false, false, false, true),
    DEAD_FIRE(false, false, true, false, false, true),
    DEAD_HORN(false, false, false, true, false, true),
    DEAD_TUBE(false, false, false, false, true, true);

    public final boolean brain;
    public final boolean bubble;
    public final boolean fire;
    public final boolean horn;
    public final boolean tube;
    public final boolean dead;

    CoralFanVariant(
            boolean brain,
            boolean bubble,
            boolean fire,
            boolean horn,
            boolean tube,
            boolean dead
    ) {
        this.brain = brain;
        this.bubble = bubble;
        this.fire = fire;
        this.horn = horn;
        this.tube = tube;
        this.dead = dead;
    }
}
