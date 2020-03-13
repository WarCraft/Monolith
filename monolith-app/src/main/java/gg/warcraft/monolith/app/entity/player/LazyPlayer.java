package gg.warcraft.monolith.app.entity.player;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.entity.attribute.Attributes;
import gg.warcraft.monolith.api.entity.player.GameMode;
import gg.warcraft.monolith.api.entity.player.MonolithPlayerData;
import gg.warcraft.monolith.api.entity.player.MonolithStatistic;
import gg.warcraft.monolith.api.entity.player.Player;
import gg.warcraft.monolith.api.entity.player.PlayerProfile;
import gg.warcraft.monolith.api.entity.player.PlayerServerData;
import gg.warcraft.monolith.api.entity.status.Status;
import gg.warcraft.monolith.api.entity.team.service.TeamQueryService;
import gg.warcraft.monolith.api.util.Lazy;
import gg.warcraft.monolith.api.item.Inventory;
import gg.warcraft.monolith.app.entity.LazyEntity;

import java.util.function.Supplier;

public class LazyPlayer extends LazyEntity implements Player {
    private final Lazy<PlayerProfile> profile;
    private final Lazy<PlayerServerData> serverData;

    @Inject
    public LazyPlayer(TeamQueryService teamQueryService,
                      @Assisted("profile") Supplier<PlayerProfile> profileSupplier,
                      @Assisted("data") Supplier<PlayerServerData> serverDataSupplier,
                      @Assisted("attributes") Supplier<Attributes> attributesSupplier,
                      @Assisted("status") Supplier<Status> statusSupplier) {
        super(teamQueryService, profileSupplier, serverDataSupplier, attributesSupplier, statusSupplier);
        this.profile = new Lazy<>(profileSupplier);
        this.serverData = new Lazy<>(serverDataSupplier);
    }

    @Override
    public int getTimeConnected() {
        String timeConnected = profile.get().getData().getOrDefault(MonolithPlayerData.TIME_CONNECTED.getKey(), "0");
        try {
            return Integer.parseInt(timeConnected);
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int getTimeFirstConnected() {
        String timeFirstConnected =
                profile.get().getData().getOrDefault(MonolithPlayerData.TIME_FIRST_CONNECTED.getKey(), "0");
        try {
            return Integer.parseInt(timeFirstConnected);
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int getTimeLastSeen() {
        String timeLastSeen = profile.get().getData().getOrDefault(MonolithPlayerData.TIME_LAST_SEEN.getKey(), "0");
        try {
            return Integer.parseInt(timeLastSeen);
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int getTimePlayed() {
        return profile.get().getStatistics().get(MonolithStatistic.TIME_PLAYED.getKey());
    }

    @Override
    public int getCurrency(String currency) {
        return profile.get().getCurrencies().getOrDefault(currency, 0);
    }

    @Override
    public int getLifetimeCurrency(String currency) {
        return profile.get().getLifetimeCurrencies().getOrDefault(currency, 0);
    }

    @Override
    public int getStatistic(String statistic) {
        return profile.get().getStatistics().getOrDefault(statistic, 0);
    }

    @Override
    public GameMode getGameMode() {
        return serverData.get().getGameMode();
    }

    @Override
    public Inventory getInventory() {
        return serverData.get().getInventory();
    }

    @Override
    public boolean isSneaking() {
        return serverData.get().isSneaking();
    }

    @Override
    public boolean isOnline() {
        return serverData.get().isOnline();
    }
}
