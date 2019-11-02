package gg.warcraft.monolith.spigot.entity.player.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.entity.EquipmentSlot;
import gg.warcraft.monolith.api.entity.player.PlayerServerData;
import gg.warcraft.monolith.api.entity.player.service.PlayerServerAdapter;
import gg.warcraft.monolith.api.world.item.Item;
import gg.warcraft.monolith.spigot.entity.player.SpigotOfflinePlayerData;
import gg.warcraft.monolith.spigot.entity.player.SpigotPlayerDataFactory;
import gg.warcraft.monolith.spigot.world.item.SpigotItemMapper;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class SpigotPlayerAdapter implements PlayerServerAdapter {
    private final Server server;
    private final SpigotPlayerDataFactory playerDataFactory;
    private final SpigotItemMapper itemMapper;

    @Inject
    public SpigotPlayerAdapter(Server server, SpigotPlayerDataFactory playerDataFactory,
                               SpigotItemMapper itemMapper) {
        this.server = server;
        this.playerDataFactory = playerDataFactory;
        this.itemMapper = itemMapper;
    }

    @Override
    public PlayerServerData getPlayerServerData(UUID playerId) {
        Player player = server.getPlayer(playerId);
        if (player != null) {
            return playerDataFactory.create(player);
        }

        OfflinePlayer offlinePlayer = server.getOfflinePlayer(playerId);
        if (offlinePlayer.hasPlayedBefore()) {
            return new SpigotOfflinePlayerData(offlinePlayer);
        }

        return null;
    }

    @Override
    public Collection<UUID> getOnlinePlayers() {
        return server.getOnlinePlayers().stream()
                .map(Player::getUniqueId)
                .collect(Collectors.toList());
    }

    @Override
    public void setEquipment(UUID playerId, EquipmentSlot slot, Item item) {
        Player player = server.getPlayer(playerId);
        if (player == null) {
            return;
        }

        ItemStack spigotItem = itemMapper.map(item);
        PlayerInventory inventory = player.getInventory();
        switch (slot) {
            case HEAD:
                inventory.setHelmet(spigotItem);
                break;
            case CHEST:
                inventory.setChestplate(spigotItem);
                break;
            case LEGS:
                inventory.setLeggings(spigotItem);
                break;
            case FEET:
                inventory.setBoots(spigotItem);
                break;
            case MAIN_HAND:
                inventory.setItemInMainHand(spigotItem);
                break;
            case OFF_HAND:
                inventory.setItemInOffHand(spigotItem);
                break;
        }
    }

    @Override
    public void setExperienceProgress(UUID playerId, float progress) {
        Player player = server.getPlayer(playerId);
        if (player == null) {
            return;
        }

        float clippedProgress = Math.min(1, Math.max(0, progress));
        player.setExp(clippedProgress);
    }

    @Override
    public void setLevel(UUID playerId, int level) {
        Player player = server.getPlayer(playerId);
        if (player == null) {
            return;
        }

        player.setLevel(level);
    }

    @Override
    public boolean giveItem(UUID playerId, Item item, boolean dropOnFullInventory) {
        Player player = server.getPlayer(playerId);
        if (player == null) {
            return false;
        }

        ItemStack spigotItem = itemMapper.map(item);
        Map<Integer, ItemStack> undelivered = player.getInventory().addItem(spigotItem);
        if (undelivered.isEmpty()) {
            return true;
        } else if (dropOnFullInventory) {
            undelivered.values().forEach(undeliveredItem -> {
                Location playerLocation = player.getLocation();
                playerLocation.getWorld().dropItemNaturally(playerLocation, undeliveredItem);
            });
        }

        return false;
    }

    @Override
    public UUID resolvePlayerId(String minecraftName) {
        Player player = server.getPlayerExact(minecraftName);
        return player != null ? player.getUniqueId() : null;
    }

    @Override
    public void sendMessage(UUID playerId, String message) {
        Player player = server.getPlayer(playerId);
        if (player != null) {
            player.sendMessage(message);
        }
    }

    @Override
    public void sendTitle(UUID playerId, String title, String subTitle) {
        Player player = server.getPlayer(playerId);
        if (player != null) {
            player.sendTitle(title, subTitle);
        }
    }
}
