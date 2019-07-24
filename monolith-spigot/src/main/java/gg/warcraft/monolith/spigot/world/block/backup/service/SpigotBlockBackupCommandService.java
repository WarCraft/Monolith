package gg.warcraft.monolith.spigot.world.block.backup.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.core.PluginLogger;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.backup.BlockBackup;
import gg.warcraft.monolith.api.world.block.backup.service.BlockBackupCommandService;
import gg.warcraft.monolith.api.world.block.backup.service.BlockBackupRepository;
import gg.warcraft.monolith.app.world.block.backup.SimpleBlockBackup;
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper;
import gg.warcraft.monolith.spigot.world.block.SpigotBlockTypeMapper;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class SpigotBlockBackupCommandService implements BlockBackupCommandService {
    private static final String META_DATA_KEY = SpigotBlockBackupCommandService.class.getCanonicalName();

    private final BlockBackupRepository blockBackupRepository;
    private final SpigotLocationMapper locationMapper;
    private final SpigotBlockTypeMapper blockTypeMapper;
    private final Plugin plugin;
    private final Logger pluginLogger;

    @Inject
    public SpigotBlockBackupCommandService(BlockBackupRepository blockBackupRepository,
                                           SpigotLocationMapper locationMapper,
                                           SpigotBlockTypeMapper blockTypeMapper,
                                           Plugin plugin, @PluginLogger Logger pluginLogger) {
        this.blockBackupRepository = blockBackupRepository;
        this.locationMapper = locationMapper;
        this.blockTypeMapper = blockTypeMapper;
        this.plugin = plugin;
        this.pluginLogger = pluginLogger;
    }

    @Override
    public UUID createBlockBackup(BlockLocation location) {
        Location spigotLocation = locationMapper.map(location);
        Block block = spigotLocation.getBlock();

        List<MetadataValue> metaData = block.getMetadata(META_DATA_KEY);
        if (!metaData.isEmpty()) {
            String idString = metaData.get(0).asString();
            return UUID.fromString(idString);
        }

        UUID id = UUID.randomUUID();
        BlockType type = blockTypeMapper.map(block.getType());
        int data = block.getData();
        BlockBackup blockBackup = new SimpleBlockBackup(id, type, data, location);

        MetadataValue newMetaData = new FixedMetadataValue(plugin, id.toString());
        block.setMetadata(META_DATA_KEY, newMetaData);
        blockBackupRepository.save(blockBackup);
        return id;
    }

    @Override
    public void restoreBlockBackup(UUID id) {
        BlockBackup blockBackup = blockBackupRepository.get(id);
        if (blockBackup == null) {
            pluginLogger.warning("Attempted to restore block backup with unknown id: " + id);
            return;
        }

        Location spigotLocation = locationMapper.map(blockBackup.getLocation());
        Block block = spigotLocation.getBlock();
        List<MetadataValue> metaData = block.getMetadata(META_DATA_KEY);
        if (!metaData.isEmpty() && !metaData.get(0).asString().equals(id.toString())) {
            pluginLogger.warning("Restoring block backup for block that has different backup meta data.");
        }

        BlockState blockState = block.getState();
        blockState.setTypeId(blockBackup.getType().getId());
        blockState.setRawData((byte) blockBackup.getData());
        blockState.update(true, false);

        block.removeMetadata(META_DATA_KEY, plugin);
        blockBackupRepository.delete(id);
    }
}
