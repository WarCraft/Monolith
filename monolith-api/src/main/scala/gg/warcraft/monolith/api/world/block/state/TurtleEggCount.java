package gg.warcraft.monolith.api.world.block.state;

public enum TurtleEggCount {
    COUNT_1,
    COUNT_2,
    COUNT_3,
    COUNT_4;

    private static final TurtleEggCount[] finalValues = values();

    public static TurtleEggCount valueOf(int data) {
        return finalValues[data - 1];
    }

    public int toInt() {
        return ordinal() + 1;
    }
}
