package gg.warcraft.monolith.api.entity.player;

import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.Equipment;
import gg.warcraft.monolith.api.entity.attribute.Attributes;
import gg.warcraft.monolith.api.entity.status.Status;
import gg.warcraft.monolith.api.entity.team.Team;
import gg.warcraft.monolith.api.item.Inventory;
import gg.warcraft.monolith.api.world.location.OrientedLocation;
import org.joml.AABBf;
import org.joml.Vector3f;

import java.util.UUID;

public interface PlayerDecorator extends Player {

    Player getPlayer();

    @Override
    default UUID getId() {
        return getPlayer().getId();
    }

    @Override
    default EntityType getType() {
        return getPlayer().getType();
    }

    @Override
    default String getName() {
        return getPlayer().getName();
    }

    @Override
    default OrientedLocation getLocation() {
        return getPlayer().getLocation();
    }

    @Override
    default OrientedLocation getEyeLocation() {
        return getPlayer().getEyeLocation();
    }

    @Override
    default Vector3f getVelocity() {
        return getPlayer().getVelocity();
    }

    @Override
    default float getHealth() {
        return getPlayer().getHealth();
    }

    @Override
    default Team getTeam() {
        return getPlayer().getTeam();
    }

    @Override
    default Attributes getAttributes() {
        return getPlayer().getAttributes();
    }

    @Override
    default Status getStatus() {
        return getPlayer().getStatus();
    }

    @Override
    default Equipment getEquipment() {
        return getPlayer().getEquipment();
    }

    @Override
    default AABBf getBoundingBox() {
        return getPlayer().getBoundingBox();
    }

    @Override
    default boolean isGrounded() {
        return getPlayer().isGrounded();
    }

    @Override
    default boolean hasPermission(String permission) {
        return getPlayer().hasPermission(permission);
    }

    @Override
    default int getTimeConnected() {
        return getPlayer().getTimeConnected();
    }

    @Override
    default int getTimeFirstConnected() {
        return getPlayer().getTimeFirstConnected();
    }

    @Override
    default int getTimeLastSeen() {
        return getPlayer().getTimeLastSeen();
    }

    @Override
    default int getTimePlayed() {
        return getPlayer().getTimePlayed();
    }

    @Override
    default int getCurrency(String currency) {
        return getPlayer().getCurrency(currency);
    }

    @Override
    default int getLifetimeCurrency(String currency) {
        return getPlayer().getLifetimeCurrency(currency);
    }

    @Override
    default int getStatistic(String statistic) {
        return getPlayer().getStatistic(statistic);
    }

    @Override
    default String getData(String key) {
        return getPlayer().getData(key);
    }

    @Override
    default int getDataInt(String key) {
        return getPlayer().getDataInt(key);
    }

    @Override
    default float getDataFoat(String key) {
        return getPlayer().getDataFoat(key);
    }

    @Override
    default boolean getDataBool(String key) {
        return getPlayer().getDataBool(key);
    }

    @Override
    default GameMode getGameMode() {
        return getPlayer().getGameMode();
    }

    @Override
    default Inventory getInventory() {
        return getPlayer().getInventory();
    }

    @Override
    default boolean isSneaking() {
        return getPlayer().isSneaking();
    }
}
