package gg.warcraft.monolith.api.world.block.state;

public enum TurtleEggAge {
    AGE_0,
    AGE_1,
    AGE_2;

    public int toInt() {
        return ordinal();
    }
}
