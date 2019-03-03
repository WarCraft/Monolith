package gg.warcraft.monolith.app.entity.player;

import gg.warcraft.monolith.api.entity.player.PlayerProfile;
import gg.warcraft.monolith.api.entity.player.PlayerProfileCopyer;
import gg.warcraft.monolith.app.entity.SimpleEntityProfile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class SimplePlayerProfile extends SimpleEntityProfile implements PlayerProfile {
    private final long timeConnected;
    private final long timeFirstConnected;
    private final long timeLastSeen;
    private final long timePlayed;
    private final Map<String, Integer> currencies;
    private final Map<String, Integer> lifetimeCurrencies;
    private final Map<String, Integer> statistics;

    public SimplePlayerProfile(UUID playerId, long timeConnected, long timeFirstConnected, long timeLastSeen,
                               long timePlayed, Map<String, Integer> currencies,
                               Map<String, Integer> lifetimeCurrencies, Map<String, Integer> statistics,
                               Map<String, String> data) {
        super(playerId, data);
        this.timeConnected = timeConnected;
        this.timeFirstConnected = timeFirstConnected;
        this.timeLastSeen = timeLastSeen;
        this.timePlayed = timePlayed;
        this.currencies = checkNotNull(currencies);
        this.lifetimeCurrencies = checkNotNull(lifetimeCurrencies);
        this.statistics = checkNotNull(statistics);

        checkArgument(timeConnected >= 0);
        checkArgument(timeFirstConnected >= 0);
        checkArgument(timeLastSeen >= 0);
        checkArgument(timePlayed >= 0);
        checkArgument(!currencies.containsKey(null));
        checkArgument(!currencies.containsKey(""));
        checkArgument(!currencies.containsValue(null));
        checkArgument(!lifetimeCurrencies.containsKey(null));
        checkArgument(!lifetimeCurrencies.containsKey(""));
        checkArgument(!lifetimeCurrencies.containsValue(null));
        checkArgument(!statistics.containsKey(null));
        checkArgument(!statistics.containsKey(""));
        checkArgument(!statistics.containsValue(null));
        currencies.forEach((key, value) -> checkArgument(value >= 0));
        lifetimeCurrencies.forEach((key, value) -> checkArgument(value >= 0));
    }

    @Override
    public UUID getPlayerId() {
        return getEntityId();
    }

    @Override
    public long getTimeConnected() {
        return timeConnected;
    }

    @Override
    public long getTimeFirstConnected() {
        return timeFirstConnected;
    }

    @Override
    public long getTimeLastSeen() {
        return timeLastSeen;
    }

    @Override
    public long getTimePlayed() {
        return timePlayed;
    }

    @Override
    public Map<String, Integer> getCurrencies() {
        return new HashMap<>(currencies);
    }

    @Override
    public Map<String, Integer> getLifetimeCurrencies() {
        return new HashMap<>(lifetimeCurrencies);
    }

    @Override
    public Map<String, Integer> getStatistics() {
        return new HashMap<>(statistics);
    }

    @Override
    public PlayerProfileCopyer getCopyer() {
        return new SimplePlayerProfileCopyer(getEntityId(), timeConnected, timeFirstConnected, timeLastSeen, timePlayed,
                currencies, lifetimeCurrencies, statistics, getData());
    }
}
