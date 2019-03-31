package gg.warcraft.monolith.api.entity;

public enum MonolithEntityData {
    TEAM("team");

    private final String key;

    MonolithEntityData(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
