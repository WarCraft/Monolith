package gg.warcraft.monolith.app.entity.player.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.core.EventService;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.EquipmentSlot;
import gg.warcraft.monolith.api.entity.MonolithEntityData;
import gg.warcraft.monolith.api.entity.event.EntityTeamChangedEvent;
import gg.warcraft.monolith.api.entity.player.Currency;
import gg.warcraft.monolith.api.entity.player.MonolithPlayerData;
import gg.warcraft.monolith.api.entity.player.MonolithStatistic;
import gg.warcraft.monolith.api.entity.player.Player;
import gg.warcraft.monolith.api.entity.player.PlayerProfile;
import gg.warcraft.monolith.api.entity.player.Statistic;
import gg.warcraft.monolith.api.entity.player.event.PlayerCurrencyGainedEvent;
import gg.warcraft.monolith.api.entity.player.event.PlayerCurrencyLostEvent;
import gg.warcraft.monolith.api.entity.player.event.PlayerStatisticChangedEvent;
import gg.warcraft.monolith.api.entity.player.service.PlayerCommandService;
import gg.warcraft.monolith.api.entity.player.service.PlayerProfileRepository;
import gg.warcraft.monolith.api.entity.player.service.PlayerQueryService;
import gg.warcraft.monolith.api.entity.player.service.PlayerServerAdapter;
import gg.warcraft.monolith.api.entity.team.Team;
import gg.warcraft.monolith.api.entity.team.service.TeamCommandService;
import gg.warcraft.monolith.api.util.ColorCode;
import gg.warcraft.monolith.api.world.item.Item;
import gg.warcraft.monolith.api.world.item.storage.ItemStorageService;
import gg.warcraft.monolith.app.entity.event.SimpleEntityTeamChangedEvent;
import gg.warcraft.monolith.app.entity.player.event.SimplePlayerCurrencyGainedEvent;
import gg.warcraft.monolith.app.entity.player.event.SimplePlayerCurrencyLostEvent;
import gg.warcraft.monolith.app.entity.player.event.SimplePlayerStatisticChangedEvent;

import java.util.Map;
import java.util.UUID;

public class DefaultPlayerCommandService implements PlayerCommandService {
    private final PlayerQueryService playerQueryService;
    private final ItemStorageService itemStorageService;
    private final PlayerProfileRepository playerProfileRepository;
    private final PlayerServerAdapter playerServerAdapter;
    private final TeamCommandService teamCommandService;
    private final EventService eventService;

    @Inject
    public DefaultPlayerCommandService(PlayerQueryService playerQueryService,
                                       ItemStorageService itemStorageService,
                                       PlayerProfileRepository playerProfileRepository,
                                       TeamCommandService teamCommandService,
                                       PlayerServerAdapter playerServerAdapter,
                                       EventService eventService) {
        this.playerQueryService = playerQueryService;
        this.itemStorageService = itemStorageService;
        this.playerProfileRepository = playerProfileRepository;
        this.teamCommandService = teamCommandService;
        this.playerServerAdapter = playerServerAdapter;
        this.eventService = eventService;
    }

    @Override
    public void setTeam(UUID playerId, Team team) {
        Player player = playerQueryService.getPlayer(playerId);
        if (player == null) {
            return;
        }

        PlayerProfile profile = playerProfileRepository.get(playerId);
        String teamName = team != null ? team.getName() : null;
        Team previousTeam = player.getTeam();

        Map<String, String> newData = profile.getData();
        newData.put(MonolithEntityData.TEAM.getKey(), teamName);
        PlayerProfile newProfile = profile.getCopyer()
                .withData(newData)
                .copy();
        playerProfileRepository.save(newProfile);

        EntityTeamChangedEvent entityTeamChangedEvent = new SimpleEntityTeamChangedEvent(
                playerId, EntityType.PLAYER, previousTeam, team);
        eventService.publish(entityTeamChangedEvent);
    }

    @Override
    public void setData(UUID playerId, String data, String value) {
        PlayerProfile profile = playerProfileRepository.get(playerId);
        if (profile == null) {
            return;
        }

        Map<String, String> newData = profile.getData();
        if (value != null && !value.isEmpty()) {
            newData.put(data, value);
        } else {
            newData.remove(data);
        }
        PlayerProfile newProfile = profile.getCopyer().withData(newData).copy();
        playerProfileRepository.save(newProfile);
    }

