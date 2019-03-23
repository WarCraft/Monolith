package gg.warcraft.monolith.api.entity.player;

import gg.warcraft.monolith.api.entity.EntityProfile;

import java.util.Map;
import java.util.UUID;

public interface PlayerProfile extends EntityProfile {

    /**
     * @return The id of the player this data belongs to. Never null.
     */
    UUID getPlayerId();

    Map<String, Integer> getCurrencies();

    Map<String, Integer> getLifetimeCurrencies();

    Map<String, Integer> getStatistics();

    PlayerProfileCopyer getCopyer();
}
