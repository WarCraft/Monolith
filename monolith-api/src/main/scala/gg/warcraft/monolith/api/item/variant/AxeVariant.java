package gg.warcraft.monolith.api.item.variant;

import gg.warcraft.monolith.api.item.ItemVariant;

public enum AxeVariant implements ItemVariant {
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

    AxeVariant(
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
