package gg.warcraft.monolith.spigot.entity.player;

import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.Equipment;
import gg.warcraft.monolith.api.entity.player.GameMode;
import gg.warcraft.monolith.api.entity.player.PlayerServerData;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.item.Inventory;
import org.bukkit.OfflinePlayer;
import org.joml.AABBf;

import java.util.UUID;

public class SpigotOfflinePlayerData implements PlayerServerData {
    private final OfflinePlayer player;

    public SpigotOfflinePlayerData(OfflinePlayer player) {
        this.player = player;
    }

    @Override
    public UUID getEntityId() {
        return player.getUniqueId();
    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public Location getLocation() {
        throw new IllegalStateException("Failed to get location for offline player with id " + player.getUniqueId());
    }

    @Override
    public Location getEyeLocation() {
        throw new IllegalStateException("Failed to get eye location for offline player with id " + player.getUniqueId());
    }

    @Override
    public Vector3f getVelocity() {
        throw new IllegalStateException("Failed to get velocity for offline player with id " + player.getUniqueId());
    }

    @Override
    public float getHealth() {
        throw new IllegalStateException("Failed to get health for offline player with id " + player.getUniqueId());
    }

    @Override
    public Equipment getEquipment() {
        throw new IllegalStateException("Failed to get equipment for offline player with id " + player.getUniqueId());
    }

    @Override
    public AABBf getBoundingBox() {
        throw new IllegalStateException("Failed to get bounding box for offline player with id " + player.getUniqueId());
    }

    @Override
    public GameMode getGameMode() {
        throw new IllegalStateException("Failed to get game mode for offline player with id " + player.getUniqueId());
    }

    @Override
    public Inventory getInventory() {
        throw new IllegalStateException("Failed to get inventory for offline player with id " + player.getUniqueId());
    }

    @Override
    public boolean isGrounded() {
        return false;
    }

    @Override
    public boolean isSneaking() {
        throw new IllegalStateException("Failed to get sneaking for offline player with id " + player.getUniqueId());
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public boolean hasPermission(String permission) {
        throw new IllegalStateException("Failed to get permission for offline player with id " + player.getUniqueId());
    }
}
