package gg.warcraft.monolith.api.entity.player;

import java.util.Map;

public interface PlayerProfileCopyer {

    PlayerProfileCopyer withTimeConnected(int timeConnected);

    PlayerProfileCopyer withTimeLastSeen(int timeLastSeen);

    PlayerProfileCopyer withTimePlayed(int timePlayed);

    PlayerProfileCopyer withTimePlayedOverflow(int overflow);

    PlayerProfileCopyer withCurrencies(Map<String, Integer> currencies);

    PlayerProfileCopyer withLifetimeCurrencies(Map<String, Integer> lifetimeCurrencies);

    PlayerProfileCopyer withStatistics(Map<String, Integer> statistics);

    PlayerProfileCopyer withData(Map<String, String> data);

    PlayerProfile copy();
}