    @Override
    public void setEquipment(UUID playerId, EquipmentSlot slot, Item item) {
        playerServerAdapter.setEquipment(playerId, slot, item);
    }

    @Override
    public boolean giveItem(UUID playerId, Item item, boolean dropOnFullInventory) {
        Player player = playerQueryService.getPlayer(playerId);
        if (player == null) {
            return false;
        }

        if (player.isOnline() && player.getInventory().hasSpace(1)) {
            return playerServerAdapter.giveItem(playerId, item, dropOnFullInventory);
        } else {
            if (dropOnFullInventory) {
                return playerServerAdapter.giveItem(playerId, item, dropOnFullInventory);
            } else {
                itemStorageService.store(item, playerId);
                return true;
            }
        }
    }

    @Override
    public void addCurrency(UUID playerId, Currency currency, int amount) {
        String currencyName = currency.getName();
        PlayerProfile profile = playerProfileRepository.get(playerId);
        if (profile == null) {
            return;
        }

        Map<String, Integer> newCurrencies = profile.getCurrencies();
        int currentAmount = newCurrencies.getOrDefault(currencyName, 0);
        int newCurrentAmount = currentAmount + amount;
        newCurrencies.put(currencyName, newCurrentAmount);

        Map<String, Integer> newLifetimeCurrencies = profile.getLifetimeCurrencies();
        int currentLifetimeAmount = newLifetimeCurrencies.getOrDefault(currencyName, 0);
        int newLifetimeAmount = currentLifetimeAmount + amount;
        newLifetimeCurrencies.put(currencyName, newLifetimeAmount);

        PlayerProfile newProfile = profile.getCopyer()
                .withCurrencies(newCurrencies)
                .withLifetimeCurrencies(newLifetimeCurrencies)
                .copy();
        playerProfileRepository.save(newProfile);

        PlayerCurrencyGainedEvent event = new SimplePlayerCurrencyGainedEvent(playerId, currencyName, amount,
                newCurrentAmount, newLifetimeAmount);
        eventService.publish(event);
    }

    @Override
    public void removeCurrency(UUID playerId, Currency currency, int amount) {
        String currencyName = currency.getName();
        PlayerProfile profile = playerProfileRepository.get(playerId);
        if (profile == null) {
            return;
        }

        Map<String, Integer> newCurrencies = profile.getCurrencies();
        int currentAmount = newCurrencies.getOrDefault(currencyName, 0);
        if (currentAmount < amount) {
            throw new IllegalArgumentException("Failed to remove " + amount + " " + currencyName +
                    ", player doesn't have that much.");
        }

        int newCurrentAmount = currentAmount - amount;
        newCurrencies.put(currencyName, newCurrentAmount);
        PlayerProfile newProfile = profile.getCopyer()
                .withCurrencies(newCurrencies)
                .copy();
        playerProfileRepository.save(newProfile);

        PlayerCurrencyLostEvent event = new SimplePlayerCurrencyLostEvent(playerId, currencyName, amount,
                newCurrentAmount);
        eventService.publish(event);
    }

    @Override
    public void revokeCurrency(UUID playerId, Currency currency, int amount) {
        String currencyName = currency.getName();
        PlayerProfile profile = playerProfileRepository.get(playerId);
        if (profile == null) {
            return;
        }

        Map<String, Integer> newCurrencies = profile.getCurrencies();
        int currentAmount = newCurrencies.getOrDefault(currencyName, 0);
        int newCurrentAmount = currentAmount - amount;
        newCurrencies.put(currencyName, newCurrentAmount);

        Map<String, Integer> newLifetimeCurrencies = profile.getLifetimeCurrencies();
        int currentLifetimeAmount = newLifetimeCurrencies.getOrDefault(currencyName, 0);
        int newCurrentLifetimeAmount = currentLifetimeAmount - amount;
        newLifetimeCurrencies.put(currencyName, newCurrentLifetimeAmount);

        PlayerProfile newProfile = profile.getCopyer()
                .withCurrencies(newCurrencies)
                .withLifetimeCurrencies(newLifetimeCurrencies)
                .copy();
        playerProfileRepository.save(newProfile);

        // TODO do we want to publish PlayerCurrencyLostEvent here?
    }

