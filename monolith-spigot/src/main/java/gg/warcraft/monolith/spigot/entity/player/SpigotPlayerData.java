package gg.warcraft.monolith.spigot.entity.player;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.player.GameMode;
import gg.warcraft.monolith.api.entity.player.PlayerServerData;
import gg.warcraft.monolith.api.item.Inventory;
import gg.warcraft.monolith.spigot.Implicits;
import gg.warcraft.monolith.spigot.entity.SpigotEntityData;
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper;
import gg.warcraft.monolith.spigot.item.SpigotInventory;
import gg.warcraft.monolith.spigot.item.SpigotItemMapper;
import org.bukkit.entity.Player;

import static com.google.common.base.Preconditions.checkState;

public class SpigotPlayerData extends SpigotEntityData implements PlayerServerData {
    private final SpigotItemMapper itemMapper;
    private final SpigotGameModeMapper gameModeMapper;
    private final Player player;

    @Inject
    public SpigotPlayerData(SpigotLocationMapper locationMapper,
                            SpigotItemMapper itemMapper, SpigotGameModeMapper gameModeMapper, @Assisted Player player) {
        super(locationMapper, itemMapper, player);
        this.itemMapper = itemMapper;
        this.gameModeMapper = gameModeMapper;
        this.player = player;
    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

    @Override
    public GameMode getGameMode() {
        GameMode gameMode = gameModeMapper.map(player.getGameMode());
        checkState(gameMode != null);
        return gameMode;
    }

    @Override
    public Inventory getInventory() {
        return new SpigotInventory(player.getInventory(),
                Implicits.itemTypeMapper(),
                Implicits.itemVariantMapper(),
                Implicits.itemMapper()); // TODO remove
    }

    @Override
    public boolean isSneaking() {
        return player.isSneaking();
    }

    @Override
    public boolean isOnline() {
        return player.isOnline();
    }
}
