package gg.warcraft.monolith.api.item.variant;

import gg.warcraft.monolith.api.item.ItemVariant;

public enum ChestplateVariant implements ItemVariant {
    LEATHER(true, false, false, false, false),
    CHAINMAIL(false, true, false, false, false),
    IRON(false, false, true, false, false),
    GOLD(false, false, false, true, false),
    DIAMOND(false, false, false, false, true);

    public final boolean leather;
    public final boolean chainmail;
    public final boolean iron;
    public final boolean gold;
    public final boolean diamond;

    ChestplateVariant(
            boolean leather,
            boolean chainmail,
            boolean iron,
            boolean gold,
            boolean diamond
    ) {
        this.leather = leather;
        this.chainmail = chainmail;
        this.iron = iron;
        this.gold = gold;
        this.diamond = diamond;
    }
}
