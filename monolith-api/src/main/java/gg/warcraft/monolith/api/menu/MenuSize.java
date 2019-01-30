package gg.warcraft.monolith.api.menu;

public enum MenuSize {
    ONE_ROW(9),
    TWO_ROWS(18),
    THREE_ROWS(27),
    FOUR_ROWS(36),
    FIVE_ROWS(45),
    SIX_ROWS(54);

    public static MenuSize fromRows(int count) {
        if (count <= 0) {
            return ONE_ROW;
        }
        switch (count) {
            case 1:
                return ONE_ROW;
            case 2:
                return TWO_ROWS;
            case 3:
                return THREE_ROWS;
            case 4:
                return FOUR_ROWS;
            case 5:
                return FIVE_ROWS;
            default:
                return SIX_ROWS;
        }
    }

    private final int numberOfSlots;

    MenuSize(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }
}
