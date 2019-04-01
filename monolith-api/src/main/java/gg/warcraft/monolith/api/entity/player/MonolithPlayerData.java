package gg.warcraft.monolith.api.entity.player;

public enum MonolithPlayerData {
    TIME_CONNECTED("timeConnected"),
    TIME_FIRST_CONNECTED("timeFirstConnected"),
    TIME_LAST_SEEN("timeLastSeen"),
    TIME_PLAYED_OVERFLOW("timePlayedOverflow");

    private final String key;

    MonolithPlayerData(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
