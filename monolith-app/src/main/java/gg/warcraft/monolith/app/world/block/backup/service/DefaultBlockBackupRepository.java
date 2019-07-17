package gg.warcraft.monolith.app.world.block.backup.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gg.warcraft.monolith.api.core.JsonMapper;
import gg.warcraft.monolith.api.core.PersistenceService;
import gg.warcraft.monolith.api.core.PluginLogger;
import gg.warcraft.monolith.api.math.Vector3i;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.block.backup.BlockBackup;
import gg.warcraft.monolith.api.world.block.backup.service.BlockBackupRepository;
import gg.warcraft.monolith.app.world.block.backup.SimpleBlockBackup;
import gg.warcraft.monolith.app.world.block.backup.persistence.BlockBackupItem;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Singleton
public class DefaultBlockBackupRepository implements BlockBackupRepository {
    private static final String KEY_PREFIX = "blockbackup:";

    private final PersistenceService persistenceService;
    private final ObjectMapper jsonMapper;
    private final Logger pluginLogger;

    @Inject
    public DefaultBlockBackupRepository(PersistenceService persistenceService,
                                        @JsonMapper ObjectMapper jsonMapper, @PluginLogger Logger pluginLogger) {
        this.persistenceService = persistenceService;
        this.jsonMapper = jsonMapper;
        this.pluginLogger = pluginLogger;
    }

    private String createBackupKey(UUID id) {
        return KEY_PREFIX + id;
    }

    BlockBackup mapItemToBackup(BlockBackupItem item) {
        BlockLocation location = new BlockLocation(item.getWorld(), new Vector3i(item.getX(), item.getY(), item.getZ()));
        return new SimpleBlockBackup(item.getId(), item.getType(), item.getData(), location);
    }

    @Override
    public BlockBackup get(UUID id) {
        String backupKey = createBackupKey(id);
        String backupJson = persistenceService.get(backupKey);
        if (backupJson == null) {
            return null;
        }

        try {
            BlockBackupItem backupItem = jsonMapper.readValue(backupJson, BlockBackupItem.class);
            return mapItemToBackup(backupItem);
        } catch (IOException ex) {
            pluginLogger.severe(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<BlockBackup> getAll() {
        return persistenceService.getAllKeys(KEY_PREFIX).stream()
                .map(dataKey -> {
                    String uuidString = dataKey.substring(dataKey.lastIndexOf(":") + 1);
                    return UUID.fromString(uuidString);
                })
                .map(this::get)
                .collect(Collectors.toList());
    }

    BlockBackupItem mapBackupToItem(BlockBackup backup) {
        BlockLocation location = backup.getLocation();
        return new BlockBackupItem(backup.getId(), backup.getType(), backup.getData(), location.world(),
                location.x(), location.y(), location.z());
    }

    @Override
    public void save(BlockBackup backup) {
        String backupKey = createBackupKey(backup.getId());
        BlockBackupItem backupItem = mapBackupToItem(backup);
        try {
            String backupJson = jsonMapper.writeValueAsString(backupItem);
            persistenceService.set(backupKey, backupJson);
        } catch (IOException ex) {
            pluginLogger.severe(ex.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
        persistenceService.delete(createBackupKey(id));
    }
}
