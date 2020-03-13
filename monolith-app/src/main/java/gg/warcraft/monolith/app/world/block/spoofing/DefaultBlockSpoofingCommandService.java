package gg.warcraft.monolith.app.world.block.spoofing;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.WorldService;
import gg.warcraft.monolith.api.block.Block;
import gg.warcraft.monolith.api.block.spoofing.BlockSpoofingCommandService;
import gg.warcraft.monolith.api.block.spoofing.BlockSpoofingRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DefaultBlockSpoofingCommandService implements BlockSpoofingCommandService {
    private final BlockSpoofingRepository blockSpoofingRepository;
    private final WorldService worldService;

    @Inject
    public DefaultBlockSpoofingCommandService(BlockSpoofingRepository blockSpoofingRepository,
                                              WorldService worldService) {
        this.blockSpoofingRepository = blockSpoofingRepository;
        this.worldService = worldService;
    }

    @Override
    public void spoofBlock(Block fakeBlock, UUID... playerIds) {
        Arrays.stream(playerIds).forEach(playerId -> {
            worldService.spoofBlock(fakeBlock, playerId);
            blockSpoofingRepository.save(fakeBlock, playerId);
        });
    }

    @Override
    public void unspoofBlock(BlockLocation location, UUID... playerIds) {
        Block realBlock = worldService.getBlock(location);
        Arrays.stream(playerIds).forEach(playerId -> {
            worldService.spoofBlock(realBlock, playerId);
            blockSpoofingRepository.delete(location, playerId);
        });
    }

    @Override
    public void unspoofAll(UUID... playerIds) {
        Map<BlockLocation, Block> realBlocks = new HashMap<>();
        Arrays.stream(playerIds).forEach(playerId -> {
            List<Block> fakeBlocks = blockSpoofingRepository.getSpoofedBlocks(playerId);
            fakeBlocks.forEach(fakeBlock -> {
                BlockLocation location = fakeBlock.location();
                Block realBlock = realBlocks.computeIfAbsent(location, worldService::getBlock);
                worldService.spoofBlock(realBlock, playerId);
                blockSpoofingRepository.delete(location, playerId);
            });
        });
    }
}
