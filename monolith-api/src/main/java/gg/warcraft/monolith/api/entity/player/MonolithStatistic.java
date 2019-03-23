package gg.warcraft.monolith.api.entity.player;

public enum MonolithStatistic implements Statistic {
    TIME_PLAYED("Time Played", "timePlayed");

    private final String name;
    private final String key;

    MonolithStatistic(String name, String key) {
        this.name = name;
        this.key = key;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getKey() {
        return key;
    }
}
