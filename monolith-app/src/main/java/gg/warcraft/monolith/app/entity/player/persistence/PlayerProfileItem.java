package gg.warcraft.monolith.app.entity.player.persistence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerProfileItem {
    private final UUID playerId;
    private final Map<String, Integer> currencies;
    private final Map<String, Integer> lifetimeCurrencies;
    private final Map<String, Integer> statistics;
    private final Map<String, String> data;

    @JsonCreator
    public PlayerProfileItem(@JsonProperty("playerId") UUID playerId,
                             @JsonProperty("currencies") Map<String, Integer> currencies,
                             @JsonProperty("lifetimeCurrencies") Map<String, Integer> lifetimeCurrencies,
                             @JsonProperty("statistics") Map<String, Integer> statistics,
                             @JsonProperty("data") Map<String, String> data) {
        this.playerId = playerId;
        this.currencies = currencies;
        this.lifetimeCurrencies = lifetimeCurrencies;
        this.statistics = statistics;
        this.data = data;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public Map<String, Integer> getCurrencies() {
        if (currencies == null) {
            return new HashMap<>();
        }
        return currencies;
    }

    public Map<String, Integer> getLifetimeCurrencies() {
        if (lifetimeCurrencies == null) {
            return new HashMap<>();
        }
        return lifetimeCurrencies;
    }

    public Map<String, Integer> getStatistics() {
        if (statistics == null) {
            return new HashMap<>();
        }
        return statistics;
    }

    public Map<String, String> getData() {
        if (data == null) {
            return new HashMap<>();
        }
        return data;
    }
}
