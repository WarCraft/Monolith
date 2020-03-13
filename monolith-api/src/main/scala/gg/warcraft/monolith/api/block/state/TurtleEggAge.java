package gg.warcraft.monolith.api.block.state;

public enum TurtleEggAge {
    AGE_0,
    AGE_1,
    AGE_2;

    private static final TurtleEggAge[] finalValues = values();

    public static TurtleEggAge valueOf(int data) {
        return finalValues[data];
    }

    public int toInt() {
        return ordinal();
    }
}
