package gg.warcraft.monolith.api.world.item.variant;

import gg.warcraft.monolith.api.world.item.ItemVariant;

public enum ShovelVariant implements ItemVariant {
    WOOD(true, false, false, false, false),
    STONE(false, true, false, false, false),
    IRON(false, false, true, false, false),
    GOLD(false, false, false, true, false),
    DIAMOND(false, false, false, false, true);

    public final boolean wood;
    public final boolean stone;
    public final boolean iron;
    public final boolean gold;
    public final boolean diamond;

    ShovelVariant(
            boolean wood,
            boolean stone,
            boolean iron,
            boolean gold,
            boolean diamond
    ) {
        this.wood = wood;
        this.stone = stone;
        this.iron = iron;
        this.gold = gold;
        this.diamond = diamond;
    }
}
