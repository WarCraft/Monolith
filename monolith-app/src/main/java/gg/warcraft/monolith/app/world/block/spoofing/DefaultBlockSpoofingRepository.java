package gg.warcraft.monolith.app.world.block.spoofing;

import com.google.inject.Singleton;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.block.Block;
import gg.warcraft.monolith.api.block.spoofing.BlockSpoofingRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Singleton
public class DefaultBlockSpoofingRepository implements BlockSpoofingRepository {
    private final Map<UUID, Map<BlockLocation, Block>> spoofedBlocks;

    public DefaultBlockSpoofingRepository() {
        this.spoofedBlocks = new HashMap<>();
    }

    @Override
    public Block getSpoofedBlock(BlockLocation location, UUID playerId) {
        Map<BlockLocation, Block> playerSpoofedBlocks = spoofedBlocks.get(playerId);
        if (playerSpoofedBlocks == null) {
            return null;
        }
        return playerSpoofedBlocks.get(location);
    }

    @Override
    public List<Block> getSpoofedBlocks(UUID playerId) {
        Map<BlockLocation, Block> playerSpoofedBlocks = spoofedBlocks.get(playerId);
        if (playerSpoofedBlocks == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(playerSpoofedBlocks.values());
    }

    @Override
    public void save(Block fakeBlock, UUID playerId) {
        Map<BlockLocation, Block> playerSpoofedBlocks =
                spoofedBlocks.computeIfAbsent(playerId, key -> new HashMap<>());
        playerSpoofedBlocks.put(fakeBlock.location(), fakeBlock);
    }

    @Override
    public void delete(BlockLocation location, UUID playerId) {
        Map<BlockLocation, Block> playerSpoofedBlocks = spoofedBlocks.get(playerId);
        if (playerSpoofedBlocks == null) {
            return;
        }
        playerSpoofedBlocks.remove(location);
        if (playerSpoofedBlocks.isEmpty()) {
            spoofedBlocks.remove(playerId);
        }
    }
}