    @Override
    public void increaseStatistics(UUID playerId, int amount, Statistic... statistics) {
        PlayerProfile profile = playerProfileRepository.get(playerId);
        if (profile == null) {
            return;
        }

        Map<String, Integer> newStatistics = profile.getStatistics();

        for (Statistic statistic : statistics) {
            String statisticKey = statistic.getKey();
            int currentValue = newStatistics.getOrDefault(statisticKey, 0);
            int newCurrentValue = currentValue + amount;
            newStatistics.put(statisticKey, newCurrentValue);
        }

        PlayerProfile newProfile = profile.getCopyer()
                .withStatistics(newStatistics)
                .copy();
        playerProfileRepository.save(newProfile);

        for (Statistic statistic : statistics) {
            int newCurrentValue = newStatistics.getOrDefault(statistic.getKey(), 0);
            PlayerStatisticChangedEvent event =
                    new SimplePlayerStatisticChangedEvent(playerId, statistic, amount, newCurrentValue);
            eventService.publish(event);
        }
    }

    @Override
    public void resetStatistics(UUID playerId, Statistic... statistics) {
        PlayerProfile profile = playerProfileRepository.get(playerId);
        if (profile == null) {
            return;
        }

        Map<String, Integer> oldStatistics = profile.getStatistics();
        Map<String, Integer> newStatistics = profile.getStatistics();

        for (Statistic statistic : statistics) {
            newStatistics.remove(statistic.getKey());
        }

        PlayerProfile newProfile = profile.getCopyer()
                .withStatistics(newStatistics)
                .copy();
        playerProfileRepository.save(newProfile);

        for (Statistic statistic : statistics) {
            int oldValue = oldStatistics.getOrDefault(statistic.getKey(), 0);
            PlayerStatisticChangedEvent event =
                    new SimplePlayerStatisticChangedEvent(playerId, statistic, -oldValue, 0);
            eventService.publish(event);
        }
    }

    @Override
    public void update(UUID playerId, boolean force) {
        // TODO make sure this doesn't get called when the player just logs in, as their time last seen will be long ago
        // TODO also make sure this doesn't do anything when the player is offline, add isOnline to PlayerServerData
        // TODO player can log off but still be in the iterator of the updater
        Player player = playerQueryService.getPlayer(playerId);
        if (player == null || (!force && !player.isOnline())) {
            return;
        }

        PlayerProfile profile = playerProfileRepository.get(playerId);
        if (profile == null) {
            throw new IllegalArgumentException("Failed to update profile for player with id " + playerId +
                    ", profile doesn't exist");
        }

        long now = System.currentTimeMillis();
        int oldTimeLastSeen = player.getTimeLastSeen();
        int newTimeLastSeen = (int) (now / 1000);
        int oldTimePlayed = player.getStatistic(MonolithStatistic.TIME_PLAYED.getKey());
        int newTimePlayed = oldTimePlayed + (newTimeLastSeen - oldTimeLastSeen);

        int oldTimePlayedOverflow = player.getDataInt(MonolithPlayerData.TIME_PLAYED_OVERFLOW.getKey());
        int currentTimePlayedOverflow = (int) (now % 1000);
        int newTimePlayedOverflow = oldTimePlayedOverflow + currentTimePlayedOverflow;
        if (newTimePlayedOverflow >= 1000) {
            newTimePlayedOverflow -= 1000;
            newTimePlayed += 1;
        }

        PlayerProfile newProfile = profile.getCopyer()
                .withTimeLastSeen(newTimeLastSeen)
                .withTimePlayed(newTimePlayed)
                .withTimePlayedOverflow(newTimePlayedOverflow)
                .copy();
        playerProfileRepository.save(newProfile);

        int deltaTimePlayed = newTimePlayed - oldTimePlayed;
        PlayerStatisticChangedEvent event =
                new SimplePlayerStatisticChangedEvent(playerId, MonolithStatistic.TIME_PLAYED, deltaTimePlayed, newTimePlayed);
        eventService.publish(event);
    }

    @Override
    public void sendMessage(UUID playerId, String message) {
        playerServerAdapter.sendMessage(playerId, message);
    }

    @Override
    public void sendNotification(UUID playerId, String notification) {
        sendMessage(playerId, ColorCode.YELLOW + "[SERVER] " + notification);
    }

    @Override
    public void sendTitle(UUID playerId, String title, String subTitle) {
        playerServerAdapter.sendTitle(playerId, title, subTitle);
    }
}
