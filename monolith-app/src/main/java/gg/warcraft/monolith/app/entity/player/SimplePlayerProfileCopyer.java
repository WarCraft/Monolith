package gg.warcraft.monolith.app.entity.player;

import gg.warcraft.monolith.api.entity.player.MonolithPlayerData;
import gg.warcraft.monolith.api.entity.player.MonolithStatistic;
import gg.warcraft.monolith.api.entity.player.PlayerProfile;
import gg.warcraft.monolith.api.entity.player.PlayerProfileCopyer;

import java.util.Map;
import java.util.UUID;

public class SimplePlayerProfileCopyer implements PlayerProfileCopyer {
    private final UUID playerId;
    private final Map<String, Integer> currencies;
    private final Map<String, Integer> lifetimeCurrencies;
    private final Map<String, Integer> statistics;
    private final Map<String, String> data;

    public SimplePlayerProfileCopyer(UUID playerId, Map<String, Integer> currencies,
                                     Map<String, Integer> lifetimeCurrencies, Map<String, Integer> statistics,
                                     Map<String, String> data) {
        this.playerId = playerId;
        this.currencies = currencies;
        this.lifetimeCurrencies = lifetimeCurrencies;
        this.statistics = statistics;
        this.data = data;
    }

    @Override
    public PlayerProfileCopyer withTimeConnected(int timeConnected) {
        this.data.put(MonolithPlayerData.TIME_CONNECTED.getKey(), "" + timeConnected);
        return this;
    }

    @Override
    public PlayerProfileCopyer withTimeLastSeen(int timeLastSeen) {
        this.data.put(MonolithPlayerData.TIME_LAST_SEEN.getKey(), "" + timeLastSeen);
        return this;
    }

    @Override
    public PlayerProfileCopyer withTimePlayed(int timePlayed) {
        this.statistics.put(MonolithStatistic.TIME_PLAYED.getKey(), timePlayed);
        return this;
    }

    @Override
    public PlayerProfileCopyer withTimePlayedOverflow(int overflow) {
        this.data.put(MonolithPlayerData.TIME_PLAYED_OVERFLOW.getKey(), "" + overflow);
        return this;
    }

    @Override
    public PlayerProfileCopyer withCurrencies(Map<String, Integer> currencies) {
        this.currencies.clear();
        this.currencies.putAll(currencies);
        return this;
    }

    @Override
    public PlayerProfileCopyer withLifetimeCurrencies(Map<String, Integer> currenciesTotal) {
        this.lifetimeCurrencies.clear();
        this.lifetimeCurrencies.putAll(currenciesTotal);
        return this;
    }

    @Override
    public PlayerProfileCopyer withStatistics(Map<String, Integer> statistics) {
        this.statistics.clear();
        this.statistics.putAll(statistics);
        return this;
    }

    @Override
    public PlayerProfileCopyer withData(Map<String, String> data) {
        this.data.clear();
        this.data.putAll(data);
        return this;
    }

    @Override
    public PlayerProfile copy() {
        return new SimplePlayerProfile(playerId, currencies, lifetimeCurrencies, statistics, data);
    }
}
