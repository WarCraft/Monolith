package gg.warcraft.monolith.api.entity.player.service;

import gg.warcraft.monolith.api.entity.EquipmentSlot;
import gg.warcraft.monolith.api.entity.player.Currency;
import gg.warcraft.monolith.api.entity.player.Statistic;
import gg.warcraft.monolith.api.world.item.Item;

import java.util.UUID;

public interface PlayerCommandServiceDecorator extends PlayerCommandService {

    PlayerCommandService getPlayerCommandService();

    @Override
    default void setEquipment(UUID playerId, EquipmentSlot slot, Item item) {
        getPlayerCommandService().setEquipment(playerId, slot, item);
    }

    @Override
    default boolean giveItem(UUID playerId, Item item, boolean dropOnFullInventory) {
        return getPlayerCommandService().giveItem(playerId, item, dropOnFullInventory);
    }

    @Override
    default void addCurrency(UUID playerId, Currency currency, int amount) {
        getPlayerCommandService().addCurrency(playerId, currency, amount);
    }

    @Override
    default void removeCurrency(UUID playerId, Currency currency, int amount) {
        getPlayerCommandService().removeCurrency(playerId, currency, amount);
    }

    @Override
    default void revokeCurrency(UUID playerId, Currency currency, int amount) {
        getPlayerCommandService().revokeCurrency(playerId, currency, amount);
    }

    @Override
    default void increaseStatistics(UUID playerId, int amount, Statistic... statistics) {
        getPlayerCommandService().increaseStatistics(playerId, amount, statistics);
    }

    @Override
    default void resetStatistics(UUID playerId, Statistic... statistics) {
        getPlayerCommandService().resetStatistics(playerId, statistics);
    }

    @Override
    default void update(UUID playerId, boolean force) {
        getPlayerCommandService().update(playerId, force);
    }

    @Override
    default void sendMessage(UUID playerId, String message) {
        getPlayerCommandService().sendMessage(playerId, message);
    }

    @Override
    default void sendNotification(UUID playerId, String notification) {
        getPlayerCommandService().sendNotification(playerId, notification);
    }

    @Override
    default void sendTitle(UUID playerId, String title, String subTitle) {
        getPlayerCommandService().sendTitle(playerId, title, subTitle);
    }
}
