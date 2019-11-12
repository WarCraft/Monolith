package gg.warcraft.monolith.spigot.core;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.core.AuthorizationService;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SpigotAuthorizationService implements AuthorizationService {
    private static final String STAFF_PERMISSION_STRING = "monolith.staff";
    private static final String ADMIN_PERMISSION_STRING = "monolith.admin";
    private static final String MOD_PERMISSION_STRING = "monolith.mod";
    private static final String DEV_PERMISSION_STRING = "monolith.dev";

    private final Server server;

    @Inject
    public SpigotAuthorizationService(Server server) {
        this.server = server;
    }

    @Override
    public boolean isDev(final UUID playerId) {
        Player player = server.getPlayer(playerId);
        return player != null && player.hasPermission(DEV_PERMISSION_STRING);
    }

    @Override
    public boolean isStaff(final UUID playerId) {
        Player player = server.getPlayer(playerId);
        return player != null && player.hasPermission(STAFF_PERMISSION_STRING);
    }

    @Override
    public boolean isAdmin(final UUID playerId) {
        Player player = server.getPlayer(playerId);
        return player != null && player.hasPermission(ADMIN_PERMISSION_STRING);
    }

    @Override
    public boolean isMod(final UUID playerId) {
        Player player = server.getPlayer(playerId);
        return player != null && player.hasPermission(MOD_PERMISSION_STRING);
    }

    @Override
    public boolean isModerating(final UUID playerId) {
        Player player = server.getPlayer(playerId);
        if (player == null) {
            return false;
        }
        Material offhandMaterial = player.getInventory().getItemInOffHand().getType();
        return player.hasPermission(MOD_PERMISSION_STRING) && offhandMaterial == Material.FLINT;
    }

    @Override
    public boolean isDebugging(UUID playerId) {
        Player player = server.getPlayer(playerId);
        if (player == null) {
            return false;
        }
        Material offhandMaterial = player.getInventory().getItemInOffHand().getType();
        return player.hasPermission(DEV_PERMISSION_STRING) && offhandMaterial == Material.DEBUG_STICK;
    }
}
