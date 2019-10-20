package gg.warcraft.monolith.api.world.block;

public enum BlockColor {
    BLACK("Black"),
    BLUE("Blue"),
    BROWN("Brown"),
    CYAN("Cyan"),
    GRAY("Gray"),
    GREEN("Green"),
    LIGHT_BLUE("Light Blue"),
    LIGHT_GRAY("Light Gray"),
    LIME("Lime"),
    MAGENTA("Magenta"),
    ORANGE("Orange"),
    PINK("Pink"),
    PURPLE("Purple"),
    RED("Red"),
    WHITE("White"),
    YELLOW("Yellow");

    public final String capitalized;

    BlockColor(String capitalized) {
        this.capitalized = capitalized;
    }
}
