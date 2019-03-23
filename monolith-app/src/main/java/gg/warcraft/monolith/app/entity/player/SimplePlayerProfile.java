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
    private final Map<String, Integer> currencies;
    private final Map<String, Integer> lifetimeCurrencies;
    private final Map<String, Integer> statistics;

    public SimplePlayerProfile(UUID playerId, Map<String, Integer> currencies,
                               Map<String, Integer> lifetimeCurrencies, Map<String, Integer> statistics,
                               Map<String, String> data) {
        super(playerId, data);
        this.currencies = checkNotNull(currencies);
        this.lifetimeCurrencies = checkNotNull(lifetimeCurrencies);
        this.statistics = checkNotNull(statistics);

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
        return new SimplePlayerProfileCopyer(getEntityId(), currencies, lifetimeCurrencies, statistics, getData());
    }
}
